package com.xmomen.demo.entity;

import com.xmomen.framework.mybatis.model.BaseMybatisModel;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name = "t_employee")
public class TEmployee extends BaseMybatisModel {
    /**
     * 主键
     */
    private Integer tEmployeeId;

    /**
     * 主键
     */
    private Integer tDepartmentId;

    /**
     * 员工名称
     */
    private String name;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 出生日期
     */
    private Date birthday;

    /**
     * 数据版本号
     */
    private Integer recordVersion;

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

    @Column(name = "T_EMPLOYEE_ID")
    @Id
    @GeneratedValue(generator = "UUIDGenerator")
    public Integer gettEmployeeId() {
        return tEmployeeId;
    }

    public void settEmployeeId(Integer tEmployeeId) {
        this.tEmployeeId = tEmployeeId;
        if(tEmployeeId == null){
              removeValidField("tEmployeeId");
              return;
        }
        addValidField("tEmployeeId");
    }

    @Column(name = "T_DEPARTMENT_ID")
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

    @Column(name = "AGE")
    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
        if(age == null){
              removeValidField("age");
              return;
        }
        addValidField("age");
    }

    @Column(name = "BIRTHDAY")
    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
        if(birthday == null){
              removeValidField("birthday");
              return;
        }
        addValidField("birthday");
    }

    @Column(name = "RECORD_VERSION")
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
}