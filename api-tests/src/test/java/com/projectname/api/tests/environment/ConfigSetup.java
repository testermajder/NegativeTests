package com.projectname.api.tests.environment;

public class ConfigSetup extends ConfigReader {

    public ConfigSetup() {
    }

    public static String getAdmin() {
        return getValue("ADMIN");
    }

    public static String getAdminPsw() {
        return getValue("ADMIN_PSW");
    }

    public static String getUser() {
        return getValue("USER");
    }

    public static String getUserPsw() {
        return getValue("USER_PSW");
    }

    public static String getDbUser() {
        return getValue("DB_USER");
    }

    public static String getDbPsw() {
        return getValue("DB_PSW");
    }

    public static String getDbUrl() {
        return getValue("DB_URL");
    }

    public static String getMainUser() {return getValue("MAIN_USER"); }

    public static String getSecondUser() { return getValue("SECOND_USER"); }

    public static String getPass() { return getValue("PASSWORD"); }

}
