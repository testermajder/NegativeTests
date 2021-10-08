package com.projectname.e2e.tests.utils;

import com.projectname.e2e.tests.environment.ConfigSetup;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.FileReader;
import java.io.IOException;
import java.util.Date;
import java.util.Map;

public class CapabilityHelper {
    public static final String PATH_TO_TEST_CAPS_JSON = "src/test/resources/browserstack.json";
    private static final long TIMESTAMP = new Date().getTime();

    public static DesiredCapabilities generateCapabilities(int env_cap_id) throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        JSONObject testCapsConfig = (JSONObject) parser.parse(new FileReader(PATH_TO_TEST_CAPS_JSON));

        JSONObject profilesJson = (JSONObject) testCapsConfig.get("capabilities");

        Map<String, String> commonCapabilities = (Map<String, String>) profilesJson.get("common_caps");
        //commonCapabilities.put("build", commonCapabilities.get("build") + " - " + TIMESTAMP);
        Map<String, String> envCapabilities = (Map<String, String>) ((org.json.simple.JSONArray) profilesJson.get("env_caps")).get(env_cap_id);

        DesiredCapabilities caps = new DesiredCapabilities();
        caps.merge(new DesiredCapabilities(commonCapabilities));
        caps.merge(new DesiredCapabilities(envCapabilities));

        caps.setCapability("browserstack.user", ConfigSetup.getBSUser());
        caps.setCapability("browserstack.key", ConfigSetup.getBSKey());

        return caps;
    }

}

