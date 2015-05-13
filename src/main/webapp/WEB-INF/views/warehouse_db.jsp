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
		        	$('[id^="receiptDate_"]').datetimepicker({ format:'d-m-Y H:i' });
		            var api = this.api();
		            var rows = api.rows( {page:'current'} ).nodes();
		            var last=null;
		 
		            api.column(2, {page:'current'}).data().each(function (group, i) {
		                if (last !== group) {
		                    $(rows).eq(i).before(
		                        '<tr class="group"><td colspan="7">' + group + '</td></tr>'
		                    );		 
		                    last = group;
		                }
		            });
		        }
		    });
			
			$('#hideInflow, #hideOutflow').click(function() {
				var button = $(this).attr('id');
				if (button == 'hideInflow') {
					
				} else {
					
				}
			});

			$('#warehouseSelect').on('change', function(e) {
				var warehouseSelect = $("#warehouseSelect").val();
				if(warehouseSelect != '') {
					window.location = ctxpath + "/warehouse/dashboard/" + warehouseSelect;
				}
			});
			
			$('[id^="receiptButton_"]').click(function(event) {
				var itemId = $(this).closest('tr').find('td:eq(0)').attr('id');
				var trid = $(this).closest('tr').attr('id');
				
				var poid = $(this).val();
				var warehouseSelect = $("#warehouseSelect").val();
				var result = confirm("Yakin isi data po " + trid + " ?");
				if (result) {
					window.location = ctxpath + "/warehouse/dashboard/" + warehouseSelect + "/loadreceipt/" + poid + "/" + itemId;
				}
			});
			
			$('#inputReceiptDate').datetimepicker({ format:'d-m-Y H:i'});
			
			$('#inputReceiptDate').on('dp.change dp.show',function(e) {
				$(this).parsley().validate();
			});
			
			$('#cancelButton').click(function() {				
				window.location = ctxpath + "/warehouse/dashboard/" + $('#selectedWarehouse').val();
			});

		});
	</script>	
	<style type="text/css">
		tr.group, tr.group:hover {
    		background-color: #ddd !important;
		}
	</style>
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
					<span class="fa fa-wrench fa-fw"></span>&nbsp;Warehouse
				</h1>
				
				<c:choose>
					<c:when test="${PAGEMODE == 'PAGEMODE_PAGELOAD' || PAGEMODE == 'PAGEMODE_LIST'}">
						<div class="panel panel-default">
							<div class="panel-heading">
								<h1 class="panel-title">
									<span class="fa fa-wrench fa-fw fa-2x"></span>&nbsp;Warehouse Dashboard
								</h1>
							</div>
							<div class="panel-body">							
								<select class="form-control" id="warehouseSelect">
									<option value="">Please Select</option>
									<c:forEach items="${ warehouseSelectionDDL }" var="w">
										<option value="${ w.warehouseId }" <c:if test="${ w.warehouseId == warehouseDashboard.selectedWarehouse }"><c:out value='selected="selected"'/></c:if>><c:out value="${ w.warehouseName }"/></option>
									</c:forEach>
								</select>
								<br/>
								<div id="inflowPanel" class="panel panel-default">
									<div class="panel-heading">
										<h1 class="panel-title">
											<span class="fa fa-mail-forward fa-rotate-90 fa-fw"></span>Inflow
										</h1>
									</div>
									<div class="panel-body">
										<table id="inflowTable" class="table table-bordered table-hover display">
											<thead>
												<tr>
													<th width="40%">Product Name</th>
													<th width="10%">Bruto</th>
													<th width="10%">Po Code</th>
													<th width="10%">Netto</th>
													<th width="10%">Tare</th>
													<th width="10%">Receipt Date</th>
													<th width="10%">&nbsp;</th>
												</tr>
											</thead>
											<tbody >											
												<c:forEach items="${ warehouseDashboard.purchaseOrderList }" var="po" varStatus="poIdx">
													<c:forEach items="${ po.itemsList }" var="iL" varStatus="iLIdx">
														
														<c:if test="${ not empty iL.receiptList }">
															<c:set var="totalReceipt" value="${ iL.receiptList.size() }"></c:set>
															<c:set var="totalReceipt" value="${ 0 }"></c:set>
														 	<c:forEach items="${ iL.receiptList }" var="receipt" varStatus="receiptIdx">
															    <tr>
																    <td><c:out value="${ iL.productLookup.productName }"/></td>
														    		<td><c:out value="${ iL.toBaseQty }"/></td>
														    		<td><c:out value="${ po.poCode }"/></td>
															    	<td><c:out value="${ warehouseDashboard.purchaseOrderList[poIdx.index].itemsList[iLIdx.index].receiptList[receiptIdx.index].net }"/></td>
															    	<td><c:out value="${ warehouseDashboard.purchaseOrderList[poIdx.index].itemsList[iLIdx.index].receiptList[receiptIdx.index].tare }"/></td>
															    	<td><c:out value="${ warehouseDashboard.purchaseOrderList[poIdx.index].itemsList[iLIdx.index].receiptList[receiptIdx.index].receiptDate }"/></td>
															    	<td>
																    		
																    </td>
														    	</tr>
														    	<c:set var="totalReceipt" value="${ totalReceipt + (warehouseDashboard.purchaseOrderList[poIdx.index].itemsList[iLIdx.index].receiptList[receiptIdx.index].net + warehouseDashboard.purchaseOrderList[poIdx.index].itemsList[iLIdx.index].receiptList[receiptIdx.index].tare) }"></c:set>
													    	</c:forEach>
												    	
													    	<c:if test="${ totalReceipt <  warehouseDashboard.purchaseOrderList[poIdx.index].itemsList[iLIdx.index].toBaseQty }">											    	
														    	<tr id="${ po.poCode }">
														    		<td id="${ iL.itemsId }"><c:out value="${ iL.productLookup.productName }"/></td>
														    		<td><c:out value="${ iL.toBaseQty }"/></td>
														    		<td><c:out value="${ po.poCode }"/></td>
															    	<td></td>
															    	<td></td>
															    	<td></td>
															    	<td class="center-align">
															    		<button type="button" id="receiptButton_${ iLIdx.index }_${ totalReceipt }" class="btn btn-primary" value="${ po.poId }"><span class="fa fa-edit fa-fw"></span></button>
																    </td>
														    	</tr>
												    		</c:if>
											    		</c:if>
											    	
												    	<c:if test="${ empty iL.receiptList }">
													    	<tr id="${ po.poCode }">
													    		<td id="${ iL.itemsId }"><c:out value="${ iL.productLookup.productName }"/></td>
													    		<td><c:out value="${ iL.toBaseQty }"/></td>
													    		<td><c:out value="${ po.poCode }"/></td>
														    	<td></td>
														    	<td></td>
														    	<td></td>
														    	<td class="center-align">
														    		<button type="button" class="btn btn-xs btn-primary" id="receiptButton_0" value="${ po.poId }"><span class="fa fa-edit fa-fw"></span></button>
															    </td>
													    	</tr>
												    	</c:if>
													</c:forEach>
												</c:forEach>
											</tbody>
										</table>
									</div>
									<ul class="list-group">       
										<li class="list-group-item">
											<button type="button" id="hideInflow" class="btn btn-xs btn-default"><span class="fa fa-arrows-v fa-fw"></span></button>
										</li>
									</ul>
								</div>
								<br/>
								<div id="outflowPanel" class="panel panel-default">
									<div class="panel-heading">
										<h1 class="panel-title">
											<span class="fa fa-mail-reply fa-rotate-90 fa-fw"></span>Outflow
										</h1>
									</div>
									<div class="panel-body">
										<table id="outflowTable" class="table table-bordered table-hover display responsive">
											<thead>
												<tr>
													<th>Product Name</th>
													<th>Bruto</th>
													<th>Netto</th>
													<th>Tare</th>
													<th>&nbsp;</th>
												</tr>
											</thead>
											<tbody>
											</tbody>
										</table>
									</div>
									<ul class="list-group">
										<li class="list-group-item">
											<button type="button" id="hideOutflow" class="btn btn-xs btn-default"><span class="fa fa-arrows-v fa-fw"></span></button>
										</li>
									</ul>
								</div>
							</div>
						</div>
					</c:when>
					<c:when test="${PAGEMODE == 'PAGEMODE_EDIT'}">						
						<div class="panel panel-default">
							<div class="panel-heading">
								<h1 class="panel-title">
									<span class="fa fa-wrench fa-fw fa-2x"></span>&nbsp;Submit PO Detail
								</h1>
							</div>
							<div class="panel-body">
								<form:form id="warehouseDashboardForm" role="form" class="form-horizontal" modelAttribute="warehouseDashboard" action="${pageContext.request.contextPath}/warehouse/dashboard/savereceipt/${ warehouseDashboard.selectedPO }/${ warehouseDashboard.selectedItems }" data-parsley-validate="parsley">
									<div class="form-group">
										<label for="inputWarehouseId" class="col-sm-2 control-label">Warehouse</label>
										<div class="col-sm-5">
											<form:select class="form-control" disabled="true" path="selectedWarehouse">
												<form:options items="${ warehouseSelectionDDL }" itemValue="warehouseId" itemLabel="warehouseName"/>
											</form:select>
										</div>
									</div>
									<div class="form-group">
										<label for="inputPoCode" class="col-sm-2 control-label">PO Code</label>
										<div class="col-sm-3">
											<input class="form-control" value="${ selectedPoObject.poCode }" readonly="readonly"/>											
										</div>
									</div>
									<div class="form-group">
										<label for="inputProductName" class="col-sm-2 control-label">Product</label>
										<div class="col-sm-8">
											<input class="form-control" value="${ selectedItemsObject.productLookup.productName }" readonly="readonly"/>
										</div>
									</div>									
									<div class="form-group">
										<label for="inputBruto" class="col-sm-2 control-label">Bruto</label>
										<div class="col-sm-2">
											<c:forEach items="${ selectedPoObject.itemsList }" var="iL">
												<c:if test="${ iL.itemsId == selectedItemsObject.itemsId }">
													<input class="form-control" id="inputBruto" value="${ iL.toBaseQty }" readonly="readonly"/>
												</c:if>
											</c:forEach>
										</div>
									</div>
									<div class="form-group">
										<label for="inputNet" class="col-sm-2 control-label">Net</label>
										<div class="col-sm-2">
											<form:input class="form-control" id="inputNet" path="receipt.net" />												
										</div>
									</div>
									<div class="form-group">
										<label for="inputNet" class="col-sm-2 control-label">Tare</label>
										<div class="col-sm-2">
											<form:input class="form-control" id="inputTare" path="receipt.tare"/>										
										</div>
									</div>
									<div class="form-group">
										<label for="inputReceiptDate" class="col-sm-2 control-label">Receipt Date</label>
										<div class="col-sm-5">
											<form:input id="inputReceiptDate" class="form-control" path="receipt.receiptDate" data-parsley-required="true" data-parsley-trigger="change"/>												
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
				</c:choose>				
			</div>
		</div>
	</div>	
</body>
</html>
