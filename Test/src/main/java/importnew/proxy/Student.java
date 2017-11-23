package importnew.proxy;

public class Student implements Person, Human {
    @Override
    public void say() {
        if(this instanceof Person) {
            System.out.println("11111");
        }
        if(this instanceof Human) {
            System.out.println("22222");
        }
    }
}
