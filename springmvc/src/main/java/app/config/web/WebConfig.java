package app.config.web;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@ComponentScan(basePackages= {"mvc"})
@EnableWebMvc
public class WebConfig extends WebMvcConfigurerAdapter{
	
	@Bean
	public ViewResolver internalViewResolver() {
		InternalResourceViewResolver resolver=new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/jsp/");
		resolver.setSuffix(".jsp");
		resolver.setExposeContextBeansAsAttributes(true);
		return resolver;
	}
	
	@Bean
	public ViewResolver contetViewResolver() {
		ContentNegotiatingViewResolver resolver=new ContentNegotiatingViewResolver();
		return resolver;
	}
	
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}
	
	@Bean(name= {"multipartResolver"})
	public MultipartResolver getMultipart() {
//		CommonsMultipartResolver commonsMultipartResolver= new CommonsMultipartResolver();
//		commonsMultipartResolver.setMaxInMemorySize(1024);
		return new StandardServletMultipartResolver();
	}
	
	
}
