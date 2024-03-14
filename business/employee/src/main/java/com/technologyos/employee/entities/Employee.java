package com.technologyos.employee.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity(name="Employee")
@Table(name = "employee")
@Data
public class Employee {

    @Id
    @SequenceGenerator(name = "employee_sequence", sequenceName = "employee_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "employee_sequence")
    @Column(name = "employee_id", updatable = false)
    private Long id;

    @Column(name = "employee_name", nullable = false)
    private String name;

    @Column(name = "employee_email", nullable = false)
    private String email;
}
