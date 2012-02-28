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

      </div>

	<div class="row">
		<div class="column span6">
			<h4>Create your own Session</h4>
			<br />
			<form id="createSessionForm" method="post"
				action="/learn/createMeeting">
				<label>Nickname</label> <input type="text" class="span3"
					placeholder="Nickname" id="nickName" name="nickName"> <label>Session
					Title</label> <input type="text" class="span3" placeholder="Session Title"
					id="sessionTitle" name="sessionTitle"> <br />
				<br /> <a class="btn btn-primary btn-large" href="#"
					onclick="$('#createSessionForm').submit();">Start a Session</a>
			</form>
		</div>	
		<div class="column span6">
			<h4>Existing Sessions</h4>
			<br />
			<c:forEach items="${learningSessions}" var="lSession">
				<b><a href="/learn/join?learningSessionId=${lSession.sessionId}">${lSession.sessionTitle}</a></b>
				<br />
				<br />
			</c:forEach>
		</div>
	</div>
	<%--
	<c:if test="${loggedIn}">
		<div class="row">
			<div class="span12">
				<h1>${user.nickname}</h1>
			</div>
			<div class="column span6">
				<h2>Proficient</h2>
				<h3 class="label superstar">&#x2605; Super Star</h3>
				<div class="scrollbox">
					<ul>
						<c:forEach items="${exercises.superStarExercises}" var="exercise">
							<li>${exercise.exerciseModel.displayName}</li>
						</c:forEach>
					</ul>
				</div>
				<h3 class="label success">Proficient</h3>
				<div class="scrollbox">
					<ul>
						<c:forEach items="${exercises.proficientExercises}" var="exercise">
							<li>${exercise.exerciseModel.displayName}</li>
						</c:forEach>
					</ul>
				</div>
				<h3 class="label warning">Reviewing</h3>
				<div class="scrollbox">
					<ul>
						<c:forEach items="${exercises.reviewingExercises}" var="exercise">
							<li>${exercise.exerciseModel.displayName}</li>
						</c:forEach>
					</ul>
				</div>
			</div>
			<div class="column span6">
				<h2>Learning</h2>
				<h3 class="label important">Struggling</h3>
				<div class="scrollbox">
					<ul>
						<c:forEach items="${exercises.strugglingExercises}" var="exercise">
							<li>${exercise.exerciseModel.displayName}</li>
						</c:forEach>
					</ul>
				</div>
				<h3 class="label notice">In Progress</h3>
				<div class="scrollbox">
					<ul>
						<c:forEach items="${exercises.inProgressExercises}" var="exercise">
							<li>${exercise.exerciseModel.displayName}</li>
						</c:forEach>
					</ul>
				</div>
				<h3 class="label">Not Started</h3>
				<div class="scrollbox">
					<ul>
						<c:forEach items="${exercises.notStartedExercises}" var="exercise">
							<li>${exercise.exerciseModel.displayName}</li>
						</c:forEach>
					</ul>
				</div>
			</div>
	</c:if>
	 --%>
</div>
</div>
<jsp:include page="shared/footer.jsp" />

