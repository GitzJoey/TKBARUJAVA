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
					<span class="fa fa-cart-arrow-down fa-fw"></span>&nbsp;Sales Order
				</h1>
				
				<div class="panel panel-default">
					<div class="panel-heading">
						<h1 class="panel-title">
							<c:choose>
								<c:when test="${PAGEMODE == 'PAGEMODE_ADD'}">
									<span class="fa fa-cart-arrow-down fa-fw fa-2x"></span>&nbsp;New Sales Order
								</c:when>
							</c:choose>
						</h1>
					</div>
					<div class="panel-body">
						<form:form id="soForm" role="form" class="form-horizontal" modelAttribute="soForm" action="${pageContext.request.contextPath}/so/save">
							<div class="panel panel-default">
								<div class="panel-body">
									<div class="row">
										<div class="col-md-11">
										</div>
										<div class="col-md-1 pull-right">
											<button id="searchButton" type="submit" class="btn btn-primary">Search</button>
										</div>
									</div>
								</div>
							</div>							
							<div role="tabpanel">
								<ul class="nav nav-tabs" role="tablist">
									<li role="presentation" class="active"><a href="#soTab" aria-controls="soTab" role="tab" data-toggle="tab"><span class="fa fa-plus fa-fw"></span>&nbsp;New Sales</a></li>
								</ul>

								<div class="tab-content">
									<div role="tabpanel" class="tab-pane active" id="soTab">
										<br/>
										<form:hidden path="salesId" />
										<div class="row">
											<div class="col-md-12">
												<div class="panel panel-default">
													<div class="panel-body">
														<div class="row">
															<div class="col-md-7">
																<div class="form-group">
																	<label for="inputSalesCode" class="col-sm-2 control-label">Sales Code</label>
																	<div class="col-sm-5">
																		<form:input type="text" class="form-control" id="inputSalesCode" name="inputSalesCode" path="salesCode" placeholder="Enter Sales Code"></form:input>
																	</div>										
																</div>
																<div class="form-group">
																	<label for="inputSalesType" class="col-sm-2 control-label">Sales Type</label>
																	<div class="col-sm-8">
		
																	</div>										
																</div>
																<div class="form-group">
																	<label for="inputCustomerId" class="col-sm-2 control-label">Customer</label>
																	<div class="col-sm-9">
																		<form:input type="text" class="form-control" id="inputCustomerId" name="inputCustomerId" path="customerId" placeholder="Enter Customer Name"></form:input>
																	</div>
																	<div class="col-sm-1">
																		<button id="customerTooltip" type="button" class="btn btn-default" data-toggle="tooltip" data-trigger="hover" data-html="true" data-placement="right" data-title=""><span class="fa fa-external-link fa-fw"></span></button>
																	</div>										
																</div>
															</div>
															<div class="col-md-5">
																<div class="form-group">
																	<label for="inputSalesDate" class="col-sm-3 control-label">Sales Date</label>
																	<div class="col-sm-9">
																		<form:input type="text" class="form-control" id="inputSalesDate" name="inputSalesDate" path="salesCreatedDate" placeholder="Enter Sales Date"></form:input>
																	</div>										
																</div>
																<div class="form-group">
																	<label for="inputSalesStatus" class="col-sm-3 control-label">Status</label>
																	<div class="col-sm-9">
																		<label id="inputSalesStatus" class="control-label"><c:out value="${ soForm.statusLookup.lookupValue }"></c:out></label>				
																	</div>										
																</div>
															</div>
														</div>
														<hr>
														<div class="row">
															<div class="col-md-7">
																<div class="form-group">
																	<label for="inputShippingDate" class="col-sm-2 control-label">Shipping Date</label>
																	<div class="col-sm-5">
																		<form:input type="text" class="form-control" id="inputShippingDate" name="inputShippingDate" path="shippingDate" placeholder="Enter Shipping Date"></form:input>
																	</div>										
																</div>
															</div>
														</div>
													</div>
												</div>	
											</div>
										</div>

									</div>
								</div>
							</div>													
						</form:form>
					</div>					
				</div>						
			</div>
		</div>
	</div>	
</body>
</html>
