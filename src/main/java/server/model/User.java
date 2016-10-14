package server.model;


import server.entity.Role;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by SunXianping on 2016/8/8 0008.
 */

@Entity
@Table(name="user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//根据不同数据库自动选择合适的id生成方案，这里使用mysql,为递增型
    private long id;
    private String name;
    private String passwd;
    private int level;
    private int gold;
    @ManyToMany(cascade = {CascadeType.PERSIST,CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles", joinColumns = { @JoinColumn(name = "userid") }, inverseJoinColumns = { @JoinColumn(name = "roleid") })
    private Set<Role> roles = new HashSet<Role>(0);


    public int getGold() {
        return gold;
    }

    public User setGold(int gold) {
        this.gold = gold;
        return this;
    }

    public long getId() {
        return id;
    }

    public User setId(long id) {
        this.id = id;
        return this;
    }

    public int getLevel() {
        return level;
    }

    public User setLevel(int level) {
        this.level = level;
        return this;
    }

    public String getName() {
        return name;
    }

    public User setName(String name) {
        this.name = name;
        return this;
    }

    public String getPasswd() {
        return passwd;
    }

    public User setPasswd(String passwd) {
        this.passwd = passwd;
        return this;
    }


    public Set<Role> getRoles() {
        return roles;
    }

    public User setRoles(Set<Role> roles) {
        this.roles = roles;
        return this;
    }
}
