<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
	<jsp:include page="/WEB-INF/views/include/headtag.jsp"></jsp:include>
	<script>
		$(document).ready(function() {
			var ctxpath = "${ pageContext.request.contextPath }";
		});
	</script>	
</head>
<body style="padding-top: 0px;">
	<div id="wrapper" class="container-fluid">

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

				<div class="row">
					<div class="col-md-6">
						<h1>
							<span class="fa fa-laptop fa-fw"></span><strong>Setup</strong>
						</h1>
						
						<hr/>
						
						<div class="well">
							<form:form id="setupForm" role="form" class="form-horizontal" modelAttribute="setupForm" action="${pageContext.request.contextPath}/setup/install" data-parsley-validate="parsley">
								<form:hidden path="storeName.storeId"/>
								<div class="form-group">
									<label for="inputStoreName" class="control-label">Store Name</label>
									<form:input type="text" class="form-control" id="inputStoreName" name="inputStoreName" path="storeName.storeName" placeholder="Store Name" data-parsley-required="true" data-parsley-length="[6, 30]" data-parsley-trigger="keyup"></form:input>
								</div>
								<div class="form-group">
									<label for="inputAddress1" class="control-label">Address</label>
									<form:input type="text" class="form-control" id="inputAddress1" name="inputAddress1" path="storeName.storeAddress1"></form:input>
									<form:input type="text" class="form-control" id="inputAddress2" name="inputAddress2" path="storeName.storeAddress2"></form:input>
									<form:input type="text" class="form-control" id="inputAddress3" name="inputAddress3" path="storeName.storeAddress3"></form:input>
								</div>
	
								<form:hidden path="adminName.userId"/>
								<div class="form-group">
									<label for="inputUserName" class="control-label">User Name</label>
									<form:input path="adminName.userName" type="text" class="form-control" id="inputUserName" name="inputUserName" placeholder="Enter User Name" data-parsley-length="[4, 10]" data-parsley-pattern="^[a-zA-Z0-9]+$" data-parsley-required="true" data-parsley-trigger="keyup"></form:input>
								</div>
								<div class="form-group">
									<label for="inputPassword" class="control-label">Password</label>
									<form:input path="adminName.userPassword" type="password" class="form-control" id="inputPassword" name="inputPassword" placeholder="Password" data-parsley-required="true" data-parsley-trigger="keyup"></form:input>
								</div>
								<div class="form-group">
									<button id="submitButton" type="submit" class="btn btn-primary">Install</button>
								</div>
							</form:form>
						</div>
					</div>
					<div class="col-md-6">
						<h1>
							<span class="fa fa-wrench fa-fw"></span><strong>Tools</strong>
						</h1>
		
						<hr>

						<div class="panel panel-default">
							<div class="panel-body">
								<input type="text" class="form-control" id="user_resetpassword" placeholder="Reset Password">
								<a href="${ pageContext.request.contextPath }/setup/user/reset_pass" class="btn btn-default btn-xs">Reset Password</a>
							</div>
						</div>
					
					</div>
				</div>
			</div>
		</div>
		<jsp:include page="/WEB-INF/views/include/footer.jsp"></jsp:include>		
	
	</div>	
</body>
</html>
