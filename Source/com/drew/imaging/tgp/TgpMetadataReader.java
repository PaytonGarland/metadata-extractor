package com.drew.imaging.tgp;

import com.drew.lang.annotations.NotNull;
import com.drew.metadata.Metadata;
import com.drew.metadata.file.FileMetadataReader;
import com.drew.metadata.tgp.TgpBoxHandler;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Obtains metadata from 3GP files.
 *
 * @author Payton Garland
 */
public class TgpMetadataReader
{
    @NotNull
    public static Metadata readMetadata(@NotNull File file) throws IOException
    {
        InputStream inputStream = new FileInputStream(file);
        Metadata metadata;
        try {
            metadata = readMetadata(inputStream);
        } finally {
            inputStream.close();
        }
        new FileMetadataReader().read(file, metadata);
        return metadata;
    }

    @NotNull
    public static Metadata readMetadata(@NotNull InputStream inputStream) throws IOException
    {
        Metadata metadata = new Metadata();
        new TgpReader().extract(metadata, inputStream, new TgpBoxHandler(metadata));
        return metadata;
    }
}
