package com.udfex.framework.mybatis.model;

import com.udfex.framework.model.BaseModel;
import com.udfex.framework.mybatis.page.PageInfo;

/**
 * Created by jengt_000 on 2014/12/23.
 */
public class BaseMybatisExample extends BaseModel {

    public PageInfo pageInfo;

    public PageInfo getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(PageInfo pageInfo) {
        this.pageInfo = pageInfo;
    }
}
