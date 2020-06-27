package com.dfbz.service;

import com.dfbz.dao.UserDao;
import com.dfbz.entity.Page;
import com.dfbz.entity.User;
import org.springframework.util.StringUtils;

import java.util.List;

public class UserService {
    private UserDao userDao = new UserDao();

    public List<User> listAll(Page page, Boolean flag) {

        return userDao.listAll(page.getPageCurrent(), page.getSize(), flag);
    }

    public int addUser(User user) {
        return userDao.addUser(user);
    }

    public List<User> findByName(String name, Integer gender, Page page, Boolean flag) {
//        System.out.println(gender);

        return userDao.findByName(name, gender, page.getPageCurrent(), page.getSize(), flag);
    }

    public User findByUid(int uid) {
        return userDao.findByUid(uid);
    }

    public int updateUser(User user) {
        return userDao.updateUser(user);
    }

    public int delete(String uid) {
        return userDao.delete(uid);
    }

    public User findByNameAndUid(String name, Integer uid) {
        return userDao.findByNameAndUid(name, uid);
    }

    public User findByNameAccurate(String name) {
        return userDao.findByNameAccurate(name);
    }
}
