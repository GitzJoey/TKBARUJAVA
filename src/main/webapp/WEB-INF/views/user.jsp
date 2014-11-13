<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<jsp:include page="/WEB-INF/views/include/headtag.jsp"></jsp:include>
	<script>
		$(document).ready(function() {
			$('#userForm').bootstrapValidator({
       			feedbackIcons: {
           			valid: 'glyphicon glyphicon-ok',
           			invalid: 'glyphicon glyphicon-remove',
					validating: 'glyphicon glyphicon-refresh'
       			},
       			submitButtons: '[type="submit"]',
       			fields: {
       				inputUserName: {
               			validators: {
                   			notEmpty: { },
							stringLength: { min: 4, max: 10 },
							regexp: { regexp: /^[a-zA-Z0-9]+$/ },
	                   		different: { field: 'inputUserName' }
               			}
           			},
           			inputPassword: {
               			validators: {
                   			notEmpty: {	},
                   			different: { field: 'inputUserName' },
                   			stringLength: { min: 6 }
               			}
           			}
       			}
			});
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
			<div id="content" class="col-md-10 offset-md-1">
				<h1>
					<span class="fa fa-user fa-fw"></span>&nbsp;User 
				</h1>
				
				<c:if test="${ERRORPAGE == 'ERRORPAGE_SHOW'}">
	    			<div class="alert alert-danger alert-dismissible" role="alert">
	  					<button type="button" class="close" data-dismiss="alert">
	  						<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
	  					</button>
	  					<h4><strong>Warning!</strong></h4>
	  					<br>
	  					${errorMessageText}
					</div>
				</c:if>
				
				<c:choose>
					<c:when test="${PAGEMODE == 'PAGEMODE_PAGELOAD' || PAGEMODE == 'PAGEMODE_LIST' || PAGEMODE == 'PAGEMODE_DELETE'}">
						<div class="panel panel-default">
							<div class="panel-heading">
								<h1 class="panel-title">
									<span class="fa fa-user fa-fw fa-2x"></span>User List
								</h1>
							</div>
							<div class="panel-body">
								<div class="table-responsive">
									<table class="table table-bordered table-hover">
										<thead>
											<tr>
												<th width="5%">&nbsp;</th>
												<th width="15%">User Name</th>
												<th width="25%">Name</th>
												<th width="35%">Address</th>
												<th width="15%">Phone</th>
												<th width="5%">Status</th>
											</tr>
										</thead>
										<tbody>
											<c:if test="${not empty userList}">
												<c:forEach var="i" varStatus="status" items="${userList}">
													<tr>
														<td>&nbsp;</td>
														<td><c:out value="${i.userName}"></c:out></td>
														<td>&nbsp;</td>
														<td>&nbsp;</td>
														<td>&nbsp;</td>
														<td>&nbsp;</td>
													</tr>
												</c:forEach>
											</c:if>
										</tbody>
									</table>
								</div>
								<a class="btn btn-sm btn-primary" href="${pageContext.request.contextPath}/admin/user/add.html"><span class="fa fa-plus fa-fw"></span>&nbsp;Add</button></a>&nbsp;&nbsp;&nbsp;
								<button type="button" class="btn btn-sm btn-primary"><span class="fa fa-plus fa-fw"></span>&nbsp;Edit</button>&nbsp;&nbsp;&nbsp;
								<button type="button" class="btn btn-sm btn-primary"><span class="fa fa-plus fa-fw"></span>&nbsp;Delete</button>
							</div>
						</div>
					</c:when>
					<c:when test="${PAGEMODE == 'PAGEMODE_ADD' || PAGEMODE == 'PAGEMODE_EDIT'}">
						<div class="panel panel-default">
							<div class="panel-heading">
								<h1 class="panel-title">
									<c:choose>
										<c:when test="${PAGEMODE == 'PAGEMODE_ADD'}">
											<span class="fa fa-plus fa-fw fa-2x"></span>&nbsp;Add User
										</c:when>
										<c:otherwise>
											<span class="fa fa-write fa-fw fa-2x"></span>&nbsp;Edit User
										</c:otherwise>
									</c:choose>
								</h1>
							</div>
							<div class="panel-body">
								<form id="userForm" role="form" class="form-horizontal">
									<div class="form-group">
										<label for="inputUserName" class="col-sm-2 control-label">User Name</label>
										<div class="col-sm-3">
											<input type="text" class="form-control" id="inputUserName" name="inputUserName" placeholder="Enter User Name">
										</div>
									</div>
									<div class="form-group">
										<label for="inputPassword" class="col-sm-2 control-label">Password</label>
										<div class="col-sm-3">
											<input type="password" class="form-control" id="inputPassword" name="inputPassword" placeholder="Password">
										</div>
									</div>
									<div class="form-group">
										<label for="inputFirstName" class="col-sm-2 control-label">First Name</label>
										<div class="col-sm-5">
											<input type="text" class="form-control" id="inputFirstName" placeholder="First Name">
										</div>
									</div>
									<div class="form-group">
										<label for="inputLastName" class="col-sm-2 control-label">Last Name</label>
										<div class="col-sm-5">
											<input type="text" class="form-control" id="inputLastName" placeholder="Last Name">
										</div>
									</div>
									<div class="form-group">
										<label for="inputAddress1" class="col-sm-2 control-label">Address</label>
										<div class="col-sm-10">
											<input type="text" class="form-control" id="inputAddress1" placeholder="Address 1">
										</div>
									</div>
									<div class="form-group">
										<label for="inputAddress2" class="col-sm-2 control-label">&nbsp;</label>
										<div class="col-sm-10">
											<input type="text" class="form-control" id="inputAddress2" placeholder="Address 2">
										</div>
									</div>
									<div class="form-group">
										<label for="inputAddress3" class="col-sm-2 control-label">&nbsp;</label>
										<div class="col-sm-10">
											<input type="text" class="form-control" id="inputAddress3" placeholder="Address 3">
										</div>
									</div>
									<div class="form-group">
										<label for="inputRole" class="col-sm-2 control-label">Role</label>
										<div class="col-sm-2">
											<select class="form-control">
												<option>Admin</option>
											</select>
										</div>
									</div>
									<div class="form-group">
										<label for="inputStatus" class="col-sm-2 control-label">Status</label>
										<div class="col-sm-2">
											<select class="form-control">
												<option>Active</option>
												<option>Inactive</option>
											</select>
										</div>
									</div>
									<div class="col-md-3 offset-md-9">
										<div class="btn-toolbar">
											<button type="cancel" class="btn btn-default pull-right">Cancel</button>
											<button type="submit" class="btn btn-default pull-right">Submit</button>
										</div>
									</div>
								</form>
							</div>
						</div>					
					</c:when>
				</c:choose> 				
			</div>
		</div>		
	</div>	
</body>
</html>
