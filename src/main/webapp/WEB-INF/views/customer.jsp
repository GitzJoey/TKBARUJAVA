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
					$('#customerForm').attr('action', ctxpath + "/customer/edit/" + $('#customerId').val() + "/bank/addbank/0");
				} else {
					var bankAccId = 0;
					$('input[id^="cbx_bankAccId_"]').each(function(index, item) { 
						if ($(item).prop("checked") == true) { 
							hasSelected = true;
							bankAccId = $(item).attr('id').split('_')[2];
						} 
					});
					
					if (!hasSelected) { jsAlert('Please select at least 1 bank account'); return false;}
					else {
						if (button == 'editBankAcc') {
							$('#customerForm').attr('action', ctxpath + "/customer/edit/" + $('#customerId').val() + "/bank/editbank/" + bankAccId);
						} else {
							$('#customerForm').attr('action', ctxpath + "/customer/edit/" + $('#customerId').val() + "/bank/deletebank/" + bankAccId);
						}
					}
				}				
			});
					
			$('#saveBankAcc, #discardBankAcc').click(function() {
				var button = $(this).attr('id');
				var bankAccButtonMode = $('#bankAccButtonMode').val();
				
				if (button == 'saveBankAcc') {
					$('#customerForm').attr('action', ctxpath + "/customer/edit/" + $('#customerId').val() + "/bank/" + bankAccButtonMode + "/" + $('#currentBankAccIdSelected').val() + "/save");
				} else if (button == 'discardBankAcc') {
					var index = $('#currentBankAccIndexSelected').val();
					
					$('input[name="bankAccList[' + index + '].shortName"]').val($('#fromDB_shortName').val());
					$('input[name="bankAccList[' + index + '].bankName"]').val($('#fromDB_bankName').val());
					$('input[name="bankAccList[' + index + '].accNum"]').val($('#fromDB_accNum').val());
					$('input[name="bankAccList[' + index + '].bankStatus"]').val($('#fromDB_bankStatus').val());
					$('input[name="bankAccList[' + index + '].bankRemarks"]').val($('#fromDB_bankRemarks').val());					
					
					$('#customerForm').attr('action', ctxpath + "/customer/edit/" + $('#customerId').val() + "/bank/" + bankAccButtonMode + "/" + $('#currentBankAccIdSelected').val() + "/discard");
				}
			});
			
			//Person
			$('input[id^="cbx_picList_"]').click(function() {
				var selected = $(this);	
				
				$('input[id^="cbx_picList_"]').each(function(index, item) {
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
					$('#customerForm').attr('action', ctxpath + "/customer/edit/" + $('#customerId').val() + "/person/addperson/0");
				} else {
					$('input[id^="cbx_picList_"]').each(function(index, item) {
						if ($(item).prop("checked") == true) {
							hasSelected = true;
							personId = $(item).attr('id').split('_')[2];
						}					
					});
					
					if (!hasSelected) { jsAlert('Please select at least 1 person'); return false; }
					else {
						if (button == 'editPerson') {
							$('#customerForm').attr('action', ctxpath + "/customer/edit/" + $('#customerId').val() + "/person/editperson/" + personId);
						} else {
							$('#customerForm').attr('action', ctxpath + "/customer/edit/" + $('#customerId').val() + "/person/deleteperson/" + personId);
						}						
					}
				}				
			});
					
			$('#savePerson, #discardPerson').click(function() {
				var button = $(this).attr('id');
				var personButtonMode = $('#personButtonMode').val();
				
				if (button == 'savePerson') {
					$('#customerForm').attr('action', ctxpath + "/customer/edit/" + $('#customerId').val() + "/person/" + personButtonMode + "/" + $('#currentPersonIdSelected').val() + "/save");
				} else {
					$('#customerForm').attr('action', ctxpath + "/customer/edit/" + $('#customerId').val() + "/person/" + personButtonMode + "/" + $('#currentPersonIdSelected').val() + "/discard");
				}
			});

			$('button[id^="phoneButton_"]').click(function() {
				var button = $(this).attr('id').split('_')[1];
				var prsnId = $(this).attr('id').split('_')[2];
				var prsnIdx = $(this).attr('id').split('_')[3];
				
				if (button == 'plusPhone') {
					$('#customerForm').attr('action', ctxpath + "/customer/edit/" + $('#customerId').val() + 	"/person/" + prsnId + "/" + prsnIdx + "/addphone/0/0");
				} else {
					var hasSelected = false;
					var phoneListId = "";
					var phoneListIndex = "";
					$('input[id^="cbx_phoneList_' + prsnId + '_"]').each(function(index, item) {						
						if ($(item).prop("checked") == true) {
							hasSelected = true;
							phoneListId = $(item).attr('id').split('_')[3];
							phoneListIndex = $(item).val();
						}
					});

					if (!hasSelected) { jsAlert('Please select at least 1 phone'); return false;}
					else {
						$('#customerForm').attr('action', ctxpath + "/customer/edit/" + $('#customerId').val() + "/person/" + prsnId + "/" + prsnIdx + "/deletephone/" + phoneListId + "/" + phoneListIndex);	
					}
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
			});
			
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

	        $.listen('parsley:field:validate', function () {
	        	validateFront();
	        });
	        
	        var validateFront = function () {
				if (true === $('#customerForm').parsley().isValid("tab1", false)) {
	              	$('#custDataTabError').addClass('hidden');
	            } else {
	            	$('#custDataTabError').removeClass('hidden');
	            }

				if (true === $('#customerForm').parsley().isValid("tab2", false)) {
	              	$('#picTabError').addClass('hidden');
	            } else {
	            	$('#picTabError').removeClass('hidden');
	            }
			};
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
					<c:when test="${ PAGEMODE == 'PAGEMODE_PAGELOAD' || PAGEMODE == 'PAGEMODE_LIST' || PAGEMODE == 'PAGEMODE_DELETE' }">
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
												<th width="95%"><spring:message code="customer_jsp.table.header.customer_details" text="Customer Details"/></th>
											</tr>
										</thead>
										<tbody>
											<c:if test="${not empty customerList}">
												<c:forEach items="${ customerList }" var="i" varStatus="status">
													<tr>
														<td align="center"><input id="cbx_<c:out value="${ i.customerId }"/>" type="checkbox" value="<c:out value="${ i.customerId }"/>"/></td>
														<td>
															<table class="table borderless">
																<tbody>
																	<tr>
																		<td>
																			<table class="table borderless nopaddingrow">
																				<tr>
																					<td colspan="2">
																						<strong class="title"><c:out value="${ i.customerName }"></c:out></strong>
																						<hr>
																					</td>
																				</tr>
																				<tr>
																					<td width="35%">
																						<strong>Customer Details</strong><br/>
																						<c:out value="${ i.customerAddress }"></c:out><br/>
																						<c:out value="${ i.customerCity }"></c:out><br/>															
																						<c:out value="${ i.customerPhone }"></c:out><br/>
																						<c:out value="${ i.npwpNum }"></c:out><br/>
																						<c:out value="${ i.customerRemarks }"></c:out><br/>
																					</td>
																					<td width="65%">
																						<strong>Person In Charge</strong>
																						<br/>
																						<c:forEach items="${ i.picList }" var="iPIC">
																							<c:out value="${ iPIC.firstName }"/><br/>
																						</c:forEach>
																						<br/>
																						<strong>Bank Account</strong>
																						<br/>
																						<c:forEach items="${ i.bankAccList }" var="iBA">
																							<c:out value="${ iBA.shortName }"/><br/>
																						</c:forEach>
																						<br/>
																						<strong>Settings</strong>
																						<br/>
																						<br/>
																						<br/>
																					</td>
																				</tr>
																			</table>
																		</td>
																	</tr>
																</tbody>
															</table>															
														</td>
													</tr>
												</c:forEach>
											</c:if>
										</tbody>
									</table>
								</div>
								<a id="addNew" class="btn btn-sm btn-primary" href="${pageContext.request.contextPath}/customer/add"><span class="fa fa-plus fa-fw"></span>&nbsp;<spring:message code="common.add_button" text="Add"/></a>&nbsp;&nbsp;&nbsp;
								<a id="editTableSelection" class="btn btn-sm btn-primary" href=""><span class="fa fa-edit fa-fw"></span>&nbsp;<spring:message code="common.edit_button" text="Edit"/></a>&nbsp;&nbsp;&nbsp;
								<a id="deleteTableSelection" class="btn btn-sm btn-primary" href=""><span class="fa fa-close fa-fw"></span>&nbsp;<spring:message code="common.delete_button" text="Delete"/></a>
							</div>
						</div>
					</c:when>
					<c:when test="${ PAGEMODE == 'PAGEMODE_ADD' || PAGEMODE == 'PAGEMODE_EDIT' }">
						<div class="panel panel-default">
							<div class="panel-heading">
								<h1 class="panel-title">
									<c:choose>
										<c:when test="${ PAGEMODE == 'PAGEMODE_ADD' }">
											<span class="fa fa-plus fa-fw fa-2x"></span>&nbsp;Add Customer
										</c:when>
										<c:otherwise>
											<span class="fa fa-edit fa-fw fa-2x"></span>&nbsp;Edit Customer
										</c:otherwise>
									</c:choose>
								</h1>
							</div>
							<div class="panel-body">
								<form:form id="customerForm" role="form" class="form-horizontal" modelAttribute="customerForm" action="${pageContext.request.contextPath}/customer/save" data-parsley-validate="parsley">
									<div role="tabpanel">
										<ul class="nav nav-tabs" role="tablist">
											<li role="presentation" class="<c:if test="${ activeTab == 'custDataTab' }"><c:out value="active"/></c:if>"><a href="#custDataTab" aria-controls="custDataTab" role="tab" data-toggle="tab"><span class="fa fa-info-circle fa-fw"></span>&nbsp;Customer Data<span id="custDataTabError" class="parsley-asterisk hidden">&nbsp;*</span></a></li>
											<li role="presentation" class="<c:if test="${ activeTab == 'picTab' }"><c:out value="active"/></c:if>"><a href="#picTab" aria-controls="picTab" role="tab" data-toggle="tab"><span class="fa fa-key fa-fw"></span>&nbsp;Person In Charge<span id="picTabError" class="parsley-asterisk hidden">&nbsp;*</span></a></li>
											<li role="presentation" class="<c:if test="${ activeTab == 'bankAccTab' }"><c:out value="active"/></c:if>"><a href="#bankAccTab" aria-controls="bankAccTab" role="tab" data-toggle="tab"><span class="fa  fa-bank fa-fw"></span>&nbsp;Bank Account</a></li>
											<li role="presentation" class="<c:if test="${ activeTab == 'settingsTab' }"><c:out value="active"/></c:if>"><a href="#settingsTab" aria-controls="settingsTab" role="tab" data-toggle="tab"><span class="fa  fa-cogs fa-fw"></span>&nbsp;Settings</a></li>
										</ul>

										<div class="tab-content">
											<div role="tabpanel" class="tab-pane <c:if test="${ activeTab == 'custDataTab' }"><c:out value="active"/></c:if>" id="custDataTab">
												<br/>
												<div class="form-group">
													<label for="inputCustomerName" class="col-sm-2 control-label">Customer Name</label>
													<div class="col-sm-3">
														<form:hidden path="customerId" />
														<form:input path="customerName" type="text" class="form-control" id="inputCustomerName" name="inputCustomerName" placeholder="Enter Customer Name" data-parsley-required="true" data-parsley-trigger="keyup" data-parsley-group="tab1"></form:input>
													</div>
												</div>
												<div class="form-group">
													<label for="inputCustomerAddress" class="col-sm-2 control-label">Address</label>
													<div class="col-sm-8">
														<form:input path="customerAddress" type="text" class="form-control" id="inputCustomerAddress" name="inputCustomerAddress" placeholder="Enter Address" data-parsley-required="true" data-parsley-trigger="keyup" data-parsley-group="tab1"></form:input>
													</div>
												</div>
												<div class="form-group">
													<label for="inputCustomerCity" class="col-sm-2 control-label">City</label>
													<div class="col-sm-4">
														<form:input path="customerCity" type="text" class="form-control" id="inputCustomerCity" name="inputCustomerCity" placeholder="Enter City" data-parsley-required="true" data-parsley-trigger="keyup" data-parsley-group="tab1"></form:input>
													</div>
												</div>
												<div class="form-group">
													<label for="inputCustomerPhone" class="col-sm-2 control-label">Phone</label>
													<div class="col-sm-4">
														<form:input path="customerPhone" type="text" class="form-control" id="inputCustomerPhone" name="inputCustomerPhone" placeholder="Enter Phone" data-parsley-required="true" data-parsley-trigger="keyup" data-parsley-group="tab1"></form:input>
													</div>
												</div>
												<div class="form-group">
													<label for="inputCustomerNpwpNum" class="col-sm-2 control-label">NPWP Number</label>
													<div class="col-sm-4">
														<form:input path="npwpNum" type="text" class="form-control" id="inputNpwpNum" name="inputNpwpNum" placeholder="Enter NPWP Number"></form:input>
													</div>
												</div>        
												<div class="form-group">
													<label for="inputCustomerStatus" class="col-sm-2 control-label">Status</label>
													<div class="col-sm-2">
														<form:select class="form-control" path="customerStatus">
															<form:options items="${ statusDDL }" itemValue="lookupKey" itemLabel="lookupValue"/>
														</form:select>
													</div>
												</div>
												<div class="form-group">
													<label for="inputCustomerRemarks" class="col-sm-2 control-label">Remarks</label>
													<div class="col-sm-8">
														<form:input path="customerRemarks" type="text" class="form-control" id="inputCustomerRemarks" name="inputCustomerRemarks" placeholder="Remarks"></form:input>
													</div>
												</div>
											</div>
											<div role="tabpanel" class="tab-pane <c:if test="${ activeTab == 'picTab' }"><c:out value="active"/></c:if>" id="picTab">
												<br/>
												<div id="personListPanel" class="panel panel-default">
													<div class="panel-heading">
														<div class="btn-toolbar">															
															<button type="submit" id="addPerson" class="btn btn-xs btn-primary pull-right"><span class="fa fa-plus fa-fw"></span>&nbsp;Add</button>
														</div>
													</div>
													<div class="panel-body">
														<c:choose>
															<c:when test="${ empty customerForm.picList }">
																<p>No Data.</p>
															</c:when>
															<c:otherwise>
																<div id="accordion_picList" class="panel-group" >															
																	<c:forEach items="${ customerForm.picList }" var="picListLoop" varStatus="picListLoopIdx">
																		<div class="panel panel-default">
																	        <div class="panel-heading accordion-toggle collapsed" data-toggle="collapse" data-parent="#accordion_picList" data-target="#collapse_<c:out value="${ picListLoopIdx.index }"/>">
																	             <h4 class="panel-title">PIC&nbsp;<c:out value="${ picListLoopIdx.index + 1 }"/>&nbsp;-&nbsp;<c:out value="${ customerForm.picList[picListLoopIdx.index].firstName }"/>&nbsp;<c:out value="${ customerForm.picList[picListLoopIdx.index].lastName }"/></h4>														
																	        </div>
																	        <div id="collapse_<c:out value="${ picListLoopIdx.index }"/>" class="panel-collapse collapse">
																	            <div class="panel-body">
																					<form:hidden path="picList[${picListLoopIdx.index}].personId"/>
																					<div class="row">																				
																						<div class="form-group">
																							<label for="firstName" class="col-sm-2 control-label">Name</label>
																							<div class="col-sm-4">
																								<form:input type="text" class="form-control" path="picList[${picListLoopIdx.index}].firstName" data-parsley-required="true" data-parsley-trigger="keyup" data-parsley-group="tab2"/>
																								<form:input type="text" class="form-control" path="picList[${picListLoopIdx.index}].lastName" data-parsley-required="true" data-parsley-trigger="keyup"  data-parsley-group="tab2"/>
																							</div>
																						</div>
																					</div>
																					<br/>
																					<div class="row">
																						<div class="form-group">
																							<label for="addressLine1" class="col-sm-2 control-label">Address</label>
																							<div class="col-sm-7">																						
																								<form:input type="text" class="form-control" path="picList[${picListLoopIdx.index}].addressLine1"/>
																								<form:input type="text" class="form-control" path="picList[${picListLoopIdx.index}].addressLine2"/>
																								<form:input type="text" class="form-control" path="picList[${picListLoopIdx.index}].addressLine3"/>
																							</div>
																						</div>
																					</div>
																					<br/>
																					<div class="row">
																						<div class="form-group">
																							<label for="emailAddr" class="col-sm-2 control-label">Email</label>																				
																							<div class="col-sm-5">
																								<form:input type="text" class="form-control" path="picList[${picListLoopIdx.index}].emailAddr" />
																							</div>
																						</div>
																					</div>
																					<br/>
																					<div class="row">
																						<div class="form-group">
																							<label for="phoneListPanel" class="col-sm-2 control-label">Phone List</label>														
																							<div class="col-sm-10 pull-right">
																								<div id="phoneListPanel" class="panel panel-default">
																									<div class="panel-heading no-padding">
																										<div class="btn-toolbar">
																											<button type="submit" id="phoneButton_minusPhone_<c:out value="${ customerForm.picList[picListLoopIdx.index].personId }"/>_<c:out value="${ picListLoopIdx.index }"/>" class="btn btn-xs btn-primary pull-right"><span class="fa fa-minus fa-fw"></span></button>
																											<button type="submit" id="phoneButton_plusPhone_<c:out value="${ customerForm.picList[picListLoopIdx.index].personId }"/>_<c:out value="${ picListLoopIdx.index }"/>" class="btn btn-xs btn-primary pull-right"><span class="fa fa-plus fa-fw"></span></button>
																										</div>																																																															
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
																											<c:forEach items="${ picListLoop.phoneList }" varStatus="phoneListLoopIdx">
																												<tr>
																													<td align="center">
																														<input id="cbx_phoneList_<c:out value="${ customerForm.picList[picListLoopIdx.index].personId }"/>_<c:out value="${ customerForm.picList[picListLoopIdx.index].phoneList[phoneListLoopIdx.index].phoneListId }"/>" type="checkbox" value="<c:out value="${ phoneListLoopIdx.index }"/>"/>
																														<form:hidden path="picList[${picListLoopIdx.index}].phoneList[${phoneListLoopIdx.index}].phoneListId"/>
																													</td>
																													<td>
																														<form:select class="form-control" path="picList[${picListLoopIdx.index}].phoneList[${phoneListLoopIdx.index}].providerName">
																															<form:options items="${ providerDDL }" itemValue="lookupKey" itemLabel="lookupValue"/>
																														</form:select>			
																													</td>
																													<td>
																														<form:input type="text" class="form-control" path="picList[${picListLoopIdx.index}].phoneList[${phoneListLoopIdx.index}].phoneNumber"/>
																													</td>
																													<td>
																														<form:select class="form-control" path="picList[${picListLoopIdx.index}].phoneList[${phoneListLoopIdx.index}].phoneStatus">
																															<form:options items="${ statusDDL }" itemValue="lookupKey" itemLabel="lookupValue"/>
																														</form:select>
																													</td>
																													<td>
																														<form:input type="text" class="form-control" path="picList[${picListLoopIdx.index}].phoneList[${phoneListLoopIdx.index}].phoneNumRemarks"/>
																													</td>																					
																												</tr>
																											</c:forEach>
																										</tbody>
																									</table>
																								</div>
																							</div>
																						</div>
																	            	</div>
																	            </div>
																		        <ul class="list-group">	
																		        	<li class="list-group-item">
																		        		<div class="checkbox">
																		        			<input id="cbx_picList_<c:out value="${ customerForm.picList[picListLoopIdx.index].personId }"/>" type="checkbox" value="<c:out value="${ picListLoopIdx.index }"/>"/>
																		        			<label for="cbx_picList_<c:out value="${ customerForm.picList[picListLoopIdx.index].personId }"/>">Delete</label> 
																		        		</div>
																		        	</li>
																		        </ul>															            
																		        <div class="panel-footer">
																		        	<div class="btn-toolbar">
																		        		<button type="submit" id="deletePerson" class="btn btn-xs btn-primary pull-right"><span class="fa fa-close fa-fw"></span>&nbsp;Delete</button>
																		        	</div>
																		        </div>
																	        </div>
																	    </div>
																    </c:forEach>
																</div>															
															</c:otherwise>
														</c:choose>
													</div>
												</div>
											</div>
											<div role="tabpanel" class="tab-pane <c:if test="${ activeTab == 'bankAccTab' }"><c:out value="active"/></c:if>" id="bankAccTab">
												<br/>
												<c:if test="${ empty editBankIdx }">
													<div id="bankAccListPanel" class="panel panel-default">
														<div class="panel-heading">
															<div class="btn-toolbar">
																<button type="submit" id="deleteBankAcc" class="btn btn-xs btn-primary pull-right"><span class="fa fa-close fa-fw"></span>&nbsp;Delete</button>&nbsp;&nbsp;&nbsp;
																<button type="submit" id="editBankAcc" class="btn btn-xs btn-primary pull-right"><span class="fa fa-edit fa-fw"></span>&nbsp;Edit</button>&nbsp;&nbsp;&nbsp;
																<button type="submit" id="addBankAcc" class="btn btn-xs btn-primary pull-right"><span class="fa fa-plus fa-fw"></span>&nbsp;Add</button>
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
																<c:forEach items="${ customerForm.bankAccList }" varStatus="baIdx">
																	<tr>
																		<td align="center">
																			<input id="cbx_bankAccId_<c:out value="${ customerForm.bankAccList[baIdx.index].bankAccId }"/>" type="checkbox" value="<c:out value="${ baIdx.index }"/>"/>
																			<form:hidden path="bankAccList[${ baIdx.index }].bankAccId"/>
																		</td>
																		<td>
																			<c:out value="${ customerForm.bankAccList[baIdx.index].shortName }"/>
																			&nbsp;-&nbsp;
																			<c:out value="${ customerForm.bankAccList[baIdx.index].bankName }"/>
																		</td>
																		<td><c:out value="${ customerForm.bankAccList[baIdx.index].accNum }"/></td>
																		<td><c:out value="${ customerForm.bankAccList[baIdx.index].bankRemarks }"/></td>
																		<td><c:out value="${ customerForm.bankAccList[baIdx.index].bankStatus }"/></td>
																	</tr>
																</c:forEach>
															</tbody>
														</table>
													</div>
												</c:if>												
												<c:forEach items="${ customerForm.bankAccList }" varStatus="baIdx">
													<c:set var="collapseFlag" value="collapse"/>
													<c:if test="${ editBankIdx == baIdx.index }">
														<c:set var="collapseFlag" value=""/>
														<div id="currentBankAccSelected">
															<input id="currentBankAccIdSelected" type="hidden" value="<c:out value="${ customerForm.bankAccList[baIdx.index].bankAccId }"/>"/>
															<input id="currentBankAccIndexSelected" type="hidden" value="<c:out value="${ baIdx.index }"/>"/>
															<input id="bankAccButtonMode" type="hidden" value="${ bankAccButtonMode }"/>
															
															<input id="fromDB_shortName" type="hidden" value="<c:out value="${ customerForm.bankAccList[baIdx.index].shortName }"/>"/>
															<input id="fromDB_bankName" type="hidden" value="<c:out value="${ customerForm.bankAccList[baIdx.index].bankName }"/>"/>
															<input id="fromDB_accNum" type="hidden" value="<c:out value="${ customerForm.bankAccList[baIdx.index].accNum }"/>"/>
															<input id="fromDB_bankStatus" type="hidden" value="<c:out value="${ customerForm.bankAccList[baIdx.index].bankStatus }"/>"/>
															<input id="fromDB_bankRemarks" type="hidden" value="<c:out value="${ customerForm.bankAccList[baIdx.index].bankRemarks }"/>"/>
														</div>
													</c:if>													
													<div id="bankAccListInputPanel_<c:out value="${ customerForm.bankAccList[baIdx.index].bankAccId }"/>" class="panel panel-default <c:out value="${ collapseFlag }"/>">
														<form:hidden path="bankAccList[${ baIdx.index }].bankAccId"/>
														<br/>
														<div class="row">
															<label for="shortName" class="col-sm-2 control-label">Short Name</label>
															<div class="col-sm-2"><form:input type="text" class="form-control" path="bankAccList[${ baIdx.index }].shortName" data-parsley-required="true" data-parsley-trigger="keyup"/></div>
														</div>
														<br/>
														<div class="row">
															<label for="bankName" class="col-sm-2 control-label">Bank Name</label>
															<div class="col-sm-4"><form:input type="text" class="form-control" path="bankAccList[${ baIdx.index }].bankName" data-parsley-required="true" data-parsley-trigger="keyup"/></div>
														</div>
														<br/>
														<div class="row">
															<label for="accountNumber" class="col-sm-2 control-label">Account</label>
															<div class="col-sm-5"><form:input type="text" class="form-control" path="bankAccList[${ baIdx.index }].accNum" data-parsley-required="true" data-parsley-trigger="keyup"/></div>
														</div>
														<br/>
														<div class="row">
															<label for="bankStatus" class="col-sm-2 control-label">Status</label>
															<div class="col-sm-3">
																<form:select class="form-control" path="bankAccList[${ baIdx.index }].bankStatus">
																	<form:options items="${ statusDDL }" itemValue="lookupKey" itemLabel="lookupValue"/>
																</form:select>
															</div>																																	
														</div>
														<br/>
														<div class="row">
															<label for="bankAccRemarks" class="col-sm-2 control-label">Remarks</label>
															<div class="col-sm-6"><form:input type="text" class="form-control" path="bankAccList[${ baIdx.index }].bankRemarks"/></div>
														</div>
														<br/>
														<div class="row">
															<label for="bankAccButton" class="col-sm-2 control-label">&nbsp;</label>
															<div class="col-sm-5">																 
																<button id="saveBankAcc" type="submit" class="btn btn-sm btn-primary">Save</button>
																<button id="discardBankAcc" type="submit" class="btn btn-sm btn-primary">Discard</button>
															</div>
														</div>
														<br/>
													</div>
												</c:forEach>												
											</div>
											<div role="tabpanel" class="tab-pane <c:if test="${ activeTab == 'settingsTab' }"><c:out value="active"/></c:if>" id="settingsTab">
												&nbsp;
											</div>
										</div>
									</div>
									<hr>
									<div class="col-md-7 col-offset-md-5">
										<div class="btn-toolbar">
											<button id="cancelButton" type="reset" class="btn btn-primary pull-right"><spring:message code="common.cancel_button" text="Cancel"/></button>
											<button id="submitButton" type="submit" class="btn btn-primary pull-right"><spring:message code="common.submit_button" text="Submit"/></button>
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
