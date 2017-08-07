package com.drew.metadata.tgp.media;

import com.drew.lang.SequentialReader;
import com.drew.lang.annotations.NotNull;
import com.drew.metadata.Metadata;
import com.drew.metadata.tgp.TgpBoxTypes;
import com.drew.metadata.tgp.TgpMediaHandler;
import com.drew.metadata.tgp.boxes.Box;
import com.drew.metadata.tgp.boxes.Mp4VisualSampleEntryBox;

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
        Mp4VisualSampleEntryBox mp4VisualSampleEntryBox = new Mp4VisualSampleEntryBox(reader, box);
        mp4VisualSampleEntryBox.addMetadata(directory);
    }

    @Override
    public void processMediaInformation(@NotNull SequentialReader reader, @NotNull Box box) throws IOException
    {
//        VideoMediaHeaderBox videoMediaHeaderBox = new VideoMediaHeaderBox(reader, box);
//        videoMediaHeaderBox.addMetadata(directory);
    }

    @Override
    public void processTimeToSample(@NotNull SequentialReader reader, @NotNull Box box) throws IOException
    {
//        TimeToSampleBox timeToSampleBox = new TimeToSampleBox(reader, box);
//        timeToSampleBox.addMetadata(directory);
    }
}
