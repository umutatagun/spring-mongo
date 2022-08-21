package com.example.eshop.service;

import com.example.eshop.model.Employee;
import com.example.eshop.repository.EmployeeRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.Optional;

class EmployeeServiceTest {
    @InjectMocks
    private EmployeeService sut;

    @Mock
    private EmployeeRepo employeeRepo;

    @Captor
    private ArgumentCaptor<Employee> employeeCaptor;

    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Test getEmployeeById called repository")
    void should_get_employee_by_id(){
        String id = "tested_id";

        Employee res = new Employee();

        Mockito.when(employeeRepo.findById(id)).thenReturn(Optional.of(res));

        sut.findEmployeeById(id);

        Mockito.verify(employeeRepo).findById(id);
    }

}
