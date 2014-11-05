package com.fjx.common.framework.base.dao.impl;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.CriteriaSpecification;
import org.springframework.beans.factory.annotation.Autowired;

import com.fjx.common.framework.base.dao.IBaseDao;
import com.fjx.common.framework.system.exception.MyRuntimeException;
import com.fjx.common.framework.system.pagination.Pagination;
import com.fjx.common.framework.system.pagination.PaginationContext;

/**
 * 
 * @author fengjianxin
 * @param <T>
 */
public class BaseDao implements IBaseDao {
	
	protected Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	protected SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	protected Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}
	
	/**
	 * 创建Query
	 * @param ql
	 * @param isHql
	 * @return
	 */
	private Query createMyQuery(String ql, boolean isHql, Object... parameters) {
		Query q = null;
		if (isHql) {
			q = getCurrentSession().createQuery(ql);
		} else {
			q = getCurrentSession().createSQLQuery(ql);
		}
		return setQueryParameters(q, parameters);
	}

	/**
	 * Query 赋值
	 * @param q
	 * @param parameters
	 * @return
	 */
	private Query setQueryParameters(Query q, Object... parameters) {
		if (parameters != null && parameters.length > 0) {
			String paramsStr = "";
			if(parameters[0] instanceof  List ){
				List<Object> params = (List<Object>) parameters[0];
				for(int i = 0; i < params.size(); i++){
					Object param = params.get(i);
					q.setParameter(i, param);
					paramsStr += " 【" + i + " : " + param + "】";
				}
			}else{
				for (int i = 0; i < parameters.length; i++) {
					Object param = parameters[i];
					q.setParameter(i, param);
					paramsStr += " 【" + i + " : " + param + "】";
				}
			}
			logger.debug("设置ql参数：" + paramsStr);
		}
		return q;
	}

	/**
	 * 将hql语句转换为查询记录总数的hql
	 * @param hql hql/sql语句
	 * @return
	 */
	private String getCountHQL(String hql) {
		int index = hql.indexOf("from");
		if (index != -1) {
			return "select count(*) " + hql.substring(index);
		}
		throw new MyRuntimeException("查询语句错误");
	}
	
	/**
	 * 分页查询
	 * @param hql
	 * @param isHql
	 * @param parameters
	 * @return
	 */
	private <X> Pagination<X> findPage(final String hql, boolean isHql, boolean isMap, final Object... parameters){
		int total = getCount(hql, isHql, parameters);
		logger.debug("查询的总记录数: " + total);
		Query q = createMyQuery(hql, isHql,parameters);
		q.setFirstResult(PaginationContext.getOffset()); // filter拦截到的分页参数
		q.setMaxResults(PaginationContext.getPagesize()); // filter拦截到的分页参数
		if(isMap){
			q.setResultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP);
		}
		List<X> datas = q.list();
		Pagination<X> pagination = new Pagination<X>(datas, total);
		return pagination;
	}
	
	@Override
	public <X> Serializable save(X entity) {
		Serializable serializable = null;
		serializable = getCurrentSession().save(entity);
		return serializable;
	}

	@Override
	public <X> void saveOrUpdate(X entity) {
		getCurrentSession().saveOrUpdate(entity);
	}

	@Override
	public <X> void delete(Class<X> entityClass, Serializable pk) {
		Session session = getCurrentSession();
		X entity = (X) session.load(entityClass, pk);
		session.delete(entity);
	}

	@Override
	public <X> void delete(X entity) {
		getCurrentSession().delete(entity);
	}

	@Override
	public <X> void deleteAll(Collection<X> entities){
		Session session = getCurrentSession();
		for(X entity : entities){
			session.delete(entity);
		}
	}

	@Override
	public <X> void update(X entity) {
		getCurrentSession().update(entity);
	}

	@Override
	public <X> X load(Class<X> entityClass, Serializable pk) {
		if(null == pk || "".equals(pk)){
			return null;
		}
		return (X) getCurrentSession().load(entityClass, pk);
	}

	@Override
	public <X> X findOneByHql(final String hql, final Object... parameters)	{
		return findOne(hql, true, parameters);
	}

	@Override
	public Map findOneBySql(final String sql, final Object... parameters) {
		return findOne(sql, false, parameters);
	}

	@Override
	public <X> List<X> findList(Class<X> entityClass){
		String hql = "from " + entityClass.getName();
		return (List<X>) createMyQuery(hql,true).list();
	}

	@Override
	public <X> List<X> findListByHql(String hql, Object... parameters){
		return (List<X>) createMyQuery(hql,true,parameters).list();
	}

	@Override
	public List<Map<String, Object>> findListMapBySql(String sql, Object... parameters){
		Query q = createMyQuery(sql, false, parameters);
		// 将结果集转为List<Map>
		q.setResultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP);
		return q.list();
	}

	@Override
	public <X> Pagination<X> page(Class<X> entityClass) {
		String hql = "from " + entityClass.getName();
		return pageByHql(hql);
	}

	@Override
	public <X> Pagination<X> pageByHql(final String hql,final Object... parameters){
		return findPage(hql, true,false, parameters);
	}
	
	@Override
	public Pagination<Map<String, Object>> pageListMapBySql(final String sql, final Object... parameters) {
		return findPage(sql, false, true, parameters);
	}

	@Override
	public <X> X findUniqueByHql(String hql, Object... parameters){
		return findUnique(hql, true, parameters);
	}

	@Override
	public <X> X findUniqueBySql(String sql, Object... parameters){
		return findUnique(sql, false, parameters);
	}

	@Override
	public int getCount(String ql, boolean isHql, Object... parameters) {
		final String countSQL = getCountHQL(ql);
		int total;
		Query q = createMyQuery(countSQL, isHql,parameters);
		Object res = q.uniqueResult();
		total = (Long.valueOf(res.toString())).intValue();
		return total;
	}

	@Override
	public int bulkUpdate(String ql, boolean isHql, Object... parameters) {
		Query query = createMyQuery(ql, isHql, parameters);
		return query.executeUpdate();
	}

	@Override
	public boolean bulkUpdate(List<String> qls, boolean isHql, List<Object> paramList) {
		boolean flag = true;
		for (int i = 0; i < qls.size(); i++) {
			bulkUpdate(qls.get(i),isHql,paramList.get(i));
		}
		return flag;
	}

	@Override
	public boolean bulkUpdateInFetch(List<String> qls,boolean isHql,  List<Object> paramList){
		boolean flag = true;
		for(int i = 0; i < qls.size(); i++){
			int tmp = bulkUpdate(qls.get(i), isHql, paramList.get(i));
			//如果没有更新到数据
			if(1 > tmp){
				flag = false;
			}
		}
		return flag;
	}

	/**
	 * 查询唯一的一个字段
	 * 
	 * @param ql
	 * @param isHql
	 * @param parameters
	 * @return
	 * @throws HibernateException
	 * @throws SQLException
	 */
	private <X> X findUnique(String ql, boolean isHql, Object... parameters) {
		Query q = createMyQuery(ql, isHql,parameters);
		return (X) q.uniqueResult();
	}

	/**
	 * 
	 * @param ql
	 * @param isHql
	 * @param parameters
	 * @return
	 * @throws Exception
	 */
	private <X> X findOne(final String ql, boolean isHql,final Object... parameters){
		Query q = createMyQuery(ql, isHql,parameters);
		q.setMaxResults(1);
		if (!isHql) {
			q.setResultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP);
		}
		List list = q.list();
		if (null != list && list.size() > 0) {
			return (X) list.get(0);
		}
		return null;
	}

}
