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

    public void extract(@NotNull final String filePath, @NotNull final Metadata metadata) throws IOException, ParseException {

        ZipFile zipFile = new ZipFile(new File(filePath));

        ZipDirectory directory = new ZipDirectory();
        metadata.addDirectory(directory);

        Enumeration<? extends ZipEntry> entries = zipFile.entries();
        int fileCount = 0;

        while (entries.hasMoreElements()) {
            ZipEntry entry = entries.nextElement();

            int spacer = (fileCount * 6) + fileCount + 100;
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

            ZipDirectory._tagNameMap.put(spacer + 5, "File " + fileCount + ": Comment");
            if (entry.getComment() != null)
                directory.setString(spacer + 5, entry.getComment());
        }

        directory.setInt(ZipDirectory.TAG_ZIP_FILE_COUNT, fileCount);
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
}
