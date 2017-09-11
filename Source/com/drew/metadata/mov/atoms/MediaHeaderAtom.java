/*
 * Copyright 2002-2017 Drew Noakes
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
package com.drew.metadata.mov.atoms;

import com.drew.lang.SequentialReader;
import com.drew.metadata.mov.QuickTimeDirectory;
import com.drew.metadata.mov.QuickTimeHandlerFactory;

import java.io.IOException;

/**
 * https://developer.apple.com/library/content/documentation/QuickTime/QTFF/QTFFChap2/qtff2.html#//apple_ref/doc/uid/TP40000939-CH204-SW34
 *
 * @author Payton Garland
 */
public class MediaHeaderAtom extends FullAtom
{
    long creationTime;
    long modificationTime;
    long timescale;
    long duration;
    int language;
    int quality;

    public MediaHeaderAtom(SequentialReader reader, Atom atom) throws IOException
    {
        super(reader, atom);

        creationTime = reader.getUInt32();
        modificationTime = reader.getUInt32();
        timescale = reader.getUInt32();
        duration = reader.getUInt32();
        language = reader.getUInt16();
        quality = reader.getUInt16();
    }

    public void addMetadata(QuickTimeDirectory directory)
    {
        directory.setLong(QuickTimeDirectory.TAG_MEDIA_CREATION_TIME, creationTime);
        directory.setLong(QuickTimeDirectory.TAG_MEDIA_MODIFICATION_TIME, modificationTime);
        directory.setLong(QuickTimeDirectory.TAG_MEDIA_TIME_SCALE, timescale);
        directory.setLong(QuickTimeDirectory.TAG_MEDIA_DURATION, duration);
        directory.setInt(QuickTimeDirectory.TAG_MEDIA_LANGUAGE, language);
        directory.setInt(QuickTimeDirectory.TAG_MEDIA_QUALITY, quality);
    }
}
