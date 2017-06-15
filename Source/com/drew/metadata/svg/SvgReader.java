package com.drew.metadata.svg;

import com.drew.lang.annotations.NotNull;
import com.drew.metadata.Metadata;
import com.sun.deploy.xml.XMLAttribute;
import com.sun.deploy.xml.XMLNode;
import com.sun.deploy.xml.XMLParser;
import com.sun.org.apache.xerces.internal.xni.XMLAttributes;
import org.apache.commons.io.IOUtils;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

/**
 * @author Payton Garland
 */
public class SvgReader {

    public void extract(@NotNull final XMLParser xmlParser, @NotNull final Metadata metadata)
    {
        try {
            XMLNode svg = xmlParser.parse();
            XMLAttribute curr = svg.getAttributes();

            while (curr != null) {
                System.out.println(curr.getName() + ": " + curr.getValue());
                curr = curr.getNext();
            }
        } catch (SAXException e) {
            e.printStackTrace();
        }
    }

}
