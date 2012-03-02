<%@ include file="../../standard-include.jspf" %>

	<b>Rate this Session</b>
	<div id="star"></div>
	
	<script type="text/javascript">
		$('#star').raty({
		  click: function(score, evt) {
		    $.ajax({
		    	url:"/user-feedback/rate?score="+score
		    });
		    $("#message").html("You just rated us " + score);
		  }
		});
	</script>
	<div id="message">
	</div>