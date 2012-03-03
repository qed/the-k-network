<jsp:include page="../shared/header.jsp" />

<table cellpadding="5">
	<tr>
		<td valign="top">
			<div id="welcomeMessageDiv"><b>Welcome, ${sessionScope.nickName}</b></div><br/>
			<div id="tokboxIncludeDiv">
				<jsp:include page="../tokbox/include.jsp"/>
			</div>
		</td>
		<td valign="top">
			<div id="userFeedbackIncludeDiv">
				<jsp:include page="../user-feedback/view.jsp" />
			</div>
		</td>
	</tr>
</table>
<br/><br/>

<div id="whiteboardFrameDiv">
	<iframe name="inlineframe" src="/learn/whiteboard" 
			frameborder="0" scrolling="no" 
			width="100%" height="800"
			marginwidth="3" marginheight="0" ></iframe>
</div>

<jsp:include page="../shared/footer.jsp" />