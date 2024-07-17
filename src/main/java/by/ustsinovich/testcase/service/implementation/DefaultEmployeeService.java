package by.ustsinovich.testcase.service.implementation;

import by.ustsinovich.testcase.entity.Employee;
import by.ustsinovich.testcase.exception.EmployeeNotFoundException;
import by.ustsinovich.testcase.repository.EmployeeRepository;
import by.ustsinovich.testcase.service.EmployeeService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Default implementation of the EmployeeService interface.
 */
@Service
public class DefaultEmployeeService implements EmployeeService {

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
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
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
        return employeeRepository.save(employee);
    }

    /**
     * Updates an existing employee.
     *
     * @param id         the ID of the employee to update
     * @param newEmployee the updated Employee object
     * @return the updated Employee object
     * @throws EmployeeNotFoundException if the employee is not found
     */
    @Override
    @Transactional
    public Employee updateEmployee(Long id, Employee newEmployee) {
        Employee employee = employeeRepository
                .findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(id));

        employee.setFirstName(newEmployee.getFirstName());
        employee.setPatronymic(newEmployee.getPatronymic());
        employee.setLastName(newEmployee.getLastName());
        employee.setJobTitle(newEmployee.getJobTitle());
        employee.setEmail(newEmployee.getEmail());
        employee.setPhone(newEmployee.getPhone());

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
        Employee employee = employeeRepository
                .findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(id));

        employeeRepository.delete(employee);
    }

    /**
     * Returns a list of employees by their IDs.
     *
     * @param ids the IDs of the employees to retrieve
     * @return a list of Employee objects
     */
    @Override
    public List<Employee> getEmployeesByIds(List<Long> ids) {
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
        employeeRepository.saveAll(employees);
    }

}