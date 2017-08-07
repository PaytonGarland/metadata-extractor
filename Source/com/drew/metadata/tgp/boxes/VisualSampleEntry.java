package com.drew.metadata.tgp.boxes;

import com.drew.lang.SequentialReader;
import com.drew.metadata.tgp.media.TgpVideoDirectory;

import java.io.IOException;

/**
 * ISO/IED 14496-12:2015 pg.156
 */
public class VisualSampleEntry extends SampleEntry
{
    int version;
    int revisionLevel;
    String vendor;
    int temporalQuality;
    int spatialQuality;
    int width;
    int height;
    long horizresolution;
    long vertresolution;
    int frameCount;
    String compressorname;
    int depth;

    public VisualSampleEntry(SequentialReader reader, Box box) throws IOException
    {
        super(reader, box);

        version = reader.getInt16();
        revisionLevel = reader.getInt16();
        vendor = reader.getString(4);
        temporalQuality = reader.getInt32();
        spatialQuality = reader.getInt32();
        width = reader.getUInt16();
        height = reader.getUInt16();
        horizresolution = reader.getUInt32();
        vertresolution = reader.getUInt32();
        reader.skip(4); // Reserved
        frameCount = reader.getUInt16();
        compressorname = reader.getString(32);
        depth = reader.getUInt16();
        reader.skip(2); // Pre-defined
    }

    public void addMetadata(TgpVideoDirectory directory)
    {
        directory.setInt(TgpVideoDirectory.TAG_WIDTH, width);
        directory.setInt(TgpVideoDirectory.TAG_HEIGHT, height);
        directory.setString(TgpVideoDirectory.TAG_COMPRESSION_TYPE, compressorname.trim());
        directory.setInt(TgpVideoDirectory.TAG_DEPTH, depth);

        // Calculate horizontal res
        double horizontalInteger = (horizresolution & 0xFFFF0000) >> 16;
        double horizontalFraction = (horizresolution & 0xFFFF) / Math.pow(2, 4);
        directory.setDouble(TgpVideoDirectory.TAG_HORIZONTAL_RESOLUTION, horizontalInteger + horizontalFraction);

        double verticalInteger = (vertresolution & 0xFFFF0000) >> 16;
        double verticalFraction = (vertresolution & 0xFFFF) / Math.pow(2, 4);
        directory.setDouble(TgpVideoDirectory.TAG_VERTICAL_RESOLUTION, verticalInteger + verticalFraction);
    }
}
