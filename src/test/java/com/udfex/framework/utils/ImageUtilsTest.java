package com.udfex.framework.utils;

import org.apache.commons.io.FileUtils;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

public class ImageUtilsTest {

    @Test
    public void testScaleImage() throws Exception {
        InputStream inputStreamFront = new FileInputStream(new File("D://front.JPG"));
        InputStream inputStreamBack = new FileInputStream(new File("D://back.JPG"));
        InputStream resultFront = ImageUtils.scaleImage(inputStreamFront);
        InputStream resultBack = ImageUtils.scaleImage(inputStreamBack);
        FileUtils.copyInputStreamToFile(resultFront, new File("D://result.JPG"));
        FileUtils.copyInputStreamToFile(resultBack, new File("D://result2.JPG"));
    }

    @Test
    public void testScaleImageWithParams() throws Exception {

    }

    @Test
    public void testScaleImageWithParams1() throws Exception {

    }
}