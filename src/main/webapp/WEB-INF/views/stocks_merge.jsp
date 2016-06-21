<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
	<jsp:include page="/WEB-INF/views/include/headtag.jsp"></jsp:include>
	<script>
		$(document).ready(function() {
			var ctxpath = "${ pageContext.request.contextPath }";
			
			$('#mergeButton').click(function() {
				if (isValid()) {
					var fval = "";
					var tval = "";
					var fwval = "";
					var twval = "";
					
					$('input[type="checkbox"][id^="cbx_f_"]').each(function(index, item) {
						if ($(item).prop("checked")) { 
							fval = $(item).val();
							fwval = $('#wh_f_' + $(item).val()).val();
						}	
					});
					$('input[type="checkbox"][id^="cbx_t_"]').each(function(index, item) {
						if ($(item).prop("checked")) { 
							tval = $(item).val(); 
							twval = $('#wh_t_' + $(item).val()).val();
						}
					});

					if (fval.length > 0 && tval.length > 0 && fwval.length > 0 && twval.length > 0) {
						window.location.href = ctxpath + "/warehouse/stocks/merge/fs/" + fval + "/wf/" + fwval + "/ts/" + tval + "/wt/" + twval;	
					}
				}
				return false;
			});

			function isValid() {
				var fcheck = false;
				var fval = "";
				var tcheck = false;
				var tval = "";
				
				$('input[type="checkbox"][id^="cbx_f_"]').each(function(index, item) {
					if ($(item).prop("checked")) { fcheck = true; fval = $(item).val(); }	
				});
				$('input[type="checkbox"][id^="cbx_t_"]').each(function(index, item) {
					if ($(item).prop("checked")) { tcheck = true; tval = $(item).val(); }
				});
				
				if (!fcheck || !tcheck) {
					jsAlert("Please select at least 1 stocks in each table.")
					return false;
				}

				if (fval == tval) {
					jsAlert("Cannot merge with the same stocks")
					return false;
				}
				
				return true;
			}
			
			$('input[type="checkbox"][id^="cbx_"]').click(function() {
				var selected = $(this);
				var ft = $(selected).attr("id").split('_')[1];

				$('input[type="checkbox"][id^="cbx_' + ft + '_"]').each(function(index, item) {
					if ($(item).attr("id") != $(selected).attr("id")) { 
						if ($(item).prop("checked")) {
							$(item).prop("checked", false);
						}
					}
				});
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
					<span class="fa fa-wrench fa-fw"></span>&nbsp;<spring:message code="stocks_merge_jsp.title" text="Warehouse"/>
				</h1>
				
				<c:choose>
					<c:when test="${ PAGEMODE == 'PAGEMODE_PAGELOAD' }">
						<div class="panel panel-default">
							<div class="panel-heading">
								<h1 class="panel-title">
									<span class="fa fa-retweet fa-fw fa-2x"></span>&nbsp;<spring:message code="stocks_merge_jsp.subtitle" text="Stocks Merge"/>
								</h1>
							</div>
							<div class="panel-body">
								<div class="panel panel-default">
									<div class="panel-heading">
										<spring:message code="stocks_merge_jsp.from_table" text="From"></spring:message>
									</div>
									<table class="table table-condensed table-bordered table-striped">
										<thead>
											<tr>
												<th width="5%"></th>
												<th width="15%"><spring:message code="stocks_merge_jsp.from_table.header.product_category" text="Product Category"></spring:message></th>
												<th width="15%"><spring:message code="stocks_merge_jsp.from_table.header.product_name" text="Product Name"></spring:message></th>
												<th width="10%"><spring:message code="stocks_merge_jsp.from_table.header.quantity" text="Quantity"></spring:message></th>
												<th width="15%"><spring:message code="stocks_merge_jsp.from_table.header.warehouse" text="Warehouse"></spring:message></th>
												<th width="35%"><spring:message code="stocks_merge_jsp.from_table.header.po_detail" text="PO Details"></spring:message></th>
												<th width="5%"></th>
											</tr>
										</thead>
										<tbody>
											<c:if test="${ not empty stocksFromList }">
												<c:forEach items="${ stocksFromList }" var="sF" varStatus="sFIdx">
													<tr>
														<td align="center">
															<input id="cbx_f_<c:out value="${ sF.stocksId }"/>" type="checkbox" value="<c:out value="${ sF.stocksId }"/>"/>
														</td>
														<td><spring:message code="${ sF.productEntity.productTypeLookup.i18nLookupValue }" text="${ sF.productEntity.productTypeLookup.lookupValue }"/></td>
														<td><c:out value="${ sF.productEntity.productName }"></c:out></td>
														<td align="right">
															<c:forEach items="${ sF.productEntity.productUnit }" var="pu">
																<c:if test="${ pu.isBaseUnit }">
																	<c:set var="defUnit" value="${ pu.unitCodeLookup.lookupValue }"></c:set>
																</c:if>
															</c:forEach>
															<c:out value="${ sF.currentQuantity }"></c:out>&nbsp;<c:out value="${ defUnit }"/>
														</td>
														<td>
															<input type="hidden" id="wh_f_<c:out value="${ sF.stocksId }"/>" value="${ sF.warehouseEntity.warehouseId }"/>
															<c:out value="${ sF.warehouseEntity.warehouseName }"/>
														</td>
														<td>PO Code: <c:out value="${ sF.purchaseOrderEntity.poCode }"/></td>
														<td></td>
													</tr>
												</c:forEach>
											</c:if>
										</tbody>
									</table>
								</div>
								<br/>
								<div class="panel panel-default">
									<div class="panel-heading">
										<spring:message code="stocks_merge_jsp.to_table" text="To"></spring:message>
									</div>
									<table class="table table-condensed table-bordered table-striped">
										<thead>
											<tr>
												<th width="5%"></th>
												<th width="15%"><spring:message code="stocks_merge_jsp.to_table.header.product_category" text="Product Category"></spring:message></th>
												<th width="15%"><spring:message code="stocks_merge_jsp.to_table.header.product_name" text="Product Name"></spring:message></th>
												<th width="10%"><spring:message code="stocks_merge_jsp.to_table.header.quantity" text="Quantity"></spring:message></th>
												<th width="15%"><spring:message code="stocks_merge_jsp.to_table.header.warehouse" text="Warehouse"></spring:message></th>
												<th width="35%"><spring:message code="stocks_merge_jsp.to_table.header.po_detail" text="PO Details"></spring:message></th>
												<th width="5%"></th>
											</tr>
										</thead>
										<tbody>
											<c:if test="${ not empty stocksToList }">
												<c:forEach items="${ stocksToList }" var="sT" varStatus="sTIdx">
													<tr>
														<td align="center">
															<input id="cbx_t_<c:out value="${ sT.stocksId }"/>" type="checkbox" value="<c:out value="${ sT.stocksId }"/>"/>
														</td>
														<td><spring:message code="${ sT.productEntity.productTypeLookup.i18nLookupValue }" text="${ sT.productEntity.productTypeLookup.lookupValue }"/></td>
														<td><c:out value="${ sT.productEntity.productName }"></c:out></td>
														<td align="right">
															<c:forEach items="${ sT.productEntity.productUnit }" var="pu">
																<c:if test="${ pu.isBaseUnit }">
																	<c:set var="defUnit" value="${ pu.unitCodeLookup.lookupValue }"></c:set>
																</c:if>
															</c:forEach>
															<c:out value="${ sT.currentQuantity }"></c:out>&nbsp;<c:out value="${ defUnit }"/>
														</td>
														<td>
															<input type="hidden" id="wh_t_<c:out value="${ sT.stocksId }"/>" value="${ sT.warehouseEntity.warehouseId }"/>
															<c:out value="${ sT.warehouseEntity.warehouseName }"/>
														</td>
														<td>PO Code: <c:out value="${ sT.purchaseOrderEntity.poCode }"/></td>
														<td></td>
													</tr>
												</c:forEach>
											</c:if>											
										</tbody>
									</table>
								</div>
								<br/>
								<button id="mergeButton" type="submit" class="btn btn-primary"><spring:message code="common.merge_button" text="Start Merge"/></button>
							</div>
						</div>
					</c:when>
					<c:when test="${ PAGEMODE == 'PAGEMODE_CONFIRMATION' }">
						<div class="panel panel-default">
							<div class="panel-body">
								<div class="well">
									<p>Merge Successful</p>
								</div>
								<a id="backButton" class="btn btn-primary" href="${ pageContext.request.contextPath }/warehouse/stocks/merge"><spring:message code="common.back_button" text="Back"/></a>							
							</div>
						</div>
					</c:when>
					<c:otherwise>
					
					</c:otherwise>
				</c:choose>
			</div>
		</div>
		<jsp:include page="/WEB-INF/views/include/footer.jsp"></jsp:include>
	
	</div>
</body>
</html>
