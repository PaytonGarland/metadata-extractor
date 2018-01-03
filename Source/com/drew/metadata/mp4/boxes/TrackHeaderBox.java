package com.drew.metadata.mp4.boxes;

import com.drew.lang.SequentialReader;

import java.io.IOException;

/**
 * ISO/IEC 14496-12:2015 pg.24-26
 */
public class TrackHeaderBox extends FullBox
{
    protected long creationTime;
    protected long modificationTime;
    protected long trackId;
    protected long duration;
    protected int layer;
    protected int alternateGroup;
    protected int volume;
    protected int[] matrix;
    protected long width;
    protected long height;

    public TrackHeaderBox(SequentialReader reader, Box box) throws IOException
    {
        super(reader, box);

        if (version == 1) {
            creationTime = reader.getInt64();
            modificationTime = reader.getInt64();
            trackId = reader.getUInt32();
            reader.skip(4); // Reserved
            duration = reader.getInt64();
        } else {
            creationTime = reader.getUInt32();
            modificationTime = reader.getUInt32();
            trackId = reader.getUInt32();
            reader.skip(4); // Reserved
            duration = reader.getUInt32();
        }
        reader.skip(64); // Reserved
        layer = reader.getInt16();
        alternateGroup = reader.getInt16();
        volume = reader.getInt16();
        reader.skip(2);
        matrix = new int[]{reader.getInt32(),
            reader.getInt32(),
            reader.getInt32(),
            reader.getInt32(),
            reader.getInt32(),
            reader.getInt32(),
            reader.getInt32(),
            reader.getInt32(),
            reader.getInt32()
        };
        width = reader.getUInt32();
        height = reader.getUInt32();
    }
}
