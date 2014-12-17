<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
	<jsp:include page="/WEB-INF/views/include/headtag.jsp"></jsp:include>
	<script>
		$(document).ready(function() {
			var ctxpath = "${ pageContext.request.contextPath }";
			
			$('#addBankAcc').click(function() {
				$('#bankAccListInputPanel input').each(function(index) { $(this).val(''); });

				$('#bankAccinputMode').val("ADD");
				$('#bankAccListPanel').hide();
				$('#bankAccListInputPanel').show();				
			});

			$('input[id^="cbx_bankAccId_"]').click(function() {
				var selected = $(this);	
				
				$('input[id^="cbx_bankAccId_"]').each(function(index, item) {
					if ($(item).attr("id") != $(selected).attr("id")) { 
						if ($(item).prop("checked")) {
							$(item).prop("checked", false);
						}
					}
				});
			});
			
			$('#editBankAcc, #deleteBankAcc').click(function() {				
				var hasSelected = false;
				var button = $(this).attr('id');
				$('input[id^="cbx_bankAccId_"]').each(function(index, item) {
					if ($(item).prop("checked") == true) {
						if (button == 'editBankAcc') {
							$('#bankAccListInputPanel input').each(function(index) { $(this).val(''); });
							
							$('#bankAccinputMode').val("EDIT");							
							$('#bankAccId').val($('input[id="bankAccList' + $(item).val() + '.bankAccId"]').val());
							$('#shortName').val($('input[id="bankAccList' + $(item).val() + '.shortName"]').val());						
							$('#bankName').val($('input[id="bankAccList' + $(item).val() + '.bankName"]').val());
							$('#accountNumber').val($('input[id="bankAccList' + $(item).val() + '.accNum"]').val());
							$('#bankAccRemarks').val($('input[id="bankAccList' + $(item).val() + '.bankRemarks"]').val());
							
							$('#bankAccListPanel').hide();
							$('#bankAccListInputPanel').show();				
						} else {
							$('input[id="bankAccList' + $(item).val() + '.bankAccId"]').parents('tr').remove();							
						}

						hasSelected = true;
					}					
				});
				
				if (!hasSelected) {
					jsAlert('Please select at least 1 bank account');
				}
			});
					
			$('#saveBankAcc').click(function() {
				if ($('#bankAccinputMode').val() == "ADD") {
					var custObj = { "bankAccList" : [{
										"shortName" : $('#shortName').val(),
										"bankName" : $('#bankName').val(),
										"accNum" : $('#accountNumber').val(),
										"bankRemarks" : $('#bankAccRemarks').val(),
										"bankStatus" : '' }] };

					var countArr = [];
					if ($('input[id^="cbx_bankAccId_"]').size() == 0) {
						countArr.push(0);
					} else if ($('input[id^="cbx_bankAccId_"]').size() == 1) {
						countArr.push(0);
						countArr.push(1); 
					} else {
						$('input[id^="cbx_bankAccId_"]').each(function(index, item) { countArr.push(parseInt($(item).val()) + 1); });						
					} 
					countArr.sort(function(a, b) { return b-a });
						
					$.ajax({
						url: ctxpath + "/fragment/customer/addbank/" + countArr.shift(),
						type: 'POST',
						data: JSON.stringify(custObj),
						Accept : "application/json",
						contentType: "application/json",

						success: function(res) {
							$('#bankAccListPanel').show();
							$('#bankAccListInputPanel').hide();

							$('#bankAccListTable tbody').append(res);
						},
      
						error: function(res) {
							alert("Error ! - " + res.statusText);
						}
					});									
				} else {
					$('input[id="bankAccList' + $('#bankAccId').val() + '.shortName"]').val($('#shortName').val());		
					$('label[for="bankAccList' + $('#bankAccId').val() + '.shortName"]').text($('#shortName').val());
					
					$('input[id="bankAccList' + $('#bankAccId').val() + '.bankName"]').val($('#bankName').val());
					$('label[for="bankAccList' + $('#bankAccId').val() + '.bankName"]').text($('#bankName').val());
					
					$('input[id="bankAccList' + $('#bankAccId').val() + '.accNum"]').val($('#accountNumber').val());
					$('label[for="bankAccList' + $('#bankAccId').val() + '.accNum"]').text($('#accountNumber').val());
					
					$('input[id="bankAccList' + $('#bankAccId').val() + '.bankRemarks"]').val($('#bankAccRemarks').val());
					$('label[for="bankAccList' + $('#bankAccId').val() + '.bankRemarks"]').text($('#bankAccRemarks').val());
					
					$('#bankAccListPanel').show();
					$('#bankAccListInputPanel').hide();
				}
			});
			
			$('#discardBankAcc').click(function() {
				$('#bankAccListPanel').show();
				$('#bankAccListInputPanel').hide();
				
				$('#bankAccListInputPanel input').each(function(index) {
					$(this).val('');
				});
			});
			
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
											<span class="fa fa-plus fa-fw fa-2x"></span>&nbsp;Add Customer
										</c:when>
										<c:otherwise>
											<span class="fa fa-smile-o fa-fw fa-2x"></span>&nbsp;Edit Customer
										</c:otherwise>
									</c:choose>
								</h1>
							</div>
							<div class="panel-body">
								<form:form id="customerForm" role="form" class="form-horizontal" modelAttribute="customerForm" action="${pageContext.request.contextPath}/customer/save.html">
									<div role="tabpanel">
										<ul class="nav nav-tabs" role="tablist">
											<li role="presentation" class="active"><a href="#custDataTab" aria-controls="custDataTab" role="tab" data-toggle="tab"><span class="fa fa-info-circle fa-fw"></span>&nbsp;Customer Data</a></li>
											<li role="presentation" class=""><a href="#picTab" aria-controls="picTab" role="tab" data-toggle="tab"><span class="fa fa-key fa-fw"></span>&nbsp;Person In Charge</a></li>
											<li role="presentation" class=""><a href="#bankAccTab" aria-controls="bankAccTab" role="tab" data-toggle="tab"><span class="fa  fa-bank fa-fw"></span>&nbsp;Bank Account</a></li>
											<li role="presentation" class=""><a href="#settingsTab" aria-controls="settingsTab" role="tab" data-toggle="tab"><span class="fa  fa-cogs fa-fw"></span>&nbsp;Settings</a></li>
										</ul>

										<div class="tab-content">
											<div role="tabpanel" class="tab-pane active" id="custDataTab">
												<br/>
												<div class="form-group">
													<label for="inputStoreName" class="col-sm-2 control-label">Store Name</label>
													<div class="col-sm-3">
														<form:input path="storeName" type="text" class="form-control" id="inputStoreName" name="inputStoreName" placeholder="Enter Store Name"></form:input>
													</div>
												</div>
											</div>
											<div role="tabpanel" class="tab-pane" id="picTab">
											</div>
											<div role="tabpanel" class="tab-pane" id="bankAccTab">
												<br/>
												<div id="bankAccListPanel" class="panel panel-default">
													<div class="panel-heading">
														<div class="btn-toolbar">
															<button type="button" id="deleteBankAcc" class="btn btn-xs btn-primary pull-right" href=""><span class="fa fa-close fa-fw"></span>&nbsp;Delete</button>&nbsp;&nbsp;&nbsp;
															<button type="button" id="editBankAcc" class="btn btn-xs btn-primary pull-right" href=""><span class="fa fa-edit fa-fw"></span>&nbsp;Edit</button>&nbsp;&nbsp;&nbsp;
															<button type="button" id="addBankAcc" class="btn btn-xs btn-primary pull-right"><span class="fa fa-plus fa-fw"></span>&nbsp;Add</button>
														</div>
													</div>
													<table id="bankAccListTable" class="table table-bordered table-hover">
														<thead>
															<tr>
																<th>&nbsp;</th>
																<th>&nbsp;Bank Name</th>
																<th>&nbsp;Account</th>
																<th>&nbsp;Remarks</th>
																<th>&nbsp;Status</th>
															</tr>
														</thead>
														<tbody>
															<c:forEach items="${ customerForm.bankAccList }" var="baList" varStatus="baIdx">
																<tr>
																	<td align="center">
																		<input id="cbx_bankAccId_<c:out value="${ customerForm.bankAccList[baIdx.index].bankAccId }"/>" type="checkbox" value="<c:out value="${baIdx.index}"/>"/>
																		<form:hidden path="customerForm.bankAccList[${baIdx.index}].bankAccId"/>
																		<form:hidden path="customerForm.bankAccList[${baIdx.index}].shortName"/>
																		<form:hidden path="customerForm.bankAccList[${baIdx.index}].bankName"/>
																		<form:hidden path="customerForm.bankAccList[${baIdx.index}].accNum"/>
																		<form:hidden path="customerForm.bankAccList[${baIdx.index}].bankRemarks"/>
																		<form:hidden path="customerForm.bankAccList[${baIdx.index}].bankStatus"/>
																	</td>
																	<td>&nbsp;<form:label path="customerForm.bankAccList[${baIdx.index}].shortName"></form:label>&nbsp;-&nbsp;<form:label path="customerForm.bankAccList[${baIdx.index}].bankName"></form:label>
																	<td>&nbsp;<form:label path="customerForm.bankAccList[${baIdx.index}].accNum"></form:label>
																	<td>&nbsp;<form:label path="customerForm.bankAccList[${baIdx.index}].bankRemarks"></form:label>
																	<td>&nbsp;<form:label path="customerForm.bankAccList[${baIdx.index}].bankStatus"></form:label>
																</tr>
															</c:forEach>
														</tbody>
													</table>
												</div>
												<div id="bankAccListInputPanel" class="panel panel-default collapse">
													<br/>
													<input type="hidden" id="bankAccId" value=""/>
													<input type="hidden" id="bankAccinputMode" value=""/>
													<div class="row">
														<label for="shortName" class="col-sm-2 control-label">Short Name</label>
														<div class="col-sm-2"><input type="text" class="form-control" id="shortName"/></div>
													</div>
													<br/>
													<div class="row">
														<label for="bankName" class="col-sm-2 control-label">Bank Name</label>
														<div class="col-sm-4"><input type="text" class="form-control" id="bankName"></div>
													</div>
													<br/>
													<div class="row">
														<label for="accountNumber" class="col-sm-2 control-label">Account</label>
														<div class="col-sm-5"><input type="text" class="form-control" id="accountNumber"></div>
													</div>
													<br/>
													<div class="row">
														<label for="bankAccRemarks" class="col-sm-2 control-label">Remarks</label>
														<div class="col-sm-6"><input type="text" class="form-control" id="bankAccRemarks"></div>
													</div>
													<br/>
													<div class="row">
														<label for="bankAccButton" class="col-sm-2 control-label">&nbsp;</label>
														<div class="col-sm-5">
															<button id="saveBankAcc" type="button" class="btn btn-sm btn-primary">Save</button>
															<button id="discardBankAcc" type="button" class="btn btn-sm btn-primary">Discard</button>
														</div>
													</div>
													<br/>
												</div>											
											</div>
											<div role="tabpanel" class="tab-pane" id="settingsTab">...</div>
										</div>
									</div>
									<br/>
									<hr>
									<div class="col-md-7 offset-md-5">
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
