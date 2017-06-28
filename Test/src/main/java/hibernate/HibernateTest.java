package hibernate;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class HibernateTest {
    private static UserService userService;
    // private static TxnService txnService;
    static {
        userService = (UserService) new ClassPathXmlApplicationContext("hibernate.xml").getBean("userService");
        // txnService = (TxnService) new ClassPathXmlApplicationContext("hibernate.xml").getBean("txnService");
    }

    public static void main(String[] args) throws InterruptedException {
        // userService.add();
        // txnService.process();
        userService.loadAndUpdate();
        // User user = new User();
        // user.setId(Long.valueOf(11));
        // userService.change(user);
    }
}
