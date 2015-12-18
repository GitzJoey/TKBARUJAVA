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
<body>
	<div id="wrapper" class="container-fluid">

		<jsp:include page="/WEB-INF/views/include/topmenu.jsp"></jsp:include>

		<div class="row">
			<div class="col-md-2">				
				<jsp:include page="/WEB-INF/views/include/sidemenu.jsp">
					<jsp:param value="true" name="hideMenu"/>
				</jsp:include>
			</div>
			<div id="content" class="col-md-10">
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

				<div class="panel panel-default">
					<div class="panel-heading">
						<h1 class="panel-title">
							<span class="fa fa-exclamation-circle fa-fw fa-2x"></span><spring:message code="change_pass_jsp.title" text="Change Password"/>
						</h1>
					</div>
					
					<c:choose>
						<c:when test="${true}">
							<div class="panel-body">
								<form:form id="userForm" role="form" class="form-horizontal" modelAttribute="userForm" action="${pageContext.request.contextPath}/change_pass/save" data-parsley-validate="parsley">
									<form:hidden path="userId"/>
									<div class="form-group">
										<label for="inputNewPassword" class="col-md-2 control-label"><spring:message code="change_pass_jsp.new_password" text="New Password"/></label>
										<div class="col-md-4">
											<form:password id="inputNewPassword" class="form-control" path="userPassword" data-parsley-required="true"/>
										</div>
									</div>
									<div class="form-group">
										<label for="inputConfirmNewPassword" class="col-md-2 control-label"><spring:message code="change_pass_jsp.confirm_new_password" text="Confirm New Password"/></label>
										<div class="col-md-4">
											<input id="inputConfirmNewPassword" type="password" class="form-control" data-parsley-required="true" data-parsley-equalto="#inputNewPassword"/>
										</div>
									</div>
									<div class="form-group">
										<label for="submitButton" class="col-md-2 control-label sr-only"></label>
										<div class="col-md-10">
											<button type="submit" id="submitButton" class="btn btn-primary"><spring:message code="common.submit_button" text="Submit"/></button>
										</div>
									</div>
								</form:form>
							</div>
						</c:when>
					</c:choose>
				</div>
			</div>
		</div>
		
		<jsp:include page="/WEB-INF/views/include/footer.jsp"></jsp:include>		
	
	</div>	
</body>
</html>
