package by.ustsinovich.testcase.dto;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.util.List;

public class DepartmentDto {

    private Long id;

    private String description;

    private String location;

    private String name;

    @JsonManagedReference
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