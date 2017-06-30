package com.drew.metadata.id3;

import com.drew.imaging.ImageProcessingException;
import com.drew.lang.ByteArrayReader;
import com.drew.lang.annotations.NotNull;
import com.drew.metadata.Metadata;

import java.io.IOException;
import java.util.Arrays;

public class Id3Reader
{
    public void extract(@NotNull final byte[] bytes, @NotNull final Metadata metadata)
    {
        try {
            Id3Directory directory = new Id3Directory();
            metadata.addDirectory(directory);

            ByteArrayReader reader = new ByteArrayReader(bytes);
            byte[] identifier = reader.getBytes(0, 3);
            if (!Arrays.equals("ID3".getBytes(), identifier)) {
                throw new ImageProcessingException("ID3 block not found. Format not supported.");
            }

            double version = reader.getByte(3) + (reader.getByte(4) / 10);

            int flags = reader.getByte(5);

            int size = reader.getInt32(6);

            if (version == 3.0) {
                extractV3(bytes, directory);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ImageProcessingException e) {
            e.printStackTrace();
        }
    }

    public void extractV3(@NotNull final byte[] bytes, @NotNull Id3Directory directory)
    {
        try {
            ByteArrayReader reader = new ByteArrayReader(bytes);

            int flags = reader.getByte(5);

            boolean unsychronisation = (1 == ((flags & 0x80) >> 7));

            boolean extendedheader = (1 == ((flags & 0x40) >> 6));

            boolean experimentalIndicator = (1 == ((flags & 0x20) >> 5));

            // Begin frame

            int pos = (10);
            String identifier = new String(reader.getBytes(pos, 4));
            pos += 4;
            int size = reader.getInt32(pos);
            pos += 4;
            byte[] frameFlags = reader.getBytes(pos, 2);
            pos += 2;



        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
