package com.drew.metadata.mov;

import com.drew.lang.ByteArrayReader;
import com.drew.lang.SequentialByteArrayReader;
import com.drew.lang.annotations.NotNull;

import java.io.IOException;

public class QtMediaTimecodeHandler extends QtMediaHandler
{
    @Override
    String getMediaInformation()
    {
        return QtAtomTypes.ATOM_TIMECODE_MEDIA_INFO;
    }

    /**
     * https://developer.apple.com/library/content/documentation/QuickTime/QTFF/QTFFChap3/qtff3.html#//apple_ref/doc/uid/TP40000939-CH205-BBCGABGG
     */
    @Override
    public void processSampleDescription(@NotNull QtDirectory directory, @NotNull ByteArrayReader reader) throws IOException
    {
        // Begin general structure
        int versionAndFlags = reader.getInt32(0);
        int numberOfEntries = reader.getInt32(4);
        int sampleDescriptionSize = reader.getInt32(8);
        String dataFormat = reader.getString(12, 4, "UTF-8");
        // 6-bytes of reserved space set to 0
        int dataReferenceIndex = reader.getInt16(22);
        // End general structure

        if (!dataFormat.equals("tmcd")) {
            directory.addError("Timecode sample description atom has incorrect data format, no data extracted");
        }

        // 32-bits reserved space set to 0
        int flags = reader.getInt32(28);
        int timeScale = reader.getInt32(32);
        int frameDuration = reader.getInt32(36);
        int numberOfFrames = reader.getInt8(40);
        // 8-bits reserved space set to 0
        // Source reference...

        directory.setBoolean(QtDirectory.TAG_TIMECODE_DROP_FRAME, ((flags & 0x0001) == 0x0001) ? true : false);
        directory.setBoolean(QtDirectory.TAG_TIMECODE_24_HOUR_MAX, ((flags & 0x0002) == 0x0002) ? true : false);
        directory.setBoolean(QtDirectory.TAG_TIMECODE_NEGATIVE_TIMES_OK, ((flags & 0x0004) == 0x0004) ? true : false);
        directory.setBoolean(QtDirectory.TAG_TIMECODE_COUNTER, ((flags & 0x0008) == 0x0008) ? true : false);

    }

    @Override
    public void processMediaInformation(@NotNull QtDirectory directory, @NotNull ByteArrayReader reader) throws IOException
    {
        int versionAndFlags = reader.getInt32(0);
        int textFont = reader.getInt16(4);
        int textFace = reader.getInt16(6);
        int textSize = reader.getInt16(8);
        // 16-bits reserved space set to 0
        int textColor = reader.getInt24(10);
        int backgroundColor = reader.getInt24(13);
        String fontName = reader.getString(16, (int)reader.getLength() - 16, "UTF-8");

        directory.setInt(QtDirectory.TAG_TIMECODE_TEXT_FONT, textFont);
        switch (textFace) {
            case (0x0001):
                directory.setString(QtDirectory.TAG_TIMECODE_TEXT_FACE, "Bold");
                break;
            case (0x0002):
                directory.setString(QtDirectory.TAG_TIMECODE_TEXT_FACE, "Italic");
                break;
            case (0x0004):
                directory.setString(QtDirectory.TAG_TIMECODE_TEXT_FACE, "Underline");
                break;
            case (0x0008):
                directory.setString(QtDirectory.TAG_TIMECODE_TEXT_FACE, "Outline");
                break;
            case (0x0010):
                directory.setString(QtDirectory.TAG_TIMECODE_TEXT_FACE, "Shadow");
                break;
            case (0x0020):
                directory.setString(QtDirectory.TAG_TIMECODE_TEXT_FACE, "Condense");
                break;
            case (0x0040):
                directory.setString(QtDirectory.TAG_TIMECODE_TEXT_FACE, "Extend");
        }

        directory.setInt(QtDirectory.TAG_TIMECODE_TEXT_SIZE, textSize);
        directory.setInt(QtDirectory.TAG_TIMECODE_TEXT_COLOR, textColor);
        directory.setInt(QtDirectory.TAG_TIMECODE_BACKGROUND_COLOR, backgroundColor);
        directory.setString(QtDirectory.TAG_TIMECODE_FONT_NAME, fontName);
    }

    /**
     * https://developer.apple.com/library/content/documentation/QuickTime/QTFF/QTFFChap2/qtff2.html#//apple_ref/doc/uid/TP40000939-CH204-BBCGFJII
     */
    @Override
    void processTimeToSample(@NotNull QtDirectory directory, @NotNull ByteArrayReader reader) throws IOException
    {
        // Do nothing
    }
}
