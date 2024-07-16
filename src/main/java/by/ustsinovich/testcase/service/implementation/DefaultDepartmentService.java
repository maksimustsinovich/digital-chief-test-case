package by.ustsinovich.testcase.service.implementation;

import by.ustsinovich.testcase.entity.Department;
import by.ustsinovich.testcase.entity.Employee;
import by.ustsinovich.testcase.exception.DepartmentNotFoundException;
import by.ustsinovich.testcase.repository.DepartmentRepository;
import by.ustsinovich.testcase.service.DepartmentService;
import by.ustsinovich.testcase.service.EmployeeService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DefaultDepartmentService implements DepartmentService {

    private final DepartmentRepository departmentRepository;

    private final EmployeeService employeeService;

    @Autowired
    public DefaultDepartmentService(DepartmentRepository departmentRepository, EmployeeService employeeService) {
        this.departmentRepository = departmentRepository;
        this.employeeService = employeeService;
    }

    @Override
    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    @Override
    public Department getDepartmentById(Long id) {
        return departmentRepository
                .findById(id)
                .orElseThrow(() -> new DepartmentNotFoundException(id));
    }

    @Override
    @Transactional
    public Department createDepartment(Department department) {
        return departmentRepository.save(department);
    }

    @Override
    @Transactional
    public Department updateDepartment(Long id, Department newDepartment) {
        Department department = departmentRepository
                .findById(id)
                .orElseThrow(() -> new DepartmentNotFoundException(id));

        department.setDescription(newDepartment.getDescription());
        department.setName(newDepartment.getName());
        department.setLocation(newDepartment.getLocation());

        return departmentRepository.save(department);
    }

    @Override
    @Transactional
    public void deleteDepartment(Long id) {
        Department department = departmentRepository
                .findById(id)
                .orElseThrow(() -> new DepartmentNotFoundException(id));

        departmentRepository.delete(department);
    }

    @Override
    public List<Employee> getEmployeesByDepartmentId(Long departmentId) {
        Department department = departmentRepository
                .findById(departmentId)
                .orElseThrow(() -> new DepartmentNotFoundException(departmentId));

        return new ArrayList<>(department.getEmployees());
    }

    @Override
    @Transactional
    public Department addEmployeeToDepartment(Long departmentId, List<Long> employeesId) {
        Department department = departmentRepository
                .findById(departmentId)
                .orElseThrow(() -> new DepartmentNotFoundException(departmentId));

        List<Employee> employees = employeeService.getEmployeesByIds(employeesId);

        employees.forEach(employee -> {
            department.getEmployees().add(employee);
            employee.setDepartment(department);
        });

        employeeService.createAllEmployees(employees);

        return departmentRepository.save(department);
    }

    @Override
    @Transactional
    public Department removeEmployeeFromDepartment(Long departmentId, Long employeeId) {
        Department department = departmentRepository
                .findById(departmentId)
                .orElseThrow(() -> new DepartmentNotFoundException(departmentId));

        Employee employee = employeeService.getEmployeeById(employeeId);
        department.getEmployees().remove(employee);

        return departmentRepository.save(department);
    }

}
