package spring;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

public class SpringBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object paramObject, String paramString) throws BeansException {
        System.out.println("postProcessBeforeInitialization=" + paramObject + ",bean name=" + paramString);
        return paramObject;
    }

    @Override
    public Object postProcessAfterInitialization(Object paramObject, String paramString) throws BeansException {
        System.out.println("postProcessAfterInitialization=" + paramObject + ",bean name=" + paramString);
        return paramObject;
    }
}
