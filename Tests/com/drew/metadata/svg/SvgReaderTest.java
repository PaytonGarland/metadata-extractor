package com.drew.metadata.svg;

import com.drew.lang.StreamReader;
import com.drew.lang.annotations.NotNull;
import com.drew.metadata.Metadata;
import com.drew.tools.FileUtil;
import com.sun.deploy.xml.XMLParser;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;

/**
 * Created by pgarland on 6/16/17.
 */
public class SvgReaderTest
{
    @NotNull
    public static SvgDirectory processBytes(@NotNull String file) throws Exception
    {
        Metadata metadata = new Metadata();
        InputStream stream = new FileInputStream(new File(file));
        try {
            XMLParser xmlParser = new XMLParser(new String(FileUtil.readBytes(file)));
            new SvgReader().extract(xmlParser, metadata);
        } catch (Exception e) {
            stream.close();
            throw e;
        }

        SvgDirectory directory = metadata.getFirstDirectoryOfType(SvgDirectory.class);
        assertNotNull(directory);
        return directory;
    }

    @Test
    public void testSvgAttibutes () throws Exception
    {
        SvgDirectory directory = processBytes("Tests/Data/test.svg");

        assertEquals("210mm", directory.getString(SvgDirectory.TAG_WIDTH));
        assertEquals("297mm", directory.getString(SvgDirectory.TAG_HEIGHT));
        assertEquals("0", directory.getString(SvgDirectory.TAG_VIEWBOX_MIN_WIDTH));
        assertEquals("0", directory.getString(SvgDirectory.TAG_VIEWBOX_MIN_HEIGHT));
        assertEquals("744.09448819", directory.getString(SvgDirectory.TAG_VIEWBOX_WIDTH));
        assertEquals("1052.3622047", directory.getString(SvgDirectory.TAG_VIEWBOX_HEIGHT));
    }

    @Test
    public void testDublinCoreMetadata () throws Exception
    {
        SvgDirectory directory = processBytes("Tests/Data/cube.svg");

        assertEquals("image/svg+xml", directory.getString(SvgDirectory.TAG_FORMAT));
        assertEquals("Cube", directory.getString(SvgDirectory.TAG_TITLE));
        assertEquals("A 3-D Cube", directory.getString(SvgDirectory.TAG_SUBJECT));
        assertEquals("This is a 3 dimensional cube on a white background", directory.getString(SvgDirectory.TAG_DESCRIPTION));
        assertEquals("None", directory.getString(SvgDirectory.TAG_TYPE));
    }

}
