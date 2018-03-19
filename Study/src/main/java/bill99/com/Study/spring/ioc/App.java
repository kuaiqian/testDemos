package bill99.com.Study.spring.ioc;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
      
//      ClassPathResource classPathResource=new ClassPathResource("bean.xml");
//      XmlBeanFactory beanFactory=new XmlBeanFactory(classPathResource);
//      System.out.println("begin DI");
//      beanFactory.getBean("springpost");
//      SpringInt service=(SpringInt) beanFactory.getBean("springinit");
  
    	ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("bean.xml");
    	System.out.println("begin DI");
//    	applicationContext.getBean("springinit");
    }
}
