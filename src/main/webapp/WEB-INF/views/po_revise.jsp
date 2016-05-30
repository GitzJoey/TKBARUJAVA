<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
				window.location = (ctxpath + "/po/revise");
			});

			$('#editTableSelection').click(function() {
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
					$('#editTableSelection').attr("href",ctxpath + "/po/revise/" + id);
				}
			});

			$('#addProdButton, #removeProdButton').click(function() {
				var id = "";
				var button = $(this).attr('id');
				var poId = $('#inputHiddenPoId').val();
				
				if (button == 'addProdButton') {
					$("#productSelect").parsley().validate();
					
					if(false == $('#productSelect').parsley().isValid()) {						
						return false;					
		            } else {
						id = $('#productSelect').val();
						$('#reviseForm').attr('action', ctxpath + "/po/revise/" + poId + "/additems/" + id);
		            }
				} else {
					id = $(this).val();
					$('#reviseForm').attr('action', ctxpath + "/po/revise/" + poId + "/removeitems/" + id);
				}
			});
		
			$('#submitButton').click(function() {
				var poId = $('#inputHiddenPoId').val();

				$('#reviseForm').parsley({
				    excluded: '[id^="productSelect"]'
				}).validate();

				if(false == $('#reviseForm').parsley().isValid()) {
					return false;
	            } else if ($('#itemsListTable tbody tr').size() == 0) {
	            	jsAlert('At least 1 transaction item needed');
	            	return false;
	            } else {
					$('#reviseForm').attr('action', ctxpath + "/po/revise/" + poId + "/save");
	            }
			});

			var supplier = $("#inputSupplierId").val()
			$("#supplierTooltip").tooltip({ title : supplier });
			
			$('#reviseTableList').DataTable();

			window.Parsley.addValidator('checkprod', function (val, value) {
				var supplierId = $('#inputHiddenSupplierId').val();
				var productId = $("#productSelect").val();
				var ret = false;

				var response = $.ajax({
					url : ctxpath+ "/po/check/supplier/prod",
					data : {supplierId: supplierId, productId: productId},
					type : "GET",
					async: false					
				}).responseText;
				
				if (response == "true") { ret = true; } else { ret = false; }

				return ret;
			}, 32)
			.addMessage('en', 'checkprod', 'Selected Product is not valid with selected Supplier')
			.addMessage('id', 'checkprod', 'Produk tidak sesuai dengan Supplier terpilih');
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
					<span class="fa fa-truck fa-fw"></span>&nbsp;<spring:message code="po_revise_jsp.title" text="Revise Purchase Order"/>
				</h1>

				<c:choose>
					<c:when test="${ PAGEMODE == 'PAGEMODE_LIST' }">
						<div class="panel panel-default">
							<div class="panel-heading">
								<h1 class="panel-title">
									<span class="fa fa-smile-o fa-fw fa-2x"></span><spring:message code="po_revise_jsp.po_revise_list" text="PO Revise List"/>
								</h1>
							</div>
							<div class="panel-body">
								<table id="reviseTableList" class="table table-bordered table-hover display responsive">
									<thead>
										<tr>
											<th width="5%">&nbsp;</th>
											<th width="10%"><spring:message code="po_revise_jsp.table.revise.header.po_code" text="PO Code"/></th>
											<th width="20%"><spring:message code="po_revise_jsp.table.revise.header.po_date" text="PO Date"/></th>
											<th width="25%"><spring:message code="po_revise_jsp.table.revise.header.supplier" text="Supplier"/></th>
											<th width="20%"><spring:message code="po_revise_jsp.table.revise.header.arrival_date" text="Arrival Date"/></th>
											<th width="20%"><spring:message code="po_revise_jsp.table.revise.header.status" text="Status"/></th>
										</tr>
									</thead>
									<tbody>
										<c:if test="${ not empty reviseList }">
											<c:forEach items="${ reviseList }" var="i" varStatus="status">
												<tr>
													<td align="center"><input id="cbx_<c:out value="${ i.poId }"/>" type="checkbox" value="<c:out value="${ i.poId }"/>" /></td>
													<td><c:out value="${ i.poCode }"></c:out></td>
													<td><fmt:formatDate pattern="dd-MM-yyyy" value="${ i.poCreatedDate }" /></td>
													<td><c:out value="${ i.supplierEntity.supplierName }"></c:out></td>
													<td><fmt:formatDate pattern="dd-MM-yyyy" value="${ i.shippingDate }" /></td>
													<td><spring:message code="${ i.poStatusLookup.i18nLookupValue }" text="${ i.poStatusLookup.lookupValue }"/></td>
												</tr>
											</c:forEach>
										</c:if>
									</tbody>
								</table>
								<a id="editTableSelection" class="btn btn-sm btn-primary" href=""><span class="fa fa-edit fa-fw"></span>&nbsp;<spring:message code="po_revise_jsp.revise_button" text="Revise"/></a>
								<a id="rejectTableSelection" class="btn btn-sm btn-primary" href=""><span class="fa fa-hand-stop-o fa-fw"></span>&nbsp;<spring:message code="common.reject_button" text="Reject"/></a>
							</div>
						</div>
					</c:when>
					<c:when test="${ PAGEMODE == 'PAGEMODE_EDIT' }">
						<div class="panel panel-default">
							<div class="panel-heading">
								<h1 class="panel-title">
									<c:choose>
										<c:when test="${ PAGEMODE == 'PAGEMODE_EDIT' }">
											<span class="fa fa-edit fa-fw fa-2x"></span>&nbsp;<spring:message code="po_revise_jsp.subtitle" text="Revise PO"/>
										</c:when>
									</c:choose>
								</h1>
							</div>
							<div class="panel-body">
								<form:form id="reviseForm" role="form" class="form-horizontal" modelAttribute="reviseForm" action="${pageContext.request.contextPath}/po/saverevise">
									<div class="tab-content">
										<div role="tabpanel" class="tab-pane active">
											<br />
											<form:hidden id="inputHiddenPoId" path="poId" />
											<form:hidden path="createdBy" />
											<form:hidden path="createdDate" />
											<div class="row">
												<div class="col-md-12">
													<div class="panel panel-default">
														<div class="panel-body">
															<div class="row">
																<div class="col-md-7">
																	<div class="form-group">
																		<label for="inputPOCode" class="col-sm-2 control-label"><spring:message code="po_revise_jsp.po_code" text="PO Code"/></label>
																		<div class="col-sm-5">
																			<form:input type="text" class="form-control" readonly="true" id="inputPOCode" path="poCode" placeholder="Enter PO Code"></form:input>
																		</div>
																	</div>
																	<div class="form-group">
																		<label for="inputPOType" class="col-sm-2 control-label"><spring:message code="po_revise_jsp.po_type" text="PO Type"/></label>
																		<div class="col-sm-8">
																			<form:hidden path="poTypeLookup.lookupKey"></form:hidden>
																			<form:input type="text" class="form-control" readonly="true" id="inputPOType" path="poTypeLookup.lookupValue"></form:input>
																		</div>
																	</div>
																	<div class="form-group">
																		<label for="inputSupplierId" class="col-sm-2 control-label"><spring:message code="po_revise_jsp.supplier" text="Supplier"/></label>
																		<div class="col-sm-9">
																			<form:hidden id="inputHiddenSupplierId" path="supplierEntity.supplierId" />
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
																		<label for="inputPoDate" class="col-sm-3 control-label"><spring:message code="po_revise_jsp.po_date" text="PO Date"/></label>
																		<div class="col-sm-9">
																			<form:input type="text" class="form-control" readonly="true" id="poCreatedDate" path="poCreatedDate" placeholder="Enter PO Date"></form:input>
																		</div>
																	</div>
																	<div class="form-group">
																		<label for="inputPOStatus" class="col-sm-3 control-label"><spring:message code="po_revise_jsp.po_status" text="Status"/></label>
																		<div class="col-sm-9">
																			<form:hidden path="poStatusLookup.lookupKey"></form:hidden>
																			<label id="inputPOStatus" class="control-label"><c:out value="${ reviseForm.poStatusLookup.lookupValue }"></c:out></label>
																		</div>
																	</div>
																</div>
															</div>
															<hr>
															<div class="row">
																<div class="col-md-7">
																	<div class="form-group">
																		<label for="inputShippingDate" class="col-sm-2 control-label"><spring:message code="po_revise_jsp.shipping_date" text="Shipping Date"/></label>
																		<div class="col-sm-5">
																			<form:input type="text" class="form-control" readonly="true" id="shippingDate" path="shippingDate" placeholder="Enter Shipping Date"></form:input>
																		</div>
																	</div>
																	<div class="form-group">
																		<label for="inputWarehouseId" class="col-sm-2 control-label"><spring:message code="po_revise_jsp.warehouse" text="Warehouse"/></label>
																		<div class="col-sm-8">
																			<form:hidden path="warehouseEntity.warehouseId" />
																			<form:input type="text" class="form-control" path="warehouseEntity.warehouseName" readonly="true" />
																		</div>
																	</div>
																</div>
																<div class="col-md-5">
																	<div class="form-group">
																		<label for="inputTruckVendor" class="col-sm-2 control-label"><spring:message code="po_revise_jsp.truck_vendor" text="Truck Vendor"></spring:message></label>
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
											<c:choose>
												<c:when test="${ reviseForm.poStatusLookup.lookupKey == 'L013_WP' }">
													<c:set var="disabledProdSelect" value="disabled"/>
													<c:set var="disabledAddProdButton" value="disabled"/>
													<c:set var="readonlyInputQuantity" value="true"/>
													<c:set var="disabledUnitSelect" value="true"/>
													<c:set var="readonlyInputPrice" value="true"/>
													<c:set var="disabledRemoveProdButton" value="disabled"/>
												</c:when>
												<c:otherwise>
													<c:set var="disabledProdSelect" value=""/>
													<c:set var="disabledAddProdButton" value=""/>
													<c:set var="readonlyInputQuantity" value=""/>
													<c:set var="disabledUnitSelect" value=""/>
													<c:set var="readonlyInputPrice" value=""/>
													<c:set var="disabledRemoveProdButton" value=""/>
												</c:otherwise>
											</c:choose>
											<div class="row">
												<div class="col-md-12">
													<div class="panel panel-default">
														<div class="panel-heading">
															<h1 class="panel-title"><spring:message code="po_revise_jsp.transactions" text="Transactions"/></h1>
														</div>
														<div class="panel-body">
															<div class="row">
																<div class="col-md-11">
																	<div class="form-group" style="padding-left: 2%">
																		<c:choose>
																			<c:when test="${ disabledProdSelect == 'disabled' }">
																				<select id="productSelect" name="productSelect" class="form-control" data-parsley-required="true" data-parsley-trigger="change" disabled="${ disabledProdSelect }">
																					<option value=""><spring:message code="common.please_select" text="Please Select"/></option>
																						<c:forEach items="${ productSelectionDDL }" var="psddl">
																						<option value="${ psddl.productId }">${ psddl.productName }</option>
																					</c:forEach>
																				</select>
																			</c:when>
																			<c:otherwise>
																				<select id="productSelect" name="productSelect" class="form-control" data-parsley-required="true" data-parsley-trigger="change" data-parsley-checkprod>
																					<option value=""><spring:message code="common.please_select" text="Please Select"/></option>
																						<c:forEach items="${ productSelectionDDL }" var="psddl">
																						<option value="${ psddl.productId }">${ psddl.productName }</option>
																					</c:forEach>
																				</select>
																			</c:otherwise>
																		</c:choose>
																	</div>
																</div>
																<div class="col-md-1">
																	<button id="addProdButton" type="submit" class="btn btn-primary pull-right" ${ disabledAddProdButton }>
																		<span class="fa fa-plus"></span>
																	</button>
																</div>
															</div>
															<br />
															<div class="row">
																<div class="col-md-12">
																	<table id="itemsListTable" class="table table-bordered table-hover display responsive">
																		<thead>
																			<tr>
																				<th width="30%"><spring:message code="po_revise_jsp.table.item.header.product_name" text="Product Name"/></th>
																				<th width="15%"><spring:message code="po_revise_jsp.table.item.header.quantity" text="Quantity"/></th>
																				<th width="15%" class="text-right"><spring:message code="po_revise_jsp.table.item.header.unit" text="Unit"/></th>
																				<th width="15%" class="text-right"><spring:message code="po_revise_jsp.table.item.header.price_unit" text="Price/Base Unit"/></th>
																				<th width="5%">&nbsp;</th>
																				<th width="20%"><spring:message code="po_revise_jsp.table.item.header.total_price" text="Total Price"/></th>
																			</tr>
																		</thead>
																		<tbody>
																			<c:set var="total" value="${0}" />
																			<c:forEach items="${ reviseForm.itemsList }" var="iL" varStatus="iLIdx">
																				<tr>
																					<td style="vertical-align: middle;">
																					    <form:hidden path="itemsList[${ iLIdx.index }].itemsId" /> 
																						<form:hidden path="itemsList[${ iLIdx.index }].productEntity.productId" />
																						<form:hidden path="itemsList[${ iLIdx.index }].baseUnitCodeLookup.lookupKey" />
																						<form:hidden path="itemsList[${ iLIdx.index }].createdBy" />
																						<form:hidden path="itemsList[${ iLIdx.index }].createdDate" />
																						<c:out value="${iL.productEntity.productName }"></c:out>
																					</td>
																					<td class="center-align">
																						<div class="form-group no-margin">
																							<div class="col-sm-12">
																								<form:input type="text" class="form-control text-right no-margin" id="inputItemsQuantity${ iLIdx.index }" path="itemsList[${ iLIdx.index }].prodQuantity" placeholder="Enter Quantity" data-parsley-type="number" data-parsley-trigger="keyup" readonly="${ readonlyInputQuantity }"></form:input>
																							</div>
																						</div>
																					</td>
																					<td style="vertical-align: middle;">
																						<div class="form-group no-margin">
																							<div class="col-md-12">
																								<form:select class="form-control no-margin" path="itemsList[${ iLIdx.index }].unitCodeLookup.lookupKey" data-parsley-required="true" data-parsley-trigger="change" disabled="${ disabledUnitSelect }">
																									<option value=""><spring:message code="common.please_select"></spring:message></option>
																									<c:forEach items="${ reviseForm.itemsList[iLIdx.index].productEntity.productUnit }" var="prdUnit">
																										<form:option value="${ prdUnit.unitCodeLookup.lookupKey }"><c:out value="${ prdUnit.unitCodeLookup.lookupValue }"/></form:option>
																									</c:forEach>
																								</form:select>
																								<c:if test="${ disabledUnitSelect }">
																									<form:hidden path="itemsList[${ iLIdx.index }].unitCodeLookup.lookupKey"/>
																								</c:if>
																							</div>
																						</div>
																					</td>
																					<td style="vertical-align: middle;">
																						<div class="form-group no-margin">
																							<div class="col-sm-12">
																								<form:input type="text" class="form-control text-right no-margin" id="inputItemsProdPrice${ iLIdx.index }" path="itemsList[${ iLIdx.index }].prodPrice" placeholder="Enter Price" data-parsley-type="number" data-parsley-trigger="keyup" readonly="${ readonlyInputPrice }"></form:input>
																							</div>
																						</div>
																					</td>
																					<td style="vertical-align: middle;">
																						<button id="removeProdButton" type="submit" class="btn btn-primary pull-right" value="${ iLIdx.index }" ${ disabledRemoveProdButton }>
																							<span class="fa fa-minus"></span>
																						</button>
																					</td>
																					<td style="vertical-align: middle;" class="text-right">
																						<form:hidden path="itemsList[${ iLIdx.index }].toBaseQty"/>
																						<fmt:formatNumber type="number" pattern="##,###.00" value="${ (iL.toBaseQty * iL.prodPrice) }"></fmt:formatNumber>
																					</td>
																				</tr>
																				<c:set var="total" value="${ total+ (iL.toBaseQty * iL.prodPrice) }" />
																				<c:forEach items="${ iL.receiptList }" var="iR" varStatus="iRIdx">
																					<form:hidden path="itemsList[${ iLIdx.index }].receiptList[${ iRIdx.index }].receiptId" />
																				</c:forEach>
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
																				<td width="80%" class="text-right"><spring:message code="po_revise_jsp.total" text="Total"/></td>
																				<td width="20%" class="text-right">
																					<fmt:formatNumber type="number" pattern="##,###.00" value="${ total }"></fmt:formatNumber>
																				</td>
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
																	<h1 class="panel-title"><spring:message code="po_revise_jsp.remarks" text="Remarks"/></h1>
																</div>
																<div class="panel-body">
																	<div class="row">
																		<div class="col-md-12">
																			<div class="form-group">
																				<div class="col-sm-12">
																					<form:textarea id="poRemarks" class="form-control" path="poRemarks" rows="5" />
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
														<button id="cancelButton" type="button" class="btn btn-primary pull-right"><spring:message code="common.cancel_button" text="Cancel"/></button>
														<button id="submitButton" type="submit" class="btn btn-primary pull-right"><spring:message code="common.submit_button" text="Submit"/></button>
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
