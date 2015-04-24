<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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

				$('input[type="checkbox"][id^="cbx_"]').each(function(index, item) {
					if ($(item).prop('checked')) {
							id = $(item).attr("value");
					}
				});

				if (id == "") {
					jsAlert("Please select at least 1 po");
					return false;
				} else {
					$('#editTableSelection').attr("href",ctxpath+ "/sales/newpayment/"+ id);				
				}
			});

			$('#addPayButton, #removePayButton').click(function() {
				var id = "";
				var button = $(this).attr('id');

				if (button == 'addPayButton') {
					id = $("#paymentSelect").val();
					$('#paymentSelect').parsley().validate();
                    if(false == $('#paymentSelect').parsley().isValid()) {					
						return false;
                    } else {
						$('#paymentSalesForm').attr('action', ctxpath + "/sales/addpayment/" + id);
                    }
				} else {
					id = $(this).val();
					$('#paymentSalesForm').attr('action', ctxpath + "/sales/removepayment/" + id);
				}
			});

			$('[id^="paymentDate_"]').datetimepicker({ format:'d-m-Y', timepicker:false });

			$('.paymentDate').on('dp.change dp.show',function(e) {
				$(this).parsley().validate();
			});

			$('[id^="effectiveDate_"]').datetimepicker({ format:'d-m-Y', timepicker:false });

			$('.effectiveDate').on('dp.change dp.show',function(e) {
				$(this).parsley().validate();
			});

			$('[id^="customerTooltip"]').tooltip();
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
									<span class="fa fa-code-fork fa-fw fa-2x"></span>&nbsp;Payment SO List
								</h1>
							</div>								
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
							<a id="editTableSelection" class="btn btn-sm btn-primary" href=""><span class="fa fa-edit fa-fw"></span>&nbsp;Add Payment</a>								
						</div>
					</c:when>
					<c:when test="${PAGEMODE == 'PAGEMODE_EDIT'}">						
						<div class="panel panel-default">
							<div class="panel-heading">
								<h1 class="panel-title">
									<span class="fa fa-code-fork fa-fw fa-2x"></span>&nbsp;Payment SO List
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
																	<form:hidden path="createdBy"/>
																	<form:hidden path="createdDate"/>
																	<form:input type="text" class="form-control" id="inputSalesCode" name="inputSalesCode" path="salesCode" placeholder="Enter Sales Code" readonly="true"></form:input>
																</div>										
															</div>
															<div class="form-group">
																<label for="inputSalesType" class="col-sm-2 control-label">Sales Type</label>
																<div class="col-sm-8">
																<form:hidden path="salesType"/>
																 <form:input type="text" class="form-control" id="inputSalesType" name="inputSalesType" path="soTypeLookup.lookupValue" readonly="true"></form:input>
																</div>										
															</div>
															<div class="form-group">
																<label for="inputCustomerId" class="col-sm-2 control-label">Customer</label>
																<div class="col-sm-9">
																	<form:hidden path="customerId"/>
																	<form:input type="text" class="form-control" id="inputCustomerId" name="inputCustomerId" path="customerLookup.customerName" placeholder="Search Customer" disabled="true"></form:input>
																</div>
																<div class="col-sm-1">
																	<button id="customerTooltip" title="${ paymentSalesForm.customerLookup.customerName }" type="button" class="btn btn-default" data-toggle="tooltip" data-trigger="hover" data-html="true" data-placement="right" data-title=""><span class="fa fa-external-link fa-fw"></span></button>
																</div>										
															</div>
														</div>
														<div class="col-md-5">
															<div class="form-group">
																<label for="inputSalesDate" class="col-sm-3 control-label">Sales Date</label>
																<div class="col-sm-9">
																	<form:input type="text" class="form-control" id="inputSalesDate" name="inputSalesDate" path="salesCreatedDate" placeholder="Enter Sales Date" readonly="true"></form:input>
																</div>										
															</div>
															<div class="form-group">
																<label for="inputSalesStatus" class="col-sm-3 control-label">Status</label>
																<div class="col-sm-9">
																<form:hidden path="salesStatus"/>
																	<label id="inputPOStatus" class="control-label"><c:out value="${ paymentSalesForm.statusLookup.lookupValue }"></c:out></label>
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
																	<form:input type="text" class="form-control" id="inputShippingDate" name="inputShippingDate" path="shippingDate" placeholder="Enter Shipping Date" readonly="true"></form:input>
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
																		<c:set var="total" value="${0}" />
																			<c:forEach items="${ paymentSalesForm.itemsList }" var="iL" varStatus="iLIdx">
																				<tr>
																					<td style="vertical-align: middle;">
																						<form:hidden path="itemsList[${ iLIdx.index }].itemsId"/>
																						<form:hidden path="itemsList[${ iLIdx.index }].productId"/>
																						<c:out value="${ paymentSalesForm.itemsList[iLIdx.index].productLookup.productName }"></c:out>
																					</td>
																					<td>
																						<form:input type="text" class="form-control text-right" id="inputItemsQuantity" name="inputItemsQuantity" path="itemsList[${ iLIdx.index }].prodQuantity" placeholder="Enter Quantity" readonly="true"></form:input>
																					</td>
																					<td>
																						<form:hidden id="inputItemsUnitCode" name="inputItemsUnitCode" path="itemsList[${ iLIdx.index }].unitCode" ></form:hidden>
																						<c:out value="${iL.unitCodeLookup.lookupValue}"></c:out>
																					</td>
																					<td>
																						<form:input type="text" class="form-control text-right" id="inputItemsProdPrice" name="inputItemsProdPrice" path="itemsList[${ iLIdx.index }].prodPrice" placeholder="Enter Price" readonly="true"></form:input>
																					</td>
																					<td>
																						
																					</td>
																					<td class="text-right">
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
																				<td width="80%" class="text-right">
																					Total
																				</td>
																				<td width="20%" class="text-right">
																					<c:out value="${ total }"></c:out>
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
																			<form:textarea class="form-control" path="salesRemarks" rows="5" readonly="true"/>
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
																<div class="form-group" style="padding-left: 2%">
																	<select id="paymentSelect" name="paymentSelect" class="form-control" data-parsley-required="true" data-parsley-trigger="change">
																		<option value="">Please Select</option>
																		<c:forEach items="${ paymentTypeDDL }" var="pddl">
																			<option value="${ pddl.lookupKey }">${ pddl.lookupValue }</option>
																		</c:forEach>
																	</select>
																</div>
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
																			<c:forEach items="${ paymentSalesForm.paymentList }" var="iL" varStatus="iLIdx">
																				<tr>
																					<td style="vertical-align: middle;">
																						<form:hidden path="paymentList[${ iLIdx.index }].paymentId" />
																						<form:hidden path="paymentList[${ iLIdx.index }].paymentType" />
																						<label><c:out value="${ paymentSalesForm.paymentList[ iLIdx.index ].paymentTypeLookup.lookupValue }"></c:out></label>
																					</td>
																					<td id="tdPaymentDate${ iLIdx.index }">
																						<div class="input-group">
																							<form:input type="text" class="form-control paymentDate" id="paymentDate_${ iLIdx.index }" path="paymentList[${ iLIdx.index }].paymentDate" placeholder="DD-MM-YYYY"></form:input>
																						</div>
																					</td>
																					<td>
																					
																					<c:if test="${ iL.paymentType == 'L017_TRANSFER' || iL.paymentType == 'L017_GIRO'}">
																						<c:forEach items="${ bankDDL }" var="bankL" varStatus="bankIdx">
																							<c:set var="test" value="0" />
																							<c:if test="${bankL.lookupKey == paymentSalesForm.paymentList[iLIdx.index].bankCode}">
																								<c:set var="test" value="1" />
																							</c:if>
																							<c:choose>
																								<c:when test="${test == 1}">
																									<form:hidden path="paymentList[${ iLIdx.index }].bankCode"/>
																									<form:checkbox id="cbxBank_${ iLIdx.index }" path="paymentList[${ iLIdx.index }].bankCode" disabled="true" value="${ bankL.lookupKey }" label="${ bankL.lookupValue }" />
																									<br>
																								</c:when>
																								<c:otherwise>
																								<c:if test="${ empty paymentSalesForm.paymentList[ iLIdx.index ].bankCode }">
																									<form:checkbox id="cbxBank_${ iLIdx.index }" path="paymentList[${ iLIdx.index }].bankCode" value="${ bankL.lookupKey }" label="${ bankL.lookupValue }" />
																									<br>
																								</c:if>
																								</c:otherwise>
																							</c:choose>
																						</c:forEach>
																					</c:if>
																					</td>
																					<td id="tdEffectiveDate_${ iLIdx.index }">
																						<div class="input-group">
																							<form:input type="text" class="form-control effectiveDate" id="effectiveDate_${ iLIdx.index }" path="paymentList[${ iLIdx.index }].effectiveDate" placeholder="DD-MM-YYYY"></form:input>
																						</div>
																					</td>
																					<td>
																						<div class="form-group">
																						<div class="col-sm-12">
																							<form:input type="text" class="form-control text-right totalAmount" id="totalAmount_${ iLIdx.index }" path="paymentList[${ iLIdx.index }].totalAmount" data-parsley-type="number" data-parsley-trigger="keyup"></form:input>
																						</div>
																						</div>
																					</td>
																					<td><form:checkbox id="linked_${ iLIdx.index }" path="paymentList[${ iLIdx.index }].linked" label="linked" />
																					    <br>
																					    <c:if test="${ paymentSalesForm.paymentList[ iLIdx.index ].paymentType == 'L017_CASH'}">
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
											<div class="col-md-7 col-offset-md-5">
												<div class="btn-toolbar">
													<button id="cancelButton" type="reset" class="btn btn-primary pull-right">Cancel</button>
													<button id="submitButton" type="submit" class="btn btn-primary pull-right">Submit</button>
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
