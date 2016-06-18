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
												<th width="50%"><spring:message code="stocks_merge_jsp.from_table.header.po_detail" text="PO Details"></spring:message></th>
												<th width="5%"></th>
											</tr>
										</thead>
										<tbody>
											<c:if test="${ not empty stocksList }">
												<c:forEach items="${ stocksList }" var="s" varStatus="sIdx">
													<tr>
														<td align="center"><input id="cbx_<c:out value="${ s.stocksId }"/>" type="checkbox" value="<c:out value="${ s.stocksId }"/>"/></td>
														<td><spring:message code="${ s.productEntity.productTypeLookup.i18nLookupValue }" text="${ s.productEntity.productTypeLookup.lookupValue }"/></td>
														<td><c:out value="${ s.productEntity.productName }"></c:out></td>
														<td align="right">
															<c:forEach items="${ s.productEntity.productUnit }" var="pu">
																<c:if test="${ pu.isBaseUnit }">
																	<c:set var="defUnit" value="${ pu.unitCodeLookup.lookupValue }"></c:set>
																</c:if>
															</c:forEach>
															<c:out value="${ s.currentQuantity }"></c:out>&nbsp;<c:out value="${ defUnit }"/>
														</td>
														<td></td>
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
												<th width="15%"></th>
												<th width="15%"></th>
												<th width="10%"></th>
												<th width="50%"></th>
												<th width="5%"></th>
											</tr>
										</thead>
										<tbody>
										</tbody>
									</table>
								</div>
								<br/>
								<button id="mergeButton" type="submit" class="btn btn-primary"><spring:message code="common.merge_button" text="Start Merge"/></button>
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
