package com.drew.metadata.svg;

import com.drew.lang.annotations.NotNull;
import com.drew.metadata.DublinCoreDirectory;
import com.drew.metadata.Metadata;
import com.sun.deploy.xml.XMLAttribute;
import com.sun.deploy.xml.XMLNode;
import com.sun.deploy.xml.XMLParser;
import org.xml.sax.SAXException;

/**
 * @author Payton Garland
 */
public class SvgReader {

    int attributeCount = 0x0100;

    /**
     * Parses XML structure to find metadata and svg attributes.
     *
     * @param xmlParser XMLParser used to parse nodes/attributes
     * @param metadata Metadata where extracted information is added
     */
    public void extract(@NotNull final XMLParser xmlParser, @NotNull final Metadata metadata)
    {
        SvgDirectory directory = new SvgDirectory();
        metadata.addDirectory(directory);

        try {
            XMLNode svg = xmlParser.parse();
            XMLAttribute curr = svg.getAttributes();

            // Add all attributes in SVG node
            while (curr != null) {
                addAttributes(curr, directory);
                curr = curr.getNext();
            }

            // Search for metadata node (if there is one)
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

    /**
     * Extract and filter encountered attributes.
     *
     * @param attribute XMLAttribute to be extracted
     * @param directory Directory to be contributed to
     */
    public void addAttributes(@NotNull XMLAttribute attribute, @NotNull SvgDirectory directory)
    {
        String name = attribute.getName();

        // Filter attributes and add them to directory
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
//            SvgDirectory._tagNameMap.put(attributeCount, attribute.getName());
//            directory.setString(attributeCount, attribute.getValue());
        }
        attributeCount++;
    }

    /**
     * Recursively parses nodes in metadata to find attributes and passes the job
     * over to addAttributes.
     *
     * @param node XMLNode current node being parsed for attributes/children
     * @param directory Directory to be contributed to
     */
    public void addMetadata(XMLNode node, @NotNull SvgDirectory directory)
    {
        // Base case
        if (node == null)
            return;

        // Loop through all attributes
        XMLNode curr = node;
        while (curr != null) {
            addDublinCoreMetadata(node, directory);
            curr = curr.getNext();
        }

        // Get any children of current node and repeat
        XMLNode child = node.getNested();
        while (child != null) {
            addMetadata(child, directory);
            child = child.getNext();
        }

    }

    public void addDublinCoreMetadata(XMLNode node, @NotNull SvgDirectory directory)
    {
        String name = node.getName();
        for (Integer key : SvgDirectory._tagNameMap.keySet()) {
            if (node.getNested() != null && node.getNested().getNested() == null && name.toUpperCase().equals(directory.getTagName(key).toUpperCase())) {
                directory.setString(key, node.getNested().toString());
            }
        }
    }

}
