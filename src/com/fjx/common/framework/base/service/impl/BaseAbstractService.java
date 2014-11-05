package com.fjx.common.framework.base.service.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.fjx.common.framework.base.dao.IBaseDao;
import com.fjx.common.framework.base.service.IBaseAbstractService;
import com.fjx.common.framework.system.pagination.Pagination;

/**
 * sercice 通用服务实现类
 * @author fengjx xd-fjx@qq.com
 * @date 2014年9月12日
 * @param <T>
 */
public abstract class BaseAbstractService<T> implements IBaseAbstractService<T> {

	@Autowired
	private IBaseDao baseDao;

	private Class<T> getEntityClass() {
		Type parentType = getClass().getGenericSuperclass();
		if (parentType instanceof ParameterizedType) {
			return (Class<T>) ((ParameterizedType) parentType)
					.getActualTypeArguments()[0];
		}
		throw new RuntimeException("未指定实体类型！");
	}

	@Override
	public <X> Serializable save(X entity) {
		return baseDao.save(entity);
	}

	@Override
	public <X> void saveOrUpdate(X entity) {
		baseDao.saveOrUpdate(entity);
	}

	@Override
	public <X> void delete(Class<X> entityClass, Serializable pk) {
		baseDao.delete(entityClass, pk);
	}

	@Override
	public void delete(Serializable pk) {
		baseDao.delete(getEntityClass(), pk);
	}

	@Override
	public <X> void delete(X entity) {
		baseDao.delete(entity);
	}

	@Override
	public <X> void deleteAll(Collection<X> entities) {
		baseDao.deleteAll(entities);
	}

	@Override
	public <X> void update(X entity) {
		baseDao.update(entity);
	}

	public T load(Serializable id) {
		return baseDao.load(getEntityClass(), id);
	}

	@Override
	public <X> X load(Class<X> entityClass, Serializable id) {
		return (X) baseDao.load(getEntityClass(), id);
	}

	@Override
	public <X> X findOneByHql(String hql, Object... parameters) {
		return baseDao.findOneByHql(hql, parameters);
	}

	@Override
	public Map findOneBySql(String sql, Object... parameters) {
		return baseDao.findOneBySql(sql, parameters);
	}

	@Override
	public <X> List<X> findList(Class<X> entityClass) {
		return baseDao.findList(entityClass);
	}
	
	@Override
	public List<T> findList() {
		return (List<T>) baseDao.findList(getEntityClass());
	}
	
	@Override
	public <X> List<X> findListByHql(String hql, Object... parameters) {
		return baseDao.findListByHql(hql, parameters);
	}

	@Override
	public List<Map<String, Object>> findListMapBySql(String sql, Object... parameters) {
		return baseDao.findListMapBySql(sql, parameters);
	}

	@Override
	public <X> X findUniqueByHql(String hql, Object... parameters) {
		return baseDao.findUniqueByHql(hql, parameters);
	}

	@Override
	public <X> X findUniqueBySql(String sql, Object... parameters) {
		return baseDao.findUniqueBySql(sql, parameters);
	}

	@Override
	public int getCount(String ql, boolean isHql, Object... parameters) {
		return baseDao.getCount(ql, isHql, parameters);
	}

	@Override
	public <X> Pagination<X> page(Class<X> entityClass) {
		return baseDao.page(entityClass);
	}

	@Override
	public Pagination<T> page() {
		return baseDao.page(getEntityClass());
	}

	@Override
	public <X> Pagination<X> pageByHql(String hql, Object... parameters) {
		return baseDao.pageByHql(hql, parameters);
	}
	
	@Override
	public Pagination<Map<String, Object>> pageListMapBySql(String sql, Object... parameters) {
		return baseDao.pageListMapBySql(sql, parameters);
	}

	@Override
	public int bulkUpdate(String ql, boolean isHql, Object... parameters) {
		return baseDao.bulkUpdate(ql, isHql, parameters);
	}

	@Override
	public boolean bulkUpdate(List<String> qls, boolean isHql,
			List<Object> paramList) {
		return baseDao.bulkUpdate(qls, isHql, paramList);
	}

	@Override
	public boolean bulkUpdateInFetch(List<String> qls, boolean isHql,
			List<Object> paramList) {
		return baseDao.bulkUpdateInFetch(qls, isHql, paramList);
	}

}
