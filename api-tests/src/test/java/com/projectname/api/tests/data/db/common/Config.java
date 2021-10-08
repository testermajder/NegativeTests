package com.projectname.api.tests.data.db.common;

import com.projectname.api.tests.environment.ConfigSetup;
import org.hibernate.cfg.Configuration;

import java.io.File;

//Use this class to extend xml config file and make sure it doesn't contain sensitive DB data
public class Config {

    public static Configuration exampleDB() {
        return new Configuration().setProperty("hibernate.connection.url", ConfigSetup.getDbUrl())
                .setProperty("hibernate.connection.username", ConfigSetup.getDbUser())
                .setProperty("hibernate.connection.password", ConfigSetup.getDbPsw())
                .configure(new File("example_database.cfg.xml"));
    }

}
