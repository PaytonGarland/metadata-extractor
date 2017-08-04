package com.drew.metadata.bpg;

import com.drew.lang.annotations.NotNull;
import com.drew.metadata.Directory;

import java.util.HashMap;

/**
 * @author Payton Garland
 */
public class BpgDirectory extends Directory
{
    public static final int TAG_PIXEL_FORMAT = 1;
    public static final int TAG_DEPTH = 2;
    public static final int TAG_COLOR_SPACE = 3;
    public static final int TAG_WIDTH = 4;
    public static final int TAG_HEIGHT = 5;

    @NotNull
    protected static final HashMap<Integer, String> _tagNameMap = new HashMap<Integer, String>();

    static {
        _tagNameMap.put(TAG_PIXEL_FORMAT, "Pixel Format");
        _tagNameMap.put(TAG_DEPTH, "Depth");
        _tagNameMap.put(TAG_COLOR_SPACE, "Color Space");
        _tagNameMap.put(TAG_WIDTH, "Width");
        _tagNameMap.put(TAG_HEIGHT, "Height");
    }

    public BpgDirectory()
    {
        this.setDescriptor(new BpgDescriptor(this));
    }

    @Override
    @NotNull
    public String getName()
    {
        return "BPG";
    }

    @Override
    @NotNull
    protected HashMap<Integer, String> getTagNameMap()
    {
        return _tagNameMap;
    }
}
