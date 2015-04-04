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

			$('#inflowTable, #outflowTable').DataTable({
				"paging":   	false,
		        "ordering": 	false,
		        "info":     	false,
		        "searching": 	false
			});
			
			$('#hideInflow, #hideOutflow').click(function() {
				var button = $(this).attr('id');
				if (button == 'hideInflow') {
					
				} else {
					
				}
			});

			$('#warehouseSelect').on('change',function(e) {
				var warehouseSelect = $("#warehouseSelect").val();
				if(warehouseSelect != ''){
					$('#poList').attr('action', ctxpath + "/warehouse/displayitems/"+warehouseSelect);
					$('#poList').submit();
				}
			});
			
			$('[id^="receiptDate_"]').datetimepicker({
				format : "DD-MM-YYYY"
			});

			//$('.receiptDate').on('dp.change dp.show',function(e) {
			//	$('#poList').formValidation('revalidateField', 'receiptDate');
			//});
			
			$('[id^="receiptButton_"]').click(function() {
				var poid = $(this).val();
				$('#poList').attr('action',ctxpath + "/warehouse/savereceipt/"+ poid);
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
							<form:form id="poList" modelAttribute="warehouseDashboard" action="${pageContext.request.contextPath}/warehouse/dashboard">
								<select class="form-control" id="warehouseSelect">
									<option value="">Please Select</option>
									<c:forEach items="${ warehouseSelectionDDL }" var="w">
										<option value="${ w.warehouseId }"><c:out value="${ w.warehouseName }"/></option>
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
									
										<table id="inflowTable" class="table table-bordered table-hover display responsive">
											<thead>
												<tr>
													<th>Product Name</th>
													<th>Bruto</th>
													<th>Netto</th>
													<th>Tare</th>
													<th>Receipt Date</th>
													<th>&nbsp;</th>
												</tr>
											</thead>
											<tbody >
											
											<c:forEach items="${ warehouseDashboard.purchaseOrderList }" var="po" varStatus="poIdx">
												<tr class="group">
													<td>
													   <form:hidden path="purchaseOrderList[${ poIdx.index }].poId"/>
													    
														<c:out value="${ po.poCode }"></c:out>
													</td>
													<td></td>
													<td></td>
													<td></td>
													<td></td>
													<td></td>
												</tr>
											
											<c:forEach items="${ po.itemsList }" var="iL" varStatus="iLIdx">
												<c:if test="${ not empty iL.receiptList }">
												<c:set var="totalReceipt" value="${ iL.receiptList.size() }"></c:set>
												<c:set var="totalNetReceipt" value="${ 0 }"></c:set>
												 	<c:forEach items="${ iL.receiptList }" var="receipt" varStatus="receiptIdx">
													    <tr>
												    	<td>
												    		<form:hidden path="purchaseOrderList[${ poIdx.index }].itemsList[${ iLIdx.index }].itemsId"/>
												    		<form:hidden path="purchaseOrderList[${ poIdx.index }].itemsList[${ iLIdx.index }].productId"/>
												    		<form:hidden path="purchaseOrderList[${ poIdx.index }].itemsList[${ iLIdx.index }].prodQuantity"/>
												    		<form:hidden path="purchaseOrderList[${ poIdx.index }].itemsList[${ iLIdx.index }].prodPrice"/>
												    		<form:hidden path="purchaseOrderList[${ poIdx.index }].itemsList[${ iLIdx.index }].unitCode"/>
												    		<form:hidden path="purchaseOrderList[${ poIdx.index }].itemsList[${ iLIdx.index }].receiptList[${receiptIdx.index}].receiptId"/>
													    	${ iL.productLookup.productName }
												    	</td>
											    		<td>${ iL.prodQuantity }</td>
												    	<td>
												    		<form:hidden path="purchaseOrderList[${poIdx.index}].itemsList[${iLIdx.index}].receiptList[${receiptIdx.index}].net"/>
												    		<c:out value="${ warehouseDashboard.purchaseOrderList[poIdx.index].itemsList[iLIdx.index].receiptList[receiptIdx.index].net }"></c:out>
												    	</td>
												    	<td>
												    		<form:hidden path="purchaseOrderList[${poIdx.index}].itemsList[${iLIdx.index}].receiptList[${receiptIdx.index}].tare"/>
												    		<c:out value="${ warehouseDashboard.purchaseOrderList[poIdx.index].itemsList[iLIdx.index].receiptList[receiptIdx.index].tare }"></c:out>
												    	</td>
												    	<td>
												    		<form:hidden path="purchaseOrderList[${poIdx.index}].itemsList[${iLIdx.index}].receiptList[${receiptIdx.index}].receiptDate"/>
												    		<c:out value="${ warehouseDashboard.purchaseOrderList[poIdx.index].itemsList[iLIdx.index].receiptList[receiptIdx.index].receiptDate }"></c:out>
												    	</td>
												    	<td>
													    		
													    </td>
											    	</tr>
											    	<c:set var="totalNetReceipt" value="${ totalNetReceipt + warehouseDashboard.purchaseOrderList[poIdx.index].itemsList[iLIdx.index].receiptList[receiptIdx.index].net }"></c:set>
											    	</c:forEach>
											    	
											    	<c:if test="${ totalNetReceipt <  warehouseDashboard.purchaseOrderList[poIdx.index].itemsList[iLIdx.index].prodQuantity }">
											    	
											    	<tr>
												    	<td>
													    	
													    	<form:hidden path="purchaseOrderList[${ poIdx.index }].itemsList[${ iLIdx.index }].itemsId"/>
												    		<form:hidden path="purchaseOrderList[${ poIdx.index }].itemsList[${ iLIdx.index }].productId"/>
												    		<form:hidden path="purchaseOrderList[${ poIdx.index }].itemsList[${ iLIdx.index }].prodQuantity"/>
												    		<form:hidden path="purchaseOrderList[${ poIdx.index }].itemsList[${ iLIdx.index }].prodPrice"/>
												    		<form:hidden path="purchaseOrderList[${ poIdx.index }].itemsList[${ iLIdx.index }].unitCode"/>
													    	
													    	${ iL.productLookup.productName }
												    	</td>
											    		<td>${ iL.prodQuantity }</td>
												    	<td>
												    		<form:input type="text" class="form-control text-right" path="purchaseOrderList[${poIdx.index}].itemsList[${iLIdx.index}].receiptList[${ totalReceipt }].net"/>
												    	</td>
												    	<td>
												    		<form:input type="text" class="form-control text-right" path="purchaseOrderList[${poIdx.index}].itemsList[${iLIdx.index}].receiptList[${ totalReceipt }].tare"/>
												    	</td>
												    	<td>
												    		
												    	</td>
												    	<td>
													    		<button type="submit" id="receiptButton_${ iLIdx.index }_${ totalReceipt }" class="btn btn-primary" value="${ po.poId }">Receipt</button>
													    </td>
											    	</tr>
											    	</c:if>
										    	</c:if>
										    	
										    	<c:if test="${ empty iL.receiptList }">
											    	<tr>
												    	<td>
												    		<form:hidden path="purchaseOrderList[${ poIdx.index }].itemsList[${ iLIdx.index }].itemsId"/>
												    		<form:hidden path="purchaseOrderList[${ poIdx.index }].itemsList[${ iLIdx.index }].productId"/>
												    		<form:hidden path="purchaseOrderList[${ poIdx.index }].itemsList[${ iLIdx.index }].prodQuantity"/>
												    		<form:hidden path="purchaseOrderList[${ poIdx.index }].itemsList[${ iLIdx.index }].prodPrice"/>
												    		<form:hidden path="purchaseOrderList[${ poIdx.index }].itemsList[${ iLIdx.index }].unitCode"/>
													    	${ iL.productLookup.productName }
												    	</td>
											    		<td>${ iL.prodQuantity }</td>
												    	<td>
												    		<form:input type="text" class="form-control text-right" path="purchaseOrderList[${poIdx.index}].itemsList[${iLIdx.index}].receiptList[0].net"/>
												    	</td>
												    	<td>
												    		<form:input type="text" class="form-control text-right" path="purchaseOrderList[${poIdx.index}].itemsList[${iLIdx.index}].receiptList[0].tare"/>
												    	</td>
												    	<td>
												    		
												    	</td>
												    	<td>
												    		<button type="submit" class="btn btn-primary" id="receiptButton_0" value="${ po.poId }">Receipt</button>
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
							</form:form>
							</div>
						</div>
					</c:when>
					<c:when test="${PAGEMODE == 'PAGEMODE_EDIT'}">
					</c:when>
				</c:choose>				
			</div>
		</div>
	</div>	
</body>
</html>
