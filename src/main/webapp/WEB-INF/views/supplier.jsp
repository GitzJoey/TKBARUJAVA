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
					$('#supplierForm').attr('action', ctxpath + "/supplier/edit/" + $('#supplierId').val() + "/bank/addbank/0");
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
							$('#supplierForm').attr('action', ctxpath + "/supplier/edit/" + $('#supplierId').val() + "/bank/editbank/" + bankAccId);
						} else {
							$('#supplierForm').attr('action', ctxpath + "/supplier/edit/" + $('#supplierId').val() + "/bank/deletebank/" + bankAccId);
						}
					}
				}				
			});
					
			$('#saveBankAcc, #discardBankAcc').click(function() {
				var button = $(this).attr('id');
				var bankAccButtonMode = $('#bankAccButtonMode').val();
				
				if (button == 'saveBankAcc') {
					$('#supplierForm').attr('action', ctxpath + "/supplier/edit/" + $('#supplierId').val() + "/bank/" + bankAccButtonMode + "/" + $('#currentBankAccIdSelected').val() + "/save");
				} else if (button == 'discardBankAcc') {
					var index = $('#currentBankAccIndexSelected').val();
					
					$('input[name="bankAccList[' + index + '].shortName"]').val($('#fromDB_shortName').val());
					$('input[name="bankAccList[' + index + '].bankName"]').val($('#fromDB_bankName').val());
					$('input[name="bankAccList[' + index + '].accNum"]').val($('#fromDB_accNum').val());
					$('input[name="bankAccList[' + index + '].bankStatus"]').val($('#fromDB_bankStatus').val());
					$('input[name="bankAccList[' + index + '].bankRemarks"]').val($('#fromDB_bankRemarks').val());					
					
					$('#supplierForm').attr('action', ctxpath + "/supplier/edit/" + $('#supplierId').val() + "/bank/" + bankAccButtonMode + "/" + $('#currentBankAccIdSelected').val() + "/discard");
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
			
			$('#addPerson, #deletePerson').click(function() {				
				var hasSelected = false;
				var button = $(this).attr('id');
				var supplierId = $('#supplierId').val().length == 0 ? 0 : $('#supplierId').val();
				
				if (button == 'addPerson') {
					$('#supplierForm').parsley().destroy();
					$('#supplierForm').attr('action', ctxpath + "/supplier/edit/" + supplierId + "/person/addperson/0");
				} else {
					$('input[id^="cbx_picList_"]').each(function(index, item) {
						if ($(item).prop("checked") == true) {
							hasSelected = true;
							personId = $(item).attr('id').split('_')[2];
						}
					});
					
					if (!hasSelected) { jsAlert('Please select at least 1 person'); return false;}
					else {
						if (button == 'deletePerson') {
							$('#supplierForm').parsley().destroy();
							$('#supplierForm').attr('action', ctxpath + "/supplier/edit/" + supplierId + "/person/deleteperson/" + personId);
						} else {
							return false;
						}
					}
				}
			});

			$('#savePerson, #discardPerson').click(function() {
				var button = $(this).attr('id');
				var personButtonMode = $('#personButtonMode').val();
				
				if (button == 'savePerson') {
					$('#supplierForm').attr('action', ctxpath + "/supplier/edit/" + $('#supplierId').val() + "/person/" + personButtonMode + "/" + $('#currentPersonIdSelected').val() + "/save");
				} else {
					$('#supplierForm').attr('action', ctxpath + "/supplier/edit/" + $('#supplierId').val() + "/person/" + personButtonMode + "/" + $('#currentPersonIdSelected').val() + "/discard");
				}
			});

			$('button[id^="phoneButton_"]').click(function() {
				var button = $(this).attr('id').split('_')[1];
				var prsnId = $(this).attr('id').split('_')[2].length == 0 ? 0 : $(this).attr('id').split('_')[2];
				var prsnIdx = $(this).attr('id').split('_')[3];
				var suppId = $('#supplierId').val().length == 0 ? 0 : $('#supplierId').val();
				
				if (button == 'plusPhone') {
					$('#supplierForm').attr('action', ctxpath + "/supplier/edit/" + suppId + "/person/" + prsnId + "/" + prsnIdx + "/addphone/0/0");
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
						$('#supplierForm').attr('action', ctxpath + "/supplier/edit/" + suppId + "/person/" + prsnId + "/" + prsnIdx + "/deletephone/" + phoneListId + "/" + phoneListIndex);	
					}
				}
			});
			
			$('#cancelButton').click(function() {				
				window.location.href = ctxpath + "/supplier";
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

			$('input[type="checkbox"][id^="prdList_"]').click(function() {
				var selected = $(this);
				var spl = "";
				
				$('input[type="checkbox"][id^="prdList_"]').each(function(index, item) {
					if ($(item).prop("checked")) {
						spl += $(this).val() + ",";		
					}					
				});
				
				$('#selectedPrdList').val(spl);
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
					jsAlert("Please select at least 1 username");
					return false;	
				} else {
					if (button == 'editTableSelection') {
						$('#editTableSelection').attr("href", ctxpath + "/supplier/edit/" + id);	
					} else {
						$('#deleteTableSelection').attr("href", ctxpath + "/supplier/delete/" + id);	
					}
						
				}				
			});			

	        $.listen('parsley:field:validate', function() {
	        	validateFront();
	        });
	        
	        var validateFront = function () {
				if (true === $('#supplierForm').parsley().isValid("tab1", false)) {
	              	$('#suppDataTabError').addClass('hidden');
	            } else {
	            	$('#suppDataTabError').removeClass('hidden');
	            }

				if (true === $('#supplierForm').parsley().isValid("tab2", false)) {
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
					<span class="fa fa-building-o fa-fw"></span>&nbsp;<spring:message code="supplier_jsp.title" text="Supplier"/>
				</h1>

				<c:choose>
					<c:when test="${ PAGEMODE == 'PAGEMODE_PAGELOAD' || PAGEMODE == 'PAGEMODE_LIST' || PAGEMODE == 'PAGEMODE_DELETE' }">
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
												<th width="95%"><spring:message code="supplier_jsp.table.header.supplier_details" text="Supplier Details"/></th>
											</tr>
										</thead>
										<tbody>
											<c:if test="${ not empty supplierList }">
												<c:forEach var="i" varStatus="status" items="${ supplierList }">
													<tr>
														<td align="center"><input id="cbx_<c:out value="${ i.supplierId }"/>" type="checkbox" value="<c:out value="${ i.supplierId }"/>"/></td>
														<td>
															<table class="table borderless">
																<tbody>
																	<tr>
																		<td>
																			<table class="table borderless nopaddingrow">
																				<tr>
																					<td colspan="2">
																						<strong class="title"><c:out value="${ i.supplierName }"></c:out></strong>
																						<hr>
																					</td>
																				</tr>
																				<tr>
																					<td width="35%">
																						<strong><spring:message code="supplier_jsp.table.list.header.supplier_detail" text="Supplier Details"/></strong><br/>
																						<c:out value="${ i.supplierAddress }"></c:out><br/>
																						<c:out value="${ i.supplierCity }"></c:out><br/>															
																						<c:out value="${ i.supplierPhone }"></c:out><br/>
																						<c:out value="${ i.npwpNum }"></c:out><br/>
																						<c:out value="${ i.supplierRemarks }"></c:out><br/>
																					</td>
																					<td width="65%">
																						<strong><spring:message code="supplier_jsp.table.list.header.person_in_charge" text="Person In Charge"/></strong>
																						<br/>
																						<c:forEach items="${ i.picList }" var="iPIC">
																							<c:out value="${ iPIC.firstName }"/><br/>
																						</c:forEach>
																						<br/>
																						<strong><spring:message code="supplier_jsp.table.list.header.bank_account" text="Bank Account"/></strong>
																						<br/>
																						<c:forEach items="${ i.bankAccList }" var="iBA">
																							<c:out value="${ iBA.shortName }"/><br/>
																						</c:forEach>
																						<br/>
																						<strong><spring:message code="supplier_jsp.table.list.header.product_list" text="Product List"/></strong>
																						<br/>
																						<c:forEach items="${ i.prodList }" var="iProd">
																							<c:out value="${ iProd.productName }"/><br/>
																						</c:forEach>
																						<br/>
																						<strong><spring:message code="supplier_jsp.table.list.header.settings" text="Settings"/></strong>
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
								<a id="addNew" class="btn btn-sm btn-primary" href="${pageContext.request.contextPath}/supplier/add"><span class="fa fa-plus fa-fw"></span>&nbsp;<spring:message code="common.add_button" text="Add"/></a>&nbsp;&nbsp;&nbsp;
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
											<span class="fa fa-plus fa-fw fa-2x"></span>&nbsp;<spring:message code="supplier_jsp.add_supplier" text="Add Supplier"/>
										</c:when>
										<c:otherwise>
											<span class="fa fa-edit fa-fw fa-2x"></span>&nbsp;<spring:message code="supplier_jsp.edit_supplier" text="Edit Supplier"/>
										</c:otherwise>
									</c:choose>
								</h1>
							</div>
							<div class="panel-body">
								<form:form id="supplierForm" role="form" class="form-horizontal" modelAttribute="supplierForm" action="${pageContext.request.contextPath}/supplier/save" data-parsley-validate="parsley">
									<div role="tabpanel">
										<ul class="nav nav-tabs" role="tablist">
											<li role="presentation" class="<c:if test="${ activeTab == 'suppDataTab' }"><c:out value="active"/></c:if>"><a href="#suppDataTab" aria-controls="suppDataTab" role="tab" data-toggle="tab"><span class="fa fa-info-circle fa-fw"></span>&nbsp;<spring:message code="supplier_jsp.tab.supplier_data" text="Supplier Data"/>&nbsp;<span id="suppDataTabError" class="parsley-asterisk hidden">*</span></a></li>
											<li role="presentation" class="<c:if test="${ activeTab == 'picTab' }"><c:out value="active"/></c:if>"><a href="#picTab" aria-controls="picTab" role="tab" data-toggle="tab"><span class="fa fa-key fa-fw"></span>&nbsp;<spring:message code="supplier_jsp.tab.person_in_charge" text="Person In Charge"/>&nbsp;<span id="picTabError" class="parsley-asterisk hidden">*</span></a></li>
											<li role="presentation" class="<c:if test="${ activeTab == 'bankAccTab' }"><c:out value="active"/></c:if>"><a href="#bankAccTab" aria-controls="bankAccTab" role="tab" data-toggle="tab"><span class="fa  fa-bank fa-fw"></span>&nbsp;<spring:message code="supplier_jsp.tab.bank_account" text="Bank Account"/></a></li>
											<li role="presentation" class="<c:if test="${ activeTab == 'prodTab' }"><c:out value="active"/></c:if>"><a href="#prodTab" aria-controls="prodTab" role="tab" data-toggle="tab"><span class="fa fa-cubes fa-fw"></span>&nbsp;<spring:message code="supplier_jsp.tab.product_list" text="Product List"/></a></li>
											<li role="presentation" class="<c:if test="${ activeTab == 'settingsTab' }"><c:out value="active"/></c:if>"><a href="#settingsTab" aria-controls="settingsTab" role="tab" data-toggle="tab"><span class="fa  fa-cogs fa-fw"></span>&nbsp;<spring:message code="supplier_jsp.tab.settings" text="Settings"/></a></li>
										</ul>

										<div class="tab-content">
											<div role="tabpanel" class="tab-pane <c:if test="${ activeTab == 'suppDataTab' }"><c:out value="active"/></c:if>" id="suppDataTab">
												<br/>
												<div class="form-group">
													<label for="inputSupplierName" class="col-sm-2 control-label"><spring:message code="supplier_jsp.supplier_name" text="Supplier Name"/></label>
													<div class="col-sm-3">
														<form:hidden path="supplierId"/>
														<form:input type="text" class="form-control" id="inputSupplierName" name="inputSupplierName" path="supplierName" placeholder="Enter Supplier Name" data-parsley-required="true" data-parsley-trigger="keyup" data-parsley-group="tab1"></form:input>
													</div>
												</div>
												<div class="form-group">
													<label for="inputSupplierAddress" class="col-sm-2 control-label"><spring:message code="supplier_jsp.supplier_address" text="Address"/></label>
													<div class="col-sm-3">
														<form:input type="text" class="form-control" id="inputSupplierAddress" name="inputSupplierAddress" path="supplierAddress" placeholder="Enter Supplier Address" data-parsley-required="true" data-parsley-trigger="keyup" data-parsley-group="tab1"></form:input>
													</div>
												</div>
												<div class="form-group">
													<label for="inputCity" class="col-sm-2 control-label"><spring:message code="supplier_jsp.supplier_city" text="City"/></label>
													<div class="col-sm-5">											
														<form:input type="text" class="form-control" id="inputCity" name="inputCity" path="supplierCity" placeholder="City" data-parsley-required="true" data-parsley-trigger="keyup" data-parsley-group="tab1"></form:input>
													</div>
												</div>
												<div class="form-group">
													<label for="inputPhoneNumber" class="col-sm-2 control-label"><spring:message code="supplier_jsp.supplier_phone_num" text="Phone Number"/></label>
													<div class="col-sm-4">
														<form:input type="text" class="form-control" id="inputPhoneNumber" name="inputPhoneNumber" path="supplierPhone"  placeholder="Enter Supplier Phone Number" data-parsley-required="true" data-parsley-trigger="keyup"></form:input>
													</div>
												</div>                     
												<div class="form-group">
													<label for="inputFax" class="col-sm-2 control-label"><spring:message code="supplier_jsp.supplier_fax" text="Fax"/></label>
													<div class="col-sm-4">
														<form:input type="text" class="form-control" id="inputFax" name="inputFax" path="supplierFax" placeholder="Fax"></form:input>
													</div>
												</div>
												<div class="form-group">
													<label for="inputNpwpNum" class="col-sm-2 control-label"><spring:message code="supplier_jsp.supplier_npwp" text="NPWP"/></label>
													<div class="col-sm-6">
														<form:input type="text" class="form-control" id="inputNpwpNum" name="inputNpwpNum" path="npwpNum"  placeholder="Enter Supplier NPWP"></form:input>
													</div>
												</div>        
												<div class="form-group">
													<label for="inputSupplierRemarks" class="col-sm-2 control-label"><spring:message code="supplier_jsp.supplier_remarks" text="Remarks"/></label>
													<div class="col-sm-10">
														<form:input type="text" class="form-control" id="inputSupplierRemarks" path="supplierRemarks" name="inputSupplierRemarks" placeholder="Remarks"></form:input>
													</div>
												</div>
												<div class="form-group">
													<label for="inputStatus" class="col-sm-2 control-label"><spring:message code="supplier_jsp.supplier_status" text="Status"/></label>
													<div class="col-sm-2">
														<form:select class="form-control" path="supplierStatusLookup.lookupKey" data-parsley-required="true">
															<option value=""><spring:message code="common.please_select"></spring:message></option>
															<form:options items="${ statusDDL }" itemValue="lookupKey" itemLabel="lookupValue"/>
														</form:select>
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
															<c:when test="${ empty supplierForm.picList }">
																<spring:message code="supplier_jsp.no_data" text="No Data."/>
															</c:when>
															<c:otherwise>
																<div id="accordion_picList" class="panel-group" >															
																	<c:forEach items="${ supplierForm.picList }" var="picListLoop" varStatus="picListLoopIdx">
																		<div class="panel panel-default">
																	        <div class="panel-heading accordion-toggle <c:if test="${ editPersonIdx != picListLoopIdx.index }"><c:out value="collapsed"/></c:if>" data-toggle="collapse" data-parent="#accordion_picList" data-target="#collapse_<c:out value="${ picListLoopIdx.index }"/>">
																	        	<h4 class="panel-title"><spring:message code="supplier_jsp.pic_num" text="PIC"/>&nbsp;<c:out value="${ picListLoopIdx.index + 1 }"/>&nbsp;-&nbsp;<c:out value="${ supplierForm.picList[picListLoopIdx.index].firstName }"/>&nbsp;<c:out value="${ supplierForm.picList[picListLoopIdx.index].lastName }"/></h4>
																	        </div>
																	        <div id="collapse_<c:out value="${ picListLoopIdx.index }"/>" class="panel-collapse <c:if test="${ editPersonIdx != picListLoopIdx.index }"><c:out value="collapse"/></c:if>">
																	            <div class="panel-body">
																					<form:hidden path="picList[${picListLoopIdx.index}].personId"/>
																					<div class="row">
																						<div class="form-group">
																							<label for="firstName" class="col-sm-2 control-label"><spring:message code="supplier_jsp.supplier_pic_name" text="Name"/></label>
																							<div class="col-sm-4">
																								<form:input type="text" class="form-control" path="picList[${picListLoopIdx.index}].firstName" data-parsley-required="true" data-parsley-trigger="keyup" data-parsley-group="tab2"/>
																								<form:input type="text" class="form-control" path="picList[${picListLoopIdx.index}].lastName" data-parsley-required="true" data-parsley-trigger="keyup" data-parsley-group="tab2"/>
																							</div>
																						</div>
																					</div>
																					<br/>
																					<div class="row">
																						<div class="form-group">
																							<label for="addressLine1" class="col-sm-2 control-label"><spring:message code="supplier_jsp.supplier_pic_address" text="Address"/></label>
																							<div class="col-sm-8">
																								<form:input type="text" class="form-control" path="picList[${picListLoopIdx.index}].addressLine1" data-parsley-required="true" data-parsley-trigger="keyup"/>
																								<form:input type="text" class="form-control" path="picList[${picListLoopIdx.index}].addressLine2"/>
																								<form:input type="text" class="form-control" path="picList[${picListLoopIdx.index}].addressLine3"/>
																							</div>
																						</div>									
																					</div>
																					<br/>
																					<div class="row">
																						<div class="form-group">
																							<label for="emailAddr" class="col-sm-2 control-label"><spring:message code="supplier_jsp.supplier_pic_email" text="Email"/></label>
																							<div class="col-sm-5">
																								<form:input type="text" class="form-control" path="picList[${picListLoopIdx.index}].emailAddr"/>
																							</div>																						
																						</div>
																					</div>
																					<br/>
																					<div class="row">
																						<div class="form-group">
																							<label for="phoneListPanel" class="col-sm-2 control-label"><spring:message code="supplier_jsp.supplier_pic_phonelist" text="Phone List"/></label>														
																							<div class="col-sm-10 pull-right">
																								<div id="phoneListPanel" class="panel panel-default">
																									<div class="panel-heading no-padding">
																										<div class="btn-toolbar">
																											<button type="submit" id="phoneButton_minusPhone_<c:out value="${ supplierForm.picList[picListLoopIdx.index].personId }"/>_<c:out value="${ picListLoopIdx.index }"/>" class="btn btn-xs btn-primary pull-right"><span class="fa fa-minus fa-fw"></span></button>
																											<button type="submit" id="phoneButton_plusPhone_<c:out value="${ supplierForm.picList[picListLoopIdx.index].personId }"/>_<c:out value="${ picListLoopIdx.index }"/>" class="btn btn-xs btn-primary pull-right"><span class="fa fa-plus fa-fw"></span></button>
																										</div>																																																															
																									</div>
																									<table id="phoneListTable" class="table table-bordered table-hover">
																										<thead>
																											<tr>
																												<th width="5%">&nbsp;</th>
																												<th width="15%"><spring:message code="supplier_jsp.supplier_pic_phonelist.provider" text="Provider"/></th>
																												<th width="15%"><spring:message code="supplier_jsp.supplier_pic_phonelist.number" text="Number"/></th>
																												<th width="15%"><spring:message code="supplier_jsp.supplier_pic_phonelist.status" text="Status"/></th>
																												<th width="25%"><spring:message code="supplier_jsp.supplier_pic_phonelist.remarks" text="Remarks"/></th>
																											</tr>
																										</thead>
																										<tbody>
																											<c:forEach items="${ picListLoop.phoneList }" varStatus="phoneListLoopIdx">
																												<tr>
																													<td align="center">
																														<input id="cbx_phoneList_<c:out value="${ supplierForm.picList[picListLoopIdx.index].personId }"/>_<c:out value="${ supplierForm.picList[picListLoopIdx.index].phoneList[phoneListLoopIdx.index].phoneListId }"/>" type="checkbox" value="<c:out value="${ phoneListLoopIdx.index }"/>"/>
																														<form:hidden path="picList[${picListLoopIdx.index}].phoneList[${phoneListLoopIdx.index}].phoneListId"/>
																													</td>
																													<td>
																														<form:select class="form-control" path="picList[${picListLoopIdx.index}].phoneList[${phoneListLoopIdx.index}].providerLookup.lookupValue">
																															<form:options items="${ providerDDL }" itemValue="lookupKey" itemLabel="lookupValue"/>
																														</form:select>																						
																													</td>
																													<td>
																														<form:input type="text" class="form-control" path="picList[${picListLoopIdx.index}].phoneList[${phoneListLoopIdx.index}].phoneNumber" data-parsley-required="true" data-parsley-trigger="keyup"/>
																													</td>
																													<td>
																														<form:select class="form-control" path="picList[${picListLoopIdx.index}].phoneList[${phoneListLoopIdx.index}].phoneStatusLookup.lookupKey">
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
																							<input id="cbx_picList_<c:out value="${ supplierForm.picList[picListLoopIdx.index].personId }"/>" type="checkbox" value="<c:out value="${ picListLoopIdx.index }"/>"/>
																							<label for="cbx_picList_<c:out value="${ supplierForm.picList[picListLoopIdx.index].personId }"/>"><spring:message code="supplier_jsp.supplier_pic_delete_check" text="Delete"/></label>
																						</div>
																					</li>
																				</ul>
																        		<div class="panel-footer">
															        				<div class="btn-toolbar">
																	        			<button type="submit" id="deletePerson" class="btn btn-xs btn-primary pull-right"><span class="fa fa-close fa-fw"></span>&nbsp;<spring:message code="supplier_jsp.supplier_pic_delete_button" text="Delete"/></button>
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
																	<th>&nbsp;<spring:message code="supplier_jsp.tab.bank.table.header.bank_name" text="Bank Name"/></th>
																	<th>&nbsp;<spring:message code="supplier_jsp.tab.bank.table.header.account" text="Account"/></th>
																	<th>&nbsp;<spring:message code="supplier_jsp.tab.bank.table.header.remarks" text="Remarks"/></th>
																	<th>&nbsp;<spring:message code="supplier_jsp.tab.bank.table.header.status" text="Status"/></th>
																</tr>
															</thead>
															<tbody>
																<c:forEach items="${ supplierForm.bankAccList }" varStatus="baIdx">
																	<tr>
																		<td align="center">
																			<input id="cbx_bankAccId_<c:out value="${ supplierForm.bankAccList[baIdx.index].bankAccId }"/>" type="checkbox" value="<c:out value="${ baIdx.index }"/>"/>
																			<form:hidden path="bankAccList[${ baIdx.index }].bankAccId"/>
																		</td>
																		<td>
																			<c:out value="${ supplierForm.bankAccList[baIdx.index].shortName }"/>
																			&nbsp;-&nbsp;
																			<c:out value="${ supplierForm.bankAccList[baIdx.index].bankName }"/>
																		</td>
																		<td><c:out value="${ supplierForm.bankAccList[baIdx.index].accNum }"/></td>
																		<td><c:out value="${ supplierForm.bankAccList[baIdx.index].bankRemarks }"/></td>
																		<td><spring:message code="${ supplierForm.bankAccList[baIdx.index].bankAccStatusLookup.i18nLookupValue }" text="${ supplierForm.bankAccList[baIdx.index].bankAccStatusLookup.lookupValue }"/></td>
																	</tr>
																</c:forEach>
															</tbody>
														</table>
													</div>
												</c:if>		
												<c:forEach items="${ supplierForm.bankAccList }" varStatus="baIdx">
													<c:set var="collapseFlag" value="collapse"/>
													<c:if test="${ editBankIdx == baIdx.index }">
														<c:set var="collapseFlag" value=""/>
														<div id="currentBankAccSelected">
															<input id="currentBankAccIdSelected" type="hidden" value="<c:out value="${ supplierForm.bankAccList[baIdx.index].bankAccId }"/>"/>
															<input id="currentBankAccIndexSelected" type="hidden" value="<c:out value="${ baIdx.index }"/>"/>
															<input id="bankAccButtonMode" type="hidden" value="${ bankAccButtonMode }"/>
															
															<input id="fromDB_shortName" type="hidden" value="<c:out value="${ supplierForm.bankAccList[baIdx.index].shortName }"/>"/>
															<input id="fromDB_bankName" type="hidden" value="<c:out value="${ supplierForm.bankAccList[baIdx.index].bankName }"/>"/>
															<input id="fromDB_accNum" type="hidden" value="<c:out value="${ supplierForm.bankAccList[baIdx.index].accNum }"/>"/>
															<input id="fromDB_bankStatus" type="hidden" value="<c:out value="${ supplierForm.bankAccList[baIdx.index].bankAccStatusLookup.lookupValue }"/>"/>
															<input id="fromDB_bankRemarks" type="hidden" value="<c:out value="${ supplierForm.bankAccList[baIdx.index].bankRemarks }"/>"/>
														</div>
													</c:if>													
													<div id="bankAccListInputPanel_<c:out value="${ supplierForm.bankAccList[baIdx.index].bankAccId }"/>" class="panel panel-default <c:out value="${ collapseFlag }"/>">
														<form:hidden path="bankAccList[${ baIdx.index }].bankAccId"/>
														<br/>
														<div class="row">
															<label for="shortName" class="col-sm-2 control-label"><spring:message code="supplier_jsp.supplier_bankacc_short_name" text="Short Name"/></label>
															<div class="col-sm-2"><form:input type="text" class="form-control" path="bankAccList[${ baIdx.index }].shortName"/></div>
														</div>
														<br/>
														<div class="row">
															<label for="bankName" class="col-sm-2 control-label"><spring:message code="supplier_jsp.supplier_bankacc_bank_name" text="Bank Name"/></label>
															<div class="col-sm-4"><form:input type="text" class="form-control" path="bankAccList[${ baIdx.index }].bankName"/></div>
														</div>
														<br/>
														<div class="row">
															<label for="accountNumber" class="col-sm-2 control-label"><spring:message code="supplier_jsp.supplier_bankacc_account" text="Account"/></label>
															<div class="col-sm-5"><form:input type="text" class="form-control" path="bankAccList[${ baIdx.index }].accNum"/></div>
														</div>
														<br/>
														<div class="row">
															<label for="bankStatus" class="col-sm-2 control-label"><spring:message code="supplier_jsp.supplier_bankacc_status" text="Status"/></label>
															<div class="col-sm-3">
																<form:select class="form-control" path="bankAccList[${ baIdx.index }].bankAccStatusLookup.lookupKey">
																	<form:options items="${ statusDDL }" itemValue="lookupKey" itemLabel="lookupValue"/>
																</form:select>
															</div>																																	
														</div>
														<br/>
														<div class="row">
															<label for="bankAccRemarks" class="col-sm-2 control-label"><spring:message code="supplier_jsp.supplier_bankacc_remarks" text="Remarks"/></label>
															<div class="col-sm-6"><form:input type="text" class="form-control" path="bankAccList[${ baIdx.index }].bankRemarks"/></div>
														</div>
														<br/>
														<div class="row">
															<label for="bankAccButton" class="col-sm-2 control-label">&nbsp;</label>
															<div class="col-sm-5">
																<button id="saveBankAcc" type="submit" class="btn btn-sm btn-primary"><spring:message code="supplier_jsp.supplier_bankacc_save_button" text="Save"/></button>
																<button id="discardBankAcc" type="submit" class="btn btn-sm btn-primary"><spring:message code="supplier_jsp.supplier_bankacc_discard_button" text="Discard"/></button>
															</div>
														</div>
														<br/>
													</div>
												</c:forEach>												
											</div>
											<div role="tabpanel" class="tab-pane <c:if test="${ activeTab == 'prodTab' }"><c:out value="active"/></c:if>" id="prodTab">
												<br/>
												<input type="hidden" id="selectedPrdList" name="selectedPrdList" value="${ selectedPrdList }"/>
												<div class="table-responsive">
													<table class="table table-bordered table-hover">
														<thead>
															<tr>
																<th width="5%">&nbsp;</th>
																<th width="10%"><spring:message code="supplier_jsp.prodlist.table.type" text="Type"/></th>
																<th width="10%"><spring:message code="supplier_jsp.prodlist.table.code" text="Code"/></th>
																<th width="20%"><spring:message code="supplier_jsp.prodlist.table.name" text="Name"/></th>
																<th width="10%"><spring:message code="supplier_jsp.prodlist.table.base_unit" text="Base Unit"/></th>
																<th width="30%"><spring:message code="supplier_jsp.prodlist.table.description" text="Description"/></th>
																<th width="10%"><spring:message code="supplier_jsp.prodlist.table.status" text="Status"/></th>
															</tr>
														</thead>
														<tbody>
															<c:if test="${ not empty productList }">
																<c:forEach items="${ productList }" var="i" varStatus="productIdx">
																	<tr>
																		<td align="center">
																			<c:set var="selected" value="false"/>
																			<c:forEach items="${ supplierForm.prodList }" var="sP">
																				<c:if test="${ sP.productId == i.productId }">
																					<c:set var="selected" value="true"/>
																				</c:if>
																			</c:forEach>
																			<c:choose>
																				<c:when test="${ selected == 'true' }">
																					<input id="prdList_<c:out value="${ i.productId }"/>" type="checkbox" value="<c:out value="${ i.productId }"/>" checked/>
																				</c:when>
																				<c:otherwise>
																					<input id="prdList_<c:out value="${ i.productId }"/>" type="checkbox" value="<c:out value="${ i.productId }"/>"/>
																				</c:otherwise>
																			</c:choose>
																		</td>
																		<td><c:out value="${ i.productTypeLookup.lookupValue }"></c:out></td>
																		<td><c:out value="${ i.shortCode }"></c:out></td>
																		<td><c:out value="${ i.productName }"></c:out></td>
																		<td>
																			<c:forEach items="${ i.productUnit }" var="pu">
																				<c:if test="${ pu.isBaseUnit == 'true' }">
																					<c:out value="${ pu.unitCodeLookup.lookupValue }"></c:out>
																				</c:if>	
																			</c:forEach>
																		</td>
																		<td><c:out value="${ i.productDesc }"></c:out></td>
																		<td><spring:message code="${ i.productStatusLookup.i18nLookupValue }" text="${ i.productStatusLookup.lookupValue }"></spring:message></td>
																	</tr>
																</c:forEach>
															</c:if>
															<c:if test="${ empty productList }">
																<tr>
																	<td colspan="8"><spring:message code="supplier_jsp.data_not_found" text="Data not found"/></td>
																</tr>
															</c:if>
														</tbody>
													</table>
												</div>
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