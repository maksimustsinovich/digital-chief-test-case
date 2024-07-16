package by.ustsinovich.testcase.controller;

import by.ustsinovich.testcase.entity.Employee;
import by.ustsinovich.testcase.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public List<Employee> retrieveAllEmployees() {
        return null;
    }

    @GetMapping("/{id}")
    public Employee retrieveEmployeeById(@PathVariable Long id) {
        return null;
    }

    @PostMapping
    public void createEmployee() {

    }

    @PutMapping("/{id}")
    public void updateEmployee(@PathVariable Long id) {

    }

    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable Long id) {

    }

}
