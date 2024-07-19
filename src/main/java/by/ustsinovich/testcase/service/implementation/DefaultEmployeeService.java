package by.ustsinovich.testcase.service.implementation;

import by.ustsinovich.testcase.entity.Department;
import by.ustsinovich.testcase.entity.Employee;
import by.ustsinovich.testcase.exception.EmployeeNotFoundException;
import by.ustsinovich.testcase.repository.EmployeeRepository;
import by.ustsinovich.testcase.service.EmployeeService;
import by.ustsinovich.testcase.specification.EmployeeSpecification;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Default implementation of the EmployeeService interface.
 */
@Service
public class DefaultEmployeeService implements EmployeeService {

    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultEmployeeService.class);

    private final EmployeeRepository employeeRepository;

    /**
     * Constructor for the DefaultEmployeeService class.
     *
     * @param employeeRepository the EmployeeRepository instance
     */
    @Autowired
    public DefaultEmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    /**
     * Returns a list of all employees.
     *
     * @return a list of Employee objects
     */
    @Override
    public Page<Employee> getAllEmployees(int page, int size, String firstName, String lastName, String patronymic,
                                          String email, String phone) {
        LOGGER.info("Getting all employees with filters: " +
                        "page={}, size={}, firstName={}, lastName={}, patronymic={}, email={}, phone={}",
                page, size, firstName, lastName, patronymic, email, phone);

        Pageable pageable = PageRequest.of(page, size);
        Specification<Employee> specification = EmployeeSpecification.filterBy(
                firstName,
                lastName,
                patronymic,
                email,
                phone
        );

        return employeeRepository.findAll(specification, pageable);
    }

    /**
     * Returns an employee by its ID.
     *
     * @param id the ID of the employee to retrieve
     * @return the Employee object with the specified ID
     * @throws EmployeeNotFoundException if the employee is not found
     */
    @Override
    public Employee getEmployeeById(Long id) {
        LOGGER.info("Getting employee by id: {}", id);
        return employeeRepository
                .findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(id));
    }

    /**
     * Creates a new employee.
     *
     * @param employee the Employee object to create
     * @return the created Employee object
     */
    @Override
    @Transactional
    public Employee createEmployee(Employee employee) {
        LOGGER.info("Creating new employee: {}", employee);
        return employeeRepository.save(employee);
    }

    /**
     * Updates an existing employee.
     *
     * @param id          the ID of the employee to update
     * @param newEmployee the updated Employee object
     * @return the updated Employee object
     * @throws EmployeeNotFoundException if the employee is not found
     */
    @Override
    @Transactional
    public Employee updateEmployee(Long id, Employee newEmployee) {
        LOGGER.info("Updating employee with id: {}", id);
        Employee employee = employeeRepository
                .findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(id));

        employee.setFirstName(newEmployee.getFirstName());
        employee.setPatronymic(newEmployee.getPatronymic());
        employee.setLastName(newEmployee.getLastName());
        employee.setJobTitle(newEmployee.getJobTitle());
        employee.setEmail(newEmployee.getEmail());
        employee.setPhone(newEmployee.getPhone());

        LOGGER.info("Updated employee: {}", employee);
        return employeeRepository.save(employee);
    }

    /**
     * Deletes an employee by its ID.
     *
     * @param id the ID of the employee to delete
     * @throws EmployeeNotFoundException if the employee is not found
     */
    @Override
    @Transactional
    public void deleteEmployee(Long id) {
        LOGGER.info("Deleting employee with id: {}", id);
        Employee employee = employeeRepository
                .findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(id));

        employeeRepository.delete(employee);
        LOGGER.info("Employee deleted successfully");
    }

    /**
     * Returns a list of employees by their IDs.
     *
     * @param ids the IDs of the employees to retrieve
     * @return a list of Employee objects
     */
    @Override
    public List<Employee> getEmployeesByIds(List<Long> ids) {
        LOGGER.info("Getting employees by ids: {}", ids);
        return employeeRepository.findAllById(ids);
    }

    /**
     * Creates multiple employees at once.
     *
     * @param employees the list of Employee objects to create
     */
    @Override
    @Transactional
    public void createAllEmployees(List<Employee> employees) {
        LOGGER.info("Creating multiple employees: {}", employees);
        employeeRepository.saveAll(employees);
        LOGGER.info("Employees created successfully");
    }

    @Override
    public Page<Employee> getEmployeesByDepartmentAndFilters(
            Department department, int page, int size, String firstName, String lastName,
            String email, String phone, String patronymic) {
        LOGGER.info("Getting all employees with filters: " +
                        "department={} page={}, size={}, firstName={}, lastName={}, patronymic={}, email={}, phone={}",
                department, page, size, firstName, lastName, patronymic, email, phone);

        Pageable pageable = PageRequest.of(page, size);
        Specification<Employee> specification = EmployeeSpecification.filterBy(
                firstName,
                lastName,
                patronymic,
                email,
                phone
        );

        specification = specification.and(
                (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("department"), department)
        );

        return employeeRepository.findAll(specification, pageable);
    }

}