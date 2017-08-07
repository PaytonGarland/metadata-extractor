package com.drew.metadata.tgp.media;

import com.drew.lang.annotations.NotNull;

import java.util.HashMap;

/**
 * @author Payton Garland
 */
public class TgpSoundDirectory extends TgpMediaDirectory
{
    // Sound Sample Description Atom
    public static final int TAG_AUDIO_FORMAT                            = 301;
    public static final int TAG_NUMBER_OF_CHANNELS                      = 302;
    public static final int TAG_AUDIO_SAMPLE_SIZE                       = 303;
    public static final int TAG_AUDIO_SAMPLE_RATE                       = 304;

    public static final int TAG_SOUND_BALANCE                           = 305;

    public TgpSoundDirectory()
    {
        this.setDescriptor(new TgpSoundDescriptor(this));
    }

    @NotNull
    protected static final HashMap<Integer, String> _tagNameMap = new HashMap<Integer, String>();

    static
    {
        TgpMediaDirectory.addTgpMediaTags(_tagNameMap);
        _tagNameMap.put(TAG_AUDIO_FORMAT, "Format");
        _tagNameMap.put(TAG_NUMBER_OF_CHANNELS, "Number of Channels");
        _tagNameMap.put(TAG_AUDIO_SAMPLE_SIZE, "Sample Size");
        _tagNameMap.put(TAG_AUDIO_SAMPLE_RATE, "Sample Rate");
        _tagNameMap.put(TAG_SOUND_BALANCE, "Balance");
    }

    @Override
    @NotNull
    public String getName()
    {
        return "3GP Sound";
    }

    @Override
    @NotNull
    protected HashMap<Integer, String> getTagNameMap()
    {
        return _tagNameMap;
    }
}
