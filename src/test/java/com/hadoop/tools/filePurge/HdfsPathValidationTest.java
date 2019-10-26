package com.hadoop.tools.filePurge;

import org.junit.Test;

import static org.junit.Assert.*;

public class HdfsPathValidationTest {

    @Test
    public void isPathValid() {

        String validPathQA = "/project/dsc/qa/landing_zone/60032_atttool/rtsa/appEW";
        String validPathProd ="/project/dsc/prod/landing_zone/60032_atttool/rtsa/appEW";
        String validPathDev = "/project/dsc/dev/landing_zone/60032_atttool/rtsa/appEW";
        String invalidPath1 = "/project/dsc/dev/landing_zone/60032_atttool/rtsa/";
        String invalidPath2 = "project/dsc/dev/landing_zone/60032_atttool/rtsa/";
        String invalidPath3 = "/project/dsc/dev/landing_zone/";
        String invalidPath4 = "/project/dsc/dev/";
        String invalidPath5 = "/project/dsc/";
        String invalidPath6 = "/project/";
        String invalidPath7 =  "/project/dsc/dev/landing_zone/rtsa/60032_atttool/appEW";

        assertTrue(HdfsPathValidation.isPathValid(validPathQA));
        assertTrue(HdfsPathValidation.isPathValid(validPathProd));
        assertTrue(HdfsPathValidation.isPathValid(validPathDev));
        assertFalse(HdfsPathValidation.isPathValid(invalidPath1));
        assertFalse(HdfsPathValidation.isPathValid(invalidPath2));
        assertFalse(HdfsPathValidation.isPathValid(invalidPath3));
        assertFalse(HdfsPathValidation.isPathValid(invalidPath4));
        assertFalse(HdfsPathValidation.isPathValid(invalidPath5));
        assertFalse(HdfsPathValidation.isPathValid(invalidPath6));
        assertFalse(HdfsPathValidation.isPathValid(invalidPath7));

    }


}