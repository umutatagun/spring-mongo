package com.example.eshop.controller;

import com.example.eshop.exception.EmployeeAlreadyExistsException;
import com.example.eshop.exception.EmployeeNotFoundException;
import com.example.eshop.model.Employee;
import com.example.eshop.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeController {
    private final EmployeeService employeeService;

    @GetMapping
    private ResponseEntity<List<Employee>> getAllEmployees() {
        return new ResponseEntity(employeeService.getAllEmployees(),OK);
    }

    @GetMapping("/{id}")
    private ResponseEntity<Employee> getEmployeeById(@PathVariable String id) {
        return new ResponseEntity(employeeService.getEmployeeById(id),OK);
    }

    @PostMapping
    private ResponseEntity<Employee> addEmployee(@RequestBody Employee e1) {
        return new ResponseEntity(employeeService.addEmployee(e1),CREATED);
    }

    @PutMapping("/{id}")
    private ResponseEntity<Employee> updateEmployee(@PathVariable String id, @RequestBody Employee e1) {
        return new ResponseEntity(employeeService.updateEmployee(id,e1),CREATED);
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<Void> deleteEmployee(@PathVariable String id) {
        employeeService.deleteEmployee(id);
        return new ResponseEntity(OK);
    }

    @ExceptionHandler(EmployeeNotFoundException.class)
    private ResponseEntity<String> employeeNotFoundException(EmployeeNotFoundException ex) {
        return new ResponseEntity(ex.getMessage(),NOT_FOUND);
    }
    @ExceptionHandler(EmployeeAlreadyExistsException.class)
    private ResponseEntity<String> employeeAlreadyExistsException(EmployeeAlreadyExistsException ex){
        return new ResponseEntity(ex.getMessage(),BAD_REQUEST);
    }
}
