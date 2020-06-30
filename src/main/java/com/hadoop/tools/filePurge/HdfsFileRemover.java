package com.hadoop.tools.filePurge;

import com.fasterxml.jackson.databind.JsonNode;
import com.hadoop.tools.connection.GetConnectionInstances;
import com.hadoop.tools.logging.LoggerConfiguration;
import com.hadoop.tools.properties.PurgeProperties;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.sql.Timestamp;

public class HdfsFileRemover {


    private JsonNode jsonNode;
    private HdfsPathValidation hdfsPathValidation;

    private static final Logger logger = LoggerFactory.getLogger(HdfsFileRemover.class);

    public HdfsFileRemover(String configFilePath) throws IOException {
        new LoggerConfiguration();
        jsonNode = new ReadConfigurations().readPaths(configFilePath);
    }

    public JsonNode getJsonNode() {
        return jsonNode;
    }


    public void run() throws IOException {

        deleteFileFromMultiplePaths();

    }


    /*
       deleteFileFromMultiplePaths() executes deleteFilesFromPath(FileSystem fs,
       String hdfspath, float retention) on all the hdfs paths provided in the
       input configuration file

     */

    private void deleteFileFromMultiplePaths() throws IOException {

        long startTimeinMs = System.currentTimeMillis();
        logger.info("Purge process started at " + new Timestamp(System.currentTimeMillis()) + "\n");

        if (jsonNode != null) {

            GetConnectionInstances.setKeytabAndKeyTabPath(getJsonNode());
            JsonNode jsonArray = jsonNode.findPath(PurgeProperties.getJsonconfigHdfspatharray());
            logger.info("Input Json Array of paths and retentions is " + jsonArray + "\n");
            FileSystem fs = GetConnectionInstances.getFileSystemInstance();

            if (GetConnectionInstances.isIsConnectionSuccessful()) {
                logger.info("Connection Successful" + "\n");
                for (int i = 0; i < jsonArray.size(); i++) {

                    logger.info("Purge Process for the " + jsonArray.get(i) + " started at " + new Timestamp(System.currentTimeMillis()) + "\n");
                    String path = jsonArray.get(i).findPath(PurgeProperties.getJsonconfigHdfspatharrayVariable()).asText();
                    Float retention = Float.parseFloat(jsonArray.get(i).findPath(PurgeProperties.getJsonconfigHdfspatharrayRetention()).asText());                  
                    deleteFilesFromPath(fs,path,retention);

                }

                long finalTimeinMs = System.currentTimeMillis();
                logger.info("purge process ended on " + new Timestamp(System.currentTimeMillis()));
                logger.info(" Total time to execute the process in seconds " + (finalTimeinMs - startTimeinMs) / 1000);

            } else {
                logger.info("Please check the Hadoop Connection");
            }

        } else {
            logger.info("Input Json is empty: There is nothing to parse");
        }


    }

    /*
      deleteFilesFromPath(FileSystem fs, String hdfspath, float retention)
      recursively deletes the files present in given hdfspath that has reached
      retention.

     */
    private void deleteFilesFromPath(FileSystem fs, String hdfspath, float retention) {

        int fileCount = 0; //fileCount is the counter to keep track of the total number of files deleted in each given directory
        long purgeTime; //purgeTime indicates the total life time of the file from the current time stamp.
        float retentionInMs; // retentioninMs store the asked retention time in milliseconds.

        try {


            if (fs.exists(new Path(hdfspath))) {

                /* FileStatus array stores the statuses of the files/directories
                   in the given path if the path is a directory */

                FileStatus[] status = fs.listStatus(new Path(hdfspath));
                retentionInMs = 24 * 60 * 60 * 1000 * retention;

                for (FileStatus file : status) {
                    if (file.isDirectory()) {
                        System.out.println(file.getPath());
                        deleteFilesFromPath(fs, file.getPath().toString(), retention);

                    } else {
                        purgeTime = System.currentTimeMillis() - file.getModificationTime();
                        if (purgeTime > retentionInMs) {
                            fileCount = fileCount + 1;
                            // logger.info("Deleting the file at " + file.getPath() + " on " + new Timestamp(System.currentTimeMillis()));
                            fs.delete(file.getPath(), false);

                        }
                    }

                }
                logger.info(" The total number of files deleted at " + hdfspath + " are " + fileCount + "\n");

            } else {
                logger.error("Input HDFS path " + hdfspath + " does not exits exist." + "\n");


            }

        } catch (IOException e) {
            logger.info("IO Exception occured " + e);
        }
    }

}
