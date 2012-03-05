<jsp:include page="../shared/header.jsp" />

<div class="span4 pull-left">

	<div id="tokboxIncludeDiv" class="span4">
	    <jsp:include page="../tokbox/include.jsp"/>
	</div>
	<div id="userFeedbackIncludeDiv" class="span5 alert alert-info">
	    <jsp:include page="../user-feedback/view.jsp" />
	</div>
	
</div>		

<div id="whiteboardFrameDiv">
	<iframe name="inlineframe" src="/learn/whiteboard" 
			frameborder="0" scrolling="no" 
			width="100%" height="800"
			marginwidth="3" marginheight="0" ></iframe>
</div>

<jsp:include page="../shared/footer.jsp" />