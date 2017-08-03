package hibernate;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public class UserService {
    private UserDao userDao;

    private UserDao userDao1;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public User load() {
        User user = new User();
        user.setId(Long.valueOf(12));
        User user2 = userDao.load(user);
        System.out.println(user2.getName());
        return user2;
    }

    public User get() {
        User user = new User();
        user.setId(Long.valueOf(1111));
        return userDao.get(user);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void process() {
        User user = new User();
        user.setId(Long.valueOf(12));
        // User user1 = userDao.loadforUpdate(user);
        userDao.load(user);
        System.out.println("111111111");
        load();
        System.out.println("222222222");
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void loadAndUpdate() throws InterruptedException {
        User user = new User();
        user.setId(Long.valueOf(11));
        User row = userDao.loadforUpdate(user);
        System.out.println("username=" + row.getName());
        user = new User();
        user.setId(Long.valueOf(11));
        userDao1.load(user);
        row.setName("aa12345");
        // Thread.currentThread().sleep(10000);
    }

    @Transactional(propagation = Propagation.NEVER)
    public void change(User row) {
        User user = userDao.load(row);
    }

    @Transactional
    public void add() {
        User user = new User();
        user.setName("aaaaa");
        Long row = userDao.save(user);
    }

    public void update() {
        User user = new User();
        user.setId(Long.valueOf(11505));
        user.setName("111111");
        userDao.update(user);
    }

    public List<User> queryByPan(String pan) {
        return userDao.queryByPan(pan);
    }

    public List<User> queryByTime(Date txnStartTime, Date txnEndTime) {
        return userDao.queryByTime(txnStartTime, txnEndTime);
    }

    @Autowired
    public void setUserDao(@Qualifier("userDao") UserDao userDao) {
        this.userDao = userDao;
    }

    @Autowired
    public void setUserDao1(@Qualifier("userDao1") UserDao userDao1) {
        this.userDao1 = userDao1;
    }
}
