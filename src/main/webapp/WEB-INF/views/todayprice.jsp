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
				window.location.href = ctxpath + "/price/todayprice";
			});

			$('#updatePrice').click(function() {
				if ($('#selectedInputDate').val() == '') {
					jsAlert("Please select the date.");
					return false;
				} else {
					$('#updatePrice').attr("href", ctxpath + "/price/updateprice/" + $('#selectedInputDate').val().replace(' ', '_'));
				}
			});
			
			$('input[id^="marketPrice_"]').on('input', function() {
				var stockIdx = $(this).attr('id').replace('marketPrice_', '').split('_')[0]; 
				var priceIdx = $(this).attr('id').replace('marketPrice_', '').split('_')[1];
				var inputMarketPrice = $(this).val();
				
				$('#marketPriceHidden_' + stockIdx + '_' + priceIdx + ' input').each(function(idx, itm) {
					$(this).val(inputMarketPrice);
				});
				
				if ($.isNumeric(inputMarketPrice)) {
					calculatePrice(stockIdx, inputMarketPrice);	
				}
			});
			
			$('#selectedInputDate').datetimepicker({ format:'d-m-Y H:i' });
			
			function calculatePrice(sIdx, marketPrice) {
				var inptL = $('#priceList_' + sIdx + ' input[type="text"]').size();
				var result = 0;
				
				for(var i=0; i<inptL; i++) {
					if ($('#priceLevelType_' + sIdx + '_' + i).val() == 'L022_INC') {
						result = parseInt(marketPrice) + parseInt($('#priceLevelInc_' + sIdx + '_' + i).val());
					} else {
						console.log();

						result = parseInt(marketPrice) + (parseInt(marketPrice) * (parseInt($('#priceLevelPct_' + sIdx + '_' + i).val()) / 100));
					}
					$('input[name="stocksList[' + sIdx + '].priceList[' + i + '].price"]').val(result);
				}
			}

			window.Parsley.addValidator('mindate', function(value, requirement) {
				var timestamp = Date.parse(value);
		        var minTs = Date.parse(requirement);

		        return isNaN(timestamp) ? false : timestamp > minTs;    
		    }, 32)
		    .addMessage('en', 'mindate', 'This date should be greater than %s')
		    .addMessage('in', 'mindate', 'Tanggal harus lebih dari %s');
			
			$('button[id^=partialButton_]').click(function() {
				var stockId = $(this).val();
				if ($('#todayPriceForm').parsley().isValid('s' + stockId)) {
					$('#todayPriceForm').parsley().destroy();
					$('#todayPriceForm').attr('action', ctxpath + "/updateprice/partial/" + stockId);	
				}
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
					<span class="fa fa-dollar fa-fw"></span>&nbsp;<spring:message code="today_price_jsp.title" text="Price"></spring:message>
				</h1>

				<c:choose>
					<c:when test="${ PAGEMODE == 'PAGEMODE_LIST' }">
						<div class="panel panel-default">
							<div class="panel-heading">
								<h1 class="panel-title">
									<span class="fa fa-barcode fa-fw fa-2x"></span>&nbsp;<spring:message code="today_price_jsp.price_list" text="Today Price"></spring:message>
								</h1>
							</div>
							<div class="panel-body">
								<div id="stockaccordion" class="panel-group">
									<c:forEach items="${ stocksList }" var="s" varStatus="sIdx">
										<div class="panel panel-default">
								            <div class="panel-heading" data-toggle="collapse" data-parent="#stockaccordion" data-target="#collapse_${ sIdx.index }">
		               							<h4 class="panel-title">
		                   							<a data-toggle="collapse" data-parent="#stockaccordion" href="#collapse_${ sIdx.index }">${ s.productEntity.productName }</a>
								                </h4>
							            	</div>
						            		<table class="table">
						            			<thead>
						                    		<tr>
						                    			<th><spring:message code="today_price_jsp.current_table.header.date" text="Date"/></th>
						                    			<c:forEach items="${ priceLevelList }" var="pLL" varStatus="pLLIdx">
						                    				<th>
						                    					<c:out value="${ pLL.priceLevelName }"/>
						                    				</th>
						                    			</c:forEach>
						                    		</tr>
						            			</thead>
						            			<tbody>
						            				<c:if test="${ not empty s.priceList }">
							                    		<c:forEach items="${ s.priceList }" var="p" varStatus="pIdx">
						                    				<fmt:formatDate pattern="dd-MM-yyyy hh:mm" value="${ p.inputDate }" var="pDate"/>
						                    				<c:if test="${ pIdx.index == 0 }">					                    				
									                    		<tr>
									                    			<td>
									                    				<fmt:formatDate pattern="dd-MM-yyyy hh:mm" value="${ p.inputDate }"/>
									                    			</td>
									                    			<c:forEach items="${ s.priceList }" var="pL">
									                    				<fmt:formatDate pattern="dd-MM-yyyy hh:mm" value="${ pL.inputDate }" var="ppDate"/>
									                    				<c:if test="${ pDate eq ppDate }">
									                    					<td>
									                    						<c:out value="${ pL.price }"/>
									                    					</td>
									                    				</c:if>
									                    			</c:forEach>
									                    		</tr>								                    		
									                    	</c:if>
									                    </c:forEach>
								                    </c:if>
						            			</tbody>
						            		</table>
						            		<div class="panel-body" data-toggle="collapse" data-parent="#stockaccordion" data-target="#collapse_${ sIdx.index }">
						            			<div class="row">
						            				<div class="col-md-12">
														<spring:message code="today_price_jsp.price_history" text="Price History"/>
						            				</div>
						            			</div>
						            		</div>

							            	<div id="collapse_${ sIdx.index }" class="panel-collapse collapse">
							                    <table class="table">
							                    	<thead>
							                    		<tr>
							                    			<th><spring:message code="today_price_jsp.history_table.header.date" text="Date"/></th>
							                    			<c:forEach items="${ priceLevelList }" var="pLL" varStatus="pLLIdx">
							                    				<th>
							                    					<c:out value="${ pLL.priceLevelName }"/>
							                    				</th>
							                    			</c:forEach>
							                    		</tr>
							                    	</thead>
							                    	<tbody>
							                    		<c:if test="${ not empty s.priceList }">
							                    			<c:forEach items="${ s.priceList }" var="p" varStatus="pIdx">
							                    				<fmt:formatDate pattern="dd-MM-yyyy hh:mm" value="${ p.inputDate }" var="pDate"/>
							                    				<c:choose>
								                    				<c:when test="${ pIdx.index == 0 }">
										                    		</c:when>
										                    		<c:when test="${ cDate ne pDate }">
											                    		<tr>
											                    			<td>
											                    				<fmt:formatDate pattern="dd-MM-yyyy hh:mm" value="${ p.inputDate }"/>
											                    			</td>
											                    			<c:forEach items="${ s.priceList }" var="pL">
											                    				<fmt:formatDate pattern="dd-MM-yyyy hh:mm" value="${ pL.inputDate }" var="ppDate"/>
											                    				<c:if test="${ pDate eq ppDate }">
											                    					<td>
											                    						<c:out value="${ pL.price }"/>
											                    					</td>
											                    				</c:if>
											                    			</c:forEach>
											                    		</tr>
										                    		</c:when>
							                    				</c:choose>
							                    				<fmt:formatDate pattern="dd-MM-yyyy hh:mm" value="${ p.inputDate }" var="cDate"/>
							                    			</c:forEach>
														</c:if>
							                    	</tbody>
							                    </table>
							            	</div>
										</div>
									</c:forEach>
								</div>
								<br/>
								<div class="row">
									<div class="col-md-4">
										<input type="text" id="selectedInputDate" class="form-control input-sm" data-parsley-required="true" data-parsley-mindate="true"/>
									</div>
									<div class="col-md-8">
										<a id="updatePrice" class="btn btn-sm btn-primary" href=""><span class="fa fa-edit fa-fw"></span>&nbsp;<spring:message code="today_price_jsp.update_price_button" text="Update Price"/></a>
									</div>
								</div>
							</div>
						</div>
					</c:when>
					<c:when test="${ PAGEMODE == 'PAGEMODE_EDIT' }">						
						<div class="panel panel-default">
							<div class="panel-heading">
								<h1 class="panel-title">
									<span class="fa fa-barcode fa-fw fa-2x"></span>&nbsp;<spring:message code="today_price_jsp.update_price" text="Update Price"/>
								</h1>
							</div>
							<div class="panel-body">
								<form:form id="todayPriceForm" role="form" class="form-horizontal" modelAttribute="todayPriceForm" action="${pageContext.request.contextPath}/price/saveprice" data-parsley-validate="parsley">									
									<div id="updateaccordion" class="panel-group">
										<c:forEach items="${ todayPriceForm.stocksList }" var="s" varStatus="sIdx">
											<form:hidden path="stocksList[${ sIdx.index }].stocksId"/>
											<div class="panel panel-default">
									            <div class="panel-heading" data-toggle="collapse" data-parent="#updateaccordion" data-target="#collapse_${ sIdx.index }">
			               							<h4 class="panel-title">
			                   							<a data-toggle="collapse" data-parent="#updateaccordion" href="#collapse_${ sIdx.index }">${ s.productEntity.productName }</a>
									                </h4>
								            	</div>
								            	<div id="collapse_${ sIdx.index }" class="panel-collapse collapse in">
					                    			<div class="panel-body">
														<div class="form-horizontal">
															<div class="form-group">
																<label for="inputDate" class="col-md-2 control-label"><spring:message code="today_price_jsp.input_date" text="Input Date"/></label>
																<div class="col-md-10">
																	<label class="control-label">
																		<fmt:formatDate pattern="dd-MM-yyyy hh:mm" value="${ todayPriceForm.stocksList[sIdx.index].priceList[0].inputDate }" />
																		<c:forEach items="${ s.priceList }" var="p" varStatus="pIdx">
																			<fmt:formatDate pattern="dd-MM-yyyy hh:mm" value="${ todayPriceForm.stocksList[sIdx.index].priceList[pIdx.index].inputDate }" var="iDate"/>
																			<form:hidden path="stocksList[${ sIdx.index }].priceList[${ pIdx.index }].inputDate"/>
																		</c:forEach>
																	</label>
																</div>
															</div>
															<div class="form-group">
																<label for="marketPrice" class="col-md-2 control-label"><spring:message code="today_price_jsp.market_price" text="Market Price"/></label>
																<div class="col-md-3">
																	<input type="text" id="marketPrice_${ sIdx.index }_${ sIdx.index }" class="form-control text-right" data-parsley-required="true" data-parsley-group="s${ sIdx.index }"/>
																	<div id="marketPriceHidden_${ sIdx.index }_${ sIdx.index }">
																		<c:forEach items="${ s.priceList }" var="p" varStatus="pIdx">
																			<form:hidden path="stocksList[${ sIdx.index }].priceList[${ pIdx.index }].marketPrice"/>
																		</c:forEach>
																	</div>
																</div>
															</div>
															<div class="form-group">
																<label for="price" class="col-md-2 control-label"><spring:message code="today_price_jsp.price" text="Price"/></label>
																<div class="col-md-10">
																	<div class="form-inline">
																		<div id="priceList_${ sIdx.index }">
																			<c:forEach items="${ s.priceList }" var="p" varStatus="pIdx">
																				<div class="form-group">
																					<label for="sr-only"></label>
																					<div class="col-md-10">
																						<c:choose>
																							<c:when test="${ todayPriceForm.stocksList[ sIdx.index ].priceList[ pIdx.index ].priceLevelEntity.priceLevelTypeLookup.lookupKey == 'L022_INC' }">
																								<c:set var="tooltipTitle" value="Type: ${ todayPriceForm.stocksList[ sIdx.index ].priceList[ pIdx.index ].priceLevelEntity.priceLevelTypeLookup.lookupValue }&#013;Type: ${ todayPriceForm.stocksList[ sIdx.index ].priceList[ pIdx.index ].priceLevelEntity.incrementValue }"></c:set>	
																							</c:when>
																							<c:otherwise>
																								<c:set var="tooltipTitle" value="Type: ${ todayPriceForm.stocksList[ sIdx.index ].priceList[ pIdx.index ].priceLevelEntity.priceLevelTypeLookup.lookupValue }&#013;Type: ${ todayPriceForm.stocksList[ sIdx.index ].priceList[ pIdx.index ].priceLevelEntity.percentageValue }%"></c:set>
																							</c:otherwise>
																						</c:choose>
																						<form:input class="form-control" path="stocksList[${ sIdx.index }].priceList[${ pIdx.index }].price" aria-describedby="helpBlock${ sIdx.index }${ pIdx.index }" data-parsley-required="true" data-parsley-group="s${ sIdx.index }"/>
																						<span class="help-block" data-toggle="tooltip" data-placement="top" title="${ tooltipTitle }">Level : <c:out value="${ todayPriceForm.stocksList[ sIdx.index ].priceList[ pIdx.index ].priceLevelEntity.priceLevelName }"/></span>
																						<form:hidden id="priceLevelType_${ sIdx.index }_${ pIdx.index }" path="stocksList[${ sIdx.index }].priceList[${ pIdx.index }].priceLevelEntity.priceLevelTypeLookup.lookupKey"/>
																						<form:hidden id="priceLevelInc_${ sIdx.index }_${ pIdx.index }" path="stocksList[${ sIdx.index }].priceList[${ pIdx.index }].priceLevelEntity.incrementValue"/>
																						<form:hidden id="priceLevelPct_${ sIdx.index }_${ pIdx.index }" path="stocksList[${ sIdx.index }].priceList[${ pIdx.index }].priceLevelEntity.percentageValue"/>
																						<form:hidden path="stocksList[${ sIdx.index }].priceList[${ pIdx.index }].priceLevelEntity.priceLevelId"/>
																					</div>
																				</div>
																			</c:forEach>
																		</div>
																	</div>
																</div>
															</div>														
														</div>
						                    		</div>
													<div class="panel-footer">
														<div class="btn-toolbar">
														<button id="partialButton_${ sIdx.index }" type="submit" class="btn btn-xs btn-primary" value="${ sIdx.index }"><spring:message code="today_price_jsp.update_partial_button" text="Partial Update"/></button>
														</div>
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
