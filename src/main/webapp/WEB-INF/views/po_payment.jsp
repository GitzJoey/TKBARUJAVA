<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/WEB-INF/views/include/headtag.jsp"></jsp:include>
<script>
$(document).ready(function() {
						var ctxpath = "${ pageContext.request.contextPath }";

						$('[id^="paymentDate"]').datetimepicker({
							format : "DD-MM-YYYY"
						});

						$('.paymentDate').on('dp.change dp.show',function(e) {
							$('#poForm').formValidation('revalidateField', 'paymentDate');
						});

						$('[id^="effectiveDate"]').datetimepicker({
							format : "DD-MM-YYYY"
						});

						$('.effectiveDate').on('dp.change dp.show',function(e) {
							$('#poForm').formValidation('revalidateField', 'effectiveDate');
						});

						$('#addNew, #editTableSelection').click(function() {
							var id = "";
							var button = $(this).attr('id');

							$('input[type="checkbox"][id^="cbx_"]').each(function(index, item) {
								if ($(item).prop('checked')) 
									{
										id = $(item).attr("value");
									}
								});

								if (id == "") 
									{
										jsAlert("Please select at least 1 po");
										return false;
									} else {
										if (button == 'addNew') {
											$('#addNew').attr("href",ctxpath+ "/po/newpayment/"+ id);
										} else if (button == 'editTableSelection') {
											$('#editTableSelection').attr("href",ctxpath+ "/po/editpayment/"+ id);
										}
									}
							});

						$('#addPayButton, #removePayButton').click(function() {
									var id = "";
									var button = $(this).attr('id');

									if (button == 'addPayButton') {
										id = $("#paymentSelect").val();
										$('#poForm').attr('action',ctxpath + "/po/addpayment/"+ id);
									} else {
										id = $(this).val();
										$('#poForm').attr('action',ctxpath + "/po/removepayment/"+ id);
									}
								});

						$('[id^="cbxBank"]').click(function() {
							var id = '';

							$('input[type="checkbox"][id^="cbxBank"]').each(function(index, item) {
								if ($(item).prop('checked')) {
									id = $(item).attr("value");
								}
							});

							if (id != "") {
								var result = confirm("Yakin untuk menambah payment ini?");
								if (result) {
									$('#submitButton').click();
								}
							}
						});

						var supplier = $("#inputSupplierId").val();

						$("#supplierTooltip").tooltip({ title : supplier });

						$("#poForm").formValidation(
							{
							locale : 'id_ID',
							framework : 'bootstrap',
							//	excluded : 'disabled',
							icon : {
								valid : 'glyphicon glyphicon-ok',
								invalid : 'glyphicon glyphicon-remove',
								validating : 'glyphicon glyphicon-refresh'
							},
							fields : {
								'paymentDate' : {
									selector : '.paymentDate',
									row : '.input-group',
									icon : false,
									validators : {
	
										notEmpty : {},
										date : {
											format : 'DD-MM-YYYY'
										}
									}
								},
								'effectiveDate' : {
									selector : '.effectiveDate',
									row : '.input-group',
									icon : false,
									validators : {
										notEmpty : {},
										date : {
											format : 'DD-MM-YYYY'
										}
									}
								},
								'totalAmount' : {
									selector : '.totalAmount',
									row : '.input-group',
									icon : false,
									validators : {
										notEmpty : {},
										greaterThan : {
	                                                   value : 1
											},
										numeric :{}
									}
								},
								'paymentSelect' : {
									row : '.row .col-md-11',
									icon : false,
									validators : {
										callback : {
											message : 'Silahkan pilih payment type!',
											callback : function(value, validator, $field) {
												var productSelect = $("#paymentSelect").val();
												if (productSelect == '') {
													return false;
												} else {
													return true;
												}
	
											}
										}
									}
								}
							}
						}).on('click','#addPayButton',function(e) {
							$('#poForm').formValidation('revalidateField','paymentSelect');
						}).on('click','#submitButton',function(e) {
							$('#poForm').formValidation('removeField','paymentSelect');
						}).on('click','#removePayButton',function(e) {
							$('#poForm').formValidation('removeField','paymentSelect');
						}).on('success.field.fv',function(e, data) {
							if (data.field == 'paymentSelect') {
								if (paymentSelect != '') {
									$('#poForm').formValidation('removeField','paymentSelect');
								}
							}
					});

});
</script>
</head>
<body>
	<div id="wrapper" class="container-fluid"><jsp:include
			page="/WEB-INF/views/include/topmenu.jsp"></jsp:include><div
			class="row">
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
					<c:when
						test="${ PAGEMODE == 'PAGEMODE_LIST' }">
						<div class="panel panel-default">
							<div class="panel-heading">
								<h1 class="panel-title">
									<span class="fa fa-calculator fa-fw fa-2x"></span>&nbsp;PO
									Payment
								</h1>
							</div>
							<div class="panel-body">
								<div class="table-responsive">
									<table id="poPaymentListTable"
										class="table table-bordered table-hover display responsive">
										<thead>
											<tr>
												<th width="5%">&nbsp;</th>
												<th width="10%">PO Code</th>
												<th width="15%">Supplier Name</th>
												<th width="15%">Date</th>
												<th width="20%">Total Price</th>
												<th width="5%">&nbsp;</th>
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
														<td></td>
														<td></td>
													</tr>
												</c:forEach>
											</c:if>
										</tbody>
									</table>
								</div>
								<a id="addNew" class="btn btn-sm btn-primary" href=""><span class="fa fa-plus fa-fw"></span>&nbsp;Add Payment</a>&nbsp;&nbsp;&nbsp;
							</div>
						</div>
					</c:when>
					<c:when
						test="${ PAGEMODE == 'PAGEMODE_EDIT' }">
						<div class="panel panel-default">
							<div class="panel-heading">
								<h1 class="panel-title">
									<span class="fa fa-edit fa-fw fa-2x"></span>&nbsp;New Payment
								</h1>
							</div>
							<div class="panel-body">
								<form:form id="poForm" role="form" class="form-horizontal" modelAttribute="poForm" action="${pageContext.request.contextPath}/po/savepayment">
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
																		<label for="inputPOCode"
																			class="col-sm-2 control-label">PO Code</label>
																		<div class="col-sm-5">
																			<form:input type="text" class="form-control" readonly="true" id="inputPOCode" name="inputPOCode" path="poCode" placeholder="Enter PO Code"></form:input>
																		</div>
																	</div>
																	<div class="form-group">
																		<label for="inputPOType"
																			class="col-sm-2 control-label">PO Type</label>
																		<div class="col-sm-8">
																			<form:hidden path="poType"></form:hidden>
																			<form:input type="text" class="form-control" readonly="true" id="inputPOType" name="inputPOType" path="poTypeLookup.lookupValue"></form:input>
																		</div>
																	</div>
																	<div class="form-group">
																		<label for="inputSupplierId"
																			class="col-sm-2 control-label">Supplier</label>
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
																		<label for="inputPoDate"
																			class="col-sm-3 control-label">PO Date</label>
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
																		<label for="inputShippingDate"
																			class="col-sm-2 control-label">Shipping Date</label>
																		<div class="col-sm-5">
																			<form:input type="text" class="form-control" readonly="true" id="shippingDate" path="shippingDate"></form:input>
																		</div>
																	</div>
																	<div class="form-group">
																		<label for="inputWarehouseId"
																			class="col-sm-2 control-label">Warehouse</label>
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
													<div class="col-md-12">
														<div class="panel panel-default">
															<div class="panel-heading">
																<h1 class="panel-title">New Transaction</h1>
															</div>
															<div class="panel-body">
																<br />
																<div class="row">
																	<div class="col-md-12">
																		<table id="itemsListTable"
																			class="table table-bordered table-hover display responsive">
																			<thead>
																				<tr>
																					<th width="40%">Product Name</th>
																					<th width="10%">Quantity</th>
																					<th width="10%">Unit</th>
																					<th width="15%">Price/Unit</th>
																					<th width="25%">Total Price</th>
																				</tr>
																			</thead>
																			<tbody>
																				<c:set var="total" value="${0}" />
																				<c:forEach items="${ poForm.itemsList }" var="iL" varStatus="iLIdx">
																					<tr>
																						<td style="vertical-align: middle;">
																							<form:hidden path="itemsList[${ iLIdx.index }].productId"></form:hidden>
																							<form:hidden path="itemsList[${ iLIdx.index }].productLookup.productName"></form:hidden>
																							<c:out value="${poForm.itemsList[ iLIdx.index ].productLookup.productName }"></c:out>
																						</td>
																						<td>
																							<form:hidden path="itemsList[${ iLIdx.index }].prodQuantity"></form:hidden>
																							<label><c:out value="${ poForm.itemsList[ iLIdx.index ].prodQuantity }"></c:out></label>
																						</td>
																						<td>
																							<form:hidden path="itemsList[${ iLIdx.index }].unitCode"></form:hidden>
																							<c:out value="${poForm.itemsList[ iLIdx.index ].unitCodeLookup.lookupValue}"></c:out>
																						</td>
																						<td>
																							<form:hidden path="itemsList[${ iLIdx.index }].prodPrice"></form:hidden>
																							<label><c:out value="${ poForm.itemsList[ iLIdx.index ].prodPrice }"></c:out></label>	
																						</td>
																						<td>
																							<c:out value="${ (iL.prodQuantity * iL.prodPrice) }"></c:out>
																						</td>
																					</tr>
																					<c:set var="total" value="${ total+ (iL.prodQuantity * iL.prodPrice)}" />
																				</c:forEach>
																			</tbody>
																		</table>
																	</div>
																</div>
																<div class="row">
																	<div class="col-md-12">
																		<table id="itemsTotalListTable"
																			class="table table-bordered table-hover display responsive">
																			<tbody>
																				<tr>
																					<td width="75%">Total</td>
																					<td width="25%"><c:out value="${ total }"></c:out></td>
																				</tr>
																			</tbody>
																		</table>
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
																		<h1 class="panel-title">New Payment</h1>
																	</div>
																	<div class="panel-body">
																		<div class="row">
																			<div class="col-md-11">
																				<select id="paymentSelect" name="paymentSelect" class="form-control">
																					<option value="">Please Select</option>
																					<c:forEach items="${ paymentTypeDDL }" var="pddl">
																						<option value="${ pddl.lookupKey }">${ pddl.lookupValue }</option>
																					</c:forEach>
																				</select>
																			</div>
																			<div class="col-md-1">
																				<button id="addPayButton" type="submit" class="btn btn-primary pull-right">
																					<span class="fa fa-plus"></span>
																				</button>
																			</div>
																		</div>
																		<br />
																		<div class="row">
																			<div class="col-md-12">
																				<table id="paymentListTable"
																					class="table table-bordered table-hover display responsive">
																					<thead>
																						<tr>
																							<th width="10%">Payment Type</th>
																							<th width="15%">Payment Date</th>
																							<th width="25%">Bank</th>
																							<th width="15%">Effective Date</th>
																							<th width="15%">Total Amount</th>
																							<th width="15%">&nbsp;</th>
																							<th width="5%">&nbsp;</th>
																						</tr>
																					</thead>
																					<tbody>
																						<c:set var="totalPay" value="${0}" />
																						<c:forEach items="${ poForm.paymentList }" var="iL" varStatus="iLIdx">
																							<tr>
																								<td style="vertical-align: middle;">
																									<form:hidden path="paymentList[${ iLIdx.index }].paymentId" />
																									<form:hidden path="paymentList[${ iLIdx.index }].paymentType" />
																									<label><c:out value="${ poForm.paymentList[ iLIdx.index ].paymentTypeLookup.lookupValue }"></c:out></label>
																								</td>
																								<td id="tdPaymentDate${ iLIdx.index }">
																									<div class="input-group">
																										<form:input type="text" class="form-control paymentDate" id="paymentDate${ iLIdx.index }" path="paymentList[${ iLIdx.index }].paymentDate" placeholder="DD-MM-YYYY"></form:input>
																									</div>
																								</td>
																								<td>
																								
																								<c:if test="${ iL.paymentType == 'L017_TRANSFER' || iL.paymentType == 'L017_GIRO'}">
																									<c:forEach items="${ bankDDL }" var="bankL" varStatus="bankIdx">
																										<c:set var="test" value="0" />
																										<c:if test="${bankL.lookupKey == poForm.paymentList[iLIdx.index].bankCode}">
																											<c:set var="test" value="1" />
																										</c:if>
																										<c:choose>
																											<c:when test="${test == 1}">
																												<form:hidden path="paymentList[${ iLIdx.index }].bankCode"/>
																												<form:checkbox id="cbxBank${ iLIdx.index }" path="paymentList[${ iLIdx.index }].bankCode" disabled="true" value="${ bankL.lookupKey }" label="${ bankL.lookupValue }" />
																												<br>
																											</c:when>
																											<c:otherwise>
																											<c:if test="${ empty poForm.paymentList[ iLIdx.index ].bankCode }">
																												<form:checkbox id="cbxBank${ iLIdx.index }" path="paymentList[${ iLIdx.index }].bankCode" value="${ bankL.lookupKey }" label="${ bankL.lookupValue }" />
																												<br>
																											</c:if>
																											</c:otherwise>
																										</c:choose>
																									</c:forEach>
																								</c:if>
																								</td>
																								<td id="tdEffectiveDate${ iLIdx.index }">
																									<div class="input-group">
																										<form:input type="text" class="form-control effectiveDate" id="effectiveDate${ iLIdx.index }" path="paymentList[${ iLIdx.index }].effectiveDate" placeholder="DD-MM-YYYY"></form:input>
																									</div>
																								</td>
																								<td>
																									<div class="input-group">
																										<form:input type="text" class="form-control text-right totalAmount" id="totalAmount${ iLIdx.index }" path="paymentList[${ iLIdx.index }].totalAmount"></form:input>
																									</div>
																								</td>
																								<td><form:checkbox id="linked${ iLIdx.index }" path="paymentList[${ iLIdx.index }].linked" label="linked" />
																								    <br>
																								    <c:if test="${ poForm.paymentList[ iLIdx.index ].paymentType == 'L017_CASH'}">
																										<c:forEach items="${ cashStatusDDL }" var="cash" varStatus="cashIdx">
																											<form:checkbox id="cbx_cash_${ cashIdx.index }" path="paymentList[${ iLIdx.index }].paymentStatus" value="${ cash.lookupKey }" label="${ cash.lookupValue }" />
																											<br>
																										</c:forEach>
																									</c:if> 
																									<c:if test="${ iL.paymentType == 'L017_TERM' }">
																										<c:forEach items="${ termStatusDDL }" var="statusL" varStatus="statusIdx">
																											<form:checkbox id="cbx_term_${statusIdx.index}" path="paymentList[${ iLIdx.index }].paymentStatus" value="${ statusL.lookupKey }" label="${ statusL.lookupValue }" />
																											<br>
																										</c:forEach>
																									</c:if>
																									<c:if test="${ iL.paymentType == 'L017_TRANSFER' }">
																										<c:forEach items="${ transferStatusDDL }" var="transfer" varStatus="transferIdx">
																											<form:checkbox id="cbx_transfer_${transferIdx.index}" path="paymentList[${ iLIdx.index }].paymentStatus" value="${ transfer.lookupKey }" label="${ transfer.lookupValue }" />
																											<br>
																										</c:forEach>
																									</c:if>
																									<c:if test="${ iL.paymentType == 'L017_GIRO' }">
																										<c:forEach items="${ giroStatusDDL }" var="giro" varStatus="giroIdx">
																											<form:checkbox id="cbx_giro_${giroIdx.index}" path="paymentList[${ iLIdx.index }].paymentStatus" value="${ giro.lookupKey }" label="${ giro.lookupValue }" />
																											<br>
																										</c:forEach>
																									</c:if> 
																								</td>
																								<td>
																									<button id="removePayButton" type="submit" class="btn btn-primary pull-right" value="${ iLIdx.index }">
																										<span class="fa fa-minus"></span>
																									</button>
																								</td>
																							</tr>
																							<c:set var="totalPay"
																								value="${ totalPay+ iL.totalAmount}" />
																						</c:forEach>
																					</tbody>
																				</table>
																			</div>
																		</div>
																		<div class="row">
																			<div class="col-md-12">
																				<table id="itemsTotalListTable"
																					class="table table-bordered table-hover display responsive">
																					<tbody>
																						<tr>
																							<td width="60%">Total</td>
																							<td width="20%"><c:out value="${ totalPay }"></c:out></td>
																							<td width="20%"></td>
																						</tr>
																					</tbody>
																				</table>
																			</div>
																		</div>
																	</div>
																</div>
															</div>
														</div>
													</div>

													<div class="col-md-7 col-offset-md-5">
														<div class="btn-toolbar">
															<button id="cancelButton" type="reset" class="btn btn-primary pull-right">Cancel</button>
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
	</div>
</body>
</html>