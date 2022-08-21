package com.example.eshop.service;

import com.example.eshop.model.dto.EmployeeDto;
import com.example.eshop.exception.EmployeeAlreadyExistsException;
import com.example.eshop.exception.EmployeeNotFoundException;
import com.example.eshop.model.Employee;
import com.example.eshop.repository.CustomerRepo;
import com.example.eshop.repository.EmployeeRepo;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class EmployeeService {
    private final EmployeeRepo employeeRepo;
    private final CustomerRepo customerRepo;
    private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(10,new SecureRandom());

    public List<Employee> getAllEmployees() {
        return employeeRepo.findAll();
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
        employee.setPassword(bCryptPasswordEncoder.encode(e1.getPassword()));
        employee.setLastModifiedDate(new Date());
        employeeRepo.save(employee);

        return employeeToDto(employee);
    }

    public Employee findEmployeeById(String id){
        return employeeRepo.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee not found with id "+id));
    }

    public EmployeeDto employeeToDto(Employee employee) {
        EmployeeDto employeeDto = new EmployeeDto();

        employeeDto.setEmployeeId(employee.getEmployeeId());
        employeeDto.setEmployeeSurname(employee.getEmployeeSurname());
        employeeDto.setEmployeeName(employee.getEmployeeName());
        employeeDto.setEmail(employee.getEmail());
        employeeDto.setPoint(employee.getPoint());

        return employeeDto;
    }
}
