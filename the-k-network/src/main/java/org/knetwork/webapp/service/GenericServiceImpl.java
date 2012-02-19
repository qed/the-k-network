package org.knetwork.webapp.service;

import javax.inject.Inject;

import org.knetwork.webapp.dao.GenericDao;
import org.springframework.stereotype.Service;

@Service
public class GenericServiceImpl implements GenericService {

	private GenericDao dao;

	@Inject
	public void setDao(final GenericDao dao) {
		this.dao = dao;
	}
	
	/* (non-Javadoc)
	 * @see org.knetwork.webapp.service.GenericService#getDao()
	 */
	@Override
	public GenericDao getDao() {
		return dao;
	}
	
}
