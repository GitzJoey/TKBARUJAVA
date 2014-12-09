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
				window.location.href("${ pageContext.request.contextPath }/supplier/list.html");
			});
			
			$('#addBank').click(function() {

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
					$('#editTableSelection').attr("href", ctxpath + "/supplier/edit/" + id + ".html");	
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
					$('#deleteTableSelection').attr("href", ctxpath + "/supplier/delete/" + id + ".html");	
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
					<span class="fa fa-building-o fa-fw"></span>&nbsp;Supplier
				</h1>

				<c:choose>
					<c:when test="${PAGEMODE == 'PAGEMODE_PAGELOAD' || PAGEMODE == 'PAGEMODE_LIST' || PAGEMODE == 'PAGEMODE_DELETE'}">
						<div class="panel panel-default">
							<div class="panel-heading">
								<h1 class="panel-title">
									<span class="fa fa-building-o fa-fw fa-2x"></span>Supplier List
								</h1>
							</div>
							<div class="panel-body">
								<div class="table-responsive">
									<table class="table table-bordered table-hover">
										<thead>
											<tr>
												<th width="5%">&nbsp;</th>
												<th width="15%">Company Name</th>
												<th width="25%"></th>
												<th width="35%"></th>
												<th width="15%"></th>
												<th width="5%"></th>
											</tr>
										</thead>
										<tbody>
											<c:if test="${ not empty supplierList }">
												<c:forEach var="i" varStatus="status" items="${ supplierList }">
													<tr>
														<td align="center"><input id="cbx_<c:out value="${ i.supplierId }"/>" type="checkbox" value="<c:out value="${ i.supplierId }"/>"/></td>
														<td><c:out value="${ i.companyName }"></c:out></td>
														<td></td>
														<td></td>
														<td>&nbsp;</td>
														<td>&nbsp;</td>
													</tr>
												</c:forEach>
											</c:if>
										</tbody>
									</table>
								</div>
								<a id="addNew" class="btn btn-sm btn-primary" href="${pageContext.request.contextPath}/supplier/add.html"><span class="fa fa-plus fa-fw"></span>&nbsp;Add</a>&nbsp;&nbsp;&nbsp;
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
											<span class="fa fa-plus fa-fw fa-2x"></span>&nbsp;Add Supplier
										</c:when>
										<c:otherwise>
											<span class="fa fa-edit fa-fw fa-2x"></span>&nbsp;Edit Supplier
										</c:otherwise>
									</c:choose>
								</h1>
							</div>
							<div class="panel-body">
								<form:form id="userForm" role="form" class="form-horizontal" modelAttribute="supplierForm" action="${pageContext.request.contextPath}/supplier/save.html">
									<form:hidden path="supplierId"/>
									<div class="form-group">
										<label for="inputCompanyName" class="col-sm-2 control-label">Company Name</label>
										<div class="col-sm-3">
											<form:input type="text" class="form-control" id="inputCompanyName" name="inputCompanyName" path="companyName" placeholder="Enter Company Name"></form:input>
										</div>
									</div>
									<div class="form-group">
										<label for="inputCompanyAddress" class="col-sm-2 control-label">Address</label>
										<div class="col-sm-3">
											<form:input type="text" class="form-control" id="inputCompanyAddress" name="inputCompanyAddress" path="companyAddress" placeholder="Enter Company Address"></form:input>
										</div>
									</div>
									<div class="form-group">
										<label for="inputCity" class="col-sm-2 control-label">City</label>
										<div class="col-sm-5">											
											<form:input type="text" class="form-control" id="inputCity" name="inputCity" path="companyCity" placeholder="City"></form:input>
										</div>
									</div>
									<div class="form-group">
										<label for="inputPhoneNumber" class="col-sm-2 control-label">Phone Number</label>
										<div class="col-sm-10">
											<form:input type="text" class="form-control" id="inputAddress1" name="inputPhoneNumber" path="compPhone"  placeholder="Enter Company Phone Number"></form:input>
										</div>
									</div>
									<div class="form-group">
										<label for="inputFax" class="col-sm-2 control-label">Fax</label>
										<div class="col-sm-10">
											<form:input type="text" class="form-control" id="inputFax" name="inputFax" path="compFax" placeholder="Fax"></form:input>
										</div>
									</div>
									<div class="form-group">
										<label for="inputSupplierRemarks" class="col-sm-2 control-label">Remarks</label>
										<div class="col-sm-5">
											<form:input type="text" class="form-control" id="inputSupplierRemarks" path="supplierRemarks" name="inputSupplierRemarks" placeholder="Remarks"></form:input>
										</div>
									</div>
									<div class="form-group">
										<label for="inputStatus" class="col-sm-2 control-label">Status</label>
										<div class="col-sm-2">
											<form:select class="form-control" path="companyStatus">
												<form:options items="${ statusDDL }" itemValue="lookupCode" itemLabel="lookupDescription"/>
											</form:select>
										</div>
									</div>
									<div class="form-group">
										<label for="inputBankAccTable" class="col-sm-2 control-label">Bank Account</label>
										<div class="col-sm-10">
											<table class="table table-bordered table-hover">
												<thead>
													<tr>
														<th width="5%">&nbsp;</th>
														<th width="20%">Bank Name</th>
														<th width="10%">Short Name</th>
														<th width="15%">Account</th>
														<th width="35%">Remarks</th>
														<th width="5%">Status</th>
													</tr>
												</thead>
											</table>
											<table class="table borderless nopaddingrow">
												<tr>
													<td colspan="2">
														<button id="addBank" type="button" class="btn btn-primary"><span class="fa fa-plus fa-fw"></span></button>
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
