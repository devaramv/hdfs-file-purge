package com.hadoop.tools.logging;

import com.hadoop.tools.properties.PurgeProperties;
import org.apache.log4j.*;

import java.io.IOException;
import java.time.LocalDateTime;

public class LoggerConfiguration {
    public LoggerConfiguration() throws IOException {

        // Instanstiating consoleAppender with PatternLayout, and Target set to System.out

        ConsoleAppender consoleAppender = new ConsoleAppender(new PatternLayout(PurgeProperties.getLoggingPattern()),
                PurgeProperties.getConsoleLoggingTarget());
        consoleAppender.setName(PurgeProperties.getAppName()); // Sets the name of the logging tool
        consoleAppender.setThreshold(Level.INFO);
        consoleAppender.activateOptions();
        Logger.getRootLogger().addAppender(consoleAppender);


        //File Appender

        FileAppender fileAppender = new FileAppender();

        fileAppender.setFile(PurgeProperties.getLogPath() + LocalDateTime.now() + "_" + PurgeProperties.getAppName()
                + PurgeProperties.getLogExtension());
        fileAppender.setLayout(new PatternLayout(PurgeProperties.getLoggingPattern()));
        fileAppender.setThreshold(Level.INFO);


//        fileAppender.setThreshold(Level.ALL);

        fileAppender.activateOptions();
        Logger.getRootLogger().addAppender(fileAppender);
    }
}