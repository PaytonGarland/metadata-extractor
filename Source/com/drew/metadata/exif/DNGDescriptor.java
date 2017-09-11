package com.drew.metadata.exif;

import com.drew.lang.Rational;
import com.drew.lang.annotations.Nullable;
import com.drew.metadata.TagDescriptor;

import java.util.Arrays;

import static com.drew.metadata.exif.DNGDirectory.*;

/**
 * @author Payton Garland
 */
public class DNGDescriptor extends TagDescriptor<DNGDirectory>
{

    public DNGDescriptor(DNGDirectory directory)
    {
        super(directory);
    }

    @Nullable
    @Override
    public String getDescription(int tagType)
    {
        // TODO order case blocks and corresponding methods in the same order as the TAG_* values are defined

        switch (tagType) {
            case TAG_DNG_BACKWARD_VERSION:
            case TAG_DNG_VERSION:
                return getDngVersionDescription(tagType);
            case TAG_CFA_LAYOUT:
                return getCfaLayoutDescription();
            case TAG_BLACK_LEVEL_REPEAT_DIM:
                return getBlackLevelRepeatDimDescription();
            case TAG_MAKER_NOTE_SAFETY:
                return getMakerNoteSafetyDescription();
            case TAG_LENS_INFO:
                return getLensInfoDescription();
            case TAG_ACTIVE_AREA:
                return getActiveAreaDescription();
            case TAG_COLORIMETRIC_REFERENCE:
                return getColorimetricReferenceDescription();
            case TAG_PROFILE_EMBED_POLICY:
                return getProfileEmbedPolicyDescription();
            case TAG_PREVIEW_COLOR_SPACE:
                return getPreviewColorSpaceDescription();
            case TAG_DEFAULT_BLACK_RENDER:
                return getDefaultBlackRenderDescription();
            case TAG_PROFILE_HUE_SAT_MAP_ENCODING:
            case TAG_PROFILE_LOOK_TABLE_ENCODING:
                return getProfileEncodingDescription(tagType);
            case TAG_DEFAULT_CROP_SIZE:
            case TAG_ORIGINAL_DEFAULT_CROP_SIZE:
            case TAG_ORIGINAL_BEST_QUALITY_FINAL_SIZE:
            case TAG_ORIGINAL_DEFAULT_FINAL_SIZE:
                return getSizeDescription();
            default:
                return super.getDescription(tagType);
        }
    }

    @Nullable
    public String getDngVersionDescription(int tagType)
    {
        byte[] version = _directory.getByteArray(tagType);
        if (version == null)
            return null;
        StringBuilder versionString = new StringBuilder();
        for (byte b : version) {
            versionString.append((int)b);
            versionString.append('.');
        }
        versionString.deleteCharAt(versionString.length() - 1);
        return versionString.toString();
    }

    @Nullable
    public String getCfaLayoutDescription()
    {
        Integer value = _directory.getInteger(TAG_CFA_LAYOUT);
        if (value == null)
            return null;
        switch (value) {
            case 1:
                return "Rectangular (or square) layout";
            case 2:
                return "Staggered layout A: even columns are offset down by 1/2 row";
            case 3:
                return "Staggered layout B: even columns are offset up by 1/2 row";
            case 4:
                return "Staggered layout C: even rows are offset right by 1/2 column";
            case 5:
                return "Staggered layout D: even rows are offset left by 1/2 column";
            case 6:
                return "Staggered layout E: even rows are offset up by 1/2 row, even columns are offset left by 1/2 column";
            case 7:
                return "Staggered layout F: even rows are offset up by 1/2 row, even columns are offset right by 1/2 column";
            case 8:
                return "Staggered layout G: even rows are offset down by 1/2 row, even columns are offset left by 1/2 column";
            case 9:
                return "Staggered layout H: even rows are offset down by 1/2 row, even columns are offset right by 1/2 column";
            default:
                return "Unknown (" + value + ")";
        }
    }

    @Nullable
    public String getBlackLevelRepeatDimDescription()
    {
        byte[] patternSizes = _directory.getByteArray(TAG_BLACK_LEVEL_REPEAT_DIM);
        if (patternSizes == null)
            return null;

        StringBuilder repeatSize = new StringBuilder();

        for (byte patternSize : patternSizes) {
            switch (patternSize) {
                case 0:
                    repeatSize.append("BlackLevelRepeatRows" + ", ");
                    break;
                case 1:
                    repeatSize.append("BlackLevelRepeatCols" + ", ");
                    break;
                default:
                    repeatSize.append("Unknown (" + (int)patternSize + ")" + ", ");
            }
        }

        repeatSize.delete(repeatSize.length() - 2, repeatSize.length() - 1);
        return repeatSize.toString();
    }

    @Nullable
    public String getMakerNoteSafetyDescription()
    {
        Integer value = _directory.getInteger(TAG_MAKER_NOTE_SAFETY);
        if (value == null)
            return null;
        switch (value) {
            case 1:
                return "safe";
            case 2:
                return "unsafe";
            default:
                return "Unknown (" + value + ")";
        }
    }

    @Nullable
    public String getSizeDescription()
    {
        Object values = _directory.getObject(TAG_DEFAULT_CROP_SIZE);
        StringBuilder defaultCropSize = new StringBuilder();
        if (values instanceof Rational[]) {
            Rational[] rationals = (Rational[])values;
            if (rationals.length >= 2) {
                defaultCropSize.append("Image Width: " + rationals[0].getNumerator() + "px, ");
                defaultCropSize.append("Image Length: " + rationals[1].getNumerator() + "px");
            }
        } else if (values instanceof short[]) {
            short[] shorts = (short[])values;
            if (shorts.length >= 2) {
                defaultCropSize.append("Image Width: " + shorts[0] + "px, ");
                defaultCropSize.append("Image Length: " + shorts[1] + "px");
            }
        } else if (values instanceof long[]) {
            long[] longs = (long[])values;
            if (longs.length >= 2) {
                defaultCropSize.append("Image Width: " + longs[0] + "px, ");
                defaultCropSize.append("Image Length: " + longs[1] + "px");
            }
        } else {
            return null;
        }
        return defaultCropSize.toString();
    }


    @Nullable
    public String getLensInfoDescription()
    {
        Rational[] rationals = _directory.getRationalArray(TAG_LENS_INFO);
        if (rationals == null)
            return null;
        StringBuilder lensInfo = new StringBuilder();
        if (rationals.length >= 4) {
            lensInfo.append("Min Focal Length: " + rationals[0].getNumerator() + "mm, ");
            lensInfo.append("Max Focal Length: " + rationals[1].getNumerator() + "mm, ");
            if (rationals[2].getNumerator() == 0) {
                lensInfo.append("Min F-Stop at Min Focal Length: unknown, ");
            } else {
                lensInfo.append("Min F-Stop at Min Focal Length: " + rationals[2].getNumerator() + ", ");
            }
            if (rationals[3].getNumerator() == 0) {
                lensInfo.append("Min F-Stop at Max Focal Length: unknown");
            } else {
                lensInfo.append("Min F-Stop at Max Focal Length: " + rationals[3].getNumerator());
            }
        }
        return lensInfo.toString();
    }

    @Nullable
    public String getActiveAreaDescription()
    {
        Object values = _directory.getObject(TAG_ACTIVE_AREA);
        StringBuilder activeArea = new StringBuilder();
        if (values == null)
            return null;
        if (values instanceof short[]) {
            short[] shorts = (short[]) values;
            if (shorts.length == 4) {
                activeArea.append("Top: " + shorts[0] + ", ");
                activeArea.append("Left: " + shorts[1] + ", ");
                activeArea.append("Bottom: " + shorts[2] + ", ");
                activeArea.append("Right: " + shorts[3]);
            } else {
                activeArea.append("Unknown (" + Arrays.toString(shorts) + ")");
            }
        } else if (values instanceof long[]) {
            long[] longs = (long[]) values;
            if (longs.length == 4) {
                activeArea.append("Top: " + longs[0] + ", ");
                activeArea.append("Left: " + longs[1] + ", ");
                activeArea.append("Bottom: " + longs[2] + ", ");
                activeArea.append("Right: " + longs[3]);
            } else {
                activeArea.append("Unknown (" + Arrays.toString(longs) + ")");
            }
        } else {
            return null;
        }
        return activeArea.toString();
    }

    @Nullable
    public String getColorimetricReferenceDescription()
    {
        Integer value = _directory.getInteger(TAG_COLORIMETRIC_REFERENCE);
        if (value == null)
            return null;

        switch (value) {
            case 0:
                return "The XYZ values are scene-referred.";
            case 1:
                return "The XYZ values are output-referred, using the ICC profile perceptual dynamic range.";
            default:
                return "Unknown (" + value + ")";
        }
    }

    @Nullable
    public String getProfileEmbedPolicyDescription()
    {
        Long value = _directory.getLongObject(TAG_PROFILE_EMBED_POLICY);
        if (value == null)
            return null;
        if (value != (int)(long)value)
            return "Unknown (" + value + ")";

        switch ((int)(long)value) {
            case 0:
                return "allow copying";
            case 1:
                return "embed if used";
            case 2:
                return "embed never";
            case 3:
                return "no restrictions";
            default:
                return "Unknown (" + value + ")";
        }
    }

    @Nullable
    public String getPreviewColorSpaceDescription()
    {
        Long value = _directory.getLongObject(TAG_PREVIEW_COLOR_SPACE);
        if (value == null)
            return null;
        if (value != (int)(long)value)
            return "Unknown (" + value + ")";

        int intValue = (int)(long)value;

        switch (intValue) {
            case 0:
                return "Unknown";
            case 1:
                return "Gray Gamma 2.2";
            case 2:
                return "sRGB";
            case 3:
                return "Adobe RGB";
            case 4:
                return "ProPhoto RGB";
            default:
                return "Unknown (" + value + ")";
        }
    }

    @Nullable
    public String getDefaultBlackRenderDescription()
    {
        Long value = _directory.getLongObject(TAG_DEFAULT_BLACK_RENDER);
        if (value == null)
            return null;
        if (value != (int)(long)value)
            return "Unknown (" + value + ")";

        int intValue = (int)(long)value;

        switch (intValue) {
            case 0:
                return "Auto";
            case 1:
                return "None";
            default:
                return "Unknown (" + value + ")";
        }
    }

    @Nullable
    public String getProfileEncodingDescription(int tagType)
    {
        Long value = _directory.getLongObject(tagType);
        if (value == null)
            return null;
        if (value != (int)(long)value)
            return "Unknown (" + value + ")";

        int intValue = (int)(long)value;

        switch (intValue) {
            case 0:
                return "Linear encoding";
            case 1:
                return "sRGB encoding";
            default:
                return "Unknown (" + value + ")";
        }
    }
}
