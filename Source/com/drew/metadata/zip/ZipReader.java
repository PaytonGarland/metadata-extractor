package com.drew.metadata.zip;

import com.drew.lang.RandomAccessStreamReader;
import com.drew.lang.annotations.NotNull;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 * @author Payton Garland
 */
public class ZipReader {
    private ArrayList<String> fileNames = new ArrayList<String>();
    private ArrayList<String> fileModDates = new ArrayList<String>();
    private ArrayList<String> fileModTimes = new ArrayList<String>();
    private ArrayList<String> compatibility = new ArrayList<String>();
    private ArrayList<String> versionsNeeded = new ArrayList<String>();
    private ArrayList<String> compression = new ArrayList<String>();
    private ArrayList<String> compressedSizes = new ArrayList<String>();
    private ArrayList<String> uncompressedSizes = new ArrayList<String>();
    private ArrayList<String> encryption = new ArrayList<String>();

    public void extract(@NotNull final RandomAccessStreamReader reader, @NotNull final Metadata metadata) throws IOException, ParseException {

        ZipFile zipFile = new ZipFile(new File("Tests/Data/Archive.zip"));

        ZipDirectory directory = new ZipDirectory();
        metadata.addDirectory(directory);

        Enumeration<? extends ZipEntry> entries = zipFile.entries();
        int fileCount = 0;

        while (entries.hasMoreElements()) {
            ZipEntry entry = entries.nextElement();
            int spacer = (fileCount * 5) + fileCount + 100;
            fileCount++;
            SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
            ZipDirectory._tagNameMap.put(spacer, "File " + fileCount + ": Name");
            directory.setString(spacer, entry.getName());

            ZipDirectory._tagNameMap.put(spacer + 1, "File " + fileCount + ": Modification Date");
            Date date = new Date(entry.getTime());
            String modificationDate = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss").format(date);
            directory.setString(spacer + 1, modificationDate);

            ZipDirectory._tagNameMap.put(spacer + 2, "File " + fileCount + ": Compressed Size");
            directory.setString(spacer + 2, entry.getCompressedSize() + " bytes");

            ZipDirectory._tagNameMap.put(spacer + 3, "File " + fileCount + ": Uncompressed Size");
            directory.setString(spacer + 3, entry.getSize() + " bytes");

            ZipDirectory._tagNameMap.put(spacer + 4, "File " + fileCount + ": Compression Method");
            directory.setString(spacer + 4, addCompressionMethod(entry.getMethod()));
        }

        directory.setInt(ZipDirectory.TAG_ZIP_FILE_COUNT, fileCount);
//        reader.setMotorolaByteOrder(false);

//        int pos = 0;
//
//
//        int search = 0;
//        int centralDirectory = 0x02014B50;
//        int zip64endOfCentralDirectory = 0x06064B50;
//        int endOfCentralDirectory = 0x06054b50;
//
//        while (!(search == endOfCentralDirectory)) {
//            search = reader.getInt32(pos);
//            pos++;
//            if (search == centralDirectory) {
//                search = 0;
//                extractCentralDirectory(reader, directory, pos);
//            } else if (search == zip64endOfCentralDirectory) {
//                search = 0;
//                extractZip64EndOfCentralDirectory(reader, directory, pos);
//            }
//        }
//        extractEndOfCentralDirectory(reader, directory, pos);
    }

    private void extractCentralDirectory(@NotNull final RandomAccessStreamReader reader, @NotNull final Directory directory, @NotNull int pos) throws IOException
    {
        pos += 3;

        int versionMadeBy = reader.getInt16(pos);
        pos += 2;
        addVersionMadeBy(versionMadeBy);

        int versionNeeded = reader.getInt16(pos);
        pos += 2;
        addVersionNeeded(versionNeeded);

        int bitFlag = reader.getInt16(pos);
        pos += 2;

        int compressionMethod = reader.getInt16(pos);
        pos += 2;
        addCompressionMethod(compressionMethod);

        addBitFlag(bitFlag, compressionMethod);

        int lastModFileTime = reader.getInt16(pos);
        pos += 2;

        fileModTimes.add(String.format("%1$02d", ((lastModFileTime & 0xF800) >> 11)) + ":"
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

    private void extractZip64EndOfCentralDirectory(@NotNull final RandomAccessStreamReader reader, @NotNull final Directory directory, @NotNull int pos) throws IOException
    {
        //TODO: Test Zip64 extension
        long sizeOfFields = reader.getInt64(pos);
        pos += 8;

        int versionMadeBy = reader.getInt16(pos);
        pos += 2;

        int versionNeeded = reader.getInt16(pos);
        pos += 2;

        int currentDiskNum = reader.getInt32(pos);
        pos += 4;

        int diskNumWithCentralDir = reader.getInt32(pos);
        pos += 4;

        //long numCentralDirectoriesOnDisk = reader.getInt64(pos);
        pos += 8;

        long totalNumberOfEntries = reader.getInt64(pos);
        pos += 8;

        long sizeOfCentralDirectory = reader.getInt64(pos);
        pos += 8;

        long offsetOfCentralDirectory = reader.getInt64(pos);
        pos += 8;

        pos += sizeOfCentralDirectory;
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

    private String addCompressionMethod(int compressionMethod)
    {
        switch (compressionMethod) {
            case (0):
                return ("The file is stored (no compression)");
            case (1):
                return ("The file is Shrunk");
            case (2):
                return ("The file is Reduced with compression factor 1");
            case (3):
                return ("The file is Reduced with compression factor 2");
            case (4):
                return ("The file is Reduced with compression factor 3");
            case (5):
                return ("The file is Reduced with compression factor 4");
            case (6):
                return ("The file is Imploded");
            case (7):
                return ("Reserved for Tokenizing compression algorithm");
            case (8):
                return ("The file is Deflated");
            case (9):
                return ("Enhanced Deflating using Deflate64(tm)");
            case (10):
                return ("PKWARE Data Compression Library Imploding (old IBM TERSE)");
            case (11):
                return ("Reserved by PKWARE");
            case (12):
                return ("File is compressed using BZIP2 algorithm");
            case (13):
                return ("Reserved by PKWARE");
            case (14):
                return ("LZMA (EFS)");
            case (15):
                return ("Reserved by PKWARE");
            case (16):
                return ("Reserved by PKWARE");
            case (17):
                return ("Reserved by PKWARE");
            case (18):
                return ("File is compressed using IBM TERSE (new)");
            case (19):
                return ("IBM LZ77 z Architecture (PFS)");
            case (97):
                return ("WavPack compressed data");
            case (98):
                return ("PPMd version I, Rev 1");
            default:
                return (" ");
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

    private void addVersionNeeded(int versionNeeded)
    {
//        switch (versionNeeded) {
//            case (1.0):
//                versionsNeeded.add("Default value");
//                break;
//            case (1.1):
//                versionsNeeded.add("File is a volume label");
//                break;
//            case (2.0):
//                versionsNeeded.add("File is a folder (directory)");
//                break;
//            case (2.0):
//                versionsNeeded.add("File is compressed using Deflate compression");
//                break;
//            case (2.0):
//                versionsNeeded.add("File is encrypted using traditional PKWARE encryption");
//                break;
//            case (2.1):
//                versionsNeeded.add("File is compressed using Deflate64(tm)");
//                break;
//            case (2.5):
//                versionsNeeded.add("File is compressed using PKWARE DCL Implode");
//                break;
//            case (2.7):
//                versionsNeeded.add("File is a patch data set");
//                break;
//            case (4.5):
//                versionsNeeded.add("File uses ZIP64 format extensions");
//                break;
//            case (4.6):
//                versionsNeeded.add("File is compressed using BZIP2 compression*");
//                break;
//            case (5.0):
//                versionsNeeded.add("File is encrypted using DES");
//                break;
//            case (5.0):
//                versionsNeeded.add("File is encrypted using 3DES");
//                break;
//            case (5.0):
//                versionsNeeded.add("File is encrypted using original RC2 encryption");
//                break;
//            case (5.0):
//                versionsNeeded.add("File is encrypted using RC4 encryption");
//                break;
//            case (5.1):
//                versionsNeeded.add("File is encrypted using AES encryption");
//                break;
//            case (5.1):
//                versionsNeeded.add("File is encrypted using corrected RC2 encryption**");
//                break;
//            case (5.2):
//                versionsNeeded.add("File is encrypted using corrected RC2-64 encryption**");
//                break;
//            case (6.1):
//                versionsNeeded.add("File is encrypted using non-OAEP key wrapping***");
//                break;
//            case (6.2):
//                versionsNeeded.add("Central directory encryption");
//                break;
//            case (6.3):
//                versionsNeeded.add("File is compressed using LZMA");
//                break;
//            case (6.3):
//                versionsNeeded.add("File is compressed using PPMd+");
//                break;
//            case (6.3):
//                versionsNeeded.add("File is encrypted using Blowfish");
//                break;
//            case (6.3):
//                versionsNeeded.add("File is encrypted using Twofish");
//                break;
//            default:
//        }
    }
}
