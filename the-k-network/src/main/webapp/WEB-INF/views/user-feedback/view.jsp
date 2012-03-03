<%@ include file="../../standard-include.jspf" %>

	<div id="rateThisSessionDiv">
		<b>Rate this Session</b>
		<div id="star"></div>
		
		<script type="text/javascript">
			$('#star').raty({
			  click: function(score, evt) {
			    $.ajax({
			    	url:"/user-feedback/rate?score="+score
			    });
			    $("#messageRating").html("You just rated us " + score);
			  }
			});
		</script>
		
		<div id="messageRating">
		</div>
	</div>
	<br/>
	
	<div id="commentThisSessionDiv">
		<b>Comment about this Service/Session</b>
		<div id="comment">
			<form id="commentForm">
				<textarea rows="3" cols="10" id="commentText"></textarea><br/>
				<a href="#" onclick="saveComment()" class="btn btn-primary" >Save</a>
			</form>
		</div>
		
		<script type="text/javascript">
			function saveComment() {
			    $.ajax({
			    	url:"/user-feedback/comment?comment="+$("#commentText").val()
			    });
			    $("#messageComment").html("Thanks!");
			}
		</script>
		<div id="messageComment">
		</div>
	</div>