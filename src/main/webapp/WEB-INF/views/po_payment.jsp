<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
	<jsp:include page="/WEB-INF/views/include/headtag.jsp"></jsp:include>
	<script>
		$(document).ready(function() {
			var ctxpath = "${ pageContext.request.contextPath }";

			$('#cancelButton').click(function() {
				window.location = (ctxpath + "/po/payment");
			});

			$('.datepicker').datetimepicker({ format:'d-m-Y', timepicker:false });
			
			$('.datepicker').on('dp.change dp.show',function(e) {
				$(this).parsley().validate();
			});

			$('#cashPayButton, #transferPayButton, #termPayButton, #giroPayButton').click(function() {
				var id = "";
				var button = $(this).attr('id');

				$('input[type="checkbox"][id^="cbx_"]').each(function(index, item) {
					if ($(item).prop('checked')) {
						id = $(item).attr("value");
					}
				});

				if (id == "") {
					jsAlert("Please select at least 1 po");
					return false;
				} else {
					if(button == 'cashPayButton'){
						$('#cashPayButton').attr("href", ctxpath + "/po/payment/cash/"+ id);
					} else if (button == 'transferPayButton') {
						$('#transferPayButton').attr("href", ctxpath + "/po/payment/transfer/"+ id);
					} else if (button == 'termPayButton') {
						$('#termPayButton').attr("href", ctxpath+ "/po/payment/term/"+ id);
					} else if (button == 'giroPayButton') {
						$('#giroPayButton').attr("href", ctxpath+ "/po/payment/giro/"+ id);
					} else {
						return false;
					}
				}
			});

			$('#addPayButton, #removePayButton').click(function() {
				var id = "";
				var button = $(this).attr('id');

				if (button == 'addPayButton') {
					$("#paymentSelect").parsley().validate();
					
					if (false == $('#paymentSelect').parsley().isValid()) {						
						return false;					
		            } else {
						id = $("#paymentSelect").val();
						$('#poForm').attr('action',ctxpath + "/po/addpayment/"+ id);
		            }
				} else {
					id = $(this).val();
					$('#poForm').attr('action',ctxpath + "/po/removepayment/"+ id);
				}
			});

			var supplier = $("#inputSupplierId").val();
			$("#supplierTooltip").tooltip({ title : supplier });
			
			$('#poPaymentListTable').DataTable();
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
				<c:if test="${ERRORPAGE == 'ERRORPAGE_SHOW'}">
					<div class="alert alert-danger alert-dismissible collapse"
						role="alert">
						<button type="button" class="close" data-dismiss="alert">
							<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
						</button>
						<h4>
							<strong>Warning!</strong>
						</h4>
						<br> ${errorMessageText}
					</div>
				</c:if>
				
				<div id="jsAlerts"></div>
				
				<h1>
					<span class="fa fa-truck fa-fw"></span>&nbsp;PO Payment
				</h1>
				
				<c:choose>
					<c:when test="${ PAGEMODE == 'PAGEMODE_LIST' }">
						<div class="panel panel-default">
							<div class="panel-heading">
								<h1 class="panel-title">
									<span class="fa fa-calculator fa-fw fa-2x"></span>&nbsp;PO
									Payment
								</h1>
							</div>
							<div class="panel-body">
								<table id="poPaymentListTable"
									class="table table-bordered table-hover display responsive">
									<thead>
										<tr>
											<th width="5%">&nbsp;</th>
											<th width="5%">PO Code</th>
											<th width="15%">Supplier Name</th>
											<th width="10%">Date</th>
											<th width="10%" class="text-right">Total</th>
											<th width="10%" class="text-right">Total Paid</th>
									</tr>
									</thead>
									<tbody>
										<c:if test="${not empty paymentList}">
											<c:forEach items="${ paymentList }" var="p" varStatus="pL">
												<tr>
													<td align="center"><input id="cbx_<c:out value="${ p.poId }"/>" type="checkbox" value="<c:out value="${ p.poId }"/>" /></td>
													<td><c:out value="${ p.poCode }"></c:out></td>
													<td><c:out value="${ p.supplierLookup.supplierName }"></c:out></td>
													<td><fmt:formatDate pattern="dd-MM-yyyy" value="${ p.poCreatedDate }" /></td>
													<td class="text-right"></td>
													<td class="text-right"></td>
												</tr>
											</c:forEach>
										</c:if>
									</tbody>
								</table>
								<a id="cashPayButton" class="btn btn-sm btn-primary" href=""><span class="fa fa-dollar fa-fw"></span>&nbsp;Cash Payment</a>&nbsp;&nbsp;&nbsp;
								<a id="transferPayButton" class="btn btn-sm btn-primary" href=""><span class="fa fa-bank fa-fw"></span>&nbsp;Transfer Payment</a>&nbsp;&nbsp;&nbsp;
								<a id="giroPayButton" class="btn btn-sm btn-primary" href=""><span class="fa fa-book fa-fw"></span>&nbsp;Giro Payment</a>&nbsp;&nbsp;&nbsp;
								<a id="termPayButton" class="btn btn-sm btn-primary" href=""><span class="fa fa-calendar fa-fw"></span>&nbsp;Term Payment</a>&nbsp;&nbsp;&nbsp;
							</div>
						</div>
					</c:when>
					<c:when test="${ PAGEMODE == 'PAGEMODE_EDIT' }">
						<div class="panel panel-default">
							<div class="panel-heading">
								<h1 class="panel-title">
									<span class="fa fa-edit fa-fw fa-2x"></span>&nbsp;New Payment
								</h1>
							</div>
							<div class="panel-body">
								<form:form id="poForm" role="form" class="form-horizontal" modelAttribute="poForm" action="${pageContext.request.contextPath}/po/savepayment" data-parsley-validate="parsley">
									<div id="tabpanel" role="tabpanel">
										<div class="tab-content">
											<div role="tabpanel" class="tab-pane active">
												<br />
												<form:hidden path="poId" />
												<form:hidden path="createdBy" />
												<form:hidden path="createdDate" />
												<div class="row">
													<div class="col-md-12">
														<div class="panel panel-default">
															<div class="panel-body">
																<div class="row">
																	<div class="col-md-7">
																		<div class="form-group">
																			<label for="inputPOCode" class="col-sm-2 control-label">PO Code</label>
																			<div class="col-sm-5">
																				<form:input type="text" class="form-control" readonly="true" id="inputPOCode" name="inputPOCode" path="poCode" placeholder="Enter PO Code"></form:input>
																			</div>
																		</div>
																		<div class="form-group">
																			<label for="inputPOType" class="col-sm-2 control-label">PO Type</label>
																			<div class="col-sm-8">
																				<form:hidden path="poType"></form:hidden>
																				<form:input type="text" class="form-control" readonly="true" id="inputPOType" name="inputPOType" path="poTypeLookup.lookupValue"></form:input>
																			</div>
																		</div>
																		<div class="form-group">
																			<label for="inputSupplierId" class="col-sm-2 control-label">Supplier</label>
																			<div class="col-sm-9">
																				<form:hidden path="supplierId" />
																				<form:input id="inputSupplierId" type="text" class="form-control" path="supplierLookup.supplierName" readonly="true" />
																			</div>
																			<div class="col-sm-1">
																				<button id="supplierTooltip" type="button" class="btn btn-default" data-toggle="tooltip" data-trigger="hover" data-html="true" data-placement="right" data-title="">
																					<span class="fa fa-external-link fa-fw"></span>
																				</button>
																			</div>
																		</div>
																	</div>
																	<div class="col-md-5">
																		<div class="form-group">
																			<label for="inputPoDate" class="col-sm-3 control-label">PO Date</label>
																			<div class="col-sm-9">
																				<form:input type="text" class="form-control" readonly="true" id="poCreatedDate" path="poCreatedDate"></form:input>
																			</div>
																		</div>
																		<div class="form-group">
																			<label for="inputPOStatus" class="col-sm-3 control-label">Status</label>
																			<div class="col-sm-9">
																				<form:hidden path="poStatus"/>
																				<label id="inputPOStatus" class="control-label"><c:out value="${ poForm.statusLookup.lookupValue }"></c:out></label>
																			</div>
																		</div>
																	</div>
																</div>
																<hr>
																<div class="row">
																	<div class="col-md-7">
																		<div class="form-group">
																			<label for="inputShippingDate" class="col-sm-2 control-label">Shipping Date</label>
																			<div class="col-sm-5">
																				<form:input type="text" class="form-control" readonly="true" id="shippingDate" path="shippingDate"></form:input>
																			</div>
																		</div>
																		<div class="form-group">
																			<label for="inputWarehouseId" class="col-sm-2 control-label">Warehouse</label>
																			<div class="col-sm-8">
																				<form:hidden path="warehouseId" />
																				<form:input type="text" class="form-control" path="warehouseLookup.warehouseName" readonly="true" />
																			</div>
																		</div>
																	</div>
																	<div class="col-md-5">
																		<div class="form-group">
																			<div class="col-sm-9"></div>
																		</div>
																	</div>
																</div>
															</div>
														</div>
													</div>
												</div>
												<div class="row">
													<c:set var="lastIdx" value="${ poForm.paymentList.size()-1 }"></c:set>
													<div class="col-md-12">
														<div class="panel panel-default">
															<div class="panel-heading">
																<h1 class="panel-title">Transactions</h1>
															</div>
															<div class="panel-body">
																<br />
																<div class="row">
																	<div class="col-md-12">
																		<table id="itemsListTable" class="table table-bordered table-hover display responsive">
																			<thead>
																				<tr>
																					<th width="30%">Product Name</th>
																					<th width="15%">Quantity</th>
																					<th width="15%" class="text-right">Unit</th>
																					<th width="15%" class="text-right">Price/Base Unit</th>
																					<th width="5%">&nbsp;</th>
																					<th width="20%" class="text-right">Total Price</th>
																				</tr>
																			</thead>
																			<tbody>
																				<c:set var="total" value="${0}" />
																				<c:forEach items="${ poForm.itemsList }" var="iL" varStatus="iLIdx">
																					<tr>
																						<td style="vertical-align: middle;">
																							<form:hidden path="itemsList[${ iLIdx.index }].itemsId"></form:hidden>
																							<form:hidden path="itemsList[${ iLIdx.index }].productId"></form:hidden>
																							<form:hidden path="itemsList[${ iLIdx.index }].productLookup.productName"></form:hidden>
																							<c:out value="${poForm.itemsList[ iLIdx.index ].productLookup.productName }"></c:out>
																						</td>
																						<td class="text-right">
																							<form:hidden path="itemsList[${ iLIdx.index }].prodQuantity"></form:hidden>
																							<label><c:out value="${ poForm.itemsList[ iLIdx.index ].prodQuantity }"></c:out></label>
																						</td>
																						<td>
																							<form:hidden path="itemsList[${ iLIdx.index }].unitCode"></form:hidden>
																							<c:out value="${poForm.itemsList[ iLIdx.index ].unitCodeLookup.lookupValue}"></c:out>
																						</td>
																						<td class="text-right">
																							<form:hidden path="itemsList[${ iLIdx.index }].prodPrice"></form:hidden>
																							<label><c:out value="${ poForm.itemsList[ iLIdx.index ].prodPrice }"></c:out></label>	
																						</td>
																						<td></td>
																						<td class="text-right">
																							<fmt:formatNumber type="number" pattern="##,###.00" value="${ (iL.prodQuantity * iL.prodPrice) }"></fmt:formatNumber>
																						</td>
																					</tr>
																					<c:set var="total" value="${ total + (iL.prodQuantity * iL.prodPrice) }" />
																				</c:forEach>
																			</tbody>
																		</table>
																	</div>
																</div>
																<div class="row">
																	<div class="col-md-12">
																		<table id="itemsTotalListTable" class="table table-bordered table-hover display responsive">
																			<tbody>
																				<tr>
																					<td width="80%" class="text-right">Total</td>
																					<td width="20%" class="text-right"><fmt:formatNumber type="number" pattern="##,###.00" value="${ total }" /></td>
																				</tr>
																			</tbody>
																		</table>
																	</div>
																</div>
															</div>
														</div>
													</div>
												</div>
												<div class="row">
													<div class="col-md-12">
														<div class="panel panel-default">
															<div class="panel-heading">
																<h1 class="panel-title">Remarks</h1>
															</div>
															<div class="panel-body">
																<div class="row">
																	<div class="col-md-12">
																		<div class="form-group">
																			<div class="col-sm-12">
																				<c:out value="${ poForm.poRemarks }"></c:out>
																			</div>
																		</div>
																	</div>
																</div>
															</div>
														</div>
													</div>
												</div>
												<div class="row">
													<div class="col-md-12">
														<div class="panel panel-default">
															<div class="panel-heading">
																<h1 class="panel-title">Payment History</h1>
															</div>
															<div class="panel-body">																		
																<div class="row">
																	<div class="col-md-12">
																		<table id="paymentListTable" class="table table-bordered table-hover display responsive">
																			<thead>
																				<tr>
																					<th width="10%">Payment Type</th>
																					<th width="15%">Payment Date</th>
																					<th width="25%">Bank</th>
																					<th width="15%">Effective Date</th>
																					<th width="15%">&nbsp;</th>
																					<th width="20%" class="text-right">Total Amount</th>
																				</tr>
																			</thead>
																			<tbody>
																				<c:set var="totalPay" value="${0}" />
																				<c:forEach items="${ poForm.paymentList }" var="iL" varStatus="ilIdx" >
																					<c:if test="${ ilIdx.index < lastIdx }">
																						<tr>
																							<td style="vertical-align: middle;">
																								<form:hidden path="paymentList[${ ilIdx.index }].paymentId" />
																								<form:hidden path="paymentList[${ ilIdx.index }].paymentType" />
																								<label><c:out value="${ poForm.paymentList[ ilIdx.index ].paymentTypeLookup.lookupValue }"></c:out></label>
																							</td>
																							<td>
																								<div class="input-group">
																									<form:input type="text" class="form-control" id="effectiveDate_${ ilIdx.index }" path="paymentList[${ ilIdx.index }].paymentDate" readonly="true"></form:input>
																								</div>
																							</td>
																							<td>
																								<div class="input-group">
																									<c:if test="${ poForm.paymentList[ilIdx.index].paymentType == 'L017_TRANSFER' || poForm.paymentList[ilIdx.index].paymentType == 'L017_GIRO'}">
																										<c:forEach items="${ bankDDL }" var="bankL" varStatus="bankIdx">
																											<c:set var="test" value="0" />
																											<c:if test="${bankL.lookupKey == poForm.paymentList[ilIdx.index].bankCode}">
																												<c:set var="test" value="1" />
																											</c:if>
																											<c:choose>
																												<c:when test="${test == 1}">
																													<form:hidden path="paymentList[${ ilIdx.index }].bankCode"/>
																													<form:checkbox id="cbxBank_${ ilIdx.index }" path="paymentList[${ ilIdx.index }].bankCode" disabled="true" value="${ bankL.lookupKey }" label="${ bankL.lookupValue }" />
																													<br>
																												</c:when>
																												<c:otherwise>
																												<c:if test="${ empty poForm.paymentList[ ilIdx.index ].bankCode }">
																													<form:checkbox id="cbxBank_${ ilIdx.index }" path="paymentList[${ ilIdx.index }].bankCode" value="${ bankL.lookupKey }" label="${ bankL.lookupValue }" />
																													<br>
																												</c:if>
																												</c:otherwise>
																											</c:choose>
																										</c:forEach>
																									</c:if>
																									&nbsp;
																								</div>
																							</td>
																							<td>
																								<div class="input-group">
																									<form:input type="text" class="form-control" id="effectiveDate_${ ilIdx.index }" path="paymentList[${ ilIdx.index }].effectiveDate" placeholder="DD-MM-YYYY" readonly="true"></form:input>
																								</div>
																							</td>
																							<td>
																								<form:checkbox id="linked_${ ilIdx.index }" path="paymentList[${ ilIdx.index }].linked" label="linked" disabled="true"/>
																							    <br>
																							    <form:hidden path="paymentList[${ ilIdx.index }].paymentStatus"/>
																							    <c:if test="${ poForm.paymentList[ ilIdx.index ].paymentType == 'L017_CASH'}">
																									<c:forEach items="${ cashStatusDDL }" var="cash" varStatus="cashIdx">						
																										<form:checkbox id="cbx_cash_${ statusIdx.index }" path="paymentList[${ ilIdx.index }].paymentStatus" value="${ cash.lookupKey }" label="${ cash.lookupValue }" disabled="true"/>
																										<br>
																									</c:forEach>
																								</c:if> 
																								<c:if test="${ poForm.paymentList[ ilIdx.index ].paymentType == 'L017_TERM' }">
																									<c:forEach items="${ termStatusDDL }" var="statusL" varStatus="statusIdx">
																										<form:checkbox id="cbx_term_${ statusIdx.index }" path="paymentList[${ ilIdx.index }].paymentStatus" value="${ statusL.lookupKey }" label="${ statusL.lookupValue }" disabled="true"/>
																										<br>
																									</c:forEach>
																								</c:if>
																								<c:if test="${ poForm.paymentList[ ilIdx.index ].paymentType == 'L017_TRANSFER' }">
																									<c:forEach items="${ transferStatusDDL }" var="transfer" varStatus="transferIdx">
																										<form:checkbox id="cbx_transfer_${ transferIdx.index }" path="paymentList[${ ilIdx.index }].paymentStatus" value="${ transfer.lookupKey }" label="${ transfer.lookupValue }" disabled="true"/>
																										<br>
																									</c:forEach>
																								</c:if>
																								<c:if test="${ poForm.paymentList[ ilIdx.index ].paymentType == 'L017_GIRO' }">
																									<c:forEach items="${ giroStatusDDL }" var="giro" varStatus="giroIdx">
																										<form:checkbox id="cbx_giro_${ giroIdx.index }" path="paymentList[${ ilIdx.index }].paymentStatus" value="${ giro.lookupKey }" label="${ giro.lookupValue }" disabled="true"/>
																										<br>
																									</c:forEach>
																								</c:if> 
																							</td>						
																							<td>
																								<div class="input-group">
																									<div class="col-sm-12">
																										<form:input type="text" class="form-control text-right totalAmount" id="totalAmount_${ ilIdx.index }" path="paymentList[${ ilIdx.index }].totalAmount" readonly="true"></form:input>
																									</div>
																								</div>
																							</td>
																						</tr>
																						<c:set var="totalPay" value="${ totalPay + poForm.paymentList[ ilIdx.index ].totalAmount }" />
																					</c:if>
																				</c:forEach>
																			</tbody>
																		</table>
																	</div>
																</div>
																<div class="row">
																	<div class="col-md-12">
																		<table id="itemsTotalListTable" class="table table-bordered table-hover display responsive">
																			<tbody>
																				<tr>
																					<td width="80%" class="text-right">Total</td>
																					<td width="20%" class="text-right"><c:out value="${ totalPay }"></c:out></td>
																				</tr>
																			</tbody>
																		</table>
																	</div>
																</div>
															</div>
														</div>
													</div>
												</div>
												<div class="row">
													<div class="col-md-12">
														<div class="panel panel-default">
															<div class="panel-heading">
																<h1 class="panel-title">
																	<c:choose>
																		<c:when test="${ poForm.paymentList[lastIdx].paymentType == 'L017_TRANSFER' }">
																			Transfer Payment
																		</c:when>
																		<c:when test="${ poForm.paymentList[lastIdx].paymentType == 'L017_GIRO' }">
																			Giro Payment
																		</c:when>
																		<c:when test="${ poForm.paymentList[lastIdx].paymentType == 'L017_TERM' }">
																			Term Payment
																		</c:when>
																		<c:when test="${ poForm.paymentList[lastIdx].paymentType == 'L017_CASH' }">
																			Cash Payment
																		</c:when>
																		<c:otherwise>
																			Payment
																		</c:otherwise>
																	</c:choose>
																</h1>
															</div>
															<div class="panel-body">
																<div class="row">
																	<div class="col-md-7">
																		<div class="form-group">
																			<label for="inputPaymentDate" class="col-sm-2 control-label">Payment Type</label>
																			<div class="col-sm-5">
																				<form:hidden path="paymentList[${ lastIdx }].paymentType" ></form:hidden>
																				<form:input class="form-control" path="paymentList[${ lastIdx }].paymentTypeLookup.lookupValue" readonly="true"></form:input>
																			</div>
																		</div>
																		<c:if test="${ poForm.paymentList[lastIdx].paymentType == 'L017_TRANSFER' || poForm.paymentList[lastIdx].paymentType == 'L017_GIRO'}">
																			<div class="form-group">
																				<label for="inputBank" class="col-sm-2 control-label">Bank</label>
																				<div class="col-sm-8">																			
																					<c:forEach items="${ bankDDL }" var="bankL" varStatus="bankIdx">
																						<c:set var="test" value="0" />
																						<c:if test="${bankL.lookupKey == poForm.paymentList[lastIdx].bankCode}">
																							<c:set var="test" value="1" />
																						</c:if>
																						<c:choose>
																							<c:when test="${test == 1}">
																								<form:hidden path="paymentList[${ lastIdx }].bankCode"/>
																								<form:checkbox id="cbxBank_${ lastIdx }" path="paymentList[${lastIdx}].bankCode" disabled="true" value="${ bankL.lookupKey }" label="${ bankL.lookupValue }" />
																								<br>
																							</c:when>
																							<c:otherwise>
																								<c:if test="${ empty poForm.paymentList[ lastIdx ].bankCode }">
																									<form:checkbox id="cbxBank_${ lastIdx }" path="paymentList[${ lastIdx }].bankCode" value="${ bankL.lookupKey }" label="${ bankL.lookupValue }" />
																									<br>
																								</c:if>
																							</c:otherwise>
																						</c:choose>
																					</c:forEach>
																				</div>
																			</div>
																		</c:if>
																		<div class="form-group">
																			<label for="inputEffectiveDate" class="col-sm-2 control-label">Effective Date</label>
																			<div class="col-sm-9">
																				<form:input id="inputEffectiveDate" type="text" class="form-control datepicker" path="paymentList[${ lastIdx }].effectiveDate" data-parsley-required="true" data-parsley-trigger="change"  />
																			</div>
																		</div>
																	</div>
																	<div class="col-md-5">
																		<div class="form-group">
																			<label for="inputPaymentDate" class="col-sm-3 control-label">Payment Date</label>
																			<div class="col-sm-9">
																				<form:input type="text" class="form-control datepicker" id="inputPaymentDate" path="paymentList[${ lastIdx }].paymentDate" data-parsley-required="true" data-parsley-trigger="change"></form:input>
																			</div>
																		</div>
																		<div class="form-group">
																			<label for="inputTotalAmount" class="col-sm-3 control-label">Total Amount</label>
																			<div class="col-sm-9">
																				<form:input type="text" class="form-control" id="inputTotalAmount" path="paymentList[${ lastIdx }].totalAmount" data-parsley-min="1" data-parsley-trigger="keyup"></form:input>
																			</div>
																		</div>
																		<div class="form-group">
																			<label for="linked_${ lastIdx }" class="col-sm-3 control-label">Status</label>
																			<div class="col-sm-9">
																				<form:checkbox id="linked_${ lastIdx }" path="paymentList[${ lastIdx }].linked" label="linked" />
																		    	<br>
																			    <c:if test="${ poForm.paymentList[ lastIdx ].paymentType == 'L017_CASH'}">
																					<c:forEach items="${ cashStatusDDL }" var="cash" varStatus="cashIdx">
																						<form:checkbox id="cbx_cash_${ cashIdx.index }" path="paymentList[${ lastIdx }].paymentStatus" value="${ cash.lookupKey }" label="${ cash.lookupValue }" />
																						<br>
																					</c:forEach>
																				</c:if> 
																				<c:if test="${ poForm.paymentList[ lastIdx ].paymentType == 'L017_TERM' }">
																					<c:forEach items="${ termStatusDDL }" var="statusL" varStatus="statusIdx">
																						<form:checkbox id="cbx_term_${statusIdx.index}" path="paymentList[${ lastIdx }].paymentStatus" value="${ statusL.lookupKey }" label="${ statusL.lookupValue }" />
																						<br>
																					</c:forEach>
																				</c:if>
																				<c:if test="${ poForm.paymentList[ lastIdx ].paymentType == 'L017_TRANSFER' }">
																					<c:forEach items="${ transferStatusDDL }" var="transfer" varStatus="transferIdx">
																						<form:checkbox id="cbx_transfer_${transferIdx.index}" path="paymentList[${ lastIdx }].paymentStatus" value="${ transfer.lookupKey }" label="${ transfer.lookupValue }" />
																						<br>
																					</c:forEach>
																				</c:if>
																				<c:if test="${ poForm.paymentList[ lastIdx ].paymentType == 'L017_GIRO' }">
																					<c:forEach items="${ giroStatusDDL }" var="giro" varStatus="giroIdx">
																						<form:checkbox id="cbx_giro_${giroIdx.index}" path="paymentList[${ lastIdx }].paymentStatus" value="${ giro.lookupKey }" label="${ giro.lookupValue }" />
																						<br>
																					</c:forEach>
																				</c:if> 
																			</div>
																		</div>
																	</div>
																</div>
															</div>
														</div>
													</div>
												</div>
												<div class="row">
													<div class="col-md-7 col-offset-md-5">
														<div class="btn-toolbar">
															<button id="cancelButton" type="button" class="btn btn-primary pull-right">Cancel</button>
															<button id="submitButton" type="submit" class="btn btn-primary pull-right">Submit</button>
														</div>
													</div>
												</div>
											</div>
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
