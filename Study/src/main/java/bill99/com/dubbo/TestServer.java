package bill99.com.dubbo;

import java.io.IOException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestServer {

	public static void main(String[] args) throws IOException {
		ApplicationContext applicationContext=new ClassPathXmlApplicationContext("dubbo-server.xml");
		System.in.read();
	}

}
