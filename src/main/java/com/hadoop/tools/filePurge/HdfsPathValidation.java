package com.hadoop.tools.filePurge;

import com.hadoop.tools.properties.PurgeProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HdfsPathValidation {
    private static final Logger logger = LoggerFactory.getLogger(HdfsPathValidation.class);

    /*

    isPathValid(String path) will return true if the input path is valid

    validPath Examples:
            /project/dsc/qa/landing_zone/10879_gep/rtsa/appEW
            /project/dsc/qa/landing_zone/10879_gep/rtsa/database_rtsa_lz_db/
            /project/dsc/dev/landing_zone/10879_gep/rtsa/database_rtsa_lz1_db/
            /project/dsc/prod/landing_zone/10879_gep/rtsa/database_rtsa_lz1_db/
            /project/dsc/dev/transformation_zone/10879_gep/rtsa/database_rtsa_lz1_db/

    InvalidPath Examples:

            project/dsc/qa/landing_zone/10879_gep/rtsa/
            /project/dsc/qa/landing_zone/10879_gep/rtsa/
            /project/dsc/qa/landing_zone/db
            /project/dsc/qa/landing_zone/rtsa/db
            /project/dsc/qa/
            /project/

            */

    public static boolean isPathValid(String path) {
        boolean pathValid = false;
        if (rtsaPathValidityCheck(path)) {
            pathValid = true;
        }
        return pathValid;
    }


    private static boolean rtsaPathValidityCheck(String path) {

        String[] pathSplit = path.split("/");
        boolean isConditionsPassed = false;
        if (pathSplit[0].length() == 0
                && pathSplit.length > 7
                && (pathSplit[6].toLowerCase().equals(PurgeProperties.getHdfsPathVariableDataproducer()))) {

            if (pathSplit[1].toLowerCase().equals(PurgeProperties.getHdfsPathVariableProject())
                    && pathSplit[2].toLowerCase().equals(PurgeProperties.getHdfsPathVariableOrg())
                    && (pathSplit[3].toLowerCase().equals(PurgeProperties.getHdfsPathVariableEnvironmentQa())
                    || pathSplit[3].toLowerCase().equals(PurgeProperties.getHdfsPathVariableEnvironmentDev())
                    || pathSplit[3].toLowerCase().equals(PurgeProperties.getHdfsPathVariableEnvironmentProd()))
                    && (pathSplit[4].toLowerCase().equals(PurgeProperties.getHdfsPathVariableLandingZone())
                    || pathSplit[4].toLowerCase().equals(PurgeProperties.getHdfsPathVariableTransformationZone()))) {

                isConditionsPassed = true;
            } else {
                isConditionsPassed = false;
            }

        } else {
            isConditionsPassed = false;
        }

        return isConditionsPassed;
    }


    public static void printErrorMessage() {

        logger.error("    Please check input path\n " + "\n" +
                "        ValidPath Examples:\n" +
                "            /project/dsc/qa/landing_zone/10879_gep/rtsa/appEW/\n"+
                "            /project/dsc/qa/landing_zone/10879_gep/rtsa/database_rtsa_lz_db/appEW\n" +
                "            /project/dsc/qa/landing_zone/10879_gep/rtsa/database_rtsa_lz_db/appEW\n" +
                "            /project/dsc/dev/transformation_zone/10879_gep/rtsa/database_rtsa_lz1_db/histApp\n" +
                "\n" +
                "        InvalidPath Examples:\n" +
                "            /project/dsc/qa/landing_zone/10879_gep/rtsa/\n" +
                "             /project/dsc/qa/landing_zone/10879_gep/\n" +
                "            /dsc/qa/landing_zone/10879_gep/rtsa/\n" +
                "            /project/dsc/landing_zo/10879_gep/rtsa/\n" +
                "             project/dsc/qa/landing_zone/10879_gep/rtsa/\n" +
                "            /project/dsc/a/transformation_zone/10879_gep/rtsa/\n");
    }

}
