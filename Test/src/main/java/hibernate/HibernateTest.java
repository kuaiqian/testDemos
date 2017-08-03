package hibernate;

import java.text.ParseException;
import java.util.Date;

import org.apache.commons.lang.time.DateUtils;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class HibernateTest {
    private static UserService userService;
    // private static TxnService txnService;
    static {
        userService = (UserService) new ClassPathXmlApplicationContext("hibernate.xml").getBean("userService");
        // txnService = (TxnService) new ClassPathXmlApplicationContext("hibernate.xml").getBean("txnService");
    }

    public static void main(String[] args) throws InterruptedException, ParseException {
        // userService.add();
        // txnService.process();
        // userService.loadAndUpdate();
        // User user = new User();
        // user.setId(Long.valueOf(11));
        // userService.change(user);
        String[] patterns = new String[] { "yyyyMMdd" };
        Date txnStartTime = DateUtils.parseDate("20170607", patterns);
        Date txnEndTime = DateUtils.parseDate("20170807", patterns);
        java.util.List<User> list = userService.queryByTime(txnStartTime, txnEndTime);
        for (User user : list) {
            System.out.println(user.getName());
        }
    }
}
