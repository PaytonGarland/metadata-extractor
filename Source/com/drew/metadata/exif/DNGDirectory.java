package com.drew.metadata.exif;

import com.drew.lang.annotations.NotNull;
import com.drew.metadata.Directory;

import java.util.HashMap;

/**
 * @author Payton Garland
 */
public class DNGDirectory extends Directory
{
    /** DNG-specific tags http://wwwimages.adobe.com/content/dam/Adobe/en/products/photoshop/pdfs/dng_spec_1.4.0.0.pdf */
    public static final int TAG_DNG_VERSION                       = 0xC612;
    public static final int TAG_DNG_BACKWARD_VERSION              = 0xC613;
    public static final int TAG_UNIQUE_CAMERA_MODEL               = 0xC614;
    public static final int TAG_LOCALIZED_CAMERA_MODEL            = 0xC615;
    public static final int TAG_CFA_PLANE_COLOR                   = 0xC616;
    public static final int TAG_CFA_LAYOUT                        = 0xC617;
    public static final int TAG_LINEARIZATION_TABLE               = 0xC618;
    public static final int TAG_BLACK_LEVEL_REPEAT_DIM            = 0xC619;
    public static final int TAG_BLACK_LEVEL                       = 0xC61A;
    public static final int TAG_BLACK_LEVEL_DELTA_H               = 0xC61B;
    public static final int TAG_BLACK_LEVEL_DELTA_V               = 0xC61C;
    public static final int TAG_WHITE_LEVEL                       = 0xC61D;
    public static final int TAG_DEFAULT_SCALE                     = 0xC61E;
    public static final int TAG_BEST_QUALITY_SCALE                = 0xC65C;
    public static final int TAG_DEFAULT_CROP_ORIGIN               = 0xC61F;
    public static final int TAG_DEFAULT_CROP_SIZE                 = 0xC620;
    public static final int TAG_CALLIBRATION_ILLUMINANT_1         = 0xC65A;
    public static final int TAG_CALLIBRATION_ILLUMINANT_2         = 0xC65B;
    public static final int TAG_COLOR_MATRIX_1                    = 0xC621;
    public static final int TAG_COLOR_MATRIX_2                    = 0xC622;
    public static final int TAG_CAMERA_CALIBRATION_1              = 0xC623;
    public static final int TAG_CAMERA_CALIBRATION_2              = 0xC624;
    public static final int TAG_REDUCTION_MATRIX_1                = 0xC625;
    public static final int TAG_REDUCTION_MATRIX_2                = 0xC626;
    public static final int TAG_ANALOG_BALANCE                    = 0xC627;
    public static final int TAG_AS_SHOT_NEUTRAL                   = 0xC628;
    public static final int TAG_AS_SHOT_WHITE_X_Y                 = 0xC629;
    public static final int TAG_BASELINE_EXPOSURE                 = 0xC62A;
    public static final int TAG_BASELINE_NOISE                    = 0xC62B;
    public static final int TAG_BASELINE_SHARPNESS                = 0xC62C;
    public static final int TAG_BAYER_GREEN_SPLIT                 = 0xC62D;
    public static final int TAG_LINEAR_RESPONSE_LIMIT             = 0xC62E;
    public static final int TAG_CAMERA_SERIAL_NUMBER              = 0xC62F;
    public static final int TAG_LENS_INFO                         = 0xC630;
    public static final int TAG_CHROMA_BLUR_RADIUS                = 0xC631;
    public static final int TAG_ANTI_ALIAS_STRENGTH               = 0xC632;
    public static final int TAG_SHADOW_SCALE                      = 0xC633;
    public static final int TAG_DNG_PRIVATE_DATA                  = 0xC634;
    public static final int TAG_MAKER_NOTE_SAFETY                 = 0xC635;
    public static final int TAG_RAW_DATA_UNIQUE_ID                = 0xC65D;
    public static final int TAG_ORIGINAL_RAW_FILE_NAME            = 0xC68B;
    public static final int TAG_ORIGINAL_RAW_FILE_DATA            = 0xC68C;
    public static final int TAG_ACTIVE_AREA                       = 0xC68D;
    public static final int TAG_MASKED_AREAS                      = 0xC68E;
    public static final int TAG_AS_SHOT_ICC_PROFILE               = 0xC68F;
    public static final int TAG_AS_SHOT_PRE_PROFILE_MATRIX        = 0xC690;
    public static final int TAG_CURRENT_ICC_PROFILE               = 0xC691;
    public static final int TAG_CURRENT_PRE_PROFILE_MATRIX        = 0xC692;
    // 1.2.0.0
    public static final int TAG_COLORIMETRIC_REFERENCE            = 0xC6BF;
    public static final int TAG_CAMERA_CALIBRATION_SIGNATURE      = 0xC6F3;
    public static final int TAG_PROFILE_CALIBRATION_SIGNATURE     = 0xC6F4;
    public static final int TAG_EXTRA_CAMERA_PROFILES             = 0xC6F5;
    public static final int TAG_AS_SHOT_PROFILE_NAME              = 0xC6F6;
    public static final int TAG_NOISE_REDUCTION_APPLIED           = 0xC6F7;
    public static final int TAG_PROFILE_NAME                      = 0xC6F8;
    public static final int TAG_PROFILE_HUE_SAT_MAP_DIMS          = 0xC6F9;
    public static final int TAG_PROFILE_HUE_SAT_MAP_DATA_1        = 0xC6FA;
    public static final int TAG_PROFILE_HUE_SAT_MAP_DATA_2        = 0xC6FB;
    public static final int TAG_PROFILE_TONE_CURVE                = 0xC6FC;
    public static final int TAG_PROFILE_EMBED_POLICY              = 0xC6FD;
    public static final int TAG_PROFILE_COPYRIGHT                 = 0xC6FE;
    public static final int TAG_FORWARD_MATRIX_1                  = 0xC714;
    public static final int TAG_FORWARD_MATRIX_2                  = 0xC715;
    public static final int TAG_PREVIEW_APPLICATION_NAME          = 0xC716;
    public static final int TAG_PREVIEW_APPLICATION_VERSION       = 0xC717;
    public static final int TAG_PREVIEW_SETTINGS_NAME             = 0xC718;
    public static final int TAG_PREVIEW_SETTINGS_DIGEST           = 0xC719;
    public static final int TAG_PREVIEW_COLOR_SPACE               = 0xC71A;
    public static final int TAG_PREVIEW_DATE_TIME                 = 0xC71B;
    public static final int TAG_RAW_IMAGE_DIGEST                  = 0xC71C;
    public static final int TAG_ORIGINAL_RAW_FILE_DIGEST          = 0xC71D;
    public static final int TAG_SUB_TILE_BLOCK_SIZE               = 0XC71E;
    public static final int TAG_ROW_INTERLEAVE_FACTOR             = 0xC71F;
    public static final int TAG_PROFILE_LOOK_TABLE_DIMS           = 0xC725;
    public static final int TAG_PROFILE_LOOK_TABLE_DATA           = 0xC726;
    // 1.3.0.0
    public static final int TAG_OPCODE_LIST_1                     = 0xC740;
    public static final int TAG_OPCODE_LIST_2                     = 0xC741;
    public static final int TAG_OPCODE_LIST_3                     = 0xC74E;
    public static final int TAG_NOISE_PROFILE                     = 0xC761;
    // 1.4.0.0
    public static final int TAG_DEFAULT_USER_CROP                 = 0xC7B5;
    public static final int TAG_DEFAULT_BLACK_RENDER              = 0xC7A6;
    public static final int TAG_BASELINE_EXPOSURE_OFFSET          = 0xC7A5;
    public static final int TAG_PROFILE_LOOK_TABLE_ENCODING       = 0xC7A4;
    public static final int TAG_PROFILE_HUE_SAT_MAP_ENCODING      = 0xC7A3;
    public static final int TAG_ORIGINAL_DEFAULT_FINAL_SIZE       = 0xC791;
    public static final int TAG_ORIGINAL_BEST_QUALITY_FINAL_SIZE  = 0xC792;
    public static final int TAG_ORIGINAL_DEFAULT_CROP_SIZE        = 0xC793;
    public static final int TAG_NEW_RAW_IMAGE_DIGEST              = 0xC7A7;
    public static final int TAG_RAW_TO_PREVIEW_GAIN               = 0xC7A8;

    public DNGDirectory()
    {
        this.setDescriptor(new DNGDescriptor(this));
    }

    @NotNull
    protected static final HashMap<Integer, String> _tagNameMap = new HashMap<Integer, String>();

    static
    {
        _tagNameMap.put(TAG_DNG_VERSION, "DNG Version");
        _tagNameMap.put(TAG_DNG_BACKWARD_VERSION, "DNG Backward Version");
        _tagNameMap.put(TAG_UNIQUE_CAMERA_MODEL, "Unique Camera Model");
        _tagNameMap.put(TAG_LOCALIZED_CAMERA_MODEL, "Localized Camera Model");
        _tagNameMap.put(TAG_CFA_PLANE_COLOR, "CFA Plane Color");
        _tagNameMap.put(TAG_CFA_LAYOUT, "CFA Layout");
        _tagNameMap.put(TAG_LINEARIZATION_TABLE, "Linearization Table");
        _tagNameMap.put(TAG_BLACK_LEVEL_REPEAT_DIM, "Black Level Repeat Dim");
        _tagNameMap.put(TAG_BLACK_LEVEL, "Black Level");
        _tagNameMap.put(TAG_BLACK_LEVEL_DELTA_H, "Black Level Delta H");
        _tagNameMap.put(TAG_BLACK_LEVEL_DELTA_V, "Black Level Delta V");
        _tagNameMap.put(TAG_WHITE_LEVEL, "White Level");
        _tagNameMap.put(TAG_DEFAULT_SCALE, "Default Scale");
        _tagNameMap.put(TAG_BEST_QUALITY_SCALE, "Best Quality Scale");
        _tagNameMap.put(TAG_DEFAULT_CROP_ORIGIN, "Default Crop Origin");
        _tagNameMap.put(TAG_DEFAULT_CROP_SIZE, "Default Crop Size");
        _tagNameMap.put(TAG_CALLIBRATION_ILLUMINANT_1, "Callibration Illuminant 1");
        _tagNameMap.put(TAG_CALLIBRATION_ILLUMINANT_2, "Callibration Illuminant 2");
        _tagNameMap.put(TAG_COLOR_MATRIX_1, "Color Matrix 1");
        _tagNameMap.put(TAG_COLOR_MATRIX_2, "Color Matrix 2");
        _tagNameMap.put(TAG_CAMERA_CALIBRATION_1, "Camera Calibration 1");
        _tagNameMap.put(TAG_CAMERA_CALIBRATION_2, "Camera Calibration 2");
        _tagNameMap.put(TAG_REDUCTION_MATRIX_1, "Reduction Matrix 1");
        _tagNameMap.put(TAG_REDUCTION_MATRIX_2, "Reduction Matrix 2");
        _tagNameMap.put(TAG_ANALOG_BALANCE, "Analog Balance");
        _tagNameMap.put(TAG_AS_SHOT_NEUTRAL, "As Shot Neutral");
        _tagNameMap.put(TAG_AS_SHOT_WHITE_X_Y, "As Shot White X Y");
        _tagNameMap.put(TAG_BASELINE_EXPOSURE, "Baseline Exposure");
        _tagNameMap.put(TAG_BASELINE_NOISE, "Baseline Noise");
        _tagNameMap.put(TAG_BASELINE_SHARPNESS, "Baseline Sharpness");
        _tagNameMap.put(TAG_BAYER_GREEN_SPLIT, "Bayer Green Split");
        _tagNameMap.put(TAG_LINEAR_RESPONSE_LIMIT, "Linear Response Limit");
        _tagNameMap.put(TAG_CAMERA_SERIAL_NUMBER, "Camera Serial Number");
        _tagNameMap.put(TAG_LENS_INFO, "Lens Info");
        _tagNameMap.put(TAG_CHROMA_BLUR_RADIUS, "Chroma Blur Radius");
        _tagNameMap.put(TAG_ANTI_ALIAS_STRENGTH, "Anti Alias Strength");
        _tagNameMap.put(TAG_SHADOW_SCALE, "Shadow Scale");
        _tagNameMap.put(TAG_DNG_PRIVATE_DATA, "DNG Private Data");
        _tagNameMap.put(TAG_MAKER_NOTE_SAFETY, "Maker Note Safety");
        _tagNameMap.put(TAG_RAW_DATA_UNIQUE_ID, "Raw Data Unique ID");
        _tagNameMap.put(TAG_ORIGINAL_RAW_FILE_NAME, "Original Raw File Name");
        _tagNameMap.put(TAG_ORIGINAL_RAW_FILE_DATA, "");
        _tagNameMap.put(TAG_ACTIVE_AREA, "Active Area");
        _tagNameMap.put(TAG_MASKED_AREAS, "Masked Areas");
        _tagNameMap.put(TAG_AS_SHOT_ICC_PROFILE, "As Shot ICC Profile");
        _tagNameMap.put(TAG_AS_SHOT_PRE_PROFILE_MATRIX, "As Shot Pre Profile Matrix");
        _tagNameMap.put(TAG_CURRENT_ICC_PROFILE, "Current ICC Profile");
        _tagNameMap.put(TAG_CURRENT_PRE_PROFILE_MATRIX, "Current Pre Profile Matrix");
        _tagNameMap.put(TAG_COLORIMETRIC_REFERENCE, "Colorimetric Reference");
        _tagNameMap.put(TAG_CAMERA_CALIBRATION_SIGNATURE, "Camera Calibration Signature");
        _tagNameMap.put(TAG_PROFILE_CALIBRATION_SIGNATURE, "Profile Calibration Signature");
        _tagNameMap.put(TAG_EXTRA_CAMERA_PROFILES, "Extra Camera Profiles");
        _tagNameMap.put(TAG_AS_SHOT_PROFILE_NAME, "As Shot Profile Name");
        _tagNameMap.put(TAG_NOISE_REDUCTION_APPLIED, "Noise Reduction Applied");
        _tagNameMap.put(TAG_PROFILE_NAME, "Profile Name");
        _tagNameMap.put(TAG_PROFILE_HUE_SAT_MAP_DIMS, "Profile Hue Sat Map Dims");
        _tagNameMap.put(TAG_PROFILE_HUE_SAT_MAP_DATA_1, "Profile Hue Sat Map Data 1");
        _tagNameMap.put(TAG_PROFILE_HUE_SAT_MAP_DATA_2, "Profile Hue Sat Map Data 2");
        _tagNameMap.put(TAG_PROFILE_TONE_CURVE, "Profile Tone Curve");
        _tagNameMap.put(TAG_PROFILE_EMBED_POLICY, "Profile Embed Policy");
        _tagNameMap.put(TAG_PROFILE_COPYRIGHT, "Profile Copyright");
        _tagNameMap.put(TAG_FORWARD_MATRIX_1, "Forward Matrix 1");
        _tagNameMap.put(TAG_FORWARD_MATRIX_2, "Forward Matrix 2");
        _tagNameMap.put(TAG_PREVIEW_APPLICATION_NAME, "Preview Application Name");
        _tagNameMap.put(TAG_PREVIEW_APPLICATION_VERSION, "Preview Application Version");
        _tagNameMap.put(TAG_PREVIEW_SETTINGS_NAME, "Preview Settings Name");
        _tagNameMap.put(TAG_PREVIEW_SETTINGS_DIGEST, "Preview Settings Digest");
        _tagNameMap.put(TAG_PREVIEW_COLOR_SPACE, "Preview Color Space");
        _tagNameMap.put(TAG_PREVIEW_DATE_TIME, "Preview Date Time");
        _tagNameMap.put(TAG_RAW_IMAGE_DIGEST, "Raw Image Digest");
        _tagNameMap.put(TAG_ORIGINAL_RAW_FILE_DIGEST, "Original Raw File Digest");
        _tagNameMap.put(TAG_SUB_TILE_BLOCK_SIZE, "Sub Title Block Size");
        _tagNameMap.put(TAG_ROW_INTERLEAVE_FACTOR, "Row Interleave Factor");
        _tagNameMap.put(TAG_PROFILE_LOOK_TABLE_DIMS, "Profile Look Table Dims");
        _tagNameMap.put(TAG_PROFILE_LOOK_TABLE_DATA, "Profile Look Table Data");
        _tagNameMap.put(TAG_OPCODE_LIST_1, "Opcode List 1");
        _tagNameMap.put(TAG_OPCODE_LIST_2, "Opcode List 2");
        _tagNameMap.put(TAG_OPCODE_LIST_3, "Opcode List 3");
        _tagNameMap.put(TAG_NOISE_PROFILE, "Noise Profile");
        _tagNameMap.put(TAG_DEFAULT_USER_CROP, "Default User Crop");
        _tagNameMap.put(TAG_DEFAULT_BLACK_RENDER, "Default Black Render");
        _tagNameMap.put(TAG_BASELINE_EXPOSURE_OFFSET, "Baseline Exposure Offset");
        _tagNameMap.put(TAG_PROFILE_LOOK_TABLE_ENCODING, "Profile Look Table Encoding");
        _tagNameMap.put(TAG_PROFILE_HUE_SAT_MAP_ENCODING, "Profile Hue Sat Map Encoding");
        _tagNameMap.put(TAG_ORIGINAL_DEFAULT_FINAL_SIZE, "Original Default Final Size");
        _tagNameMap.put(TAG_ORIGINAL_BEST_QUALITY_FINAL_SIZE, "Original Best Quality Final Size");
        _tagNameMap.put(TAG_ORIGINAL_DEFAULT_CROP_SIZE, "Original Default Crop Size");
        _tagNameMap.put(TAG_NEW_RAW_IMAGE_DIGEST, "New Raw Image Digest");
        _tagNameMap.put(TAG_RAW_TO_PREVIEW_GAIN, "Raw To Preview Gain");
    }

    @Override
    @NotNull
    public String getName()
    {
        return "DNG Exif IFD0";
    }

    @Override
    @NotNull
    protected HashMap<Integer, String> getTagNameMap()
    {
        return _tagNameMap;
    }
}
