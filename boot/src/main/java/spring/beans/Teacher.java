package spring.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import spring.config.BOY;

@Component
public class Teacher {
    private String name;

    @Autowired
    @BOY
    Student student;

    public Teacher() {
        super();
    }

    public Teacher(String name) {
        this.name = name;
    }

    public void test(String message) {
        System.out.print("teacher:" + getName() + "\t");
        student.sayHi(message);
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
