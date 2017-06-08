package com.drew.metadata.xmp;

import com.drew.lang.annotations.NotNull;

import java.util.HashMap;

public class XmpAasDirectory extends XmpDirectoryBase {

    public static final int TAG_XMP_AAS_AAS = "aas:aas".hashCode();
    public static final int TAG_XMP_AAS_ACDSEE = "aas:acdsee".hashCode();
    public static final int TAG_XMP_AAS_ALBUM = "aas:album".hashCode();
    public static final int TAG_XMP_AAS_APPLE_FI = "aas:apple-fi".hashCode();
    public static final int TAG_XMP_AAS_AUX = "aas:aux".hashCode();
    public static final int TAG_XMP_AAS_CC = "aas:cc".hashCode();
    public static final int TAG_XMP_AAS_CELL = "aas:cell".hashCode();
    public static final int TAG_XMP_AAS_CREATORATOM = "aas:creatorAtom".hashCode();
    public static final int TAG_XMP_AAS_CRS = "aas:crs".hashCode();
    public static final int TAG_XMP_AAS_DC = "aas:dc".hashCode();
    public static final int TAG_XMP_AAS_DEX = "aas:dex".hashCode();
    public static final int TAG_XMP_AAS_DICOM = "aas:DICOM".hashCode();
    public static final int TAG_XMP_AAS_DIGIKAM = "aas:digiKam".hashCode();
    public static final int TAG_XMP_AAS_DWC = "aas:dwc".hashCode();
    public static final int TAG_XMP_AAS_EXIF = "aas:exif".hashCode();
    public static final int TAG_XMP_AAS_EXIFEX = "aas:exifEX".hashCode();
    public static final int TAG_XMP_AAS_EXPRESSIONMEDIA = "aas:expressionmedia".hashCode();
    public static final int TAG_XMP_AAS_EXTENSIS = "aas:extensis".hashCode();
    public static final int TAG_XMP_AAS_FPV = "aas:fpv".hashCode();
    public static final int TAG_XMP_AAS_GAUDIO = "aas:GAudio".hashCode();
    public static final int TAG_XMP_AAS_GETTY = "aas:getty".hashCode();
    public static final int TAG_XMP_AAS_GIMAGE = "aas:GImage".hashCode();
    public static final int TAG_XMP_AAS_GPANO = "aas:GPano".hashCode();
    public static final int TAG_XMP_AAS_GSPHERICAL = "aas:GSpherical".hashCode();
    public static final int TAG_XMP_AAS_ICS = "aas:ics".hashCode();
    public static final int TAG_XMP_AAS_IPTCCORE = "aas:iptcCore".hashCode();
    public static final int TAG_XMP_AAS_IPTCEXT = "aas:iptcExt".hashCode();
    public static final int TAG_XMP_AAS_LR = "aas:lr".hashCode();
    public static final int TAG_XMP_AAS_MEDIAPRO = "aas:mediapro".hashCode();
    public static final int TAG_XMP_AAS_MICROSOFT = "aas:microsoft".hashCode();
    public static final int TAG_XMP_AAS_MP = "aas:MP".hashCode();
    public static final int TAG_XMP_AAS_MP1 = "aas:MP1".hashCode();
    public static final int TAG_XMP_AAS_MWG_COLL = "aas:mwg-coll".hashCode();
    public static final int TAG_XMP_AAS_MWG_KW = "aas:mwg-kw".hashCode();
    public static final int TAG_XMP_AAS_MWG_RS = "aas:mwg-rs".hashCode();
    public static final int TAG_XMP_AAS_PDF = "aas:pdf".hashCode();
    public static final int TAG_XMP_AAS_PDFX = "aas:pdfx".hashCode();
    public static final int TAG_XMP_AAS_PHOTOMECH = "aas:photomech".hashCode();
    public static final int TAG_XMP_AAS_PHOTOSHOP = "aas:photoshop".hashCode();
    public static final int TAG_XMP_AAS_PIXELLIVE = "aas:PixelLive".hashCode();
    public static final int TAG_XMP_AAS_PLUS = "aas:plus".hashCode();
    public static final int TAG_XMP_AAS_PMI = "aas:pmi".hashCode();
    public static final int TAG_XMP_AAS_PRISM = "aas:prism".hashCode();
    public static final int TAG_XMP_AAS_PRL = "aas:prl".hashCode();
    public static final int TAG_XMP_AAS_PRM = "aas:prm".hashCode();
    public static final int TAG_XMP_AAS_PUR = "aas:pur".hashCode();
    public static final int TAG_XMP_AAS_RDF = "aas:rdf".hashCode();
    public static final int TAG_XMP_AAS_SWF = "aas:swf".hashCode();
    public static final int TAG_XMP_AAS_TIFF = "aas:tiff".hashCode();
    public static final int TAG_XMP_AAS_X = "aas:x".hashCode();
    public static final int TAG_XMP_AAS_XMP = "aas:xmp".hashCode();
    public static final int TAG_XMP_AAS_XMPBJ = "aas:xmpBJ".hashCode();
    public static final int TAG_XMP_AAS_XMPDM = "aas:xmpDM".hashCode();
    public static final int TAG_XMP_AAS_XMPMM = "aas:xmpMM".hashCode();
    public static final int TAG_XMP_AAS_XMPNOTE = "aas:xmpNote".hashCode();
    public static final int TAG_XMP_AAS_XMPPLUS = "aas:xmpPLUS".hashCode();
    public static final int TAG_XMP_AAS_XMPRIGHTS = "aas:xmpRights".hashCode();
    public static final int TAG_XMP_AAS_XMPTPG = "aas:xmpTPg".hashCode();

    @NotNull
    protected static final HashMap<Integer, String> _tagNameMap = new HashMap<Integer, String>();

    static
    {
        _tagNameMap.put(TAG_XMP_AAS_AAS, "aas:aas");
        _tagNameMap.put(TAG_XMP_AAS_ACDSEE, "aas:acdsee");
        _tagNameMap.put(TAG_XMP_AAS_ALBUM, "aas:album");
        _tagNameMap.put(TAG_XMP_AAS_APPLE_FI, "aas:apple-fi");
        _tagNameMap.put(TAG_XMP_AAS_AUX, "aas:aux");
        _tagNameMap.put(TAG_XMP_AAS_CC, "aas:cc");
        _tagNameMap.put(TAG_XMP_AAS_CELL, "aas:cell");
        _tagNameMap.put(TAG_XMP_AAS_CREATORATOM, "aas:creatorAtom");
        _tagNameMap.put(TAG_XMP_AAS_CRS, "aas:crs");
        _tagNameMap.put(TAG_XMP_AAS_DC, "aas:dc");
        _tagNameMap.put(TAG_XMP_AAS_DEX, "aas:dex");
        _tagNameMap.put(TAG_XMP_AAS_DICOM, "aas:DICOM");
        _tagNameMap.put(TAG_XMP_AAS_DIGIKAM, "aas:digiKam");
        _tagNameMap.put(TAG_XMP_AAS_DWC, "aas:dwc");
        _tagNameMap.put(TAG_XMP_AAS_EXIF, "aas:exif");
        _tagNameMap.put(TAG_XMP_AAS_EXIFEX, "aas:exifEX");
        _tagNameMap.put(TAG_XMP_AAS_EXPRESSIONMEDIA, "aas:expressionmedia");
        _tagNameMap.put(TAG_XMP_AAS_EXTENSIS, "aas:extensis");
        _tagNameMap.put(TAG_XMP_AAS_FPV, "aas:fpv");
        _tagNameMap.put(TAG_XMP_AAS_GAUDIO, "aas:GAudio");
        _tagNameMap.put(TAG_XMP_AAS_GETTY, "aas:getty");
        _tagNameMap.put(TAG_XMP_AAS_GIMAGE, "aas:GImage");
        _tagNameMap.put(TAG_XMP_AAS_GPANO, "aas:GPano");
        _tagNameMap.put(TAG_XMP_AAS_GSPHERICAL, "aas:GSpherical");
        _tagNameMap.put(TAG_XMP_AAS_ICS, "aas:ics");
        _tagNameMap.put(TAG_XMP_AAS_IPTCCORE, "aas:iptcCore");
        _tagNameMap.put(TAG_XMP_AAS_IPTCEXT, "aas:iptcExt");
        _tagNameMap.put(TAG_XMP_AAS_LR, "aas:lr");
        _tagNameMap.put(TAG_XMP_AAS_MEDIAPRO, "aas:mediapro");
        _tagNameMap.put(TAG_XMP_AAS_MICROSOFT, "aas:microsoft");
        _tagNameMap.put(TAG_XMP_AAS_MP, "aas:MP");
        _tagNameMap.put(TAG_XMP_AAS_MP1, "aas:MP1");
        _tagNameMap.put(TAG_XMP_AAS_MWG_COLL, "aas:mwg-coll");
        _tagNameMap.put(TAG_XMP_AAS_MWG_KW, "aas:mwg-kw");
        _tagNameMap.put(TAG_XMP_AAS_MWG_RS, "aas:mwg-rs");
        _tagNameMap.put(TAG_XMP_AAS_PDF, "aas:pdf");
        _tagNameMap.put(TAG_XMP_AAS_PDFX, "aas:pdfx");
        _tagNameMap.put(TAG_XMP_AAS_PHOTOMECH, "aas:photomech");
        _tagNameMap.put(TAG_XMP_AAS_PHOTOSHOP, "aas:photoshop");
        _tagNameMap.put(TAG_XMP_AAS_PIXELLIVE, "aas:PixelLive");
        _tagNameMap.put(TAG_XMP_AAS_PLUS, "aas:plus");
        _tagNameMap.put(TAG_XMP_AAS_PMI, "aas:pmi");
        _tagNameMap.put(TAG_XMP_AAS_PRISM, "aas:prism");
        _tagNameMap.put(TAG_XMP_AAS_PRL, "aas:prl");
        _tagNameMap.put(TAG_XMP_AAS_PRM, "aas:prm");
        _tagNameMap.put(TAG_XMP_AAS_PUR, "aas:pur");
        _tagNameMap.put(TAG_XMP_AAS_RDF, "aas:rdf");
        _tagNameMap.put(TAG_XMP_AAS_SWF, "aas:swf");
        _tagNameMap.put(TAG_XMP_AAS_TIFF, "aas:tiff");
        _tagNameMap.put(TAG_XMP_AAS_X, "aas:x");
        _tagNameMap.put(TAG_XMP_AAS_XMP, "aas:xmp");
        _tagNameMap.put(TAG_XMP_AAS_XMPBJ, "aas:xmpBJ");
        _tagNameMap.put(TAG_XMP_AAS_XMPDM, "aas:xmpDM");
        _tagNameMap.put(TAG_XMP_AAS_XMPMM, "aas:xmpMM");
        _tagNameMap.put(TAG_XMP_AAS_XMPNOTE, "aas:xmpNote");
        _tagNameMap.put(TAG_XMP_AAS_XMPPLUS, "aas:xmpPLUS");
        _tagNameMap.put(TAG_XMP_AAS_XMPRIGHTS, "aas:xmpRights");
        _tagNameMap.put(TAG_XMP_AAS_XMPTPG, "aas:xmpTPg");
        addXmpTags(_tagNameMap);
    }

    @Override
    @NotNull
    public String getName()
    {
        return "XMP:AAS";
    }

    @Override
    @NotNull
    protected HashMap<Integer, String> getTagNameMap()
    {
        return _tagNameMap;
    }
}
