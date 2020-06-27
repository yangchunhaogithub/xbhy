package com.dfbz.dao;


import com.dfbz.entity.Dept;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import java.util.List;

/**
 * @author ych
 * @create 2020-06-24 17:00
 */
/*
 * @author admin
 * @date 2020/6/24
 */
public class DeptDao extends BaseDao{
    public String findDeptByDeptId(Integer deptId) {
        String sql="select * from dept where id=?";
        Dept dept=new Dept();
        try {
            dept= template.queryForObject(sql, new BeanPropertyRowMapper<>(Dept.class), deptId);
        }catch (EmptyResultDataAccessException e){
            return null;
        }
        return dept.getName();
    }

    public List<Dept> listAll() {
        String sql = "select * from dept";
        List<Dept> query = template.query(sql, new BeanPropertyRowMapper<>(Dept.class));
        return query;
    }
}
