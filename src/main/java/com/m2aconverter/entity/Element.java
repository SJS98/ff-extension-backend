package com.m2aconverter.entity;

import com.m2aconverter.util.ElementType;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Element {
    private String name;
    private ElementType type;
    private List<Path> paths;
    private List<ElementProperty> properties;
}
