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
					<span class="fa fa-truck fa-fw"></span>&nbsp;Purchase Order
				</h1>

				<div class="panel panel-default">
					<div class="panel-heading">
						<h1 class="panel-title">
							<c:choose>
								<c:when test="${PAGEMODE == 'PAGEMODE_EDIT'}">
									<span class="fa fa-calculator fa-fw fa-2x"></span>&nbsp;PO Payment
								</c:when>
							</c:choose>
						</h1>
					</div>
					<div class="panel-body">
						<table id="poPaymentListTable" class="table table-bordered table-hover display responsive">
							<thead>
								<tr>
									<th width="10%">PO Code</th>
									<th width="15%">Supplier Name</th>
									<th width="15%">Date</th>
									<th width="20%">Total Price</th>
									<th width="5%">&nbsp;</th>
								</tr>
							</thead>
							<tbody>
								<c:if test="${not empty poList}">
									<c:forEach items="${ poList }" var="p" varStatus="pL">
										<tr>
											<td>
												<form:hidden path="${ p.poId }"/>
												<c:out value="${ p.poCode }"></c:out>
											</td>
											<td>
												<c:out value="${ p.supplierLookup.lookupValue }"></c:out>
											</td>
											<td>
											</td>
											<td>
											</td>
											<td>
											</td>
										</tr>
									</c:forEach>
								</c:if>
							</tbody>
						</table>
					</div>					
				</div>						
			</div>
		</div>
	</div>	
</body>
</html>
