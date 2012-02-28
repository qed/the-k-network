<jsp:include page="../shared/header.jsp" />
<div class="container">
<div class="row">
	<div class="column span6">
		<jsp:include page="../user-feedback/view.jsp" />
	</div>
	<div class="column span6">
		<jsp:include page="../tokbox/include.jsp"/>
	</div>
</div>

<div class="row">
	
	<div class="column span12" style="margin:10px">
		<iframe name="inlineframe" src="/learn/whiteboard" 
			frameborder="0" scrolling="auto" width="800" 
			height="600" marginwidth="5" marginheight="5" ></iframe>
	</div>
</div>
</div>
<jsp:include page="../shared/footer.jsp" />