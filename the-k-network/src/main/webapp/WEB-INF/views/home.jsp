<%@ include file="../standard-include.jspf" %>
<jsp:include page="shared/header.jsp" />
	<!-- Main hero unit for a primary marketing message or call to action -->

<div class="container">

      <!-- Main hero unit for a primary marketing message or call to action -->
      <div class="hero-unit">
        <h1>The K-Network</h1>
        <p>
			A peer to peer network<br />
			Khan Academy Driven<br />
			Give help. Get help. On Demand<br />
		</p>
        <p>
        <%--
			<a class="btn btn-primary btn-large" href="${requestTokenUrl}">Join our Network!</a>
		 --%>
		 	<a class="btn btn-primary btn-large" href="/user-features">Use Site Features</a>
		</p>

      </div>

      <!-- Example row of columns -->
      <div class="row">
        <div class="span4">
          <h2>Our Mission</h2>
           <p>Donec id elit non mi porta gravida at eget metus. Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut fermentum massa justo sit amet risus. Etiam porta sem malesuada magna mollis euismod. Donec sed odio dui. </p>
        </div>
        <div class="span4">
          <h2>Our Story</h2>
           <p>Donec id elit non mi porta gravida at eget metus. Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut fermentum massa justo sit amet risus. Etiam porta sem malesuada magna mollis euismod. Donec sed odio dui. </p>
       </div>
        <div class="span4">
          <h2>Our Team</h2>
          <p>Donec sed odio dui. Cras justo odio, dapibus ac facilisis in, egestas eget quam. Vestibulum id ligula porta felis euismod semper. Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut fermentum massa justo sit amet risus.</p>
        </div>
      </div>

</div>
<jsp:include page="shared/footer.jsp" />
						

