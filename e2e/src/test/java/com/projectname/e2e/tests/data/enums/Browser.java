package com.projectname.e2e.tests.data.enums;

public enum Browser {
    CHROME("Chrome"), EDGE("Edge"), FIREFOX("Firefox");

    private final String browser;

    private Browser(String browser) {
        this.browser = browser;
    }

    public String getValue() {
        return this.browser;
    }
};