package org.knetwork.webapp.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@SuppressWarnings({ "rawtypes" })
@Transactional(propagation = Propagation.SUPPORTS)
public interface GenericDao {

	public List createDynamicQuery(String dynamicQuery, String aliasName,
			Class pojoClass, int start, int max, String orderByNames[],
			boolean isAscending[]);

	public List executeQuery(String strQuery);

	public int executeUpdateQuery(String strQuery);

	public int executeUpdateQuery(String namedQuery, String parameterNames[],
			Object parameters[]);

	public List findAll(Class clazz);

	public List findAll(Class clazz, String orderByNames[],
			boolean isAscending[]);

	public List findByCachedNamedQuery(String namedQuery);

	public List findByExample(Object exampleInstance);

	public List findByExample(Object exampleInstance, int start, int max);

	public List findByExample(Object exampleInstance, int start, int max,
			String orderByNames[], boolean isAscending[]);

	public List findByExampleAndDetach(Object exampleInstance);

	public Object getCopy(Object in);

	public List findByExampleIncludeNulls(Object exampleInstance,
			String ignorePropertyNames[]);

	public Object findById(Class clazz, Serializable id);

	public List findByNamedQuery(String namedQuery);

	public List findByNamedQuery(String namedQuery, String parameterNames[],
			Object parameters[]);

	public List findByNamedQuery(String namedQuery, String parameterNames[],
			Object parameters[], int start, int max);

	public List findByNamedQuery(String namedQuery, String parameterNames[],
			Object parameters[], int start, int max, String orderByNames[],
			boolean isAscending[]);

	public List findByNamedQueryAndDetach(String namedQuery,
			String parameterNames[], Object parameters[]);

	public void refresh(Object po);

	public void remove(Object po);

	public void removeById(Class clazz, Serializable id);

	public void save(Object po);

	public void update(Object po);

	public int getCountFromNamedQuery(String queryName,
			String parameterNames[], Object parameterValues[]);

	public int getCountFromQuery(String queryName, String parameterNames[],
			Object parameterValues[]);

	public int getCountFromQueryString(String originalQueryString,
			String parameterNames[], Object parameterValues[]);

	public String getNamedSqlQuery(String queryName);

	public int getRecordCountForExample(Object exampleInstance);

	public Object getSingleValueFromQuery(String namedQuery,
			String parameterNames[], Object parameterValues[]);

}