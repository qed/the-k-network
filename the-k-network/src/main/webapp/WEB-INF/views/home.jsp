<%@ include file="../standard-include.jspf"%>
<jsp:include page="shared/header.jsp" />
<div class="container">

      <div class="hero-unit">
        <h1>The K-Network</h1>
        <p>
			A peer to peer network<br />
			Khan Academy Driven<br />
			Give help. Get help. On Demand<br />
		</p>
        <p>
			<a class="btn btn-primary btn-large" href="${requestTokenUrl}">Join our Network!</a>
		</p>


		<p>
			${sessionScope.commandOutput}
		</p>
      </div>

	<%@ include file="includes/users_and_sessions.jsp" %>
	
</div>
<jsp:include page="shared/footer.jsp" />

