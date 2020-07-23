package com.businessassistant.assistantapi.employee;

import com.businessassistant.assistantapi.order.Order;
import com.businessassistant.assistantapi.employee_position.Position;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String surname;
    @ManyToOne
    private Position position;
    @OneToMany(mappedBy = "employee")
    private List<Order> orders = new ArrayList<>();
    private Integer hoursWorked;
    private Boolean isOnPayroll;

}
