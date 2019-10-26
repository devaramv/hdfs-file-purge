package com.hadoop.tools.filePurge;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import static org.junit.Assert.*;

public class ReadConfigurationsTest {

    Logger logger = LoggerFactory.getLogger(ReadConfigurationsTest.class);


    @Test
    public void readPaths() throws Exception {
        String fileName = "config.properties";
        String path = ReadConfigurationsTest.class.getClassLoader().getResource(fileName).getPath();
        System.out.println(path);

        JsonNode expectedOP = null;
        ObjectMapper objectMapper = new ObjectMapper();
        try (InputStream inputStream = new FileInputStream(new File(path))) {
            expectedOP = objectMapper.readTree(inputStream);
            inputStream.close();

        } catch (Exception e) {
            logger.error("File not found " + e);
        }
        JsonNode actualOP = new ReadConfigurations().readPaths(path.toString());
        assertEquals(expectedOP, actualOP);



    }

}