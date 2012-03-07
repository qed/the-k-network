<%@ include file="../../standard-include.jspf" %>

<jsp:include page="../shared/header.jsp" />

<div class="container">
	<h3>Teams:</h3>
	<br />
	<c:forEach items="${organizations}" var="org">
		<i class="icon-tags"></i><b><a href="team/${org.orgId}"/>${org.orgTitle}</a></b>
		<br />
		<br />
	</c:forEach>
	<p/>
	<u><a href="show-add-org">Add a new Team</a><u>
</div>

<jsp:include page="../shared/footer.jsp" />
