package com.hadoop.tools.filePurge;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

public class ReadConfigurations {


    private static final Logger logger = LoggerFactory.getLogger(ReadConfigurations.class);

     /*
      readPaths(String configfilepath) will read the input configuration file
      which has a json then return a jsonNode
     */

    public JsonNode readPaths(String configfilepath) throws IOException {
        logger.info("Input config file path is " + configfilepath + "\n");
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = null;

        try (InputStream inputStream = new FileInputStream(new File(configfilepath))) {

            logger.info("Reading json configuration file " + configfilepath);
            jsonNode = objectMapper.readTree(inputStream);
            logger.info(" Json input is " + jsonNode);
            logger.info("Closing inputstream");
            inputStream.close();
            return jsonNode;

        } catch (FileNotFoundException e) {
            logger.error("Configuration file does not exist ", e);
            java.lang.System.exit(1);

        }

        return jsonNode;
    }
}
