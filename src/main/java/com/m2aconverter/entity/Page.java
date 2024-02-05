package com.m2aconverter.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Page {
    private String pageName;
    private String pageUrl;
    private List<Page> subPage;
    private List<Element> elements;

    public Page(String pageName, String pageUrl) {
        this.pageName = pageName;
        this.pageUrl = pageUrl;
    }
}
