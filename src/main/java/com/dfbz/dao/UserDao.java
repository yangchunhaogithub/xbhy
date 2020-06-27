package com.dfbz.dao;


import com.dfbz.entity.User;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import java.util.List;

public class UserDao extends BaseDao {

    public List<User> listAll(Integer pageStr,Integer pageSize,Boolean flag) {
        if(flag){
            String sql = "select * from user where flag=1";
            List<User> query = template.query(sql, new BeanPropertyRowMapper<>(User.class));
            return query;
        }else{
            String sql = "select * from user where flag=1 limit ?,?";
            List<User> query = template.query(sql, new BeanPropertyRowMapper<>(User.class),(pageStr-1)*pageSize,pageSize);
            return query;
        }
    }

    public int addUser(User user) {
        String sql;
        int i;
        if (user.getDeptId() != null) {
            sql = "insert into user(username,email,real_name,age,phone,gender,description,register_time,dept_id,flag) values(?,?,?,?,?,?,?,?,?,1)";
            i = template.update(sql, user.getUsername(), user.getEmail(), user.getRealName(), user.getAge(), user.getPhone(), user.getGender(), user.getDescription(), user.getRegisterTime(), user.getDeptId());
            return i;
        } else {
            sql = "insert into user(username,email,real_name,age,phone,gender,description,register_time,flag) values(?,?,?,?,?,?,?,?,1)";
            i = template.update(sql, user.getUsername(), user.getEmail(), user.getRealName(), user.getAge(), user.getPhone(), user.getGender(), user.getDescription(), user.getRegisterTime());
            return i;
        }
    }

    public List<User> findByName(String name, Integer gender,Integer pageStr,Integer pageSize,Boolean flag) {
        if(flag){
            if (name != null) {
                if (gender != null && gender != -1) {
                    String sql = "select * from user where username like ? and gender=? and flag=1";
                    List<User> users = template.query(sql, new BeanPropertyRowMapper<>(User.class), "%" + name + "%", gender);
                    return users;
                } else {
                    String sql = "select * from user where username like ? and flag=1";
                    List<User> users = template.query(sql, new BeanPropertyRowMapper<>(User.class), "%" + name + "%");
                    return users;
                }
            } else {
                String sql = "select * from user where gender=? and flag=1";
                List<User> users = template.query(sql, new BeanPropertyRowMapper<>(User.class), gender);
                return users;
            }
        }else{
            if (name != null) {
                if (gender != null && gender != -1) {
                    String sql = "select * from user where username like ? and gender=? and flag=1 limit ?,?";
                    List<User> users = template.query(sql, new BeanPropertyRowMapper<>(User.class), "%" + name + "%", gender,(pageStr-1)*pageSize,pageSize);
                    return users;
                } else {
                    String sql = "select * from user where username like ? and flag=1 limit ?,?";
                    List<User> users = template.query(sql, new BeanPropertyRowMapper<>(User.class), "%" + name + "%",(pageStr-1)*pageSize,pageSize);
                    return users;
                }
            } else {
                String sql = "select * from user where gender=? and flag=1 limit ?,?";
                List<User> users = template.query(sql, new BeanPropertyRowMapper<>(User.class), gender,(pageStr-1)*pageSize,pageSize);
                return users;
            }
        }

    }

    public User findByUid(int uid) {
        String sql = "select * from user where id=? and flag=1";
        User user = template.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), uid);
        return user;
    }

    public int updateUser(User user) {
        String sql;
        int i;
        if (user.getDeptId() != null) {
            sql = "update user set username=?,email=?,real_name=?,age=?,phone=?,gender=?,description=?,dept_id=? where id=?";
            i = template.update(sql, user.getUsername(), user.getEmail(), user.getRealName(), user.getAge(), user.getPhone(), user.getGender(), user.getDescription(), user.getDeptId(), user.getId());
            return i;
        } else {
            sql = "update user set username=?,email=?,real_name=?,age=?,phone=?,gender=?,description=? where id=?";
            i = template.update(sql, user.getUsername(), user.getEmail(), user.getRealName(), user.getAge(), user.getPhone(), user.getGender(), user.getDescription(), user.getId());
            return i;
        }
    }

    public int delete(String uid) {
        String sql = "update user set flag=0 where id=?";
        int i = template.update(sql, uid);
        return i;
    }

    public User findByNameAndUid(String name, Integer uid) {
        String sql = "select * from user where id=? and username=?";
        User user = new User();
        try {
            user = template.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), uid, name);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }

        return user;
    }

    public User findByNameAccurate(String name) {
        String sql = "select * from user where username=?";
        User user = template.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), name);
        return user;
    }
}
