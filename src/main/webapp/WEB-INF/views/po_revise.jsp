<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/WEB-INF/views/include/headtag.jsp"></jsp:include>
<script>
	$(document).ready(
			function() {
				var ctxpath = "${ pageContext.request.contextPath }";
				$('#editTableSelection, #deleteTableSelection').click(
						function() {
							var id = "";
							var button = $(this).attr('id');

							$('input[type="checkbox"][id^="cbx_"]').each(
									function(index, item) {
										if ($(item).prop('checked')) {
											id = $(item).attr("value");
										}
									});
							if (id == "") {
								jsAlert("Please select at least 1 po");
								return false;
							} else {
								if (button == 'editTableSelection') {
									$('#editTableSelection').attr("href",
											ctxpath + "/po/revise/" + id);
								} else {
									$('#deleteTableSelection').attr("href",
											ctxpath + "/po/delete/" + id);
								}
							}
						});

				$('#addProdButton, #removeProdButton').click(
						function() {
							var id = "";
							var button = $(this).attr('id');

							if (button == 'addProdButton') {
								id = $('#productSelect').val();
								$('#reviseForm').attr('action',
										ctxpath + "/po/additems/" + id);
							} else {
								id = $(this).val();
								$('#reviseForm').attr('action',
										ctxpath + "/po/removeitems/" + id);
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
					<span class="fa fa-truck fa-fw"></span>&nbsp;Purchase Order Revise
				</h1>

				<c:choose>
					<c:when
						test="${PAGEMODE == 'PAGEMODE_PAGELOAD' || PAGEMODE == 'PAGEMODE_LIST'}">
						<div class="panel panel-default">
							<div class="panel-heading">
								<h1 class="panel-title">
									<span class="fa fa-smile-o fa-fw fa-2x"></span>PO Revise List
								</h1>
							</div>
							<div class="panel-body">
								<div class="table-responsive">
									<table class="table table-bordered table-hover">
										<thead>
											<tr>
												<th width="5%">&nbsp;</th>
												<th width="20%">PO Code</th>
												<th width="20%">PO Date</th>
												<th width="20%">Supplier</th>
											</tr>
										</thead>
										<tbody>
											<c:if test="${not empty reviseList}">
												<c:forEach items="${ reviseList }" var="i"
													varStatus="status">
													<tr>
														<td align="center"><input
															id="cbx_<c:out value="${ i.poId }"/>" type="checkbox"
															value="<c:out value="${ i.poId }"/>" /></td>
														<td><c:out value="${ i.poCode }"></c:out></td>
														<td><c:out value="${ i.poCreatedDate }"></c:out></td>
														<td><c:out value="${ i.supplierLookup.supplierName }"></c:out>
														</td>
													</tr>
												</c:forEach>
											</c:if>
										</tbody>
									</table>
								</div>
								<a id="editTableSelection" class="btn btn-sm btn-primary"
									href=""><span class="fa fa-edit fa-fw"></span>&nbsp;Revise</a>
							</div>
						</div>
					</c:when>
					<c:when test="${PAGEMODE == 'PAGEMODE_EDIT'}">
						<div class="panel panel-default">
							<div class="panel-heading">
								<h1 class="panel-title">
									<c:choose>
										<c:when test="${PAGEMODE == 'PAGEMODE_EDIT'}">
											<span class="fa fa-edit fa-fw fa-2x"></span>&nbsp;Revise PO
										</c:when>

									</c:choose>
								</h1>
							</div>
							<div class="panel-body">
								<form:form id="reviseForm" role="form" class="form-horizontal"
									modelAttribute="reviseForm"
									action="${pageContext.request.contextPath}/po/saverevise">
									<div class="tab-content">
										<div role="tabpanel" class="tab-pane active">
											<br />
											<form:hidden path="poId" />
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
																			<form:input type="text" class="form-control"
																				readonly="true" id="inputPOCode" name="inputPOCode"
																				path="poCode" placeholder="Enter PO Code"></form:input>
																		</div>
																	</div>
																	<div class="form-group">
																		<label for="inputPOType"
																			class="col-sm-2 control-label">PO Type</label>
																		<div class="col-sm-8"></div>
																	</div>
																	<div class="form-group">
																		<label for="inputSupplierId"
																			class="col-sm-2 control-label">Supplier</label>
																		<div class="col-sm-9">
																			<form:hidden path="supplierId" />
																			<form:input type="text" class="form-control"
																				path="supplierLookup.supplierName" readonly="true" />
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
																		<label for="inputPoDate"
																			class="col-sm-3 control-label">PO Date</label>
																		<div class="col-sm-9">
																			<form:input type="text" class="form-control"
																				readonly="true" id="poCreatedDate"
																				name="poCreatedDate" path="poCreatedDate"
																				placeholder="Enter PO Date"></form:input>
																		</div>
																	</div>
																	<div class="form-group">
																		<label for="inputPOStatus"
																			class="col-sm-3 control-label">Status</label>
																		<div class="col-sm-9">
																			<form:hidden path="poStatus"></form:hidden>
																				<label id="inputPOStatus" class="control-label"><c:out
																				value="${ reviseForm.statusLookup.lookupValue }"></c:out></label>
																		   
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
																				readonly="true" id="shippingDate"
																				name="shippingDate" path="shippingDate"
																				placeholder="Enter Shipping Date"></form:input>
																		</div>
																	</div>
																	<div class="form-group">
																		<label for="inputWarehouseId"
																			class="col-sm-2 control-label">Warehouse</label>
																		<div class="col-sm-8">

																			<form:hidden path="warehouseId" />
																			<form:input type="text" class="form-control"
																				path="warehouseLookup.warehouseName" readonly="true" />
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
															<div class="row">
																<div class="col-md-11">
																	<select id="productSelect" class="form-control">
																		<option value="">Please Select</option>
																		<c:forEach items="${ productSelectionDDL }"
																			var="psddl">
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
																			<c:forEach items="${ reviseForm.itemsList }" var="iL"
																				varStatus="iLIdx">
																				<tr>
																					<td style="vertical-align: middle;"><form:hidden
																							path="itemsList[${ iLIdx.index }].itemsId" /> <form:hidden
																							path="itemsList[${ iLIdx.index }].productId" />
																						<c:out value="${iL.productLookup.productName }"></c:out></td>
																					<td><form:input type="text"
																							class="form-control text-right"
																							id="inputItemsQuantity" name="inputItemsQuantity"
																							path="itemsList[${ iLIdx.index }].prodQuantity"
																							placeholder="Enter Quantity"></form:input></td>
																					<td></td>
																					<td><form:input type="text"
																							class="form-control text-right"
																							id="inputItemsProdPrice"
																							name="inputItemsProdPrice"
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
