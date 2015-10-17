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

			$('#outflowTable').DataTable({
				"paging":   	false,
		        "ordering": 	false,
		        "info":     	false,
		        "searching": 	false
			});
			
			var table = $('#inflowTable').DataTable({
				"paging":   	false,
		        "ordering": 	false,
		        "info":     	false,
		        "searching": 	false,
		        "columnDefs": [
		            { "visible": false, "targets": 2 }
		        ],		       
		        "displayLength": 25,
		        "drawCallback": function ( settings ) {
		            var api = this.api();
		            var rows = api.rows( {page:'current'} ).nodes();
		            var last=null;
		 
		            api.column(2, {page:'current'}).data().each(function (group, i) {
		                if (last !== group) {
		                    $(rows).eq(i).before(
		                        '<tr class="group"><td colspan="5">' + group + '</td></tr>'
		                    );		 
		                    last = group;
		                }
		            });
		        }
		    });
			
			$('#warehouseSelect').on('change', function(e) {
				var warehouseSelect = $("#warehouseSelect").val();
				if(warehouseSelect != '') {
					window.location = ctxpath + "/warehouse/dashboard/id/" + warehouseSelect;
				}
			});
			
			$('[id^="receiptButton_"]').click(function(event) {
				var itemId = $(this).closest('tr').find('td:eq(0)').attr('id');
				var trid = $(this).closest('tr').attr('id');
				
				var poid = $(this).val();
				var warehouseSelect = $("#warehouseSelect").val();
				var result = true;
				if (result) {
					window.location = ctxpath + "/warehouse/dashboard/id/" + warehouseSelect + "/loadreceipt/" + poid + "/" + itemId;
				}
			});

			$('[id^="deliverButton_"]').click(function(event) {
				var salesId = $(this).attr('id').replace('deliverButton_', '');
				
				var warehouseSelect = $("#warehouseSelect").val();
				var result = true;
				if (result) {
					window.location = ctxpath + "/warehouse/dashboard/id/" + warehouseSelect + "/loaddeliver/" + salesId;
				}
			});
			
			$('#inputReceiptDate').datetimepicker({ format:'d-m-Y H:i'});
			
			$('#inputReceiptDate').on('dp.change dp.show',function(e) {
				$(this).parsley().validate();
			});
			
			$('#inputDeliverDate').datetimepicker({ format:'d-m-Y H:i'});
			
			$('#inputDeliverDate').on('dp.change dp.show',function(e) {
				$(this).parsley().validate();
			});
			
			$('#cancelButton').click(function() {				
				window.location = ctxpath + "/warehouse/dashboard/id/" + $('#selectedWarehouse').val();
			});
			
			window.Parsley.addValidator('equalwithbruto', function (value, requirement) {
				if (requirement == false) return true;
				console.log(Number($('input[name="receipt.bruto"]').val()));
				console.log(Number($('input[name="receipt.net"]').val()));
				console.log(Number($('input[name="receipt.tare"]').val()));
				console.log(Number($('input[name="receipt.bruto"]').val()) == (Number($('input[name="receipt.net"]').val()) + Number($('input[name="receipt.tare"]').val())));
				if (Number($('input[name="receipt.bruto"]').val()) == (Number($('input[name="receipt.net"]').val()) + Number($('input[name="receipt.tare"]').val()))) {
					return true;
				} else if (Number($('#inputBrutoDeliver').val()) == (Number($('input[name="deliver.net"]').val()) + Number($('input[name="deliver.tare"]').val()))) {
					return true;
				} else {
					return false;
				}				
			}, 32)
			.addMessage('en', 'equalwithbruto', 'Netto and Tare value not equal with Bruto')
			.addMessage('id', 'equalwithbruto', 'Nilai bersih dan Tara tidak sama dengan Nilai Kotor');

			$('#warehouseDashboardForm').parsley();
			
			$('#inputDeliverDate').parsley().on('field:success', function() {
				$('#deliverDateHidden input[type="hidden"]').val($('#inputDeliverDate').val());
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
				<c:if test="${ERRORFLAG == 'ERRORFLAG_SHOW' }">
	    			<div class="alert alert-danger alert-dismissible" role="alert">
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
					<span class="fa fa-wrench fa-fw"></span>&nbsp;<spring:message code="warehouse_db_jsp.title" text="Warehouse"/>
				</h1>
				
				<c:choose>
					<c:when test="${ PAGEMODE == 'PAGEMODE_PAGELOAD' || PAGEMODE == 'PAGEMODE_LIST' }">
						<div class="panel panel-default">
							<div class="panel-heading">
								<h1 class="panel-title">
									<span class="fa fa-wrench fa-fw fa-2x"></span>&nbsp;<spring:message code="warehouse_db_jsp.subtitle" text="Warehouse Dashboard"/>
								</h1>
							</div>
							<div class="panel-body">
								<select class="form-control" id="warehouseSelect">
									<option value=""><spring:message code="common.please_select" text="Please Select"/></option>
									<c:forEach items="${ warehouseSelectionDDL }" var="w">
										<option value="${ w.warehouseId }" <c:if test="${ w.warehouseId == warehouseDashboard.selectedWarehouse }"><c:out value='selected="selected"'/></c:if>><c:out value="${ w.warehouseName }"/></option>
									</c:forEach>
								</select>
								<br/>
								<div id="inflowPanel" class="panel panel-default">
									<div class="panel-heading">
										<h1 class="panel-title">
											<span class="fa fa-mail-forward fa-rotate-90 fa-fw"></span><spring:message code="warehouse_db_jsp.inflow" text="Inflow"/><c:if test="${ not empty startDate }">&nbsp;[&nbsp;<fmt:formatDate pattern="dd MMM" value="${ startDate }" />&nbsp;-&nbsp;<fmt:formatDate pattern="dd MMM" value="${ endDate }" />&nbsp;]</c:if>
										</h1>
									</div>
									<div class="panel-body">
										<table id="inflowTable" class="table table-bordered table-hover display responsive" style="width: 100%; border-collapse: separate;">														
											<thead>
												<tr>
													<th colspan="6"><spring:message code="warehouse_db_jsp.table.inflow.header.po_code" text="Po Code"/></th>
												</tr>
												<tr>
													<th width="36%"><spring:message code="warehouse_db_jsp.table.inflow.header.product_name" text="Product Name"/></th>
													<th width="11%" class="center-align"><spring:message code="warehouse_db_jsp.table.inflow.header.bruto" text="Bruto"/></th>
													<th width="2%" class="never"><spring:message code="warehouse_db_jsp.table.inflow.header.po_code" text="Po Code"/></th>
													<th width="13%" class="center-align"><spring:message code="warehouse_db_jsp.table.inflow.header.shipping_date" text="Shipping Date"/></th>
													<th width="13%" class="center-align"><spring:message code="warehouse_db_jsp.table.inflow.header.receipt_date" text="Receipt Date"/></th>
													<th width="3%" class="all">&nbsp;</th>
												</tr>
											</thead>
											<tbody >											
												<c:forEach items="${ warehouseDashboard.purchaseOrderList }" var="po" varStatus="poIdx">
													<c:forEach items="${ po.itemsList }" var="iL" varStatus="iLIdx">
												    	<tr id="${ po.poCode }">
												    		<td id="${ iL.itemsId }" class="valign-middle"><c:out value="${ iL.productLookup.productName }"/></td>
												    		<td class="right-align"><c:out value="${ iL.toBaseQty }"/>&nbsp;<c:out value="${ iL.baseUnitCodeLookup.lookupValue }"/></td>
												    		<td class="never"><c:out value="${ po.poCode }"/>&nbsp;-&nbsp;<c:out value="${ po.supplierLookup.supplierName }"/></td>
													    	<td class="center-align"><fmt:formatDate pattern="dd MMM yyyy" value="${ po.shippingDate }"/></td>
													    	<td class="center-align">
													    		<c:if test="${ not empty iL.receiptList }">
													    			<fmt:formatDate pattern="dd MMM yyyy HH:mm" value="${ iL.receiptList[0].receiptDate }"/>
													    		</c:if>
													    	</td>
													    	<td class="center-align all">
														    	<c:if test="${ empty iL.receiptList }">
														    		<button type="button" class="btn btn-xs btn-primary" id="receiptButton_0" value="${ po.poId }"><span class="fa fa-edit fa-fw"></span></button>
														    	</c:if>
														    </td>
												    	</tr>
													</c:forEach>
												</c:forEach>
											</tbody>
										</table>
									</div>									
								</div>
								<br/>
								<br/>
								<div id="outflowPanel" class="panel panel-default">
									<div class="panel-heading">
										<h1 class="panel-title">
											<span class="fa fa-mail-reply fa-rotate-90 fa-fw"></span><spring:message code="warehouse_db_jsp.outflow" text="Outflow"/><c:if test="${ not empty startDate }">&nbsp;[&nbsp;<fmt:formatDate pattern="dd MMM" value="${ startDate }" />&nbsp;-&nbsp;<fmt:formatDate pattern="dd MMM" value="${ endDate }" />&nbsp;]</c:if>
										</h1>
									</div>
									<div class="panel-body">
										<table id="outflowTable" class="table table-bordered table-hover display responsive">
											<thead>
												<tr>
													<th><spring:message code="warehouse_db_jsp.table.outflow.header.customer_name" text="Customer Name"/></th>
													<th><spring:message code="warehouse_db_jsp.table.outflow.header.sales_code" text="Sales Code"/></th>
													<th class="center-align"><spring:message code="warehouse_db_jsp.table.outflow.header.shipping_date" text="Shipping Date"/></th>
													<th class="center-align"><spring:message code="warehouse_db_jsp.table.outflow.header.deliver_date" text="Deliver Date"/></th>
													<th></th>
												</tr>
											</thead>
											<tbody>
												<c:forEach items="${ warehouseDashboard.salesOrderList }" var="so" varStatus="soIdx">
											    	<tr>
											    		<td><c:out value="${ so.customerLookup.customerName }"/></td>
											    		<td><c:out value="${ so.salesCode }"/></td>
												    	<td class="center-align"><fmt:formatDate pattern="dd MMM yyyy" value="${ so.shippingDate }"/></td>
												    	<td></td>
												    	<td class="center-align">
												    		<button type="button" class="btn btn-xs btn-primary" id="deliverButton_${ so.salesId }" value="${ so.salesId }"><span class="fa fa-edit fa-fw"></span></button>
													    </td>
											    	</tr>
												</c:forEach>
											</tbody>
										</table>
									</div>
								</div>
							</div>
						</div>
					</c:when>
					<c:when test="${ PAGEMODE == 'PAGEMODE_EDIT' }">
						<c:choose>
							<c:when test="${ flow == 'Outflow' }">
								<div class="panel panel-default">
									<div class="panel-heading">
										<h1 class="panel-title">
											<span class="fa fa-wrench fa-fw fa-2x"></span>&nbsp;Submit Sales Order Detail
										</h1>
									</div>
									<div class="panel-body">
										<form:form id="warehouseDashboardForm" role="form" class="form-horizontal" modelAttribute="warehouseDashboard" action="${pageContext.request.contextPath}/warehouse/dashboard/savedeliver/${ warehouseDashboard.selectedSales }/${ warehouseDashboard.selectedWarehouse }" data-parsley-validate="parsley">
											<div class="form-group">
												<label for="inputWarehouseId" class="col-sm-2 control-label">Warehouse</label>
												<div class="col-sm-5">
													<form:select class="form-control" disabled="true" path="selectedWarehouse">
														<form:options items="${ warehouseSelectionDDL }" itemValue="warehouseId" itemLabel="warehouseName"/>
													</form:select>
												</div>
											</div>
											<div class="form-group">
												<label for="inputPoCode" class="col-sm-2 control-label">Sales Code</label>
												<div class="col-sm-3">
													<input class="form-control" value="${ selectedSoObject.salesCode }" readonly="readonly"/>											
												</div>
											</div>
											<div class="form-group">
												<label for="inputCustomerName" class="col-sm-2 control-label">Customer Name</label>
												<div class="col-sm-3">
													<input class="form-control" value="${ selectedSoObject.customerLookup.customerName }" readonly="readonly"/>											
												</div>
											</div>											
											<div class="form-group">
												<label for="inputProductName" class="col-sm-2 control-label">Product</label>
												<div class="col-sm-10">
													 <div class="table-responsive">
 															<table class="table">
 																<thead>
 																	<tr>
 																		<th>Product</th>
 																		<th>Quantity</th>
 																		<th>Stocks</th>
 																		<th>Bruto</th>
 																	</tr>
 																</thead>
 																<tbody>
 																	<c:forEach items="${ selectedSoObject.itemsList }" var="iL" varStatus="itIdx">
	 																	<tr>
	 																		<td><form:hidden path="salesOrderList[0].itemsList[${ itIdx.index }].itemsId"/>
	 																			<c:out value="${ iL.productLookup.productName }"/><c:out value="${ warehouseDashboardForm.salesOrderList[0].itemsList.size() }"/>
	 																		</td>
	 																		<td><c:out value="${ iL.prodQuantity }"/>&nbsp;<c:out value="${ iL.unitCodeLookup.lookupValue }"/></td>
	 																		<td><c:out value="${ iL.stocksLookup.prodQuantity }"/></td>
	 																		<td><form:input class="form-control" path="salesOrderList[0].itemsList[${ itIdx.index }].deliverList[0].bruto"></form:input></td>
	 																	</tr>
 																	</c:forEach>
 																</tbody>
														</table>
													</div>
												</div>
											</div>
											<div class="form-group">
												<label for="inputShippingDate" class="col-sm-2 control-label">Shipping Date</label>
												<div class="col-sm-5">
													<fmt:formatDate pattern="dd MMM yyyy" value="${ selectedSoObject.shippingDate }" var="formattedShippingDate"/>
													<input class="form-control" value="${ formattedShippingDate }" readonly="readonly"/>			
												</div>
											</div>
											<div class="form-group">
												<label for="inputDeliverDate" class="col-sm-2 control-label">Deliver Date</label>
												<div class="col-sm-5">
													<div id="deliverDateHidden">
														<c:forEach items="${ selectedSoObject.itemsList }" var="iL" varStatus="itIdx">
															<form:hidden path="salesOrderList[0].itemsList[${ itIdx.index }].deliverList[0].deliverDate"/>
														</c:forEach>
													</div>
													<input id="inputDeliverDate" class="form-control" data-parsley-required="true" data-parsley-trigger="change"/>												
												</div>
											</div>
											<div class="col-md-7 col-offset-md-5">
												<div class="btn-toolbar">
													<button id="cancelButton" type="button" class="btn btn-primary pull-right">Cancel</button>
													<button id="submitButton" type="submit" class="btn btn-primary pull-right">Submit</button>
												</div>
											</div>
										</form:form>
									</div>
								</div>
							</c:when>
							<c:otherwise>
								<div class="panel panel-default">
									<div class="panel-heading">
										<h1 class="panel-title">
											<span class="fa fa-wrench fa-fw fa-2x"></span>&nbsp;<spring:message code="warehouse_db_jsp.inflow.submit_po_detail" text="Submit PO Detail"></spring:message>
										</h1>
									</div>
									<div class="panel-body">
										<form:form id="warehouseDashboardForm" role="form" class="form-horizontal" modelAttribute="warehouseDashboard" action="${pageContext.request.contextPath}/warehouse/dashboard/savereceipt/${ warehouseDashboard.selectedPO }/${ warehouseDashboard.selectedItems }" data-parsley-validate="parsley">
											<div class="form-group">
												<label for="inputWarehouseId" class="col-sm-2 control-label"><spring:message code="warehouse_db_jsp.inflow.warehouse" text="Warehouse"></spring:message></label>
												<div class="col-sm-5">
													<form:select class="form-control" disabled="true" path="selectedWarehouse">
														<form:options items="${ warehouseSelectionDDL }" itemValue="warehouseId" itemLabel="warehouseName"/>
													</form:select>
												</div>
											</div>
											<div class="form-group">
												<label for="inputPoCode" class="col-sm-2 control-label"><spring:message code="warehouse_db_jsp.inflow.po_code" text="PO Code"/></label>
												<div class="col-sm-3">
													<input class="form-control" value="${ selectedPoObject.poCode }" readonly="readonly"/>											
												</div>
											</div>
											<div class="form-group">
												<label for="inputProductName" class="col-sm-2 control-label"><spring:message code="warehouse_db_jsp.inflow.product" text="Product"/></label>
												<div class="col-sm-8">
													<input class="form-control" value="${ selectedItemsObject.productLookup.productName }" readonly="readonly"/>
												</div>
											</div>
											<div class="form-group">
												<label for="inputBruto" class="col-sm-2 control-label"><spring:message code="warehouse_db_jsp.inflow.bruto" text="Bruto"/></label>
												<div class="col-sm-3">
													<div class="input-group">
														<form:input class="form-control" path="receipt.bruto" data-parsley-min="1" data-parsley-required="true" data-parsley-equalwithbruto="true" data-parsley-type="digits"></form:input>
														<span class="input-group-addon">
															<c:forEach items="${ selectedPoObject.itemsList }" var="i">
																<c:if test="${ i.itemsId == warehouseDashboard.selectedItems }">
																	<spring:message code="${ i.baseUnitCodeLookup.i18nLookupValue }" text="${ i.baseUnitCodeLookup.lookupValue }"/>			
																</c:if>
															</c:forEach>
														</span>
													</div>
												</div>
											</div>
											<div class="form-group">
												<label for="inputNet" class="col-sm-2 control-label"><spring:message code="warehouse_db_jsp.inflow.net" text="Net"/></label>
												<div class="col-sm-3">
													<div class="input-group">
														<form:input class="form-control" path="receipt.net" data-parsley-min="1" data-parsley-required="true" data-parsley-equalwithbruto="true" data-parsley-type="digits"/>										
														<span class="input-group-addon">
															<c:forEach items="${ selectedPoObject.itemsList }" var="i">
																<c:if test="${ i.itemsId == warehouseDashboard.selectedItems }">
																	<spring:message code="${ i.baseUnitCodeLookup.i18nLookupValue }" text="${ i.baseUnitCodeLookup.lookupValue }"/>			
																</c:if>
															</c:forEach>
														</span>
													</div>
												</div>
											</div>
											<div class="form-group">
												<label for="inputNet" class="col-sm-2 control-label"><spring:message code="warehouse_db_jsp.inflow.tare" text="Tare"/></label>
												<div class="col-sm-3">
													<div class="input-group">
														<form:input class="form-control" path="receipt.tare" data-parsley-min="0" data-parsley-required="true" data-parsley-equalwithbruto="true" data-parsley-type="digits"/>
														<span class="input-group-addon">
															<c:forEach items="${ selectedPoObject.itemsList }" var="i">
																<c:if test="${ i.itemsId == warehouseDashboard.selectedItems }">
																	<spring:message code="${ i.baseUnitCodeLookup.i18nLookupValue }" text="${ i.baseUnitCodeLookup.lookupValue }"/>			
																</c:if>
															</c:forEach>
														</span>
													</div>
												</div>
											</div>
											<div class="form-group">
												<label for="inputShippingDate" class="col-sm-2 control-label"><spring:message code="warehouse_db_jsp.inflow.shipping_date" text="Shipping Date"/></label>
												<div class="col-sm-5">
													<fmt:formatDate pattern="dd MMM yyyy" value="${ selectedPoObject.shippingDate }" var="formattedShippingDate"/>
													<input class="form-control" value="${ formattedShippingDate }" readonly="readonly"/>
												</div>
											</div>
											<div class="form-group">
												<label for="inputReceiptDate" class="col-sm-2 control-label"><spring:message code="warehouse_db_jsp.inflow.receipt_date" text="Receipt Date"/></label>
												<div class="col-sm-5">
													<form:input id="inputReceiptDate" class="form-control" path="receipt.receiptDate" data-parsley-required="true" data-parsley-trigger="change"/>
												</div>
											</div>
											<div class="col-md-7 col-offset-md-5">
												<div class="btn-toolbar">
													<button id="cancelButton" type="button" class="btn btn-primary pull-right"><spring:message code="common.cancel_button" text="Cancel"/></button>
													<button id="submitButton" type="submit" class="btn btn-primary pull-right"><spring:message code="common.submit_button" text="Submit"/></button>
												</div>
											</div>
										</form:form>
									</div>
								</div>
							</c:otherwise>						
						</c:choose>						
					</c:when>
				</c:choose>				
			</div>
		</div>
		
		<jsp:include page="/WEB-INF/views/include/footer.jsp"></jsp:include>
		
	</div>	
</body>
</html>
