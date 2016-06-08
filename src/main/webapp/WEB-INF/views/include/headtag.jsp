<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
    
    <c:choose>
	    <c:when test="${ not empty pageTitle }">
	    	<title>Toko Baru - ${ pageTitle }</title>
	    </c:when>
		<c:otherwise>
			<title>Toko Baru</title>
		</c:otherwise>
    </c:choose>
    
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="Toko, Baru, Wangon">
    <meta name="author" content="GitzJoey">
    <sec:csrfMetaTags/>
    
    <link rel="shortcut icon" type="image/x-icon" href="${pageContext.request.contextPath}/resources/images/favicon.ico" />
	
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/theme-yeti/css/bootstrap.min.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/awesome-bootstrap-checkbox/awesome-bootstrap-checkbox.css">
	<link rel="stylesheet" type="text.css" href="${pageContext.request.contextPath}/resources/parsleyjs/src/parsley.css">
	<link rel="stylesheet" type="text/css" media="screen" href="${pageContext.request.contextPath}/resources/font-awesome/css/font-awesome.min.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/bootstrap-fileinput/css/fileinput.min.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/datetimepicker/jquery.datetimepicker.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/datatables/datatables.min.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/metisMenu/dist/metisMenu.min.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/scrollToTop/css/scrollToTop.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/bootstrap-horizon/bootstrap-horizon.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/custom.css">
	
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/jQuery/jquery-2.x.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/datetimepicker/build/jquery.datetimepicker.full.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/bootstrap/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/JSON/json2.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/flot/excanvas.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/flot/jquery.flot.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/flot/jquery.flot.pie.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/flot/jquery.flot.resize.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/flot/jquery.flot.time.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/flot.tooltip/js/jquery.flot.tooltip.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/datatables/datatables.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/bootstrap-fileinput/js/fileinput.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/parsley-config.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/parsleyjs/dist/parsley.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/parsleyjs/dist/i18n/en.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/parsleyjs/dist/i18n/id.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/parsley-locale.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/metisMenu/dist/metisMenu.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/metisMenu.cookie.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/autoNumeric/autoNumeric.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/scrollToTop/js/scrollToTop.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/jquery.easing/jquery.easing.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/jquery-mousewheel/jquery.mousewheel.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/custom.js"></script>	

	<script type="text/javascript">
    	$(document).ready(function() {
			$(function () {
    			$('#menu').metisMenu({
    				cookieName: "metisMenuState"
    			});
    		});

			var sessionTimeout = 900000;
			function timeout() {
			    setTimeout(function () {
			    	sessionTimeout = (sessionTimeout - 1000);
			    	if (sessionTimeout >= 0) {
			    		$('#timeoutCount').text(sessionTimeout / 1000);
			    	}
			        timeout();
			    }, 1000);
			}
			timeout();
			
			$('#return-to-top').click(function() {
				$('body, html').animate({
			        scrollTop : 0
			    }, 500);
			});
    	});
	</script>