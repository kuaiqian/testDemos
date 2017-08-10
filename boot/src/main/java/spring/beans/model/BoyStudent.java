package spring.beans.model;

import org.springframework.stereotype.Component;

import spring.beans.Student;
import spring.config.BOY;

@Component
@BOY
public class BoyStudent extends Student {
    private String sex = "male";
    public void play() {
        System.out.println("play balls!");
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
