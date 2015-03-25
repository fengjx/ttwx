package com.fjx.common.framework.base.service;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.springframework.dao.DataAccessException;

import com.fjx.common.framework.system.pagination.Pagination;


/**
 * 通用service
 * @author fengjx xd-fjx@qq.com
 * @date 2014年11月2日
 * @param <T>
 */
public interface IBaseAbstractService<T> {
	
	/**
	 * 保存泛型指向的实体
	 * @param entity
	 */
	public <X> Serializable save (X entity) ;

	/**
	 * 批量保存泛型指向的实体（只适合插入少量数据）
	 * @param entitys
	 * @param <X>
	 * @return
	 */
	public <X> void saveList (List<X> entitys);

	/**
	 * 新增或保存实体
	 * @param entity
	 */
	public <X> void saveOrUpdate(X entity) ;
	
	/**
	 * 删除记录
	 * @param 
	 */
	public void delete (Serializable pk) ;
	
	/**
	 * 删除记录
	 * @param 
	 */
	public <X> void delete (Class<X> entityClass, Serializable pk) ;
	
	/**
	 * 删除记录
	 * @param 
	 */
	public <X> void delete (X entity) ;
	/**
	 * 
	 * @param entities
	 * @throws DataAccessException
	 */
	public <X> void deleteAll(Collection<X> entities) ;
	
	/**
	 * 更新单条记录
	 * @param entity
	 */
	public <X> void update (X entity) ;
	
	/**
	 * 
	 * @param id
	 * @return 泛型指向之外的实
	 * @throws HibernateException
	 * @throws SQLException
	 */
	public T load(Serializable id);
	
	/**
	 * 
	 * @param entityClass
	 * @param id
	 * @return 泛型指向之外的实
	 * @throws HibernateException
	 * @throws SQLException
	 */
	public <X> X load(Class<X> entityClass, Serializable id);
	
	/**
	 * 根据hql查询单条记录
	 * @param hql
	 * @param parameters
	 * @return 返回 X可以是实体或者Map
	 */
	public <X> X findOneByHql(String hql, Object... parameters);
	
	/**
	 * 根据hql查询单条记录
	 * @param sql
	 * @param parameters
	 * @return 返回map
	 */
	public Map findOneBySql(String sql, Object... parameters);
	
	/**
	 * 查询列表记录，根据泛型查询该对象纪录列表
	 * @param entityClass
	 * @return
	 */
	public <X> List<X> findList(Class<X> entityClass);
	
	/**
	 * 查询列表记录，根据泛型查询该对象纪录列表
	 */
	public List<T> findList();
	
	/**
	 * 查询列表记录
	 * @param hql
	 * @param parameters
	 * @return 返回列表数据 X 可以是实体对象或者Map类型
	 */
	public <X> List<X> findListByHql (String hql, Object... parameters);
	
	/**
	 * 根据sql查询多条记录
	 * @param parameters
	 * @return 
	 * @throws HibernateException
	 * @throws SQLException
	 */
	public List<Map<String, Object>> findListMapBySql(String sql, Object... parameters);
	
	/**
	 * 将查询单条记�?
	 * @param hql
	 * @param parameters
	 * @return	
	 */
	public <X> X findUniqueByHql(String hql, Object... parameters);
	
	/**
	 * 将查询单条记�?
	 * @param sql
	 * @param parameters
	 * @return	
	 */
	public <X> X findUniqueBySql(String sql, Object... parameters);
	
	/**
	 * 获取总记录数
	 * @param ql hql / sql
	 * @param parameters
	 * @return
	 */
	public int getCount(String ql, boolean isHql, Object... parameters);

	/**
	 * 分页查询，根据泛型查询该对象纪录列表
	 *
	 * @param entityClass
	 * @param <X>
	 * @return
	 */
	public <X> Pagination<X> page(Class<X> entityClass) ;
	
	/**
	 * 分页查询，根据泛型查询该对象纪录列表
	 * @return
	 * @
	 */
	public Pagination<T> page() ;
	
	/**
	 * 分页查询
	 * @param hql
	 * @param parameters
	 * @return	返回list分页数据 X 可以是实体对象或者Map类型
	 * @throws HibernateException
	 * @throws SQLException
	 */
	public <X> Pagination<X> pageByHql (String hql, Object... parameters);
	
	/**
	 * 分页查询
	 *
	 * @param sql
	 * @param parameters
	 * @return	返回分页数据
	 * @throws HibernateException
	 * @throws SQLException
	 */
	public <X> Pagination<Map<String, X>> pageListMapBySql (String sql, Object... parameters);
	
	/**
	 * 执行更新操作（修改或者删除）
	 * @param ql
	 * @param parameters
	 * @return 受影响的行数
	 * @throws HibernateException, SQLException
	 */
	public int bulkUpdate(String ql, boolean isHql, Object... parameters);
	
	/**
	 * 批量执行更新操作（修改或者删除）
	 * @param qls
	 * @param paramList
	 * @return 只要不报错就返回true
	 * @throws HibernateException, SQLException
	 */
	public boolean bulkUpdate(List<String> qls, boolean isHql, List<Object> paramList);
	
	/**
	 * 批量执行更新操作（修改或者删除）
	 * @param qls
	 * @param paramList
	 * @return 只要其中一条语句没有更新数据就返回false 
	 * @throws HibernateException, SQLException
	 */
	public boolean bulkUpdateInFetch(List<String> qls, boolean isHql, List<Object> paramList);

}
