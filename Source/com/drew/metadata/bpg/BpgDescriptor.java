package com.drew.metadata.bpg;

import com.drew.lang.annotations.Nullable;
import com.drew.metadata.TagDescriptor;

import static com.drew.metadata.bpg.BpgDirectory.*;

/**
 * @author Payton Garland
 */
public class BpgDescriptor extends TagDescriptor<BpgDirectory>
{
    public BpgDescriptor(BpgDirectory directory)
    {
        super(directory);
    }

    @Override
    public String getDescription(int tagType)
    {
        switch (tagType) {
            case TAG_DEPTH:
                return _directory.getInteger(tagType) + " bits";
            case TAG_WIDTH:
            case TAG_HEIGHT:
                return _directory.getInteger(tagType) + " pixels";
            case TAG_PIXEL_FORMAT:
                return getPixelFormatDescription();
            case TAG_COLOR_SPACE:
                return getColorSpaceDescription();
            default:
                return super.getDescription(tagType);
        }
    }

    @Nullable
    public String getPixelFormatDescription()
    {
        Integer value = _directory.getInteger(TAG_PIXEL_FORMAT);
        if (value == null) {
            return null;
        }
        switch (value) {
            case (0):
                return "Grayscale";
            case (1):
                return "4:2:0. Chroma at position (0.5, 0.5) (JPEG chroma position)";
            case (2):
                return "4:2:2. Chroma at position (0.5, 0) (JPEG chroma position)";
            case (3):
                return "4:4:4";
            case (4):
                return "4:3:0. Chroma at position (0, 0.5) (MPEG2 chroma position)";
            case (5):
                return "4:2:2. Chroma at position (0, 0) (MPEG2 chroma position)";
            default:
                return "Reserved";
        }
    }

    @Nullable
    public String getColorSpaceDescription()
    {
        Integer value = _directory.getInteger(TAG_COLOR_SPACE);
        if (value == null) {
            return null;
        }
        switch (value) {
            case (0):
                return "YCbCr (BT 601, same as JPEG and HEVC matrix_coeffs = 5)";
            case (1):
                return "RGB (component order: G B R)";
            case (2):
                return "YCgCo (same as HEVC matrix_coeffs = 8)";
            case (3):
                return "YCbCr (BT 709, same as HEVC matrix_coeffs = 1)";
            case (4):
                return "YCbCr (BT 2020 non constant luminance system, same as HEVC matrix_coeffs = 9)";
            case (5):
                return "reserved for BT 2020 constant luminance system, not supported in this version of the specification.";
            default:
                return "Reserved";
        }
    }
}
