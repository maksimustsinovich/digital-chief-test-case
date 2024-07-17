package by.ustsinovich.testcase.exception;

public class DepartmentNotFoundException extends ResourceNotFoundException {

    public DepartmentNotFoundException(Long id) {
        super("Department with ID `%d` not found".formatted(id));
    }

}
