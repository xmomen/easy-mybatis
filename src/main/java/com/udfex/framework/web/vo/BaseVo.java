package com.udfex.framework.web.vo;

import com.udfex.framework.mybatis.page.Page;
import com.udfex.framework.mybatis.page.PageInfo;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * @author: Jeng Tan
 * @date: 2015/3/27
 */
@Component
@JsonIgnoreProperties(ignoreUnknown=true)
public class BaseVo implements Serializable {

    private Integer recordVersion;

    private PageInfo pageInfo;

    public Integer getRecordVersion() {
        return recordVersion;
    }

    public void setRecordVersion(Integer recordVersion) {
        this.recordVersion = recordVersion;
    }

    public PageInfo getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(PageInfo pageInfo) {
        this.pageInfo = pageInfo;
    }
}
