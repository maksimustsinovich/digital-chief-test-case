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

/**
 * Default implementation of the DepartmentService interface.
 */
@Service
public class DefaultDepartmentService implements DepartmentService {

    private final DepartmentRepository departmentRepository;

    private final EmployeeService employeeService;

    /**
     * Constructor for the DefaultDepartmentService class.
     *
     * @param departmentRepository the DepartmentRepository instance
     * @param employeeService      the EmployeeService instance
     */
    @Autowired
    public DefaultDepartmentService(DepartmentRepository departmentRepository, EmployeeService employeeService) {
        this.departmentRepository = departmentRepository;
        this.employeeService = employeeService;
    }

    /**
     * Returns a list of all departments.
     *
     * @return a list of Department objects
     */
    @Override
    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    /**
     * Returns a department by its ID.
     *
     * @param id the ID of the department to retrieve
     * @return the Department object with the specified ID
     * @throws DepartmentNotFoundException if the department is not found
     */
    @Override
    public Department getDepartmentById(Long id) {
        return departmentRepository
                .findById(id)
                .orElseThrow(() -> new DepartmentNotFoundException(id));
    }

    /**
     * Creates a new department.
     *
     * @param department the Department object to create
     * @return the created Department object
     */
    @Override
    @Transactional
    public Department createDepartment(Department department) {
        return departmentRepository.save(department);
    }

    /**
     * Updates an existing department.
     *
     * @param id          the ID of the department to update
     * @param newDepartment the updated Department object
     * @return the updated Department object
     * @throws DepartmentNotFoundException if the department is not found
     */
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

    /**
     * Deletes a department by its ID.
     *
     * @param id the ID of the department to delete
     * @throws DepartmentNotFoundException if the department is not found
     */
    @Override
    @Transactional
    public void deleteDepartment(Long id) {
        Department department = departmentRepository
                .findById(id)
                .orElseThrow(() -> new DepartmentNotFoundException(id));

        departmentRepository.delete(department);
    }

    /**
     * Returns a list of employees belonging to a department.
     *
     * @param departmentId the ID of the department
     * @return a list of Employee objects
     * @throws DepartmentNotFoundException if the department is not found
     */
    @Override
    public List<Employee> getEmployeesByDepartmentId(Long departmentId) {
        Department department = departmentRepository
                .findById(departmentId)
                .orElseThrow(() -> new DepartmentNotFoundException(departmentId));

        return new ArrayList<>(department.getEmployees());
    }

    /**
     * Adds employees to a department.
     *
     * @param departmentId the ID of the department
     * @param employeesId  the IDs of the employees to add
     * @return the updated Department object
     * @throws DepartmentNotFoundException if the department is not found
     */
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

    /**
     * Removes an employee from a department.
     *
     * @param departmentId the ID of the department
     * @param employeeId   the ID of the employee to remove
     * @return the updated Department object
     * @throws DepartmentNotFoundException if the department is not found
     */
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