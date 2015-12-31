package com.udfex.framework.model;

import com.udfex.framework.core.BasePo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jengt_000 on 2014/12/28.
 */
public class BaseModel extends BasePo implements Serializable{

    //对应数据库数据版本字段，请勿随意修改
    public static final String FIELD_RECORD_VERSION = "RECORD_VERSION";
    public static final String FIELD_UPDATE_DATE_TIME = "UPDATE_DATE_TIME";
    public static final String FIELD_CREATE_DATE_TIME = "CREATE_DATE_TIME";

    public static final String ROW_STATUS_INITIAL = "initial";
    public static final String ROW_STATE_ADDED = "add";
    public static final String ROW_STATE_DELETED = "remove";
    public static final String ROW_STATE_MODIFIED = "modify";
    private String rowStatus;
    private List<String> validFields = new ArrayList();

    public String getRowStatus() {
		return rowStatus;
	}

	public void setRowStatus(String rowStatus) {
		this.rowStatus = rowStatus;
	}

	public boolean isInitilRow() {
        return rowStatus != null && ROW_STATUS_INITIAL.equals(rowStatus);
    }

    public boolean isAddRow() {
        return rowStatus != null && ROW_STATE_ADDED.equals(rowStatus);
    }

    public boolean isModifyRow() {
        return rowStatus != null && ROW_STATE_MODIFIED.equals(rowStatus);
    }

    public boolean isDeletedRow() {
        return rowStatus != null && ROW_STATE_DELETED.equals(rowStatus);
    }

    protected void addValidField(String fieldName) {
        if (!this.validFields.contains(fieldName)){
            this.validFields.add(fieldName);
        }
    }

    protected void removeValidField(String fieldName) {
        if (this.validFields.contains(fieldName)){
            this.validFields.remove(fieldName);
        }
    }

    public List<String> validFields()
    {
        return this.validFields;
    }
}
