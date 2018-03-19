package bill99.com.dubbo;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestClient {

	public static void main(String[] args) {
		ApplicationContext applicationContext=new ClassPathXmlApplicationContext("dubbo-consumer.xml");
		GreetingService greetingsService=applicationContext.getBean("greetingsService",GreetingService.class);
		System.out.println(greetingsService.sayHello());
	}

}
