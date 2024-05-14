package com.technologyos.task.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
public class TaskProps {
    private String msg;
    private String buildVersion;
    private Map<String, String> user;
}
