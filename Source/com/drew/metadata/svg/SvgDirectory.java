package com.drew.metadata.svg;

import com.drew.lang.annotations.NotNull;
import com.drew.metadata.Directory;

import java.util.HashMap;

/**
 * @author Payton Garland
 */
public class SvgDirectory extends Directory
{
    public static final int TAG_VIEWBOX_MIN_WIDTH                           = 1;
    public static final int TAG_VIEWBOX_WIDTH                               = 2;
    public static final int TAG_VIEWBOX_MIN_HEIGHT                          = 3;
    public static final int TAG_VIEWBOX_HEIGHT                              = 4;
    public static final int TAG_WIDTH                                       = 5;
    public static final int TAG_HEIGHT                                      = 6;
    public static final int TAG_TITLE                                       = 7;
    public static final int TAG_SUBJECT                                     = 8;
    public static final int TAG_DESCRIPTION                                 = 9;
    public static final int TAG_TYPE                                        = 10;
    public static final int TAG_SOURCE                                      = 11;
    public static final int TAG_RELATION                                    = 12;
    public static final int TAG_COVERAGE                                    = 13;
    public static final int TAG_CREATOR                                     = 14;
    public static final int TAG_PUBLISHER                                   = 15;
    public static final int TAG_CONTRIBUTOR                                 = 16;
    public static final int TAG_RIGHTS                                      = 17;
    public static final int TAG_DATE                                        = 18;
    public static final int TAG_FORMAT                                      = 19;
    public static final int TAG_IDENTIFIER                                  = 20;
    public static final int TAG_LANGUAGE                                    = 21;
    public static final int TAG_AUDIENCE                                    = 22;
    public static final int TAG_PROVENANCE                                  = 23;
    public static final int TAG_RIGHTS_HOLDER                               = 24;
    public static final int TAG_INSTRUCTIONAL_METHOD                        = 25;
    public static final int TAG_ACCRUAL_METHOD                              = 26;
    public static final int TAG_ACCRUAL_PERIODICITY                         = 27;
    public static final int TAG_ACCRUAL_POLICY                              = 28;
    public static final int TAG_ID                                          = 29;

    @NotNull
    protected static final HashMap<Integer, String> _tagNameMap = new HashMap<Integer, String>();

    static {

        _tagNameMap.put(TAG_VIEWBOX_MIN_WIDTH, "View Box Minimum Width");
        _tagNameMap.put(TAG_VIEWBOX_WIDTH, "View Box Width");
        _tagNameMap.put(TAG_VIEWBOX_MIN_HEIGHT, "View Box Minimum Height");
        _tagNameMap.put(TAG_VIEWBOX_HEIGHT, "View Box Height");
        _tagNameMap.put(TAG_WIDTH, "Width");
        _tagNameMap.put(TAG_HEIGHT, "Height");
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
        _tagNameMap.put(TAG_PROVENANCE, "Provenance");
        _tagNameMap.put(TAG_RIGHTS_HOLDER, "Rights Holder");
        _tagNameMap.put(TAG_INSTRUCTIONAL_METHOD, "Instructional Method");
        _tagNameMap.put(TAG_ACCRUAL_METHOD, "Accrual Method");
        _tagNameMap.put(TAG_ACCRUAL_PERIODICITY, "Accrual Periodicity");
        _tagNameMap.put(TAG_ACCRUAL_POLICY, "Accrual Policy");
        _tagNameMap.put(TAG_ID, "Id");

    }

    public SvgDirectory()
    {
        this.setDescriptor(new SvgDescriptor(this));
    }

    @Override
    @NotNull
    public String getName()
    {
        return "SVG";
    }

    @Override
    @NotNull
    protected HashMap<Integer, String> getTagNameMap()
    {
        return _tagNameMap;
    }
}
