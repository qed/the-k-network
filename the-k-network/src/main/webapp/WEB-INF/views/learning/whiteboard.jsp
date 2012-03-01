<%@ include file="../../standard-include.jspf" %>
<script type="text/javascript" src="/resources/js/lib/jquery-1.7.1.js"></script>

<form id="whiteboardForm" action="" method="post"></form>

<script type="text/javascript">
	var urlCreate = "/whiteboard-showcase/api/create";
	var urlJoin = "/whiteboard-showcase/api/join";
	var urlFinal = "/whiteboard-showcase/whiteboard/workplace";
	$.ajax({
	  url: urlCreate+"?sessionId=${learningSessionId}&title=${sessionTitle}&username=${sessionScope.nickName}",
	  success: function(data) {
		  $("#whiteboardForm").attr('action', urlFinal + data);
		  $("#whiteboardForm").submit();
	  }
	});
</script>

