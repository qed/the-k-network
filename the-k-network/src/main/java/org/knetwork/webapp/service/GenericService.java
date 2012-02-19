package org.knetwork.webapp.service;

import javax.inject.Inject;

import org.knetwork.webapp.dao.GenericDao;

public interface GenericService {

	@Inject
	public void setDao(final GenericDao dao);
	public GenericDao getDao();

}