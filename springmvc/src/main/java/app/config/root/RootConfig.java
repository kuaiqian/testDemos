package app.config.root;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import service.UserService;
import service.impl.UserServiceImpl;

@ComponentScan
public class RootConfig {
	@Bean
	public UserService getUserService() {
		return new UserServiceImpl();
	}
}
