package by.ustsinovich.testcase.controller;

import by.ustsinovich.testcase.dto.EmployeeDto;
import by.ustsinovich.testcase.mapper.EmployeeMapper;
import by.ustsinovich.testcase.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    private final EmployeeMapper employeeMapper;

    @Autowired
    public EmployeeController(EmployeeService employeeService, EmployeeMapper employeeMapper) {
        this.employeeService = employeeService;
        this.employeeMapper = employeeMapper;
    }

    @GetMapping
    public List<EmployeeDto> retrieveAllEmployees() {
        return employeeService
                .getAllEmployees()
                .stream()
                .map(employeeMapper::map)
                .toList();
    }

    @GetMapping("/{id}")
    public EmployeeDto retrieveEmployeeById(@PathVariable Long id) {
        return employeeMapper.map(employeeService.getEmployeeById(id));
    }

    @PostMapping
    public EmployeeDto createEmployee(@RequestBody EmployeeDto employeeDto) {
        return employeeMapper.map(employeeService.createEmployee(employeeMapper.map(employeeDto)));
    }

    @PutMapping("/{id}")
    public EmployeeDto updateEmployee(@PathVariable Long id, @RequestBody EmployeeDto employeeDto) {
        return employeeMapper.map(employeeService.updateEmployee(id, employeeMapper.map(employeeDto)));
    }

    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
    }

}
