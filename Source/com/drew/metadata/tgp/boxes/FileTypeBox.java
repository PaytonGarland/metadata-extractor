package com.drew.metadata.tgp.boxes;

import com.drew.lang.SequentialReader;
import com.drew.metadata.tgp.TgpDirectory;

import java.io.IOException;
import java.util.ArrayList;

/**
 * ISO/IED 14496-12:2015 pg.8
 */
public class FileTypeBox extends Box
{
    String majorBrand;
    long minorVersion;
    ArrayList<String> compatibleBrands;

    public FileTypeBox(SequentialReader reader, Box box) throws IOException
    {
        super(box);

        majorBrand = reader.getString(4);
        minorVersion = reader.getUInt32();
        compatibleBrands = new ArrayList<String>();
        for (int i = 16; i < size; i += 4) {
            compatibleBrands.add(reader.getString(4));
        }
    }

    public void addMetadata(TgpDirectory directory)
    {
        directory.setString(TgpDirectory.TAG_MAJOR_BRAND, majorBrand);
        directory.setLong(TgpDirectory.TAG_MINOR_VERSION, minorVersion);
        directory.setStringArray(TgpDirectory.TAG_COMPATIBLE_BRANDS, compatibleBrands.toArray(new String[compatibleBrands.size()]));
    }
}
