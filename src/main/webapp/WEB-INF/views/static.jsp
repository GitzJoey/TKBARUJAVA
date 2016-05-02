<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
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
						<a href="${pageContext.request.contextPath}/login.html"><span class="fa fa-power-off"></span>&nbsp;<spring:message code="static_jsp.menu.login" text="Login"/></a>
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
		</div>
		
		<div class="row">
			<div class="col-md-3">
				<div class="panel panel-default">
					<div class="panel-body">
						<div class="row">
							<div class="col-md-6">
								<img src="${ pageContext.request.contextPath }/resources/images/palm-oil.jpg" class="img-responsive img-circle"/>
							</div>
							<div class="col-md-6 text-right">
								<div class="huge">9800<span class="small">/Kg</span></div>
								<div class="tiny">17 March 2015</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="col-md-3">
				<div class="panel panel-default">
					<div class="panel-body">
						<div class="row">
							<div class="col-md-6">
								<img src="${ pageContext.request.contextPath }/resources/images/soybeans.jpg" class="img-responsive img-circle"/>
							</div>
							<div class="col-md-6 text-right">
								<div class="huge">7100<span class="small">/Kg</span></div>
								<div class="tiny">17 March 2015</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="col-md-3">
				<div class="panel panel-default">
					<div class="panel-body">
						<div class="row">
							<div class="col-md-6">
								<img src="${ pageContext.request.contextPath }/resources/images/sugar.jpg" class="img-responsive img-circle"/>
							</div>
							<div class="col-md-6 text-right">
								<div class="huge">8900<span class="small">/Kg</span></div>
								<div class="tiny">17 March 2015</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="col-md-3">
				<div class="panel panel-default">
					<div class="panel-body">
						<div class="row">
							<div class="col-md-6">
								<img src="${ pageContext.request.contextPath }/resources/images/wheat.jpg" class="img-responsive img-circle"/>
							</div>
							<div class="col-md-6 text-right">
								<div class="huge">2600<span class="small">/Kg</span></div>
								<div class="tiny">17 March 2015</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		
		<div class="row">
			<div class="col-md-8">
				<div class="page-header">
				  <h1><span class="fa fa-envelope"></span>&nbsp;Contacts</h1>
				</div>
				<div class="well">
					<form id="staticForm" role="form" class="form-horizontal" action="${pageContext.request.contextPath}/static/sendform" data-parsley-validate="parsley">
						<div class="form-group">
							<label for="inputName" class="control-label pull-left"><spring:message code="static_jsp.name" text="Name"/></label>
							<input type="text" class="form-control" id="inputName" name="inputName" placeholder="Enter Name" data-parsley-required="true"/>
						</div>
						<div class="form-group">
							<label for="inputPhoneNum" class="control-label"><spring:message code="static_jsp.phone_num" text="Phone Number"/></label>
							<input type="text" class="form-control" id="inputPhoneNum" name="inputPhoneNum" placeholder="Enter Phone Number" data-parsley-required="true"/>
						</div>
						<div class="form-group">
							<label for="inputEmail" class="control-label"><spring:message code="static_jsp.email" text="Email"/></label>
							<input type="text" class="form-control" id="inputEmail" name="inputEmail" placeholder="Enter Email" data-parsley-required="true"/>
						</div>
						<div class="form-group">
							<label for="inputMessage" class="control-label"><spring:message code="static_jsp.message" text="Message"/></label>
							<textarea id="inputMessage" class="form-control" rows="5"></textarea>
						</div>
						<div class="form-group">
							<div class="btn-toolbar">
								<button id="submitButton" type="submit" class="btn btn-primary"><spring:message code="common.send_button" text="Send"/></button>
								<button id="cancelButton" type="reset" class="btn btn-primary"><spring:message code="common.cancel_button" text="Cancel"/></button>
							</div>
						</div>
					</form>
				</div>
			</div>
			<div class="col-md-4">
				<div class="row">
					<div class="page-header">
					  <h1><span class="fa fa-phone"></span>&nbsp;Phone</h1>
					</div>
					<div class="col-md-12">
						<h4 class="pull-right"><strong>Telkomsel:</strong> +62 812-1234-1234</h4>
						<h4 class="pull-right"><strong>Mentari:</strong> +62 812-1234-1234</h4>
						<h4 class="pull-right"><strong>XL:</strong> +62 812-1234-1234</h4>
					</div>
				</div>
			</div>
		</div>

		<div class="row">
			<div class="col-md-12">
				<p class="pull-right">
					IP Address: <c:out value="${ pageContext.request.remoteAddr }"/><br/>							
				</p>
			</div>
		</div>
		
		<hr>
		
		<jsp:include page="/WEB-INF/views/include/footer.jsp"></jsp:include>

	</div>
</body>
</html>
