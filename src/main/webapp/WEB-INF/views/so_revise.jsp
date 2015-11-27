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
					if (button == 'editTableSelection') {
						$('#editTableSelection').attr("href", ctxpath + "/sales/revise/" + id);
					} else {
						return false;
					}
				}
			});		
			
			$('#submitButton').click(function() {
				var salesId = $('#inputHiddenSalesId').val();

				$('#reviseSalesForm').parsley({
				    excluded: '[id^="productSelect"]'
				}).validate();

				if(false == $('#reviseSalesForm').parsley().isValid()) {
					return false;
	            } else {
					$('#reviseSalesForm').attr('action', ctxpath + "/sales/revise/" + salesId + "/save");
	            }
			});

			$('#addProdButton, #removeProdButton').click(function() {
				var id = "";
				var button = $(this).attr('id');
				var salesId = $('#inputHiddenSalesId').val();
				
				if (button == 'addProdButton') {
					id = $('#productSelect').val();
					$('#productSelect').parsley().validate();
					if(false == $('#productSelect').parsley().isValid()) {
						return false;
					} else {
						$('#reviseSalesForm').attr('action',ctxpath + "/sales/revise/" + salesId + "/additems/" + id);
					}
				} else {
					id = $(this).val();
					$('#reviseSalesForm').attr('action',ctxpath + "/sales/revise/" + salesId + "/removeitems/" + id);
				}
			});
			
		    $('#cancelButton').click(function() {
		    	window.location.href = ctxpath + "/sales/revise";
			});
		    
			$('#reviseTableList').DataTable();
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
					<span class="fa fa-cart-arrow-down fa-fw"></span>&nbsp;<spring:message code="so_revise_jsp.title" text="Revise Sales Order"/>
				</h1>

				<c:choose>
					<c:when test="${PAGEMODE == 'PAGEMODE_LIST'}">
						<div class="panel panel-default">
							<div class="panel-heading">
								<h1 class="panel-title">
									<span class="fa fa-code-fork fa-fw fa-2x"></span>&nbsp;<spring:message code="so_revise_jsp.subtitle" text="Revise SO List"/>
								</h1>
							</div>					
							<div class="panel-body">
								<table id="reviseTableList" class="table table-bordered table-hover display responsive">
									<thead>
										<tr>
											<th width="5%">&nbsp;</th>
											<th width="20%"><spring:message code="so_revise_jsp.table.revise.header.sales_code" text="Sales Code"/></th>
											<th width="20%"><spring:message code="so_revise_jsp.table.revise.header.sales_date" text="Sales Date"/></th>
											<th width="20%"><spring:message code="so_revise_jsp.table.revise.header.customer" text="Customer"/></th>
										</tr>
									</thead>
									<tbody>
										<c:if test="${ not empty reviseSalesList }">
											<c:forEach items="${ reviseSalesList }" var="i" varStatus="status">
												<tr>
													<td align="center"><input id="cbx_<c:out value="${ i.salesId }"/>" type="checkbox" value="<c:out value="${ i.salesId }"/>" /></td>
													<td><c:out value="${ i.salesCode }"></c:out></td>
													<td><fmt:formatDate pattern="dd-MM-yyyy" value="${ i.salesCreatedDate }" /></td>
													<td><c:out value="${ i.customerEntity.customerName }"></c:out></td>
												</tr>
											</c:forEach>
										</c:if>
									</tbody>
								</table>
								<a id="editTableSelection" class="btn btn-sm btn-primary" href=""><span class="fa fa-edit fa-fw"></span>&nbsp;<spring:message code="so_revise_jsp.revise_button" text="Revise"/></a>
							</div>
						</div>
					</c:when>
					<c:when test="${ PAGEMODE == 'PAGEMODE_EDIT' }">
						<div class="panel panel-default">
							<div class="panel-heading">
								<h1 class="panel-title">
									<span class="fa fa-code-fork fa-fw fa-2x"></span>&nbsp;<spring:message code="so_revise_jsp.subtitle" text="Revise Sales"/>
								</h1>
							</div>
							<div class="panel-body">
								<form:form id="reviseSalesForm" role="form" class="form-horizontal" modelAttribute="reviseSalesForm" action="${pageContext.request.contextPath}/sales/saverevise">
									<div class="row">
										<div class="col-md-12">
											<div class="panel panel-default">
												<div class="panel-body">
													<div class="row">
														<div class="col-md-7">
															<div class="form-group">
																<label for="inputSalesCode" class="col-sm-2 control-label"><spring:message code="so_revise_jsp.sales_code" text="Sales Code"/></label>
																<div class="col-sm-5">
																	<form:hidden id="inputHiddenSalesId" path="salesId"/>
																	<form:hidden path="createdBy"/>
																	<form:hidden path="createdDate"/>
																	<form:input type="text" class="form-control" id="inputSalesCode" name="inputSalesCode" path="salesCode" placeholder="Enter Sales Code" readonly="true"></form:input>
																</div>										
															</div>
															<div class="form-group">
																<label for="inputSalesType" class="col-sm-2 control-label"><spring:message code="so_revise_jsp.sales_type" text="Sales Type"/></label>
																<div class="col-sm-8">																
																 <form:hidden path="salesTypeLookup.lookupKey"/>
																 <form:input type="text" class="form-control" id="inputSalesType" name="inputSalesType" path="salesTypeLookup.lookupValue" readonly="true"></form:input>	
																</div>
															</div>
															<div class="form-group">
																<label for="inputCustomerId" class="col-sm-2 control-label"><spring:message code="so_revise_jsp.customer" text="Customer"/></label>
																<div class="col-sm-10">
																	<form:hidden path="customerEntity.customerId"/>
																	<form:input type="text" class="form-control" id="inputCustomerId" name="inputCustomerId" path="customerEntity.customerName" placeholder="Search Customer" disabled="true"></form:input>
																</div>
															</div>
															<c:if test="${ reviseSalesForm.salesTypeLookup.lookupKey == 'L015_WIN' }">
																<div class="form-group">
																	<label for="inputWalkInCustomerDetail" class="col-sm-2 control-label">&nbsp;</label>
																	<div class="col-sm-10">
																		<form:textarea class="form-control" path="walkInCustDetail" rows="3" readonly="true"/>
																	</div>
																</div>
															</c:if>
															<c:if test="${ reviseSalesForm.salesTypeLookup.lookupKey == 'L015_S' }">
																<div class="form-group">
																	<label for="inputCustomerDetail" class="col-sm-2 control-label">&nbsp;</label>
																	<div class="col-sm-10">
																		<textarea class="form-control" rows="3" id="inputCustomerDetail" readonly="readonly"><spring:message code="sales_jsp.customer_details" text="Customer Details"/></textarea>
																	</div>
																</div>
															</c:if>
														</div>
														<div class="col-md-5">
															<div class="form-group">
																<label for="inputSalesDate" class="col-sm-3 control-label"><spring:message code="so_revise_jsp.sales_date" text="Sales Date"/></label>
																<div class="col-sm-9">
																	<form:input type="text" class="form-control" id="inputSalesDate" name="inputSalesDate" path="salesCreatedDate" placeholder="Enter Sales Date" readonly="true"></form:input>
																</div>										
															</div>
															<div class="form-group">
																<label for="inputSalesStatus" class="col-sm-3 control-label"><spring:message code="so_revise_jsp.status" text="Status"/></label>
																<div class="col-sm-9">
																    <form:hidden path="salesStatusLookup.lookupKey"/>
																	<label id="inputPOStatus" class="control-label"><c:out value="${ reviseSalesForm.salesStatusLookup.lookupValue }"></c:out></label>
																</div>										
															</div>
														</div>
													</div>
													<hr>
													<div class="row">
														<div class="col-md-7">
															<div class="form-group">
																<label for="inputShippingDate" class="col-sm-2 control-label"><spring:message code="so_revise_jsp.shipping_date" text="Shipping Date"/></label>
																<div class="col-sm-5">
																	<form:input type="text" class="form-control" id="inputShippingDate" name="inputShippingDate" path="shippingDate" placeholder="Enter Shipping Date" readonly="true"></form:input>
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
															<h1 class="panel-title"><spring:message code="so_revise_jsp.transactions" text="Transactions"/></h1>
														</div>
														<div class="panel-body">
															<div class="row">
																<div class="col-md-11">
																<div class="form-group" style="padding-left: 2%">
																	<select id="productSelect" class="form-control" data-parsley-required="true" data-parsley-trigger="change">
																		<option value=""><spring:message code="common.please_select" text="Please Select"/></option>
																		<c:forEach items="${ stocksListDDL }" var="sddl">
																			<option value="${ sddl.stocksId }">${ sddl.productLookup.productName }</option>
																		</c:forEach>
																	</select>
																	</div>
																</div>
																<div class="col-md-1">
																	<button id="addProdButton" type="submit" class="btn btn-primary pull-right"><span class="fa fa-plus"></span></button>
																</div>
															</div>
															<br/>
															<div class="row">
																<div class="col-md-12">
																	<table id="itemsListTable" class="table table-bordered table-hover display responsive">
																		<thead>
																			<tr>
																				<th width="30%"><spring:message code="so_revise_jsp.table.item.header.product_name" text="Product Name"/></th>
																				<th width="15%"><spring:message code="so_revise_jsp.table.item.header.quantity" text="Quantity"/></th>
																				<th width="15%" class="text-right"><spring:message code="so_revise_jsp.table.item.header.unit" text="Unit"/></th>
																				<th width="15%" class="text-right"><spring:message code="so_revise_jsp.table.item.header.price_unit" text="Price/Base Unit"/></th>
																				<th width="5%">&nbsp;</th>
																				<th width="20%" class="text-right"><spring:message code="so_revise_jsp.table.item.header.total_price" text="Total Price"/></th>
																			</tr>
																		</thead>
																		<tbody>
																		<c:set var="total" value="${0}" />
																			<c:forEach items="${ reviseSalesForm.itemsList }" var="iL" varStatus="iLIdx">
																				<tr>
																					<td style="vertical-align: middle;">
																						<form:hidden path="itemsList[${ iLIdx.index }].itemsId"/>
																						<form:hidden path="itemsList[${ iLIdx.index }].productId"/>
																						<c:out value="${ reviseSalesForm.itemsList[iLIdx.index].productLookup.productName }"></c:out>
																					</td>
																					<td style="vertical-align:middle;">
																						<div class="form-group no-margin">
																							<div class="col-sm-12">
																								<form:input type="text" class="form-control text-right" id="inputItemsQuantity" name="inputItemsQuantity" path="itemsList[${ iLIdx.index }].prodQuantity" placeholder="Enter Quantity" data-parsley-type="number" data-parsley-trigger="keyup"></form:input>
																							</div>
																						</div>
																					</td>
																					<td style="vertical-align: middle;">
																						<div class="form-group no-margin">
																							<div class="col-md-12">
																								<form:select class="form-control no-margin" path="itemsList[${ iLIdx.index }].unitCode">
																									<option value=""><spring:message code="common.please_select"></spring:message></option>
																									<c:forEach items="${ reviseSalesForm.itemsList[iLIdx.index].productLookup.productUnit }" var="prdUnit">
																										<form:option value="${ prdUnit.unitCode }"><c:out value="${ prdUnit.unitCodeLookup.lookupValue }"/></form:option>
																									</c:forEach>
																								</form:select>
																							</div>
																						</div>
																					</td>
																					<td style="vertical-align: middle;">
																						<div class="form-group no-margin">
																							<div class="col-sm-12">
																								<form:input type="text" class="form-control text-right" id="inputItemsProdPrice" name="inputItemsProdPrice" path="itemsList[${ iLIdx.index }].prodPrice" placeholder="Enter Price" data-parsley-type="number" data-parsley-trigger="keyup"></form:input>
																							</div>
																						</div>
																					</td>
																					<td>
																						<button id="removeProdButton" type="submit" class="btn btn-primary pull-right" value="${ iLIdx.index }"><span class="fa fa-minus"></span></button>
																					</td>
																					<td style="vertical-align: middle; text-align: right;">
																						<fmt:formatNumber type="number" pattern="##,###.00" value="${ (iL.toBaseQty * iL.prodPrice) }"></fmt:formatNumber>
																					</td>
																				</tr>
																				<c:set var="total" value="${ total + (iL.toBaseQty * iL.prodPrice) }" />
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
																				<td width="80%" class="text-right">
																					<spring:message code="so_revise_jsp.total" text="Total"/>
																				</td>
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
												</div>
											</div>
											<div class="row">
												<div class="col-md-12">
													<div class="panel panel-default">
														<div class="panel-heading">
															<h1 class="panel-title"><spring:message code="so_revise_jsp.remarks" text="Remarks"/></h1>
														</div>
														<div class="panel-body">
															<div class="row">
																<div class="col-md-12">
																	<div class="form-group">
																		<div class="col-sm-12">
																			<form:textarea class="form-control" path="salesRemarks" rows="5"/>
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
													<button id="cancelButton" type="reset" class="btn btn-primary pull-right"><spring:message code="common.cancel_button" text="Cancel"/></button>
													<button id="submitButton" type="submit" class="btn btn-primary pull-right"><spring:message code="common.submit_button" text="Submit"/></button>
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
