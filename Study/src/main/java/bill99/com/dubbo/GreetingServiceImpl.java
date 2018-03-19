package bill99.com.dubbo;

public class GreetingServiceImpl implements GreetingService{

	public String sayHello() {
		System.out.println("hello");
		return "hello";
	}

}
