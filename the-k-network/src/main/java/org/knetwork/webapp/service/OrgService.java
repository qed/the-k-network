package org.knetwork.webapp.service;

import java.util.List;

import org.knetwork.webapp.entity.hibernate.OrganizationPo;
import org.springframework.stereotype.Service;

@Service
public interface OrgService {

	public List<OrganizationPo> getOrganizations();

	public void saveOrganization(String orgId, String orgTitle);

}