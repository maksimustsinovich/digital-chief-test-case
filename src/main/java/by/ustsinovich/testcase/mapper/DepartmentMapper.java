package by.ustsinovich.testcase.mapper;

import by.ustsinovich.testcase.dto.DepartmentDto;
import by.ustsinovich.testcase.entity.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class DepartmentMapper {

    private final EmployeeMapper employeeMapper;

    @Autowired
    public DepartmentMapper(EmployeeMapper employeeMapper) {
        this.employeeMapper = employeeMapper;
    }

    public DepartmentDto map(Department department) {
        DepartmentDto departmentDto = new DepartmentDto();

        departmentDto.setDescription(department.getDescription());
        departmentDto.setId(department.getId());
        departmentDto.setName(department.getName());
        departmentDto.setLocation(department.getLocation());
        departmentDto.setEmployees(department
                .getEmployees()
                .stream()
                .map(employeeMapper::map)
                .toList());

        return departmentDto;
    }

    public Department map(DepartmentDto departmentDto) {
        Department department = new Department();

        department.setDescription(departmentDto.getDescription());
        department.setName(departmentDto.getName());
        department.setLocation(departmentDto.getLocation());
        department.setId(departmentDto.getId());
        department.setEmployees(departmentDto
                .getEmployees()
                .stream()
                .map(employeeMapper::map)
                .collect(Collectors.toSet()));

        return department;
    }

}
