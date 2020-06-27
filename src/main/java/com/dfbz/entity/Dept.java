package com.dfbz.entity;

import java.io.Serializable;

/**
 * @author ych
 * @create 2020-06-24 17:46
 */
/*
 * @author admin
 * @date 2020/6/24
 */
public class Dept implements Serializable {
    private Integer id;
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
