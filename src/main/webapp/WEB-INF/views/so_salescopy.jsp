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
			
			$('#makeCopySelection').click(function() {
				var id = "";
				var button = $(this).attr('id');

				$('input[type="checkbox"][id^="cbx_"]').each(function(index, item) {
					if ($(item).prop('checked')) {
						id = $(item).attr("value");
					}
				});
				if (id == "") {
					jsAlert("Please select at least 1 copy");
					return false;
				} else {
					if (button == 'makeCopySelection') {
						$('#makeCopySelection').attr("href", ctxpath + "/sales/salescopy/" + id);
					} else {
						return false;
					}
				}
			});		
			
			$('#viewSelection').click(function() {
				var id = "";
				var button = $(this).attr('id');
				
				$('input[type="checkbox"][id^="cbx_"]').each(function(index, item) {
					if ($(item).prop('checked')) {
						id = $(item).attr("value");
					}
				});
				
				if (id == "") {
					jsAlert("Please select at least 1 copy");
					return false;
				}
				
				$('#viewSelection').attr("href", ctxpath + "/sales/salescopy/view/" + id + "/detail");	
			});		
			
			$('#submitButton').click(function() {
				var salesId = $('#inputHiddenSalesId').val();

				if ($('#itemsListTable tbody tr').size() == 0) {
					jsAlert("Please specify at least 1 product");
					return false;
				}
				
				$('#salesOrderCopyForm').parsley({
				    excluded: '[id^="productSelect"]'
				}).validate();

				if (false == $('#salesOrderCopyForm').parsley().isValid()) {
					return false;
	            } else {
					$('#salesOrderCopyForm').attr('action', ctxpath + "/sales/salescopy/" + salesId + "/save");
	            }
			});
						
			$('#searchTableSelection').click(function() {
				if ($('#searchSalesCode').val() == '') {
					jsAlert('Please input sales code');
					return false;
				}
				$('#searchTableSelection').attr("href", ctxpath + "/sales/salescopy/view/" + $('#searchSalesCode').val());	
			});
		
			$('#cancelButton').click(function() {
				if ($('#hidden_salesCode').size() != 0) {
					window.location.href = ctxpath + "/sales/salescopy/view/" + $('#hidden_salesCode').val();	
				} else if ($('#inputSalesCode').size() != 0) {
					window.location.href = ctxpath + "/sales/salescopy/view/" + $('#inputSalesCode').val(); 
				} else {
					window.location.href = ctxpath + "/sales/salescopy";
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
						$('#salesOrderCopyForm').attr('action',ctxpath + "/sales/salescopy/" + salesId + "/additems/" + id);
					}
				} else {
					id = $(this).val();
					$('#salesOrderCopyForm').attr('action',ctxpath + "/sales/salescopy/" + salesId + "/removeitems/" + id);
				}
			});
			
			$('#salesCopyTableList').DataTable();
			$('[id^="inputShippingDate"]').datetimepicker({ format:'d-m-Y', timepicker:false });
			$('[id^="inputSalesDate"]').datetimepicker({ format:'d-m-Y', timepicker:false });
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
	    			<div class="alert alert-danger alert-dismissible collapse in" role="alert">
	  					<button type="button" class="close" data-dismiss="alert">
	  						<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
	  					</button>
	  					<h4><strong>Warning!</strong></h4>
	  					<br>
	  					<c:out value="${ errorMessageText }"/>
					</div>
				</c:if>
				
				<div id="jsAlerts"></div>
				
				<h1>
					<span class="fa fa-cart-arrow-down fa-fw"></span>&nbsp;<spring:message code="so_sales_copy_jsp.title" text="Sales"/>
				</h1>

				<c:choose>
					<c:when test="${PAGEMODE == 'PAGEMODE_PAGELOAD'}">
						<div class="panel panel-default">
							<div class="panel-heading">
								<h1 class="panel-title">
									<span class="fa fa-copy fa-fw fa-2x"></span>&nbsp;<spring:message code="so_sales_copy_jsp.subtitle" text="Sales Copy"/>
								</h1>
							</div>
							<div class="panel-body">
								<br />
								<div class="row">
									<div class="col-md-4">
							    		<input id="searchSalesCode" class="form-control" type="text" name="searchSalesCode" placeholder="Enter Sales Code" >
									</div>
									<div class="col-md-8">
										<a id="searchTableSelection" class="btn btn-sm btn-primary" href=""><span class="fa fa-search fa-fw"></span>&nbsp;<spring:message code="common.search_button" text="Search"/></a>
									</div>
								</div>
								<br />
							</div>
						</div>
					</c:when>
					<c:when test="${PAGEMODE == 'PAGEMODE_LIST'}">
						<div class="panel panel-default">
							<div class="panel-heading">
								<h1 class="panel-title">
									<span class="fa fa-copy fa-fw fa-2x"></span>&nbsp;<spring:message code="so_sales_copy_jsp.subtitle" text="Sales Copy"/>
								</h1>
							</div>
							<div class="panel-body">
								<br />
								<div class="row">
									<div class="col-md-4">
								    	<input id="searchSalesCode" class="form-control" type="text" name="searchSalesCode" placeholder="Enter Sales Code" value="${ searchString }">
									</div>
									<div class="col-md-8">
										<a id="searchTableSelection" class="btn btn-sm btn-primary" href=""><span class="fa fa-search fa-fw"></span>&nbsp;<spring:message code="common.search_button" text="Search"/></a>
									</div>
								</div>
								<br />
								<table id="salesCopyTableList" class="table table-bordered table-hover display responsive">
									<thead>
										<tr>
											<th width="5%">&nbsp;</th>
											<th width="80%"><spring:message code="so_sales_copy_jsp.table.header.sales_code" text="Sales Code"/></th>
											<th width="15%"><spring:message code="so_sales_copy_jsp.table.header.sales_copy_count" text="Copy Count"/></th>
										</tr>
									</thead>
									<tbody>
										<c:if test="${ not empty SalesCopyList }">
											<c:forEach items="${ SalesCopyList }" var="i" varStatus="status">
												<tr>
													<td align="center"><input id="cbx_<c:out value="${ i.salesId }"/>" type="checkbox" value="<c:out value="${ i.salesId }"/>" /></td>
													<td><c:out value="${ i.salesCode }"></c:out>													</td>
													<td>${ i.soCopyList.size() }</td>
												</tr>
											</c:forEach>
										</c:if>
									</tbody>
								</table>
								<a id="makeCopySelection" class="btn btn-sm btn-primary" href=""><span class="fa fa-copy fa-fw"></span>&nbsp;<spring:message code="common.make_copy_button" text="Make Copy"/></a>
								<a id="viewSelection" class="btn btn-sm btn-primary" data-parent="#accordion" href="#collapseThree" ><span class="fa fa-eye fa-fw"></span>&nbsp;<spring:message code="common.view_button" text="View"/></a>
							</div>
						</div>
					</c:when>
					<c:when test="${PAGEMODE == 'PAGEMODE_VIEW'}">
						<div class="panel panel-default">
							<div class="panel-heading">
								<h1 class="panel-title">
									<span class="fa fa-copy fa-fw fa-2x"></span>&nbsp;<spring:message code="so_sales_copy_jsp.detail_copy" text="Detail Copy"/>
								</h1>
							</div>
							<div class="panel-body">
								<div class="panel-body">
									<input type="hidden" id="hidden_salesCode" value="${ salesCopyViewList.salesCode }"/>
					           		<c:forEach items="${ salesCopyViewList.soCopyList }" var="x">
					           			<div class="panel panel-default">
					           				<div class="panel-heading">
								                <h4 class="panel-title">
								                    <a data-toggle="collapse" data-parent="#accordion" href="#${ x.salesCopyId }">
									                    <c:out value="${ x.salesOrderCopyCode }"></c:out>
									                    <c:if test="${ not empty x.salesOrderCopyDescription }">
									                    	&nbsp;-&nbsp;<c:out value="${ x.salesOrderCopyDescription }"></c:out>
									                    </c:if>
								                    </a>
								                </h4>
								            </div>
								            <div id="${ x.salesCopyId }" class="panel-collapse collapse">
								                <div class="panel-body">
								                	<div role="tabpanel">
														<div class="tab-content">
								                			<div class="row">
																<div class="col-md-12">
																	<div class="panel panel-default">
																		<div class="panel-body">
																			<div class="row">
																				<div class="col-md-7">
																					<div class="form-group">
																						<label for="inputSalesOrderCopyCode" class="col-sm-3 control-label"><spring:message code="so_sales_copy_jsp.sales_order_copy_code" text="Sales Order Copy Code"/></label>
																						<label class="col-sm-4 control-label"><c:out value="${ x.salesOrderCopyCode }"></c:out></label>
																					</div>
																				</div>
																				<div class="col-md-7">
																					<div class="form-group">
																						<label for="inputSalesOrderCopyDescription" class="col-sm-3 control-label"><spring:message code="so_sales_copy_jsp.sales_order_copy_description" text="Sales Order Copy Description"/></label>
																						<label class="col-sm-4 control-label"><c:out value="${ x.salesOrderCopyDescription }"></c:out></label>
																					</div>
																				</div>
																			</div>
																		</div>
																	</div>																		
																	<div class="panel panel-default">
																		<div class="panel-body">
																			<div class="row">
																				<div class="col-md-7">
																					<div class="form-group">
																						<label for="inputSalesCode" class="col-sm-3 control-label"><spring:message code="so_sales_copy_jsp.sales_code" text="Sales Code"/></label>
																						<label class="col-sm-4 control-label"><c:out value="${ x.salesCode }"></c:out></label>
																					</div>
																				</div>
																				<div class="col-md-5">
																					<div class="form-group">
																						<label for="inputSalesDate" class="col-sm-4 control-label"><spring:message code="so_sales_copy_jsp.sales_date" text="Sales Date"/></label>
																						<label class="col-sm-8 control-label"><fmt:formatDate value="${ x.salesCreatedDate }" pattern="dd-MM-yyyy" /></label>
																					</div>
																				</div>
																				<div class="col-md-7">
																					<div class="form-group">
																						<label for="inputSalesType" class="col-sm-3 control-label"><spring:message code="so_sales_copy_jsp.sales_type" text="Sales Type"/></label>
																						<label class="col-sm-4 control-label"><spring:message code="${ x.salesTypeLookup.i18nLookupValue }" text="${ x.salesTypeLookup.lookupValue }"></spring:message></label>
																					</div>
																				</div>
																				<div class="col-md-5">
																					<div class="form-group">
																						<label for="inputSalesStatus" class="col-sm-4 control-label"><spring:message code="so_sales_copy_jsp.status" text="Status"/></label>
																						<label class="col-sm-8 control-label"><spring:message code="${ x.salesStatusLookup.i18nLookupValue }" text="${ x.salesStatusLookup.lookupValue }"></spring:message></label>
																					</div>
																				</div>
																			</div>
																			<hr>
																			<div class="row">
																				<div class="col-md-7">
																					<div class="form-group">
																						<label for="inputShippingDate" class="col-sm-3 control-label"><spring:message code="so_sales_copy_jsp.shipping_date" text="Shipping Date"/></label>
																						<label class="col-sm-4 control-label"><fmt:formatDate value="${ x.createdDate }" pattern="dd-MM-yyyy" /></label>
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
																             <h4 class="panel-title">
																             	<spring:message code="so_sales_copy_jsp.customer" text="Customer"/>
																      		</h4>
																      	</div>
																    	<div class="panel-body">
																			<div class="row">
																				<div class="col-md-7">
																					<div class="form-group">
																						<label for="inputCustomerType" class="col-sm-3 control-label"><spring:message code="so_sales_copy_jsp.customer_type" text="Customer Type"/></label>
																						<label class="col-sm-4 control-label"><spring:message code="${ x.customerTypeLookup.i18nLookupValue }" text="${ x.customerTypeLookup.lookupValue }"></spring:message></label>
																					</div>
																				</div>
																				<div class="col-md-7">
																					<div class="form-group">
																						<label for="inputCustomerName" class="col-sm-3 control-label"><spring:message code="so_sales_copy_jsp.customer_name" text="Customer Name"/></label>
																						<label class="col-sm-4 control-label">
																							<c:if test="${ x.customerTypeLookup.lookupKey == 'L022_WIN' }">
																								<c:out value="${ x.walkInCustDetail }"></c:out>
																							</c:if>
																							<c:if test="${ x.customerTypeLookup.lookupKey == 'L022_R' }">
																								<c:out value="${ x.customerEntity.customerName }"></c:out>
																							</c:if>
																						</label>
																					</div>
																				</div>
																				<div class="col-md-5">
																				
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
																             <h4 class="panel-title">
																             	<spring:message code="so_sales_copy_jsp.transactions" text="Transactions"/>
																      		</h4>
																      	</div>
																		<div class="panel-body">
																			<div class="row">
																				<div class="col-md-12">
																					<table id="searchProductResultTable" class="table table-bordered table-hover display responsive">
																						<thead>
																							<tr>
																								<th width="30%"><spring:message code="so_sales_copy_jsp.table.item.header.product_name" text="Product Name"/></th>
																								<th width="20%" class="text-right"><spring:message code="so_sales_copy_jsp.table.item.header.quantity" text="Quantity"/></th>
																								<th width="15%" class="text-right"><spring:message code="so_sales_copy_jsp.table.item.header.unit" text="Unit"/></th>
																								<th width="15%" class="text-right"><spring:message code="so_sales_copy_jsp.table.item.header.price_unit" text="Price/Base Unit"/></th>
																								<th width="20%" class="text-right"><spring:message code="so_sales_copy_jsp.table.item.header.total_price" text="Total Price"/></th>
																							</tr>
																						</thead>
																						<tbody>
																							<c:set var="total" value="${0}" />
																							<c:forEach items="${ x.itemsList }" var="y">
																								<tr>
																									<td style="vertical-align: middle;">
																										<c:out value="${ y.productEntity.productName }"/>
																									</td>
																									<td class="text-right">
																										<c:out value="${ y.prodQuantity }"></c:out>
																									</td>
																									<td class="text-right">
																										<spring:message code="${ y.unitCodeLookup.i18nLookupValue }" text="${ y.unitCodeLookup.lookupValue }"></spring:message>
																									</td>
																									<td style="vertical-align: middle;"  class="text-right">
																										<c:out value="${ y.prodPrice }"></c:out>
																									</td>
																									<td style="vertical-align: middle;"  class="text-right">																										
																										<c:set var="total" value="${ total + (y.toBaseQty * y.prodQuantity) }" />
																										<fmt:formatNumber type="number" pattern="##,###.00" value="${ (y.toBaseQty * y.prodQuantity) }"></fmt:formatNumber>
																									</td>
																								</tr>
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
																									<spring:message code="so_sales_copy_jsp.total" text="Total"/>
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
														</div>
													</div>
													<div class="row">
														<div class="col-md-12">
															<div class="panel panel-default">
																<div class="panel-heading">
																	<h1 class="panel-title"><spring:message code="so_sales_copy_jsp.remarks" text="Remarks"/></h1>
																</div>
																<div class="panel-body">
																	<br/>
																	<div class="row">
																		<div class="col-md-12">
																			<label class="control-label"><c:out value="${ i.salesRemarks }"></c:out></label>
																		</div>
																	</div>
																</div>
															</div>
														</div>
													</div>
													<div class="row">
														<div class="col-md-12">
															<div class="text-center">
																<button id="printButton" type="button" class="btn btn-primary"><span class="fa fa-print"></span>&nbsp;<spring:message code="common.print_button" text="Print"/></button>
															</div>
														</div>
													</div>
												</div>
											</div>
										</div>
									</c:forEach>
								</div>
					    		<button id="cancelButton" type="reset" class="btn btn-primary"><span class="fa fa-arrow-left"></span>&nbsp;<spring:message code="common.back_button" text="Back"/></button>								
							</div>
						</div>
					</c:when>
					<c:when test="${ PAGEMODE == 'PAGEMODE_ADD' }">
						<div class="panel panel-default">
							<div class="panel-heading">
								<h1 class="panel-title">
									<span class="fa fa-copy fa-fw fa-2x"></span>&nbsp;<spring:message code="so_sales_copy_jsp.subtitle" text="Sales Copy"/>
								</h1>
							</div>
							<div class="panel-body">
								<form:form id="salesOrderCopyForm" role="form" class="form-horizontal" modelAttribute="salesOrderCopyForm" action="${pageContext.request.contextPath}/sales/savecopy">
									<div class="row">
										<div class="col-md-12">
											<div class="panel panel-default">
												<div class="panel-body">
													<div class="row">
														<div class="col-md-7">
															<div class="form-group">
																<label for="inputSalesOrderCopyCode" class="col-sm-2 control-label"><spring:message code="so_sales_copy_jsp.sales_order_copy_code" text="Sales Order Copy Code"/></label>
																<div class="col-sm-5">
																 	<form:input type="text" class="form-control" id="inputsalesOrderCopyCode" name="inputsalesOrderCopyCode" path="salesOrderCopyCode" readonly="true"></form:input>
																</div>
															</div>
															<div class="form-group">
																<label for="inputSalesOrderCopyDescription" class="col-sm-2 control-label"><spring:message code="so_sales_copy_jsp.sales_order_copy_description" text="Sales Order Copy Description"/></label>
																<div class="col-sm-8">
																	<form:input type="text" class="form-control" id="inputsalesOrderCopyDescription" name="inputsalesOrderCopyDescription" path="salesOrderCopyDescription" placeholder="Enter Description" readonly="false"></form:input>
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
									<div class="row">
										<div class="col-md-12">
											<div class="panel panel-default">
												<div class="panel-body">
													<div class="row">
														<div class="col-md-7">
															<div class="form-group">
																<label for="inputSalesCode" class="col-sm-2 control-label"><spring:message code="so_sales_copy_jsp.sales_code" text="Sales Code"/></label>
																<div class="col-sm-5">
																	<form:hidden id="inputHiddenSalesId" path="salesOrderEntity.salesId"/>
																	<form:hidden path="createdBy"/>
																	<form:hidden path="createdDate"/>
																	<form:input type="text" class="form-control" id="inputSalesCode" name="inputSalesCode" path="salesCode" placeholder="Enter Sales Code" readonly="true"></form:input>
																</div>
															</div>
															<div class="form-group">
																<label for="inputSalesType" class="col-sm-2 control-label"><spring:message code="so_sales_copy_jsp.sales_type" text="Sales Type"/></label>
																<div class="col-sm-8">
															   		<form:select class="form-control" path="salesTypeLookup.lookupKey">
																		<option value=""><spring:message code="common.please_select" text="Please Select"/></option>
																		<c:forEach items="${ soTypeDDL }" var="i">
																			<form:option value="${ i.lookupKey }"><spring:message code="${ i.i18nLookupValue }" text="${ i.lookupValue }"></spring:message></form:option>
																		</c:forEach>
																	</form:select>
																</div>
															</div>
														</div>
														<div class="col-md-5">
															<div class="form-group">
																<label for="inputSalesDate" class="col-sm-3 control-label"><spring:message code="so_sales_copy_jsp.sales_date" text="Sales Date"/></label>
																<div class="col-sm-9">
																	<form:input type="text" class="form-control" id="inputSalesDate" name="inputSalesDate" path="salesCreatedDate" placeholder="Enter Sales Date" readonly="false"></form:input>
																</div>
															</div>
															<div class="form-group">
																<label for="inputSalesStatus" class="col-sm-3 control-label"><spring:message code="so_sales_copy_jsp.status" text="Status"/></label>
																<div class="col-sm-9">
																    <form:hidden path="salesStatusLookup.lookupKey"/>
																	<label id="inputSalesStatus" class="control-label"><spring:message code="${ salesOrderCopyForm.salesStatusLookup.i18nLookupValue }" text="${ salesOrderCopyForm.salesStatusLookup.lookupValue }"></spring:message></label>
																</div>
															</div>
														</div>
													</div>
													<hr>
													<div class="row">
														<div class="col-md-7">
															<div class="form-group">
																<label for="inputShippingDate" class="col-sm-2 control-label"><spring:message code="so_sales_copy_jsp.shipping_date" text="Shipping Date"/></label>
																<div class="col-sm-5">
																	<form:input type="text" class="form-control" id="inputShippingDate" name="inputShippingDate" path="shippingDate" placeholder="Enter Shipping Date" readonly="false"></form:input>
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
															<h1 class="panel-title"><spring:message code="so_sales_copy_jsp.customer_title" text="Customer"/></h1>
														</div>
														<div class="panel-body">
															<div class="row">
																<div class="col-md-7">
																	<div class="form-group">
																		<label for="inputCustomerId" class="col-sm-2 control-label"><spring:message code="so_sales_copy_jsp.customer" text="Customer"/></label>
																		<div class="col-sm-10">
																			<form:hidden path="customerEntity.customerId"/>
																			<form:hidden path="customerTypeLookup.lookupKey"/>
																			<c:if test="${ salesOrderCopyForm.customerTypeLookup.lookupKey == 'L022_WIN' }">
																				<form:input type="text" class="form-control" id="inputCustomerId" name="inputCustomerId" path="customerEntity.customerName" placeholder="Walk In Customer" disabled="true"></form:input>
																			</c:if>
																			<c:if test="${ salesOrderCopyForm.customerTypeLookup.lookupKey == 'L022_R' }">
																				<form:input type="text" class="form-control" id="inputCustomerId" name="inputCustomerId" path="customerEntity.customerName" placeholder="Search Customer" disabled="true"></form:input>
																			</c:if>
																		</div>
																	</div>
																	<c:if test="${ salesOrderCopyForm.customerTypeLookup.lookupKey == 'L022_WIN' }">
																		<div class="form-group">
																			<label for="inputWalkInCustomerDetail" class="col-sm-2 control-label">&nbsp;</label>
																			<div class="col-sm-10">
																				<form:textarea class="form-control" path="walkInCustDetail" rows="3"/>
																			</div>
																		</div>
																	</c:if>
																	<c:if test="${ salesOrderCopyForm.customerTypeLookup.lookupKey == 'L022_R' }">
																		<div class="form-group">
																			<label for="inputCustomerDetail" class="col-sm-2 control-label">&nbsp;</label>
																			<div class="col-sm-10">
																				<textarea class="form-control" rows="3" id="inputCustomerDetail" readonly><c:out value="${ salesOrderCopyForm.customerEntity }"/></textarea>
																			</div>
																		</div>
																	</c:if>
																</div>
																<div class="col-md-5">
																	
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
															<h1 class="panel-title"><spring:message code="so_sales_copy_jsp.transactions" text="Transactions"/></h1>
														</div>
														<div class="panel-body">
															<div class="row">
																<div class="col-md-11">
																<div class="form-group" style="padding-left: 2%">
																	<select id="productSelect" class="form-control" data-parsley-required="true" data-parsley-trigger="change">
																		<option value=""><spring:message code="common.please_select" text="Please Select"/></option>
																		<c:forEach items="${ productListDDL }" var="pddl">
																			<option value="${ pddl.productId }">${ pddl.productName }</option>
																		</c:forEach>
																		<c:if test="${ salesOrderCopyForm.salesTypeLookup.lookupKey == 'L015_SVC' }">
																			<c:forEach items="${ stocksListDDL }" var="sddl">
																				<option value="${ sddl.stocksId }">${ sddl.productEntity.productName }</option>
																			</c:forEach>
																		</c:if>
																		<c:if test="${ salesOrderCopyForm.salesTypeLookup.lookupKey == 'L015_SVC' }">
																		</c:if>
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
																				<th width="30%"><spring:message code="so_sales_copy_jsp.table.item.header.product_name" text="Product Name"/></th>
																				<th width="15%"><spring:message code="so_sales_copy_jsp.table.item.header.quantity" text="Quantity"/></th>
																				<th width="15%" class="text-right"><spring:message code="so_sales_copy_jsp.table.item.header.unit" text="Unit"/></th>
																				<th width="15%" class="text-right"><spring:message code="so_sales_copy_jsp.table.item.header.price_unit" text="Price/Base Unit"/></th>
																				<th width="5%">&nbsp;</th>
																				<th width="20%" class="text-right"><spring:message code="so_sales_copy_jsp.table.item.header.total_price" text="Total Price"/></th>
																			</tr>
																		</thead>
																		<tbody>
																		<c:set var="total" value="${0}" />
																			<c:forEach items="${ salesOrderCopyForm.itemsList }" var="iL" varStatus="iLIdx">
																				<tr>
																					<td style="vertical-align: middle;">
																						<form:hidden path="itemsList[${ iLIdx.index }].SalesOrderCopyItemsId"/>
																						<form:hidden path="itemsList[${ iLIdx.index }].productEntity.productId"/>	
																						<form:hidden path="itemsList[${ iLIdx.index }].productEntity.productName"/>	
																						<form:hidden path="itemsList[${ iLIdx.index }].baseUnitCodeLookup.lookupKey" />
																						<form:hidden path="itemsList[${ iLIdx.index }].baseUnitCodeLookup.lookupValue" />
																						<form:hidden path="itemsList[${ iLIdx.index }].toBaseValue" />
																						<form:hidden path="itemsList[${ iLIdx.index }].toBaseQty" />
																						<form:hidden path="itemsList[${ iLIdx.index }].createdBy" />
																						<form:hidden path="itemsList[${ iLIdx.index }].createdDate" />
																						<c:out value="${ salesOrderCopyForm.itemsList[iLIdx.index].productEntity.productName }"></c:out>
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
																								<form:select class="form-control no-margin" path="itemsList[${ iLIdx.index }].unitCodeLookup.lookupKey">
																									<option value=""><spring:message code="common.please_select"></spring:message></option>
																									<c:forEach items="${ salesOrderCopyForm.itemsList[iLIdx.index].productEntity.productUnit }" var="prdUnit">
																										<form:option value="${ prdUnit.unitCodeLookup.lookupKey }"><c:out value="${ prdUnit.unitCodeLookup.lookupValue }"/></form:option>
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
																					<spring:message code="so_sales_copy_jsp.total" text="Total"/>
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
															<h1 class="panel-title"><spring:message code="so_sales_copy_jsp.remarks" text="Remarks"/></h1>
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
