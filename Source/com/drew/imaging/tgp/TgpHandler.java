package com.drew.imaging.tgp;

import com.drew.lang.annotations.NotNull;
import com.drew.metadata.Metadata;
import com.drew.metadata.tgp.TgpDirectory;
import com.drew.metadata.tgp.boxes.Box;

import java.io.IOException;

/**
 * @author Payton Garland
 */
public abstract class TgpHandler<T extends TgpDirectory>
{
    protected Metadata metadata;
    protected T directory;

    public TgpHandler(Metadata metadata)
    {
        this.metadata = metadata;
        this.directory = getDirectory();
        metadata.addDirectory(directory);
    }

    protected abstract T getDirectory();

    protected abstract boolean shouldAcceptBox(@NotNull Box box);

    protected abstract boolean shouldAcceptContainer(@NotNull Box box);

    protected abstract TgpHandler processBox(@NotNull Box box, @NotNull byte[] payload) throws IOException;

    protected TgpHandler processContainer(@NotNull Box box) throws IOException
    {
        return processBox(box, null);
    }
}
