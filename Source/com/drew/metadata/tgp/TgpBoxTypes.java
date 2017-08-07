package com.drew.metadata.tgp;

import java.util.ArrayList;

/**
 * @author Payton Garland
 */
public class TgpBoxTypes
{
    public static final String BOX_FILE_TYPE                        = "ftyp";
    public static final String BOX_MOVIE_HEADER                     = "mvhd";
    public static final String BOX_VIDEO_MEDIA_INFO                 = "vmhd";
    public static final String BOX_SOUND_MEDIA_INFO                 = "smhd";
    public static final String BOX_HINT_MEDIA_INFO                  = "hmhd";
    public static final String BOX_NULL_MEDIA_INFO                  = "nmhd";
    public static final String BOX_HANDLER                          = "hdlr";
    public static final String BOX_SAMPLE_DESCRIPTION               = "stsd";
    public static final String BOX_TIME_TO_SAMPLE                   = "stts";
    public static final String BOX_MEDIA_HEADER                     = "mdhd";

    public static final String BOX_VISUAL_SAMPLE                    = "mp4v";
    public static final String BOX_AUDIO_SAMPLE                     = "mp4a";
    public static final String BOX_AMR_SAMPLE                       = "samr";
    public static final String BOX_AMR_SAMPLE_2                     = "sawb";
    public static final String BOX_H263_SAMPLE                      = "s263";
    public static final String BOX_AMR_SPECIFIC                     = "damr";
    public static final String BOX_H263_SPECIFIC                    = "d263";
    public static final String BOX_AMRWP_SAMPLE                     = "sawp";
    public static final String BOX_AMRWP_SPECIFIC                   = "dawp";
    public static final String BOX_CVO_SAMPLE                       = "3gvo";
    public static final String BOX_LOCATION_SAMPLE                  = "3glo";
    public static final String BOX_ORIENTATION_SAMPLE               = "3gor";
    public static final String BOX_EVS_SAMPLE                       = "sevs";



    public static ArrayList<String> _boxList = new ArrayList<String>();

    static {
        _boxList.add(BOX_FILE_TYPE);
        _boxList.add(BOX_MOVIE_HEADER);
        _boxList.add(BOX_VIDEO_MEDIA_INFO);
        _boxList.add(BOX_SOUND_MEDIA_INFO);
        _boxList.add(BOX_HINT_MEDIA_INFO);
        _boxList.add(BOX_NULL_MEDIA_INFO);
        _boxList.add(BOX_HANDLER);
        _boxList.add(BOX_SAMPLE_DESCRIPTION);
        _boxList.add(BOX_TIME_TO_SAMPLE);
        _boxList.add(BOX_MEDIA_HEADER);
    }
}
