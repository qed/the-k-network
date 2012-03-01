<jsp:include page="../shared/header.jsp" />
<div class="container-fluid">
	<div class="row">
		<div class="column span4 well">
			<h3>Welcome, ${sessionScope.nickName}</h3><br/>
			<jsp:include page="../tokbox/include.jsp"/>
		</div>
		<div class="column span8 well">
			<jsp:include page="../user-feedback/view.jsp" />
		</div>
	</div>
</div>

<div class="container-fluid">
	<iframe name="inlineframe" src="/learn/whiteboard" 
		frameborder="0" scrolling="no" 
		width="1400" height="600"
		marginwidth="3" marginheight="0" ></iframe>
</div>

<jsp:include page="../shared/footer.jsp" />