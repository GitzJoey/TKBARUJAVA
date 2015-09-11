<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<!DOCTYPE html>
<html>
<head>		
	<jsp:include page="/WEB-INF/views/include/headtag.jsp"></jsp:include>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/static.css">
	<script>
		$(document).ready(function() {
			var ctxpath = "${ pageContext.request.contextPath }";
		});
	</script>	
</head>
<body>
	<div class="container">
		<div class="header clearfix">
			<nav>
				<ul class="nav nav-pills pull-right">
					<li role="presentation">
						<a href="${pageContext.request.contextPath}/static/index.html"><spring:message code="static_jsp.menu.home" text="Home"/></a>
					</li>
					<li role="presentation">
						<a href="${pageContext.request.contextPath}/static/index.html#aboutus"><spring:message code="static_jsp.menu.about_us" text="About Us"/></a>
					</li>
					<li role="presentation">
						<a href="${pageContext.request.contextPath}/static/index.html#contacts"><spring:message code="static_jsp.menu.contacts" text="Contact"/></a>
					</li>
					<li role="presentation">
						<a href="${pageContext.request.contextPath}/login.html"><spring:message code="static_jsp.menu.login" text="Login"/></a>
					</li>
				</ul>
			</nav>
		</div>
		<br/>
		<div class="jumbotron jumbotron-wallpaper">
			<div class="row">
				<div class="col-md-12">
					<h1 class="pull-right text-right">
						<strong><c:out value="${ storeData.storeName }"/></strong>
					</h1>
				</div>
			</div>
			<div class="row">
				<div class="col-md-12">
					<h4 class="pull-right text-right">
						<c:out value="${ storeData.storeAddress1 }"/><br/>
						<c:out value="${ storeData.storeAddress2 }"/><br/>
						<c:out value="${ storeData.storeAddress3 }"/>
					</h4>
				</div>
			</div>
			<br/>
			<br/>
			<br/>
			<br/>
			<br/>
			<br/>
			<br/>
			<br/>
			<br/>
			<br/>
			<br/>
		</div>

		<div class="row">
			<div class="col-md-8">
				<div class="page-header">
				  <h1>About Us</h1>
				</div>			
				<p>Donec id elit non mi porta gravida at eget metus. Maecenas faucibus mollis interdum. Donec id elit non mi porta gravida at eget metus. Maecenas faucibus mollis interdum. Donec id elit non mi porta gravida at eget metus. Maecenas faucibus mollis interdum. Donec id elit non mi porta gravida at eget metus. Maecenas faucibus mollis interdum.</p>
				<br/>
				<br/>
				<br/>
				<br/>
				<br/>
				<br/>
				<br/>
				<br/>
				<br/>
				<br/>
				<br/>
			</div>
			<div class="col-md-4">
				<div class="page-header">
				  <h1>Contacts</h1>
				</div>			
			</div>
		</div>

		<jsp:include page="/WEB-INF/views/include/footer.jsp"></jsp:include>

	</div>
</body>
</html>
