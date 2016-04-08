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
			var salesCode = $('#searchSalesCode').val(); 
			
			$('#searchTableSelection').click(function() {
				$('#searchTableSelection').attr("href", ctxpath + "/sales/salescopy/view/" + $('#searchSalesCode').val());	
			});
		
			$('#cancelButton').click(function() {
		    	window.location.href = ctxpath + "/sales/salescopy";
			});
		    
		    $('#salesCopyTableList').DataTable();
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
					<span class="fa fa-cart-arrow-down fa-fw"></span>&nbsp;<spring:message code="so_sales_copy_jsp.title" text="Sales Copy"/>
				</h1>

				<c:choose>
					<c:when test="${PAGEMODE == 'PAGEMODE_LIST'}">
						<div class="panel panel-default">
							<div class="panel-heading">
								<h1 class="panel-title">
									<span class="fa fa-code-fork fa-fw fa-2x"></span>&nbsp;<spring:message code="so_sales_copy_jsp.subtitle" text="Sales Copy"/>
								</h1>
							</div>
							<div class="panel-body">
								<br />
								<div class="row">
									<div class="col-md-4">
							    		<input id="searchSalesCode" class="form-control" type="text" name="searchSalesCode" placeholder="Enter Sales Code" >
									</div>
									<div class="col-md-8">
										<a id="searchTableSelection" class="btn btn-sm btn-primary" href=""><span class="fa fa-edit fa-fw"></span>&nbsp;<spring:message code="" text="Search"/></a>
									</div>
								</div>
								<br />
							</div>
						</div>
					</c:when>
					<c:when test="${PAGEMODE == 'PAGEMODE_VIEW'}">
						<div class="panel panel-default">
							<div class="panel-heading">
								<h1 class="panel-title">
									<span class="fa fa-code-fork fa-fw fa-2x"></span>&nbsp;<spring:message code="so_sales_copy_jsp.subtitle" text="Sales Copy"/>
								</h1>
							</div>
							<div class="panel-body">
								<br />
								<div class="row">
									<div class="col-md-4">
								    	<input id="searchSalesCode" class="form-control" type="text" name="searchSalesCode" placeholder="Enter Sales Code" >
									</div>
									<div class="col-md-8">
										<a id="searchTableSelection" class="btn btn-sm btn-primary" href=""><span class="fa fa-edit fa-fw"></span>&nbsp;<spring:message code="" text="Search"/></a>
									</div>
								</div>
								<br />
								<table id="salesCopyTableList" class="table table-bordered table-hover display responsive">
									<thead>
										<tr>
											<th width="5%">&nbsp;</th>
											<th width="20%"><spring:message code="so_sales_copy_jsp.table.revise.header.sales_code" text="Sales Code"/></th>
											<th width="20%"><spring:message code="so_sales_copy_jsp.table.revise.header.sales_copy_count" text="Copy Count"/></th>
										</tr>
									</thead>
									<tbody>
										<c:if test="${ not empty SalesCopyList }">
											<c:forEach items="${ SalesCopyList }" var="i" varStatus="status">
												<tr>
													<td align="center"><input id="cbx_<c:out value="${ i.salesId }"/>" type="checkbox" value="<c:out value="${ i.salesId }"/>" /></td>
													<td><c:out value="${ i.salesCode }"></c:out></td>
													<td><c:out value=""></c:out></td>
												</tr>
											</c:forEach>
										</c:if>
									</tbody>
								</table>
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
