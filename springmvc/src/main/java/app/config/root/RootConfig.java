package app.config.root;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;

import aop.UserAop;
import service.UserService;
import service.impl.UserServiceImpl;

@ComponentScan
@EnableAspectJAutoProxy
@Import(value= {DaoConfig.class})
public class RootConfig {
	@Bean
	public UserService getUserService() {
		return new UserServiceImpl();
	}
	@Bean
	public UserAop getUserAop() {
		return new UserAop();
	}
}
