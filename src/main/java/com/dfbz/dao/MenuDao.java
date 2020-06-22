package com.dfbz.dao;

import com.dfbz.entity.Menu;
import com.dfbz.utils.DBUtil;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 * @auth admin
 * @date 2020/6/22
 * @Description
 */
public class MenuDao extends BaseDao {

    public List<Menu> listAll() {
        String sql = "select * from menu";
        return template.query(sql, new BeanPropertyRowMapper<Menu>(Menu.class));
    }

}
