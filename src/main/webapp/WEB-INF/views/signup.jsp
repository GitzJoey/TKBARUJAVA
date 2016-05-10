<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
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
			
			$('#cancelButton').click(function() {				
				window.location.href = ctxpath + "/static/index.html";
			});
			
			$('#userListTable').DataTable();
		});
	</script>
</head>
<body>
	<div id="wrapper" class="container">

		<div class="header clearfix">
			<nav>
				<ul class="nav nav-pills pull-right">
					<li role="presentation">
						<a href="${pageContext.request.contextPath}/static/signup.html"><span class="fa fa-pencil"></span>&nbsp;<spring:message code="signup_jsp.menu.signup" text="Sign Up"/></a>
					</li>
					<li role="presentation">
						<a href="${pageContext.request.contextPath}/login.html"><span class="fa fa-power-off"></span>&nbsp;<spring:message code="signup_jsp.menu.login" text="Login"/></a>
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
			<div id="content" class="col-md-12">
				<c:if test="${ ERRORFLAG == 'ERRORFLAG_SHOW' }">
	    			<div class="alert alert-danger alert-dismissible collapse in" role="alert">
	  					<button type="button" class="close" data-dismiss="alert">
	  						<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
	  					</button>
	  					<h4><strong>Warning!</strong></h4>
	  					<br>
	  					<c:out value="${ errorMessageText }"/>
					</div>
				</c:if>
				
				<div id="jsAlerts"></div>

				<h1>
					<span class="fa fa-user fa-fw"></span>&nbsp;<spring:message code="signup_jsp.title" text="Sign Up User"/>
				</h1>
				
				<c:choose>				
					<c:when test="${ PAGEMODE == 'PAGEMODE_ADD' || PAGEMODE == 'PAGEMODE_EDIT' }">
						<div class="panel panel-default">
							<div class="panel-body">
								<form:form id="userForm" role="form" class="form-horizontal" modelAttribute="userForm" action="${pageContext.request.contextPath}/static/signup/save" data-parsley-validate="parsley">
									<form:hidden path="userId"/>
									<div class="form-group">
										<label for="inputUserName" class="col-sm-2 control-label"><spring:message code="signup_jsp.user_name" text="User Name"/></label>
										<div class="col-sm-10">
											<c:choose>
												<c:when test="${ PAGEMODE == 'PAGEMODE_ADD' }">
													<form:input path="userName" type="text" class="form-control" id="inputUserName" name="inputUserName" placeholder="Enter User Name" data-parsley-length="[4, 10]" data-parsley-pattern="^[a-zA-Z0-9]+$" data-parsley-required="true" data-parsley-trigger="keyup"></form:input>
												</c:when>
												<c:otherwise>
													<form:input path="userName" type="text" class="form-control" id="inputUserName" name="inputUserName" placeholder="Enter User Name" readonly="true"></form:input>
												</c:otherwise>
											</c:choose>
										</div>
									</div>
									<div class="form-group">
										<label for="inputPassword" class="col-sm-2 control-label"><spring:message code="signup_jsp.password" text="Password"/></label>
										<div class="col-sm-10">
											<form:input path="userPassword" type="password" class="form-control" id="inputPassword" name="inputPassword" placeholder="Password" data-parsley-required="true" data-parsley-minlength="6" data-parsley-trigger="keyup"></form:input>
										</div>
									</div>
									<hr>
									<form:hidden path="personId"/>
									<form:hidden path="personEntity.personId"/>
									<div class="form-group">
										<label for="inputFirstName" class="col-sm-2 control-label"><spring:message code="signup_jsp.first_name" text="first name"/></label>
										<div class="col-sm-10">
											<form:input path="personEntity.firstName" type="text" class="form-control" id="inputFirstName" name="inputFirstName" placeholder="First Name" data-parsley-required="true" data-parsley-pattern="^[a-zA-Z]+$" data-parsley-trigger="keyup"></form:input>
										</div>
									</div>
									<div class="form-group">
										<label for="inputLastName" class="col-sm-2 control-label"><spring:message code="signup_jsp.last_name" text="Last Name"/></label>
										<div class="col-sm-10">
											<form:input path="personEntity.lastName" type="text" class="form-control" id="inputLastName" name="inputLastName" placeholder="Last Name" data-parsley-required="true" data-parsley-pattern="^[a-zA-Z]+$"  data-parsley-trigger="keyup"></form:input>
										</div>
									</div>
									<div class="form-group">
										<label for="inputEmailAddress" class="col-sm-2 control-label"><spring:message code="signup_jsp.email_address" text="email address"/></label>
										<div class="col-sm-10">
											<form:input path="personEntity.emailAddr" type="text" class="form-control" id="inputEmailAddress" name="inputEmailAddress" placeholder="Enter Email Address"></form:input>
										</div>
									</div>
									<div class="form-group">
										<label for="inputButton" class="col-sm-2 control-label">&nbsp;</label>
										<div class="col-sm-10">
											<div class="btn-toolbar">
												<button id="submitButton" type="submit" class="btn btn-primary"><spring:message code="common.submit_button" text="Submit"/></button>
												<button id="cancelButton" type="reset" class="btn btn-primary"><spring:message code="common.cancel_button" text="Cancel"/></button>												
											</div>
										</div>
									</div>
								</form:form>
							</div>
						</div>
					</c:when>
				</c:choose> 				
			</div>
		</div>
		
		<jsp:include page="/WEB-INF/views/include/footer.jsp"></jsp:include>
	
	</div>
</body>
</html>
