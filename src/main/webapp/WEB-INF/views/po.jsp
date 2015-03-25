<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/WEB-INF/views/include/headtag.jsp"></jsp:include>
<script>
	$(document)
			.ready(
					function() {
						var ctxpath = "${ pageContext.request.contextPath }";

						$('#poCreatedDate').datetimepicker({
							format : "DD-MM-YYYY"
						});
						$('#shippingDate').datetimepicker({
							format : "DD-MM-YYYY"
						});

						$('#addProdButton, #removeProdButton')
								.click(
										function() {
											var id = "";
											var button = $(this).attr('id');

											if (button == 'addProdButton') {
												id = $('#productSelect').val();
												$('#poForm')
														.attr(
																'action',
																ctxpath
																		+ "/po/additems/"
																		+ id);
											} else {
												id = $(this).val();
												$('#poForm')
														.attr(
																'action',
																ctxpath
																		+ "/po/removeitems/"
																		+ id);
											}
										});

						$('#itemsListTable').DataTable({
							"paging" : false,
							"ordering" : false,
							"info" : false,
							"searching" : false
						});

						$('#poPaymentListTable').DataTable({
							"paging" : false,
							"ordering" : false,
							"info" : false,
							"searching" : false
						});

						$('select[name="supplierId"]')
								.change(
										function() {
											if ($('select[name="supplierId"]')
													.val() != "") {
												$
														.ajax({
															url : ctxpath
																	+ "/po/retrieve/supplier",
															data : 'supplierId='
																	+ encodeURIComponent($(
																			'select[name="supplierId"]')
																			.val()),
															type : "GET",

															success : function(
																	response) {
																$(
																		'#supplierTooltip')
																		.tooltip(
																				{
																					title : response
																				});
															},
															error : function(
																	xhr,
																	status,
																	error) {
																alert(xhr.responseText);
															}
														});
											}
										});

						$('#poForm').formValidation({
							locale : 'id_ID',
							framework : 'bootstrap',
							excluded : ':disabled',
							icon : {
								valid : 'glyphicon glyphicon-ok',
								invalid : 'glyphicon glyphicon-remove',
								validating : 'glyphicon glyphicon-refresh'
							},
							fields : {
								'poCode' : {
									validators : {
										notEmpty : {}
									}
								},
								'poCreatedDate' : {
									validators : {
										//	notEmpty : {},
										date : {
											format : 'DD-MM-YYYY'
										}
									}
								},
								'shippingDate' : {
									validators : {
										//	notEmpty : {},
										date : {
											format : 'DD-MM-YYYY'
										}
									}
								},
								'warehouseId' : {
									icon : false,
									validators : {
										notEmpty : {}
									}
								},
								'supplierId' : {
									icon : false,
									validators : {
										notEmpty : {}
									}
								}
							}
						});

						$('#poCreatedDate').on('change', function() {
							$('#poCreatedDate').blur();
						});

						$('#list a[href="#addTab"]')
								.on(
										'click',
										function() { // Click event on the "Add Tab" button
											var nbrLiElem = ($('ul#list li').length) - 1; // Count how many <li> there are (minus 1 because one <li> is the "Add Tab" button)

											// Add a <li></li> line before the last-child
											// Including the complete structure: the li ID, the <a href=""></a> etc... check the Bootstrap togglable tabs structure
											$('ul#list li:last-child')
													.before(
															'<li id="li'
																	+ (nbrLiElem + 1)
																	+ '"><a href="#tab'
																	+ (nbrLiElem + 1)
																	+ '" role="tab" data-toggle="tab">Tab '
																	+ (nbrLiElem + 1)
																	+ ' <button type="button" class="btn btn-warning btn-xs" onclick="removeTab('
																	+ (nbrLiElem + 1)
																	+ ');"><span class="glyphicon glyphicon-remove"></span></button></a>');

											var newElem = $('#tab' + nbrLiElem).clone().attr('id', 'tab' + (nbrLiElem+1)).fadeIn('slow'); // create the new element via clone(), and manipulate it's ID using newNum value

											 // inputPOCode
									        newElem.find('.label_ttl').attr('for', 'ID' + newNum + '_title');
									        newElem.find('.select_ttl').attr('id', 'ID' + newNum + '_title').attr('name', 'ID' + newNum + '_title').val('');
									 
									        // First name - text
									        newElem.find('.label_fn').attr('for', 'ID' + newNum + '_first_name');
									        newElem.find('.input_fn').attr('id', 'ID' + newNum + '_first_name').attr('name', 'ID' + newNum + '_first_name').val('');
									 
									        // Last name - text
									        newElem.find('.label_ln').attr('for', 'ID' + newNum + '_last_name');
									        newElem.find('.input_ln').attr('id', 'ID' + newNum + '_last_name').attr('name', 'ID' + newNum + '_last_name').val('');
									 
									        // Color - checkbox
									        newElem.find('.label_checkboxitem').attr('for', 'ID' + newNum + '_checkboxitem');
									        newElem.find('.input_checkboxitem').attr('id', 'ID' + newNum + '_checkboxitem').attr('name', 'ID' + newNum + '_checkboxitem').val([]);
									 
									        // Skate - radio
									        newElem.find('.label_radio').attr('for', 'ID' + newNum + '_radioitem');
									        newElem.find('.input_radio').attr('id', 'ID' + newNum + '_radioitem').attr('name', 'ID' + newNum + '_radioitem').val([]);
									 
									        // Email - text
									        newElem.find('.label_email').attr('for', 'ID' + newNum + '_email_address');
									        newElem.find('.input_email').attr('id', 'ID' + newNum + '_email_address').attr('name', 'ID' + newNum + '_email_address').val('');


											// Add a <div></div> markup after the last-child of the <div class="tab-content">
											$(
													'div.tab-content div#tab'
															+ nbrLiElem)
													.after(newElem);
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
					<span class="fa fa-truck fa-fw"></span>&nbsp;Purchase Order
				</h1>

				<div class="panel panel-default">
					<div class="panel-heading">
						<h1 class="panel-title">
							<c:choose>
								<c:when test="${PAGEMODE == 'PAGEMODE_ADD'}">
									<span class="fa fa-truck fa-fw fa-2x"></span>&nbsp;New Purchase Order
								</c:when>
							</c:choose>
						</h1>
					</div>
					<div class="panel-body">
						<form:form id="poForm" role="form" class="form-horizontal"
							modelAttribute="poForm"
							action="${pageContext.request.contextPath}/po/save">
							<div role="tabpanel" class="tab-pane active">
								<ul id="list" class="nav nav-tabs" role="tablist">
									<li role="presentation" class="active"><a href="#tab1"
										aria-controls="tab1" role="tab" data-toggle="tab"><span
											class="fa fa-plus fa-fw"></span>&nbsp;New PO</a></li>
									<li id="last"><a href="#addTab"><span
											class="glyphicon glyphicon-plus"></span> Add Tab</a></li>
								</ul>
								<div class="tab-content">
									<br />
									<div role="tabpanel" class="tab-pane active" id="tab1">
									<form:hidden path="poId" />
									<div class="row">
										<div class="col-md-12">
											<div class="panel panel-default">
												<div class="panel-body">
													<div class="row">
														<div class="col-md-7">
															<div class="form-group">
																<label for="inputPOCode" class="col-sm-2 control-label">PO
																	Code</label>
																<div class="col-sm-5">
																	<form:input type="text" class="form-control"
																		id="inputPOCode" name="inputPOCode" path="poCode"
																		placeholder="Enter PO Code"></form:input>
																</div>
															</div>
															<div class="form-group">
																<label for="inputPOType" class="col-sm-2 control-label">PO
																	Type</label>
																<div class="col-sm-8"></div>
															</div>
															<div class="form-group">
																<label for="inputSupplierId"
																	class="col-sm-2 control-label">Supplier</label>
																<div class="col-sm-9">
																	<form:select class="form-control" path="supplierId">
																		<option value="">Please Select</option>
																		<form:options items="${ supplierSelectionDDL }"
																			itemValue="supplierId" itemLabel="supplierName" />
																	</form:select>
																</div>
																<div class="col-sm-1">
																	<button id="supplierTooltip" type="button"
																		class="btn btn-default" data-toggle="tooltip"
																		data-trigger="hover" data-html="true"
																		data-placement="right" data-title="">
																		<span class="fa fa-external-link fa-fw"></span>
																	</button>
																</div>
															</div>
														</div>
														<div class="col-md-5">
															<div class="form-group">
																<label for="poCreatedDate"
																	class="col-sm-3 control-label">PO Date</label>
																<div class="col-sm-9">
																	<form:input type="text" class="form-control"
																		id="poCreatedDate" name="poCreatedDate"
																		path="poCreatedDate" placeholder="Enter PO Date"></form:input>

																</div>
															</div>
															<div class="form-group">
																<label for="inputPOStatus"
																	class="col-sm-3 control-label">Status</label>
																<div class="col-sm-9">
																	<label id="inputPOStatus" class="control-label"><c:out
																			value="${ poForm.statusLookup.lookupValue }"></c:out></label>
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
																	<form:input type="text" class="form-control"
																		id="shippingDate" name="shippingDate"
																		path="shippingDate" placeholder="Enter Shipping Date"></form:input>
																</div>
															</div>
															<div class="form-group">
																<label for="inputWarehouseId"
																	class="col-sm-2 control-label">Warehouse</label>
																<div class="col-sm-8">
																	<form:select class="form-control" path="warehouseId">
																		<option value="">Please Select</option>
																		<form:options items="${ warehouseSelectionDDL }"
																			itemValue="warehouseId" itemLabel="warehouseName" />
																	</form:select>
																</div>
															</div>
														</div>
														<div class="col-md-5">
															<div class="form-group">
																<label for="inputPOStatus"
																	class="col-sm-3 control-label"></label>
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
													<div class="row">
														<div class="col-md-11">
															<select id="productSelect" class="form-control">
																<option value="">Please Select</option>
																<c:forEach items="${ productSelectionDDL }" var="psddl">
																	<option value="${ psddl.productId }">${ psddl.productName }</option>
																</c:forEach>
															</select>
														</div>
														<div class="col-md-1">
															<button id="addProdButton" type="submit"
																class="btn btn-primary pull-right">
																<span class="fa fa-plus"></span>
															</button>
														</div>
													</div>
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
																		<th width="5%">&nbsp;</th>
																		<th width="20%">Total Price</th>
																	</tr>
																</thead>
																<tbody>
																	<c:set var="total" value="${0}" />
																	<c:forEach items="${ poForm.itemsList }" var="iL"
																		varStatus="iLIdx">
																		<tr>
																			<td style="vertical-align: middle;"><form:hidden
																					path="itemsList[${ iLIdx.index }].itemsId" /> <form:hidden
																					path="itemsList[${ iLIdx.index }].productId" /> <c:out
																					value="${iL.productLookup.productName }"></c:out></td>
																			<td><form:input type="text"
																					class="form-control text-right"
																					id="inputItemsQuantity" name="inputItemsQuantity"
																					path="itemsList[${ iLIdx.index }].prodQuantity"
																					placeholder="Enter Quantity"></form:input></td>
																			<td></td>
																			<td><form:input type="text"
																					class="form-control text-right"
																					id="inputItemsProdPrice" name="inputItemsProdPrice"
																					path="itemsList[${ iLIdx.index }].prodPrice"
																					placeholder="Enter Price"></form:input></td>

																			<td>
																				<button id="removeProdButton" type="submit"
																					class="btn btn-primary pull-right"
																					value="${ iLIdx.index }">
																					<span class="fa fa-minus"></span>
																				</button>
																			</td>
																			<td><c:out
																					value="${ (iL.prodQuantity * iL.prodPrice) }"></c:out></td>
																		</tr>
																		<c:set var="total"
																			value="${ total+ (iL.prodQuantity * iL.prodPrice)}" />
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
																		<td width="85%">Total</td>
																		<td width="20%"><c:out value="${ total }"></c:out></td>
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
																			<form:textarea class="form-control" path="poRemarks"
																				rows="5" />
																		</div>
																	</div>
																</div>
															</div>
														</div>
													</div>
												</div>
											</div>
										</div>


										<div class="col-md-7 col-offset-md-5">
											<div class="btn-toolbar">
												<button id="cancelButton" type="reset"
													class="btn btn-primary pull-right">Cancel</button>
												<button id="submitButton" type="submit"
													class="btn btn-primary pull-right">Submit</button>
											</div>
										</div>
									</div>
								</div>
								</div>
							</div>
						</form:form>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
