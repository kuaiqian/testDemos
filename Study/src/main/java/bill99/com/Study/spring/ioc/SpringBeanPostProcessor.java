package bill99.com.Study.spring.ioc;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

public class SpringBeanPostProcessor implements BeanPostProcessor {
    public Object postProcessBeforeInitialization(Object paramObject, String paramString) throws BeansException {
        System.out.println("postProcessBeforeInitialization=" + paramObject + ",bean name=" + paramString);
        return paramObject;
    }

    public Object postProcessAfterInitialization(Object paramObject, String paramString) throws BeansException {
        System.out.println("postProcessAfterInitialization=" + paramObject + ",bean name=" + paramString);
        return paramObject;
    }
}
