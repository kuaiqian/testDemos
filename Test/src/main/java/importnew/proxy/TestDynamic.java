package importnew.proxy;

public class TestDynamic {
    public static void main(String[] args) {
        Person person = new Student();
        MyProxy<Person> myProxy = new MyProxy<Person>(person);
        Person person2 = myProxy.getProxy();
        person2.say();
    }
}
