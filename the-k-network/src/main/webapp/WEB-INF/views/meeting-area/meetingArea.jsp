<%@ include file="../../standard-include.jspf" %>

<jsp:include page="../shared/header.jsp" />

<div class="container">
	<h3>Meeting Area</h3>
	
	<div id="whiteboardChatDiv">
	<c:if test="${sessionScope.hasNickName}">
	
	<iframe name="inlineframe" src="${whiteboardJoinUrl}" 
			frameborder="0" scrolling="no" 
			width="340" height="400"
			marginwidth="3" marginheight="0" ></iframe>
	</c:if>

	<jsp:include page="../includes/team_users_and_sessions.jsp" />

	</div>
</div>

<jsp:include page="../shared/footer.jsp" />
