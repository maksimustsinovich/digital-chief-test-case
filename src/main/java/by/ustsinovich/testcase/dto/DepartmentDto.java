package by.ustsinovich.testcase.dto;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.List;

@Schema(description = "Department")
public class DepartmentDto {

    private Long id;

    @Size(max = 200, message = "Department description must be less than 200 characters")
    private String description;

    @Size(max = 50, message = "Department location must be less than 50 characters")
    private String location;

    @NotBlank(message = "Department name is required")
    @Size(max = 50, message = "Department name must be less than 50 characters")
    private String name;

    @JsonManagedReference
    @Schema(description = "List of employees in the department")
    private List<EmployeeDto> employees;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<EmployeeDto> getEmployees() {
        return employees;
    }

    public void setEmployees(List<EmployeeDto> employees) {
        this.employees = employees;
    }

}
