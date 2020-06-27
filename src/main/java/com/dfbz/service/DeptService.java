package com.dfbz.service;

import com.dfbz.dao.DeptDao;
import com.dfbz.entity.Dept;

import java.util.List;

/**
 * @author ych
 * @create 2020-06-24 16:59
 */
/*
 * @author admin
 * @date 2020/6/24
 */
public class DeptService {
    private DeptDao deptDao=new DeptDao();

    public String findDeptByDeptId(Integer deptId) {
        return deptDao.findDeptByDeptId(deptId);
    }

    public List<Dept> findAll() {
        return deptDao.listAll();
    }
}
