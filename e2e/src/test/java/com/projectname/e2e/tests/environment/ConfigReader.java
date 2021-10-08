package com.projectname.e2e.tests.environment;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class ConfigReader {

    private static String defaultFileName = "src/test/resources/env-e2e.properties";
    private static Map<String, Properties> config = new HashMap<>();

    public static void init(String filename) {
        try (InputStream input = new FileInputStream(filename)){
            Properties prop  = new Properties();
            prop.load(input);
            config.put(filename, prop);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getValue(String key) {
        return getValue(key, defaultFileName);
    }

    protected static String getValue(String key, String fileName) {
        if (config == null || !config.containsKey(fileName)) {
            init(fileName);
        }

        String value = config.get(fileName).getProperty(key);
        if (value != null) {
            return value;
        }

        return System.getenv(key);
    }
}

