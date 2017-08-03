package simlator;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:simlator/CupShZlCpBankContext.xml" })
public class TestSpring {
    @Autowired
    public ApplicationContext applicationContext;

    @Test
    public void test() {
        while (true) {
        }
    }
}
