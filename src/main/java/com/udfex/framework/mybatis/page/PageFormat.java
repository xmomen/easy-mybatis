package com.udfex.framework.mybatis.page;

/**
 * 分页数据格式化
 */
public class PageFormat {

    public static PageModel dataFormat(Page page) {
        PageInfo pageInfo = new PageInfo();
        pageInfo.setEndRow(page.getEndRow());
        pageInfo.setPageNum(page.getPageNum());
        pageInfo.setPages(page.getPages());
        pageInfo.setPageSize(page.getPageSize());
        pageInfo.setStartRow(page.getStartRow());
        pageInfo.setTotal(page.getTotal());
        return new PageModel(page.getResult(), pageInfo);
    }
}
