package com.drew.metadata.tgp.boxes;

import com.drew.lang.SequentialReader;

import java.io.IOException;

/**
 * @author Payton Garland
 */
public class FullBox extends Box
{
    protected int version;
    protected byte[] flags;

    public FullBox(SequentialReader reader, Box box) throws IOException
    {
        super(box);

        version = reader.getUInt8();
        flags = reader.getBytes(3);
    }
}
