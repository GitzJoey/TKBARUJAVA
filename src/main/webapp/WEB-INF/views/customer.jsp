<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
	<jsp:include page="/WEB-INF/views/include/headtag.jsp"></jsp:include>
	<script>
		$(document).ready(function() {
			$('#cancelButton').click(function() {				
				window.location.href("${ pageContext.request.contextPath }/customer/list.html");
			});
			
			$('#addPhone').click(function() {
				return false;
			});
			
			$('input[type="checkbox"][id^="cbx_"]').click(function() {
				var selected = $(this);
				
				$('input[type="checkbox"][id^="cbx_"]').each(function(index, item) {
					if ($(item).attr("id") != $(selected).attr("id")) { 
						if ($(item).prop("checked")) {
							$(item).prop("checked", false);
						}
					}
				});
			})
			
			$('#editTableSelection').click(function() {
				var id = "";
				var ctxpath = "${ pageContext.request.contextPath }";
				$('input[type="checkbox"][id^="cbx_"]').each(function(index, item) {
					if ($(item).prop('checked')) {
						id = $(item).attr("value");	
					}
				});
				if (id == "") {
					jsAlert("Please select at least 1 username");
					return false;	
				} else {
					$('#editTableSelection').attr("href", ctxpath + "/customer/edit/" + id + ".html");	
				}				
			});
			
			$('#deleteTableSelection').click(function() {
				var id = "";
				var ctxpath = "${ pageContext.request.contextPath }";
				$('input[type="checkbox"][id^="cbx_"]').each(function(index, item) {
					if ($(item).prop('checked')) {
						id = $(item).attr("value");	
					}
				});
				if (id == "") {
					jsAlert("Please select at least 1 username");
					return false;	
				} else {
					$('#deleteTableSelection').attr("href", ctxpath + "/customer/delete/" + id + ".html");	
				}								
			});
			
			$('#userForm').bootstrapValidator({
       			feedbackIcons: {
           			valid: 'glyphicon glyphicon-ok',
           			invalid: 'glyphicon glyphicon-remove',
					validating: 'glyphicon glyphicon-refresh'
       			},
       			submitButtons: 'button[type="submit"]',
       			fields: {
       				inputUserName: {
               			validators: {
                   			notEmpty: { },
							stringLength: { min: 4, max: 10 },
							regexp: { regexp: /^[a-zA-Z0-9]+$/ },
	                   		different: { field: 'inputCustomerName' }
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
				<c:if test="${ERRORPAGE == 'ERRORPAGE_SHOW'}">
	    			<div class="alert alert-danger alert-dismissible collapse" role="alert">
	  					<button type="button" class="close" data-dismiss="alert">
	  						<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
	  					</button>
	  					<h4><strong>Warning!</strong></h4>
	  					<br>
	  					${errorMessageText}
					</div>
				</c:if>
				
				<div id="jsAlerts"></div>

				<h1>
					<span class="fa fa-smile-o fa-fw"></span>&nbsp;Customer 
				</h1>

				<c:choose>
					<c:when test="${PAGEMODE == 'PAGEMODE_PAGELOAD' || PAGEMODE == 'PAGEMODE_LIST' || PAGEMODE == 'PAGEMODE_DELETE'}">
						<div class="panel panel-default">
							<div class="panel-heading">
								<h1 class="panel-title">
									<span class="fa fa-smile-o fa-fw fa-2x"></span>Customer List
								</h1>
							</div>
							<div class="panel-body">
								<div class="table-responsive">
									<table class="table table-bordered table-hover">
										<thead>
											<tr>
												<th width="5%">&nbsp;</th>
												<th width="15%">Store Name</th>
												<th width="25%">Address</th>
												<th width="35%"></th>
												<th width="15%">Phone</th>
												<th width="5%">Status</th>
											</tr>
										</thead>
										<tbody>
											<c:if test="${not empty userList}">
												<c:forEach var="i" varStatus="status" items="${customerList}">
													<tr>
														<td align="center"><input id="cbx_<c:out value="${ i.customerId }"/>" type="checkbox" value="<c:out value="${ i.userId }"/>"/></td>
														<td><c:out value="${i.customerName}"></c:out></td>
														<td><c:out value="${ i.personEntity.firstName }"></c:out>&nbsp;<c:out value="${ i.personEntity.firstName }"></c:out></td>
														<td>
															<c:out value="${ i.personEntity.addressLine1 }"/><br/>
															<c:out value="${ i.personEntity.addressLine2 }"/><br/>
															<c:out value="${ i.personEntity.addressLine3 }"/>
														</td>
														<td>&nbsp;</td>
														<td>&nbsp;</td>
													</tr>
												</c:forEach>
											</c:if>
										</tbody>
									</table>
								</div>
								<a id="addNew" class="btn btn-sm btn-primary" href="${pageContext.request.contextPath}/admin/user/add.html"><span class="fa fa-plus fa-fw"></span>&nbsp;Add</a>&nbsp;&nbsp;&nbsp;
								<a id="editTableSelection" class="btn btn-sm btn-primary" href=""><span class="fa fa-edit fa-fw"></span>&nbsp;Edit</a>&nbsp;&nbsp;&nbsp;
								<a id="deleteTableSelection" class="btn btn-sm btn-primary" href=""><span class="fa fa-close fa-fw"></span>&nbsp;Delete</a>
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
											<span class="fa fa-edit fa-fw fa-2x"></span>&nbsp;Edit User
										</c:otherwise>
									</c:choose>
								</h1>
							</div>
							<div class="panel-body">
								<form:form id="userForm" role="form" class="form-horizontal" modelAttribute="userForm" action="${pageContext.request.contextPath}/admin/user/save.html">
									<div class="form-group">
										<label for="inputUserName" class="col-sm-2 control-label">User Name</label>
										<div class="col-sm-3">
											<c:choose>
												<c:when test="${PAGEMODE == 'PAGEMODE_ADD'}">
													<form:input path="userName" type="text" class="form-control" id="inputUserName" name="inputUserName" placeholder="Enter User Name"></form:input>
												</c:when>
												<c:otherwise>
													<form:input path="userName" type="text" class="form-control" id="inputUserName" name="inputUserName" placeholder="Enter User Name" disabled="true"></form:input>
												</c:otherwise>
											</c:choose>
											
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
											<form:input path="personEntity.firstName" type="text" class="form-control" id="inputFirstName" name="inputFirstName" placeholder="First Name"></form:input>
										</div>
									</div>
									<div class="form-group">
										<label for="inputLastName" class="col-sm-2 control-label">Last Name</label>
										<div class="col-sm-5">
											<form:input path="personEntity.lastName" type="text" class="form-control" id="inputLastName" name="inputLastName" placeholder="Last Name"></form:input>
										</div>
									</div>
									<div class="form-group">
										<label for="inputAddress1" class="col-sm-2 control-label">Address</label>
										<div class="col-sm-10">
											<form:input path="personEntity.addressLine1" type="text" class="form-control" id="inputAddress1" name="inputAddress1" placeholder="Address 1"></form:input>
										</div>
									</div>
									<div class="form-group">
										<label for="inputAddress2" class="col-sm-2 control-label">&nbsp;</label>
										<div class="col-sm-10">
											<form:input path="personEntity.addressLine2" type="text" class="form-control" id="inputAddress2" name="inputAddress2" placeholder="Address 2"></form:input>
										</div>
									</div>
									<div class="form-group">
										<label for="inputAddress3" class="col-sm-2 control-label">&nbsp;</label>
										<div class="col-sm-10">
											<form:input path="personEntity.addressLine3" type="text" class="form-control" id="inputAddress3" name="inputAddress3" placeholder="Address 3"></form:input>
										</div>
									</div>
									<div class="form-group">
										<label for="inputEmailAddress" class="col-sm-2 control-label">Email Address</label>
										<div class="col-sm-6">
											<form:input path="personEntity.emailAddr" type="text" class="form-control" id="inputEmailAddress" name="inputEmailAddress" placeholder="Enter Email Address"></form:input>
										</div>
									</div>
									<div class="form-group">
										<label for="inputRole" class="col-sm-2 control-label">Role</label>
										<div class="col-sm-2">
											<form:select class="form-control" path="roleFunctionEntity.roleId">
												<form:options items="${ roleDDL }" itemValue="roleId" itemLabel="roleName"></form:options>
											</form:select>
										</div>
									</div>
									<div class="form-group">
										<label for="inputStatus" class="col-sm-2 control-label">Status</label>
										<div class="col-sm-2">
											<form:select class="form-control" path="userStatus">
												<form:options items="${ statusDDL }" itemValue="lookupCode" itemLabel="lookupDescription"/>
											</form:select>
										</div>
									</div>
									<div class="form-group">
										<label for="inputStatus" class="col-sm-2 control-label">Phone List</label>
										<div class="col-sm-9">
											<table id="phoneTable" class="table borderless nopaddingrow">
												<c:forEach items="${ personEntity.phoneList }" var="phone" varStatus="ph">
													<tr>
														<td width="25%">
															<form:input type="text" class="form-control" id="inputProvider" path="phone.providerName" placeholder="Provider"></form:input>
														</td>
														<td>
															<form:input type="text" class="form-control" id="inputPhoneNum" path="phone.phoneNumber" placeholder="Phone Number"></form:input>
														</td>
													</tr>												
												</c:forEach>
											</table>
											<table class="table borderless nopaddingrow">
												<tr>
													<td colspan="2">
														<button id="addPhone" type="button" class="btn btn-primary"><span class="fa fa-plus fa-fw"></span></button>
													</td>
												</tr>
											</table>
										</div>
									</div>
									<div class="col-md-3 offset-md-9">
										<div class="btn-toolbar">
											<button id="cancelButton" type="reset" class="btn btn-primary pull-right">Cancel</button>
											<button id="submitButton" type="submit" class="btn btn-primary pull-right">Submit</button>
										</div>
									</div>
								</form:form>
							</div>
						</div>					
					</c:when>
				</c:choose> 				
			</div>
		</div>		
	</div>	
</body>
</html>
