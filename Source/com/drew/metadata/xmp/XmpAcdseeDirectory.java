package com.drew.metadata.xmp;

import com.drew.lang.annotations.NotNull;

import java.util.HashMap;

/**
 * Created by pgarland on 6/8/17.
 */
public class XmpAcdseeDirectory extends XmpDirectoryBase{

    public static final int TAG_XMP_ACDSEE_AUTHOR = "acdsee:Author".hashCode();
    public static final int TAG_XMP_ACDSEE_CAPTION = "acdsee:Caption".hashCode();
    public static final int TAG_XMP_ACDSEE_CATEGORIES = "acdsee:Categories".hashCode();
    public static final int TAG_XMP_ACDSEE_COLLECTIONS = "acdsee:Collections".hashCode();
    public static final int TAG_XMP_ACDSEE_DATETIME = "acdsee:DateTime".hashCode();
    public static final int TAG_XMP_ACDSEE_DPP = "acdsee:DPP".hashCode();
    public static final int TAG_XMP_ACDSEE_EDITSTATUS = "acdsee:EditStatus".hashCode();
    public static final int TAG_XMP_ACDSEE_FIXTUREIDENTIFIER = "acdsee:FixtureIdentifier".hashCode();
    public static final int TAG_XMP_ACDSEE_KEYWORDS = "acdsee:Keywords".hashCode();
    public static final int TAG_XMP_ACDSEE_NOTES = "acdsee:Notes".hashCode();
    public static final int TAG_XMP_ACDSEE_OBJECTCYCLE = "acdsee:ObjectCycle".hashCode();
    public static final int TAG_XMP_ACDSEE_ORIGINATINGPROGRAM = "acdsee:OriginatingProgram".hashCode();
    public static final int TAG_XMP_ACDSEE_RATING = "acdsee:Rating".hashCode();
    public static final int TAG_XMP_ACDSEE_RAWRPPUSED = "acdsee:Rawrppused".hashCode();
    public static final int TAG_XMP_ACDSEE_RELEASEDATE = "acdsee:ReleaseDate".hashCode();
    public static final int TAG_XMP_ACDSEE_RELEASETIME = "acdsee:ReleaseTime".hashCode();
    public static final int TAG_XMP_ACDSEE_RPP = "acdsee:RPP".hashCode();
    public static final int TAG_XMP_ACDSEE_SNAPSHOTS = "acdsee:Snapshots".hashCode();
    public static final int TAG_XMP_ACDSEE_TAGGED = "acdsee:Tagged".hashCode();

    @NotNull
    protected static final HashMap<Integer, String> _tagNameMap = new HashMap<Integer, String>();

    static {
        _tagNameMap.put(TAG_XMP_ACDSEE_AUTHOR, "acdsee:Author");
        _tagNameMap.put(TAG_XMP_ACDSEE_CAPTION, "acdsee:Caption");
        _tagNameMap.put(TAG_XMP_ACDSEE_CATEGORIES, "acdsee:Categories");
        _tagNameMap.put(TAG_XMP_ACDSEE_COLLECTIONS, "acdsee:Collections");
        _tagNameMap.put(TAG_XMP_ACDSEE_DATETIME, "acdsee:DateTime");
        _tagNameMap.put(TAG_XMP_ACDSEE_DPP, "acdsee:DPP");
        _tagNameMap.put(TAG_XMP_ACDSEE_EDITSTATUS, "acdsee:EditStatus");
        _tagNameMap.put(TAG_XMP_ACDSEE_FIXTUREIDENTIFIER, "acdsee:FixtureIdentifier");
        _tagNameMap.put(TAG_XMP_ACDSEE_KEYWORDS, "acdsee:Keywords");
        _tagNameMap.put(TAG_XMP_ACDSEE_NOTES, "acdsee:Notes");
        _tagNameMap.put(TAG_XMP_ACDSEE_OBJECTCYCLE, "acdsee:ObjectCycle");
        _tagNameMap.put(TAG_XMP_ACDSEE_ORIGINATINGPROGRAM, "acdsee:OriginatingProgram");
        _tagNameMap.put(TAG_XMP_ACDSEE_RATING, "acdsee:Rating");
        _tagNameMap.put(TAG_XMP_ACDSEE_RAWRPPUSED, "acdsee:Rawrppused");
        _tagNameMap.put(TAG_XMP_ACDSEE_RELEASEDATE, "acdsee:ReleaseDate");
        _tagNameMap.put(TAG_XMP_ACDSEE_RELEASETIME, "acdsee:ReleaseTime");
        _tagNameMap.put(TAG_XMP_ACDSEE_RPP, "acdsee:RPP");
        _tagNameMap.put(TAG_XMP_ACDSEE_SNAPSHOTS, "acdsee:Snapshots");
        _tagNameMap.put(TAG_XMP_ACDSEE_TAGGED, "acdsee:Tagged");
        addXmpTags(_tagNameMap);
    }

    @Override
    @NotNull
    public String getName()
    {
        return "XMP:ACDSEE";
    }

    @Override
    @NotNull
    protected HashMap<Integer, String> getTagNameMap()
    {
        return _tagNameMap;
    }
}
