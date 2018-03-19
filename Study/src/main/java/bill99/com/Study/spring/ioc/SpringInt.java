package bill99.com.Study.spring.ioc;

import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;

public class SpringInt implements BeanNameAware,InitializingBean,DisposableBean,FactoryBean<SpringInit2>{
	private SpringInit2 init2;
    private SpringInt() {
        super();
        System.out.println("construct");
    }

	public void afterPropertiesSet() throws Exception {
		System.out.println("InitializingBean");

	}

	public void setBeanName(String arg0) {
		System.out.println("setBeanName");
	}
    
	public void init() {
		System.out.println("custom init");
	}

	public void destroy() throws Exception {
		System.out.println("destroy");
	}

	public SpringInit2 getObject() throws Exception {
		return null;
	}

	public Class<?> getObjectType() {
		return SpringInit2.class;
	}

	public boolean isSingleton() {
		return true;
	}

	public SpringInit2 getInit2() {
		return init2;
	}

	public void setInit2(SpringInit2 init2) {
		this.init2 = init2;
		System.out.println("SpringInit2");
	}
    
}
