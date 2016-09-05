package ua.com.juja.sqlcmd.model;

import org.springframework.data.repository.CrudRepository;
import ua.com.juja.sqlcmd.model.entity.Student;

import java.util.List;

public interface StudentsRepository extends CrudRepository<Student, Integer> {
    List<Student> findAll();
}
