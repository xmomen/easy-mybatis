package com.udfex.framework.mybatis.provider;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.jdbc.SqlBuilder;

import com.udfex.framework.log.Logger;
import com.udfex.framework.log.LoggerFactory;
import com.udfex.framework.mybatis.model.BaseMybatisModel;
import com.udfex.framework.mybatis.support.MybatisModelFieldSpec;
import com.udfex.framework.mybatis.utils.ModelUtils;

public class BaseMapperProvider {

    Logger log = LoggerFactory.getLogger(BaseMapperProvider.class);

    public <Model extends BaseMybatisModel> String countByModel(Model param){
        Class modelClass = (Class) param.getClass();
        SqlBuilder.BEGIN();
        SqlBuilder.SELECT("count(1)");
        SqlBuilder.FROM(ModelUtils.getTableName(modelClass));
        List<MybatisModelFieldSpec> mybatisModelFieldSpecList = BaseMybatisModel.getModelFieldSpec(modelClass);
        int i = 0;
        for (Iterator<MybatisModelFieldSpec> iterator = mybatisModelFieldSpecList.iterator(); iterator
                .hasNext();) {
            MybatisModelFieldSpec currentModelFieldSpec = (MybatisModelFieldSpec) iterator.next();
            if(param.validFields().contains(currentModelFieldSpec.getFieldName())){
                if(i==0){
                    SqlBuilder.WHERE(buildSqlByFieldSpec(currentModelFieldSpec));
                }else{
                    SqlBuilder.AND();
                    SqlBuilder.WHERE(buildSqlByFieldSpec(currentModelFieldSpec));
                }
                i++;
            }
        }
        return SqlBuilder.SQL();
    }

    public <Model extends BaseMybatisModel> String existByPrimaryKey(Map<String, Object> param){
        Class modelClass = (Class) param.get(BaseMybatisModel.PARAMETER_MODEL_CLASS);
        MybatisModelFieldSpec primaryKeyFieldSpec =
                BaseMybatisModel.getFieldSpecByFieldName(modelClass,ModelUtils.getPrimaryKeyFieldName(modelClass));
        SqlBuilder.BEGIN();
        SqlBuilder.SELECT("count(1)");
        SqlBuilder.FROM(ModelUtils.getTableName(modelClass));
        SqlBuilder.WHERE(buildSqlByPrimaryKeyFieldSpec(primaryKeyFieldSpec, param));

        return SqlBuilder.SQL();
    }

//    public <Model extends BaseMybatisModel> String insert(Model param){
//        Class modelClass = (Class) param.getClass();
//
//        String primaryKeyFieldName = ModelUtils.getPrimaryKeyFieldName(modelClass);
//        String recVersionFieldName = ModelUtils.getRecVersionFieldName(modelClass);
//        SqlBuilder.BEGIN();
//        SqlBuilder.INSERT_INTO(ModelUtils.getTableName(modelClass));
//        List<MybatisModelFieldSpec> mybatisModelFieldSpecList = BaseMybatisModel.getModelFieldSpec(modelClass);
//        for (Iterator<MybatisModelFieldSpec> iterator = mybatisModelFieldSpecList.iterator(); iterator
//                .hasNext();) {
//            MybatisModelFieldSpec currentModelFieldSpec = (MybatisModelFieldSpec) iterator.next();
//            //只更新值不为空的有效字段
//            if(param.validFields().contains(currentModelFieldSpec.getFieldName())){
//                SqlBuilder.VALUES(currentModelFieldSpec.getColumnName(),
//                        "#{" + currentModelFieldSpec.getFieldName() + ",jdbcType=" + currentModelFieldSpec.getJdbcType() + "}");
//            }
//        }
//        return SqlBuilder.SQL();
//    }

    public <Model extends BaseMybatisModel> String selectByPrimaryKey(Map<String, Object> param){
        Class modelClass = (Class) param.get(BaseMybatisModel.PARAMETER_MODEL_CLASS);
        MybatisModelFieldSpec primaryKeyFieldSpec =
                BaseMybatisModel.getFieldSpecByFieldName(modelClass,ModelUtils.getPrimaryKeyFieldName(modelClass));
        SqlBuilder.BEGIN();
        SqlBuilder.SELECT("*");
        SqlBuilder.FROM(ModelUtils.getTableName(modelClass));
        SqlBuilder.WHERE(buildSqlByPrimaryKeyFieldSpec(primaryKeyFieldSpec, param));
        return SqlBuilder.SQL();
    }

    public <Model extends BaseMybatisModel> String selectByModel(Model param){
        Class modelClass = (Class) param.getClass();
        SqlBuilder.BEGIN();
        SqlBuilder.SELECT("*");
        SqlBuilder.FROM(ModelUtils.getTableName(modelClass));
        List<MybatisModelFieldSpec> mybatisModelFieldSpecList = BaseMybatisModel.getModelFieldSpec(modelClass);
        int i = 0;
        for (Iterator<MybatisModelFieldSpec> iterator = mybatisModelFieldSpecList.iterator(); iterator
                .hasNext();) {
            MybatisModelFieldSpec currentModelFieldSpec = (MybatisModelFieldSpec) iterator.next();
            if(param.validFields().contains(currentModelFieldSpec.getFieldName())){
                if(i==0){
                    SqlBuilder.WHERE(buildSqlByFieldSpec(currentModelFieldSpec));
                }else{
                    SqlBuilder.AND();
                    SqlBuilder.WHERE(buildSqlByFieldSpec(currentModelFieldSpec));
                }
                i++;
            }
        }
        return SqlBuilder.SQL();
    }

    public <Model extends BaseMybatisModel> String deleteByPrimaryKey(Map<String, Object> param){
        Class modelClass = (Class) param.get(BaseMybatisModel.PARAMETER_MODEL_CLASS);
        MybatisModelFieldSpec primaryKeyFieldSpec =
                BaseMybatisModel.getFieldSpecByFieldName(modelClass,ModelUtils.getPrimaryKeyFieldName(modelClass));
        SqlBuilder.BEGIN();
        SqlBuilder.DELETE_FROM(ModelUtils.getTableName(modelClass));
        SqlBuilder.WHERE(buildSqlByPrimaryKeyFieldSpec(primaryKeyFieldSpec, param));
        return SqlBuilder.SQL();
    }

    public <Model extends BaseMybatisModel> String delete(Model param){
        Class modelClass = (Class) param.getClass();
        String primaryKeyFieldName = ModelUtils.getPrimaryKeyFieldName(modelClass);
        String recVersionFieldName = ModelUtils.getRecVersionFieldName(modelClass);
        MybatisModelFieldSpec recVerFieldSpec = null;
        MybatisModelFieldSpec primaryKeyFieldSpec = null;
        SqlBuilder.BEGIN();
        SqlBuilder.DELETE_FROM(ModelUtils.getTableName(modelClass));
        int i = 0;
        List<MybatisModelFieldSpec> mybatisModelFieldSpecList = BaseMybatisModel.getModelFieldSpec(modelClass);
        for (Iterator<MybatisModelFieldSpec> iterator = mybatisModelFieldSpecList.iterator(); iterator
                .hasNext();) {
            MybatisModelFieldSpec currentModelFieldSpec = (MybatisModelFieldSpec) iterator.next();
            if(primaryKeyFieldName.equals(currentModelFieldSpec.getFieldName())){
                primaryKeyFieldSpec = currentModelFieldSpec;
                continue;
            }
            if(recVersionFieldName.equals(currentModelFieldSpec.getFieldName())){
                recVerFieldSpec = currentModelFieldSpec;
                continue;
            }
            //只更新值不为空的有效字段，且排除主键和版本字段
            if(param.validFields().contains(currentModelFieldSpec.getFieldName())){
                if(i > 0){
                    SqlBuilder.AND();
                }
                SqlBuilder.WHERE(buildSqlByFieldSpec(currentModelFieldSpec));
                i++;
            }
        }
        SqlBuilder.AND();
        //添加主键过滤条件
        SqlBuilder.WHERE(buildSqlByFieldSpec(primaryKeyFieldSpec));
        //如存在版本号字段且字段值不为null则加上数据版本条件
        Integer recVerValue = (Integer) ModelUtils.getFieldValueByFieldName(param, recVersionFieldName);
        if(null != recVerFieldSpec && recVerValue != null){
            SqlBuilder.AND();
            SqlBuilder.WHERE(buildSqlByFieldSpec(recVerFieldSpec));
        }
        return SqlBuilder.SQL();
    }

    public <Model extends BaseMybatisModel> String update(Model param){
        Class modelClass = (Class) param.getClass();

        String primaryKeyFieldName = ModelUtils.getPrimaryKeyFieldName(modelClass);
        String recVersionFieldName = ModelUtils.getRecVersionFieldName(modelClass);
        MybatisModelFieldSpec recVerFieldSpec = null;
        MybatisModelFieldSpec primaryKeyFieldSpec = null;

        SqlBuilder.BEGIN();
        SqlBuilder.UPDATE(ModelUtils.getTableName(modelClass));
        List<MybatisModelFieldSpec> mybatisModelFieldSpecList = BaseMybatisModel.getModelFieldSpec(modelClass);
        for (Iterator<MybatisModelFieldSpec> iterator = mybatisModelFieldSpecList.iterator(); iterator
                     .hasNext();) {
            MybatisModelFieldSpec currentModelFieldSpec = (MybatisModelFieldSpec) iterator.next();
            if(primaryKeyFieldName != null && primaryKeyFieldName.equals(currentModelFieldSpec.getFieldName())){
                primaryKeyFieldSpec = currentModelFieldSpec;
                continue;
            }
            if(recVersionFieldName != null && recVersionFieldName.equals(currentModelFieldSpec.getFieldName())){
                recVerFieldSpec = currentModelFieldSpec;
                continue;
            }
            //只更新值不为空的有效字段，且排除主键和版本字段
            if(param.validFields().contains(currentModelFieldSpec.getFieldName())){
                SqlBuilder.SET(buildSqlByFieldSpec(currentModelFieldSpec));
            }
        }

        //若存在数据版本字段，且数据版本字段值不为null，则版本数自增+1
		/*Integer recVerValue = (Integer) ModelUtils.getFieldValueByFieldName(param, recVersionFieldName);
		if(null != recVerFieldSpec && recVerValue != null){
		    SqlBuilder.SET(buildSqlByFieldSpec(recVerFieldSpec) + " + 1");
		}*/
        //添加主键过滤条件
        SqlBuilder.WHERE(buildSqlByFieldSpec(primaryKeyFieldSpec));
        //如存在版本号字段，且数据版本字段值不为null，则加上数据版本字段条件
		/*if (null != recVerFieldSpec && recVerValue != null) {
		    SqlBuilder.AND();
		    SqlBuilder.WHERE(buildSqlByFieldSpec(recVerFieldSpec));
		}*/
        return SqlBuilder.SQL();
    }

    private static String buildSqlByFieldSpec(MybatisModelFieldSpec modelFieldSpec){
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(modelFieldSpec.getColumnName());
        stringBuffer.append(" = #{");
        stringBuffer.append(modelFieldSpec.getFieldName() + ",jdbcType=" + modelFieldSpec.getJdbcType());
        stringBuffer.append("}");
        return stringBuffer.toString();
    }

    private static String buildSqlByPrimaryKeyFieldSpec(MybatisModelFieldSpec modelFieldSpec, Map paramMap){
        String primaryKey = modelFieldSpec.getFieldName();
        //添加与之匹配的主键映射
        paramMap.put(primaryKey, paramMap.get(BaseMybatisModel.PRIMARY_KEY));
        //移除框架定义的主键映射
        paramMap.remove(BaseMybatisModel.PRIMARY_KEY);
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(modelFieldSpec.getColumnName());
        stringBuffer.append(" = #{");
        stringBuffer.append(primaryKey + ",jdbcType=" + modelFieldSpec.getJdbcType());
        stringBuffer.append("}");
        return stringBuffer.toString();
    }
}
