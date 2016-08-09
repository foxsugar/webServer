package server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import server.model.User;
import server.dao.IUserDao;

/**
 * Created by SunXianping on 2016/8/8 0008.
 */

@Service("userService")
public class UserService {

    @Autowired
    private IUserDao userDao;

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


    }


}
