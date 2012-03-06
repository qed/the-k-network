<%@ include file="../../standard-include.jspf" %>

<jsp:include page="../shared/header.jsp" />

<div class="container">
	<h3>Add an Organization</h3>
	
	<form id="createOrgForm" method="post"
		action="save-org">
		<label>Organization ID (Preferably one word, no spaces)</label>
		<input type="text" class="span3"
			placeholder="Organization ID" id="orgId" name="orgId"/>
		<br /> 
		<label>Organization Title</label>
		<input type="text" class="span3"
			placeholder="Organization Title" id="orgTitle" name="orgTitle"/>
		<br /> 
		<button type="submit" name="Save" value="Save" class="btn btn-primary">Save</button>
	</form>
</div>

<jsp:include page="../shared/footer.jsp" />