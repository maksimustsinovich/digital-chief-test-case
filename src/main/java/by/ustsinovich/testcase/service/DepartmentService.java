package by.ustsinovich.testcase.service;

import by.ustsinovich.testcase.entity.Department;
import by.ustsinovich.testcase.entity.Employee;
import org.springframework.data.domain.Page;

import java.util.List;

public interface DepartmentService {

    Page<Department> getAllDepartments(int page, int size, String name, String location);

    Department getDepartmentById(Long id);

    Department createDepartment(Department department);

    Department updateDepartment(Long id, Department newDepartment);

    void deleteDepartment(Long id);

    Page<Employee> getEmployeesByDepartmentId(
            Long departmentId,
            int page,
            int size,
            String firstName,
            String lastName,
            String email,
            String phone,
            String patronymic
    );

    Department addEmployeeToDepartment(Long departmentId, List<Long> employeesId);

    Department removeEmployeeFromDepartment(Long departmentId, Long employeeId);

}
