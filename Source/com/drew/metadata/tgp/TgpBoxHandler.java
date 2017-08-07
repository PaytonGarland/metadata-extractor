package com.drew.metadata.tgp;

import com.drew.imaging.tgp.TgpHandler;
import com.drew.lang.SequentialByteArrayReader;
import com.drew.lang.SequentialReader;
import com.drew.lang.annotations.NotNull;
import com.drew.metadata.Metadata;
import com.drew.metadata.tgp.boxes.*;

import java.io.IOException;

/**
 * @author Payton Garland
 */
public class TgpBoxHandler extends TgpHandler<TgpDirectory>
{
    private TgpHandlerFactory handlerFactory = new TgpHandlerFactory(this);

    public TgpBoxHandler(Metadata metadata)
    {
        super(metadata);
    }

    @Override
    protected TgpDirectory getDirectory()
    {
        return new TgpDirectory();
    }

    @Override
    public boolean shouldAcceptBox(@NotNull Box box)
    {
        return box.type.equals(TgpBoxTypes.BOX_FILE_TYPE)
            || box.type.equals(TgpBoxTypes.BOX_MOVIE_HEADER)
            || box.type.equals(TgpBoxTypes.BOX_HANDLER)
            || box.type.equals(TgpBoxTypes.BOX_MEDIA_HEADER);
    }

    @Override
    public boolean shouldAcceptContainer(Box box)
    {
        return box.type.equals(TgpContainerTypes.BOX_TRACK)
            || box.type.equals(TgpContainerTypes.BOX_METADATA)
            || box.type.equals(TgpContainerTypes.BOX_MOVIE)
            || box.type.equals(TgpContainerTypes.BOX_MEDIA);
    }

    @Override
    public TgpHandler processBox(@NotNull Box box, @NotNull byte[] payload) throws IOException
    {
        if (payload != null) {
            SequentialReader reader = new SequentialByteArrayReader(payload);
            if (box.type.equals(TgpBoxTypes.BOX_HANDLER)) {
                HandlerBox handlerBox = new HandlerBox(reader, box);
                return handlerFactory.getHandler(handlerBox, metadata);
            } else if (box.type.equals(TgpBoxTypes.BOX_MOVIE_HEADER)) {
                MovieHeaderBox movieHeaderBox = new MovieHeaderBox(reader, box);
                movieHeaderBox.addMetadata(directory);
            } else if (box.type.equals(TgpBoxTypes.BOX_MEDIA_HEADER)) {
                MediaHeaderBox mediaHeaderBox = new MediaHeaderBox(reader, box);
            } else if (box.type.equals(TgpBoxTypes.BOX_FILE_TYPE)) {
                FileTypeBox fileTypeBox = new FileTypeBox(reader, box);
                fileTypeBox.addMetadata(directory);

            }
        } else {

        }
        return this;
    }
}
