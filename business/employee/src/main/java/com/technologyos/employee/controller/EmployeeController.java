package com.technologyos.employee.controller;

import com.technologyos.employee.business.IEmployeeBusiness;
import com.technologyos.employee.entities.Employee;
import com.technologyos.employee.repository.EmployeeRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/api/v1/employee")
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private Environment env;

    @GetMapping()
    @Operation(summary = "Get all employee",
        description = "Get all employee with active status sorted by ASC creating time", tags = {"Employee"})
    @ApiResponses(value={@ApiResponse(responseCode = "200", description = "Success",
        content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Employee.class))}),
        @ApiResponse(responseCode = "400", description = "Bad request")})
    @CircuitBreaker(name="getEmployeesCB", fallbackMethod = "getEmployeesFB")
    public ResponseEntity<List<Employee>> findAll() {
        return Optional.ofNullable(employeeRepository.findAll().isEmpty() ? null:employeeRepository.findAll())
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.noContent().build());
    }

    public void getEmployeesFB(Throwable throwable){
        log.error("getEmployeesFB error " + throwable);
    }

    @Autowired
    private IEmployeeBusiness employeeBusiness;

    @GetMapping("/check")
    public String check(){
        employeeBusiness.getTask();
        return "Your environment is " + env.getProperty("environment") + " with the database "+ env.getProperty("db");
    }

}
