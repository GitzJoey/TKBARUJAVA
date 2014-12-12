<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<!DOCTYPE html>
<html>
<head>
	<jsp:include page="/WEB-INF/views/include/headtag.jsp"></jsp:include>
	<script>
		$(document).ready(function() {
			$('#cancelButton').click(function() {				
				window.location.href("about:blank");
			});
			
			$('#addPhone').click(function() {
				var ctxpath = "${ pageContext.request.contextPath }";
				
				$.get(ctxpath + "/fragment/addphone.html", {count: 1}).done(function(data) {
					$('#phoneTable > tbody').append(data);
				});
				return false;
			});
			
			$('#personForm').bootstrapValidator({
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
					<span class="fa fa-users fa-fw"></span>&nbsp;Person
				</h1>

				<c:choose>
					<c:when test="${PAGEMODE == 'PAGEMODE_ADD' || PAGEMODE == 'PAGEMODE_EDIT'}">
						<div class="panel panel-default">
							<div class="panel-heading">
								<h1 class="panel-title">
									<c:choose>
										<c:when test="${PAGEMODE == 'PAGEMODE_ADD'}">
											<span class="fa fa-plus fa-fw fa-2x"></span>&nbsp;Add Person
										</c:when>
										<c:otherwise>
											<span class="fa fa-edit fa-fw fa-2x"></span>&nbsp;Edit Person
										</c:otherwise>
									</c:choose>
								</h1>
							</div>
							<div class="panel-body">
								<form:form id="personForm" role="form" class="form-horizontal" modelAttribute="personForm" action="${pageContext.request.contextPath}/${cat}/person/save.html">
									<div class="form-group">
										<label for="inputFirstName" class="col-sm-2 control-label">First Name</label>
										<div class="col-sm-5">											
											<form:input path="firstName" type="text" class="form-control" id="inputFirstName" name="inputFirstName" placeholder="First Name"></form:input>
										</div>
									</div>
									<div class="form-group">
										<label for="inputLastName" class="col-sm-2 control-label">Last Name</label>
										<div class="col-sm-5">
											<form:input path="lastName" type="text" class="form-control" id="inputLastName" name="inputLastName" placeholder="Last Name"></form:input>
										</div>
									</div>
									<div class="form-group">
										<label for="inputAddress1" class="col-sm-2 control-label">Address</label>
										<div class="col-sm-10">
											<form:input path="addressLine1" type="text" class="form-control" id="inputAddress1" name="inputAddress1" placeholder="Address 1"></form:input>
										</div>
									</div>
									<div class="form-group">
										<label for="inputAddress2" class="col-sm-2 control-label">&nbsp;</label>
										<div class="col-sm-10">
											<form:input path="addressLine2" type="text" class="form-control" id="inputAddress2" name="inputAddress2" placeholder="Address 2"></form:input>
										</div>
									</div>
									<div class="form-group">
										<label for="inputAddress3" class="col-sm-2 control-label">&nbsp;</label>
										<div class="col-sm-10">
											<form:input path="addressLine3" type="text" class="form-control" id="inputAddress3" name="inputAddress3" placeholder="Address 3"></form:input>
										</div>
									</div>
									<div class="form-group">
										<label for="inputEmailAddress" class="col-sm-2 control-label">Email Address</label>
										<div class="col-sm-6">
											<form:input path="emailAddr" type="text" class="form-control" id="inputEmailAddress" name="inputEmailAddress" placeholder="Enter Email Address"></form:input>
										</div>
									</div>
									<div class="form-group">
										<label for="inputStatus" class="col-sm-2 control-label">Phone List</label>
										<div class="col-sm-9">
											<table id="phoneTable" class="table borderless nopaddingrow">
												<tbody>
													<c:forEach items="${ personForm.phoneList }" var="pList" varStatus="phoneIdx">
														<tr>
															<td width="25%">
																<spring:bind path="personForm.phoneList[${phoneIdx.index}].providerName">
																	<form:input type="text" class="form-control" id="inputProvider" name="inputProvider" path="${ status.expression }" placeholder="Provider"></form:input>
																</spring:bind>
															</td>
															<td>
																<spring:bind path="personForm.phoneList[${phoneIdx.index}].phoneNumber">
																	<form:input type="text" class="form-control" id="inputPhoneNum" name="inputPhoneNum" path="${ status.expression }" placeholder="Phone Number"></form:input>
																</spring:bind>
															</td>
														</tr>													
													</c:forEach>
												</tbody>
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
