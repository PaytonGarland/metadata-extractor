package com.drew.metadata.svg;

import com.adobe.xmp.XMPException;
import com.adobe.xmp.XMPMeta;
import com.adobe.xmp.XMPMetaFactory;
import com.drew.lang.annotations.NotNull;
import com.drew.metadata.Metadata;
import com.drew.metadata.xmp.XmpDirectory;
import com.drew.metadata.xmp.XmpReader;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.namespace.NamespaceContext;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.*;
import java.io.*;
import java.util.Scanner;

/**
 * @author Payton Garland
 */
public class SvgReader {
    /**
     * Parses XML structure to find metadata and svg attributes.
     *
     * @param inputStream InputStream used to get nodes/attributes
     * @param metadata Metadata where extracted information is added
     */
    public void extract(@NotNull final InputStream inputStream, @NotNull final Metadata metadata)
    {
        SvgDirectory directory = new SvgDirectory();
        metadata.addDirectory(directory);
        try {
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            documentBuilderFactory.setNamespaceAware(true);
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document doc = documentBuilder.parse(inputStream);
            XPath xPath = XPathFactory.newInstance().newXPath();
            NamespaceContext ctx = new SvgNamespaceResolver();
            xPath.setNamespaceContext(ctx);

            // Get all attributes
            NodeList svgAttributeResults = (NodeList) xPath.evaluate("//@*", doc, XPathConstants.NODESET);
            addResults(svgAttributeResults, directory);

            // Get all dublin core metadata elements
            NodeList dublinCoreResults = (NodeList) xPath.evaluate("//dc:*", doc, XPathConstants.NODESET);
            addResults(dublinCoreResults, directory);
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }
    }

    /**
     * Sort through extracted node list and separate into directory accordingly.
     *
     * @param nodeList NodeList of elements to sort
     * @param directory Directory to add sorted elements to
     */
    private void addResults(NodeList nodeList, SvgDirectory directory)
    {
        for (int i = 0; i < nodeList.getLength(); i++) {
            String name = nodeList.item(i).getLocalName();
            for (Integer key : SvgDirectory._tagNameMap.keySet()) {
                if (name.toUpperCase().equals(directory.getTagName(key).toUpperCase())) {
                    if (directory.getString(key) == null)
                        directory.setString(key, nodeList.item(i).getTextContent().trim());
                } else if (name.equals("viewBox")) {
                    Scanner scnr = new Scanner(nodeList.item(i).getTextContent());
                    directory.setFloat(1, scnr.nextFloat());
                    directory.setFloat(3, scnr.nextFloat());
                    directory.setFloat(2, scnr.nextFloat());
                    directory.setFloat(4, scnr.nextFloat());
                }
            }
        }
    }
}
