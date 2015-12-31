package com.udfex.test.mybatis.model;

import com.udfex.framework.mybatis.model.BaseMybatisExample;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TEmployeeExample extends BaseMybatisExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TEmployeeExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andTEmployeeIdIsNull() {
            addCriterion("T_EMPLOYEE_ID is null");
            return (Criteria) this;
        }

        public Criteria andTEmployeeIdIsNotNull() {
            addCriterion("T_EMPLOYEE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andTEmployeeIdEqualTo(Integer value) {
            addCriterion("T_EMPLOYEE_ID =", value, "tEmployeeId");
            return (Criteria) this;
        }

        public Criteria andTEmployeeIdNotEqualTo(Integer value) {
            addCriterion("T_EMPLOYEE_ID <>", value, "tEmployeeId");
            return (Criteria) this;
        }

        public Criteria andTEmployeeIdGreaterThan(Integer value) {
            addCriterion("T_EMPLOYEE_ID >", value, "tEmployeeId");
            return (Criteria) this;
        }

        public Criteria andTEmployeeIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("T_EMPLOYEE_ID >=", value, "tEmployeeId");
            return (Criteria) this;
        }

        public Criteria andTEmployeeIdLessThan(Integer value) {
            addCriterion("T_EMPLOYEE_ID <", value, "tEmployeeId");
            return (Criteria) this;
        }

        public Criteria andTEmployeeIdLessThanOrEqualTo(Integer value) {
            addCriterion("T_EMPLOYEE_ID <=", value, "tEmployeeId");
            return (Criteria) this;
        }

        public Criteria andTEmployeeIdIn(List<Integer> values) {
            addCriterion("T_EMPLOYEE_ID in", values, "tEmployeeId");
            return (Criteria) this;
        }

        public Criteria andTEmployeeIdNotIn(List<Integer> values) {
            addCriterion("T_EMPLOYEE_ID not in", values, "tEmployeeId");
            return (Criteria) this;
        }

        public Criteria andTEmployeeIdBetween(Integer value1, Integer value2) {
            addCriterion("T_EMPLOYEE_ID between", value1, value2, "tEmployeeId");
            return (Criteria) this;
        }

        public Criteria andTEmployeeIdNotBetween(Integer value1, Integer value2) {
            addCriterion("T_EMPLOYEE_ID not between", value1, value2, "tEmployeeId");
            return (Criteria) this;
        }

        public Criteria andTDepartmentIdIsNull() {
            addCriterion("T_DEPARTMENT_ID is null");
            return (Criteria) this;
        }

        public Criteria andTDepartmentIdIsNotNull() {
            addCriterion("T_DEPARTMENT_ID is not null");
            return (Criteria) this;
        }

        public Criteria andTDepartmentIdEqualTo(Integer value) {
            addCriterion("T_DEPARTMENT_ID =", value, "tDepartmentId");
            return (Criteria) this;
        }

        public Criteria andTDepartmentIdNotEqualTo(Integer value) {
            addCriterion("T_DEPARTMENT_ID <>", value, "tDepartmentId");
            return (Criteria) this;
        }

        public Criteria andTDepartmentIdGreaterThan(Integer value) {
            addCriterion("T_DEPARTMENT_ID >", value, "tDepartmentId");
            return (Criteria) this;
        }

        public Criteria andTDepartmentIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("T_DEPARTMENT_ID >=", value, "tDepartmentId");
            return (Criteria) this;
        }

        public Criteria andTDepartmentIdLessThan(Integer value) {
            addCriterion("T_DEPARTMENT_ID <", value, "tDepartmentId");
            return (Criteria) this;
        }

        public Criteria andTDepartmentIdLessThanOrEqualTo(Integer value) {
            addCriterion("T_DEPARTMENT_ID <=", value, "tDepartmentId");
            return (Criteria) this;
        }

        public Criteria andTDepartmentIdIn(List<Integer> values) {
            addCriterion("T_DEPARTMENT_ID in", values, "tDepartmentId");
            return (Criteria) this;
        }

        public Criteria andTDepartmentIdNotIn(List<Integer> values) {
            addCriterion("T_DEPARTMENT_ID not in", values, "tDepartmentId");
            return (Criteria) this;
        }

        public Criteria andTDepartmentIdBetween(Integer value1, Integer value2) {
            addCriterion("T_DEPARTMENT_ID between", value1, value2, "tDepartmentId");
            return (Criteria) this;
        }

        public Criteria andTDepartmentIdNotBetween(Integer value1, Integer value2) {
            addCriterion("T_DEPARTMENT_ID not between", value1, value2, "tDepartmentId");
            return (Criteria) this;
        }

        public Criteria andNameIsNull() {
            addCriterion("NAME is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("NAME is not null");
            return (Criteria) this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("NAME =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("NAME <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("NAME >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("NAME >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("NAME <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("NAME <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("NAME like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("NAME not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("NAME in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("NAME not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("NAME between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("NAME not between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andAgeIsNull() {
            addCriterion("AGE is null");
            return (Criteria) this;
        }

        public Criteria andAgeIsNotNull() {
            addCriterion("AGE is not null");
            return (Criteria) this;
        }

        public Criteria andAgeEqualTo(Integer value) {
            addCriterion("AGE =", value, "age");
            return (Criteria) this;
        }

        public Criteria andAgeNotEqualTo(Integer value) {
            addCriterion("AGE <>", value, "age");
            return (Criteria) this;
        }

        public Criteria andAgeGreaterThan(Integer value) {
            addCriterion("AGE >", value, "age");
            return (Criteria) this;
        }

        public Criteria andAgeGreaterThanOrEqualTo(Integer value) {
            addCriterion("AGE >=", value, "age");
            return (Criteria) this;
        }

        public Criteria andAgeLessThan(Integer value) {
            addCriterion("AGE <", value, "age");
            return (Criteria) this;
        }

        public Criteria andAgeLessThanOrEqualTo(Integer value) {
            addCriterion("AGE <=", value, "age");
            return (Criteria) this;
        }

        public Criteria andAgeIn(List<Integer> values) {
            addCriterion("AGE in", values, "age");
            return (Criteria) this;
        }

        public Criteria andAgeNotIn(List<Integer> values) {
            addCriterion("AGE not in", values, "age");
            return (Criteria) this;
        }

        public Criteria andAgeBetween(Integer value1, Integer value2) {
            addCriterion("AGE between", value1, value2, "age");
            return (Criteria) this;
        }

        public Criteria andAgeNotBetween(Integer value1, Integer value2) {
            addCriterion("AGE not between", value1, value2, "age");
            return (Criteria) this;
        }

        public Criteria andBirthdayIsNull() {
            addCriterion("BIRTHDAY is null");
            return (Criteria) this;
        }

        public Criteria andBirthdayIsNotNull() {
            addCriterion("BIRTHDAY is not null");
            return (Criteria) this;
        }

        public Criteria andBirthdayEqualTo(Date value) {
            addCriterion("BIRTHDAY =", value, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayNotEqualTo(Date value) {
            addCriterion("BIRTHDAY <>", value, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayGreaterThan(Date value) {
            addCriterion("BIRTHDAY >", value, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayGreaterThanOrEqualTo(Date value) {
            addCriterion("BIRTHDAY >=", value, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayLessThan(Date value) {
            addCriterion("BIRTHDAY <", value, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayLessThanOrEqualTo(Date value) {
            addCriterion("BIRTHDAY <=", value, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayIn(List<Date> values) {
            addCriterion("BIRTHDAY in", values, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayNotIn(List<Date> values) {
            addCriterion("BIRTHDAY not in", values, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayBetween(Date value1, Date value2) {
            addCriterion("BIRTHDAY between", value1, value2, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayNotBetween(Date value1, Date value2) {
            addCriterion("BIRTHDAY not between", value1, value2, "birthday");
            return (Criteria) this;
        }

        public Criteria andRecordVersionIsNull() {
            addCriterion("RECORD_VERSION is null");
            return (Criteria) this;
        }

        public Criteria andRecordVersionIsNotNull() {
            addCriterion("RECORD_VERSION is not null");
            return (Criteria) this;
        }

        public Criteria andRecordVersionEqualTo(Integer value) {
            addCriterion("RECORD_VERSION =", value, "recordVersion");
            return (Criteria) this;
        }

        public Criteria andRecordVersionNotEqualTo(Integer value) {
            addCriterion("RECORD_VERSION <>", value, "recordVersion");
            return (Criteria) this;
        }

        public Criteria andRecordVersionGreaterThan(Integer value) {
            addCriterion("RECORD_VERSION >", value, "recordVersion");
            return (Criteria) this;
        }

        public Criteria andRecordVersionGreaterThanOrEqualTo(Integer value) {
            addCriterion("RECORD_VERSION >=", value, "recordVersion");
            return (Criteria) this;
        }

        public Criteria andRecordVersionLessThan(Integer value) {
            addCriterion("RECORD_VERSION <", value, "recordVersion");
            return (Criteria) this;
        }

        public Criteria andRecordVersionLessThanOrEqualTo(Integer value) {
            addCriterion("RECORD_VERSION <=", value, "recordVersion");
            return (Criteria) this;
        }

        public Criteria andRecordVersionIn(List<Integer> values) {
            addCriterion("RECORD_VERSION in", values, "recordVersion");
            return (Criteria) this;
        }

        public Criteria andRecordVersionNotIn(List<Integer> values) {
            addCriterion("RECORD_VERSION not in", values, "recordVersion");
            return (Criteria) this;
        }

        public Criteria andRecordVersionBetween(Integer value1, Integer value2) {
            addCriterion("RECORD_VERSION between", value1, value2, "recordVersion");
            return (Criteria) this;
        }

        public Criteria andRecordVersionNotBetween(Integer value1, Integer value2) {
            addCriterion("RECORD_VERSION not between", value1, value2, "recordVersion");
            return (Criteria) this;
        }

        public Criteria andCreateUserCodeIsNull() {
            addCriterion("CREATE_USER_CODE is null");
            return (Criteria) this;
        }

        public Criteria andCreateUserCodeIsNotNull() {
            addCriterion("CREATE_USER_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andCreateUserCodeEqualTo(String value) {
            addCriterion("CREATE_USER_CODE =", value, "createUserCode");
            return (Criteria) this;
        }

        public Criteria andCreateUserCodeNotEqualTo(String value) {
            addCriterion("CREATE_USER_CODE <>", value, "createUserCode");
            return (Criteria) this;
        }

        public Criteria andCreateUserCodeGreaterThan(String value) {
            addCriterion("CREATE_USER_CODE >", value, "createUserCode");
            return (Criteria) this;
        }

        public Criteria andCreateUserCodeGreaterThanOrEqualTo(String value) {
            addCriterion("CREATE_USER_CODE >=", value, "createUserCode");
            return (Criteria) this;
        }

        public Criteria andCreateUserCodeLessThan(String value) {
            addCriterion("CREATE_USER_CODE <", value, "createUserCode");
            return (Criteria) this;
        }

        public Criteria andCreateUserCodeLessThanOrEqualTo(String value) {
            addCriterion("CREATE_USER_CODE <=", value, "createUserCode");
            return (Criteria) this;
        }

        public Criteria andCreateUserCodeLike(String value) {
            addCriterion("CREATE_USER_CODE like", value, "createUserCode");
            return (Criteria) this;
        }

        public Criteria andCreateUserCodeNotLike(String value) {
            addCriterion("CREATE_USER_CODE not like", value, "createUserCode");
            return (Criteria) this;
        }

        public Criteria andCreateUserCodeIn(List<String> values) {
            addCriterion("CREATE_USER_CODE in", values, "createUserCode");
            return (Criteria) this;
        }

        public Criteria andCreateUserCodeNotIn(List<String> values) {
            addCriterion("CREATE_USER_CODE not in", values, "createUserCode");
            return (Criteria) this;
        }

        public Criteria andCreateUserCodeBetween(String value1, String value2) {
            addCriterion("CREATE_USER_CODE between", value1, value2, "createUserCode");
            return (Criteria) this;
        }

        public Criteria andCreateUserCodeNotBetween(String value1, String value2) {
            addCriterion("CREATE_USER_CODE not between", value1, value2, "createUserCode");
            return (Criteria) this;
        }

        public Criteria andCreateDateTimeIsNull() {
            addCriterion("CREATE_DATE_TIME is null");
            return (Criteria) this;
        }

        public Criteria andCreateDateTimeIsNotNull() {
            addCriterion("CREATE_DATE_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andCreateDateTimeEqualTo(Date value) {
            addCriterion("CREATE_DATE_TIME =", value, "createDateTime");
            return (Criteria) this;
        }

        public Criteria andCreateDateTimeNotEqualTo(Date value) {
            addCriterion("CREATE_DATE_TIME <>", value, "createDateTime");
            return (Criteria) this;
        }

        public Criteria andCreateDateTimeGreaterThan(Date value) {
            addCriterion("CREATE_DATE_TIME >", value, "createDateTime");
            return (Criteria) this;
        }

        public Criteria andCreateDateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("CREATE_DATE_TIME >=", value, "createDateTime");
            return (Criteria) this;
        }

        public Criteria andCreateDateTimeLessThan(Date value) {
            addCriterion("CREATE_DATE_TIME <", value, "createDateTime");
            return (Criteria) this;
        }

        public Criteria andCreateDateTimeLessThanOrEqualTo(Date value) {
            addCriterion("CREATE_DATE_TIME <=", value, "createDateTime");
            return (Criteria) this;
        }

        public Criteria andCreateDateTimeIn(List<Date> values) {
            addCriterion("CREATE_DATE_TIME in", values, "createDateTime");
            return (Criteria) this;
        }

        public Criteria andCreateDateTimeNotIn(List<Date> values) {
            addCriterion("CREATE_DATE_TIME not in", values, "createDateTime");
            return (Criteria) this;
        }

        public Criteria andCreateDateTimeBetween(Date value1, Date value2) {
            addCriterion("CREATE_DATE_TIME between", value1, value2, "createDateTime");
            return (Criteria) this;
        }

        public Criteria andCreateDateTimeNotBetween(Date value1, Date value2) {
            addCriterion("CREATE_DATE_TIME not between", value1, value2, "createDateTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeZoneIsNull() {
            addCriterion("CREATE_TIME_ZONE is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeZoneIsNotNull() {
            addCriterion("CREATE_TIME_ZONE is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeZoneEqualTo(String value) {
            addCriterion("CREATE_TIME_ZONE =", value, "createTimeZone");
            return (Criteria) this;
        }

        public Criteria andCreateTimeZoneNotEqualTo(String value) {
            addCriterion("CREATE_TIME_ZONE <>", value, "createTimeZone");
            return (Criteria) this;
        }

        public Criteria andCreateTimeZoneGreaterThan(String value) {
            addCriterion("CREATE_TIME_ZONE >", value, "createTimeZone");
            return (Criteria) this;
        }

        public Criteria andCreateTimeZoneGreaterThanOrEqualTo(String value) {
            addCriterion("CREATE_TIME_ZONE >=", value, "createTimeZone");
            return (Criteria) this;
        }

        public Criteria andCreateTimeZoneLessThan(String value) {
            addCriterion("CREATE_TIME_ZONE <", value, "createTimeZone");
            return (Criteria) this;
        }

        public Criteria andCreateTimeZoneLessThanOrEqualTo(String value) {
            addCriterion("CREATE_TIME_ZONE <=", value, "createTimeZone");
            return (Criteria) this;
        }

        public Criteria andCreateTimeZoneLike(String value) {
            addCriterion("CREATE_TIME_ZONE like", value, "createTimeZone");
            return (Criteria) this;
        }

        public Criteria andCreateTimeZoneNotLike(String value) {
            addCriterion("CREATE_TIME_ZONE not like", value, "createTimeZone");
            return (Criteria) this;
        }

        public Criteria andCreateTimeZoneIn(List<String> values) {
            addCriterion("CREATE_TIME_ZONE in", values, "createTimeZone");
            return (Criteria) this;
        }

        public Criteria andCreateTimeZoneNotIn(List<String> values) {
            addCriterion("CREATE_TIME_ZONE not in", values, "createTimeZone");
            return (Criteria) this;
        }

        public Criteria andCreateTimeZoneBetween(String value1, String value2) {
            addCriterion("CREATE_TIME_ZONE between", value1, value2, "createTimeZone");
            return (Criteria) this;
        }

        public Criteria andCreateTimeZoneNotBetween(String value1, String value2) {
            addCriterion("CREATE_TIME_ZONE not between", value1, value2, "createTimeZone");
            return (Criteria) this;
        }

        public Criteria andUpdateUserCodeIsNull() {
            addCriterion("UPDATE_USER_CODE is null");
            return (Criteria) this;
        }

        public Criteria andUpdateUserCodeIsNotNull() {
            addCriterion("UPDATE_USER_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateUserCodeEqualTo(String value) {
            addCriterion("UPDATE_USER_CODE =", value, "updateUserCode");
            return (Criteria) this;
        }

        public Criteria andUpdateUserCodeNotEqualTo(String value) {
            addCriterion("UPDATE_USER_CODE <>", value, "updateUserCode");
            return (Criteria) this;
        }

        public Criteria andUpdateUserCodeGreaterThan(String value) {
            addCriterion("UPDATE_USER_CODE >", value, "updateUserCode");
            return (Criteria) this;
        }

        public Criteria andUpdateUserCodeGreaterThanOrEqualTo(String value) {
            addCriterion("UPDATE_USER_CODE >=", value, "updateUserCode");
            return (Criteria) this;
        }

        public Criteria andUpdateUserCodeLessThan(String value) {
            addCriterion("UPDATE_USER_CODE <", value, "updateUserCode");
            return (Criteria) this;
        }

        public Criteria andUpdateUserCodeLessThanOrEqualTo(String value) {
            addCriterion("UPDATE_USER_CODE <=", value, "updateUserCode");
            return (Criteria) this;
        }

        public Criteria andUpdateUserCodeLike(String value) {
            addCriterion("UPDATE_USER_CODE like", value, "updateUserCode");
            return (Criteria) this;
        }

        public Criteria andUpdateUserCodeNotLike(String value) {
            addCriterion("UPDATE_USER_CODE not like", value, "updateUserCode");
            return (Criteria) this;
        }

        public Criteria andUpdateUserCodeIn(List<String> values) {
            addCriterion("UPDATE_USER_CODE in", values, "updateUserCode");
            return (Criteria) this;
        }

        public Criteria andUpdateUserCodeNotIn(List<String> values) {
            addCriterion("UPDATE_USER_CODE not in", values, "updateUserCode");
            return (Criteria) this;
        }

        public Criteria andUpdateUserCodeBetween(String value1, String value2) {
            addCriterion("UPDATE_USER_CODE between", value1, value2, "updateUserCode");
            return (Criteria) this;
        }

        public Criteria andUpdateUserCodeNotBetween(String value1, String value2) {
            addCriterion("UPDATE_USER_CODE not between", value1, value2, "updateUserCode");
            return (Criteria) this;
        }

        public Criteria andUpdateDateTimeIsNull() {
            addCriterion("UPDATE_DATE_TIME is null");
            return (Criteria) this;
        }

        public Criteria andUpdateDateTimeIsNotNull() {
            addCriterion("UPDATE_DATE_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateDateTimeEqualTo(Date value) {
            addCriterion("UPDATE_DATE_TIME =", value, "updateDateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateDateTimeNotEqualTo(Date value) {
            addCriterion("UPDATE_DATE_TIME <>", value, "updateDateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateDateTimeGreaterThan(Date value) {
            addCriterion("UPDATE_DATE_TIME >", value, "updateDateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateDateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("UPDATE_DATE_TIME >=", value, "updateDateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateDateTimeLessThan(Date value) {
            addCriterion("UPDATE_DATE_TIME <", value, "updateDateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateDateTimeLessThanOrEqualTo(Date value) {
            addCriterion("UPDATE_DATE_TIME <=", value, "updateDateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateDateTimeIn(List<Date> values) {
            addCriterion("UPDATE_DATE_TIME in", values, "updateDateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateDateTimeNotIn(List<Date> values) {
            addCriterion("UPDATE_DATE_TIME not in", values, "updateDateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateDateTimeBetween(Date value1, Date value2) {
            addCriterion("UPDATE_DATE_TIME between", value1, value2, "updateDateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateDateTimeNotBetween(Date value1, Date value2) {
            addCriterion("UPDATE_DATE_TIME not between", value1, value2, "updateDateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeZoneIsNull() {
            addCriterion("UPDATE_TIME_ZONE is null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeZoneIsNotNull() {
            addCriterion("UPDATE_TIME_ZONE is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeZoneEqualTo(String value) {
            addCriterion("UPDATE_TIME_ZONE =", value, "updateTimeZone");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeZoneNotEqualTo(String value) {
            addCriterion("UPDATE_TIME_ZONE <>", value, "updateTimeZone");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeZoneGreaterThan(String value) {
            addCriterion("UPDATE_TIME_ZONE >", value, "updateTimeZone");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeZoneGreaterThanOrEqualTo(String value) {
            addCriterion("UPDATE_TIME_ZONE >=", value, "updateTimeZone");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeZoneLessThan(String value) {
            addCriterion("UPDATE_TIME_ZONE <", value, "updateTimeZone");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeZoneLessThanOrEqualTo(String value) {
            addCriterion("UPDATE_TIME_ZONE <=", value, "updateTimeZone");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeZoneLike(String value) {
            addCriterion("UPDATE_TIME_ZONE like", value, "updateTimeZone");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeZoneNotLike(String value) {
            addCriterion("UPDATE_TIME_ZONE not like", value, "updateTimeZone");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeZoneIn(List<String> values) {
            addCriterion("UPDATE_TIME_ZONE in", values, "updateTimeZone");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeZoneNotIn(List<String> values) {
            addCriterion("UPDATE_TIME_ZONE not in", values, "updateTimeZone");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeZoneBetween(String value1, String value2) {
            addCriterion("UPDATE_TIME_ZONE between", value1, value2, "updateTimeZone");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeZoneNotBetween(String value1, String value2) {
            addCriterion("UPDATE_TIME_ZONE not between", value1, value2, "updateTimeZone");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}