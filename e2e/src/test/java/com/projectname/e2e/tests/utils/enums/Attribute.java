package com.projectname.e2e.tests.utils.enums;

public enum Attribute {
    class_("class"),
    for_("for"),
    type("type"),
    data_test_id("data-test-id"),
    href("href"),
    checked("checked"),
    role("role"),
    title("title");

    Attribute(String value) {
        this.value = value;
    }

    private final String value;

    public String getValue() {
        return value;
    }
}
