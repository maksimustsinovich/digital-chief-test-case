package by.ustsinovich.testcase.controller;

import by.ustsinovich.testcase.entity.Department;
import by.ustsinovich.testcase.entity.Employee;
import by.ustsinovich.testcase.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/departments")
public class DepartmentController {

    private final DepartmentService departmentService;

    @Autowired
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping
    public List<Department> retrieveAllDepartments() {
        return null;
    }

    @GetMapping("/{id}")
    public Department retrieveDepartmentById(@PathVariable Long id) {
        return null;
    }

    @PostMapping
    public void createDepartment() {

    }

    @PutMapping("/{id}")
    public void updateDepartment(@PathVariable Long id) {

    }

    @DeleteMapping("/{id}")
    public void deleteDepartment(@PathVariable Long id) {

    }

    @GetMapping("/{departmentId}/employees")
    public List<Employee> retrieveEmployeesByDepartment(@PathVariable Long departmentId) {
        return null;
    }

    @PostMapping("/{departmentId}/employees")
    public void addEmployeeToDepartment(@PathVariable Long departmentId) {

    }

    @DeleteMapping("/{departmentId}/employees/{employeeId}")
    public void removeEmployeeFromDepartment(@PathVariable Long departmentId, @PathVariable Long employeeId) {

    }

}
