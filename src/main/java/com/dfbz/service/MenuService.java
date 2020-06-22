package com.dfbz.service;

import com.dfbz.dao.MenuDao;
import com.dfbz.entity.Menu;

import java.util.List;

/**
 * @auth admin
 * @date 2020/6/22
 * @Description
 */
public class MenuService {

    private MenuDao menuDao = new MenuDao();

    public List<Menu> listAll() {
        return menuDao.listAll();
    }
}
