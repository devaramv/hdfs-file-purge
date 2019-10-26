package com.hadoop.tools.filePurge;

import com.hadoop.tools.properties.PurgeProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class RunHdfsFileRemover {
    private static final Logger logger = LoggerFactory.getLogger(RunHdfsFileRemover.class);

    public static void main(String[] args) throws IOException {
        if (args.length != 1) {
            logger.info(PurgeProperties.getAppUsage());
        }
        String configfilepath = args[0];
        new HdfsFileRemover(configfilepath).run();
    }
}
