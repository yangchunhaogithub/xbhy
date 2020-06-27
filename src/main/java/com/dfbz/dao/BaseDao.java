package com.dfbz.dao;

import com.dfbz.utils.DBUtil;
import org.springframework.jdbc.core.JdbcTemplate;


/**
 * @auth admin
 * @date 2020/6/22
 * @Description
 */
public class BaseDao {

    public JdbcTemplate template = new JdbcTemplate(DBUtil.getDataSource());

}
