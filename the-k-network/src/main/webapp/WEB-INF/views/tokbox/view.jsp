<%@ include file="../../standard-include.jspf" %>
	
	<div class="smalltype">Status:  <span id="call-status">Connecting</span></div>
    <div id="links" style="height:24px">
        <input type="button" value="Start Publishing" id ="publishLink" onClick="javascript:startPublishing()" />
        <input type="button" value="Stop Publishing" id ="unpublishLink" onClick="javascript:stopPublishing()" />
    </div>
    <div id="myCamera" class="publisherContainer">
        <input type="button" id="push-to-talk" value="Click to talk" onClick="startTalking()" />
    </div>
	<div id="subscribers"></div>