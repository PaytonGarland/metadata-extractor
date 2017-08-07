package com.drew.metadata.tgp.boxes;

import com.drew.lang.SequentialReader;
import com.drew.metadata.tgp.media.TgpSoundDirectory;

import java.io.IOException;

/**
 * ISO/IED 14496-12:2015 pg.161
 */
public class AudioSampleEntry extends SampleEntry
{
    int channelcount;
    int samplesize;
    long samplerate;

    public AudioSampleEntry(SequentialReader reader, Box box) throws IOException
    {
        super(reader, box);

        reader.skip(8); // Reserved
        channelcount = reader.getUInt16();
        samplesize = reader.getInt16();
        reader.skip(2); // Pre-defined
        reader.skip(2); // Reserved
        samplerate = reader.getUInt32();
        // ChannelLayout()
        // DownMix and/or DRC boxes
        // More boxes as needed
    }

    public void addMetadata(TgpSoundDirectory directory)
    {
        directory.setInt(TgpSoundDirectory.TAG_NUMBER_OF_CHANNELS, channelcount);
        directory.setInt(TgpSoundDirectory.TAG_AUDIO_SAMPLE_SIZE, samplesize);
        directory.setLong(TgpSoundDirectory.TAG_AUDIO_SAMPLE_RATE, samplerate);
    }
}
