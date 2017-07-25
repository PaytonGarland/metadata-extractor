package com.drew.metadata.svg;

import javax.xml.namespace.NamespaceContext;
import java.util.Iterator;

/**
 * @author Payton Garland
 */
public class SvgNamespaceResolver implements NamespaceContext {
    @Override
    public String getNamespaceURI(String prefix) {
        if (prefix.equals("dc")) {
            return "http://purl.org/dc/elements/1.1/";
        } else if (prefix.equals("cc")) {
            return "http://creativecommons.org/ns#";
        } else if (prefix.equals("rdf")) {
            return "http://www.w3.org/1999/02/22-rdf-syntax-ns#";
        } else if (prefix.equals("svg")) {
            return "http://www.w3.org/2000/svg";
        }
        return null;
    }

    @Override
    public String getPrefix(String namespaceURI) {
        return null;
    }

    @Override
    public Iterator getPrefixes(String namespaceURI) {
        return null;
    }
}
