package com.businessassistant.assistantapi.employee;

import com.businessassistant.assistantapi.gen.Employee;
import com.businessassistant.assistantapi.order.OrderMapper;

import java.math.BigInteger;
import java.util.stream.Collectors;

public class EmployeeMapper {
    public static Employee toDto(com.businessassistant.assistantapi.employee.Employee employee){
        Employee dto= new Employee();
        dto.setId(employee.getId());
        dto.setName(employee.getName());
        dto.setHoursWorked(BigInteger.valueOf(employee.getHoursWorked()));
        dto.setOrders(employee.getOrders()
                .stream()
                .map(OrderMapper::toDto)
                .collect(Collectors.toList()));
        dto.setIsOnPayroll(employee.getIsOnPayroll());
        dto.setPosition(String.valueOf(employee.getPosition()));
        dto.setSurname(employee.getSurname());
        return dto;
    }
}
