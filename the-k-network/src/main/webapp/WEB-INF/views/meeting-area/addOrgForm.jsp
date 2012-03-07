<%@ include file="../../standard-include.jspf" %>

<jsp:include page="../shared/header.jsp" />

<div class="container">
	<h3>Add a Team</h3>
	
	<form id="createOrgForm" method="post"
		action="save-org">
		<label>Use one word, no spaces.  The URL will be shared as: "theknetwork.org/team/{name}"</label>
		<input type="text" class="span3"
			placeholder="Team URL" id="orgId" name="orgId"/>
		<br /> 
		<label>This can be multiple words and will be shown in of the app's User Interface</label>
		<input type="text" class="span3"
			placeholder="Team Title" id="orgTitle" name="orgTitle"/>
		<br /> 
		<button type="submit" name="Save" value="Save" class="btn btn-primary">Save</button>
	</form>
</div>

<jsp:include page="../shared/footer.jsp" />
