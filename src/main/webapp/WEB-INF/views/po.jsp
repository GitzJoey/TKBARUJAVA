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
						var tabCount = "${ loginContext.poList.size() }";
						var activetab;
						var productSelect;

						$('.poCreatedDate').datetimepicker({
							format : "DD-MM-YYYY"
						});

						$('.poCreatedDate').on('dp.change dp.show',function(e) {
							$('#poForm').formValidation('revalidateField', 'poCreatedDate');
						});
						
						$('.shippingDate').datetimepicker({ format : "DD-MM-YYYY" });

						$('.shippingDate').on('dp.change dp.show', function(e) {
							$('#poForm').formValidation('revalidateField', 'shippingDate');
						});

						$('[id^="removeProdButton"]').click(function() {
							$('#poForm').formValidation('removeField', 'productSelect');
							activetab = $(".nav-tabs li.active").attr("id");
							var id = "";
							id = $(this).val();
							$('#poForm').attr('action', ctxpath + "/po/removeitems/"+ activetab + "/" + +id);
						});

						$('button[id^="addProdButton"]').click(function() {
							activetab = $(".nav-tabs li.active").attr("id");
							productSelect = $("#productSelect"+ activetab).val();
							$('#poForm').attr('action',ctxpath + "/po/additems/"+ activetab + "/"+ productSelect);
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

						$('select[id^="inputSupplierId"]').change(function() {
							if ($('select[id^="inputSupplierId"]').val() != "") {
								$.ajax({
									url : ctxpath+ "/po/retrieve/supplier",
									data : 'supplierId='+ encodeURIComponent($('select[id^="inputSupplierId"]').val()),
									type : "GET",
									success : function(response) {
										$('[id^="supplierTooltip"]').tooltip({ title : response });
									},
									error : function(xhr, status, error) {
										alert(xhr.responseText);
									}
								});
							}
						});

						$("#poForm").formValidation({
							locale : 'id_ID',
							framework : 'bootstrap',
							button : {
								selector : '[id^="submitButton"]',
								disabled : 'disabled'
							},
							//	excluded : 'disabled',
							icon : {
								valid : 'glyphicon glyphicon-ok',
								invalid : 'glyphicon glyphicon-remove',
								validating : 'glyphicon glyphicon-refresh'
							},
							fields : {
	
								'poCode' : {
									selector : '.poCode',
									row : '.col-sm-5',
									validators : {
										notEmpty : {}
									}
								},
								'poCreatedDate' : {
									selector : '.poCreatedDate',
									row : '.col-sm-9',
									validators : {
										notEmpty : {},
										date : {
											format : 'DD-MM-YYYY'
										}
									}
								},
								'shippingDate' : {
									selector : '.shippingDate',
									row : '.col-sm-5',
									validators : {
										notEmpty : {},
										date : {
											format : 'DD-MM-YYYY'
										}
									}
								},
								'warehouseId' : {
									selector : '.warehouseId',
									row : '.col-sm-8',
									icon : false,
									validators : {
										notEmpty : {}
									}
								},
								'supplierId' : {
									selector : '.supplierId',
									row : '.col-sm-9',
									icon : false,
									validators : {
										notEmpty : {}
									}
								},
								'productSelect' : {
									selector : '.productSelect',
									row : '.row .col-md-11',
									icon : false,
									validators : {
										callback : {
											message : 'Silahkan pilih produk!',
											callback : function(value, validator, $field) {
												activetab = $(".nav-tabs li.active").attr("id");
												var productSelect = $("#productSelect"+ activetab).val();
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
						}).on('click','button[id^="addProdButton"]',function(e) {
							$('#poForm').formValidation('revalidateField','productSelect');
						}).on('success.field.fv',function(e, data) {
							if (data.field == 'productSelect') {
								activetab = $(".nav-tabs li.active").attr("id");
								var productSelect = $("#productSelect"+ activetab).val();
								if (productSelect != '') {
									$('#poForm').formValidation('removeField','productSelect');
								}
							}
					});

						$('#addTab').click(
							function() {
								activetab = $(".nav-tabs li.active").attr("id");
								$('#addTab').attr("href", ctxpath + "/po/addpoform");
							}
						);

						$("button[id^='submitButton']").click(
							function() {
								$('#poForm').formValidation('removeField','productSelect');
								activetab = $(".nav-tabs li.active").attr("id");
								$('#poForm').attr('action',ctxpath + "/po/save/" + activetab);
							}
						);

						$('[id^="cancelButton"]').click(
							function() {
								activetab = $(".nav-tabs li.active").attr("id");
								$('#poForm').attr("action",ctxpath + "/po/cancel/"+ activetab);
							}
						);

						$('#list a[href="#tab' + tabCount + '"]').tab('show');

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
						<form:form id="poForm" role="form" class="form-horizontal" modelAttribute="loginContext">
							<ul id="list" class="nav nav-tabs" role="tablist">
								<c:forEach items="${ loginContext.poList }" var="poForm" varStatus="poIdx">
									<li role="presentation" class="" id="${poIdx.index}">
										<a href="#tab${poIdx.index+1}" aria-controls="tab${poIdx.index+1}" role="tab" data-toggle="tab"><span class="glyphicon glyphicon-plus"></span>&nbsp;New
											PO ${poIdx.index+1}
										</a>
									</li>
								</c:forEach>
								<li id="last">
									<a id="addTab" class="btn btn-xs btn-default pull-right" href="#"><span class="glyphicon glyphicon-plus"></span></a>
								</li>
							</ul>
							<div class="tab-content">
								<br />
								<c:forEach items="${ loginContext.poList }" var="poForm" varStatus="poIdx">
									<div role="tabpanel" class="tab-pane" id="tab${poIdx.index+1}">
										<form:hidden path="poList[${poIdx.index}].poId" />
										<div class="row">
											<div class="col-md-12">
												<div class="panel panel-default">
													<div class="panel-body">
														<div class="row">
															<div class="col-md-7">
																<div class="form-group">
																	<label for="poCode" class="col-sm-2 control-label">PO
																		Code</label>
																	<div class="col-sm-5">
																		<form:input type="text" class="form-control poCode" id="poCode${poIdx.index}" path="poList[${poIdx.index}].poCode" placeholder="Enter PO Code" readonly="${ loginContext.poList[poIdx.index].poStatus =='L013_WA' }"></form:input>
																	</div>
																</div>
																<div class="form-group">
																	<label for="inputPOType${poIdx.index}" class="col-sm-2 control-label">PO Type</label>
																	<div class="col-sm-8">
																		<form:select class="form-control" id="inputPOType${poIdx.index}" path="poList[${poIdx.index}].poType" disabled="${ loginContext.poList[poIdx.index].poStatus =='L013_WA' }">
																			<option value="">Please Select</option>
																			<form:options items="${ poTypeDDL }" itemValue="lookupKey" itemLabel="lookupValue" />
																		</form:select>
																	</div>
																</div>
																<div class="form-group">
																	<label for="inputSupplierId${poIdx.index}" class="col-sm-2 control-label">Supplier</label>
																	<div class="col-sm-9">
																		<form:select class="form-control supplierId" id="inputSupplierId${poIdx.index}" path="poList[${poIdx.index}].supplierId" disabled="${ loginContext.poList[poIdx.index].poStatus =='L013_WA' }">
																			<option value="">Please Select</option>
																			<form:options items="${ supplierSelectionDDL }" itemValue="supplierId" itemLabel="supplierName" />
																		</form:select>
																	</div>
																	<div class="col-sm-1">
																		<button id="supplierTooltip${poIdx.index}" type="button" class="btn btn-default" data-toggle="tooltip" data-trigger="hover" data-html="true" data-placement="right" data-title="">
																			<span class="fa fa-external-link fa-fw"></span>
																		</button>
																	</div>
																</div>
															</div>
															<div class="col-md-5">
																<div class="form-group">
																	<label for="poCreatedDate" class="col-sm-3 control-label">PO Date</label>
																	<div class="col-sm-9">
																		<form:input type="text" class="form-control poCreatedDate" id="poCreatedDate${poIdx.index}" path="poList[${poIdx.index}].poCreatedDate" placeholder="Enter PO Date" readonly="${ loginContext.poList[poIdx.index].poStatus =='L013_WA' }"></form:input>
																	</div>
																</div>
																<div class="form-group">
																	<label for="inputPOStatus${poIdx.index}" class="col-sm-3 control-label">Status</label>
																	<div class="col-sm-9">
																		<form:hidden path="poList[${poIdx.index}].poStatus" />
																		<label id="inputPOStatus${poIdx.index}" class="control-label">
																			<c:out value="${ poForm.statusLookup.lookupValue }"></c:out>
																		</label>
																	</div>
																</div>
															</div>
														</div>
														<hr>
														<div class="row">
															<div class="col-md-7">
																<div class="form-group">
																	<label for="shippingDate${poIdx.index}" class="col-sm-2 control-label">Shipping Date</label>
																	<div class="col-sm-5">
																		<form:input type="text" class="form-control shippingDate" id="shippingDate${poIdx.index}" path="poList[${poIdx.index}].shippingDate" placeholder="Enter Shipping Date" readonly="${ loginContext.poList[poIdx.index].poStatus =='L013_WA' }"></form:input>
																	</div>
																</div>
																<div class="form-group">
																	<label for="inputWarehouseId${poIdx.index}" class="col-sm-2 control-label">Warehouse</label>
																	<div class="col-sm-8">
																		<form:select class="form-control warehouseId" id="inputWarehouseId${poIdx.index}" path="poList[${poIdx.index}].warehouseId" disabled="${ loginContext.poList[poIdx.index].poStatus =='L013_WA' }">
																			<option value="">Please Select</option>
																			<form:options items="${ warehouseSelectionDDL }" itemValue="warehouseId" itemLabel="warehouseName" />
																		</form:select>
																	</div>
																</div>
															</div>
															<div class="col-md-5">
																<div class="form-group">
																	<label for="inputPOStatus" class="col-sm-3 control-label"></label>
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
															<div class="col-md-11" id="product-select${poIdx.index}">
																<select id="productSelect${poIdx.index}" class="form-control productSelect" >
																	<option value="">Please Select</option>
																	<c:forEach items="${ productSelectionDDL }" var="psddl">
																		<option value="${ psddl.productId }">${ psddl.productName }</option>
																	</c:forEach>
																</select>
															</div>
															<div class="col-md-1">
															<c:if test="${ loginContext.poList[poIdx.index].poStatus =='L013_D' }">
																<button id="addProdButton${poIdx.index}" type="submit" class="btn btn-primary pull-right" >
																	<span class="fa fa-plus"></span>
																</button>
															</c:if>
															</div>
														</div>
														<br />
														<div class="row">
															<div class="col-md-12">
																<table id="itemsListTable" class="table table-bordered table-hover display responsive">
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
																		<c:forEach items="${ loginContext.poList[poIdx.index].itemsList }" var="iL" varStatus="iLIdx">
																			<tr>
																				<td style="vertical-align: middle;">
																					<form:hidden path="poList[${poIdx.index}].itemsList[${ iLIdx.index }].itemsId" />
																					<form:hidden path="poList[${poIdx.index}].itemsList[${ iLIdx.index }].productId" />
																					<label>
																						<c:out value="${ iL.productLookup.productName }"></c:out>
																					</label>
																				</td>
																				<td>
																					<form:input type="text" class="form-control text-right" id="inputItemsQuantity${poIdx.index}" path="poList[${poIdx.index}].itemsList[${ iLIdx.index }].prodQuantity" placeholder="Enter Quantity" readonly="${ loginContext.poList[poIdx.index].poStatus =='L013_WA' }"></form:input>
																				</td>
																				<td style="vertical-align: middle;"><form:hidden path="poList[${poIdx.index}].itemsList[${ iLIdx.index }].unitCode" />
																					<label>
																						<c:out value="${ iL.unitCodeLookup.lookupValue }"></c:out>
																					</label>
																				</td>
																				<td>
																					<form:input type="text" class="form-control text-right" id="inputItemsProdPrice${poIdx.index}" path="poList[${poIdx.index}].itemsList[${ iLIdx.index }].prodPrice" placeholder="Enter Price" readonly="${ loginContext.poList[poIdx.index].poStatus =='L013_WA' }"></form:input>
																				</td>
																				<td>
																				<c:if test="${ loginContext.poList[poIdx.index].poStatus =='L013_D' }">
																					<button id="removeProdButton" type="submit" value="${ iLIdx.index }" class="btn btn-primary pull-right">
																						<span class="fa fa-minus"></span>
																					</button>
																				</c:if>
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
																<table id="itemsTotalListTable" class="table table-bordered table-hover display responsive">
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
																				<form:textarea id="poRemarks${poIdx.index}" class="form-control" path="poList[${poIdx.index}].poRemarks" rows="5" readonly="${ loginContext.poList[poIdx.index].poStatus =='L013_WA' }"/>
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
												    <c:if test="${ loginContext.poList[poIdx.index].poStatus =='L013_D' }">
														<button id="cancelButton${poIdx.index}" type="submit" class="btn btn-primary pull-right">Cancel</button>
														<button id="submitButton${poIdx.index}" type="submit" class="btn btn-primary pull-right">Submit</button>
													</c:if>
													<c:if test="${ loginContext.poList[poIdx.index].poStatus =='L013_WA' }">
														<button id="cancelButton${poIdx.index}" type="submit" class="btn btn-primary pull-right">Close</button>
													</c:if>
												</div>
											</div>
										</div>
									</div>
								</c:forEach>
							</div>
						</form:form>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
