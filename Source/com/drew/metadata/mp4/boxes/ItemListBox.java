/*
 * Copyright 2002-2019 Drew Noakes and contributors
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 *
 * More information about this project is available at:
 *
 *    https://drewnoakes.com/code/exif/
 *    https://github.com/drewnoakes/metadata-extractor
 */
package com.drew.metadata.mp4.boxes;

import com.drew.lang.SequentialReader;
import com.drew.metadata.mp4.media.Mp4MetaBoxDirectory;

import java.io.EOFException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class ItemListBox extends Box
{
    private class ItemListEntry
    {
        private int tag;
        private String val;

        ItemListEntry(int tag) {
            this.tag = tag;
            this.val = null;
        }

        int getTag() {
            return this.tag;
        }

        void setVal(String val) {
            this.val = val;
        }

        String getVal() {
            return this.val;
        }
    }

    public static Map<String, ItemListEntry> itemMap;

    {
        itemMap = new HashMap<String, ItemListEntry>();

        itemMap.put("�alb", new ItemListEntry(Mp4MetaBoxDirectory.TAG_ALBUM));
        itemMap.put("aART", new ItemListEntry(Mp4MetaBoxDirectory.TAG_ALBUM_ARTIST));
    }

    public ItemListBox(SequentialReader reader, Box box) throws IOException
    {
        super(box);

        long totalRead = 0;
        try {
            while (totalRead < box.size) {
                long recordLen = reader.getUInt32();
                String fieldName = reader.getString(4);
                long fieldLen = reader.getUInt32();
                String typeName = reader.getString(4);//data
                totalRead += 16;
                if ("data".equals(typeName)) {
                    reader.skip(8);//not sure what these are
                    totalRead += 8;
                    int toRead = (int) fieldLen - 16;
                    if (toRead <= 0) {
                        //log?
                        return;
                    }
                    if ("covr".equals(fieldName)) {
                        //covr can be an image file, e.g. png or jpeg
                        //skip this for now
                        reader.skip(toRead);
                    } else if ("cpil".equals(fieldName)) {
                        int compilationId = (int) reader.getByte();
//                    metadata.set(XMPDM.COMPILATION, compilationId);
                    } else if ("trkn".equals(fieldName)) {
                        if (toRead == 8) {
                            long numA = reader.getUInt32();
                            long numB = reader.getUInt32();
//                        metadata.set(XMPDM.TRACK_NUMBER, (int)numA);
                        } else {
                            //log
                            reader.skip(toRead);
                        }
                    } else if ("disk".equals(fieldName)) {
                        int a = reader.getInt32();
                        short b = reader.getInt16();
//                    metadata.set(XMPDM.DISC_NUMBER, a);
                    } else {
                        String val = reader.getString(toRead);
                        if (itemMap.containsKey(fieldName)) {
                            ItemListEntry entry = itemMap.get(fieldName);
                            entry.setVal(val);
                            itemMap.put(fieldName, entry);
                        }
//                    try {
//                        addMetadata(fieldName, val);
//                    } catch (SAXException e) {
//                        //need to punch through IOException catching in MP4Reader
//                        throw new RuntimeSAXException(e);
//                    }
                    }

                    totalRead += toRead;
                } else {
                    int toSkip = (int) recordLen - 16;
                    if (toSkip <= 0) {
                        //log?
                        return;
                    }
                    reader.skip(toSkip);
                    totalRead += toSkip;
                }
            }
        } catch (EOFException e) {
            System.out.println("EOF reached");
        }
    }

    public void addMetadata(Mp4MetaBoxDirectory directory)
    {
        for (Map.Entry<String, ItemListEntry> entry : itemMap.entrySet()) {
            if (entry.getValue().getVal() != null) {
                directory.setString(entry.getValue().getTag(), entry.getValue().getVal());
            }
        }
    }
}
