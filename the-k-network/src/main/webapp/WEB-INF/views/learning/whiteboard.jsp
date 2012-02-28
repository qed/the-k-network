<%@ include file="../../standard-include.jspf" %>
<script type="text/javascript" src="/resources/js/lib/jquery-1.7.1.js"></script>
<form id="whiteboardForm" method="post" action="/whiteboard-showcase-0.1/api/create">
	<input type="hidden" name="sessionId" id="sessionId"/>
	<input type="hidden" name="title" id="title"/>
	<input type="hidden" name="username" id="username"/>
</form>

<script type="text/javascript">
	$("#sessionId").val('${learningSessionId}');
	$("#title").val("A Title");
	$("#username").val("Johnny");
	$("#whiteboardForm").submit();
</script>