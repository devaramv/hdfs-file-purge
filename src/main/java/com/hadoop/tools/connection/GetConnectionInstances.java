package com.hadoop.tools.connection;

import com.fasterxml.jackson.databind.JsonNode;
import com.hadoop.tools.properties.PurgeProperties;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class GetConnectionInstances {

    private static final Logger logger = LoggerFactory.getLogger(GetConnectionInstances.class);
    private static boolean isConnectionSuccessful = false;
    private static String kerberosPrincipal;
    private static String kerberosKeyTabPath;

    public static String getKerberosPrincipal() {
        return kerberosPrincipal;
    }

    public static String getKerberosKeyTabPath() {
        return kerberosKeyTabPath;
    }

    private static Configuration getHadoopConnectionInstance() throws IOException {

        final Configuration conf = HadoopConnection.connectToHadoop(kerberosPrincipal, kerberosKeyTabPath);
        if (HadoopConnection.isIsConnectionEstablished()) {
            logger.info("Connection to hadoop is established ");
            isConnectionSuccessful = true;
        }

        return conf;
    }

    public static FileSystem getFileSystemInstance() throws IOException {
        logger.debug("Fetching FileSystem Instance to read Hdfs file paths");
        return FileSystem.get(getHadoopConnectionInstance());
    }

    public static void setKeytabAndKeyTabPath(JsonNode jsonNode) {

        kerberosPrincipal = jsonNode.findPath(PurgeProperties.getJsonconfigKrbPrincipal()).asText();
        kerberosKeyTabPath = jsonNode.findPath(PurgeProperties.getJsonconfigKrbKeytabPath()).asText();

        if (kerberosPrincipal.equals("null") || kerberosPrincipal.equals("")) {
            kerberosPrincipal = null;
            kerberosKeyTabPath = null;
        }
    }

    public static boolean isIsConnectionSuccessful() {
        return isConnectionSuccessful;
    }
}
