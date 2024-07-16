package by.ustsinovich.testcase.repository;

import by.ustsinovich.testcase.entity.Department;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends CrudRepository<Department, Long> {



}
