package com.drew.metadata.tgp;

import com.drew.imaging.tgp.TgpHandler;
import com.drew.metadata.Metadata;
import com.drew.metadata.mp4.Mp4MediaHandler;
import com.drew.metadata.mp4.media.Mp4VideoDirectory;
import com.drew.metadata.mp4.media.Mp4VideoHandler;
import com.drew.metadata.tgp.boxes.HandlerBox;
import com.drew.metadata.tgp.media.TgpSoundHandler;
import com.drew.metadata.tgp.media.TgpVideoHandler;

/**
 * @author Payton Garland
 */
public class TgpHandlerFactory
{
    private static final String HANDLER_SOUND_MEDIA             = "soun";
    private static final String HANDLER_VIDEO_MEDIA             = "vide";
    private static final String HANDLER_HINT_MEDIA              = "hint";
    private static final String HANDLER_TEXT_MEDIA              = "text";
    private static final String HANDLER_META_MEDIA              = "meta";

    private TgpHandler caller;

    public static Long HANDLER_PARAM_TIME_SCALE              = null;
    public static Long HANDLER_PARAM_CREATION_TIME           = null;
    public static Long HANDLER_PARAM_MODIFICATION_TIME       = null;
    public static Long HANDLER_PARAM_DURATION                = null;
    public static String HANDLER_PARAM_LANGUAGE              = null;

    public TgpHandlerFactory(TgpHandler caller)
    {
        this.caller = caller;
    }

    public TgpHandler getHandler(HandlerBox box, Metadata metadata)
    {
        String type = box.getHandlerType();

        if (type.equals(HANDLER_VIDEO_MEDIA)) {
            return new TgpVideoHandler(metadata);
        } else if (type.equals(HANDLER_SOUND_MEDIA)) {
            return new TgpSoundHandler(metadata);
        }
        return caller;
    }
}
