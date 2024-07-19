package by.ustsinovich.testcase.service;

import by.ustsinovich.testcase.entity.Employee;
import org.springframework.data.domain.Page;

import java.util.List;

public interface EmployeeService {

    Page<Employee> getAllEmployees(int page, int size,
                                   String firstName, String lastName, String patronymic, String email, String phone);

    Employee getEmployeeById(Long id);

    Employee createEmployee(Employee employee);

    Employee updateEmployee(Long id, Employee newEmployee);

    void deleteEmployee(Long id);

    List<Employee> getEmployeesByIds(List<Long> ids);

    void createAllEmployees(List<Employee> employees);

}
