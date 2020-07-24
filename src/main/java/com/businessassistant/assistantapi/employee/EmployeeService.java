package com.businessassistant.assistantapi.employee;


import com.businessassistant.assistantapi.exception.ServiceFaultException;
import com.businessassistant.assistantapi.gen.Employee;
import com.businessassistant.assistantapi.gen.ServiceStatus;
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

    public Employee changePayrollStatus(long id) {

        Optional<com.businessassistant.assistantapi.employee.Employee> employeeById = employeeRepository.findById(id);
        ServiceStatus serviceStatus = new ServiceStatus();

        if (!employeeById.isPresent()) {
            serviceStatus.setStatusCode("404");
            serviceStatus.setMessage("No employee with this id");
            throw new ServiceFaultException("Not Found", serviceStatus);
        }

        com.businessassistant.assistantapi.employee.Employee employee = employeeById.get();

        employee.setIsOnPayroll(!employee.getIsOnPayroll());
        employeeRepository.save(employee);

        return EmployeeMapper.toDto(employee);
    }
}
