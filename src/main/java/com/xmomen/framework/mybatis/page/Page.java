package com.xmomen.framework.mybatis.page;

import org.apache.ibatis.session.RowBounds;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Mybatis - 分页对象
 * @param <E>
 */
public class Page<E> extends ArrayList<E> implements Serializable {
    /**
     * 不进行count查询
     */
    public static final int NO_SQL_COUNT = -1;
    public static final int SQL_COUNT = 0;
    private int pageNum;//当前页
    private int pageSize;//每页条数
    private int startRow;//当前页首条记录的行数
    private int endRow;//当前页最后一条记录的行数
    private long total;//总记录数
    private int pages;//总页数

    public Page(){}

    public Page(int pageNum, int pageSize) {
        this(pageNum, pageSize, SQL_COUNT);
    }

    public Page(int pageNum, int pageSize, int total) {
        super(pageSize);
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.total = total;
        this.startRow = pageNum > 0 ? (pageNum - 1) * pageSize : 0;
        this.endRow = pageNum * pageSize;
    }

    public Page(RowBounds rowBounds, int total) {
        super(rowBounds.getLimit());
        this.pageSize = rowBounds.getLimit();
        this.startRow = rowBounds.getOffset();
        //RowBounds方式默认不求count总数，如果想求count,可以修改这里为SQL_COUNT
        this.total = total;
        this.endRow = this.startRow + this.pageSize;
    }

    public List<E> getResult() {
        return this;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public int getEndRow() {
        return endRow;
    }

    public void setEndRow(int endRow) {
        this.endRow = endRow;
    }

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

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "Page{" +
                "pageNum=" + pageNum +
                ", pageSize=" + pageSize +
                ", startRow=" + startRow +
                ", endRow=" + endRow +
                ", total=" + total +
                ", pages=" + pages +
                '}';
    }
}