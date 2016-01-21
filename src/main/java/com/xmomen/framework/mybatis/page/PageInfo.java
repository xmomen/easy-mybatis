package com.xmomen.framework.mybatis.page;

import java.io.Serializable;

/**
 * Created by Jeng on 2016/1/21.
 */
public class PageInfo implements Serializable {

    private int pageNum;//当前页
    private int pageSize;//每页条数
    private int startRow;//当前页首条记录的行数
    private int endRow;//当前页最后一条记录的行数
    private long total;//总记录数
    private int pages;//总页数

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getStartRow() {
        return startRow;
    }

    public void setStartRow(int startRow) {
        this.startRow = startRow;
    }

    public int getEndRow() {
        return endRow;
    }

    public void setEndRow(int endRow) {
        this.endRow = endRow;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }
}
