package by.ustsinovich.testcase.service.implementation;

import by.ustsinovich.testcase.entity.Employee;
import by.ustsinovich.testcase.exception.EmployeeNotFoundException;
import by.ustsinovich.testcase.repository.EmployeeRepository;
import by.ustsinovich.testcase.service.EmployeeService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DefaultEmployeeService implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    public DefaultEmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee getEmployeeById(Long id) {
        return employeeRepository
                .findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(id));
    }

    @Override
    @Transactional
    public Employee createEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

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

    @Override
    @Transactional
    public void deleteEmployee(Long id) {
        Employee employee = employeeRepository
                .findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(id));

        employeeRepository.delete(employee);
    }

    @Override
    public List<Employee> getEmployeesByIds(List<Long> ids) {
        return employeeRepository.findAllById(ids);
    }

    @Override
    @Transactional
    public void createAllEmployees(List<Employee> employees) {
        employeeRepository.saveAll(employees);
    }

}
