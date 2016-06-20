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

			$('[id^="deliverButton_"]').click(function(event) {
				var salesId = $(this).attr('id').replace('deliverButton_', '');
				var result = true;
				
				if (result) {
					window.location = ctxpath + "/customer/delivery/confirmation/loaddeliver/" + salesId;
				}
			});
						
			$('#cancelButton').click(function() {				
				window.location = ctxpath + "/customer/delivery/confirmation/";
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
				<c:if test="${ ERRORFLAG == 'ERRORFLAG_SHOW'  }">
	    			<div class="alert alert-danger alert-dismissible" role="alert">
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
					<span class="fa fa-smile-o fa-fw"></span>&nbsp;<spring:message code="customer_menu_jsp.title" text="Customer"/>
				</h1>
				
				<c:choose>
					<c:when test="${ PAGEMODE == 'PAGEMODE_PAGELOAD' || PAGEMODE == 'PAGEMODE_LIST' }">
						<div class="panel panel-default">
							<div class="panel-heading">
								<h1 class="panel-title">
									<span class="fa fa-check fa-fw fa-2x"></span>&nbsp;<spring:message code="customer_menu_jsp.subtitle" text="Customer Delivery"/>
								</h1>
							</div>
							<div class="panel-body">
								<br/>
								<table id="outflowTable" class="table table-bordered table-hover display responsive">
									<thead>
										<tr>
											<th rowspan="2"><spring:message code="customer_menu_jsp.table.header.sales_code" text="Sales Code"/></th>
											<th rowspan="2"><spring:message code="customer_menu_jsp.table.header.shipping_date" text="Shipping Date"/></th>											
											<th rowspan="2"><spring:message code="customer_menu_jsp.table.header.deliver_date" text="Deliver Date"/></th>
											<th colspan="2"><spring:message code="customer_menu_jsp.table.header.items_list" text="items list"/></th>
											<th rowspan="2"></th>
										</tr>
										<tr>
											<th><spring:message code="customer_menu_jsp.table.header.items_list.product_name" text="Product Name"/></th>
											<th><spring:message code="customer_menu_jsp.table.header.items_list.bruto" text="Bruto"/></th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${ CustomerMenuList }" var="cm" varStatus="cmIdx">
									    		<tr>
										    		<td><c:out value="${ cm.salesCode }"/></td>
										    		<td><fmt:formatDate pattern="dd MMM yyyy" value="${ cm.shippingDate }"/></td>											    	
										    		<td>
										    			<c:out value="${ cm.itemsList[0].deliverList[0].deliverDate }"/>
										    		</td>
													<td>
														<c:forEach items="${ cm.itemsList }" var="iL" varStatus="iLIdx">
															<c:out value="${ cm.itemsList[iLIdx.index].productEntity.productName }"/>
											   				<br />
											   			</c:forEach>
										   			</td>
												   	<td align="right">
												   		<c:forEach items="${ cm.itemsList }" var="iLx" varStatus="iLxIdx">
															<c:out value="${ cm.itemsList[iLxIdx.index].deliverList[0].bruto }"/>
												   			<c:out value="${ cm.itemsList[iLxIdx.index].unitCodeLookup.lookupValue }"/>
										    				<br />
											   			</c:forEach>
										   			</td>
													<td class="center-align">
											    		<button type="button" class="btn btn-xs btn-primary" id="deliverButton_${ cm.salesId }" value="${ cm.salesId }"><span class="fa fa-edit fa-fw"></span></button>
												    </td>
											    </tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
						</div>
					</c:when>
					<c:when test="${ PAGEMODE == 'PAGEMODE_EDIT' }">
						<div class="panel panel-default">
							<div class="panel-heading">
								<h1 class="panel-title">
									<span class="fa fa-check fa-fw fa-2x"></span>&nbsp;<spring:message code="customer_menu_jsp.submit_so_detail" text="Submit Sales Order Detail"/>
								</h1>
							</div>
							<div class="panel-body">
								<form:form id="customerMenuSOForm" role="form" class="form-horizontal" modelAttribute="customerMenuSOForm" action="${pageContext.request.contextPath}/customer/delivery/confirmation/savedeliver/${ customerMenuSOForm.salesId }" data-parsley-validate="parsley">
									<div class="form-group">
										<label for="inputPoCode" class="col-sm-2 control-label"><spring:message code="customer_menu_jsp.sales_code" text="Sales Code"/></label>
										<div class="col-sm-3">
											<input class="form-control" value="${ customerMenuSOForm.salesCode }" readonly="readonly"/>
										</div>
									</div>
									<div class="form-group">
										<label for="inputShippingDate" class="col-sm-2 control-label"><spring:message code="customer_menu_jsp.shipping_date" text="Shipping Date"/></label>
										<div class="col-sm-5">
											<fmt:formatDate pattern="dd MMM yyyy" value="${ customerMenuSOForm.shippingDate }" var="formattedShippingDate"/>
											<input class="form-control" value="${ formattedShippingDate }" readonly="readonly"/>
										</div>
									</div>
									<div class="form-group">
										<label for="inputProductName" class="col-sm-2 control-label"><spring:message code="customer_menu_jsp.product" text="Product"/></label>
										<div class="col-sm-10">
											<div class="table-responsive">
												<table class="table">
													<thead>
														<tr>
															<th><spring:message code="customer_menu_jsp.table.header.product" text="Product"/></th>
															<th><spring:message code="customer_menu_jsp.table.header.quantity" text="Quantity"/></th>
															<th width="15%"><spring:message code="customer_menu_jsp.table.header.bruto" text="Bruto"/></th>
															<th width="15%"><spring:message code="customer_menu_jsp.table.header.netto" text="Netto"/></th>
															<th width="20%"><spring:message code="customer_menu_jsp.table.header.unit" text="Unit"/></th>
														</tr>
													</thead>
													<tbody>
														<c:forEach items="${ customerMenuSOForm.itemsList }" var="iL" varStatus="itIdx">
															<tr>
																<td>
																	<form:hidden path="itemsList[${ itIdx.index }].itemsId"/>
																	<form:hidden path="itemsList[${ itIdx.index }].deliverList[0].deliverItemsEntity.itemsId"/>
																	<form:hidden path="itemsList[${ itIdx.index }].deliverList[0].deliverStoreEntity.storeId"/>
																	<c:out value="${ iL.productEntity.productName }"/><c:out value="${ customerMenuSOForm.itemsList.size() }"/>
																</td>
																<td><c:out value="${ iL.prodQuantity }"/>&nbsp;<c:out value="${ iL.unitCodeLookup.lookupValue }"/></td>
																<td>
																	<c:out value="${ customerMenuSOForm.itemsList[itIdx.index].deliverList[0].bruto }"/>&nbsp;<c:out value="${ iL.unitCodeLookup.lookupValue }"/>
																</td>
																<td><form:input class="form-control" path="itemsList[${ itIdx.index }].deliverList[0].net" data-parsley-required="true"></form:input></td>
																<td>
																	<form:select class="form-control" path="itemsList[${ itIdx.index }].unitCodeLookup.lookupKey" data-parsley-required="true" data-parsley-trigger="change" readonly="false" disabled="true">
																		<option value=""><spring:message code="common.please_select" text="Please Select"/></option>
																		<c:forEach items="${ cmDDL }" var="cm">
																			<form:option value="${ cm.lookupKey }"><spring:message code="${ cm.i18nLookupValue }"></spring:message></form:option>
																		</c:forEach>
																	</form:select>
																</td>
															</tr>
														</c:forEach>
													</tbody>
												</table>
											</div>
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
		
		<jsp:include page="/WEB-INF/views/include/footer.jsp"></jsp:include>
		
	</div>
</body>
</html>
