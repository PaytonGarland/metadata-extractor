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
package com.drew.metadata.mp4.media;

import com.drew.lang.annotations.NotNull;

import java.util.HashMap;

public class Mp4MetaBoxDirectory extends Mp4MediaDirectory
{
    public static final Integer TAG_ALBUM = 1001;
    public static final Integer TAG_ALBUM_ARTIST = 1002;

    @NotNull
    private static final HashMap<Integer, String> _tagNameMap = new HashMap<Integer, String>();

    static
    {
        Mp4MetaBoxDirectory.addMp4MediaTags(_tagNameMap);
        _tagNameMap.put(TAG_ALBUM, "Album");
        _tagNameMap.put(TAG_ALBUM_ARTIST, "Album Artist");
    }

    public Mp4MetaBoxDirectory()
    {
        this.setDescriptor(new Mp4MetaBoxDescriptor(this));
    }

    @NotNull
    @Override
    public String getName()
    {
        return "QuickTime Metadata";
    }

    @NotNull
    @Override
    protected HashMap<Integer, String> getTagNameMap()
    {
        return _tagNameMap;
    }
}
