package by.ustsinovich.testcase.service;

import by.ustsinovich.testcase.entity.Employee;

import java.util.List;

public interface EmployeeService {

    List<Employee> getAllEmployees();

    Employee getEmployeeById(Long id);

    Employee createEmployee(Employee employee);

    Employee updateEmployee(Long id, Employee newEmployee);

    void deleteEmployee(Long id);

    List<Employee> getEmployeesByIds(List<Long> ids);

    void createAllEmployees(List<Employee> employees);

}
