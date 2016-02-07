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
				<jsp:include page="/WEB-INF/views/include/sidemenu.jsp"></jsp:include>
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

				<h1>
					<span class="fa fa-user fa-fw"></span>&nbsp;<spring:message code="user_profile_jsp.user_profile" text="User Profile"></spring:message>
				</h1>
				
				<c:choose>
					<c:when test="${true}">
						<div class="panel panel-default">
							<div class="panel-heading">
								<h1 class="panel-title">
									<span class="fa fa-user fa-fw fa-2x"></span><c:out value="${ userForm.personEntity.firstName }"/>&nbsp;<c:out value="${ userForm.personEntity.lastName }"/>&nbsp;(<c:out value="${ userForm.userName }"/>)
								</h1>
							</div>
							<div class="panel-body">
								<div class="row">
									<div class="col-md-3" align="center">
										<form:hidden path="userForm.userId"/>
										<br/>
										<img class="img-responsive" alt="user" src="${ pageContext.request.contextPath }/resources/images/def-user.png">
									</div>
									<div class="col-md-9"> 
										<table class="table">
											<thead>
												<tr>
													<th width="15%">&nbsp;</th>
													<th width="85%">&nbsp;</th>
												</tr>
											</thead>
											<tbody>
												<tr>
													<td><spring:message code="user_profile_jsp.user_name" text="User Name"/></td>
													<td><c:out value="${ userForm.userName }"/></td>
												</tr>
												<tr>
													<td><spring:message code="user_profile_jsp.user_role" text="Role"/></td>
													<td><c:out value="${ userForm.roleEntity.roleName }"/></td>
												</tr>
												<tr>
													<td><spring:message code="user_profile_jsp.name" text="Name"/></td>
													<td><c:out value="${ userForm.personEntity.firstName }"/>&nbsp;<c:out value="${ userForm.personEntity.lastName }"/></td>
												</tr>
												<tr>
													<td><spring:message code="user_profile_jsp.address" text="Address"/></td>
													<td>
														<c:out value="${ userForm.personEntity.addressLine1 }"/><br/>
														<c:out value="${ userForm.personEntity.addressLine2 }"/><br/>
														<c:out value="${ userForm.personEntity.addressLine3 }"/>
													</td>
												</tr>
												<tr>
													<td><spring:message code="user_profile_jsp.email" text="Email"/></td>
													<td><c:out value="${ userForm.personEntity.emailAddr }"/></td>
												</tr>
												<tr>
													<td><spring:message code="user_profile_jsp.phone_number" text="Phone Number"/></td>
													<td>
														<c:forEach items="${ userForm.personEntity.phoneList }" var="i">
															(&nbsp;<c:out value="${ i.providerLookup.lookupValue }"/>&nbsp;)&nbsp;&nbsp;&nbsp;<c:out value="${ i.phoneNumber }"/><br/>
														</c:forEach>
													</td>
												</tr>
											</tbody>
										</table>
     									<a href="${ pageContext.request.contextPath }/user/profile/changepass" class="btn btn-primary"><span class="fa fa-key fa-fw"></span>&nbsp;<spring:message code="common.change_password" text="Change Password"/></a>
									</div>
								</div>
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
