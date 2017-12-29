package spring;

import javax.annotation.PostConstruct;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class SpringInt implements InitializingBean, ApplicationContextAware, BeanNameAware, BeanFactoryAware {
    private SpringInt() {
        super();
        System.out.println("construct");
    }

    @PostConstruct
    public void init() {
        System.out.println("@PostConstruct");
    }

    public void init1() {
        System.out.println("init1");
    }

    @Override
    public void setBeanFactory(BeanFactory paramBeanFactory) throws BeansException {
        System.out.println("setBeanFactory ");
    }

    @Override
    public void setBeanName(String paramString) {
        System.out.println("setBeanName ");
    }

    @Override
    public void setApplicationContext(ApplicationContext paramApplicationContext) throws BeansException {
        System.out.println("setApplicationContext ");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("afterPropertiesSet ");
    }
}
