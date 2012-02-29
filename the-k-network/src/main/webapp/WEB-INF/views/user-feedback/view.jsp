<%@ include file="../../standard-include.jspf" %>

	<b>Rate this Session</b>
	<div id="star"></div>
	
	<script type="text/javascript">
		$('#star').raty({
		  click: function(score, evt) {
		    $.ajax({
		    	url:"/user-feedback/rate?score="+score
		    });
		    $("#message").html("<div class='alert alert-info'>You just rated us " + score + "</div>");
		  }
		});
	</script>
	<div id="message">
		<div class="alert alert-error">
			Don't forget to rate the learning session!
		</div>
	</div>