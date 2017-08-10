package spring.beans;

public class Student {
    private String sex;
    private String name;

    public void sayHi(String message) {
        System.out.println("student:" + message + ",sex:" + getSex());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
