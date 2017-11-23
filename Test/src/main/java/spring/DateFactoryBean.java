package spring;

import java.util.Date;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.PriorityOrdered;

public class DateFactoryBean implements FactoryBean, InitializingBean, PriorityOrdered, ApplicationContextAware {
    private Date date = new Date();

    @Override
    public Object getObject() throws Exception {
        System.out.println("test===>getObject " + date.toLocaleString());
        return date;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("test===>afterPropertiesSet " + date.toLocaleString());
    }

    @Override
    public int getOrder() {
        System.out.println("test===>getOrder " + date.toLocaleString());
        return 12345;
    }

    @Override
    public Class getObjectType() {
        return Date.class;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }

    @Override
    public void setApplicationContext(ApplicationContext arg0) throws BeansException {
        System.out.println("test===>setApplicationContext " + date.toLocaleString());
    }
}
