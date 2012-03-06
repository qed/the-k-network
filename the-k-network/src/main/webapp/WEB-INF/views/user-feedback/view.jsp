<%@ include file="../../standard-include.jspf" %>

	<div id="rateThisSessionDiv" class="pull-left" style="margin-left: 10px;">
		<b>Rate this Session</b> 
		<div id="star"></div>
		
		<script type="text/javascript">
			$('#star').raty({
			  click: function(score, evt) {
			    $.ajax({
			    	url:"user-feedback/rate?score="+score
			    });
			    $("#messageRating").html("Thanks for rating!");
			  }
			});
		</script>
		
		<div id="messageRating">
		</div>
	</div>

	<div id="commentThisSessionDiv" class="pull-right" style="margin-left:25px">
		<b>What do you think?</b>
		<div id="comment">
			<form id="commentForm">
				<textarea rows="3" cols="10" id="commentText"></textarea><br/>
				<a href="#" onclick="saveComment()" class="btn btn-primary pull-left" >Save</a>
			</form>		<div id="messageComment" class="pull-left" style="margin-top:-11px; margin-left: 20px;">
		</div>
		</div>
		
		<script type="text/javascript">
			function saveComment() {
			    $.ajax({
			    	url:"user-feedback/comment?comment="+$("#commentText").val()
			    });
			    $("#messageComment").html("Thanks!");
			}	
		</script>

	</div>
