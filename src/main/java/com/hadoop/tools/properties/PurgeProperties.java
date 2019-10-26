package com.hadoop.tools.properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.util.Properties;

public class PurgeProperties {

    private static final Logger logger = LoggerFactory.getLogger(PurgeProperties.class);
    private static String PATH = "/hdfs-file-purge.properties";
    private static String LOGGING_PATTERN;
    private static String CONSOLE_LOGGING_TARGET;
    private static String LOG_PATH;
    private static String APP_NAME;
    private static String LOG_EXTENSION;
    private static String JSONCONFIG_KRB_PRINCIPAL;
    private static String JSONCONFIG_KRB_KEYTAB_PATH;
    private static String JSONCONFIG_HDFSPATHARRAY;
    private static String JSONCONFIG_HDFSPATHARRAY_VARIABLE;
    private static String JSONCONFIG_HDFSPATHARRAY_RETENTION;
    private static String APP_USAGE;
    private static String HDFS_PATH_VARIABLE_PROJECT;
    private static String HDFS_PATH_VARIABLE_ORG;
    private static String HDFS_PATH_VARIABLE_ENVIRONMENT_QA;
    private static String HDFS_PATH_VARIABLE_ENVIRONMENT_DEV;
    private static String HDFS_PATH_VARIABLE_ENVIRONMENT_PROD;
    private static String HDFS_PATH_VARIABLE_LANDING_ZONE;
    private static String HDFS_PATH_VARIABLE_TRANSFORMATION_ZONE;
    private static String HDFS_PATH_VARIABLE_DATAPRODUCER;


    static {
        try (InputStream stream = PurgeProperties.class.getResourceAsStream(PATH)) {
            Properties properties = new Properties();
            properties.load(stream);

            // Loading logging constants from the properties file

            LOGGING_PATTERN = properties.getProperty("LOGGING_PATTERN").trim();
            CONSOLE_LOGGING_TARGET = properties.getProperty("CONSOLE_LOGGING_TARGET").trim();
            LOG_PATH = properties.getProperty("LOG_PATH").trim();
            APP_NAME = properties.getProperty("APP_NAME").trim();
            LOG_EXTENSION = properties.getProperty("LOG_EXTENSION").trim();


            // Loading constants used in json configuration file from properties file

            JSONCONFIG_KRB_PRINCIPAL = properties.getProperty("JSONCONFIG_KRB_PRINCIPAL");
            JSONCONFIG_KRB_KEYTAB_PATH = properties.getProperty("JSONCONFIG_KRB_KEYTAB_PATH");
            JSONCONFIG_HDFSPATHARRAY = properties.getProperty("JSONCONFIG_HDFSPATHARRAY");
            JSONCONFIG_HDFSPATHARRAY_VARIABLE = properties.getProperty("JSONCONFIG_HDFSPATHARRAY_VARIABLE");
            JSONCONFIG_HDFSPATHARRAY_RETENTION = properties.getProperty("JSONCONFIG_HDFSPATHARRAY_RETENTION");


            // Reading Usage

            APP_USAGE = properties.getProperty("APP_USAGE");


            // Loading HdfsPathConstraint Constants

            HDFS_PATH_VARIABLE_PROJECT = properties.getProperty("HDFS_PATH_VARIABLE_PROJECT");
            HDFS_PATH_VARIABLE_ORG = properties.getProperty("HDFS_PATH_VARIABLE_ORG");
            HDFS_PATH_VARIABLE_ENVIRONMENT_QA = properties.getProperty("HDFS_PATH_VARIABLE_ENVIRONMENT_QA");
            HDFS_PATH_VARIABLE_ENVIRONMENT_DEV = properties.getProperty("HDFS_PATH_VARIABLE_ENVIRONMENT_DEV");
            HDFS_PATH_VARIABLE_ENVIRONMENT_PROD = properties.getProperty("HDFS_PATH_VARIABLE_ENVIRONMENT_PROD");
            HDFS_PATH_VARIABLE_LANDING_ZONE = properties.getProperty("HDFS_PATH_VARIABLE_LANDING_ZONE");
            HDFS_PATH_VARIABLE_TRANSFORMATION_ZONE = properties.getProperty("HDFS_PATH_VARIABLE_TRANSFORMATION_ZONE");
            HDFS_PATH_VARIABLE_DATAPRODUCER = properties.getProperty("HDFS_PATH_VARIABLE_DATAPRODUCER");


        } catch (Exception e) {
            logger.error("The file " + PATH + " is not found");
        }
    }

    public static String getLoggingPattern() {
        return LOGGING_PATTERN;
    }

    public static String getConsoleLoggingTarget() {
        return CONSOLE_LOGGING_TARGET;
    }

    public static String getLogPath() {
        return LOG_PATH;
    }

    public static String getAppName() {
        return APP_NAME;
    }

    public static String getLogExtension() {
        return LOG_EXTENSION;
    }

    public static String getJsonconfigKrbPrincipal() {
        return JSONCONFIG_KRB_PRINCIPAL;
    }

    public static String getJsonconfigKrbKeytabPath() {
        return JSONCONFIG_KRB_KEYTAB_PATH;
    }

    public static String getJsonconfigHdfspatharray() {
        return JSONCONFIG_HDFSPATHARRAY;
    }

    public static String getJsonconfigHdfspatharrayVariable() {
        return JSONCONFIG_HDFSPATHARRAY_VARIABLE;
    }

    public static String getJsonconfigHdfspatharrayRetention() {
        return JSONCONFIG_HDFSPATHARRAY_RETENTION;
    }

    public static String getAppUsage() {
        return APP_USAGE;
    }

    public static String getHdfsPathVariableProject() {
        return HDFS_PATH_VARIABLE_PROJECT;
    }

    public static String getHdfsPathVariableOrg() {
        return HDFS_PATH_VARIABLE_ORG;
    }

    public static String getHdfsPathVariableEnvironmentQa() {
        return HDFS_PATH_VARIABLE_ENVIRONMENT_QA;
    }

    public static String getHdfsPathVariableEnvironmentDev() {
        return HDFS_PATH_VARIABLE_ENVIRONMENT_DEV;
    }

    public static String getHdfsPathVariableEnvironmentProd() {
        return HDFS_PATH_VARIABLE_ENVIRONMENT_PROD;
    }

    public static String getHdfsPathVariableLandingZone() {
        return HDFS_PATH_VARIABLE_LANDING_ZONE;
    }

    public static String getHdfsPathVariableTransformationZone() {
        return HDFS_PATH_VARIABLE_TRANSFORMATION_ZONE;
    }

    public static String getHdfsPathVariableDataproducer() {
        return HDFS_PATH_VARIABLE_DATAPRODUCER;
    }

}
