package hibernate;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.orm.hibernate3.HibernateCallback;

import pcids.banksign.model.BankSignInfo;

public class UserDao extends HibernateGenicDao {
    public User load(final User user) {
        return (User) getHibernateTemplate().execute(new HibernateCallback(){
            @Override
            public Object doInHibernate(Session sess) throws HibernateException, SQLException {
                Transaction txn = sess.getTransaction();
                System.out.println(txn.isActive() + "====================");
                Object o = sess.load(User.class, user.getId());
                return o;
            }
        });
    }

    public User loadforUpdate(final User user) {
        return (User) getHibernateTemplate().execute(new HibernateCallback(){
            @Override
            public Object doInHibernate(Session sess) throws HibernateException, SQLException {
                Transaction txn = sess.getTransaction();
                System.out.println(txn.isActive() + "====================");
                Object o = sess.load(User.class, user.getId(), LockMode.UPGRADE);
                if(o != null) {
                    sess.refresh(o, LockMode.UPGRADE);
                }
                return o;
            }
        });
    }

    public User get(User user) {
        return (User) getHibernateTemplate().get(User.class, user.getId());
    }

    public Long save(User user) {
        return (Long) getHibernateTemplate().save(user);
    }

    public void update(User user) {
        getHibernateTemplate().update(user);
    }

    @SuppressWarnings("unchecked")
    public List<User> queryByPan(final String pan) {
        return (List<User>) getHibernateTemplate().execute(new HibernateCallback(){
            @Override
            public List<BankSignInfo> doInHibernate(Session session) throws HibernateException, SQLException {
                String sql = "select distinct a.name from User a where a.pan=:pan";
                Query query = session.createQuery(sql);
                query.setCacheable(true);
                query.setParameter("pan", pan);
                query.list();
                query.setParameter("pan", pan);
                return query.list();
            }
        });
    }
}
