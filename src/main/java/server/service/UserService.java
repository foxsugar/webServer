package server.service;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Service;
import server.dao.IResourceDao;
import server.dao.IRoleDao;
import server.dao.IUsersDao;
import server.entity.Users;
import server.model.User;
import server.dao.IUserDao;
import sun.misc.BASE64Encoder;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by SunXianping on 2016/8/8 0008.
 */

@Service("userService")
public class UserService {

    @Autowired
    private IUserDao userDao;

    @Autowired
    private IUsersDao usersDao;
    @Autowired
    private IRoleDao roldDao;
    @Autowired
    private IResourceDao resourceDao;

    public void test() {
        for (User user : userDao.findAll()) {
            System.out.println(user.getName());
        }
        System.out.println("cont = " + userDao.count());
        User user = new User();
        user.setGold(2);
        user.setName("1");
        user.setPasswd("1");
        System.out.println(userDao.save(user));


        userDao.findAll();


    }

    public void register(String userName,String passwd) {
        User user = new User();
        user.setName(userName);
        try {
            try {
                MessageDigest md = MessageDigest.getInstance("MD5");
                BASE64Encoder base64en = new BASE64Encoder();
                String passwd1=base64en.encode(md.digest((passwd + "salt").getBytes("utf-8")));
                user.setPasswd(passwd);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        userDao.save(user);


    }

    public User getUser(String name) {
        List<User> userList = userDao.findByName(name);
        return userList.size()>0?userList.get(0):null;
    }

    public Page<User> listUser(int page, int pageSize){

        Pageable pageAble = new PageRequest(page,pageSize);
        return userDao.findAll(pageAble);
    }

    public void updateUser(long id,String name) {
        User user = userDao.findOne(id);
        if (user != null) {
            user.setName(name);
        }
        userDao.save(user);
    }

    public void addUser(User user) {
        userDao.save(user);
    }

    public void delUser(List<Long> ids) {
        for (long id : ids) {
            userDao.findOne(id).setRoles(null);
            userDao.delete(id);
        }
    }



}
