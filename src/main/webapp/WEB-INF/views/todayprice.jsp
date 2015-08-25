<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
	<jsp:include page="/WEB-INF/views/include/headtag.jsp"></jsp:include>
	<script>
		$(document).ready(function() {
			var ctxpath = "${ pageContext.request.contextPath }";

			$('#cancelButton').click(function() {
				window.location.href(ctxpath + "/price/todayprice");
			});

			$('#updatePrice').click(function() {
				if ($('#selectedInputDate').val() == '') {
					jsAlert("Please select the date.");
					return false;
				} else {
					$('#updatePrice').attr("href", ctxpath + "/price/updateprice/" + $('#selectedInputDate').val());
				}
			});
			
			$('#selectedInputDate').datetimepicker({ format:'d-m-Y', timepicker:false });
			
			$('[id^="effectiveDate"]').datetimepicker({ format:'d-m-Y', timepicker:false });
			$('[id^="effectiveDate"]').on('dp.change dp.show',function(e) {
				$(this).parsley().validate();
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
					<span class="fa fa-dollar fa-fw"></span>&nbsp;Price
				</h1>

				<c:choose>
					<c:when test="${ PAGEMODE == 'PAGEMODE_LIST' }">
						<div class="panel panel-default">
							<div class="panel-heading">
								<h1 class="panel-title">
									<span class="fa fa-barcode fa-fw fa-2x"></span>&nbsp;Today Price
								</h1>
							</div>					
							<div class="panel-body">
								<div class="row">
									<div class="col-md-2">
										<label for="inputInputedDate" class="col-sm-2 control-label">Date</label>
										<div class="col-sm-5">
											<label for="inputInputedDate" class="col-sm-2 control-label"><fmt:formatDate pattern="dd-MM-yyyy" value="${ cal.time }" /></label>
										</div>										
									</div>
								</div>
								<div id="stockaccordion" class="panel-group">
									<c:forEach items="${ stocksList }" var="s" varStatus="sIdx">
										<div class="panel panel-default">
								            <div class="panel-heading" data-toggle="collapse" data-parent="#stockaccordion" data-target="#collapse_${ sIdx.index }">
		               							<h4 class="panel-title">
		                   							<a data-toggle="collapse" data-parent="#stockaccordion" href="#collapse_${ sIdx.index }">${ s.productLookup.productName }</a>
								                </h4>
							            	</div>
						            		<div class="panel-body" data-toggle="collapse" data-parent="#stockaccordion" data-target="#collapse_${ sIdx.index }">
						            			<div class="row">
						            				<div class="col-md-12">
						            					Market Price : <br/>
						            					Price : <br/>
						            				</div>
						            			</div>						            			
						            		</div>
							            	<div id="collapse_${ sIdx.index }" class="panel-collapse collapse">
							                    <table class="table">
							                    	<thead>
							                    		<tr>
							                    			<th colspan="10">
							                    				Price History
							                    			</th>
							                    		</tr>
							                    		<tr>
							                    			<th width="10%">
							                    				Input Date
							                    			</th>
							                    			<c:forEach items="${ s.priceList }" var="p" varStatus="pIdx">
																<th>
																	<c:out value="${ p.priceLevelEntity.priceLevelName }"/>
																</th>							                    			
							                    			</c:forEach>
							                    		</tr>
							                    	</thead>
							                    	<tbody>
								                    	<tr>
								                    		<td>
								                    			<fmt:formatDate pattern="dd-MM-yyyy" value="${ s.priceList[0].inputDate }" />
								                    		</td>
							                    			<c:forEach items="${ s.priceList }" var="p" varStatus="pIdx">
																<td>
																	<fmt:formatNumber type="number" pattern="##,###.00" value="${ p.price }"></fmt:formatNumber>
																</td>
							                    			</c:forEach>
								                    	</tr>
							                    	</tbody>
							                    </table>
							            	</div>
										</div>
									</c:forEach>
								</div>
								<br/>
								<div class="row">
									<div class="col-md-4">
										<input type="text" id="selectedInputDate" class="form-control input-sm" data-parsley-required="true"/>
									</div>
									<div class="col-md-8">
										<a id="updatePrice" class="btn btn-sm btn-primary" href=""><span class="fa fa-edit fa-fw"></span>&nbsp;Update Price</a>
									</div>
								</div>
							</div>
						</div>
					</c:when>
					<c:when test="${ PAGEMODE == 'PAGEMODE_EDIT' }">						
						<div class="panel panel-default">
							<div class="panel-heading">
								<h1 class="panel-title">
									<span class="fa fa-barcode fa-fw fa-2x"></span>&nbsp;Update Price 
								</h1>
							</div>								
							<div class="panel-body">
								<form:form id="todayPriceForm" role="form" class="form-horizontal" modelAttribute="todayPriceForm" action="${pageContext.request.contextPath}/price/saveprice" data-parsley-validate="parsley">									
									<div id="updateaccordion" class="panel-group">
										<c:forEach items="${ todayPriceForm.stocksList }" var="s" varStatus="sIdx">
											<div class="panel panel-default">
									            <div class="panel-heading" data-toggle="collapse" data-parent="#updateaccordion" data-target="#collapse_${ sIdx.index }">
			               							<h4 class="panel-title">
			                   							<a data-toggle="collapse" data-parent="#updateaccordion" href="#collapse_${ sIdx.index }">${ s.productLookup.productName }</a>
									                </h4>
								            	</div>
								            	<div id="collapse_${ sIdx.index }" class="panel-collapse collapse in">
					                    			<div class="panel-body">
						                    			<c:forEach items="${ s.priceList }" var="p" varStatus="pIdx">
															<div class="form-group">
																<label for="stocksList[${ sIdx.index }].priceList[${ pIdx.index }]" class="col-md-2 control-label"><c:out value="${ todayPriceForm.stocksList[ sIdx.index ].priceList[ pIdx.index ].priceLevelEntity.priceLevelName }"/></label>
																<div class="col-md-5">
																	<form:input id="stocksList[${ sIdx.index }].priceList[${ pIdx.index }]" class="form-control" path="stocksList[${ sIdx.index }].priceList[${ pIdx.index }].price" data-parsley-required="true"/>
																</div>
															</div>
						                    			</c:forEach>
					                    			</div>
								            	</div>
											</div>
										</c:forEach>
									</div>
									<div class="row">
										<div class="col-md-7 col-offset-md-5">
											<div class="btn-toolbar">
												<button id="cancelButton" type="reset" class="btn btn-primary pull-right"><spring:message code="common.cancel_button" text="Cancel"/></button>
												<button id="submitButton" type="submit" class="btn btn-primary pull-right"><spring:message code="common.submit_button" text="Submit"/></button>
											</div>
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
