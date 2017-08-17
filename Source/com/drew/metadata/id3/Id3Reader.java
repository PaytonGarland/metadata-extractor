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

                directory.setBoolean(Id3Directory.TAG_UNSYNCHRONISATION, ((flags & 0x80) == 1) ? true : false);

                boolean extendedHeader = ((flags & 0x40) == 1) ? true : false;

                boolean experimentalIndicator = ((flags & 0x20) == 1) ? true : false;

                boolean readable = ((flags & 0x1F) > 0) ? false : true;

                extractV3(bytes, directory, extendedHeader);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ImageProcessingException e) {
            e.printStackTrace();
        }
    }

    public void extractV3(@NotNull final byte[] bytes, @NotNull Id3Directory directory, @NotNull boolean extendedHeader)
    {
        try {
            int pos = 10;

            ByteArrayReader reader = new ByteArrayReader(bytes);

            if (extendedHeader) {
                int extendedSize = reader.getInt32(pos);
                pos += 4;
                int extendedFlags = reader.getInt16(pos);
                pos += 2;
                int extendedPadding = reader.getInt32(pos);
                pos += 4;
                boolean crcDataAvailable = false;
                if ((extendedFlags & 0x80) == 1) {
                    crcDataAvailable = true;
                    int crcData = reader.getInt32(pos);
                    pos += 4;
                }
            }

            // Begin frame

            while (pos < bytes.length) {
                String identifier = new String(reader.getBytes(pos, 4));
                pos += 4;
                int size = reader.getInt32(pos);
                pos += 4;
                int flags = reader.getInt16(pos);
                pos += 2;
                boolean tagAlterPreservation = ((flags & 0x8000) == 1) ? true : false;
                boolean fileAlterPreservation = ((flags & 0x4000) == 1) ? true : false;
                boolean readOnly = ((flags & 0x2000) == 1) ? true : false;
                boolean compression = ((flags & 0x0080) == 1) ? true : false;
                boolean encryption = ((flags & 0x0040) == 1) ? true : false;
                boolean groupIdentity = ((flags & 0x0020) == 1) ? true : false;

                if (identifier.startsWith("T")) {
                    int encoding = reader.getByte(pos);
                    pos++;
                    String value;
                    switch (encoding) {
                        case (1):
                            boolean bigEndian = (Arrays.equals(reader.getBytes(pos, 2), new byte[]{(byte)0xFF, (byte)0xFE}) ? true : false);
                            pos += 2;
                            value = new String(reader.getBytes(pos, size - 3));
                            pos += size - 3;
                            if (Id3Directory._tagNameMap.containsKey(identifier.hashCode())) {
                                directory.setString(identifier.hashCode(), value);
                            }
                            break;
                        case (0):
                            value = new String(reader.getBytes(pos, size - 2));
                            pos += size - 1;
                            if (Id3Directory._tagNameMap.containsKey(identifier.hashCode())) {
                                directory.setString(identifier.hashCode(), value);
                            }
                            break;
                        default:
                    }

                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
