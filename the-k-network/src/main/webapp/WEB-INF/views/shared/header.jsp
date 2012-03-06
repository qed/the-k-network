<%@ include file="../../standard-include.jspf" %>
<!doctype html>
<!--[if IE 7]><html class="no-js ie7 oldie" lang="en"><![endif]-->
<!--[if IE 8]><html class="no-js ie8 oldie" lang="en"><![endif]-->
<!-- Consider adding an manifest.appcache: h5bp.com/d/Offline -->
<!--[if gt IE 8]><!--><html class="no-js" lang="en"><!--<![endif]-->
<head>
    <base href="/">
	<link REL="SHORTCUT ICON" HREF="resources/img/light-bulb-icon.png">

	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<title><fmt:message key="site.title"/></title>
	<meta name="description" content="site.description">
	<meta name="author" content="">
	<meta name="viewport" content="width=device-width,initial-scale=1">
	
	<!--[if lt IE 9]>
	<script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
	<![endif]-->
	
	<link rel="stylesheet" href="resources/css/bootstrap.css">
	<link rel="stylesheet" href="resources/css/knetwork.css">
	
	<script type="text/javascript" src="resources/js/lib/jquery-1.7.1.js"></script>
	<script type="text/javascript" src="resources/js/lib/raty/js/jquery.raty.js"></script>
	<script src="http://staging.tokbox.com/v0.91/js/TB.min.js"></script>
	
	<script type="text/javascript">
		var learningSessionId = '${learningSessionId}';
	</script>
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
				<li class="pull-right">
					<a href="#">
						<div id="welcomeMessageDiv"><c:if test="${sessionScope.hasNickName}"><b>Logout</b></c:if></div>
					</a>
				</li>
			</ul>
		</div>
	</div>
</div>
