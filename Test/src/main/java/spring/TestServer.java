package spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestServer {
    public static void main(String[] args) {
      ApplicationContext applicationContext = new ClassPathXmlApplicationContext("bean.xml");
    }
}
