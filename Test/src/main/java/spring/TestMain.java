package spring;

import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

import spring.aop.Human;

public class TestMain {

    public static void main(String[] args) {
//        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("bean.xml");
        ClassPathResource classPathResource=new ClassPathResource("bean.xml");
        XmlBeanFactory beanFactory=new XmlBeanFactory(classPathResource);
        Human human=(Human) beanFactory.getBean("rmiClient",Human.class);
        for (int i = 0; i < 100; i++) {
            human.eat(i);
        }
    }
}
