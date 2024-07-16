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
        return departmentService.getAllDepartments();
    }

    @GetMapping("/{id}")
    public Department retrieveDepartmentById(@PathVariable Long id) {
        return departmentService.getDepartmentById(id);
    }

    @PostMapping
    public Department createDepartment(@RequestBody Department department) {
        return departmentService.createDepartment(department);
    }

    @PutMapping("/{id}")
    public Department updateDepartment(@PathVariable Long id, @RequestBody Department department) {
        return departmentService.updateDepartment(id, department);
    }

    @DeleteMapping("/{id}")
    public void deleteDepartment(@PathVariable Long id) {
        departmentService.deleteDepartment(id);
    }

    @GetMapping("/{departmentId}/employees")
    public List<Employee> retrieveEmployeesByDepartmentId(@PathVariable Long departmentId) {
        return departmentService.getEmployeesByDepartmentId(departmentId);
    }

    @PostMapping("/{departmentId}/employees")
    public Department addEmployeeToDepartment(@PathVariable Long departmentId, @RequestBody Long employeeId) {
        return departmentService.addEmployeeToDepartment(departmentId, employeeId);
    }

    @DeleteMapping("/{departmentId}/employees/{employeeId}")
    public Department removeEmployeeFromDepartment(@PathVariable Long departmentId, @PathVariable Long employeeId) {
        return departmentService.removeEmployeeFromDepartment(departmentId, employeeId);
    }

}
