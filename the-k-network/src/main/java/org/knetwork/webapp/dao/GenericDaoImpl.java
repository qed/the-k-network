package org.knetwork.webapp.dao;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.EntityMode;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.StatelessSession;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.SimpleExpression;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.metadata.ClassMetadata;
import org.hibernate.type.ComponentType;
import org.hibernate.type.IntegerType;
import org.hibernate.type.Type;
import org.knetwork.webapp.entity.hibernate.BasePo;
import org.knetwork.webapp.util.ObjectCloner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ReflectionUtils;

@SuppressWarnings({ "unchecked", "rawtypes" })
@Transactional(propagation = Propagation.SUPPORTS)
public class GenericDaoImpl implements GenericDao {
	private static final String DOUBLE_SPACE = "  ";

	private static final String SINGLE_SPACE = " ";

	private SessionFactory sessionFactory;
	
	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	/* (non-Javadoc)
	 * @see org.knetwork.webapp.dao.GenericDao#executeQuery(java.lang.String)
	 */
	@Override
	public List executeQuery(String strQuery) {
		SQLQuery objQuery = getSession().createSQLQuery(strQuery);

		objQuery.setFirstResult(0);

		return objQuery.list();
	}

	/* (non-Javadoc)
	 * @see org.knetwork.webapp.dao.GenericDao#executeUpdateQuery(java.lang.String)
	 */
	@Override
	public int executeUpdateQuery(String strQuery) {
		SQLQuery objQuery = getSession().createSQLQuery(strQuery);

		return objQuery.executeUpdate();
	}

	/* (non-Javadoc)
	 * @see org.knetwork.webapp.dao.GenericDao#executeUpdateQuery(java.lang.String, java.lang.String[], java.lang.Object[])
	 */
	@Override
	public int executeUpdateQuery(String namedQuery, String parameterNames[],
			Object parameters[]) {
		int result = 0;

		Query q = getSession().getNamedQuery(namedQuery);

		setParametersForQuery(q, parameterNames, parameters);

		result = q.executeUpdate();

		return result;
	}

	/* (non-Javadoc)
	 * @see org.knetwork.webapp.dao.GenericDao#findAll(java.lang.Class)
	 */
	@Override
	public List findAll(Class clazz) {
		Criteria c = getSession().createCriteria(clazz);

		List results = c.list();

		return results;
	}

	/* (non-Javadoc)
	 * @see org.knetwork.webapp.dao.GenericDao#findAll(java.lang.Class, java.lang.String[], boolean[])
	 */
	@Override
	public List findAll(Class clazz, String orderByNames[],
			boolean isAscending[]) {
		Criteria c = getSession().createCriteria(clazz);

		addOrderByClauseToCriteria(c, orderByNames, isAscending);

		List results = c.list();

		return results;
	}

	/* (non-Javadoc)
	 * @see org.knetwork.webapp.dao.GenericDao#findByCachedNamedQuery(java.lang.String)
	 */
	@Override
	public List findByCachedNamedQuery(String namedQuery) {
		Query q = getSession().getNamedQuery(namedQuery);

		q.setReadOnly(true);

		q.setFirstResult(0);

		List results = q.list();

		return results;
	}

	/* (non-Javadoc)
	 * @see org.knetwork.webapp.dao.GenericDao#findByExample(java.lang.Object)
	 */
	@Override
	public List findByExample(Object exampleInstance) {
		return findByExample(exampleInstance, false);
	}

	/* (non-Javadoc)
	 * @see org.knetwork.webapp.dao.GenericDao#findByExample(java.lang.Object, int, int)
	 */
	@Override
	public List findByExample(Object exampleInstance, int start, int max) {
		Criteria crit = getCriteria(exampleInstance, null);

		crit.setFirstResult(start);

		crit.setMaxResults(max);

		crit.setReadOnly(true);

		List results = crit.list();

		return results;
	}

	/* (non-Javadoc)
	 * @see org.knetwork.webapp.dao.GenericDao#findByExample(java.lang.Object, int, int, java.lang.String[], boolean[])
	 */
	@Override
	public List findByExample(Object exampleInstance, int start, int max,
			String orderByNames[], boolean isAscending[]) {
		Criteria crit = getCriteria(exampleInstance, null);

		crit.setReadOnly(true);

		crit.setFirstResult(start);

		crit.setMaxResults(max);

		addOrderByClauseToCriteria(crit, orderByNames, isAscending);

		List results = crit.list();

		return results;
	}

	/* (non-Javadoc)
	 * @see org.knetwork.webapp.dao.GenericDao#findByExampleAndDetach(java.lang.Object)
	 */
	@Override
	public List findByExampleAndDetach(Object exampleInstance) {
		return findByExample(exampleInstance, true);
	}

	/* (non-Javadoc)
	 * @see org.knetwork.webapp.dao.GenericDao#getCopy(java.lang.Object)
	 */
	@Override
	public Object getCopy(Object in) {
		return ObjectCloner.deepCopy(in);
	}

	/* (non-Javadoc)
	 * @see org.knetwork.webapp.dao.GenericDao#findByExampleIncludeNulls(java.lang.Object, java.lang.String[])
	 */
	@Override
	public List findByExampleIncludeNulls(Object exampleInstance,
			String ignorePropertyNames[]) {
		Object copyOfInstance = getCopy(exampleInstance);

		Criteria crit = getCriteria(copyOfInstance, false, ignorePropertyNames,
				null);

		crit.setFirstResult(0);

		crit.setReadOnly(true);

		List results = crit.list();

		if (results == null) {
			results = new ArrayList();
		}

		return results;
	}

	/* (non-Javadoc)
	 * @see org.knetwork.webapp.dao.GenericDao#findById(java.lang.Class, java.io.Serializable)
	 */
	@Override
	public Object findById(Class clazz, Serializable id) {
		BasePo exampleInstance = null;

		try {
			exampleInstance = (BasePo) clazz.newInstance();

			Serializable newId = convertIdToProperType(exampleInstance, id);

			Object entity = getSession().get(clazz, newId);

			return entity;
		} catch (InstantiationException e) {
			throw new RuntimeException("Could not get object by id", e);
		} catch (IllegalAccessException e) {
			throw new RuntimeException("Could not get object by id", e);
		}
	}

	/* (non-Javadoc)
	 * @see org.knetwork.webapp.dao.GenericDao#findByNamedQuery(java.lang.String)
	 */
	@Override
	public List findByNamedQuery(String namedQuery) {
		Query q = getSession().getNamedQuery(namedQuery);

		q.setReadOnly(true);

		q.setFirstResult(0);

		List results = q.list();

		return results;
	}

	/* (non-Javadoc)
	 * @see org.knetwork.webapp.dao.GenericDao#findByNamedQuery(java.lang.String, java.lang.String[], java.lang.Object[])
	 */
	@Override
	public List findByNamedQuery(String namedQuery, String parameterNames[],
			Object parameters[]) {
		Query q = getQuery(namedQuery, parameterNames, parameters);

		List results = null;

		q.setReadOnly(true);

		results = q.list();

		return results;
	}

	/* (non-Javadoc)
	 * @see org.knetwork.webapp.dao.GenericDao#findByNamedQuery(java.lang.String, java.lang.String[], java.lang.Object[], int, int)
	 */
	@Override
	public List findByNamedQuery(String namedQuery, String parameterNames[],
			Object parameters[], int start, int max) {
		Query q = getQuery(namedQuery, parameterNames, parameters);

		List results = null;

		q.setReadOnly(true);

		q.setFirstResult(start);

		q.setMaxResults(max);

		results = q.list();

		return results;
	}

	/* (non-Javadoc)
	 * @see org.knetwork.webapp.dao.GenericDao#findByNamedQuery(java.lang.String, java.lang.String[], java.lang.Object[], int, int, java.lang.String[], boolean[])
	 */
	@Override
	public List findByNamedQuery(String namedQuery, String parameterNames[],
			Object parameters[], int start, int max, String orderByNames[],
			boolean isAscending[]) {
		Query q = getQuery(namedQuery, parameterNames, parameters,
				orderByNames, isAscending);

		List results = null;

		q.setReadOnly(true);

		q.setFirstResult(start);

		q.setMaxResults(max);

		results = q.list();

		return results;
	}

	/* (non-Javadoc)
	 * @see org.knetwork.webapp.dao.GenericDao#findByNamedQueryAndDetach(java.lang.String, java.lang.String[], java.lang.Object[])
	 */
	@Override
	public List findByNamedQueryAndDetach(String namedQuery,
			String parameterNames[], Object parameters[]) {
		List results = null;

		StatelessSession statelessSession = null;

		try {
			statelessSession = getSessionFactory().openStatelessSession();

			Query q = statelessSession.getNamedQuery(namedQuery);

			setParametersForQuery(q, parameterNames, parameters);

			q.setReadOnly(true);

			results = q.list();
		} finally {
			if (statelessSession != null) {
				statelessSession.close();

				statelessSession = null;
			}
		}

		return results;
	}

	/* (non-Javadoc)
	 * @see org.knetwork.webapp.dao.GenericDao#refresh(java.lang.Object)
	 */
	@Override
	public void refresh(Object po) {
		getSession().refresh(po);
	}

	/* (non-Javadoc)
	 * @see org.knetwork.webapp.dao.GenericDao#remove(java.lang.Object)
	 */
	@Override
	public void remove(Object po) {
		getSession().delete(po);
	}

	/* (non-Javadoc)
	 * @see org.knetwork.webapp.dao.GenericDao#removeById(java.lang.Class, java.io.Serializable)
	 */
	@Override
	public void removeById(Class clazz, Serializable id) {
		getSession().delete(findById(clazz, id));
	}

	/* (non-Javadoc)
	 * @see org.knetwork.webapp.dao.GenericDao#save(java.lang.Object)
	 */
	@Override
	public void save(Object po) {
		getSession().save(po);
	}

	/* (non-Javadoc)
	 * @see org.knetwork.webapp.dao.GenericDao#update(java.lang.Object)
	 */
	@Override
	public void update(Object po) {
		getSession().update(po);
	}

	/* (non-Javadoc)
	 * @see org.knetwork.webapp.dao.GenericDao#getCountFromNamedQuery(java.lang.String, java.lang.String[], java.lang.Object[])
	 */
	@Override
	public int getCountFromNamedQuery(String queryName,
			String parameterNames[], Object parameterValues[]) {
		SQLQuery q = (SQLQuery) getSession().getNamedQuery(queryName);

		return getCountFromNativeQuery(q, parameterNames, parameterValues);
	}

	/* (non-Javadoc)
	 * @see org.knetwork.webapp.dao.GenericDao#getCountFromQuery(java.lang.String, java.lang.String[], java.lang.Object[])
	 */
	@Override
	public int getCountFromQuery(String queryName, String parameterNames[],
			Object parameterValues[]) {
		SQLQuery countQuery = (SQLQuery) getSession().getNamedQuery(queryName);

		setParametersForQuery(countQuery, parameterNames, parameterValues);

		Object result = countQuery.uniqueResult();

		if (result instanceof BigDecimal) {
			BigDecimal bd = (BigDecimal) result;

			return bd.intValue();
		} else {
			return 0;
		}
	}

	/* (non-Javadoc)
	 * @see org.knetwork.webapp.dao.GenericDao#getCountFromQueryString(java.lang.String, java.lang.String[], java.lang.Object[])
	 */
	@Override
	public int getCountFromQueryString(String originalQueryString,
			String parameterNames[], Object parameterValues[]) {
		String newQString = createCountQueryFromNativeQuery(originalQueryString);

		SQLQuery countQuery = getSession().createSQLQuery(newQString);

		setParametersForQuery(countQuery, parameterNames, parameterValues);

		Object result = countQuery.uniqueResult();

		if (result instanceof BigDecimal) {
			BigDecimal bd = (BigDecimal) result;

			return bd.intValue();
		} else {
			return 0;
		}
	}

	/* (non-Javadoc)
	 * @see org.knetwork.webapp.dao.GenericDao#getNamedSqlQuery(java.lang.String)
	 */
	@Override
	public String getNamedSqlQuery(String queryName) {
		SQLQuery q = (SQLQuery) getSession().getNamedQuery(queryName);

		return q.getQueryString();
	}

	/* (non-Javadoc)
	 * @see org.knetwork.webapp.dao.GenericDao#getRecordCountForExample(java.lang.Object)
	 */
	@Override
	public int getRecordCountForExample(Object exampleInstance) {
		int count = 0;

		StatelessSession statelessSession = null;

		try {
			statelessSession = getSessionFactory().openStatelessSession();

			Criteria crit = getCriteria(exampleInstance, statelessSession);

			crit.setReadOnly(true);

			count = getProjectedRowCount(crit);
		} finally {
			if (statelessSession != null) {
				statelessSession.close();

				statelessSession = null;
			}
		}

		return count;
	}

	/* (non-Javadoc)
	 * @see org.knetwork.webapp.dao.GenericDao#getSingleValueFromQuery(java.lang.String, java.lang.String[], java.lang.Object[])
	 */
	@Override
	public Object getSingleValueFromQuery(String namedQuery,
			String parameterNames[], Object parameterValues[]) {
		Object result = null;

		Query q = getQuery(namedQuery, parameterNames, parameterValues);

		result = q.uniqueResult();

		return result;
	}

	// PRIVATE HELPER FUNCTIONS

	private void addOrderByClauseToCriteria(Criteria crit,
			String orderByNames[], boolean isAscending[]) {
		if ((orderByNames == null) || (orderByNames.length == 0)) {
			return;
		}

		for (int i = 0; i < orderByNames.length; i++) {
			if (isAscending[i]) {
				crit.addOrder(Order.asc(orderByNames[i]));
			} else {
				crit.addOrder(Order.desc(orderByNames[i]));
			}
		}
	}

	/**
	 * Adds order by clauses to an existing query string
	 * 
	 * @param queryString
	 * @param orderByNames
	 * @param isAscending
	 * @return
	 */

	private String addOrderByItemsToQuery(String queryString,
			String orderByNames[], boolean isAscending[]) {
		StringBuilder sqlBuffer = new StringBuilder();

		String lowerCaseQuery = queryString.toLowerCase();

		int orderByLastIndex = lowerCaseQuery.lastIndexOf("order");

		queryString = queryString.substring(0, orderByLastIndex);

		sqlBuffer.append(queryString).append(SINGLE_SPACE);

		for (int i = 0; i < orderByNames.length; i++) {
			sqlBuffer.append((i > 0) ? ", " : "");

			sqlBuffer.append(orderByNames[i]).append(SINGLE_SPACE);

			sqlBuffer.append(isAscending[i] ? "ASC" : "DESC").append(
					SINGLE_SPACE);
		}

		return sqlBuffer.toString();
	}

	/**
	 * @param result
	 * @param idName
	 * @param id
	 * @param value
	 * @param propName
	 * @param properties
	 */

	private void addRestriction(ArrayList result, String idName, Type id,
			Object value, String propName, String properties[]) {
		SimpleExpression se;

		if (value != null) {
			if (!((value instanceof String) && "-1".equals(value))) {
				if (value instanceof String) {
					value = "%" + value + "%";
				}

				if (((properties.length > 1) && (idName != null))
						&& !idName.equals(propName)) {
					se = Restrictions.like(idName + "." + propName, value);

					result.add(se);
				} else {
					if (value instanceof String) {
						se = Restrictions.like(propName, value);

						result.add(se);
					} else {
						se = Restrictions.eq(propName, value);

						result.add(se);
					}
				}
			}
		}
	}

	/**
	 * @param sql
	 * @param orderByNames
	 * @param isAscending
	 * @return
	 */

	private String appendOrderByClauseToQueryString(String sql,
			String orderByNames[], boolean isAscending[]) {
		Map<String, String> orderMap = getOrderByColumnsFromNativeQuery(sql);

		for (int i = 0; i < orderByNames.length; i++) {
			orderByNames[i] = orderMap.get(orderByNames[i]);
		}

		sql = addOrderByItemsToQuery(sql, orderByNames, isAscending);

		return sql;
	}

	private Serializable convertIdToProperType(BasePo po, Serializable id) {
		SessionFactory sessionFactory = getSession().getSessionFactory();

		ClassMetadata metadata = sessionFactory.getClassMetadata(po.getClass());

		Type idType = metadata.getIdentifierType();

		if ((id instanceof String) && (idType instanceof IntegerType)) {
			return Integer.parseInt((String) id);
		} else {
			return id;
		}
	}

	/**
	 * Wraps native queries in select count(*) from (...) so that a count can be
	 * obtained without too much String manipulation
	 * 
	 * @param in
	 * @return
	 */
	private String createCountQueryFromNativeQuery(String in) {
		replaceDoubleSpaces(in);

		int indexOfFrom = StringUtils.indexOf(in.toLowerCase(), "from") + 4;

		String sql = "SELECT count(*) FROM " + in.substring(indexOfFrom);

		return sql;
	}

	private List findByExample(Object exampleInstance, boolean detachObjects) {
		List results = null;

		StatelessSession statelessSession = null;

		try {
			if (detachObjects) {
				statelessSession = getSessionFactory().openStatelessSession();
			}

			Criteria crit = getCriteria(exampleInstance, statelessSession);

			crit.setFirstResult(0);

			crit.setReadOnly(true);

			results = crit.list();

			if (results == null) {
				results = new ArrayList();
			}
		} finally {
			if (statelessSession != null) {
				statelessSession.close();

				statelessSession = null;
			}
		}

		return results;
	}

	/**
	 * When performing a query that excludes properties, this must be performed
	 * in order to make sure they are also set to null. That is because if the
	 * property is part of a composite id, the criteria will not ignore the
	 * property even if it is excluded.
	 * 
	 * @param po
	 * @param propertyName
	 */

	private void nullifyExcludedProperty(Object po, String propertyName) {
		Field field = ReflectionUtils.findField(po.getClass(), propertyName);

		ReflectionUtils.makeAccessible(field);

		ReflectionUtils.setField(field, po, null);
	}

	private void replaceDoubleSpaces(String in) {
		if (StringUtils.contains(in, DOUBLE_SPACE)) {
			while (StringUtils.contains(in, DOUBLE_SPACE)) {
				in = StringUtils.replace(in, DOUBLE_SPACE, SINGLE_SPACE);
			}
		}
	}

	private int getCountFromNativeQuery(SQLQuery q, String parameterNames[],
			Object parameterValues[]) {
		String qString = q.getQueryString();

		String newQString = createCountQueryFromNativeQuery(qString);

		SQLQuery countQuery = getSession().createSQLQuery(newQString);

		setParametersForQuery(countQuery, parameterNames, parameterValues);

		Object result = countQuery.uniqueResult();

		if (result instanceof BigDecimal) {
			BigDecimal bd = (BigDecimal) result;

			return bd.intValue();
		} else {
			return 0;
		}
	}

	/**
	 * Gets the criteria.
	 * 
	 * @param exampleInstance
	 *            the example instance
	 * @param statelessSession
	 *            DOCUMENT ME!
	 * @return the criteria
	 */

	private Criteria getCriteria(Object exampleInstance,
			StatelessSession statelessSession) {
		return getCriteria(exampleInstance, true, null, statelessSession);
	}

	/**
	 * Gets the criteria.
	 * 
	 * @param exampleInstance
	 *            the example instance
	 * @param excludeNulls
	 * @param ignorePropertyNames
	 * @param statelessSession
	 *            DOCUMENT ME!
	 * @return the criteria
	 */

	private Criteria getCriteria(Object exampleInstance, boolean excludeNulls,
			String ignorePropertyNames[], StatelessSession statelessSession) {
		Criteria crit;

		if (statelessSession != null) {
			crit = statelessSession.createCriteria(exampleInstance.getClass());
		} else {
			crit = getSession().createCriteria(exampleInstance.getClass());
		}

		if (ignorePropertyNames != null) {
			for (String prop : ignorePropertyNames) {
				nullifyExcludedProperty(exampleInstance, prop);
			}
		}

		Example example = Example.create(exampleInstance);

		example.enableLike(MatchMode.ANYWHERE);

		example.ignoreCase();

		if (ignorePropertyNames != null) {
			for (String prop : ignorePropertyNames) {
				example.excludeProperty(prop);
			}
		}

		if (!excludeNulls) {
			example.excludeNone();
		}

		ArrayList<SimpleExpression> restrictions = getRestrictions(exampleInstance);

		for (SimpleExpression se : restrictions) {
			crit.add(se);
		}

		crit.add(example);

		crit.setCacheable(false);

		return crit;
	}

	/**
	 * Returns a map of column names = property names for a given native query
	 * that includes aliased columns.
	 * 
	 * @param sql
	 * @return
	 */

	private Map<String, String> getOrderByColumnsFromNativeQuery(String sql) {
		Map<String, String> colMap = new HashMap<String, String>();

		String parts[] = StringUtils.splitByWholeSeparatorPreserveAllTokens(
				sql, "as {");

		List<String> columns = new ArrayList<String>();

		List<String> properties = new ArrayList<String>();

		for (int i = 0; i < parts.length; i++) {
			if (i == 0) {
				String p1[] = StringUtils.split(parts[i], " ");

				columns.add(p1[p1.length - 1].trim());
			} else if (i == (parts.length - 1)) {
				String pLast[] = StringUtils.split(parts[i], " ");

				properties.add(getPropertyNameFromPath(pLast[0].trim()));
			} else {
				replaceDoubleSpaces(parts[i]);

				String pMiddle[] = StringUtils.splitByWholeSeparator(parts[i],
						"},");

				columns.add(pMiddle[1].trim());

				properties.add(getPropertyNameFromPath(pMiddle[0].trim()));
			}
		}

		for (int j = 0; j < properties.size(); j++) {
			colMap.put(properties.get(j), columns.get(j));
		}

		return colMap;
	}

	/**
	 * Gets the projected row count.
	 * 
	 * @param criteria
	 *            the criteria
	 * @return the projected row count
	 */

	private int getProjectedRowCount(Criteria criteria) {
		int itemCount;

		criteria.setProjection(Projections.rowCount());

		Long count = (Long) criteria.uniqueResult();

		itemCount = count.intValue();

		criteria.setProjection(null);

		criteria.setResultTransformer(Criteria.ROOT_ENTITY);

		return itemCount;
	}

	/**
	 * Gets the name of the properties.
	 * 
	 * @param type
	 *            the type
	 * @return the properties
	 */

	private String[] getProperties(Type type) {
		if (type.isComponentType()) {
			return ((ComponentType) type).getPropertyNames();
		} else {
			return null;
		}
	}

	private String getPropertyNameFromPath(String path) {
		String parts[] = StringUtils.split(path, ".");

		return parts[parts.length - 1];
	}

	/**
	 * Gets the propertyValue for the propertName given.
	 * 
	 * @param type
	 *            the type
	 * @param object
	 *            the object
	 * @param property
	 *            the property
	 * @param index
	 *            the index
	 * @return the property value
	 */

	private Object getPropertyValue(Type type, Object object, String property,
			int index) {
		if (!type.isComponentType()) {
			return object;
		}

		if (index == -1) {
			return null;
		} else {
			return ((ComponentType) type).getPropertyValue(object, index,
					EntityMode.POJO);
		}
	}

	/**
	 * Gets the query.
	 * 
	 * @param namedQuery
	 *            the named query
	 * @param parameterNames
	 *            the parameter names
	 * @param parameters
	 *            the parameters
	 * @return the query
	 */

	private Query getQuery(String namedQuery, String parameterNames[],
			Object parameters[]) {
		Query q = getSession().getNamedQuery(namedQuery);

		setParametersForQuery(q, parameterNames, parameters);

		return q;
	}

	/**
	 * Gets the query, alters it to include an order by clause.
	 * 
	 * @param namedQuery
	 *            the named query
	 * @param parameterNames
	 *            the parameter names
	 * @param parameters
	 *            the parameters
	 * @param orderByNames
	 *            DOCUMENT ME!
	 * @param isAscending
	 *            DOCUMENT ME!
	 * @return the query
	 */

	private Query getQuery(String namedQuery, String parameterNames[],
			Object parameters[], String orderByNames[], boolean isAscending[]) {
		Query q = getSession().getNamedQuery(namedQuery);

		String sql = q.getQueryString();

		if (q instanceof SQLQuery) {
			sql = appendOrderByClauseToQueryString(sql, orderByNames,
					isAscending);
		}

		q = getSession().createQuery(sql);

		setParametersForQuery(q, parameterNames, parameters);

		return q;
	}

	/**
	 * Returns a list of SimpleExpressions for the id of the Entity passed.
	 * 
	 * @param exampleInstance
	 *            the example instance
	 * @return !
	 */

	private ArrayList<SimpleExpression> getRestrictions(Object exampleInstance) {
		ArrayList result = new ArrayList<SimpleExpression>();

		SessionFactory sessionFactory = getSessionFactory();

		ClassMetadata metadata = sessionFactory
				.getClassMetadata(exampleInstance.getClass());

		String idName = metadata.getIdentifierPropertyName();

		Object idObject = metadata.getIdentifier(exampleInstance,
				(SessionImplementor) getSession());

		Type id = metadata.getIdentifierType();

		Object value = null;

		String propName = null;

		String properties[] = getProperties(id);

		if (properties == null) {
			properties = new String[] { idName };
		}

		if ((properties != null) && (idObject != null)) {
			for (int i = 0; i < properties.length; i++) {
				propName = properties[i];

				value = getPropertyValue(id, idObject, properties[i], i);

				if (value instanceof String) {
					value = ((String) value).toUpperCase();
				}

				addRestriction(result, idName, id, value, propName, properties);
			}
		}

		return result;
	}

	private void setParametersForQuery(Query q, String parameterNames[],
			Object parameters[]) {
		if (parameterNames != null) {
			for (int i = 0; i < parameterNames.length; i++) {
				if (parameters[i] instanceof List) {
					q.setParameterList(parameterNames[i], (List) parameters[i]);
				} else {
					if (parameters[i] instanceof String) {
						parameters[i] = ((String) parameters[i]).toUpperCase();
					}

					q.setParameter(parameterNames[i], parameters[i]);
				}
			}
		}
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	@Inject
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
}
