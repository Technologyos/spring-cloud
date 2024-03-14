package com.technologyos.employee.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
public class EmployeeProps {
    private String msg;
    private String buildVersion;
    private Map<String, String> user;
}
