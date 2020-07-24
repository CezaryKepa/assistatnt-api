package com.businessassistant.assistantapi.employee;

import com.businessassistant.assistantapi.exception.ServiceFaultException;
import com.businessassistant.assistantapi.gen.*;
import com.businessassistant.assistantapi.gen.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.util.List;
import java.util.Optional;

@Endpoint
public class EmployeeEndpoint {

    private EmployeeService employeeService;

    @Autowired
    public EmployeeEndpoint(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PayloadRoot(namespace = "http://www.kepa.com/api/employee", localPart = "getEmployeeRequest")
    @ResponsePayload
    public GetEmployeeResponse getEmployeeById(@RequestPayload GetEmployeeRequest getEmployeeRequest) {
        Optional<Employee> employee = employeeService.findById(getEmployeeRequest.getId());
        ServiceStatus serviceStatus = new ServiceStatus();
        GetEmployeeResponse getEmployeeResponse = new GetEmployeeResponse();

        if (!employee.isPresent()) {
            serviceStatus.setStatusCode("404");
            serviceStatus.setMessage("No employee with this id");
            throw new ServiceFaultException("Not Found", serviceStatus);
        }

        Employee payload = employee.get();
        serviceStatus.setStatusCode("200");
        serviceStatus.setMessage("OK");
        getEmployeeResponse.setEmployee(payload);
        getEmployeeResponse.setServiceStatus(serviceStatus);

        return getEmployeeResponse;
    }

    @PayloadRoot(namespace = "http://www.kepa.com/api/employee", localPart = "getAllEmployeesRequest")
    @ResponsePayload
    public GetAllEmployeesResponse findAll(@RequestPayload GetAllEmployeesRequest getAllEmployeesRequest){

        List<Employee> employees = employeeService.findAll();
        GetAllEmployeesResponse getAllEmployeesResponse = new GetAllEmployeesResponse();

        getAllEmployeesResponse.setEmployees(employees);

        return getAllEmployeesResponse;
    }
    @PayloadRoot(namespace = "http://www.kepa.com/api/employee", localPart = "changePayrollStatus")
    @ResponsePayload
    public ChangePayrollStatusResponse changePayrollStatus(@RequestPayload ChangePayrollStatus changePayrollStatus){

        Employee employee = employeeService.changePayrollStatus(changePayrollStatus.getId());
        ServiceStatus serviceStatus = new ServiceStatus();
        ChangePayrollStatusResponse changePayrollStatusResponse = new ChangePayrollStatusResponse();

        serviceStatus.setStatusCode("200");
        serviceStatus.setMessage("OK");
        changePayrollStatusResponse.setIsOnPayroll(employee.isIsOnPayroll());
        changePayrollStatusResponse.setServiceStatus(serviceStatus);

        return changePayrollStatusResponse;
    }


}
