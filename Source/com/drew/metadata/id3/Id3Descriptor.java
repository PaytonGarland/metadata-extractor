package com.drew.metadata.id3;

import com.drew.lang.annotations.Nullable;
import com.drew.metadata.TagDescriptor;

public class Id3Descriptor extends TagDescriptor<Id3Directory>
{
    public Id3Descriptor(Id3Directory directory)
    {
        super(directory);
    }

    @Override
    @Nullable
    public String getDescription(int tagType)
    {
        return super.getDescription(tagType);
    }
}
