package spring.beans.model;

import org.springframework.stereotype.Component;

import spring.beans.Student;
import spring.config.GIRL;

@Component
@GIRL
public class GirlStudent extends Student {
    private String sex = "female";
    public void sing() {
        System.out.println("hello,world!");
    }

    @Override
    public String getSex() {
        return sex;
    }

    @Override
    public void setSex(String sex) {
        this.sex = sex;
    }
}
