package com.drew.metadata.tgp.media;

import com.drew.lang.SequentialReader;
import com.drew.lang.annotations.NotNull;
import com.drew.metadata.Metadata;
import com.drew.metadata.tgp.TgpBoxTypes;
import com.drew.metadata.tgp.TgpMediaHandler;
import com.drew.metadata.tgp.boxes.*;

import java.io.IOException;

/**
 * @author Payton Garland
 */
public class TgpSoundHandler extends TgpMediaHandler<TgpSoundDirectory>
{
    public TgpSoundHandler(Metadata metadata)
    {
        super(metadata);
    }

    @Override
    protected TgpSoundDirectory getDirectory()
    {
        return new TgpSoundDirectory();
    }

    @Override
    protected String getMediaInformation()
    {
        return TgpBoxTypes.BOX_SOUND_MEDIA_INFO;
    }

    @Override
    public void processSampleDescription(@NotNull SequentialReader reader, @NotNull Box box) throws IOException
    {
        AudioSampleEntry audioSampleEntry = new AudioSampleEntry(reader, box);
        audioSampleEntry.addMetadata(directory);
    }

    @Override
    public void processMediaInformation(@NotNull SequentialReader reader, @NotNull Box box) throws IOException
    {
        SoundMediaHeaderBox soundMediaHeaderBox = new SoundMediaHeaderBox(reader, box);
        soundMediaHeaderBox.addMetadata(directory);
    }

    @Override
    protected void processTimeToSample(@NotNull SequentialReader reader, @NotNull Box box) throws IOException
    {
        TimeToSampleBox timeToSampleBox = new TimeToSampleBox(reader, box);
        timeToSampleBox.addMetadata(directory);
    }
}
