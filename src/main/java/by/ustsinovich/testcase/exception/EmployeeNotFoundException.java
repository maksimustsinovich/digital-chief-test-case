package by.ustsinovich.testcase.exception;

public class EmployeeNotFoundException extends ResourceNotFoundException {

    public EmployeeNotFoundException(Long id) {
        super("Employee with ID `%d` not found".formatted(id));
    }

}
