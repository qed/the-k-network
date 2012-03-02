<jsp:include page="../shared/header.jsp" />

<table cellpadding="5">
	<tr>
		<td valign="top"><b>Welcome, ${sessionScope.nickName}</b><br/>
			<jsp:include page="../tokbox/include.jsp"/></td>
		<td valign="top"><jsp:include page="../user-feedback/view.jsp" /></td>
	</tr>
</table>
<br/><br/>
<iframe name="inlineframe" src="/learn/whiteboard" 
		frameborder="0" scrolling="no" 
		width="100%" height="800"
		marginwidth="3" marginheight="0" ></iframe>

<jsp:include page="../shared/footer.jsp" />