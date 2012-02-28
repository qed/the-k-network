<%@ include file="../../standard-include.jspf" %>
<style type="text/css">
  .small > * {
    width: 80px;
    height: 40px;
  }
</style>
<div id="tokboxContainer">
<script type="text/javascript" charset="utf-8">
	// set up the globals used by the next included js file
		var apiKey = ${apiKey};
		var sessionId = '${tokboxSessionId}';
		var token = 'devtoken';
</script>

<script type="text/javascript" src="/resources/js/knetwork-tokbox.js"></script>
	
<div id="subscribers"></div>
</div>	

<div class="container">
	<div class="smalltype">Status:  <span id="call-status">Connecting</span></div>
	<div id="links" style="width:300px">
	    <a href="#" id ="publishLink" onClick="javascript:startPublishing()">Connect</a><br/>
	    <a href="#" id ="unpublishLink" onClick="javascript:stopPublishing()">Leave</a><br/>
	    <a href="#" id ="push-to-talk" onClick="startTalking()">Unmute</a>
	</div>
</div>

<div class="container">
	<div id="myCamera" class="publisherContainer">
		<span id="filler"></span>
	</div>
</div>