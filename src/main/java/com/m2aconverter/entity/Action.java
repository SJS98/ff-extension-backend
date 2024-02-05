package com.m2aconverter.entity;

import com.m2aconverter.util.ActionType;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class Action {
    private ActionType action;
    private List<String> moreActions;
    private Element element;
    private Page page;
    private String scriptType;
}