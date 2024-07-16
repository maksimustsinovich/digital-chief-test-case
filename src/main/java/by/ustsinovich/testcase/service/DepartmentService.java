package by.ustsinovich.testcase.service;

import by.ustsinovich.testcase.entity.Department;
import by.ustsinovich.testcase.entity.Employee;

import java.util.List;

public interface DepartmentService {

    List<Department> getAllDepartments();

    Department getDepartmentById(Long id);

    Department createDepartment(Department department);

    Department updateDepartment(Long id, Department newDepartment);

    void deleteDepartment(Long id);

    List<Employee> getEmployeesByDepartment(Long departmentId);

    void addEmployeeToDepartment(Long departmentId);

    void removeEmployeeFromDepartment(Long departmentId, Long employeeId);

}
