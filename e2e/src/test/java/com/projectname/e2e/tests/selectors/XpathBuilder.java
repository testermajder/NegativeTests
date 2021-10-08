package com.projectname.e2e.tests.selectors;

import com.projectname.e2e.tests.utils.enums.Attribute;
import com.projectname.e2e.tests.utils.enums.Tag;
import org.openqa.selenium.By;

public class XpathBuilder {

    private StringBuilder xpath = new StringBuilder();
    private String tag = Tag.wildcard.getValue();
    private String span = Tag.span.getValue();

    private XpathBuilder(Tag tag) {
        this.tag = tag.getValue();
    }

    public XpathBuilder() {
    }

    public static XpathBuilder anXpath() {
        return new XpathBuilder();
    }

    public XpathBuilder withTag(Tag tag) {
        return withTag(tag.getValue());
    }

    public XpathBuilder withTag(String tag) {
        this.tag = tag;
        return this;
    }

    public XpathBuilder withTestAutomationId(String testDataId) {
        xpath.append(".//" + tag + "[contains(@test-auto-id, '" + testDataId + "')]");
        return this;
    }

    public XpathBuilder withTagHavingText(String text) {
        xpath.append(".//" + tag + "[text() = '" + text + "']");
        return this;
    }

    public XpathBuilder withAttributeContainingName(Attribute attribute, String name) {
        return withAttributeContainingName(attribute.getValue(), name);
    }

    public XpathBuilder withAttributeContainingName(String attribute, String name) {
        xpath.append(".//" + tag + "[contains(@" + attribute + ", '" + name + "')]");
        return this;
    }

    public XpathBuilder withClassContainingName(String name) {
        xpath.append(".//" + tag + "[contains(@" + Attribute.class_.getValue() + ", '" + name + "')]");
        return this;
    }

    public XpathBuilder withSpanContainingName(String name){
        xpath.append(".//"+tag+"/"+span+"[contains(text(),'"+name+"')]");
        return this;
    }

    public XpathBuilder endingWithTag(Tag tag) {
        xpath.append(".//" + tag);
        return this;
    }

    public XpathBuilder findFollowingSibling() {
        xpath.append("/following-sibling::*");
        return this;
    }

    public XpathBuilder andWithText(String text) {
        xpath.append("[text()='")
                .append(text)
                .append("']");
        return this;
    }

    public XpathBuilder or() {
        xpath.append("|");
        return this;
    }

    public XpathBuilder elementNr(int nr) {
        xpath.insert(0, "(");
        xpath.append(")");
        xpath.append("[" + nr + "]");
        return this;
    }

    public XpathBuilder ancestor(Attribute attribute, String value) {
        return ancestor(attribute.getValue(), value);
    }

    public XpathBuilder ancestor(String attribute, String value) {
        xpath.append("/ancestor::*[contains(@")
                .append(attribute)
                .append(", '")
                .append(value)
                .append("')][1]");
        return this;
    }

    public XpathBuilder append(String string) {
        xpath.append(string);
        return this;
    }

    public By build() {
        return By.xpath(xpath.toString());
    }
}
