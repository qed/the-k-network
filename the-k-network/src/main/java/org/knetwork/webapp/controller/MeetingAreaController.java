package org.knetwork.webapp.controller;

import java.net.MalformedURLException;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.knetwork.webapp.entity.hibernate.OrganizationPo;
import org.knetwork.webapp.service.OrgService;
import org.knetwork.webapp.util.SessionMapUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MeetingAreaController {

	private OrgService orgService;
	private static final String PREFIX = "http://theknetwork.org/whiteboard";
	
	@RequestMapping("class-congregation/{organization}")
	public String joinMeetingArea(final HttpSession session,
			final HttpServletRequest request, final Model model,
			@PathVariable("organization") String organization)
			throws MalformedURLException {
		
		SessionMapUtil.initWhiteboardSessionForChat(session, organization,
				(String) session.getAttribute("nickName"), "Group%20Chat",
				"create", PREFIX);
		
		return "meeting-area/meetingArea";
	}
	
	@RequestMapping("show-add-org")
	public String showAddOrgPage(final HttpSession session,
			final HttpServletRequest request, final Model model)
			throws MalformedURLException {
		
		return "meeting-area/addOrgForm";
	}
	
	@RequestMapping("save-org")
	public String saveOrganization(final HttpSession session,
			final HttpServletRequest request, final Model model,
			@RequestParam("orgId") String orgId, @RequestParam("orgTitle") String orgTitle)
			throws MalformedURLException {
		
		orgService.saveOrganization(orgId, orgTitle);
		return "redirect:/org-list";
	}
	
	@RequestMapping("org-list")
	public String listOrgs(final HttpSession session,
			final HttpServletRequest request, final Model model)
			throws MalformedURLException {
		List<OrganizationPo> results = orgService.getOrganizations();
		model.addAttribute("organizations", results);
		return "meeting-area/organizationList";
	}
	
	@Inject
	public void setOrgService(OrgService orgService) {
		this.orgService = orgService;
	}
	
}
