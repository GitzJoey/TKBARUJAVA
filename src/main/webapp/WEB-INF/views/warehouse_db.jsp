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
		        "columns": [
		                    { "width": "20%" },
		                    { "width": "20%" },
		                    null,
		                    { "width": "10%" },
		                    { "width": "10%" },
		                    { "width": "10%" },
		                    { "width": "10%" },
		                  ]
		       ,
		       
		    //    "displayLength": 25,
		        "drawCallback": function ( settings ) {
		        	$('.showCalendar').datepicker();
		            var api = this.api();
		            var rows = api.rows( {page:'current'} ).nodes();
		            var last=null;
		 
		            api.column(2, {page:'current'} ).data().each( function ( group, i ) {
		                if ( last !== group ) {
		                    $(rows).eq( i ).before(
		                        '<tr class="group"><td colspan="7">'+group+'</td></tr>'
		                    );
		 
		                    last = group;
		                }
		            } );
		        }
		    } );
		 
		   
			
			$('#hideInflow, #hideOutflow').click(function() {
				var button = $(this).attr('id');
				if (button == 'hideInflow') {
					
				} else {
					
				}
			});

			$('#warehouseSelect').on('change',function(e) {
				var warehouseSelect = $("#warehouseSelect").val();
				if(warehouseSelect != ''){
					$('#warehouseDashboardForm').attr('action', ctxpath + "/warehouse/displayitems/"+warehouseSelect);
					$('#warehouseDashboardForm').submit();
				}
			});
			
			$('[id^="receiptButton_"]').click(function() {
				var poid = $(this).val();
				var pocode = $('#'+poid).val();
				var result = confirm("Yakin isi data po "+pocode+" ?");
				if (result) {
					$('#warehouseDashboardForm').attr('action',ctxpath + "/warehouse/savereceipt/"+ poid);
				}
				
				
			});

			//$('[id^="receiptDate_"]').datetimepicker({format:'DD-MM-YYYY H:mm',pickerPosition: "bottom-left"});
			//$('.datetimepicker').datetimepicker({format:'DD-MM-YYYY H:mm'});
			$('[id^="receiptDate_"]').datepicker({format:'DD-MM-YYYY H:mm'});
			
		});
	</script>	
	<!--  <style type="text/css">
	tr.group,
	tr.group:hover {
    background-color: #ddd !important;
}
	</style> -->
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
							<form:form id="warehouseDashboardForm" modelAttribute="warehouseDashboard" action="${pageContext.request.contextPath}/warehouse/dashboard">
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
									
										<table id="inflowTable" class="table table-bordered table-hover display">
											<thead>
												<tr>
													<th>Product Name</th>
													<th>Bruto</th>
													<th>Po Code</th>
													<th>Netto</th>
													<th>Tare</th>
													<th>Receipt Date</th>
													<th>&nbsp;</th>
												</tr>
											</thead>
											<tbody >
											
											<c:forEach items="${ warehouseDashboard.purchaseOrderList }" var="po" varStatus="poIdx">
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
															    <form:hidden path="purchaseOrderList[${ poIdx.index }].poId"/>
															    <form:hidden id="${ po.poId }" path="purchaseOrderList[${ poIdx.index }].poCode"/>
																<c:out value="${ po.poCode }"></c:out>
														    </td>
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
											    	
											    	<tr id="tr_${ poIdx.index }_${ iLIdx.index }_${ totalReceipt }">
											    		<td>
												    		<form:hidden path="purchaseOrderList[${ poIdx.index }].itemsList[${ iLIdx.index }].itemsId"/>
												    		<form:hidden path="purchaseOrderList[${ poIdx.index }].itemsList[${ iLIdx.index }].productId"/>
												    		<form:hidden path="purchaseOrderList[${ poIdx.index }].itemsList[${ iLIdx.index }].prodQuantity"/>
												    		<form:hidden path="purchaseOrderList[${ poIdx.index }].itemsList[${ iLIdx.index }].prodPrice"/>
												    		<form:hidden path="purchaseOrderList[${ poIdx.index }].itemsList[${ iLIdx.index }].unitCode"/>
												    		<c:out value="${ iL.productLookup.productName }"></c:out>
												    	</td>
											    		<td>
											    			<c:out value="${ iL.prodQuantity }"></c:out>
											    		</td>
											    		<td>
														    <form:hidden path="purchaseOrderList[${ poIdx.index }].poId"/>
														    <form:hidden id="${ po.poId }" path="purchaseOrderList[${ poIdx.index }].poCode"/>
															<c:out value="${ po.poCode }"></c:out>
													    </td>
												    	<td>
												    	
												    		<form:input size="8" type="text" class="form-control text-right" path="purchaseOrderList[${poIdx.index}].itemsList[${iLIdx.index}].receiptList[${ totalReceipt }].net"/>
												    	
												    	</td>
												    	<td>
												    	
												    	
												    		<form:input size="8" type="text" class="form-control text-right" path="purchaseOrderList[${poIdx.index}].itemsList[${iLIdx.index}].receiptList[${ totalReceipt }].tare"/>
												    	
												    	</td>
												    	<td id="colReceiptDate_${poIdx.index}_${iLIdx.index}_${ totalReceipt }">
												    	
												    	<div class="form-group">
												    		<form:input size="12" type="text" id="receiptDate_${poIdx.index}_${iLIdx.index}_${receiptIdx.index}" path="purchaseOrderList[${poIdx.index}].itemsList[${iLIdx.index}].receiptList[${ totalReceipt }].receiptDate"/>
												    	</div>
												    	</td>
												    	<td>
												    		<button type="submit" id="receiptButton_${ iLIdx.index }_${ totalReceipt }" class="btn btn-primary" value="${ po.poId }">Submit</button>
													    </td>
											    	</tr>
											    	</c:if>
										    	</c:if>
										    	
										    	<c:if test="${ empty iL.receiptList }">
											    	<tr id="tr_${ poIdx.index }_${ iLIdx.index }_0">
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
														    <form:hidden path="purchaseOrderList[${ poIdx.index }].poId"/>
														    <form:hidden id="${ po.poId }" path="purchaseOrderList[${ poIdx.index }].poCode"/>
															<c:out value="${ po.poCode }"></c:out>
													    </td>
												    	<td>
												    	
												    	
												    		<form:input size="8" type="text" class="form-control text-right" path="purchaseOrderList[${poIdx.index}].itemsList[${iLIdx.index}].receiptList[0].net"/>
												    	
												    	
												    	</td>
												    	<td>
												    	
												    	
												    		<form:input size="8" type="text" class="form-control text-right" path="purchaseOrderList[${poIdx.index}].itemsList[${iLIdx.index}].receiptList[0].tare"/>
												    	
												    	
												    	</td>
												    	<td id="colReceiptDate_${poIdx.index}_${iLIdx.index}_0">
												    	
												    	<div class="form-group">
												    		<form:input size="12" type="text" class="form-control" id="receiptDate_${poIdx.index}_${iLIdx.index}_0" path="purchaseOrderList[${poIdx.index}].itemsList[${iLIdx.index}].receiptList[0].receiptDate"/>
												    	</div>
												    	
												    	</td>
												    	<td>
												    		<button type="submit" class="btn btn-primary" id="receiptButton_0" value="${ po.poId }">Submit</button>
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
