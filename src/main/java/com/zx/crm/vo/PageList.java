package com.zx.crm.vo;

import java.util.List;

public class PageList <T>{
    private int count;
    private List<T> pageList;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<T> getPageList() {
        return pageList;
    }

    public void setPageList(List<T> pageList) {
        this.pageList = pageList;
    }
}
