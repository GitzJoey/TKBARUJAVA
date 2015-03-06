<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
					<span class="fa fa-bar-chart-o fa-fw"></span>&nbsp;Reports
				</h1>

				<div class="panel panel-default">
					<div class="panel-heading">
						<h1 class="panel-title">
							<c:choose>
								<c:when test="${ reportId == 'rpt1' }">
								</c:when>
								<c:when test="${ reportId == 'rptmaster' }">
									<span class="fa fa-file-text-o fa-fw"></span>&nbsp;Master Data
								</c:when>
								<c:when test="${ reportId == 'rptadmin' }">
									<span class="glyphicon glyphicon-cog"></span>&nbsp;Admin Data
								</c:when>
								<c:otherwise>
									<span class=""></span>&nbsp;
								</c:otherwise>
							</c:choose>
						</h1>
					</div>
					<div class="panel-body">

						<c:choose>
							<c:when test="${ reportId == 'rpt1' }">
								<a href="${ pageContext.request.contextPath }/report/gen/html/user" target="_blank">ppp</a>
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
											customer
										</div>
										<div role="tabpanel" class="tab-pane" id="supplierTab">
											supplier
										</div>
										<div role="tabpanel" class="tab-pane" id="productTab">
											product
										</div>
										<div role="tabpanel" class="tab-pane" id="truckTab">
											truck
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
											user
										</div>
										<div role="tabpanel" class="tab-pane" id="storeTab">
											store
										</div>
										<div role="tabpanel" class="tab-pane" id="roleTab">
											role
										</div>
										<div role="tabpanel" class="tab-pane" id="functionTab">
											function
										</div>
										<div role="tabpanel" class="tab-pane" id="lookupTab">
											lookup
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
			</div>
		</div>
	</div>	
</body>
</html>
