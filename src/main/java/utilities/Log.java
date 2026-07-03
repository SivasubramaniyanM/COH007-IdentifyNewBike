package utilities;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.config.Configurator;

import java.io.File;
import java.net.URL;

public class Log {

    private static final Logger logger;

    static {
        // ---------- Step 1: Set log directory ----------
        String projectPath = System.getProperty("user.dir");
        String logDir = projectPath + File.separator + "logs";
        new File(logDir).mkdirs();
        System.setProperty("logDir", logDir);

        // ---------- Step 2: Force load log4j2.xml from classpath ----------
        URL configUrl = Log.class.getClassLoader().getResource("log4j2.xml");
        if (configUrl != null) {
            try {
                Configurator.initialize(null, configUrl.toString());
                System.out.println(">>> Log4j config loaded from : " + configUrl);
            } catch (Exception e) {
                System.out.println(">>> Failed to load Log4j config : " + e.getMessage());
            }
        } else {
            System.out.println(">>> log4j2.xml NOT FOUND in classpath!");
        }

        System.out.println(">>> Log file will be : " + logDir + File.separator + "application.log");

        // ---------- Step 3: Initialize logger AFTER config is loaded ----------
        logger = LogManager.getLogger(Log.class);
    }

    public static void info(String message)  { logger.info(message);  }
    public static void warn(String message)  { logger.warn(message);  }
    public static void error(String message) { logger.error(message); }
    public static void debug(String message) { logger.debug(message); }
    public static void fatal(String message) { logger.fatal(message); }
}