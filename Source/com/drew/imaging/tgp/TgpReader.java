package com.drew.imaging.tgp;

import com.drew.lang.StreamReader;
import com.drew.metadata.Metadata;
import com.drew.metadata.tgp.boxes.Box;

import java.io.IOException;
import java.io.InputStream;
import java.util.zip.DataFormatException;

/**
 * @author Payton Garland
 */
public class TgpReader
{
    private StreamReader reader;

    public void extract(Metadata metadata, InputStream inputStream, TgpHandler handler) throws IOException
    {
        reader = new StreamReader(inputStream);
        reader.setMotorolaByteOrder(true);

        processBoxes(reader, -1, handler);
    }

    private void processBoxes(StreamReader reader, long atomEnd, TgpHandler mp4Handler)
    {
        try {
            while ((atomEnd == -1) ? true : reader.getPosition() < atomEnd) {

                Box box = new Box(reader);

                System.out.println(box.type);

                /*
                 * Determine if fourCC is container/atom and process accordingly
                 * Unknown atoms will be skipped
                 */
                if (mp4Handler.shouldAcceptContainer(box)) {

                    processBoxes(reader, box.size + reader.getPosition() - 8, mp4Handler.processContainer(box));

                } else if (mp4Handler.shouldAcceptBox(box)) {

                    mp4Handler = mp4Handler.processBox(box, reader.getBytes((int)box.size - 8));

                } else {

                    if (box.size > 1) {
                        reader.skip(box.size - 8);
                    } else if (box.size == -1) {
                        break;
                    }

                }
            }
        } catch (IOException ignored) {
        }
    }
}
