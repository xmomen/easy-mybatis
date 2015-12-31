package com.udfex.test.mybatis.model;

import com.udfex.framework.mybatis.model.BaseMybatisModel;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name = "t_department")
public class TDepartment extends BaseMybatisModel implements Serializable {
    /**
     * 主键
     */
    private Integer tDepartmentId;

    /**
     * 部门名称
     */
    private String name;

    /**
     * 描述
     */
    private String description;

    /**
     * 创建人
     */
    private String createUserCode;

    /**
     * 创建日期
     */
    private Date createDateTime;

    /**
     * 创建人所属时区
     */
    private String createTimeZone;

    /**
     * 数据版本号
     */
    private Integer recordVersion;

    /**
     * 修改人
     */
    private String updateUserCode;

    /**
     * 修改日期
     */
    private Date updateDateTime;

    /**
     * 修改人所属时区
     */
    private String updateTimeZone;

    private static final long serialVersionUID = 1L;

    @Column(name = "T_DEPARTMENT_ID")
    @Id
    @GeneratedValue(generator = "UUIDGenerator")
    public Integer gettDepartmentId() {
        return tDepartmentId;
    }

    public void settDepartmentId(Integer tDepartmentId) {
        this.tDepartmentId = tDepartmentId;
        if(tDepartmentId == null){
              removeValidField("tDepartmentId");
              return;
        }
        addValidField("tDepartmentId");
    }

    @Column(name = "NAME")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        if(name == null){
              removeValidField("name");
              return;
        }
        addValidField("name");
    }

    @Column(name = "DESCRIPTION")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
        if(description == null){
              removeValidField("description");
              return;
        }
        addValidField("description");
    }

    @Column(name = "CREATE_USER_CODE")
    public String getCreateUserCode() {
        return createUserCode;
    }

    public void setCreateUserCode(String createUserCode) {
        this.createUserCode = createUserCode;
        if(createUserCode == null){
              removeValidField("createUserCode");
              return;
        }
        addValidField("createUserCode");
    }

    @Column(name = "CREATE_DATE_TIME")
    public Date getCreateDateTime() {
        return createDateTime;
    }

    public void setCreateDateTime(Date createDateTime) {
        this.createDateTime = createDateTime;
        if(createDateTime == null){
              removeValidField("createDateTime");
              return;
        }
        addValidField("createDateTime");
    }

    @Column(name = "CREATE_TIME_ZONE")
    public String getCreateTimeZone() {
        return createTimeZone;
    }

    public void setCreateTimeZone(String createTimeZone) {
        this.createTimeZone = createTimeZone;
        if(createTimeZone == null){
              removeValidField("createTimeZone");
              return;
        }
        addValidField("createTimeZone");
    }

    @Column(name = "RECORD_VERSION")
    @Version
    public Integer getRecordVersion() {
        return recordVersion;
    }

    public void setRecordVersion(Integer recordVersion) {
        this.recordVersion = recordVersion;
        if(recordVersion == null){
              removeValidField("recordVersion");
              return;
        }
        addValidField("recordVersion");
    }

    @Column(name = "UPDATE_USER_CODE")
    public String getUpdateUserCode() {
        return updateUserCode;
    }

    public void setUpdateUserCode(String updateUserCode) {
        this.updateUserCode = updateUserCode;
        if(updateUserCode == null){
              removeValidField("updateUserCode");
              return;
        }
        addValidField("updateUserCode");
    }

    @Column(name = "UPDATE_DATE_TIME")
    public Date getUpdateDateTime() {
        return updateDateTime;
    }

    public void setUpdateDateTime(Date updateDateTime) {
        this.updateDateTime = updateDateTime;
        if(updateDateTime == null){
              removeValidField("updateDateTime");
              return;
        }
        addValidField("updateDateTime");
    }

    @Column(name = "UPDATE_TIME_ZONE")
    public String getUpdateTimeZone() {
        return updateTimeZone;
    }

    public void setUpdateTimeZone(String updateTimeZone) {
        this.updateTimeZone = updateTimeZone;
        if(updateTimeZone == null){
              removeValidField("updateTimeZone");
              return;
        }
        addValidField("updateTimeZone");
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", tDepartmentId=").append(tDepartmentId);
        sb.append(", name=").append(name);
        sb.append(", description=").append(description);
        sb.append(", createUserCode=").append(createUserCode);
        sb.append(", createDateTime=").append(createDateTime);
        sb.append(", createTimeZone=").append(createTimeZone);
        sb.append(", recordVersion=").append(recordVersion);
        sb.append(", updateUserCode=").append(updateUserCode);
        sb.append(", updateDateTime=").append(updateDateTime);
        sb.append(", updateTimeZone=").append(updateTimeZone);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}