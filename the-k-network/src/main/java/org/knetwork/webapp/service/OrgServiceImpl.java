package org.knetwork.webapp.service;

import java.util.List;

import org.knetwork.webapp.entity.hibernate.OrganizationPo;
import org.springframework.stereotype.Service;

@Service
public class OrgServiceImpl extends GenericServiceImpl implements OrgService {

	/* (non-Javadoc)
	 * @see org.knetwork.webapp.service.OrgService#getOrganizations()
	 */
	@SuppressWarnings("unchecked")
	public List<OrganizationPo> getOrganizations() {
		List<OrganizationPo> results = (List<OrganizationPo>)getDao().findByNamedQuery("findOrganizations");
		return results;
	}
	
	/* (non-Javadoc)
	 * @see org.knetwork.webapp.service.OrgService#saveOrganization(java.lang.String, java.lang.String)
	 */
	public void saveOrganization(String orgId, String orgTitle) {
		getDao().save(new OrganizationPo(orgId, orgTitle));
	}
	
}
