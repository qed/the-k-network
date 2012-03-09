<jsp:include page="../shared/header.jsp" />



<style>

#opentok_console {
	float: right;
	width: 400px;
	font-family: 'courier', monospace;
	font-size: 12px;
}

#sessionControls {
	float: top;
}

#pubControls input {
	display: inline;
}

#publishForm {
	font-size: 14px;
	font-family: Arial, Helvetica, sans-serif;
}

#deviceManagerControls {
	font-size: 14px;
	font-family: Arial, Helvetica, sans-serif;
}


div.controls {
	width:220px;
	padding:10 0 10 0;
	font-size: 14px;
}

.videobar {
	float: top;
}

.videobar-left {
	float: left;	
	width: 250px;
}

.smalltype {
	font-size: 80%;
	padding-bottom: 6px;
}

.description {
	font-size: 90%;
	padding-top: 6px;
}

#controls {
	float: left;
	text-align: left;
	padding-right: 10px;
}

#publisherControls {
	display: none;
}

#push-to-talk {
	display: none;
}

#devicePanelContainer {
	position: absolute;
	left: 250px;
	top: 10px;
	display:none;
}

#devicePanelCloseButton {
	position: relative;
	z-index: 10;
	margin-left: 285px;
	margin-right: 12px;
	padding: 3px;
	text-align: center;
	font-size: 11px;
	background-color: lightgrey;
}

#devicePanelBackground {
	background-color: lightgrey;
	width: 340px;
	height: 230px;
}

#devicePanelInset #devicePanel {
	position: relative;
	top: -74px;
	left: -9px;
}

a.settingsClose:link,
a.settingsClose:visited,
a.settingsClose:hover {
	text-decoration: none;
	cursor: pointer;
}


.publisherContainer {
	float: left;
}

.subscriberContainer {
	width: 264px;
	margin-left: 4px;
	float:left;
}


#connectionsContainer {
	clear:both;
	background-color:#CCC;
	width:400px;
}

.swfContainer {
	float:left;
	width: 320;
	margin-left: 5px;
}

#recorderElement {
	clear:both;
	float:left;
}

#playerElement {
	clear:both;
	float:left;
}
</style>

<script src="http://staging.tokbox.com/v0.91/js/TB.min.js" type="text/javascript" charset="utf-8"></script>
	<script type="text/javascript">
	var apiKey = 1127; // OpenTok sample code key. Replace with your own API key.
	var sessionId = "153905ca48f595f63f1f56c679797e985fb53120"; // Replace with your session ID.
	var token = "devtoken"; // Should not be hard-coded. Add to the page using the OpenTok server-side libraries.

	var session;
	var publisher;
	var pushToTalk;
	var subscribers = [];

	// Un-comment either of the following to set automatic logging and exception handling.
	// See the exceptionHandler() method below.
	// TB.setLogLevel(TB.DEBUG);
	TB.addEventListener("exception", exceptionHandler);

	if (TB.checkSystemRequirements() != TB.HAS_REQUIREMENTS) {
		alert("You don't have the minimum requirements to run this application."
			  + "Please upgrade to the latest version of Flash.");
	} else {
		session = TB.initSession(sessionId);

		// Add event listeners to the session
		session.addEventListener("sessionConnected", sessionConnectedHandler);
		session.addEventListener("streamCreated", streamCreatedHandler);
		session.addEventListener("streamDestroyed", streamDestroyedHandler);
		session.addEventListener("sessionDisconnected", sessionDisconnectedHandler);

		/*
		If testing the app from the desktop, be sure to check the Flash Player Global Security setting
		to allow the page from communicating with SWF content loaded from the web. For more information,
		see http://www.tokbox.com/opentok/build/tutorials/helloworld.html#localTest
		*/
		session.connect(apiKey, token);
	}

	//--------------------------------------
	//  OPENTOK EVENT HANDLERS
	//--------------------------------------
	function sessionConnectedHandler(event) {
		document.getElementById("call-status").innerHTML = "Listening";

		// Display all streams on screen
		for (var i = 0; i < event.streams.length; i++) {
			subscribeToStream(event.streams[i]);
		}

		// Now possible to join a session
		show("publishLink");
	}

	function sessionDisconnectedHandler (event) {
		// We lost connection to the server
		document.getElementById("call-status").innerHTML = "Disconnected";
		hide("unpublishLink");
		hide("publishLink");
		hide("push-to-talk");
	}

	// Display all streams on screen, except for the ones published by this connection
	function streamCreatedHandler(event) {

		for (var i = 0; i < event.streams.length; i++) {
			if (event.streams[i].connection.connectionId != session.connection.connectionId) {
				subscribeToStream(event.streams[i]);
			} else {
				// Our publisher just started streaming
				show("unpublishLink");
			}
		}
	}

	// Remove streams from screen, except for the one published by this page
	function streamDestroyedHandler(event) {
		for (var i = 0; i < event.streams.length; i++) {
			if (event.streams[i].connection.connectionId == session.connection.connectionId) {
				// Our publisher just stopped streaming
				document.getElementById("call-status").innerHTML = "Listening";
				show("publishLink");
				hide("unpublishLink");
			}
			var removedSubscribers = session.getSubscribersForStream(event.streams[i]);
			for (i = 0; i < removedSubscribers.length; i++) {
				subscribers.splice(subscribers.indexOf(removedSubscribers[0]), 1);
			}
		}
	}

	/*
	Called when the OpenTok API detects the echo cancellation mode of the publisher.
	Also called when the echo cancellation mode changes. Turns push-to-talk mode
	on or off based on the echo cancellation mode.
	*/
	function echoCancellationModeChangedHandler(event) {
		switch(publisher.getEchoCancellationMode()) {
			case "fullDuplex":
				setPushToTalk(false);
				document.getElementById("call-status").innerHTML = "Active";
				break;
			case "none":
				document.getElementById("call-status").innerHTML = "Active";
				setPushToTalk(true);
				break;
		}
	}

	/*
	If you un-comment the call to TB.addEventListener("exception", exceptionHandler) above, OpenTok calls the
	exceptionHandler() method when exception events occur. You can modify this method to further process exception events.
	If you un-comment the call to TB.setLogLevel(), above, OpenTok automatically displays exception event messages.
	*/
	function exceptionHandler(event) {
		alert("Exception: " + event.code + "::" + event.message);
	}

	//--------------------------------------
	//  LINK CLICK HANDLERS
	//--------------------------------------

	// Called when user clicks the Join session link
	function startPublishing() {
		pushToTalk = false;

		var parentDiv = document.getElementById("myCamera");
		var followingDiv = document.getElementById("push-to-talk");
		var stubSpan = document.createElement("div"); // Create a div for the publisher to replace
		stubSpan.id = "opentok_publisher";
		parentDiv.insertBefore(stubSpan, followingDiv);

		var publishProps = {publishAudio: !pushToTalk};
		publisher = session.publish(stubSpan.id, publishProps);
		publisher.addEventListener("echoCancellationModeChanged", echoCancellationModeChangedHandler);

		document.getElementById("call-status").innerHTML = "Joining...";
		hide("publishLink");
	}

	// Called when user wants to stop participating in the session
	function stopPublishing() {
		// Hide publisher controls, including push-to-talk if it's there
		if (pushToTalk) {
			hide("push-to-talk");
			pushToTalk = false;
		}

		if (publisher) {
			// Stop the stream
			session.unpublish(publisher);
			publisher = null;
		}

		document.getElementById("call-status").innerHTML = "Leaving...";
		hide("unpublishLink");
	}

	// Called when a user pushes-to-talk
	function startTalking() {
		for (var i = 0; i < subscribers.length; i++) {
			subscribers[i].subscribeToAudio(false);
		}
		publisher.publishAudio(true);

		document.getElementById("push-to-talk").onclick = stopTalking;
		document.getElementById("push-to-talk").value = "Mute";
	}

	// Called when a user releases push-to-talk
	function stopTalking() {
		publisher.publishAudio(false);
		for (var i = 0; i < subscribers.length; i++) {
			subscribers[i].subscribeToAudio(true);
		}

		document.getElementById("push-to-talk").onclick = startTalking;
		document.getElementById("push-to-talk").value = "Unmute";
	}

	//--------------------------------------
	//  HELPER METHODS
	//--------------------------------------

	// Called to subscribe to a new stream
	function subscribeToStream(stream) {
		// Create a div for the subscriber to replace
		var parentDiv = document.getElementById("subscribers");
		var stubSpan = document.createElement("span");
		stubSpan.id = "opentok_subscriber_" + stream.streamId;
		parentDiv.appendChild(stubSpan);

		var subscriberProps = {subscribeToAudio: !pushToTalk};
		subscribers.push(session.subscribe(stream, stubSpan.id, subscriberProps));
	}

	// Enable push-to-talk functionality.
	function setPushToTalk(pttOn) {
		if (pttOn) {
			stopTalking();
			show("push-to-talk");
			pushToTalk = true;
		} else {
			turnOnAllAudio();
			hide("push-to-talk");
			pushToTalk = false;
		}
	}

	// Called when an app leaves push-to-talk mode because accoustic echo cancellation is enabled.
	function turnOnAllAudio() {
		for (var i = 0; i < subscribers.length; i++) {
			subscribers[i].subscribeToAudio(true);
		}
		publisher.publishAudio(true);
	}

	function show(id) {
		document.getElementById(id).style.display = 'block';
	}

	function hide(id) {
		document.getElementById(id).style.display = 'none';
	}

	</script>

<div class="accordion" id="accordion2">

	<div class="accordion-group">
		<div class="accordion-heading">
			<a class="accordion-toggle" data-toggle="collapse"
				data-parent="#accordion2" href="#collapseAudio">
				<h5>Audio Settings</h5>
			</a>
		</div>
		<div id="collapseAudio" class="accordion-body in collapse"
			style="height: auto;">
			<div class="accordion-inner">
				<jsp:include page="../tokbox/include.jsp" />
			</div>
		</div>
	</div>

	<div class="accordion-group">
		<div class="accordion-heading">
			<a class="accordion-toggle" data-toggle="collapse"
				data-parent="#accordion2" href="#collapseOne">
				<h5>How can we make this awesome???</h5>
			</a>
		</div>
		<div id="collapseOne" class="accordion-body collapse"
			style="height: 0px;">
			<div class="accordion-inner">
				<div id="userFeedbackIncludeDiv"
					class="offset1 pull-left alert alert-info">
					<jsp:include page="../user-feedback/view.jsp" />
				</div>
			</div>
		</div>
	</div>
</div>

<iframe class="offset1" name="inlineframe" src="learn/whiteboard" 
						frameborder="0" scrolling="auto" 
						width="100%" height="680"
						marginwidth="3" marginheight="0" ></iframe>

<jsp:include page="../shared/footer.jsp" />
