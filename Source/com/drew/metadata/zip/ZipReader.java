package com.drew.metadata.zip;

import com.drew.lang.RandomAccessStreamReader;
import com.drew.lang.annotations.NotNull;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

/**
 * @author Payton Garland
 */
public class ZipReader {
    private ArrayList<String> fileNames = new ArrayList<String>();
    private ArrayList<String> fileModDates = new ArrayList<String>();
    private ArrayList<String> fileModTimes = new ArrayList<String>();
    private ArrayList<String> compatibility = new ArrayList<String>();
    private ArrayList<String> compression = new ArrayList<String>();
    private ArrayList<String> compressedSizes = new ArrayList<String>();
    private ArrayList<String> uncompressedSizes = new ArrayList<String>();
    private ArrayList<String> encryption = new ArrayList<String>();

    public void extract(@NotNull final RandomAccessStreamReader reader, @NotNull final Metadata metadata) throws IOException, ParseException {
        reader.setMotorolaByteOrder(false);
        ZipDirectory directory = new ZipDirectory();
        metadata.addDirectory(directory);
        int pos = 0;


        int search = 0;
        int centralDirectory = 0x02014B50;
        int endOfCentralDirectory = 0x06054b50;

        while (!(search == endOfCentralDirectory)) {
            search = reader.getInt32(pos);
            pos++;
            if (search == centralDirectory) {
                search = 0;
                extractCentralDirectory(reader, directory, pos);
            }
        }
        extractEndOfCentralDirectory(reader, directory, pos);
    }

    private void extractCentralDirectory(@NotNull final RandomAccessStreamReader reader, @NotNull final Directory directory, @NotNull int pos) throws IOException
    {
        pos += 3;

        int versionMadeBy = reader.getInt16(pos);
        pos += 2;
        addVersionMadeBy(versionMadeBy);

        int versionNeeded = reader.getInt16(pos);
        pos += 2;

        int bitFlag = reader.getInt16(pos);
        pos += 2;

        int compressionMethod = reader.getInt16(pos);
        pos += 2;
        addCompressionMethod(compressionMethod);

        addBitFlag(bitFlag, compressionMethod);

        int lastModFileTime = reader.getInt16(pos);
        pos += 2;

        fileModTimes.add(((lastModFileTime & 0xF800) >> 11) + ":"
            + String.format("%1$02d", ((lastModFileTime & 0x07E0) >> 5)) + ":"
            + String.format("%1$02d", ((lastModFileTime & 0x001F) / 2)));

        int lastModFileDate = reader.getInt16(pos);
        pos += 2;

        fileModDates.add((((lastModFileDate & 0xFE00) >> 9) + 1980) + ":"
            + String.format("%1$02d", ((lastModFileDate & 0x01E0) >> 5)) + ":"
            + String.format("%1$02d", (lastModFileDate & 0x001F)));


        int crc32 = reader.getInt32(pos);
        pos += 4;

        int compressedSize = reader.getInt32(pos);
        pos += 4;
        compressedSizes.add(compressedSize + " bytes");

        int uncompressedSize = reader.getInt32(pos);
        pos += 4;
        uncompressedSizes.add(uncompressedSize + " bytes");

        int fileNameLength = reader.getInt16(pos);
        pos += 2;

        int extraFieldLength = reader.getInt16(pos);
        pos += 2;

        int fileCommentLength = reader.getInt16(pos);
        pos += 2;

        int diskNumberStart = reader.getInt16(pos);
        pos += 2;

        int internalAttributes = reader.getInt16(pos);
        pos += 2;

        int externalAttributes = reader.getInt32(pos);
        pos += 4;

        int relativeOffsetOfLocalHeader = reader.getInt32(pos);
        pos += 4;

        String fileName = new String(reader.getBytes(pos, fileNameLength));
        fileNames.add(fileName);
        pos += fileNameLength;

        String extraField = new String(reader.getBytes(pos, extraFieldLength));
        pos += extraFieldLength;

        String fileComment = new String(reader.getBytes(pos, fileCommentLength));
        pos += fileCommentLength;
    }

    private void extractEndOfCentralDirectory(@NotNull final RandomAccessStreamReader reader, @NotNull final Directory directory, @NotNull int pos) throws IOException
    {
        pos += 3;

        int numberOfThisDisk = reader.getInt16(pos);
        pos += 2;

        int numberOfDiskStart = reader.getInt16(pos);
        pos += 2;

        pos += 2;

        int numberOfDirectories = reader.getInt16(pos);
        pos += 2;

        int sizeCentralDirectory = reader.getInt32(pos);
        pos += 4;

        int centralDirectoryOffset = reader.getInt32(pos);
        pos += 4;

        int zipFileCommentLength = reader.getInt16(pos);
        pos += 2;

        String zipFileComment = new String(reader.getBytes(pos, zipFileCommentLength));
        pos += zipFileCommentLength;

        directory.setInt(ZipDirectory.TAG_ZIP_FILE_COUNT, numberOfDirectories);
        directory.setString(ZipDirectory.TAG_ZIP_FILE_COMMENT, zipFileComment);
        setStringArray(fileNames, directory, ZipDirectory.TAG_FILE_NAME, numberOfDirectories);
        setStringArray(fileModDates, directory, ZipDirectory.TAG_MOD_DATE, numberOfDirectories);
        setStringArray(fileModTimes, directory, ZipDirectory.TAG_MOD_TIME, numberOfDirectories);
        setStringArray(compatibility, directory, ZipDirectory.TAG_COMPATIBILITY, numberOfDirectories);
        setStringArray(compression, directory, ZipDirectory.TAG_COMPRESSION, numberOfDirectories);
        setStringArray(compressedSizes, directory, ZipDirectory.TAG_COMPRESSED_SIZE, numberOfDirectories);
        setStringArray(uncompressedSizes, directory, ZipDirectory.TAG_UNCOMPRESSED_SIZE, numberOfDirectories);
        setStringArray(encryption, directory, ZipDirectory.TAG_ENCRYPTION, numberOfDirectories);
    }

    private void setStringArray(ArrayList<String> values, Directory directory, int tagType, int numberOfDirectories)
    {
        String[] tagHolder = new String[numberOfDirectories];
        for (int i = 0; i < numberOfDirectories; i++) {
            tagHolder[i] = values.get(i);
        }
        directory.setStringArray(tagType, tagHolder);
    }

    private void addVersionMadeBy(int versionMadeBy)
    {
        int upperByte = ((versionMadeBy & 0xFF00) >> 8);
        switch (upperByte) {
            case (0):
                compatibility.add("MS-DOS and OS/2 (FAT / VFAT / FAT32 file systems)");
                break;
            case (1):
                compatibility.add("Amiga");
                break;
            case (2):
                compatibility.add("OpenVMS");
                break;
            case (3):
                compatibility.add("UNIX");
                break;
            case (4):
                compatibility.add("VM/CMS");
                break;
            case (5):
                compatibility.add("Atari ST");
                break;
            case (6):
                compatibility.add("OS/2 H.P.F.S.");
                break;
            case (7):
                compatibility.add("Macintosh");
                break;
            case (8):
                compatibility.add("Z-System");
                break;
            case (9):
                compatibility.add("CP/M");
                break;
            case (10):
                compatibility.add("Windows NTFS");
                break;
            case (11):
                compatibility.add("MVS (OS/390 - Z/OS)");
                break;
            case (12):
                compatibility.add("VSE");
                break;
            case (13):
                compatibility.add("Acorn Risc");
                break;
            case (14):
                compatibility.add("VFAT");
                break;
            case (15):
                compatibility.add("alternate MVS");
                break;
            case (16):
                compatibility.add("BeOS");
                break;
            case (17):
                compatibility.add("Tandem");
                break;
            case (18):
                compatibility.add("OS/400");
                break;
            case (19):
                compatibility.add("OS X (Darwin)");
                break;
            default:
                compatibility.add(" ");
        }
    }

    private void addCompressionMethod(int compressionMethod)
    {
        switch (compressionMethod) {
            case (0):
                compression.add("The file is stored (no compression)");
                break;
            case (1):
                compression.add("The file is Shrunk");
                break;
            case (2):
                compression.add("The file is Reduced with compression factor 1");
                break;
            case (3):
                compression.add("The file is Reduced with compression factor 2");
                break;
            case (4):
                compression.add("The file is Reduced with compression factor 3");
                break;
            case (5):
                compression.add("The file is Reduced with compression factor 4");
                break;
            case (6):
                compression.add("The file is Imploded");
                break;
            case (7):
                compression.add("Reserved for Tokenizing compression algorithm");
                break;
            case (8):
                compression.add("The file is Deflated");
                break;
            case (9):
                compression.add("Enhanced Deflating using Deflate64(tm)");
                break;
            case (10):
                compression.add("PKWARE Data Compression Library Imploding (old IBM TERSE)");
                break;
            case (11):
                compression.add("Reserved by PKWARE");
                break;
            case (12):
                compression.add("File is compressed using BZIP2 algorithm");
                break;
            case (13):
                compression.add("Reserved by PKWARE");
                break;
            case (14):
                compression.add("LZMA (EFS)");
                break;
            case (15):
                compression.add("Reserved by PKWARE");
                break;
            case (16):
                compression.add("Reserved by PKWARE");
                break;
            case (17):
                compression.add("Reserved by PKWARE");
                break;
            case (18):
                compression.add("File is compressed using IBM TERSE (new)");
                break;
            case (19):
                compression.add("IBM LZ77 z Architecture (PFS)");
                break;
            case (97):
                compression.add("WavPack compressed data");
                break;
            case (98):
                compression.add("PPMd version I, Rev 1");
                break;
            default:
                compression.add(" ");
        }
    }

    private void addBitFlag(int bitFlag, int compressionMethod)
    {
        if ((bitFlag & 0x0001) == 0x0001) {
            encryption.add("true");
        } else {
            encryption.add("false");
        }

        switch (compressionMethod) {
            case (6):
                if ((bitFlag & 0x0002) == 0x0002) {
//                    System.out.println("8k Sliding Dictionary");
                } else {
//                    System.out.println("4k Sliding Dictionary");
                }
                if ((bitFlag & 0x0004) == 0x0004) {
//                    System.out.println("3 Shannon-Fano trees");
                } else {
//                    System.out.println("2 Shannon-Fano trees");
                }
                break;
            case (8):
            case (9):
                switch (bitFlag & 0x0006) {
                    case (0x0000):
//                        System.out.println("Normal Compressoin");
                        break;
                    case (0x0002):
//                        System.out.println("Maximum Compression");
                        break;
                    case (0x0004):
//                        System.out.println("Fast Compression");
                        break;
                    case (0x0006):
//                        System.out.println("Super Fast Compression");
                        break;
                    default:
                }
                break;
            case (14):
                break;
            default:
        }

        if ((bitFlag & 0x0008) == 0x0008) {
//            System.out.println("Compressed/Uncompressed set to 0 in Local Header");
        } else {
//            System.out.println("Compressed/Uncompressed set in Local Header");
        }

        if ((bitFlag & 0x0020) == 0x0020) {
//            System.out.println("File is compressed, patched data");
        } else {
//            System.out.println("File is not compressed, patched data");
        }
    }
}
