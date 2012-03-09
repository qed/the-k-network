<%@ include file="../../standard-include.jspf" %>
<jsp:useBean id="now" class="java.util.Date"/>
		<footer class="centered">
		<p>&copy; <fmt:formatDate pattern="yyyy" value="${now}"/> <fmt:message key="site.copyright.holder" /></p>
		</footer>
	</div> <!-- /container -->
	
	<script src="//ajax.googleapis.com/ajax/libs/jquery/${knetworkConfig.jQueryVersion}/jquery.min.js"></script>
	<script>window.jQuery || document.write('<script src="resources/js/libs/jquery-${knetworkConfig.jQueryVersion}.js"><\/script>')</script>
	
	<!-- <script>
		window._gaq = [['_setAccount','UAXXXXXXXX1'],['_trackPageview'],['_trackPageLoadTime']];
		Modernizr.load({
			load: ('https:' == location.protocol ? '//ssl' : '//www') + '.google-analytics.com/ga.js'
		});
	</script> -->
	
	<!-- Prompt IE 6 users to install Chrome Frame. Remove this if you want to support IE 6.
			 chromium.org/developers/how-tos/chrome-frame-getting-started -->
	<!--[if lt IE 7 ]>
		<script src="//ajax.googleapis.com/ajax/libs/chrome-frame/1.0.3/CFInstall.min.js"></script>
		<script>window.attachEvent('onload',function(){CFInstall.check({mode:'overlay'})})</script>
	<![endif]-->
	<script src="resources/js/bootstrap-collapse.js"></script>
    <script src="resources/js/bootstrap-alert.js"></script>
    <script src="resources/js/bootstrap-modal.js"></script>
    <script src="resources/js/bootstrap-tab.js"></script>
    <script src="resources/js/bootstrap-tooltip.js"></script>
    <script src="resources/js/bootstrap-popover.js"></script>
</body>
</html>