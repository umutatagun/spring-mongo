package com.example.eshop.service;

import com.example.eshop.model.dto.EmployeeDto;
import com.example.eshop.exception.EmployeeAlreadyExistsException;
import com.example.eshop.exception.EmployeeNotFoundException;
import com.example.eshop.model.Employee;
import com.example.eshop.repository.CustomerRepo;
import com.example.eshop.repository.EmployeeRepo;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import java.security.SecureRandom;
import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EmployeeService {
    private final EmployeeRepo employeeRepo;
    private final CustomerRepo customerRepo;
    private final ModelMapper modelMapper;
    private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(10,new SecureRandom());

    public List<EmployeeDto> getAllEmployees() {
        List<Employee> employeeList = employeeRepo.findAll();
        return employeeList.stream()
                .map((employee -> employeeToDto(employee)))
                .collect(Collectors.toList());
    }

    public EmployeeDto getEmployeeById(String employeeId) {
        Employee e = findEmployeeById(employeeId);
        return employeeToDto(e);
    }

    public EmployeeDto addEmployee(Employee e1) {
        String checkMail = e1.getEmail();
        if(employeeRepo.findByEmail(checkMail).isPresent()){
            throw new EmployeeAlreadyExistsException("Employee Already Exists!");
        }
        if(e1.getPassword() == null){
            throw new RuntimeException("Password cannot be empty");
        }
        e1.setPassword(bCryptPasswordEncoder.encode(e1.getPassword()));
        employeeRepo.save(e1);
        if(!e1.getCustomers().isEmpty()){
            e1.getCustomers().forEach(customer -> {
                customer.setResponseableEmployee(e1.getEmployeeId());
            });
            customerRepo.saveAll(e1.getCustomers());
        }
        return employeeToDto(e1);
    }

    public void deleteEmployee(String id) {
        Employee e1 = findEmployeeById(id);
        employeeRepo.delete(e1);
    }

    public EmployeeDto updateEmployee(String employeeId ,Employee e1) {
        Employee employee = findEmployeeById(employeeId);
        employee.setEmployeeName(e1.getEmployeeName());
        employee.setEmployeeSurname(e1.getEmployeeSurname());
        employee.setPoint(e1.getPoint());
        employee.setEmail(e1.getEmail());
        employee.setCustomers(e1.getCustomers());
        if(!e1.getPassword().isEmpty()){
            employee.setPassword(bCryptPasswordEncoder.encode(e1.getPassword()));
        }
        employee.setLastModifiedDate(new Date());
        employeeRepo.save(employee);

        return employeeToDto(employee);
    }

    public Employee findEmployeeById(String id){
        return employeeRepo.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee not found with id "+id));
    }

    public EmployeeDto employeeToDto(Employee employee) {
        EmployeeDto employeeDto = modelMapper.map(employee, EmployeeDto.class);

        return employeeDto;
    }
}
