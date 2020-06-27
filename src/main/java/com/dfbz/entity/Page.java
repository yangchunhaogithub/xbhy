package com.dfbz.entity;

/**
 * @author ych
 * @create 2020-06-25 14:43
 */
/*
 * @author admin
 * @date 2020/6/25
 */
public class Page {

    private Integer pageCount;
    private Integer count;
    private Integer pageCurrent=1;
    private Integer size=5;

    public Integer getPageCount() {
        return pageCount;
    }

    public void setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getPageCurrent() {
        return pageCurrent;
    }

    public void setPageCurrent(Integer pageCurrent) {
        this.pageCurrent = pageCurrent;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }
}
