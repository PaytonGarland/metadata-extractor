package com.drew.metadata.tgp.media;

import com.drew.metadata.mp4.Mp4Directory;
import com.drew.metadata.tgp.TgpDirectory;

import java.util.HashMap;

public abstract class TgpMediaDirectory extends TgpDirectory
{
    public static final int TAG_CREATION_TIME = 101;
    public static final int TAG_MODIFICATION_TIME = 102;
    public static final int TAG_DURATION = 103;
    public static final int TAG_LANGUAGE_CODE = 104;

    protected static void addTgpMediaTags(HashMap<Integer, String> map)
    {
        map.put(TAG_CREATION_TIME, "Creation Time");
        map.put(TAG_MODIFICATION_TIME, "Modification Time");
        map.put(TAG_DURATION, "Duration");
        map.put(TAG_LANGUAGE_CODE, "ISO 639-2 Language Code");
    }
}
