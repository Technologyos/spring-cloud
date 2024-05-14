package com.technologyos.task.controller;

import com.google.gson.Gson;
import com.technologyos.task.config.ConfigProperties;
import com.technologyos.task.entities.Task;
import com.technologyos.task.models.TaskProps;
import com.technologyos.task.repository.TaskRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
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
@RequestMapping("/api/v1/task")
public class TaskController {

    @Autowired
    private TaskRepository roleRepository;

    @Autowired
    private ConfigProperties configProperties;

    @GetMapping()
    @Operation(summary = "Get all tasks",
        description = "Get all tasks with active status sorted by ASC creating time", tags = {"Task"})
    @ApiResponses(value={@ApiResponse(responseCode = "200", description = "Success",
        content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Task.class))}),
        @ApiResponse(responseCode = "400", description = "Bad request")})
    @CircuitBreaker(name="getEmployeesCB")
    public ResponseEntity<List<Task>> findAll() {
        return Optional.ofNullable(roleRepository.findAll().isEmpty() ? null:roleRepository.findAll())
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.noContent().build());
    }

    @GetMapping("/properties")
    public String getProperties(){
        TaskProps employeeProps = new TaskProps(configProperties.getMsg(),
            configProperties.getVersion(), configProperties.getDetails());
        return new Gson().toJson(employeeProps);
    }

}
