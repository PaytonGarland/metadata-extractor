package com.drew.metadata.svg;

import com.drew.lang.annotations.NotNull;
import com.drew.metadata.TagDescriptor;


/**
 * @author Payton Garland
 */
public class SvgDescriptor extends TagDescriptor<SvgDirectory>
{

    public SvgDescriptor(@NotNull SvgDirectory directory)
    {
        super(directory);
    }

    @Override
    public String getDescription(int tagType)
    {
        return _directory.getString(tagType);
    }

}
