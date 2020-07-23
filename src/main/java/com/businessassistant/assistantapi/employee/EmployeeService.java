package com.businessassistant.assistantapi.employee;


import com.businessassistant.assistantapi.client.ClientMapper;
import com.businessassistant.assistantapi.gen.Employee;
import com.businessassistant.assistantapi.order.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Optional<Employee> findById(long id) {
        return employeeRepository.findById(id).map(EmployeeMapper::toDto);
    }

    public List<Employee> findAll() {
        return employeeRepository.findAll()
                .stream()
                .map(EmployeeMapper::toDto)
                .collect(Collectors.toList());
    }

    public Optional<Employee> changePayrollStatus(long id) {

        //TODO refactor, orders when not on payroll?
        Optional<com.businessassistant.assistantapi.employee.Employee> employee = employeeRepository.findById(id);
        employee.get().setIsOnPayroll(!employee.get().getIsOnPayroll());
        employeeRepository.save(employee.get());
        return employee.map(EmployeeMapper::toDto);
    }
}
