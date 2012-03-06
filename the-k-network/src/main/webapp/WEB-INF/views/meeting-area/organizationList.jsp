<%@ include file="../../standard-include.jspf" %>

<jsp:include page="../shared/header.jsp" />

<div class="container">
	<h3>Organizations</h3>
	
	<c:forEach items="${organizations}" var="org">
		<b><a href="team/${org.orgId}"/>${org.orgTitle}</a></b>
		<br />
		<br />
	</c:forEach>
	<p/>
	<a href="show-add-org">Add an Organization</a>
</div>

<jsp:include page="../shared/footer.jsp" />