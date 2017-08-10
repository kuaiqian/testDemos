package spring;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import spring.beans.Teacher;
import spring.config.BaseConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = BaseConfig.class)
public class TestSpring {
    @Autowired
    public ApplicationContext applicationContext;

    @Test
    public void testIoc() {
        for (String beanName : applicationContext.getBeanDefinitionNames()) {
            System.out.println(beanName);
        }
        Teacher teacher = (Teacher) applicationContext.getBean("teacher");
        teacher.test("hello");
    }

    @Test
    public void testOkHttp() {

    }
}
