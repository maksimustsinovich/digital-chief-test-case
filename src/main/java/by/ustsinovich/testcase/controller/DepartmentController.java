package by.ustsinovich.testcase.controller;

import by.ustsinovich.testcase.dto.DepartmentDto;
import by.ustsinovich.testcase.dto.EmployeeDto;
import by.ustsinovich.testcase.mapper.DepartmentMapper;
import by.ustsinovich.testcase.mapper.EmployeeMapper;
import by.ustsinovich.testcase.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/departments")
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
    public List<DepartmentDto> retrieveAllDepartments() {
        return departmentService
                .getAllDepartments()
                .stream()
                .map(departmentMapper::map)
                .toList();
    }

    @GetMapping("/{id}")
    public DepartmentDto retrieveDepartmentById(@PathVariable Long id) {
        return departmentMapper.map(departmentService.getDepartmentById(id));
    }

    @PostMapping
    public DepartmentDto createDepartment(@RequestBody DepartmentDto departmentDto) {
        return departmentMapper.map(departmentService.createDepartment(departmentMapper.map(departmentDto)));
    }

    @PutMapping("/{id}")
    public DepartmentDto updateDepartment(@PathVariable Long id, @RequestBody DepartmentDto departmentDto) {
        return departmentMapper.map(departmentService.updateDepartment(id, departmentMapper.map(departmentDto)));
    }

    @DeleteMapping("/{id}")
    public void deleteDepartment(@PathVariable Long id) {
        departmentService.deleteDepartment(id);
    }

    @GetMapping("/{departmentId}/employees")
    public List<EmployeeDto> retrieveEmployeesByDepartmentId(@PathVariable Long departmentId) {
        return departmentService.getEmployeesByDepartmentId(departmentId)
                .stream()
                .map(employeeMapper::map)
                .toList();
    }

    @PostMapping("/{departmentId}/employees")
    public DepartmentDto addEmployeeToDepartment(@PathVariable Long departmentId, @RequestBody EmployeeDto employeeDto) {
        return departmentMapper.map(departmentService.addEmployeeToDepartment(departmentId, employeeDto.getId()));
    }

    @DeleteMapping("/{departmentId}/employees/{employeeId}")
    public DepartmentDto removeEmployeeFromDepartment(@PathVariable Long departmentId, @PathVariable Long employeeId) {
        return departmentMapper.map(departmentService.removeEmployeeFromDepartment(departmentId, employeeId));
    }

}
