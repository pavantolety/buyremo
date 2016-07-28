<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<tiles:insertDefinition name="user.notifications.definition">
	<tiles:putAttribute name="body">
	<div class="container text-center">
		<div class="logo-404">
			<a href="/buyremo/index"><img src="images/home/logo.png" alt="" /></a>
		</div>
		<div class="content-404">
			<h1><b>Success!</b><c:out value=' ${message}' /></h1>
			<h2><a href="/buyremo/login">Please login to continue..</a></h2>
		</div>
	</div>
	
	<script src="js/jquery.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/jquery.scrollUp.min.js"></script>
	<script src="js/price-range.js"></script>
	<script src="js/jquery.prettyPhoto.js"></script>
	<script src="js/main.js"></script>
  	</tiles:putAttribute>
</tiles:insertDefinition>