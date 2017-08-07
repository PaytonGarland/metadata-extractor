package com.drew.metadata.tgp.media;

import com.drew.lang.SequentialReader;
import com.drew.lang.annotations.NotNull;
import com.drew.metadata.Metadata;
import com.drew.metadata.tgp.TgpBoxTypes;
import com.drew.metadata.tgp.TgpMediaHandler;
import com.drew.metadata.tgp.boxes.*;

import java.io.IOException;

public class TgpVideoHandler extends TgpMediaHandler<TgpVideoDirectory>
{
    public TgpVideoHandler(Metadata metadata)
    {
        super(metadata);
    }

    @Override
    protected String getMediaInformation()
    {
        return TgpBoxTypes.BOX_VIDEO_MEDIA_INFO;
    }

    @Override
    protected TgpVideoDirectory getDirectory()
    {
        return new TgpVideoDirectory();
    }

    @Override
    public void processSampleDescription(@NotNull SequentialReader reader, @NotNull Box box) throws IOException
    {
        VisualSampleEntry visualSampleEntry = new VisualSampleEntry(reader, box);
        visualSampleEntry.addMetadata(directory);
    }

    @Override
    public void processMediaInformation(@NotNull SequentialReader reader, @NotNull Box box) throws IOException
    {
        VideoMediaHeaderBox videoMediaHeaderBox = new VideoMediaHeaderBox(reader, box);
        videoMediaHeaderBox.addMetadata(directory);
    }

    @Override
    public void processTimeToSample(@NotNull SequentialReader reader, @NotNull Box box) throws IOException
    {
        TimeToSampleBox timeToSampleBox = new TimeToSampleBox(reader, box);
        timeToSampleBox.addMetadata(directory);
    }
}
