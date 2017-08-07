package com.drew.metadata.tgp.boxes;

import com.drew.lang.SequentialReader;
import com.drew.metadata.tgp.media.TgpSoundDescriptor;
import com.drew.metadata.tgp.media.TgpSoundDirectory;

import java.io.IOException;

/**
 * @author Payton Garland
 */
public class Mp4AudioSampleEntryBox extends Box
{
    int dataReferenceIndex;
    int timescale;

    public Mp4AudioSampleEntryBox(SequentialReader reader, Box box) throws IOException
    {
        super(box);

        reader.skip(6); // Reserved
        dataReferenceIndex = reader.getUInt16();
        reader.skip(8); // Reserved
        reader.skip(2); // Reserved
        reader.skip(2); // Reserved
        reader.skip(4); // Reserved
        timescale = reader.getUInt16();
        reader.skip(2); // Reserved
        // ESDBox
    }

    public void addMetadata(TgpSoundDirectory directory)
    {
    }
}
