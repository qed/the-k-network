<%@ include file="../../standard-include.jspf" %>

<div class="container">
	<h3>Rate this Session</h3>
				
	<div id="message"></div><br/>
	<div id="star"></div>
	
	<script type="text/javascript">
		$('#star').raty({
		  click: function(score, evt) {
		    $.ajax({
		    	url:"user-feedback/rate?score="+score,
		    	success: function() {
		    		$("#message").text("You just rated us " + score);
		    	}
		    })
		  }
		});
	</script>
</div>