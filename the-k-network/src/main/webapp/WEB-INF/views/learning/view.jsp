<jsp:include page="../shared/header.jsp" />
<div class="container-fluid">
	<div class="row">
		
		<div class="column span10" style="padding-right:5px">
			<iframe name="inlineframe" src="http://theknetwork.org:9001/p/${learningSessionId}?userName=${sessionScope.nickName}" 
					frameborder="0" scrolling="no" 
					width="800" height="600"
					marginwidth="3" marginheight="0" ></iframe>
		</div>
		
		<div class="column span4 well">
			<h3>Welcome, ${sessionScope.nickName}</h3><br/>
			<jsp:include page="../tokbox/include.jsp"/>
			<br/><br/>
			<jsp:include page="../user-feedback/view.jsp" />
		</div>
		
	</div>
</div>
<jsp:include page="../shared/footer.jsp" />