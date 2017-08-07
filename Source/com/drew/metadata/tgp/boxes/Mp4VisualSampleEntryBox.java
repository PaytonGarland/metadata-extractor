package com.drew.metadata.tgp.boxes;

import com.drew.lang.SequentialReader;
import com.drew.metadata.tgp.media.TgpVideoDirectory;

import java.io.IOException;

/**
 * @author Payton Garland
 */
public class Mp4VisualSampleEntryBox extends Box
{
    int dataReferenceIndex;
    int width;
    int height;

    public Mp4VisualSampleEntryBox(SequentialReader reader, Box box) throws IOException
    {
        super(box);

        reader.skip(6); // Reserved
        dataReferenceIndex = reader.getUInt16();
        reader.skip(16); // Reserved
        width = reader.getUInt16();
        height = reader.getUInt16();
        reader.skip(4); // Reserved
        reader.skip(4); // Reserved
        reader.skip(4); // Reserved
        reader.skip(2); // Reserved
        reader.skip(32); // Reserved
        reader.skip(2); // Reserved
        // ESDBox
    }

    public void addMetadata(TgpVideoDirectory directory)
    {
        directory.setInt(TgpVideoDirectory.TAG_WIDTH, width);
        directory.setInt(TgpVideoDirectory.TAG_HEIGHT, height);
    }
}
