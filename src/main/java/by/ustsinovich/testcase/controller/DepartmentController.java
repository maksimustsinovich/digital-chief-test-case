package by.ustsinovich.testcase.controller;

import by.ustsinovich.testcase.dto.DepartmentDto;
import by.ustsinovich.testcase.dto.EmployeeDto;
import by.ustsinovich.testcase.mapper.DepartmentMapper;
import by.ustsinovich.testcase.mapper.EmployeeMapper;
import by.ustsinovich.testcase.service.DepartmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/departments")
@Tag(name = "Department API", description = "Department management API")
public class DepartmentController {

    private final DepartmentService departmentService;

    private final DepartmentMapper departmentMapper;

    private final EmployeeMapper employeeMapper;

    @Autowired
    public DepartmentController(DepartmentService departmentService, DepartmentMapper departmentMapper,
                                EmployeeMapper employeeMapper) {
        this.departmentService = departmentService;
        this.departmentMapper = departmentMapper;
        this.employeeMapper = employeeMapper;
    }

    @GetMapping
    @Operation(summary = "Retrieve all departments",
            description = "Returns a list of all departments")
    @ApiResponse(responseCode = "200",
            description = "Departments retrieved successfully")
    @ApiResponse(responseCode = "404",
            description = "No departments found")
    public List<DepartmentDto> retrieveAllDepartments() {
        return departmentService
                .getAllDepartments()
                .stream()
                .map(departmentMapper::map)
                .toList();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Retrieve department by ID",
            description = "Returns a department by its ID")
    @ApiResponse(responseCode = "200",
            description = "Department retrieved successfully")
    @ApiResponse(responseCode = "404",
            description = "Department not found")
    public DepartmentDto retrieveDepartmentById(@PathVariable Long id) {
        return departmentMapper.map(departmentService.getDepartmentById(id));
    }

    @PostMapping
    @Operation(summary = "Create a new department",
            description = "Creates a new department")
    @ApiResponse(responseCode = "201",
            description = "Department created successfully")
    @ApiResponse(responseCode = "400",
            description = "Invalid department data")
    @ResponseStatus(HttpStatus.CREATED)
    public DepartmentDto createDepartment(@RequestBody @Valid DepartmentDto departmentDto) {
        return departmentMapper.map(departmentService.createDepartment(departmentMapper.map(departmentDto)));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an existing department",
            description = "Updates an existing department")
    @ApiResponse(responseCode = "200",
            description = "Department updated successfully")
    @ApiResponse(responseCode = "404",
            description = "Department not found")
    public DepartmentDto updateDepartment(@PathVariable Long id, @RequestBody @Valid DepartmentDto departmentDto) {
        return departmentMapper.map(departmentService.updateDepartment(id, departmentMapper.map(departmentDto)));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a department",
            description = "Deletes a department")
    @ApiResponse(responseCode = "204",
            description = "Department deleted successfully")
    @ApiResponse(responseCode = "404",
            description = "Department not found")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteDepartment(@PathVariable Long id) {
        departmentService.deleteDepartment(id);
    }

    @GetMapping("/{departmentId}/employees")
    @Operation(summary = "Retrieve employees by department ID",
            description = "Returns a list of employees by department ID")
    @ApiResponse(responseCode = "200",
            description = "Employees retrieved successfully")
    @ApiResponse(responseCode = "404",
            description = "No employees found")
    public List<EmployeeDto> retrieveEmployeesByDepartmentId(@PathVariable Long departmentId) {
        return departmentService.getEmployeesByDepartmentId(departmentId)
                .stream()
                .map(employeeMapper::map)
                .toList();
    }

    @PostMapping("/{departmentId}/employees")
    @Operation(summary = "Add employees to a department",
            description = "Adds employees to a department")
    @ApiResponse(responseCode = "200",
            description = "Employees added successfully")
    @ApiResponse(responseCode = "404",
            description = "Department not found")
    public DepartmentDto addEmployeeToDepartments(@PathVariable Long departmentId, @RequestBody List<Long> employees) {
        return departmentMapper.map(departmentService.addEmployeeToDepartment(departmentId, employees));
    }

    @DeleteMapping("/{departmentId}/employees/{employeeId}")
    @Operation(summary = "Remove an employee from a department",
            description = "Removes an employee from a department")
    @ApiResponse(responseCode = "200",
            description = "Employee removed successfully")
    @ApiResponse(responseCode = "404",
            description = "Department or employee not found")
    public DepartmentDto removeEmployeeFromDepartment(@PathVariable Long departmentId, @PathVariable Long employeeId) {
        return departmentMapper.map(departmentService.removeEmployeeFromDepartment(departmentId, employeeId));
    }

}
