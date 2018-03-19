package springmvc;

import org.springframework.web.client.RestTemplate;

import springmvc.test.model.Message;


public class RestClient {

	public static void main(String[] args) {
		RestTemplate restTemplate=new RestTemplate();
		Message mess=restTemplate.getForObject("http://localhost:8080/springmvc/message/list/{userId}", Message.class,111);
		System.out.println(mess.toString());
	}

}
