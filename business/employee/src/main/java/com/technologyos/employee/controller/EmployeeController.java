package com.technologyos.employee.controller;

import com.google.gson.Gson;
import com.technologyos.employee.config.ConfigProperties;
import com.technologyos.employee.entities.Employee;
import com.technologyos.employee.models.EmployeeProps;
import com.technologyos.employee.repository.EmployeeRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/employee")
public class EmployeeController {

    @Autowired
    private EmployeeRepository roleRepository;

    @Autowired
    private ConfigProperties configProperties;

    @GetMapping()
    @Operation(summary = "Get all employee",
        description = "Get all employee with active status sorted by ASC creating time", tags = {"Employee"})
    @ApiResponses(value={@ApiResponse(responseCode = "200", description = "Success",
        content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Employee.class))}),
        @ApiResponse(responseCode = "400", description = "Bad request")})
    public ResponseEntity<List<Employee>> findAll() {
        return Optional.ofNullable(roleRepository.findAll().isEmpty() ? null:roleRepository.findAll())
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.noContent().build());
    }

    @GetMapping("/properties")
    public String getProperties(){
        EmployeeProps employeeProps = new EmployeeProps(configProperties.getMsg(),
            configProperties.getVersion(), configProperties.getDetails());
        return new Gson().toJson(employeeProps);
    }

}
