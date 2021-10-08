package com.projectname.e2e.tests.utils.enums;

public enum Tag {
    div("div"),
    button("button"),
    label("label"),
    svg("svg"),
    input("input"),
    body("body"),
    a("a"),
    p("p"),
    wildcard("*"),
    span("span");

    private final String value;

    Tag(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
