<%@ include file="../standard-include.jspf" %>
<jsp:include page="shared/header.jsp" />
	<div class="row">
		<div class="span12">
			<h1>${user.nickname}</h1>
		</div>
		<div class="span12">
			<h3>Existing Sessions</h3>
			<ul>
				<c:forEach items="${learningSessions}" var="lSession">
				<li><a href="/learn/join?learningSessionId=${lSession}">${lSession}</a></li>
				</c:forEach>
			</ul>
		</div>
		<div class="span12">
			<a class="btn btn-primary btn-large" href="/learn/createMeeting">Start a Session</a>
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
	</div>

<jsp:include page="shared/footer.jsp" />

