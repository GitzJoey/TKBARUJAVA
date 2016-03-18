<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<!DOCTYPE html>
<html>
<head>
	<jsp:include page="/WEB-INF/views/include/headtag.jsp"></jsp:include>
	<script>
		$(document).ready(function() {
			var ctxpath = "${ pageContext.request.contextPath }";
			
		    $('#poListTable').DataTable();
		    $('#soListTable').DataTable();
			$('#stockListTable').DataTable();
		    $('#customerListTable, #supplierListTable, #productListTable, #truckListTable').DataTable();
		    $('#userListTable, #storeListTable, #roleListTable, #functionListTable, #lookupListTable').DataTable();
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
					<span class="fa fa-bar-chart-o fa-fw"></span>&nbsp;Reports
				</h1>

				<c:choose>
					<c:when test="${ PAGEMODE == 'PAGEMODE_PAGELOAD' || PAGEMODE == 'PAGEMODE_LIST' }">
						<div class="panel panel-default">
							<div class="panel-heading">
								<h1 class="panel-title">
									<c:choose>
										<c:when test="${ reportId == 'rpttrx' }">
											<span class="fa fa-connectdevelop fa-fw"></span>&nbsp;Transactions
										</c:when>
										<c:when test="${ reportId == 'rptmntr' }">
											<span class="fa fa-connectdevelop fa-fw"></span>&nbsp;Monitoring
										</c:when>
										<c:when test="${ reportId == 'rptmaster' }">
											<span class="fa fa-file-text-o fa-fw"></span>&nbsp;Master Data
										</c:when>
										<c:when test="${ reportId == 'rptadmin' }">
											<span class="glyphicon glyphicon-cog"></span>&nbsp;Admin Data
										</c:when>
										<c:when test="${ reportId == 'rpttax' }">
											<span class="fa fa-institution fa-fw"></span>&nbsp;Tax Reports
										</c:when>
										<c:otherwise>
											<span class=""></span>&nbsp;
										</c:otherwise>
									</c:choose>
								</h1>
							</div>
							<div class="panel-body">
		
								<c:choose>
									<c:when test="${ reportId == 'rpttrx' }">
										<div role="tabpanel">
											<ul class="nav nav-tabs" role="tablist">
												<li role="presentation" class="active"><a href="#poTab" aria-controls="poTab" role="tab" data-toggle="tab"><span class="fa fa-truck fa-fw"></span>&nbsp;Purchase Order</a></li>
												<li role="presentation" class=""><a href="#soTab" aria-controls="soTab" role="tab" data-toggle="tab"><span class="fa fa-cart-arrow-down fa-fw"></span>&nbsp;Sales Order</a></li>
											</ul>
											<div class="tab-content">
												<div role="tabpanel" class="tab-pane active" id="poTab">
													<br/>
													<table id="poListTable" class="table table-bordered table-hover dt-responsive display nowrap" style="width: 100%; border-collapse: separate;">
														<thead>
															<tr>
																<th>PO Date</th>
																<th>PO Code</th>
																<th>Supplier</th>
																<th>Total Amount</th>
																<th>Status</th>
																<th class="none">Items</th>
															</tr>
														</thead>
														<tbody>
															<c:if test="${ not empty poList }">
																<c:forEach items="${ poList }" var="p" varStatus="pIdx">
																	<tr>
																		<td>
																			<fmt:formatDate pattern="dd-MM-yyyy" value="${ p.poCreatedDate }" />
																		</td>
																		<td>
																			<c:out value="${ p.poCode }"/>
																		</td>
																		<td>
																			<c:out value="${ p.supplierLookup.supplierName }"/>
																		</td>
																		<td>
																			<fmt:parseNumber var="poTotal" integerOnly="true" type="number" value="0" />
																			<c:forEach items="${ p.itemsList }" var="i">
																				<fmt:parseNumber var="poTotal" integerOnly="true" type="number" value="${ poTotal + ( i.prodPrice * i.toBaseQty ) }" />
																			</c:forEach>
																			<fmt:formatNumber type="number" pattern="##,###.00" value="${ poTotal }"></fmt:formatNumber>
																		</td>
																		<td>
																			<spring:message code="${ p.statusLookup.i18nLookupValue }" text="${ p.poStatus }"/>
																		</td>
																		<td class="none">
																			<c:forEach items="${ p.itemsList }" var="i">
																				<c:out value="${ i.productLookup.productName }"/>
																			</c:forEach>
																		</td>
																	</tr>
																</c:forEach>
															</c:if>
														</tbody>
													</table>
												</div>
												<div role="tabpanel" class="tab-pane" id="soTab">
													<br/>
													<table id="soListTable" class="table table-bordered table-hover display dt-responsive nowrap" style="width: 100%; border-collapse: separate;">
														<thead>
															<tr>
																<th>Sales Code</th>
																<th>Sales Date</th>
																<th>Customer</th>
																<th>Total Amount</th>
															</tr>
														</thead>
														<tbody>
														</tbody>
													</table>
												</div>
											</div>
										</div>
									</c:when>
									<c:when test="${ reportId == 'rptmntr' }">
										<div role="tabpanel">
											<ul class="nav nav-tabs" role="tablist">
												<li role="presentation" class="active"><a href="#deliveryTab" aria-controls="deliveryTab" role="tab" data-toggle="tab"><span class="fa fa-connectdevelop fa-fw"></span>&nbsp;Delivery Status</a></li>
												<li role="presentation" class=""><a href="#stockTab" aria-controls="stockTab" role="tab" data-toggle="tab"><span class="fa fa-database fa-fw"></span>&nbsp;Stocks</a></li>
											</ul>
											<div class="tab-content">
												<div role="tabpanel" class="tab-pane active" id="deliveryTab">
													Delivery Status
												</div>
												<div role="tabpanel" class="tab-pane" id="stockTab">
													<br/>
													<table id="stockListTable" class="table table-bordered table-hover display dt-responsive nowrap" style="width: 100%; border-collapse: separate;">
														<thead>
															<tr>
																<th class="all">Product Name</th>
																<th>Inflow</th>
																<th>Outflow</th>
															</tr>
														</thead>
														<tbody>
															<c:if test="${ not empty stocksList }">
																<c:forEach items="${ stocksList }" var="s">
																	<tr>
																		<td class="all"><c:out value="${ s.productLookup.productName }"/></td>
																		<td>111</td>
																		<td class="none">222</td>
																	</tr>
																</c:forEach>
															</c:if>
														</tbody>
													</table>
												</div>
											</div>
										</div>
									</c:when>
									<c:when test="${ reportId == 'rpttax' }">
										<div class="row">
											<div class="col-md-12">
											</div>
										</div>
										<div class="row">
											<div class="col-md-12">
												<button type="button" class="btn">Download Faktur Pembelian</button>
												<button type="button" class="btn">Download Faktur Penjualan</button>
											</div>
										</div>
									</c:when>
									<c:when test="${ reportId == 'rptmaster' }">
										<div role="tabpanel">
											<ul class="nav nav-tabs" role="tablist">
												<li role="presentation" class="active"><a href="#customerTab" aria-controls="customerTab" role="tab" data-toggle="tab"><span class="fa fa-smile-o fa-fw"></span>&nbsp;Customer</a></li>
												<li role="presentation" class=""><a href="#supplierTab" aria-controls="supplierTab" role="tab" data-toggle="tab"><span class="fa fa-building-o fa-fw"></span>&nbsp;Supplier</a></li>
												<li role="presentation" class=""><a href="#productTab" aria-controls="productTab" role="tab" data-toggle="tab"><span class="fa fa-cubes fa-fw"></span>&nbsp;Product</a></li>
												<li role="presentation" class=""><a href="#truckTab" aria-controls="truckTab" role="tab" data-toggle="tab"><span class="fa fa-truck fa-flip-horizontal fa-fw"></span>&nbsp;Truck</a></li>
											</ul>
											<div class="tab-content">
												<div role="tabpanel" class="tab-pane active" id="customerTab">
													<br/>
													<table id="customerListTable" class="table table-bordered table-hover display responsive">
														<thead>
															<tr>
																<th>&nbsp;</th>
																<th>&nbsp;</th>
																<th>&nbsp;</th>
																<th>&nbsp;</th>
															</tr>
														</thead>
														<tbody>
														</tbody>
													</table>
												</div>
												<div role="tabpanel" class="tab-pane" id="supplierTab">
													<br/>
													<table id="supplierListTable" class="table table-bordered table-hover display responsive">
														<thead>
															<tr>
																<th>&nbsp;</th>
																<th>&nbsp;</th>
																<th>&nbsp;</th>
																<th>&nbsp;</th>
															</tr>
														</thead>
														<tbody>
														</tbody>
													</table>
												</div>
												<div role="tabpanel" class="tab-pane" id="productTab">
													<br/>
													<table id="productListTable" class="table table-bordered table-hover display responsive">
														<thead>
															<tr>
																<th>&nbsp;</th>
																<th>&nbsp;</th>
																<th>&nbsp;</th>
																<th>&nbsp;</th>
															</tr>
														</thead>
														<tbody>
														</tbody>
													</table>
												</div>
												<div role="tabpanel" class="tab-pane" id="truckTab">
													<br/>
													<table id="truckListTable" class="table table-bordered table-hover display responsive">
														<thead>
															<tr>
																<th></th>
																<th></th>
																<th></th>
																<th></th>
															</tr>
														</thead>
														<tbody>
														</tbody>
													</table>
												</div>
											</div>
										</div>
									</c:when>
									<c:when test="${ reportId == 'rptadmin' }">
										<div role="tabpanel">
											<ul class="nav nav-tabs" role="tablist">
												<li role="presentation" class="active"><a href="#userTab" aria-controls="userTab" role="tab" data-toggle="tab"><span class="fa fa-user fa-fw"></span>&nbsp;User</a></li>
												<li role="presentation" class=""><a href="#storeTab" aria-controls="storeTab" role="tab" data-toggle="tab"><span class="fa fa-umbrella fa-fw"></span>&nbsp;Store</a></li>
												<li role="presentation" class=""><a href="#roleTab" aria-controls="roleTab" role="tab" data-toggle="tab"><span class="fa fa-tree fa-fw"></span>&nbsp;Role</a></li>
												<li role="presentation" class=""><a href="#functionTab" aria-controls="functionTab" role="tab" data-toggle="tab"><span class="fa fa-minus-square fa-fw"></span>&nbsp;Function</a></li>
												<li role="presentation" class=""><a href="#lookupTab" aria-controls="lookupTab" role="tab" data-toggle="tab"><span class="fa fa-hand-o-up fa-fw"></span>&nbsp;Lookup</a></li>
											</ul>
											<div class="tab-content">
												<div role="tabpanel" class="tab-pane active" id="userTab">
													<br/>
													<table id="userListTable" class="table table-bordered table-hover display responsive">
														<thead>
															<tr>
																<th>&nbsp;</th>
																<th>&nbsp;</th>
																<th>&nbsp;</th>
																<th>&nbsp;</th>
															</tr>
														</thead>
														<tbody>
														</tbody>
													</table>
												</div>
												<div role="tabpanel" class="tab-pane" id="storeTab">
													<br/>
													<table id="storeListTable" class="table table-bordered table-hover display responsive">
														<thead>
															<tr>
																<th>&nbsp;</th>
																<th>&nbsp;</th>
																<th>&nbsp;</th>
																<th>&nbsp;</th>
															</tr>
														</thead>
														<tbody>
														</tbody>
													</table>
												</div>
												<div role="tabpanel" class="tab-pane" id="roleTab">
													<br/>
													<table id="roleListTable" class="table table-bordered table-hover display responsive">
														<thead>
															<tr>
																<th>&nbsp;</th>
																<th>&nbsp;</th>
																<th>&nbsp;</th>
																<th>&nbsp;</th>
															</tr>
														</thead>
														<tbody>
														</tbody>
													</table>
												</div>
												<div role="tabpanel" class="tab-pane" id="functionTab">
													<br/>
													<table id="functionListTable" class="table table-bordered table-hover display responsive">
														<thead>
															<tr>
																<th>&nbsp;</th>
																<th>&nbsp;</th>
																<th>&nbsp;</th>
																<th>&nbsp;</th>
															</tr>
														</thead>
														<tbody>
														</tbody>
													</table>
												</div>
												<div role="tabpanel" class="tab-pane" id="lookupTab">
													<br/>
													<table id="lookupListTable" class="table table-bordered table-hover display responsive">
														<thead>
															<tr>
																<th>&nbsp;</th>
																<th>&nbsp;</th>
																<th>&nbsp;</th>
																<th>&nbsp;</th>
															</tr>
														</thead>
														<tbody>
														</tbody>
													</table>
												</div>
											</div>
										</div>
									</c:when>
									<c:otherwise>
										<p>reportId not found.</p>
									</c:otherwise>
								</c:choose>
							</div>
						</div>
					</c:when>
					<c:otherwise>
						<p><a href="${ pageContext.request.contextPath }/report/gen/html/user" target="_blank">ppp</a></p>
					</c:otherwise>
				</c:choose>
			</div>
		</div>
		
		<jsp:include page="/WEB-INF/views/include/footer.jsp"></jsp:include>
	
	</div>
</body>
</html>
