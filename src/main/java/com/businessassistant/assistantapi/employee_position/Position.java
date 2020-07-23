package com.businessassistant.assistantapi.employee_position;

import com.businessassistant.assistantapi.employee.Employee;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Position {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private PositionName positionName;
    private Double hourlyWage;
    @OneToMany(mappedBy = "position")
    private List<Employee> employees=new ArrayList<>();
}
