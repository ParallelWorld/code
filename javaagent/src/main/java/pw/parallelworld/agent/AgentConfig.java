package pw.parallelworld.agent;

import org.apache.log4j.PropertyConfigurator;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Collections;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

public class AgentConfig {

    public static Properties props = new Properties();
    public static Set<String> classPaths = new HashSet<String>(16);
    public static Set<String> includePatterns = new HashSet<String>(16);
    public static Set<String> excludePatterns = new HashSet<String>(16);

    public static void init() {
        try {
            loadProps();
            initLog();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void loadProps() throws Exception {

        String file = "agent.properties";
        InputStream is = new FileInputStream(file);
        props.load(is);
        String classpath = props.getProperty("classpath");
        if (classpath != null && !classpath.isEmpty()) {
            Collections.addAll(classPaths, classpath.split(","));
        }
        String includeClasses = props.getProperty("include_class");
        if (includeClasses != null && !includeClasses.isEmpty()) {
            Collections.addAll(includePatterns, includeClasses.split(","));
        }
        String excludeClasses = props.getProperty("exclude_class");
        if (excludeClasses != null && !excludeClasses.isEmpty()) {
            Collections.addAll(excludePatterns, excludeClasses.split(","));
        }
    }

    private static void initLog() throws Exception {

        String log_path = props.getProperty("log_path");
        File file = new File(log_path);
        if (!file.exists()) {
            file.mkdirs();
        }
        Properties log4j_properties = new Properties();
        log4j_properties.setProperty("log4j.logger.agentlog", "INFO,agentlog");
        log4j_properties.setProperty("log4j.additivity.agentlog", "false");
        log4j_properties.setProperty("log4j.appender.agentlog", "org.apache.log4j.DailyRollingFileAppender");
        log4j_properties.setProperty("log4j.appender.agentlog.DatePattern", "'.'yyyy-MM-dd'.log'");
        log4j_properties.setProperty("log4j.appender.agentlog.MaxBackupIndex", "1");
        log4j_properties.setProperty("log4j.appender.agentlog.layout", "org.apache.log4j.PatternLayout");
        log4j_properties.setProperty("log4j.appender.agentlog.layout.ConversionPattern", "%m%n");
        log4j_properties.setProperty("log4j.appender.agentlog.file", log_path + File.separator + "agentlog.log");
        log4j_properties.setProperty("log4j.appender.agentlog.encoding", "UTF-8");
        log4j_properties.setProperty("log4j.appender.agentlog.BufferedIO", "true");
        log4j_properties.setProperty("log4j.appender.agentlog.BufferSize", "8192");
        PropertyConfigurator.configure(log4j_properties);
    }
}
