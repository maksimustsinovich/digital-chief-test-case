package by.ustsinovich.testcase.service.implementation;

import by.ustsinovich.testcase.entity.Department;
import by.ustsinovich.testcase.entity.Employee;
import by.ustsinovich.testcase.exception.DepartmentNotFoundException;
import by.ustsinovich.testcase.repository.DepartmentRepository;
import by.ustsinovich.testcase.service.DepartmentService;
import by.ustsinovich.testcase.service.EmployeeService;
import by.ustsinovich.testcase.specification.DepartmentSpecification;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Default implementation of the DepartmentService interface.
 */
@Service
public class DefaultDepartmentService implements DepartmentService {

    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultDepartmentService.class);

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
    public Page<Department> getAllDepartments(int page, int size, String name, String location) {
        LOGGER.info("Getting all departments with filters: " +
                "page={}, size={}, name={}, location={}",
                page, size, name, location);

        Specification<Department> specification = DepartmentSpecification.filterBy(name, location);
        Pageable pageable = PageRequest.of(page, size);

        return departmentRepository.findAll(specification, pageable);
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
        LOGGER.info("Getting department by id: {}", id);
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
        LOGGER.info("Creating new department: {}", department);
        return departmentRepository.save(department);
    }

    /**
     * Updates an existing department.
     *
     * @param id            the ID of the department to update
     * @param newDepartment the updated Department object
     * @return the updated Department object
     * @throws DepartmentNotFoundException if the department is not found
     */
    @Override
    @Transactional
    public Department updateDepartment(Long id, Department newDepartment) {
        LOGGER.info("Updating department with id: {}", id);
        Department department = departmentRepository
                .findById(id)
                .orElseThrow(() -> new DepartmentNotFoundException(id));

        department.setDescription(newDepartment.getDescription());
        department.setName(newDepartment.getName());
        department.setLocation(newDepartment.getLocation());

        LOGGER.info("Updated department: {}", department);
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
        LOGGER.info("Deleting department with id: {}", id);
        Department department = departmentRepository
                .findById(id)
                .orElseThrow(() -> new DepartmentNotFoundException(id));

        departmentRepository.delete(department);
        LOGGER.info("Department deleted successfully");
    }

    /**
     * Returns a list of employees belonging to a department.
     *
     * @param departmentId the ID of the department
     * @param page
     * @param size
     * @param firstName
     * @param lastName
     * @param email
     * @param phone
     * @param patronymic
     * @return a list of Employee objects
     * @throws DepartmentNotFoundException if the department is not found
     */
    @Override
    public Page<Employee> getEmployeesByDepartmentId(Long departmentId, int page, int size,
                                                     String firstName, String lastName, String email,
                                                     String phone, String patronymic) {
        LOGGER.info("Getting employees by department id: {}", departmentId);
        Department department = departmentRepository
                .findById(departmentId)
                .orElseThrow(() -> new DepartmentNotFoundException(departmentId));

        return employeeService.getEmployeesByDepartmentAndFilters(
                department, page, size, firstName, lastName,
                email, phone, patronymic
        );
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
        LOGGER.info("Adding employees to department with id: {}", departmentId);
        Department department = departmentRepository
                .findById(departmentId)
                .orElseThrow(() -> new DepartmentNotFoundException(departmentId));

        List<Employee> employees = employeeService.getEmployeesByIds(employeesId);

        employees.forEach(employee -> {
            department.getEmployees().add(employee);
            employee.setDepartment(department);
        });

        employeeService.createAllEmployees(employees);

        LOGGER.info("Employees added to department successfully");
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
        LOGGER.info("Removing employee from department with id: {}", departmentId);
        Department department = departmentRepository
                .findById(departmentId)
                .orElseThrow(() -> new DepartmentNotFoundException(departmentId));

        Employee employee = employeeService.getEmployeeById(employeeId);
        department.getEmployees().remove(employee);

        LOGGER.info("Employee removed from department successfully");
        return departmentRepository.save(department);
    }

}