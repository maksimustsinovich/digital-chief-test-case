package by.ustsinovich.testcase.controller;

import by.ustsinovich.testcase.dto.EmployeeDto;
import by.ustsinovich.testcase.mapper.EmployeeMapper;
import by.ustsinovich.testcase.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employees")
@Tag(name = "Employee API", description = "Employee management API")
public class EmployeeController {

    private final EmployeeService employeeService;

    private final EmployeeMapper employeeMapper;

    @Autowired
    public EmployeeController(
            EmployeeService employeeService,
            EmployeeMapper employeeMapper
    ) {
        this.employeeService = employeeService;
        this.employeeMapper = employeeMapper;
    }

    @GetMapping
    @Operation(summary = "Retrieve all employees",
            description = "Returns a list of all employees")
    @ApiResponse(responseCode = "200",
            description = "Employees retrieved successfully")
    @ApiResponse(responseCode = "404",
            description = "No employees found")
    public Page<EmployeeDto> retrieveAllEmployees(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(required = false) String firstName,
            @RequestParam(required = false) String lastName,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String phone,
            @RequestParam(required = false) String patronymic
    ) {
        return employeeService
                .getAllEmployees(page, size, firstName, lastName, patronymic, email, phone)
                .map(employeeMapper::map);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Retrieve employee by ID",
            description = "Returns an employee by its ID")
    @ApiResponse(responseCode = "200",
            description = "Employee retrieved successfully")
    @ApiResponse(responseCode = "404",
            description = "Employee not found")
    public EmployeeDto retrieveEmployeeById(@PathVariable Long id) {
        return employeeMapper
                .map(employeeService.getEmployeeById(id));
    }

    @PostMapping
    @Operation(summary = "Create a new employee",
            description = "Creates a new employee")
    @ApiResponse(responseCode = "201",
            description = "Employee created successfully")
    @ApiResponse(responseCode = "400",
            description = "Invalid employee data")
    @ResponseStatus(HttpStatus.CREATED)
    public EmployeeDto createEmployee(@RequestBody @Valid EmployeeDto employeeDto) {
        return employeeMapper
                .map(employeeService.createEmployee(employeeMapper.map(employeeDto)));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an existing employee",
            description = "Updates an existing employee")
    @ApiResponse(responseCode = "200",
            description = "Employee updated successfully")
    @ApiResponse(responseCode = "404",
            description = "Employee not found")
    public EmployeeDto updateEmployee(@PathVariable Long id, @RequestBody @Valid EmployeeDto employeeDto) {
        return employeeMapper
                .map(employeeService.updateEmployee(id, employeeMapper.map(employeeDto)));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete an employee",
            description = "Deletes an employee")
    @ApiResponse(responseCode = "204",
            description = "Employee deleted successfully")
    @ApiResponse(responseCode = "404",
            description = "Employee not found")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEmployee(@PathVariable Long id) {
        employeeService
                .deleteEmployee(id);
    }

}
