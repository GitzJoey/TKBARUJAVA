<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
	<jsp:include page="/WEB-INF/views/include/headtag.jsp"></jsp:include>
	<script>
		$(document).ready(function() {
			var ctxpath = "${ pageContext.request.contextPath }";
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
					<span class="fa fa-cart-arrow-down fa-fw"></span>&nbsp;Sales Order
				</h1>
				
				<c:choose>
					<c:when test="${PAGEMODE == 'PAGEMODE_LIST'}">
						<div class="panel panel-default">
							<div class="panel-heading">
								<h1 class="panel-title">
									<span class="fa fa-code-fork fa-fw fa-2x"></span>&nbsp;Sales Payment List
								</h1>
							</div>					
							<div class="panel-body">
								<div class="table-responsive">
									<table class="table table-bordered table-hover">
										<thead>
											<tr>
												<th width="5%">&nbsp;</th>
												<th width="20%">Sales Code</th>
												<th width="20%">Sales Date</th>
												<th width="20%">Customer</th>
											</tr>
										</thead>
										<tbody>
											<c:if test="${not empty paymentSalesList}">
												<c:forEach items="${ paymentSalesList }" var="i" varStatus="status">
													<tr>
														<td align="center">
															<input id="cbx_<c:out value="${ i.salesId }"/>" type="checkbox" value="<c:out value="${ i.salesId }"/>" />
														</td>
														<td><c:out value="${ i.salesCode }"></c:out></td>
														<td><c:out value="${ i.salesCreatedDate }"></c:out></td>
														<td><c:out value="${ i.customerLookup.customerName }"></c:out>
														</td>
													</tr>
												</c:forEach>
											</c:if>
										</tbody>
									</table>
								</div>
								<a id="addNew" class="btn btn-sm btn-primary" href=""><span class="fa fa-plus fa-fw"></span>&nbsp;Add Payment</a>								
							</div>
						</div>
					</c:when>
					<c:when test="${PAGEMODE == 'PAGEMODE_EDIT'}">						
						<div class="panel panel-default">
							<div class="panel-heading">
								<h1 class="panel-title">
									<span class="fa fa-code-fork fa-fw fa-2x"></span>&nbsp;Revise SO List
								</h1>
							</div>								
							<div class="panel-body">
								<form:form id="paymentSalesForm" role="form" class="form-horizontal" modelAttribute="paymentSalesForm" action="${pageContext.request.contextPath}/sales/savepayment">
									<div class="row">
										<div class="col-md-12">
											<div class="panel panel-default">
												<div class="panel-body">
													<div class="row">
														<div class="col-md-7">
															<div class="form-group">
																<label for="inputSalesCode" class="col-sm-2 control-label">Sales Code</label>
																<div class="col-sm-5">
																	<form:hidden path="salesId"/>
																	<form:input type="text" class="form-control" id="inputSalesCode" name="inputSalesCode" path="salesCode" placeholder="Enter Sales Code"></form:input>
																</div>										
															</div>
															<div class="form-group">
																<label for="inputSalesType" class="col-sm-2 control-label">Sales Type</label>
																<div class="col-sm-8">
																	<form:select class="form-control" path="salesType">
																		<option value="">Please Select</option>
																		<form:options items="${ soTypeDDL }" itemValue="lookupKey" itemLabel="lookupValue"/>
																	</form:select>	
																</div>										
															</div>
															<div class="form-group">
																<label for="inputCustomerId" class="col-sm-2 control-label">Customer</label>
																<div class="col-sm-9">
																	<form:hidden path="customerId"/>
																	<form:input type="text" class="form-control" id="inputCustomerId" name="inputCustomerId" path="customerLookup.customerName" placeholder="Search Customer" disabled="true"></form:input>
																</div>
																<div class="col-sm-1">
																	<button id="customerTooltip" type="button" class="btn btn-default" data-toggle="tooltip" data-trigger="hover" data-html="true" data-placement="right" data-title=""><span class="fa fa-external-link fa-fw"></span></button>
																</div>										
															</div>
														</div>
														<div class="col-md-5">
															<div class="form-group">
																<label for="inputSalesDate" class="col-sm-3 control-label">Sales Date</label>
																<div class="col-sm-9">
																	<form:input type="text" class="form-control" id="inputSalesDate" name="inputSalesDate" path="salesCreatedDate" placeholder="Enter Sales Date"></form:input>
																</div>										
															</div>
															<div class="form-group">
																<label for="inputSalesStatus" class="col-sm-3 control-label">Status</label>
																<div class="col-sm-9">
																	<label id="inputPOStatus" class="control-label"><c:out value="${ soForm.statusLookup.lookupValue }"></c:out></label>
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
																	<form:input type="text" class="form-control" id="inputShippingDate" name="inputShippingDate" path="shippingDate" placeholder="Enter Shipping Date"></form:input>
																</div>										
															</div>
														</div>
													</div>					
												</div>
											</div>
											<div class="row">
												<div class="col-md-8">
													<div class="panel panel-default">
														<div class="panel-heading">
															<h1 class="panel-title">
																New Transaction
															</h1>
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
																	<button id="addProdButton" type="submit" class="btn btn-primary pull-right"><span class="fa fa-plus"></span></button>
																</div>
															</div>
															<br/>
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
																				<th width="20%" class="text-right">Total Price</th>
																			</tr>
																		</thead>
																		<tbody>
																			<c:forEach items="${ soForm.itemsList }" var="iL" varStatus="iLIdx">
																				<tr>
																					<td style="vertical-align: middle;">
																						<form:hidden path="itemsList[${ iLIdx.index }].itemsId"/>
																						<c:out value="${ soForm.itemsList[iLIdx.index].productLookup.productName }"></c:out>
																					</td>
																					<td>
																						<form:input type="text" class="form-control text-right" id="inputItemsQuantity" name="inputItemsQuantity" path="itemsList[${ iLIdx.index }].prodQuantity" placeholder="Enter Quantity"></form:input>
																					</td>
																					<td>
																						&nbsp;
																					</td>
																					<td>
																						<form:input type="text" class="form-control text-right" id="inputItemsProdPrice" name="inputItemsProdPrice" path="itemsList[${ iLIdx.index }].prodPrice" placeholder="Enter Price"></form:input>
																					</td>
																					<td>
																						<button id="removeProdButton" type="submit" class="btn btn-primary pull-right" value="${ iLIdx.index }"><span class="fa fa-minus"></span></button>
																					</td>
																					<td class="text-right">
																						&nbsp;
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
																					Total
																				</td>
																				<td width="20%" class="text-right">
																					12344556677
																				</td>
																			</tr>
																		</tbody>
																	</table>
																</div>														
															</div>
														</div>
													</div>
												</div>
												<div class="col-md-4">
													<div class="panel panel-default">
														<ul class="list-group">
															<li class="list-group-item"></li>
															<li class="list-group-item"></li>
															<li class="list-group-item"></li>
															<li class="list-group-item"></li>
															<li class="list-group-item"></li>
															<li class="list-group-item"></li>
															<li class="list-group-item"></li>
														</ul>
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
