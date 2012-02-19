<%@ include file="../../standard-include.jspf" %>
<!doctype html>
<!--[if IE 7]><html class="no-js ie7 oldie" lang="en"><![endif]-->
<!--[if IE 8]><html class="no-js ie8 oldie" lang="en"><![endif]-->
<!-- Consider adding an manifest.appcache: h5bp.com/d/Offline -->
<!--[if gt IE 8]><!--><html class="no-js" lang="en"><!--<![endif]-->
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<title><fmt:message key="site.title"/></title>
	<meta name="description" content="site.description">
	<meta name="author" content="">
	<meta name="viewport" content="width=device-width,initial-scale=1">
	
	<!--[if lt IE 9]>
	<script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
	<![endif]-->
	
	<link rel="stylesheet" href="/resources/css/bootstrap.css">
	<link rel="stylesheet" href="/resources/css/knetwork.css">
</head>

<body>
	<div class="navbar navbar-fixed-top">
      		<div class="navbar-inner">
			<div class="container">
				<a class="brand" href="#"><fmt:message key="site.title"/></a>
				<ul class="nav">
					<li><a href="#mission">Our Mission</a></li>
					<li><a href="#team">Our Team</a></li>
					<li><a href="#contact">Contact Us</a></li>
					<c:if test="${loggedIn}">
						<li><a href="/logout">Logout</a></li>
					</c:if>
				</ul>
			</div>
		</div>
	</div>
