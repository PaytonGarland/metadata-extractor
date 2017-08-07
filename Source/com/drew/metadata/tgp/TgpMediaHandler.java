package com.drew.metadata.tgp;

import com.drew.imaging.tgp.TgpHandler;
import com.drew.lang.SequentialByteArrayReader;
import com.drew.lang.SequentialReader;
import com.drew.lang.annotations.NotNull;
import com.drew.metadata.Metadata;
import com.drew.metadata.tgp.boxes.Box;
import com.drew.metadata.tgp.media.TgpMediaDirectory;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

/**
 * @author Payton Garland
 */
public abstract class TgpMediaHandler<T extends TgpMediaDirectory> extends TgpHandler<T>
{
    public TgpMediaHandler(Metadata metadata)
    {
        super(metadata);
        if (TgpHandlerFactory.HANDLER_PARAM_CREATION_TIME != null && TgpHandlerFactory.HANDLER_PARAM_MODIFICATION_TIME != null) {
            // Get creation/modification times
            Calendar calendar = Calendar.getInstance();
            calendar.set(1904, 0, 1, 0, 0, 0);      // January 1, 1904  -  Macintosh Time Epoch
            Date date = calendar.getTime();
            long macToUnixEpochOffset = date.getTime();
            String creationTimeStamp = new Date(TgpHandlerFactory.HANDLER_PARAM_CREATION_TIME * 1000 + macToUnixEpochOffset).toString();
            String modificationTimeStamp = new Date(TgpHandlerFactory.HANDLER_PARAM_MODIFICATION_TIME * 1000 + macToUnixEpochOffset).toString();
            String language = TgpHandlerFactory.HANDLER_PARAM_LANGUAGE;
            directory.setString(TgpMediaDirectory.TAG_CREATION_TIME, creationTimeStamp);
            directory.setString(TgpMediaDirectory.TAG_MODIFICATION_TIME, modificationTimeStamp);
            directory.setString(TgpMediaDirectory.TAG_LANGUAGE_CODE, language);
        }
    }

    @Override
    public boolean shouldAcceptBox(Box box)
    {
        return box.type.equals(getMediaInformation())
            || box.type.equals(TgpBoxTypes.BOX_SAMPLE_DESCRIPTION)
            || box.type.equals(TgpBoxTypes.BOX_TIME_TO_SAMPLE);
    }

    @Override
    public boolean shouldAcceptContainer(Box box)
    {
        return box.type.equals(TgpContainerTypes.BOX_SAMPLE_TABLE)
            || box.type.equals(TgpContainerTypes.BOX_MEDIA_INFORMATION);
    }

    @Override
    public TgpHandler processBox(@NotNull Box box, @NotNull byte[] payload) throws IOException
    {
        if (payload != null) {
            SequentialReader reader = new SequentialByteArrayReader(payload);
            if (box.type.equals(getMediaInformation())) {
                processMediaInformation(reader, box);
            } else if (box.type.equals(TgpBoxTypes.BOX_SAMPLE_DESCRIPTION)) {
                processSampleDescription(reader, box);
            } else if (box.type.equals(TgpBoxTypes.BOX_TIME_TO_SAMPLE)) {
                processTimeToSample(reader, box);
            }
        }
        return this;
    }

    protected abstract String getMediaInformation();

    protected abstract void processSampleDescription(@NotNull SequentialReader reader, @NotNull Box box) throws IOException;

    protected abstract void processMediaInformation(@NotNull SequentialReader reader, @NotNull Box box) throws IOException;

    protected abstract void processTimeToSample(@NotNull SequentialReader reader, @NotNull Box box) throws IOException;
}
