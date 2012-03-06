<jsp:include page="../shared/header.jsp" />

<div class="content-heading well" style="height: 200px;">
	<div>
		<c:if test="${sessionScope.hasNickName}">
			<h1 class="span5 offset1">Welcome ${sessionScope.nickName}!</h1>
		</c:if>
		<br />
		<br />
		<br />
	</div>
	
	<div id="userFeedbackIncludeDiv" class="offset1 pull-left alert alert-info">
	    <jsp:include page="../user-feedback/view.jsp" />
	</div>
	
	<div id="tokboxIncludeDiv" class="span4 offset8">
	    <jsp:include page="../tokbox/include.jsp"/>
	</div>
</div>		

<div id="whiteboardFrameDiv">
	<iframe class="offset1" name="inlineframe" src="learn/whiteboard" 
			frameborder="0" scrolling="auto" 
			width="100%" height="680"
			marginwidth="3" marginheight="0" ></iframe>

</div>

<jsp:include page="../shared/footer.jsp" />
