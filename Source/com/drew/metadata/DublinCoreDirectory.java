package com.drew.metadata;

import java.util.HashMap;

/**
 * @author Payton Garland
 */
public class DublinCoreDirectory
{
    public static final String TAG_TITLE = "dc:title";
    public static final String TAG_SUBJECT = "dc:subject";
    public static final String TAG_DESCRIPTION = "dc:description";
    public static final String TAG_TYPE = "dc:type";
    public static final String TAG_SOURCE = "dc:source";
    public static final String TAG_RELATION = "dc:relation";
    public static final String TAG_COVERAGE = "dc:coverage";
    public static final String TAG_CREATOR = "dc:creator";
    public static final String TAG_PUBLISHER = "dc:publisher";
    public static final String TAG_CONTRIBUTOR = "dc:contributor";
    public static final String TAG_RIGHTS = "dc:rights";
    public static final String TAG_DATE = "dc:date";
    public static final String TAG_FORMAT = "dc:format";
    public static final String TAG_IDENTIFIER = "dc:identifier";
    public static final String TAG_LANGUAGE = "dc:language";
    public static final String TAG_AUDIENCE = "dc:audience";

    public static HashMap<String, String> _tagNameMap = new HashMap<String, String>();

    static {
        _tagNameMap.put(TAG_TITLE, "Title");
        _tagNameMap.put(TAG_SUBJECT, "Subject");
        _tagNameMap.put(TAG_DESCRIPTION, "Description");
        _tagNameMap.put(TAG_TYPE, "Type");
        _tagNameMap.put(TAG_SOURCE, "Source");
        _tagNameMap.put(TAG_RELATION, "Relation");
        _tagNameMap.put(TAG_COVERAGE, "Coverage");
        _tagNameMap.put(TAG_CREATOR, "Creator");
        _tagNameMap.put(TAG_PUBLISHER, "Publisher");
        _tagNameMap.put(TAG_CONTRIBUTOR, "Contributor");
        _tagNameMap.put(TAG_RIGHTS, "Rights");
        _tagNameMap.put(TAG_DATE, "Date");
        _tagNameMap.put(TAG_FORMAT, "Format");
        _tagNameMap.put(TAG_IDENTIFIER, "Identifier");
        _tagNameMap.put(TAG_LANGUAGE, "Language");
        _tagNameMap.put(TAG_AUDIENCE, "Audience");
    }
}
