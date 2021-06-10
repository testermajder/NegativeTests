package com.example.test.api.environment;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {

    private static String defaultFileName = "env.properties";
    private static Properties config = null;

    public static void init(String filename) {
        try (InputStream input = new FileInputStream(filename)){
            Properties prop  = new Properties();
            prop.load(input);
            config = prop;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected static String getValue(String key) {
        return getValue(key, defaultFileName);
    }

    protected static String getValue(String key, String fileName) {
        if (config == null) {
            init(fileName);
        } else if (config != null && defaultFileName != fileName) {
            init(fileName);
        }

        String value = config.getProperty(key);
        if (value != null) {
            return value;
        }

        return System.getenv(key);
    }
}

