package com.drew.metadata.tgp.boxes;

import com.drew.lang.SequentialReader;
import com.drew.metadata.tgp.media.TgpVideoDirectory;

import java.io.IOException;

/**
 * ISO/IED 14496-12:2015 pg.155
 */
public class VideoMediaHeaderBox extends FullBox
{
    int graphicsmode;
    int[] opcolor;

    public VideoMediaHeaderBox(SequentialReader reader, Box box) throws IOException
    {
        super(reader, box);

        graphicsmode = reader.getUInt16();
        opcolor = new int[]{reader.getUInt16(), reader.getUInt16(), reader.getUInt16()};
    }

    public void addMetadata(TgpVideoDirectory directory)
    {
        directory.setIntArray(TgpVideoDirectory.TAG_OPCOLOR, opcolor);

        switch (graphicsmode) {
            case (0x00):
                directory.setString(TgpVideoDirectory.TAG_GRAPHICS_MODE, "Copy");
                break;
            case (0x40):
                directory.setString(TgpVideoDirectory.TAG_GRAPHICS_MODE, "Dither copy");
                break;
            case (0x20):
                directory.setString(TgpVideoDirectory.TAG_GRAPHICS_MODE, "Blend");
                break;
            case (0x24):
                directory.setString(TgpVideoDirectory.TAG_GRAPHICS_MODE, "Transparent");
                break;
            case (0x100):
                directory.setString(TgpVideoDirectory.TAG_GRAPHICS_MODE, "Straight alpha");
                break;
            case (0x101):
                directory.setString(TgpVideoDirectory.TAG_GRAPHICS_MODE, "Premul white alpha");
                break;
            case (0x102):
                directory.setString(TgpVideoDirectory.TAG_GRAPHICS_MODE, "Premul black alpha");
                break;
            case (0x104):
                directory.setString(TgpVideoDirectory.TAG_GRAPHICS_MODE, "Straight alpha blend");
                break;
            case (0x103):
                directory.setString(TgpVideoDirectory.TAG_GRAPHICS_MODE, "Composition (dither copy)");
                break;
            default:
        }
    }
}
