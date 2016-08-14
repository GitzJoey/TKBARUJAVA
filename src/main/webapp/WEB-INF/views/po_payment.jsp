<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
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

			$('#cashPayButton, #transferPayButton, #giroPayButton').click(function() {
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
					} else if (button == 'giroPayButton') {
						$('#giroPayButton').attr("href", ctxpath+ "/po/payment/giro/"+ id);
					} else {
						return false;
					}
				}
			});

			var supplier = $("#inputSupplierId").val();
			$("#supplierTooltip").tooltip({ title : supplier });
			
			$('#poPaymentListTable').DataTable();
			
			$('input[id^="cbxBank_"]').click(function() {
				var id = $(this).attr('id');
				
				$('input[id^="cbxBank_"]').each(function(index, item) {
					if ($(this).attr('id') != id) { 
						$(this).prop('checked', false);	
					}
				});
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
			<div id="content" class="col-md-10">
				<c:if test="${ ERRORFLAG == 'ERRORFLAG_SHOW' }">
					<div class="alert alert-danger alert-dismissible collapse in"
						role="alert">
						<button type="button" class="close" data-dismiss="alert">
							<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
						</button>
						<h4>
							<strong>Warning!</strong>
						</h4>
						<br> <c:out value="${ errorMessageText }"/>
					</div>
				</c:if>
				
				<div id="jsAlerts"></div>
				
				<h1>
					<span class="fa fa-truck fa-fw"></span>&nbsp;<spring:message code="po_payment_jsp.title" text="Purchase Order Payment"/>
				</h1>
				
				<c:choose>
					<c:when test="${ PAGEMODE == 'PAGEMODE_LIST' }">
						<div class="panel panel-default">
							<div class="panel-heading">
								<h1 class="panel-title">
									<span class="fa fa-calculator fa-fw fa-2x"></span>&nbsp;<spring:message code="po_payment_jsp.subtitle" text="PO Payment"/>
								</h1>
							</div>
							<div class="panel-body">
								<table id="poPaymentListTable" class="table table-bordered table-hover display responsive">
									<thead>
										<tr>
											<th width="5%">&nbsp;</th>
											<th width="5%"><spring:message code="po_payment_jsp.table.payment.header.po_code" text="PO Code"/></th>
											<th width="15%"><spring:message code="po_payment_jsp.table.payment.header.supplier_name" text="Supplier Name"/></th>
											<th width="10%"><spring:message code="po_payment_jsp.table.payment.header.date" text="Date"/></th>
											<th width="10%" class="text-right"><spring:message code="po_payment_jsp.table.payment.header.total" text="Total"/></th>
											<th width="10%" class="text-right"><spring:message code="po_payment_jsp.table.payment.header.paid" text="Paid"/></th>
											<th width="10%" class="text-right"><spring:message code="po_payment_jsp.table.payment.header.outstanding" text="Outstanding"/></th>
										</tr>
									</thead>
									<tbody>
										<c:if test="${ not empty poList }">
											<c:forEach items="${ poList }" var="p" varStatus="pL">
												<tr>
													<td align="center"><input id="cbx_<c:out value="${ p.poId }"/>" type="checkbox" value="<c:out value="${ p.poId }"/>" /></td>
													<td>
														<a href="${ pageContext.request.contextPath }/po/payment/view/${ p.poId }"><c:out value="${ p.poCode }"/></a>
													</td>
													<td><c:out value="${ p.supplierEntity.supplierName }"></c:out></td>
													<td><fmt:formatDate pattern="dd-MM-yyyy" value="${ p.poCreatedDate }" /></td>
													<td class="text-right">
														<fmt:parseNumber var="intTotalAmt" integerOnly="true" type="number" value="0" />
														<c:forEach items="${ p.itemsList }" var="iL">
															<c:if test="${ not empty iL.receiptList }">
																<fmt:parseNumber var="intTotalAmt" integerOnly="true" type="number" value="${ (intTotalAmt + (iL.receiptList[0].baseNet * iL.prodPrice)) }" />
															</c:if>
														</c:forEach>
														<fmt:formatNumber type="number" pattern="##,###.00" value="${ intTotalAmt }"></fmt:formatNumber>
													</td>
													<td class="text-right">
														<fmt:parseNumber var="intTotalPaid" integerOnly="true" type="number" value="0" />
														<c:forEach items="${ p.paymentList }" var="ppL">
															<fmt:parseNumber var="intTotalPaid" integerOnly="true" type="number" value="${ (intTotalPaid + ppL.totalAmount) }" />
														</c:forEach>
														<fmt:formatNumber type="number" pattern="##,###.00" value="${ intTotalPaid }"></fmt:formatNumber>
													</td>
													<td class="text-right">
														<fmt:formatNumber type="number" pattern="##,###.00" value="${ intTotalAmt - intTotalPaid }"></fmt:formatNumber>
													</td>
												</tr>
											</c:forEach>
										</c:if>
									</tbody>
								</table>
								<a id="cashPayButton" class="btn btn-sm btn-primary" href=""><span class="fa fa-dollar fa-fw"></span>&nbsp;<spring:message code="po_payment_jsp.cash_button" text="Cash Payment"/></a>&nbsp;&nbsp;&nbsp;
								<a id="transferPayButton" class="btn btn-sm btn-primary" href=""><span class="fa fa-bank fa-fw"></span>&nbsp;<spring:message code="po_payment_jsp.transfer_button" text="Transfer Payment"/></a>&nbsp;&nbsp;&nbsp;
								<a id="giroPayButton" class="btn btn-sm btn-primary" href=""><span class="fa fa-book fa-fw"></span>&nbsp;<spring:message code="po_payment_jsp.giro_button" text="Giro Payment"/></a>&nbsp;&nbsp;&nbsp;
							</div>
						</div>
					</c:when>
					<c:when test="${ PAGEMODE == 'PAGEMODE_EDIT' }">
						<div class="panel panel-default">
							<div class="panel-heading">
								<h1 class="panel-title">
									<span class="fa fa-edit fa-fw fa-2x"></span>&nbsp;<spring:message code="po_payment_jsp.subtitle" text="New Payment"/>
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
																			<label for="inputPOCode" class="col-sm-2 control-label"><spring:message code="po_payment_jsp.po_code" text="PO Code"/></label>
																			<div class="col-sm-5">
																				<form:input type="text" class="form-control" readonly="true" id="inputPOCode" name="inputPOCode" path="poCode" placeholder="Enter PO Code"></form:input>
																			</div>
																		</div>
																		<div class="form-group">
																			<label for="inputPOType" class="col-sm-2 control-label"><spring:message code="po_payment_jsp.po_type" text="PO Type"/></label>
																			<div class="col-sm-8">
																				<form:hidden path="poTypeLookup.lookupKey"></form:hidden>
																				<form:input type="text" class="form-control" readonly="true" id="inputPOType" name="inputPOType" path="poTypeLookup.lookupValue"></form:input>
																			</div>
																		</div>
																		<div class="form-group">
																			<label for="inputSupplierId" class="col-sm-2 control-label"><spring:message code="po_payment_jsp.supplier" text="Supplier"/></label>
																			<div class="col-sm-9">
																				<form:hidden path="supplierEntity.supplierId" />
																				<form:input id="inputSupplierId" type="text" class="form-control" path="supplierEntity.supplierName" readonly="true" />
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
																			<label for="inputPoDate" class="col-sm-3 control-label"><spring:message code="po_payment_jsp.po_date" text="PO Date"/></label>
																			<div class="col-sm-9">
																				<form:input type="text" class="form-control" readonly="true" id="poCreatedDate" path="poCreatedDate"></form:input>
																			</div>
																		</div>
																		<div class="form-group">
																			<label for="inputPOStatus" class="col-sm-3 control-label"><spring:message code="po_payment_jsp.po_status" text="Status"/></label>
																			<div class="col-sm-9">
																				<form:hidden path="poStatusLookup.lookupKey"/>
																				<label id="inputPOStatus" class="control-label"><spring:message code="${ poForm.poStatusLookup.i18nLookupValue }" text="${ poForm.poStatusLookup.lookupValue }"></spring:message></label>
																			</div>
																		</div>
																	</div>
																</div>
																<hr>
																<div class="row">
																	<div class="col-md-7">
																		<div class="form-group">
																			<label for="inputShippingDate" class="col-sm-2 control-label"><spring:message code="po_payment_jsp.shipping_date" text="Shipping Date"/></label>
																			<div class="col-sm-5">
																				<form:input type="text" class="form-control" readonly="true" id="shippingDate" path="shippingDate"></form:input>
																			</div>
																		</div>
																		<div class="form-group">
																			<label for="inputWarehouseId" class="col-sm-2 control-label"><spring:message code="po_payment_jsp.warehouse" text="Warehouse"/></label>
																			<div class="col-sm-8">
																				<form:hidden path="warehouseEntity.warehouseId" />
																				<form:input type="text" class="form-control" path="warehouseEntity.warehouseName" readonly="true" />
																			</div>
																		</div>
																	</div>
																	<div class="col-md-5">
																		<div class="form-group">
																			<div class="form-group">
																				<label for="inputTruckVendor" class="col-sm-2 control-label"><spring:message code="po_payment_jsp.truck_vendor" text="Truck Vendor"></spring:message></label>
																				<div class="col-sm-8">
																					<form:hidden path="truckVendorEntity.vendorTruckId" />
																					<form:input type="text" class="form-control" path="truckVendorEntity.vendorTruckName" readonly="true" />
																				</div>
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
																<h1 class="panel-title"><spring:message code="po_payment_jsp.transactions" text="Transactions"/></h1>
															</div>
															<div class="panel-body">
																<br />
																<div class="row">
																	<div class="col-md-12">
																		<table id="itemsListTable" class="table table-bordered table-hover display responsive">
																			<thead>
																				<tr>
																					<th width="30%"><spring:message code="po_payment_jsp.table.item.header.product_name" text="Product Name"/></th>
																					<th width="30%"><spring:message code="po_payment_jsp.table.item.header.quantity" text="Quantity"/></th>
																					<th width="15%" class="text-right"><spring:message code="po_payment_jsp.table.item.header.price_unit" text="Price/Base Unit"/></th>
																					<th width="5%">&nbsp;</th>
																					<th width="20%" class="text-right"><spring:message code="po_payment_jsp.table.item.header.total_price" text="Total Price"/></th>
																				</tr>
																			</thead>
																			<tbody>
																				<c:set var="total" value="${0}" />
																				<c:forEach items="${ poForm.itemsList }" var="iL" varStatus="iLIdx">
																					<tr>
																						<td style="vertical-align: middle;">
																							<form:hidden path="itemsList[${ iLIdx.index }].itemsId"></form:hidden>
																							<form:hidden path="itemsList[${ iLIdx.index }].productEntity.productId"></form:hidden>
																							<form:hidden path="itemsList[${ iLIdx.index }].productEntity.productName"></form:hidden>
																							<c:out value="${ poForm.itemsList[ iLIdx.index ].productEntity.productName }"></c:out>
																						</td>
																						<td>
																							<label>
																								Bruto: <c:out value="${ poForm.itemsList[ iLIdx.index ].receiptList[0].bruto }"/>&nbsp;<c:out value="${ iL.baseUnitCodeLookup.lookupValue }"/>&nbsp;(<c:out value="${ iL.prodQuantity }"/>&nbsp;<c:out value="${ iL.unitCodeLookup.lookupValue }"/>)<br/>
																								Netto: <c:out value="${ poForm.itemsList[ iLIdx.index ].receiptList[0].net }"></c:out>&nbsp;<c:out value="${ iL.baseUnitCodeLookup.lookupValue }"/><br/>
																								Tare: <c:out value="${ poForm.itemsList[ iLIdx.index ].receiptList[0].tare }"></c:out>
																							</label>
																						</td>
																						<td class="text-right">
																							<form:hidden path="itemsList[${ iLIdx.index }].prodPrice"></form:hidden>
																							<label><c:out value="${ poForm.itemsList[ iLIdx.index ].prodPrice }"></c:out></label>
																						</td>
																						<td></td>
																						<td class="text-right">
																							<fmt:formatNumber type="number" pattern="##,###.00" value="${ (poForm.itemsList[ iLIdx.index ].receiptList[0].net * iL.prodPrice) }"></fmt:formatNumber>
																						</td>
																					</tr>
																					<c:set var="total" value="${ total + (poForm.itemsList[ iLIdx.index ].receiptList[0].net * iL.prodPrice) }" />
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
																					<td width="80%" class="text-right"><spring:message code="po_payment_jsp.total" text="Total"/></td>
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
																<h1 class="panel-title"><spring:message code="po_payment_jsp.remarks" text="Remarks"/></h1>
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
																<h1 class="panel-title"><spring:message code="po_payment_jsp.payment_history" text="Payment History"/></h1>
															</div>
															<div class="panel-body">
																<div class="row">
																	<div class="col-md-12">
																		<table id="paymentListTable" class="table table-bordered table-hover display responsive">
																			<thead>
																				<tr>
																					<th width="10%"><spring:message code="po_payment_jsp.table.payment.detail.header.payment_type" text="Payment Type"/></th>
																					<th width="15%"><spring:message code="po_payment_jsp.table.payment.detail.header.payment_date" text="Payment Date"/></th>
																					<th width="15%"><spring:message code="po_payment_jsp.table.payment.detail.header.bank" text="Bank"/></th>
																					<th width="15%"><spring:message code="po_payment_jsp.table.payment.detail.header.effective_date" text="Effective Date"/></th>
																					<th width="10%"><spring:message code="po_payment_jsp.table.payment.detail.header.linked" text="Linked"/></th>
																					<th width="15%"><spring:message code="po_payment_jsp.table.payment.detail.header.status" text="Status"/></th>
																					<th width="20%" class="text-right"><spring:message code="po_payment_jsp.table.payment.detail.header.total_amount" text="Total Amount"/></th>
																				</tr>
																			</thead>
																			<tbody>
																				<c:set var="totalPay" value="${ 0 }" />
																				<c:forEach items="${ poForm.paymentList }" var="iL" varStatus="ilIdx" >
																					<c:if test="${ not empty poForm.paymentList[ ilIdx.index].paymentId }">
																						<tr>
																							<td style="vertical-align: middle;">
																								<form:hidden path="paymentList[${ ilIdx.index }].paymentId" />
																								<form:hidden path="paymentList[${ ilIdx.index }].paymentTypeLookup.lookupKey" />
																								<label>
																									<c:out value="${ poForm.paymentList[ ilIdx.index ].paymentTypeLookup.lookupValue }"></c:out>
																								</label>
																							</td>
																							<td>
																								<label>
																									<form:hidden path="paymentList[${ ilIdx.index }].paymentDate"/>
																									<fmt:formatDate pattern="dd-MM-yyyy" value="${ poForm.paymentList[ ilIdx.index ].paymentDate }" />
																								</label>
																							</td>
																							<td>
																								<div class="input-group">
																									<c:if test="${ poForm.paymentList[ ilIdx.index ].paymentTypeLookup.lookupKey == 'L017_TRANSFER' || poForm.paymentList[ ilIdx.index ].paymentTypeLookup.lookupKey == 'L017_GIRO'}">
																										<c:forEach items="${ bankDDL }" var="bankL" varStatus="bankIdx">
																											<c:set var="test" value="0" />
																											<c:if test="${ bankL.lookupKey == poForm.paymentList[ ilIdx.index ].bankCodeLookup.lookupKey }">
																												<c:set var="test" value="1" />
																											</c:if>
																											<c:choose>
																												<c:when test="${test == 1}">
																													<form:hidden path="paymentList[${ ilIdx.index }].bankCodeLookup.lookupKey"/>
																													<div class="checkbox">
																														<form:checkbox id="cbxBank_${ ilIdx.index }" path="paymentList[${ ilIdx.index }].bankCodeLookup.lookupKey" disabled="true" value="${ bankL.lookupKey }" label="${ bankL.lookupValue }" />
																													</div>
																												</c:when>
																												<c:otherwise>
																												<c:if test="${ empty poForm.paymentList[ ilIdx.index ].bankCodeLookup.lookupKey }">
																													<div class="checkbox">
																														<form:checkbox id="cbxBank_${ ilIdx.index }" path="paymentList[${ ilIdx.index }].bankCodeLookup.lookupKey" value="${ bankL.lookupKey }" label="${ bankL.lookupValue }" />
																													</div>
																												</c:if>
																												</c:otherwise>
																											</c:choose>
																										</c:forEach>
																									</c:if>
																									&nbsp;
																								</div>
																							</td>
																							<td>
																								<form:hidden path="paymentList[${ ilIdx.index }].effectiveDate"/>
																								<label>
																									<fmt:formatDate pattern="dd-MM-yyyy" value="${ poForm.paymentList[ ilIdx.index ].effectiveDate }" />
																								</label>
																							</td>
																							<td>
																								<c:if test="${ not empty paymentList[ilIdx.index].isLinked }">
																									<form:hidden path="paymentList[${ ilIdx.index }].isLinked"/>
																									<div class="checkbox">
																										<form:checkbox id="linked_${ ilIdx.index }" path="paymentList[${ ilIdx.index }].isLinked" label="" disabled="true"/>
																									</div>
																								</c:if>
																							</td>
																							<td>
																							    <form:hidden path="paymentList[${ ilIdx.index }].paymentStatusLookup.lookupKey"/>
																							    <c:if test="${ poForm.paymentList[ ilIdx.index ].paymentTypeLookup.lookupKey == 'L017_CASH'}">
																									<c:forEach items="${ cashStatusDDL }" var="cash" varStatus="cashIdx">
																										<div class="checkbox">
																											<form:checkbox id="cbx_cash_${ statusIdx.index }" path="paymentList[${ ilIdx.index }].paymentStatusLookup.lookupKey" value="${ cash.lookupKey }" label="${ cash.lookupValue }" disabled="true"/>
																										</div>
																									</c:forEach>
																								</c:if> 
																								<c:if test="${ poForm.paymentList[ ilIdx.index ].paymentTypeLookup.lookupKey == 'L017_TRANSFER' }">
																									<c:forEach items="${ transferStatusDDL }" var="transfer" varStatus="transferIdx">
																										<div class="checkbox">
																											<form:checkbox id="cbx_transfer_${ transferIdx.index }" path="paymentList[${ ilIdx.index }].paymentStatusLookup.lookupKey" value="${ transfer.lookupKey }" label="${ transfer.lookupValue }" disabled="true"/>
																										</div>
																									</c:forEach>
																								</c:if>
																								<c:if test="${ poForm.paymentList[ ilIdx.index ].paymentTypeLookup.lookupKey == 'L017_GIRO' }">
																									<c:forEach items="${ giroStatusDDL }" var="giro" varStatus="giroIdx">
																										<div class="checkbox">
																											<form:checkbox id="cbx_giro_${ giroIdx.index }" path="paymentList[${ ilIdx.index }].paymentStatusLookup.lookupKey" value="${ giro.lookupKey }" label="${ giro.lookupValue }" disabled="true"/>
																										</div>
																									</c:forEach>
																								</c:if>
																							</td>
																							<td style="vertical-align: middle;" class="text-right">
																								<form:hidden path="paymentList[${ ilIdx.index }].totalAmount"/>
																								<label>
																									<fmt:formatNumber type="number" pattern="##,###.00" value="${ poForm.paymentList[ ilIdx.index ].totalAmount }" />
																								</label>
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
																					<td width="80%" class="text-right"><spring:message code="po_payment_jsp.table.payment.detail.header.total_pay" text="Total"/></td>
																					<td width="20%" class="text-right"><fmt:formatNumber type="number" pattern="##,###.00" value="${ totalPay }" /></td>
																				</tr>
																				<tr>
																					<td width="80%" class="text-right"><spring:message code="po_payment_jsp.table.payment.detail.header.total_outstanding" text="Total Outstanding"/></td>
																					<td width="20%" class="text-right"><fmt:formatNumber type="number" pattern="##,###.00" value="${ total - totalPay }" /></td>
																				</tr>
																			</tbody>
																		</table>
																	</div>
																</div>
															</div>
														</div>
													</div>
												</div>
												<c:set var="lastIdx" value="${ poForm.paymentList.size() - 1 }"/>
												<c:if test="${ ViewMode != 'true' }">
													<div class="row">
														<div class="col-md-12">
															<div class="panel panel-default">
																<div class="panel-heading">
																	<h1 class="panel-title">
																		<c:choose>
																			<c:when test="${ poForm.paymentList[lastIdx].paymentTypeLookup.lookupKey == 'L017_TRANSFER' }">
																				<spring:message code="po_payment_jsp.transfer_payment" text="Transfer Payment"/>
																			</c:when>
																			<c:when test="${ poForm.paymentList[lastIdx].paymentTypeLookup.lookupKey == 'L017_GIRO' }">
																				<spring:message code="po_payment_jsp.giro_payment" text="Giro Payment"/>
																			</c:when>
																			<c:when test="${ poForm.paymentList[lastIdx].paymentTypeLookup.lookupKey == 'L017_CASH' }">
																				<spring:message code="po_payment_jsp.cash_payment" text="Cash Payment"/>
																			</c:when>
																			<c:otherwise>
																				<spring:message code="po_payment_jsp.payment" text="Payment"/>
																			</c:otherwise>
																		</c:choose>
																	</h1>
																</div>
																<div class="panel-body">
																	<div class="row">
																		<div class="col-md-7">
																			<div class="form-group">
																				<label for="inputPaymentType" class="col-sm-3 control-label"><spring:message code="po_payment_jsp.payment_type" text="Payment Type"/></label>
																				<div class="col-sm-5">
																					<form:hidden path="paymentList[${ lastIdx }].paymentTypeLookup.lookupKey" ></form:hidden>
																					<form:input class="form-control" path="paymentList[${ lastIdx }].paymentTypeLookup.lookupValue" readonly="true"></form:input>
																				</div>
																			</div>
																		</div>
																		<div class="col-md-5">
																		</div>
																	</div>
																	<div class="row">
																		<div class="col-md-7">
																			<c:if test="${ poForm.paymentList[ lastIdx ].paymentTypeLookup.lookupKey == 'L017_TRANSFER' || poForm.paymentList[ lastIdx ].paymentTypeLookup.lookupKey == 'L017_GIRO'}">
																				<div class="form-group">
																					<label for="inputBank" class="col-sm-3 control-label"><spring:message code="po_payment_jsp.bank" text="Bank"/></label>
																					<div class="col-sm-8">
																						<c:forEach items="${ bankDDL }" var="bankL" varStatus="bankIdx">
																							<c:set var="test" value="0" />
																							<c:if test="${ bankL.lookupKey == poForm.paymentList[ lastIdx ].bankCodeLookup.lookupKey }">
																								<c:set var="test" value="1" />
																							</c:if>
																							<c:choose>
																								<c:when test="${ test == 1 }">
																									<form:hidden path="paymentList[${ lastIdx }].bankCodeLookup.lookupKey"/>
																									<div class="checkbox">
																										<form:checkbox id="cbxBank_${ bankL.lookupKey }_${ lastIdx }" path="paymentList[${ lastIdx }].bankCodeLookup.lookupKey" disabled="true" value="${ bankL.lookupKey }" label="${ bankL.lookupValue }" />
																									</div>
																								</c:when>
																								<c:otherwise>
																									<c:if test="${ empty poForm.paymentList[ lastIdx ].bankCodeLookup.lookupKey }">
																										<div class="checkbox">
																											<form:checkbox id="cbxBank_${ bankL.lookupKey }_${ lastIdx }" path="paymentList[${ lastIdx }].bankCodeLookup.lookupKey" value="${ bankL.lookupKey }" label="${ bankL.lookupValue }" />
																										</div>
																									</c:if>
																								</c:otherwise>
																							</c:choose>
																						</c:forEach>
																					</div>
																				</div>
																			</c:if>
																		</div>
																		<div class="col-md-5">
																		</div>
																	</div>
																	<div class="row">
																		<div class="col-md-7">
																			<div class="form-group">
																				<label for="inputPaymentDate" class="col-sm-3 control-label"><spring:message code="po_payment_jsp.payment_date" text="Payment Date"/></label>
																				<div class="col-sm-5">
																					<form:input type="text" class="form-control datepicker" id="inputPaymentDate" path="paymentList[${ lastIdx }].paymentDate" data-parsley-required="true" data-parsley-trigger="change"></form:input>
																				</div>
																			</div>
																		</div>
																		<div class="col-md-5">
																			<div class="form-group">
																				<label for="inputEffectiveDate" class="col-sm-3 control-label"><spring:message code="po_payment_jsp.effective_date" text="Effective Date"/></label>
																				<div class="col-sm-7">
																					<form:input id="inputEffectiveDate" type="text" class="form-control datepicker" path="paymentList[${ lastIdx }].effectiveDate" data-parsley-required="true" data-parsley-trigger="change"  />
																				</div>
																			</div>
																		</div>
																	</div>
																	<div class="row">
																		<div class="col-md-7">
																			<div class="form-group">
																				<label for="inputTotalAmount" class="col-sm-3 control-label"><spring:message code="po_payment_jsp.total_amount" text="Total Amount"/></label>
																				<div class="col-sm-9">
																					<div class="input-group">
																						<span class="input-group-addon">
																							Rp
																						</span>
																						<form:input type="text" class="form-control right-align" id="inputTotalAmount" path="paymentList[${ lastIdx }].totalAmount" data-parsley-min="1" data-parsley-trigger="keyup" onfocus="this.select()"></form:input>
																					</div>
																				</div>
																			</div>
																		</div>
																		<div class="col-md-5">
																		</div>
																	</div>
																</div>
															</div>
														</div>
													</div>
												</c:if>
												<div class="row">
													<c:choose>
														<c:when test="${ ViewMode == 'true' }">
															<div class="col-md-6 col-offset-md-6">
																<div class="btn-toolbar">
																	<button id="cancelButton" type="button" class="btn btn-primary pull-right"><spring:message code="po_payment_jsp.close_button" text="Close"/></button>
																</div>
															</div>
														</c:when>
														<c:otherwise>
															<div class="col-md-7 col-offset-md-5">
																<div class="btn-toolbar">
																	<button id="cancelButton" type="button" class="btn btn-primary pull-right"><spring:message code="common.cancel_button" text="Cancel"/></button>
																	<button id="submitButton" type="submit" class="btn btn-primary pull-right"><spring:message code="common.submit_button" text="Submit"/></button>
																</div>
															</div>
														</c:otherwise>
													</c:choose>
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
