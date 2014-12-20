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
			
			//Bank Account
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
			
			$('#addBankAcc, #editBankAcc, #deleteBankAcc').click(function() {				
				var hasSelected = false;
				var button = $(this).attr('id');				
				
				if (button == 'addBankAcc') {
					$('#bankAccListInputPanel input').each(function(index) { $(this).val(''); });
				
					$('#bankAccInputMode').val("ADD");
					$('#bankAccListPanel').hide();
					$('#bankAccListInputPanel').collapse('show');					
				} else {
					$('input[id^="cbx_bankAccId_"]').each(function(index, item) {
						if ($(item).prop("checked") == true) {
							if (button == 'editBankAcc') {
								$('#bankAccListInputPanel input').each(function(index) { $(this).val(''); });
								
								$('#bankAccInputMode').val("EDIT");							
								$('#bankAccId').val($('input[id="bankAccList' + $(item).val() + '.bankAccId"]').val());
								$('#shortName').val($('input[id="bankAccList' + $(item).val() + '.shortName"]').val());						
								$('#bankName').val($('input[id="bankAccList' + $(item).val() + '.bankName"]').val());
								$('#accountNumber').val($('input[id="bankAccList' + $(item).val() + '.accNum"]').val());
								$('#bankAccRemarks').val($('input[id="bankAccList' + $(item).val() + '.bankRemarks"]').val());
								
								$('#bankAccListPanel').hide();
								$('#bankAccListInputPanel').collapse('show');				
							} else {
								$('input[id="bankAccList' + $(item).val() + '.bankAccId"]').parents('tr').remove();							
							}

							hasSelected = true;
						}					
					});
					
					if (!hasSelected) { jsAlert('Please select at least 1 bank account'); }					
				}				
			});
					
			$('#saveBankAcc, #discardBankAcc').click(function() {
				var button = $(this).attr('id');
				
				if (button == 'saveBankAcc') {
					if ($('#bankAccInputMode').val() == "ADD") {
						var custObj = { "bankAccList" : [{
											"shortName" : $('#shortName').val(),
											"bankName" : $('#bankName').val(),
											"accNum" : $('#accountNumber').val(),
											"bankRemarks" : $('#bankAccRemarks').val(),
											"bankStatus" : '' }] };
						
						var countArr = [];
						if ($('input[id^="cbx_bankAccId_"]').size() == 0) { countArr.push(0); } 
						else if ($('input[id^="cbx_bankAccId_"]').size() == 1) { countArr.push(0); countArr.push(1); 
						} else { $('input[id^="cbx_bankAccId_"]').each(function(index, item) { countArr.push(parseInt($(item).val()) + 1); }); } 
						countArr.sort(function(a, b) { return b-a });
							
						$.ajax({
							url: ctxpath + "/fragment/customer/addbank/" + countArr.shift(),
							type: 'POST',
							data: JSON.stringify(custObj),
							Accept : "application/json",
							contentType: "application/json",
							
							success: function(res) {
								$('#bankAccListPanel').show();
								$('#bankAccListInputPanel').collapse('hide');

								$('#bankAccListTable tbody').append(res);
							},
	      
							error: function(res) { jsAlert("Error ! - " + res.statusText); }
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
						$('#bankAccListInputPanel').collapse('hide');
					}					
				} else {
					$('#bankAccListPanel').show();
					$('#bankAccListInputPanel').collapse('hide');
					
					$('#bankAccListInputPanel input').each(function(index) { $(this).val(''); });					
				}
			});
			
			//Person
			$('input[id^="cbx_personId_"]').click(function() {
				var selected = $(this);	
				
				$('input[id^="cbx_personId_"]').each(function(index, item) {
					if ($(item).attr("id") != $(selected).attr("id")) { 
						if ($(item).prop("checked")) {
							$(item).prop("checked", false);
						}
					}
				});
			});
			
			$('#addPerson, #editPerson, #deletePerson').click(function() {				
				var hasSelected = false;
				var button = $(this).attr('id');				
				
				if (button == 'addPerson') {
					$('#personListInputPanel input').each(function(index) { $(this).val(''); });
					$('#phoneListTable tbody').empty();

					$('#personInputMode').val("ADD");
					$('#personListPanel').hide();
					$('#personListInputPanel').collapse('show');
				} else {
					$('input[id^="cbx_personId_"]').each(function(index, item) {
						if ($(item).prop("checked") == true) {
							if (button == 'editPerson') {
								$('#personListInputPanel input').each(function(index) { $(this).val(''); });
								$('#phoneListTable tbody').empty();
								
								$('#personInputMode').val("EDIT");							
								$('#personId').val($('input[id="picList' + $(item).val() + '.personId"]').val());
								$('#firstName').val($('input[id="picList' + $(item).val() + '.firstName"]').val());						
								$('#lastName').val($('input[id="picList' + $(item).val() + '.lastName"]').val());
								$('#addressLine1').val($('input[id="picList' + $(item).val() + '.addressLine1"]').val());
								$('#addressLine2').val($('input[id="picList' + $(item).val() + '.addressLine2"]').val());
								$('#addressLine3').val($('input[id="picList' + $(item).val() + '.addressLine3"]').val());
								$('#emailAddr').val($('input[id="picList' + $(item).val() + '.emailAddr"]').val());
								
								$('#hiddenPhoneList div').each(function(index, item) {
									var prsnId = $(item).attr('id').split('_')[1];
									alert(prsnId);
									$('#phoneListTable tbody').append(''+
										'<tr>'+
											'<td align="center"><div class="checkbox"><label><input id="cbx_phoneId_' + prsnId + '_' + index + '" type="checkbox"/></label></div></td>'+
											'<td><input type="text" class="form-control input-sm" id="providerName" placeholder="Enter Provider" value="' + $('#providerName', item).val() + '"/></td>'+
											'<td><input type="text" class="form-control input-sm" id="phoneNumber" placeholder="Enter Number" value="' + $('#phoneNumber', item).val() + '"/></td>'+
											'<td><input type="text" class="form-control input-sm" id="phoneStatus" placeholder="Status" value="' + $('#phoneStatus', item).val() + '"/></td>'+
											'<td><input type="text" class="form-control input-sm" id="phoneNumRemarks" placeholder="Remarks" value="' + $('#phoneNumRemarks', item).val() + '"/></td>'+
										'</tr>'+
									'');
								});
								
								$('#personListPanel').hide();
								$('#personListInputPanel').collapse('show');				
							} else {
								$('input[id="picList' + $(item).val() + '.personId"]').parents('tr').remove();							
							}

							hasSelected = true;
						}					
					});
					
					if (!hasSelected) { jsAlert('Please select at least 1 person'); }					
				}				
			});
					
			$('#savePerson, #discardPerson').click(function() {
				var button = $(this).attr('id');
				
				if (button == 'savePerson') {
					if ($('#personInputMode').val() == "ADD") {						
						var phoneListObj = [];
						
						$('#phoneListTable tbody tr').each(function(index, item) {							
							phoneListObj.push({
								"providerName" 		: $('#providerName', item).val(),
								"phoneNumber" 		: $('#phoneNumber', item).val(),
								"phoneStatus" 		: $('#phoneStatus', item).val(),
								"phoneNumRemarks" 	: $('#phoneNumRemarks', item).val()
							});
						});						
						
						var persListObj = [];			
							persListObj.push({
								"personId" 		: 0,
								"firstName" 	: $('#firstName').val(),
								"lastName"		: $('#lastName').val(),
								"addressLine1"	: $('#addressLine1').val(),
								"addressLine2"	: $('#addressLine2').val(),
								"addressLine3" 	: $('#addressLine3').val(),
								"emailAddr"		: $('#emailAddr').val(),
								"photoPath"		: $('#photoPath').val(),
								"phoneList"		: phoneListObj
							});
							
						
						var custObj = { };
							custObj.picList = persListObj;
							
						var countArr = [];
						if ($('input[id^="cbx_personId_"]').size() == 0) { countArr.push(0); } 
						else if ($('input[id^="cbx_personId_"]').size() == 1) { countArr.push(0); countArr.push(1); 
						} else { $('input[id^="cbx_personId_"]').each(function(index, item) { countArr.push(parseInt($(item).val()) + 1); }); } 
						countArr.sort(function(a, b) { return b-a });
							
						$.ajax({
							url: ctxpath + "/fragment/customer/addperson/" + countArr.shift(),
							type: 'POST',
							data: JSON.stringify(custObj),
							Accept : "application/json",
							contentType: "application/json",
							
							success: function(res) {
								$('#personListPanel').show();
								$('#personListInputPanel').collapse('hide');

								$('#personListTable tbody').append(res);	
							},
	      
							error: function(res) { jsAlert("Error ! - " + res.statusText); }
						});									
					} else {
						$('input[id="picList' + $('#personId').val() + '.firstName"]').val($('#firstName').val());		
						$('label[for="picList' + $('#personId').val() + '.firstName"]').text($('#firstName').val());
						
						$('input[id="picList' + $('#personId').val() + '.lastName"]').val($('#lastName').val());
						$('label[for="picList' + $('#personId').val() + '.lastName"]').text($('#lastName').val());
						
						$('input[id="picList' + $('#personId').val() + '.addressLine1"]').val($('#addressLine1').val());
						$('label[for="picList' + $('#personId').val() + '.addressLine1"]').text($('#addressLine1').val());

						$('input[id="picList' + $('#personId').val() + '.addressLine2"]').val($('#addressLine2').val());
						$('label[for="picList' + $('#personId').val() + '.addressLine2"]').text($('#addressLine2').val());

						$('input[id="picList' + $('#personId').val() + '.addressLine3"]').val($('#addressLine3').val());
						$('label[for="picList' + $('#personId').val() + '.addressLine3"]').text($('#addressLine3').val());

						$('input[id="picList' + $('#personId').val() + '.emailAddr"]').val($('#emailAddr').val());
						$('label[for="picList' + $('#personId').val() + '.emailAddr"]').text($('#emailAddr').val());
						
						$('#personListPanel').show();
						$('#personListInputPanel').collapse('hide');
					}					
				} else {
					$('#personListPanel').show();
					$('#personListInputPanel').collapse('hide');
					
					$('#personListInputPanel input').each(function(index) { $(this).val(''); });					
				}
			});

			$('#plusPhone, #minusPhone').click(function() {
				var button = $(this).attr('id');
				var prsnId = $('#personId').val();
				
				if (button = 'plusPhone') {
					var countArr = [];
					if ($('input[id^="cbx_phoneId_' + prsnId + '_"]').size() == 0) { countArr.push(0); } 
					else if ($('input[id^="cbx_phoneId_' + prsnId + '_"]').size() == 1) { countArr.push(0); countArr.push(1); 
					} else { $('input[id^="cbx_phoneId_' + prsnId + '_"]').each(function(index, item) { countArr.push(parseInt($(item).val()) + 1); }); } 
					countArr.sort(function(a, b) { return b-a });
					
					$('#phoneListTable tbody').append(''+
						'<tr>'+
							'<td align="center"><div class="checkbox"><label><input id="cbx_phoneId_' + prsnId + '_' + countArr.shift() + '" type="checkbox"/></label></div></td>'+
							'<td><input type="text" class="form-control input-sm" id="providerName" placeholder="Enter Provider"/></td>'+
							'<td><input type="text" class="form-control input-sm" id="phoneNumber" placeholder="Enter Number"/></td>'+
							'<td><input type="text" class="form-control input-sm" id="phoneStatus" placeholder="Status"/></td>'+
							'<td><input type="text" class="form-control input-sm" id="phoneNumRemarks" placeholder="Remarks"/></td>'+
						'</tr>'+
					'');
				} else {
					
				}
			});
			
			$('#cancelButton').click(function() {				
				window.location.href(ctxpath + "/customer");
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
			
			$('#editTableSelection, #deleteTableSelection').click(function() {
				var id = "";
				var button = $(this).attr('id');
								
				$('input[type="checkbox"][id^="cbx_"]').each(function(index, item) {
					if ($(item).prop('checked')) {
						id = $(item).attr("value");	
					}
				});
				if (id == "") {
					jsAlert("Please select at least 1 customer");
					return false;	
				} else {
					if (button == 'editTableSelection') {
						$('#editTableSelection').attr("href", ctxpath + "/customer/edit/" + id);	
					} else {
						$('#deleteTableSelection').attr("href", ctxpath + "/customer/delete/" + id);	
					}						
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
											<c:if test="${not empty customerList}">
												<c:forEach items="${ customerList }" var="i" varStatus="status">
													<tr>
														<td align="center"><input id="cbx_<c:out value="${ i.customerId }"/>" type="checkbox" value="<c:out value="${ i.customerId }"/>"/></td>
														<td><c:out value="${ i.storeName }"></c:out></td>
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
								<a id="addNew" class="btn btn-sm btn-primary" href="${pageContext.request.contextPath}/customer/add"><span class="fa fa-plus fa-fw"></span>&nbsp;Add</a>&nbsp;&nbsp;&nbsp;
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
								<form:form id="customerForm" role="form" class="form-horizontal" modelAttribute="customerForm" action="${pageContext.request.contextPath}/customer/save">
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
												<br/>
												<div id="personListPanel" class="panel panel-default">
													<div class="panel-heading">
														<div class="btn-toolbar">
															<button type="button" id="deletePerson" class="btn btn-xs btn-primary pull-right"><span class="fa fa-close fa-fw"></span>&nbsp;Delete</button>&nbsp;&nbsp;&nbsp;
															<button type="button" id="editPerson" class="btn btn-xs btn-primary pull-right"><span class="fa fa-edit fa-fw"></span>&nbsp;Edit</button>&nbsp;&nbsp;&nbsp;
															<button type="button" id="addPerson" class="btn btn-xs btn-primary pull-right"><span class="fa fa-plus fa-fw"></span>&nbsp;Add</button>
														</div>
													</div>
													<table id="personListTable" class="table table-bordered table-hover">
														<thead>
															<tr>
																<th>&nbsp;</th>
																<th>&nbsp;Name</th>
																<th>&nbsp;Address</th>
																<th>&nbsp;Email</th>
																<th>&nbsp;Status</th>
															</tr>
														</thead>
														<tbody>
															<c:forEach items="${ customerForm.picList }" varStatus="picIdx">
																<tr>
																	<td align="center">
																		<input id="cbx_personId_<c:out value="${ customerForm.picList[picIdx.index].personId }"/>" type="checkbox" value="<c:out value="${picIdx.index}"/>"/>																		
																		<form:hidden path="picList[${picIdx.index}].personId"/>																																				
																		<form:hidden path="picList[${picIdx.index}].firstName"/>
																		<form:hidden path="picList[${picIdx.index}].lastName"/>
																		<form:hidden path="picList[${picIdx.index}].addressLine1"/>
																		<form:hidden path="picList[${picIdx.index}].addressLine2"/>
																		<form:hidden path="picList[${picIdx.index}].addressLine3"/>
																		<form:hidden path="picList[${picIdx.index}].emailAddr"/>
																		<form:hidden path="picList[${picIdx.index}].photoPath"/>
																		<div id="hiddenPhoneList">
																			<c:forEach items="${ customerForm.picList[picIdx.index].phoneList }" varStatus="phoneListIdx">
																				<div id="phoneId_<c:out value="${ customerForm.picList[picIdx.index].personId }"/>_<c:out value="${ phoneListIdx.index }"/>">
																					<form:hidden path="picList[${picIdx.index}].phoneList[${phoneListIdx.index}].providerName"/>																			
																					<form:hidden path="picList[${picIdx.index}].phoneList[${phoneListIdx.index}].phoneNumber"/>
																					<form:hidden path="picList[${picIdx.index}].phoneList[${phoneListIdx.index}].phoneStatus"/>
																					<form:hidden path="picList[${picIdx.index}].phoneList[${phoneListIdx.index}].phoneNumRemarks"/>
																				</div>
																			</c:forEach>
																		</div>
																	</td>
																	<td>&nbsp;<span id="picList[${picIdx.index}].firstName"><c:out value="${ customerForm.picList[picIdx.index].firstName }"></c:out></span>&nbsp;<span id="picList[${picIdx.index}].lastName"><c:out value="${ customerForm.picList[picIdx.index].lastName }">/</c:out></span></td>
																	<td>
																		&nbsp;<span id="picList[${picIdx.index}].addressLine1"><c:out value="${ customerForm.picList[picIdx.index].addressLine1 }"></c:out></span><br/>
																		&nbsp;<span id="picList[${picIdx.index}].addressLine2"><c:out value="${ customerForm.picList[picIdx.index].addressLine1 }"></c:out></span><br/>
																		&nbsp;<span id="picList[${picIdx.index}].addressLine3"><c:out value="${ customerForm.picList[picIdx.index].addressLine1 }"></c:out></span><br/>
																		<br/>
																		<strong>Phone List</strong><br/>
																		<c:forEach items="${ customerForm.picList[picIdx.index].phoneList }" varStatus="phoneListIdx">
																			<span id="picList[${picIdx.index}].phoneList[${phoneListIdx.index}].providerName"><c:out value="${ customerForm.picList[picIdx.index].phoneList[phoneListIdx.index].providerName }"></c:out></span>
																			&nbsp;-&nbsp;
																			<span id="picList[${picIdx.index}].phoneList[${phoneListIdx.index}].phoneNumber"><c:out value="${ customerForm.picList[picIdx.index].phoneList[phoneListIdx.index].phoneNumber }"></c:out></span>
																			&nbsp;(&nbsp;
																			<span id="picList[${picIdx.index}].phoneList[${phoneListIdx.index}].phoneStatus"><c:out value="${ customerForm.picList[picIdx.index].phoneList[phoneListIdx.index].phoneStatus }"></c:out></span>
																			&nbsp;-&nbsp;
																			<span id="picList[${picIdx.index}].phoneList[${phoneListIdx.index}].phoneNumRemarks"><c:out value="${ customerForm.picList[picIdx.index].phoneList[phoneListIdx.index].phoneNumRemarks }"></c:out></span>
																			&nbsp;)&nbsp;
																			<br/>
																		</c:forEach>
																	</td>
																	<td>&nbsp;<span id="picList[${picIdx.index}].emailAddr"><c:out value="${ picList[picIdx.index].emailAddr }"></c:out></span></td>
																	<td></td>
																</tr>
															</c:forEach>
														</tbody>
													</table>
												</div>
												<div id="personListInputPanel" class="panel panel-default collapse">
													<br/>
													<input type="hidden" id="personId" value=""/>
													<input type="hidden" id="personInputMode" value=""/>
													<div class="row">
														<label for="firstName" class="col-sm-2 control-label">Name</label>
														<div class="col-sm-4">
															<input type="text" class="form-control" id="firstName"/>
														</div>
														<div class="col-sm-4">
															<input type="text" class="form-control" id="lastName"/>
														</div>
													</div>
													<br/>
													<div class="row">
														<label for="addressLine1" class="col-sm-2 control-label">Address</label>
														<div class="col-sm-8">
															<input type="text" class="form-control" id="addressLine1"/>
															<input type="text" class="form-control" id="addressLine2"/>
															<input type="text" class="form-control" id="addressLine3"/>
														</div>														
													</div>
													<br/>
													<div class="row">
														<label for="emailAddr" class="col-sm-2 control-label">Email</label>
														<div class="col-sm-5">
															<input type="text" class="form-control" id="emailAddr"/>
														</div>
													</div>
													<br/>
													<div class="row">																												
														<div class="col-sm-10 pull-right">
															<div id="phoneListPanel" class="panel panel-default">
																<div class="panel-heading">
																	<strong>Phone List</strong>																	
																</div>
																<table id="phoneListTable" class="table table-bordered table-hover">
																	<thead>
																		<tr>
																			<th width="5%">&nbsp;</th>
																			<th width="15%">Provider</th>
																			<th width="15%">Number</th>
																			<th width="15%">Status</th>
																			<th width="25%">Remarks</th>
																		</tr>
																	</thead>
																	<tbody>
																	</tbody>
																</table>
																<div class="panel-footer no-padding">
																	<div class="btn-toolbar">
																	<button type="button" id="minusPhone" class="btn btn-xs btn-primary pull-right"><span class="fa fa-minus fa-fw"></span></button>
																	<button type="button" id="plusPhone" class="btn btn-xs btn-primary pull-right"><span class="fa fa-plus fa-fw"></span></button>
																	</div>										
																</div>
															</div>
														</div>
													</div>
													<div class="row">
														<label for="personButton" class="col-sm-2 control-label">&nbsp;</label>
														<div class="col-sm-5">
															<button id="savePerson" type="button" class="btn btn-sm btn-primary">Save</button>
															<button id="discardPerson" type="button" class="btn btn-sm btn-primary">Discard</button>
														</div>
													</div>
													<br/>
												</div>
											</div>
											<div role="tabpanel" class="tab-pane" id="bankAccTab">
												<br/>
												<div id="bankAccListPanel" class="panel panel-default">
													<div class="panel-heading">
														<div class="btn-toolbar">
															<button type="button" id="deleteBankAcc" class="btn btn-xs btn-primary pull-right"><span class="fa fa-close fa-fw"></span>&nbsp;Delete</button>&nbsp;&nbsp;&nbsp;
															<button type="button" id="editBankAcc" class="btn btn-xs btn-primary pull-right"><span class="fa fa-edit fa-fw"></span>&nbsp;Edit</button>&nbsp;&nbsp;&nbsp;
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
																	<td>&nbsp;<form:label path="customerForm.bankAccList[${baIdx.index}].shortName"></form:label>&nbsp;-&nbsp;<form:label path="customerForm.bankAccList[${baIdx.index}].bankName"></form:label></td>
																	<td>&nbsp;<form:label path="customerForm.bankAccList[${baIdx.index}].accNum"></form:label></td>
																	<td>&nbsp;<form:label path="customerForm.bankAccList[${baIdx.index}].bankRemarks"></form:label></td>
																	<td>&nbsp;<form:label path="customerForm.bankAccList[${baIdx.index}].bankStatus"></form:label></td>
																</tr>
															</c:forEach>
														</tbody>
													</table>
												</div>
												<div id="bankAccListInputPanel" class="panel panel-default collapse">
													<br/>
													<input type="hidden" id="bankAccId" value=""/>
													<input type="hidden" id="bankAccInputMode" value=""/>
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
									<hr>
									<div class="col-md-7 col-offset-md-5">
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
