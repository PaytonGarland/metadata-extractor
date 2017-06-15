package com.drew.metadata.svg;

import com.adobe.xmp.XMPException;
import com.adobe.xmp.XMPMeta;
import com.adobe.xmp.XMPMetaFactory;
import com.adobe.xmp.properties.XMPProperty;
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
import java.util.Iterator;
import java.util.Scanner;

/**
 * @author Payton Garland
 */
public class SvgReader {

    int attributeCount = 0x0100;

    public void extract(@NotNull final XMLParser xmlParser, @NotNull final Metadata metadata)
    {
        SvgDirectory directory = new SvgDirectory();
        metadata.addDirectory(directory);

        try {
            XMLNode svg = xmlParser.parse();
            XMLAttribute curr = svg.getAttributes();
            while (curr != null) {
                addAttributes(curr, directory);
                curr = curr.getNext();
            }

            XMLNode node = svg.getNested();
            while (node != null && !node.getName().equals("metadata")) {
                node = node.getNext();
            }
            if (node != null && node.getName().equals("metadata"))
                addMetadata(node, directory);

        } catch (SAXException e) {
            e.printStackTrace();
        }
    }

    public void addAttributes(@NotNull XMLAttribute attribute, @NotNull SvgDirectory directory)
    {
        String name = attribute.getName();

        if (name.equals("viewBox")) {
            String[] bounds = attribute.getValue().split(" ");
            if (bounds.length == 4) {
                directory.setString(SvgDirectory.TAG_VIEWBOX_MIN_WIDTH, bounds[0]);
                directory.setString(SvgDirectory.TAG_VIEWBOX_MIN_HEIGHT, bounds[1]);
                directory.setString(SvgDirectory.TAG_VIEWBOX_WIDTH, bounds[2]);
                directory.setString(SvgDirectory.TAG_VIEWBOX_HEIGHT, bounds[3]);
            } else {
                directory.addError("ERROR: ViewBox is missing values. Data not added.");
            }
        } else if (name.equals("width")) {
            directory.setString(SvgDirectory.TAG_WIDTH, attribute.getValue());
        } else if (name.equals("height")) {
            directory.setString(SvgDirectory.TAG_HEIGHT, attribute.getValue());
        } else {
            SvgDirectory._tagNameMap.put(attributeCount, attribute.getName());
            directory.setString(attributeCount, attribute.getValue());
        }
        attributeCount++;
    }

    public void addMetadata(XMLNode node, @NotNull SvgDirectory directory)
    {
        if (node == null)
            return;

        XMLAttribute curr = node.getAttributes();
        while (curr != null) {
            addAttributes(curr, directory);
            curr = curr.getNext();
        }

        XMLNode child = node.getNested();
        while (child != null) {
            addMetadata(child, directory);
            child = child.getNext();
        }

    }

}
