package by.ustsinovich.testcase.mapper;

import by.ustsinovich.testcase.dto.EmployeeDto;
import by.ustsinovich.testcase.entity.Employee;
import org.springframework.stereotype.Component;

@Component
public class EmployeeMapper {

    public EmployeeDto map(Employee employee) {
        EmployeeDto employeeDto = new EmployeeDto();

        employeeDto.setId(employee.getId());
        employeeDto.setFirstName(employee.getFirstName());
        employeeDto.setPatronymic(employee.getPatronymic());
        employeeDto.setLastName(employee.getLastName());
        employeeDto.setEmail(employee.getEmail());
        employeeDto.setPhone(employee.getPhone());
        employeeDto.setJobTitle(employee.getJobTitle());

        return employeeDto;
    }

    public Employee map(EmployeeDto employeeDto) {
        Employee employee = new Employee();

        employee.setId(employeeDto.getId());
        employee.setFirstName(employeeDto.getFirstName());
        employee.setPatronymic(employeeDto.getPatronymic());
        employee.setLastName(employeeDto.getLastName());
        employee.setEmail(employeeDto.getEmail());
        employee.setPhone(employeeDto.getPhone());
        employee.setJobTitle(employeeDto.getJobTitle());

        return employee;
    }

}
