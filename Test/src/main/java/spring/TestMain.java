package spring;

import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

public class TestMain {

    public static void main(String[] args) {
//        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("bean.xml");
//        System.out.println("begin DI");
//        applicationContext.getBean("springinit");
        
        ClassPathResource classPathResource=new ClassPathResource("bean.xml");
        XmlBeanFactory beanFactory=new XmlBeanFactory(classPathResource);
        System.out.println("begin DI");
        beanFactory.getBean("springpost");
        SpringInt service=(SpringInt) beanFactory.getBean("springinit");
    }
}
