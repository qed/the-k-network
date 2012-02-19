<%@ include file="../../standard-include.jspf" %>
<jsp:include page="../shared/header.jsp" />
	<div class="container">
			<h1>
				Rate this Learning Session
			</h1>
			<div class="span-12 last">	
				<sf:form modelAttribute="userFeedback" method="post">
				  	<fieldset>		
						<legend>Select a Rating, 1 through 5</legend>
						
						<p>
							<sf:label id="ratingLabel" for="rating" path="rating" cssErrorClass="error">Rating</sf:label><br/>
							<sf:input path="rating" /><sf:errors path="rating" />
						</p>
						<p>	
							<input id="submit" type="submit" value="Rate" />
						</p>
					</fieldset>
				</sf:form>
			</div>
			<hr>	
			<ul>
				<li> <a href="?locale=en_us">us</a> |  <a href="?locale=en_gb">gb</a> | <a href="?locale=es_es">es</a> | <a href="?locale=de_de">de</a> </li>
			</ul>	
		</div>
<jsp:include page="../shared/footer.jsp" />

