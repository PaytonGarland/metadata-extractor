package com.drew.imaging.xml;

import com.adobe.xmp.XMPException;
import com.adobe.xmp.XMPMeta;
import com.adobe.xmp.XMPMetaFactory;
import com.adobe.xmp.properties.XMPProperty;
import com.drew.imaging.ImageProcessingException;
import com.drew.lang.annotations.NotNull;
import com.drew.metadata.Metadata;
import com.drew.metadata.file.FileMetadataReader;
import com.drew.metadata.svg.SvgReader;
import com.sun.deploy.xml.XMLNode;
import com.sun.deploy.xml.XMLParser;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

/**
 * @author
 */
public class XmlMetadataReader {
    @NotNull
    public static Metadata readMetadata(@NotNull File file) throws IOException, ImageProcessingException
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
    public static Metadata readMetadata(@NotNull InputStream inputStream) throws ImageProcessingException
    {
        Scanner scnr = new Scanner(inputStream);
        Metadata metadata = new Metadata();
        String temp = "";
        try {
            while (scnr.hasNextLine()) {
                temp += scnr.nextLine();
            }
            XMLParser xmlParser = new XMLParser(temp);
            XMLNode node = xmlParser.parse();
            if (!node.getName().equals("svg")) {
                throw new ImageProcessingException("File format is not supported");
            } else {
                scnr.close();
                SvgReader reader = new SvgReader();
                reader.extract(xmlParser, metadata);
            }
        } catch (SAXException e) {
            e.printStackTrace();
        }
        return metadata;
    }
}
