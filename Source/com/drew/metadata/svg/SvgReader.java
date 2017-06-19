package com.drew.metadata.svg;

import com.drew.lang.annotations.NotNull;
import com.drew.metadata.Metadata;
import com.sun.deploy.xml.XMLAttribute;
import com.sun.deploy.xml.XMLNode;
import com.sun.deploy.xml.XMLParser;
import com.sun.org.apache.xml.internal.security.transforms.params.XPathContainer;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.namespace.NamespaceContext;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.*;
import java.io.*;
import java.util.Iterator;

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

            // Get all attributes in SVG node
            NodeList svgAttributeResults = (NodeList) xPath.evaluate("//svg/@*", doc, XPathConstants.NODESET);
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

    private void addResults(NodeList nodeList, SvgDirectory directory)
    {
        for (int i = 0; i < nodeList.getLength(); i++) {
            String name = nodeList.item(i).getLocalName();
            for (Integer key : SvgDirectory._tagNameMap.keySet()) {
                if (name.toUpperCase().equals(directory.getTagName(key).toUpperCase())) {
                    if (directory.getString(key) == null)
                        directory.setString(key, nodeList.item(i).getTextContent().trim());
                }
            }
        }
    }
}
