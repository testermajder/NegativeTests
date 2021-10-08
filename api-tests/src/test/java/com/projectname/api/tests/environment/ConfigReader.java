package com.projectname.api.tests.environment;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {

    private static String defaultFileName = "src/test/resources/env-api.properties";
    private static final String TEST = "test";
    private static final String STAGING = "staging";
    private static final String PROD = "";
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

    public static String getApiBaseURL() {
        String currentEnv = System.getProperty("environment");
        System.out.println(currentEnv);
        //there is no test and staging env for example.com sample, so that's why by default we set PROD. In real example you would like to set it to your TEST env
        String env = currentEnv != null && !currentEnv.isEmpty() ? currentEnv : PROD;
        String base_url = "https://" + env + "reqres.in/";
        System.out.println(base_url);
        return base_url;
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

