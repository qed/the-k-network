package org.knetwork.webapp.entity.hibernate;

public class OrganizationPo extends BasePo {

	private String orgId;
	private String orgTitle;
	
	public OrganizationPo() { }
	
	public OrganizationPo(String orgId, String orgTitle) {
		super();
		this.orgId = orgId;
		this.orgTitle = orgTitle;
	}
	
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	public String getOrgTitle() {
		return orgTitle;
	}
	public void setOrgTitle(String orgTitle) {
		this.orgTitle = orgTitle;
	}
	
}
