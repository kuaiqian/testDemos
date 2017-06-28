package jdk.study;

import java.io.Externalizable;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Date;

public class SerializableTest implements Serializable {
    public static void main(String[] args) throws FileNotFoundException, IOException, CloneNotSupportedException {
        Person person = new Person();
        person.setName("aaaa");
        ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("serial.txt"));
        outputStream.writeObject(person);
        outputStream.flush();
        People people = new People();
        people.setPerson(person);
        // clone
        People peopleClone = (People) people.clone();
        System.out.println(peopleClone.getPerson().getName());

        ObjectOutputStream outputStream1 = new ObjectOutputStream(new FileOutputStream("serial.external.txt"));
        outputStream1.writeObject(people);
        outputStream1.flush();

    }

    public static class Person implements Serializable {
        String name;

        int age = 6;

        Date birth;

        public Person() {
            super();
            System.out.println("Person Cns");
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public Date getBirth() {
            return birth;
        }

        public void setBirth(Date birth) {
            this.birth = birth;
        }
    }

    static class People implements Externalizable, Cloneable {
        String country;

        String area;

        Date birth;

        Person person;

        public People() {
            super();
            System.out.println("People Cons");
        }

        public Person getPerson() {
            return person;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public String getArea() {
            return area;
        }

        public void setArea(String area) {
            this.area = area;
        }

        public Date getBirth() {
            return birth;
        }

        public void setBirth(Date birth) {
            this.birth = birth;
        }

        public void setPerson(Person person) {
            this.person = person;
        }

        @Override
        public void writeExternal(ObjectOutput out) throws IOException {
            System.out.println("writeExternal");
        }

        @Override
        public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
            System.out.println("readExternal");

        }

        @Override
        protected Object clone() throws CloneNotSupportedException {
            return super.clone();
        }
    }
}
