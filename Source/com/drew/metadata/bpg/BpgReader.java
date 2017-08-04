package com.drew.metadata.bpg;

import com.drew.lang.SequentialReader;
import com.drew.lang.annotations.NotNull;
import com.drew.metadata.Metadata;

import java.io.IOException;

/**
 * @author Payton Garland
 */
public class BpgReader
{
    private SequentialReader reader;
    private BpgDirectory directory;

    public void extract(@NotNull final SequentialReader reader, @NotNull final Metadata metadata) throws IOException
    {
        this.reader = reader;
        this.directory = new BpgDirectory();
        metadata.addDirectory(directory);
        heicFile();
    }

    private void heicFile() throws IOException
    {
        String magicNumber = reader.getString(4);
        int holder = reader.getUInt8();

        int pixelFormat = (holder & 0xE0) >> 5;
        boolean alpha1Flag = ((holder & 0x10) >> 4 == 1) ? true : false;
        int bitDepthMinus8 = (holder & 0x0F);

        holder = reader.getUInt8();

        int colorSpace = (holder & 0xF0) >> 4;
        int extensionPresentFlag = (holder & 0x08) >> 3;
        boolean alpha2Flag = ((holder & 0x04) >> 2 == 1) ? true : false;
        int limitedRangeFlag = (holder & 0x02) >> 1;
        int animationFlag = (holder & 0x01);

        long pictureWidth = getUe732();
        long pictureHeight = getUe732();

        directory.setInt(BpgDirectory.TAG_DEPTH, bitDepthMinus8 + 8);
        directory.setLong(BpgDirectory.TAG_WIDTH, pictureWidth);
        directory.setLong(BpgDirectory.TAG_HEIGHT, pictureHeight);
        directory.setInt(BpgDirectory.TAG_PIXEL_FORMAT, pixelFormat);
        directory.setInt(BpgDirectory.TAG_COLOR_SPACE, colorSpace);

        long pictureDataLength = getUe732();
        if (extensionPresentFlag == 1) {
            long extensionDataLength = reader.getUInt32();
        }
        hevcHeaderAndData(alpha1Flag, alpha2Flag);
    }

    private void extensionData()
    {

    }

    private void hevcHeaderAndData(boolean alpha1Flag, boolean alpha2Flag) throws IOException
    {
        if (alpha1Flag || alpha2Flag)
        {
            hevcHeader();
        }
        hevcHeader();
//        hevcData();
    }

    private void hevcHeader() throws IOException
    {
        long hevcHeaderLength = getUe732();
        int log2MinLumaCodingBlockSizeMinus3;
        int log2DiffMaxMinLumaCodingBlockSize;
        int log2MinTransformBlockSizeMinus2;
        int log2DiffMaxMinTransformBlockSize;
        int maxTransformHierarchyDepthIntra;
        boolean sampleAdaptiveOffsetEnabledFlag;
        boolean pcmEnabledFlag;
        if (pcmEnabledFlag) {
            int pcmSamplpeBitDepthLumaMinus1;
            int pcmSampleBitDepthChromaMinus1;
            int log2MinPcmLumaCodingBlockSizeMinus3;
            int log2DiffMaxMinPcmLumaCodingBlockSize;
            boolean pcmLoopFilterDisabledFlag;
        }
        boolean strongIntraSmoothingEnabledFlag;
        boolean spsExtensionPresentFlag;
        if (spsExtensionPresentFlag) {
            boolean spsRangeExtensionFlag;
            int spsExtension7Bits;
        }
        if (spsRangeExtensionFlag) {
            boolean transformSkipRotationEnabledFlag;
            boolean transformSkipContextEnabledFlag;
            boolean implicitRdpcmEnabledFlag;
            boolean explicitRdpcmEnabledFlag;
            boolean extendePrecisionProcessingFlag;
            boolean intraSmoothingDisabledFlag;
            boolean highPrecisionOffsetsEnabledFlag;
            boolean persistentRiceAdaptationEnabledFlag;
            boolean cabacBypassalignmentEnabledFlag;
        }
        int trailingBits;
    }


    private long getUe732() throws IOException
    {
        if (reader.isMotorolaByteOrder()) {
            // Motorola - MSB first (big endian)
            return (reader.getByte() & 0x7F) << 7 |
                (reader.getByte() & 0x7F);
        } else {
            // Intel ordering - LSB first (little endian)
            return (((long)reader.getByte())       & 0x7FL) |
                (((long)reader.getByte()) << 8  & 0x7F00L);
        }
    }
}
