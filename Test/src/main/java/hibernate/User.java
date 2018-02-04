package hibernate;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Proxy;

/**
 * 快捷签约信息
 * <p>
 * 用以保存用户银行卡与银行之间的快捷签约信�?
 * 
 * @author weicheng.wu
 * @version 1.0
 * @since 2013/9/9
 */
@Entity
@org.hibernate.annotations.Entity(dynamicUpdate = true, dynamicInsert = true)
@Proxy(lazy = false)
@Table(name = "t_test_user")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
// @SequenceGenerator(name = "idBankSignInfoGen", sequenceName = "ID_BANK_SIGN_INFO", allocationSize = 1)
public class User {
    private static final long serialVersionUID = 1L;

    private Long id;

    private String name;

    private Long age;

    private Date birth;

    @Id
    @Column(name = "id")
    // @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "idBankSignInfoGen")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "age")
    public Long getAge() {
        return age;
    }

    public void setAge(Long age) {
        this.age = age;
    }

    @Column(name = "birth")
    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }
}
