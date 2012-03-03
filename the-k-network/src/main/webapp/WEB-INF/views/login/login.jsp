<%@ include file="../../standard-include.jspf"%>
<jsp:include page="../shared/header.jsp" />
<div class="container">

	${failureMessage}

	<h2>Log in, please...</h2>

	<form name='f' action="<c:url value='j_spring_security_check' />"
		method='POST'>
	
		<table>
			<tr>
				<td>User:</td>
				<td><input type='text' name='j_username' value=''></td>
			</tr>
			<tr>
				<td>Password:</td>
				<td><input type='password' name='j_password' /></td>
			</tr>
			<tr>
				<td colspan='2'><input name="submit" type="submit" class="btn btn-primary btn-large"
					value="Log In" /></td>
			</tr>
		</table>
	
	</form>
	
</div>

<jsp:include page="../shared/footer.jsp" />