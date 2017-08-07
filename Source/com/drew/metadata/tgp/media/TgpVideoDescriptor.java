package com.drew.metadata.tgp.media;

import com.drew.lang.annotations.NotNull;
import com.drew.metadata.TagDescriptor;
import com.drew.metadata.mp4.media.Mp4VideoDirectory;

public class TgpVideoDescriptor extends TagDescriptor<TgpVideoDirectory>
{
    public TgpVideoDescriptor(@NotNull TgpVideoDirectory directory)
    {
        super(directory);
    }

    @Override
    public String getDescription(int tagType)
    {
        switch (tagType) {
            case (TgpVideoDirectory.TAG_HEIGHT):
            case (TgpVideoDirectory.TAG_WIDTH):
                return getPixelDescription(tagType);
            default:
                return super.getDescription(tagType);
        }
    }

    private String getPixelDescription(int tagType)
    {
        return _directory.getString(tagType) + " pixels";
    }
}
