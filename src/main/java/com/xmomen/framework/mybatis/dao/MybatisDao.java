package com.xmomen.framework.mybatis.dao;

import com.xmomen.framework.mybatis.model.BaseMybatisExample;
import com.xmomen.framework.mybatis.model.BaseMybatisModel;
import com.xmomen.framework.mybatis.page.Page;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.SqlSession;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * mybatisDao通用接口
 */
public interface MybatisDao {

	/**
	 * 获取mybatis原生接口
	 * @return
	 */
	public SqlSession getSqlSessionTemplate();

	/**
	 * 获取最新插入的主键
	 * @return
	 */
	@Deprecated
	public Serializable getPrimaryKey();

	/**
	 * 获取数据库系统时间对应GMT
	 * @return
	 */
	@Deprecated
	public Date getGMTDate();

	/**
	 * 获取数据库系统时间
	 * @return
	 */
	public Date getSysdate();

	/**
	 * 统计匹配model的总记录数
	 * @param model
	 * @param <MODEL>
	 * @return
	 */
	public <MODEL extends BaseMybatisModel> int countByModel(MODEL model);

	/**
	 * 统计匹配example的总记录数
	 * @param example
	 * @param <EXAMPLE>
	 * @return
	 */
	public <EXAMPLE extends BaseMybatisExample> int countByExample(EXAMPLE example);

	/**
	 * 统计匹配的总记录数
	 * @param mapperId
	 * @return
	 */
	public int count(String mapperId);

	/**
	 * 统计匹配的总记录数
	 * @param mapperId
	 * @param object
	 * @return
	 */
	public int count(String mapperId, Object object);

	/**
	 * 根据model类和主键判断是否存在的匹配数据
	 * @param paramClass
	 * @param primaryKey
	 * @param <MODEL>
	 * @return
	 */
	public <MODEL extends BaseMybatisModel> boolean existByPrimaryKey(
			@Param(BaseMybatisModel.PARAMETER_MODEL_CLASS) Class<MODEL> paramClass,
			@Param(BaseMybatisModel.PRIMARY_KEY) Serializable primaryKey);

	/**
	 * 根据model判断是否存在匹配的数据
	 * @param model
	 * @param <MODEL>
	 * @return
	 */
	public <MODEL extends BaseMybatisModel> boolean existByModel(MODEL model);

	/**
	 * 根据example判断是否存在匹配的数据
	 * @param example
	 * @param <MODEL>
	 * @return
	 */
	public <MODEL extends BaseMybatisExample> boolean existByExample(MODEL example);

	/**
	 * 根据 SQL 判断是否存在匹配的数据
	 * @return
	 */
	public boolean exist(String mapperId);

	/**
	 * 根据 SQL 判断是否存在匹配的数据
	 * @param mapperId
	 * @param object
	 * @return
	 */
	public boolean exist(String mapperId, Object object);

	/**
	 * 根据model类和主键删除匹配的数据，并返回影响行数
	 * @param paramClass
	 * @param primaryKey
	 * @param <MODEL>
	 * @return
	 */
	public <MODEL extends BaseMybatisModel> int deleteByPrimaryKey(
			@Param(BaseMybatisModel.PARAMETER_MODEL_CLASS) Class<MODEL> paramClass,
			@Param(BaseMybatisModel.PRIMARY_KEY) Serializable primaryKey);

	/**
	 * 根据model删除匹配的数据，并返回影响行数
	 * 注：执行删除操作的model对象中主键值不能为空，为空则可能造成批量删除的情况，已造成数据丢失
	 * @param model
	 * @param <MODEL>
	 * @return
	 */
	public <MODEL extends BaseMybatisModel> int delete(MODEL model);

	/**
	 * 根据model类和主键集合值删除匹配的数据，并返回影响行数
	 * @param paramClass
	 * @param ids
	 * @param <MODEL>
	 * @return
	 */
	public <MODEL extends BaseMybatisModel> int deleteAllByPrimaryKey(
			Class<MODEL> paramClass,
			Collection<? extends Serializable> ids);

	/**
	 * 根据model集合删除匹配的数据，并返回影响行数
	 * @param models
	 * @param <MODEL>
	 * @return
	 */
	public <MODEL extends BaseMybatisModel> int deleteAllByModel(Collection<MODEL> models);

	/**
	 * 根据example删除匹配的数据，并返回影响行数
	 * @param example
	 * @param <EXAMPLE>
	 * @return
	 */
	public <EXAMPLE extends BaseMybatisExample> int deleteByExample(EXAMPLE example);

	/**
	 * 根据model新增记录，并返回影响行数
	 * @param model
	 * @param <MODEL>
	 * @return
	 */
	public <MODEL extends BaseMybatisModel> int insert(MODEL model);

	/**
	 * 根据model新增记录，并返回新增后的数据
	 * @param model
	 * @param <MODEL>
	 * @return
	 */
	public <MODEL extends BaseMybatisModel> MODEL insertByModel(MODEL model);

	/**
	 * 根据model类和主键查询匹配的数据
	 * @param paramClass
	 * @param primaryKey
	 * @param <MODEL>
	 * @return
	 */
	public <MODEL extends BaseMybatisModel> MODEL selectByPrimaryKey(
			@Param(BaseMybatisModel.PARAMETER_MODEL_CLASS) Class<MODEL> paramClass,
			@Param(BaseMybatisModel.PRIMARY_KEY) Serializable primaryKey);

	/**
	 * 根据model查询匹配的数据结果集
	 * @param model
	 * @param <MODEL>
	 * @return
	 */
	public <MODEL extends BaseMybatisModel> List<MODEL> selectByModel(MODEL model);

	/**
	 * 根据model查询匹配的唯一数据（出现多个则报异常）
	 * @param model
	 * @param <MODEL>
	 * @return
	 */
	public <MODEL extends BaseMybatisModel> MODEL selectOneByModel(MODEL model);

	/**
	 * 根据example查询匹配的结果集
	 * @param example
	 * @param <MODEL>
	 * @param <EXAMPLE>
	 * @return
	 */
	public <MODEL extends BaseMybatisModel, EXAMPLE extends BaseMybatisExample> List<MODEL> selectByExample(EXAMPLE example);

	/**
	 * 根据example查询匹配的唯一数据（出现多个则报异常）
	 * @param example
	 * @param <MODEL>
	 * @param <EXAMPLE>
	 * @return
	 */
	public <MODEL extends BaseMybatisModel, EXAMPLE extends BaseMybatisExample> MODEL selectOneByExample(EXAMPLE example);

	/**
	 * 根据model进行单表分页查询
	 * 注：example对象的pageInfo属性对象不能为null，且pageInfo对象的pageSize，pageNum必须大于0
	 * @param model
	 * @param <MODEL>
	 * @return
	 */
	public <MODEL extends BaseMybatisModel> Page<MODEL> selectPageByModel(MODEL model, Integer pageSize, Integer pageNum);

	/**
	 * 根据Example进行单表分页查询
	 * 注：example对象的pageInfo属性对象不能为null，且pageInfo对象的pageSize，pageNum必须大于0
	 * @param model
	 * @param <MODEL>
	 * @param <EXAMPLE>
	 * @return
	 */
	public <MODEL extends BaseMybatisModel, EXAMPLE extends BaseMybatisExample> Page<MODEL> selectPageByExample(EXAMPLE model, Integer pageSize, Integer pageNum);

	/**
	 * 根据Object进行多表分页查询
	 * 注：pageInfo属性对象不能为null，且pageInfo对象的pageSize，pageNum必须大于0
	 * @param mapperId
	 * @param object
	 * @param pageSize
	 * @param pageNum
	 * @return
	 */
	public Page<?> selectPage(String mapperId, Object object, Integer pageSize, Integer pageNum);

	/**
	 * 根据model更新匹配数据，并返回影响行数（必须包含主键，若字段中存在版本号则条件中必须包含版本号）
	 * 注：包含主键的原因是为了避免因误操作的原因批量更新数据，导致数据大量丢失的情况
	 * @param model
	 * @param <MODEL>
	 * @return
	 */
	public <MODEL extends BaseMybatisModel> int update(MODEL model);

	/**
	 * 根据model更新匹配数据，并返回更新后的数据（必须包含主键，若字段中存在版本号则条件中必须包含版本号）
	 * 注：包含主键的原因是为了避免因误操作的原因批量更新数据，导致数据大量丢失的情况
	 * @param model
	 * @param <MODEL>
	 * @return
	 */
	public <MODEL extends BaseMybatisModel> MODEL updateByModel(MODEL model);

	/**
	 * 根据example条件将model更新到数据库中（此操作很可能会导致批量更新的情况出现，建议在完善之前废弃）
	 * @param model
	 * @param example
	 * @param <MODEL>
	 * @param <EXAMPLE>
	 * @return
	 */
	@Deprecated
	public <MODEL extends BaseMybatisModel, EXAMPLE extends BaseMybatisExample> int updateByExampleSelective(
			@Param("record") MODEL model,
			@Param("example") EXAMPLE example);

	/**
	 * 根据example条件将model更新到数据库中
	 * 注：1.单条数据操作，所更新条件所查询的数据必须为1，否则事务失败
	 * 	   2.此方法主要用于通过业务主键更新数据
	 * @param model
	 * @param example
	 * @param <MODEL>
	 * @param <EXAMPLE>
	 * @return
	 */
	public <MODEL extends BaseMybatisModel, EXAMPLE extends BaseMybatisExample> int updateOneByExampleSelective(
			@Param("record") MODEL model,
			@Param("example") EXAMPLE example);

	/**
	 * 根据model删除,更新或新增数据，并返回受影响行数
	 * 注：	1.根据rowState判断是否为执行删除/更新/新增操作
	 * 		2.若主键有值，且数据库中存在匹配数据则进行更新操作  TODO 此处需要考虑是否根据版本号进行匹配
	 * 		2.若主键值为空，则进行新增操作
	 * @param model
	 * @param <MODEL>
	 * @return
	 */
	public <MODEL extends BaseMybatisModel> int save(MODEL model);

	/**
	 * 根据model删除，更新或新增数据，并返回更新后或新增的数据
	 * 注：	1.根据rowState判断是否为执行删除/更新/新增操作
	 * 		2.若主键有值，且数据库中存在匹配数据则进行更新操作  TODO 此处需要考虑是否根据版本号进行匹配
	 * 		2.若主键值为空，则进行新增操作
	 * @param model
	 * @param <MODEL>
	 * @return
	 */
	public <MODEL extends BaseMybatisModel> MODEL saveByModel(MODEL model);

	/**
	 * 根据model批量删除，更新或新增数据，并返回更新后或新增的数据
	 * 注：	1.根据rowState判断是否为执行删除/更新/新增操作
	 * 		2.若主键有值，且数据库中存在匹配数据则进行更新操作  TODO 此处需要考虑是否根据版本号进行匹配
	 * 		2.若主键值为空，则进行新增操作
	 * @param models
	 * @param <MODEL>
	 * @return
	 */
	public <MODEL extends BaseMybatisModel> List<MODEL> saveAllByModel(Collection<MODEL> models);

}
