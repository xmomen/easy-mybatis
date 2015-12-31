/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2015/1/16 1:33:49                            */
/*==============================================================*/


DROP TABLE IF EXISTS T_DEPARTMENT;

DROP INDEX EMP_DEPART_FK ON T_EMPLOYEE;

DROP TABLE IF EXISTS T_EMPLOYEE;

/*==============================================================*/
/* Table: T_DEPARTMENT                                          */
/*==============================================================*/
CREATE TABLE T_DEPARTMENT
(
   T_DEPARTMENT_ID      INT NOT NULL AUTO_INCREMENT COMMENT '主键',
   NAME                 VARCHAR(50) COMMENT '部门名称',
   DESCRIPTION          VARCHAR(200) COMMENT '描述',
   CREATE_USER_CODE     VARCHAR(32) COMMENT '创建人',
   CREATE_DATE_TIME     DATETIME COMMENT '创建日期',
   CREATE_TIME_ZONE     VARCHAR(4) COMMENT '创建人所属时区',
   RECORD_VERSION       INT DEFAULT 0 COMMENT '数据版本号',
   UPDATE_USER_CODE     VARCHAR(32) COMMENT '修改人',
   UPDATE_DATE_TIME     DATETIME COMMENT '修改日期',
   UPDATE_TIME_ZONE     VARCHAR(4) COMMENT '修改人所属时区',
   PRIMARY KEY (T_DEPARTMENT_ID)
);

ALTER TABLE T_DEPARTMENT COMMENT '部门表';

/*==============================================================*/
/* Table: T_EMPLOYEE                                            */
/*==============================================================*/
CREATE TABLE T_EMPLOYEE
(
   T_EMPLOYEE_ID        INT NOT NULL AUTO_INCREMENT COMMENT '主键',
   T_DEPARTMENT_ID      INT COMMENT '主键',
   NAME                 VARCHAR(20) COMMENT '员工名称',
   AGE                  INT COMMENT '年龄',
   BIRTHDAY             DATETIME COMMENT '出生日期',
   RECORD_VERSION       INT DEFAULT 0 COMMENT '数据版本号',
   CREATE_USER_CODE     VARCHAR(32) COMMENT '创建人',
   CREATE_DATE_TIME     DATETIME COMMENT '创建日期',
   CREATE_TIME_ZONE     VARCHAR(4) COMMENT '创建人所属时区',
   UPDATE_USER_CODE     VARCHAR(32) COMMENT '修改人',
   UPDATE_DATE_TIME     DATETIME COMMENT '修改日期',
   UPDATE_TIME_ZONE     VARCHAR(4) COMMENT '修改人所属时区',
   PRIMARY KEY (T_EMPLOYEE_ID)
);

ALTER TABLE T_EMPLOYEE COMMENT '员工表';

/*==============================================================*/
/* Index: EMP_DEPART_FK                                         */
/*==============================================================*/
CREATE INDEX EMP_DEPART_FK ON T_EMPLOYEE
(
   T_DEPARTMENT_ID
);

