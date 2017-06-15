package com.drew.metadata.svg;

import com.drew.lang.annotations.NotNull;
import com.drew.metadata.Directory;

import java.util.HashMap;

/**
 * @author Payton Garland
 */
public class SvgDirectory extends Directory
{
    public static final int TAG_VIEWBOX_MIN_WIDTH                           = 1;
    public static final int TAG_VIEWBOX_WIDTH                               = 2;
    public static final int TAG_VIEWBOX_MIN_HEIGHT                          = 3;
    public static final int TAG_VIEWBOX_HEIGHT                              = 4;
    public static final int TAG_WIDTH                                       = 5;
    public static final int TAG_HEIGHT                                      = 6;

    @NotNull
    protected static final HashMap<Integer, String> _tagNameMap = new HashMap<Integer, String>();

    static {

        _tagNameMap.put(TAG_VIEWBOX_MIN_WIDTH, "View Box Minimum Width");
        _tagNameMap.put(TAG_VIEWBOX_WIDTH, "View Box Width");
        _tagNameMap.put(TAG_VIEWBOX_MIN_HEIGHT, "View Box Minimum Height");
        _tagNameMap.put(TAG_VIEWBOX_HEIGHT, "View Box Height");
        _tagNameMap.put(TAG_WIDTH, "Width");
        _tagNameMap.put(TAG_HEIGHT, "Height");

    }

    public SvgDirectory()
    {
        this.setDescriptor(new SvgDescriptor(this));
    }

    @Override
    @NotNull
    public String getName()
    {
        return "SVG";
    }

    @Override
    @NotNull
    protected HashMap<Integer, String> getTagNameMap()
    {
        return _tagNameMap;
    }
}
