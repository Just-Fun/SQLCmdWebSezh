package ua.com.juja.sqlcmd.model.entity;

import javax.persistence.*;

/**
 * Created by Oleg on 6/23/2016.
 */
//CREATE TABLE students(ID SERIAL PRIMARY KEY,name text NOT NULL,age integer)

@Entity
@Table(name = "students", schema = "public")
public class Student {

    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private Integer age;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    public Student() {
        // do nothing
    }

    public Student(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getAge() {
        return age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }
}
