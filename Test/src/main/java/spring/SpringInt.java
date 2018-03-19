package spring;

import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.InitializingBean;

import spring.aop.Man;

public class SpringInt implements BeanNameAware,InitializingBean{
	private Man man;
    private SpringInt() {
        super();
        System.out.println("construct");
    }

	public Man getMan() {
		return man;
	}

	public void setMan(Man man) {
		this.man = man;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println("InitializingBean");

	}

	@Override
	public void setBeanName(String arg0) {
		System.out.println("setBeanName");
	}
    
	public void init() {
		System.out.println("custom init");
	}
    
}
