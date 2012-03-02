<%@ include file="../../standard-include.jspf" %>
<style type="text/css">
  .small > * {
    width: 180px;
    height: 80px;
  }
</style>
<div id="tokboxContainer">
<script type="text/javascript" charset="utf-8">
	// set up the globals used by the next included js file
		var apiKey = ${apiKey};
		var sessionId = '${tokboxSessionId}';
		var token = '${userToken}';
</script>

<script type="text/javascript" src="/resources/js/knetwork-tokbox.js"></script>
	
<div id="subscribers"></div>
</div>	

<div class="smalltype"><span id="call-status"><div class='alert alert-success'>Connecting to Audio, please wait...</div></span></div>

<div id="myCamera" class="publisherContainer" class="small">
	<span id="filler"></span>
</div>

<div id="links" style="width:200px;display:none">
    <a href="#" class='.btn-success' id="push-to-talk">Unmute</a>
</div>