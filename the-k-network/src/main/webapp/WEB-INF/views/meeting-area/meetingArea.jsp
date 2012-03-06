<%@ include file="../../standard-include.jspf" %>

<jsp:include page="../shared/header.jsp" />

<div class="container">
	<h3>Meeting Area</h3>
	
	
	<div id="whiteboardChatDiv">
	<iframe name="inlineframe" src="${whiteboardJoinUrl}" 
			frameborder="0" scrolling="no" 
			width="340" height="400"
			marginwidth="3" marginheight="0" ></iframe>

	<c:if test="${sessionScope.isLoggedIn}">
		<jsp:include page="../includes/users_and_sessions.jsp" />
    </c:if>

	</div>
</div>

<jsp:include page="../shared/footer.jsp" />
