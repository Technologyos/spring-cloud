package com.technologyos.employee.business.impl;

import com.technologyos.employee.business.IEmployeeBusiness;
import com.technologyos.employee.service.IEmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class EmployeeBusinessImpl implements IEmployeeBusiness {

    @Autowired
    private IEmployeeService employeeService;

    @Override
    public void getTask() {
        employeeService.getTasks();
    }
}
