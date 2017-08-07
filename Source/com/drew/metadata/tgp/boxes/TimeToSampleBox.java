package com.drew.metadata.tgp.boxes;

import com.drew.lang.SequentialReader;
import com.drew.metadata.tgp.TgpHandlerFactory;
import com.drew.metadata.tgp.media.TgpSoundDirectory;
import com.drew.metadata.tgp.media.TgpVideoDirectory;

import java.io.IOException;
import java.util.ArrayList;

/**
 * ISO/IED 14496-12:2015 pg.37
 */
public class TimeToSampleBox extends FullBox
{
    long entryCount;
    ArrayList<EntryCount> entries;

    public TimeToSampleBox(SequentialReader reader, Box box) throws IOException
    {
        super(reader, box);

        entryCount = reader.getUInt32();
        entries = new ArrayList<EntryCount>();
        for (int i = 0; i < entryCount; i++) {
            entries.add(new EntryCount(reader.getUInt32(), reader.getUInt32()));
        }
    }

    public void addMetadata(TgpVideoDirectory directory)
    {
        float frameRate = (float) TgpHandlerFactory.HANDLER_PARAM_TIME_SCALE/(float)entries.get(0).sampleDelta;
        directory.setFloat(TgpVideoDirectory.TAG_FRAME_RATE, frameRate);
    }

    public void addMetadata(TgpSoundDirectory directory)
    {
        directory.setDouble(TgpSoundDirectory.TAG_AUDIO_SAMPLE_RATE, TgpHandlerFactory.HANDLER_PARAM_TIME_SCALE);
    }

    class EntryCount
    {
        long sampleCount;
        long sampleDelta;

        public EntryCount(long sampleCount, long sampleDelta)
        {
            this.sampleCount = sampleCount;
            this.sampleDelta = sampleDelta;
        }
    }
}
