package com.drew.metadata.id3;

import com.drew.lang.annotations.NotNull;
import com.drew.metadata.Directory;

import java.util.HashMap;

public class Id3Directory extends Directory
{
    public static final int TAG_UNSYNCHRONISATION = "UNSYNCHRONISATION".hashCode();

    // Version 3
    public static final int TAG_AENC = "AENC".hashCode();
    public static final int TAG_APIC = "APIC".hashCode();
    public static final int TAG_COMM = "COMM".hashCode();
    public static final int TAG_COMR = "COMR".hashCode();
    public static final int TAG_ENCR = "ENCR".hashCode();
    public static final int TAG_EQUA = "EQUA".hashCode();
    public static final int TAG_ETCO = "ETCO".hashCode();
    public static final int TAG_GEOB = "GEOB".hashCode();
    public static final int TAG_GRID = "GRID".hashCode();
    public static final int TAG_IPLS = "IPLS".hashCode();
    public static final int TAG_LINK = "LINK".hashCode();
    public static final int TAG_MCDI = "MCDI".hashCode();
    public static final int TAG_MLLT = "MLLT".hashCode();
    public static final int TAG_OWNE = "OWNE".hashCode();
    public static final int TAG_PRIV = "PRIV".hashCode();
    public static final int TAG_PCNT = "PCNT".hashCode();
    public static final int TAG_POPM = "POPM".hashCode();
    public static final int TAG_POSS = "POSS".hashCode();
    public static final int TAG_RBUF = "RBUF".hashCode();
    public static final int TAG_RVAD = "RVAD".hashCode();
    public static final int TAG_RVRB = "RVRB".hashCode();
    public static final int TAG_SYLT = "SYLT".hashCode();
    public static final int TAG_SYTC = "SYTC".hashCode();
    public static final int TAG_TALB = "TALB".hashCode();
    public static final int TAG_TBPM = "TBPM".hashCode();
    public static final int TAG_TCOM = "TCOM".hashCode();
    public static final int TAG_TCON = "TCON".hashCode();
    public static final int TAG_TCOP = "TCOP".hashCode();
    public static final int TAG_TDAT = "TDAT".hashCode();
    public static final int TAG_TDLY = "TDLY".hashCode();
    public static final int TAG_TENC = "TENC".hashCode();
    public static final int TAG_TEXT = "TEXT".hashCode();
    public static final int TAG_TFLT = "TFLT".hashCode();
    public static final int TAG_TIME = "TIME".hashCode();
    public static final int TAG_TIT1 = "TIT1".hashCode();
    public static final int TAG_TIT2 = "TIT2".hashCode();
    public static final int TAG_TIT3 = "TIT3".hashCode();
    public static final int TAG_TKEY = "TKEY".hashCode();
    public static final int TAG_TLAN = "TLAN".hashCode();
    public static final int TAG_TLEN = "TLEN".hashCode();
    public static final int TAG_TMED = "TMED".hashCode();
    public static final int TAG_TOAL = "TOAL".hashCode();
    public static final int TAG_TOFN = "TOFN".hashCode();
    public static final int TAG_TOLY = "TOLY".hashCode();
    public static final int TAG_TOPE = "TOPE".hashCode();
    public static final int TAG_TORY = "TORY".hashCode();
    public static final int TAG_TOWN = "TOWN".hashCode();
    public static final int TAG_TPE1 = "TPE1".hashCode();
    public static final int TAG_TPE2 = "TPE2".hashCode();
    public static final int TAG_TPE3 = "TPE3".hashCode();
    public static final int TAG_TPE4 = "TPE4".hashCode();
    public static final int TAG_TPOS = "TPOS".hashCode();
    public static final int TAG_TPUB = "TPUB".hashCode();
    public static final int TAG_TRCK = "TRCK".hashCode();
    public static final int TAG_TRDA = "TRDA".hashCode();
    public static final int TAG_TRSN = "TRSN".hashCode();
    public static final int TAG_TRSO = "TRSO".hashCode();
    public static final int TAG_TSIZ = "TSIZ".hashCode();
    public static final int TAG_TSRC = "TSRC".hashCode();
    public static final int TAG_TSSE = "TSSE".hashCode();
    public static final int TAG_TYER = "TYER".hashCode();
    public static final int TAG_TXXX = "TXXX".hashCode();
    public static final int TAG_UFID = "UFID".hashCode();
    public static final int TAG_USER = "USER".hashCode();
    public static final int TAG_USLT = "USLT".hashCode();
    public static final int TAG_WCOM = "WCOM".hashCode();
    public static final int TAG_WCOP = "WCOP".hashCode();
    public static final int TAG_WOAF = "WOAF".hashCode();
    public static final int TAG_WOAR = "WOAR".hashCode();
    public static final int TAG_WOAS = "WOAS".hashCode();
    public static final int TAG_WORS = "WORS".hashCode();
    public static final int TAG_WPAY = "WPAY".hashCode();
    public static final int TAG_WPUB = "WPUB".hashCode();
    public static final int TAG_WXXX = "WXXX".hashCode();

    @NotNull
    protected static final HashMap<Integer, String> _tagNameMap = new HashMap<Integer, String>();

    static
    {
        _tagNameMap.put(TAG_UNSYNCHRONISATION, "Unsynchronisation");

        // Version 3
        _tagNameMap.put(TAG_AENC, "Audio encryption");
        _tagNameMap.put(TAG_APIC, "Attached picture");
        _tagNameMap.put(TAG_COMM, "Comments");
        _tagNameMap.put(TAG_COMR, "Commercial frame");
        _tagNameMap.put(TAG_ENCR, "Encryption method registration");
        _tagNameMap.put(TAG_EQUA, "Equalization");
        _tagNameMap.put(TAG_ETCO, "Event timing codes");
        _tagNameMap.put(TAG_GEOB, "General encapsulated object");
        _tagNameMap.put(TAG_GRID, "Group identification registration");
        _tagNameMap.put(TAG_IPLS, "Involved people list");
        _tagNameMap.put(TAG_LINK, "Linked information");
        _tagNameMap.put(TAG_MCDI, "Music CD identifier");
        _tagNameMap.put(TAG_MLLT, "MPEG location lookup table");
        _tagNameMap.put(TAG_OWNE, "Ownership frame");
        _tagNameMap.put(TAG_PRIV, "Private frame");
        _tagNameMap.put(TAG_PCNT, "Play counter");
        _tagNameMap.put(TAG_POPM, "Popularimeter");
        _tagNameMap.put(TAG_POSS, "Position synchronisation frame");
        _tagNameMap.put(TAG_RBUF, "Recommended buffer size");
        _tagNameMap.put(TAG_RVAD, "Relative volume adjustment");
        _tagNameMap.put(TAG_RVRB, "Reverb");
        _tagNameMap.put(TAG_SYLT, "Synchronized lyric/text");
        _tagNameMap.put(TAG_SYTC, "Synchronized tempo codes");
        _tagNameMap.put(TAG_TALB, "Album/Movie/Show title");
        _tagNameMap.put(TAG_TBPM, "BPM (beats per minute)");
        _tagNameMap.put(TAG_TCOM, "Composer");
        _tagNameMap.put(TAG_TCON, "Content type");
        _tagNameMap.put(TAG_TCOP, "Copyright message");
        _tagNameMap.put(TAG_TDAT, "Date");
        _tagNameMap.put(TAG_TDLY, "Playlist delay");
        _tagNameMap.put(TAG_TENC, "Encoded by");
        _tagNameMap.put(TAG_TEXT, "Lyricist/Text writer");
        _tagNameMap.put(TAG_TFLT, "File type");
        _tagNameMap.put(TAG_TIME, "Time");
        _tagNameMap.put(TAG_TIT1, "Content group description");
        _tagNameMap.put(TAG_TIT2, "Title/songname/content description");
        _tagNameMap.put(TAG_TIT3, "Subtitle/Description refinement");
        _tagNameMap.put(TAG_TKEY, "Initial key");
        _tagNameMap.put(TAG_TLAN, "Language(s)");
        _tagNameMap.put(TAG_TLEN, "Length");
        _tagNameMap.put(TAG_TMED, "Media type");
        _tagNameMap.put(TAG_TOAL, "Original album/movie/show title");
        _tagNameMap.put(TAG_TOFN, "Original filename");
        _tagNameMap.put(TAG_TOLY, "Original lyricist(s)/text writer(s)");
        _tagNameMap.put(TAG_TOPE, "Original artist(s)/performer(s)");
        _tagNameMap.put(TAG_TORY, "Original release year");
        _tagNameMap.put(TAG_TOWN, "File owner/licensee");
        _tagNameMap.put(TAG_TPE1, "Lead performer(s)/Soloist(s)");
        _tagNameMap.put(TAG_TPE2, "Band/orchestra/accompaniment");
        _tagNameMap.put(TAG_TPE3, "Conductor/performer refinement");
        _tagNameMap.put(TAG_TPE4, "Interpreted, remixed, or otherwise modified by");
        _tagNameMap.put(TAG_TPOS, "Part of a set");
        _tagNameMap.put(TAG_TPUB, "Publisher");
        _tagNameMap.put(TAG_TRCK, "Track number/Position in set");
        _tagNameMap.put(TAG_TRDA, "Recording dates");
        _tagNameMap.put(TAG_TRSN, "Internet radio station name");
        _tagNameMap.put(TAG_TRSO, "Internet radio station owner");
        _tagNameMap.put(TAG_TSIZ, "Size");
        _tagNameMap.put(TAG_TSRC, "ISRC (international standard recording code)");
        _tagNameMap.put(TAG_TSSE, "Software/Hardware and settings used for encoding");
        _tagNameMap.put(TAG_TYER, "Year");
        _tagNameMap.put(TAG_TXXX, "User defined text information frame");
        _tagNameMap.put(TAG_UFID, "Unique file identifier");
        _tagNameMap.put(TAG_USER, "Terms of use");
        _tagNameMap.put(TAG_USLT, "Unsychronized lyric/text transcription");
        _tagNameMap.put(TAG_WCOM, "Commercial information");
        _tagNameMap.put(TAG_WCOP, "Copyright/Legal information");
        _tagNameMap.put(TAG_WOAF, "Official audio file webpage");
        _tagNameMap.put(TAG_WOAR, "Official artist/performer webpage");
        _tagNameMap.put(TAG_WOAS, "Official audio source webpage");
        _tagNameMap.put(TAG_WORS, "Official internet radio station homepage");
        _tagNameMap.put(TAG_WPAY, "Payment");
        _tagNameMap.put(TAG_WPUB, "Publishers official webpage");
        _tagNameMap.put(TAG_WXXX, "User defined URL link frame");
    }

    public Id3Directory()
    {
        this.setDescriptor(new Id3Descriptor(this));
    }

    @Override
    public String getName()
    {
        return "ID3";
    }

    @Override
    protected HashMap<Integer, String> getTagNameMap()
    {
        return _tagNameMap;
    }
}
