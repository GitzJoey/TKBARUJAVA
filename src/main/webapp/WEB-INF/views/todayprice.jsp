<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
	<jsp:include page="/WEB-INF/views/include/headtag.jsp"></jsp:include>
	<script>
		$(document).ready(function() {
			var ctxpath = "${ pageContext.request.contextPath }";
			
			$('#editTableSelection').click(function() {
				var id = "";
				var button = $(this).attr('id');

				$('input[type="checkbox"][id^="cbx_"]').each(function(index, item) {
					if ($(item).prop('checked')) {
						id = $(item).attr("value");
					}
				});
				if (id == "") {
					jsAlert("Please select at least 1 product");
					return false;
				} else {
					if (button == 'editTableSelection') {
						$('#editTableSelection').attr("href", ctxpath + "/price/updateprice/" + id);
					} else {
						return false;
					}
				}
			});	
			
			$('#addPriceButton, #removePriceButton').click(
					function() {
						var id = "";
						var button = $(this).attr('id');

						if (button == 'addPriceButton') {
						
								$('#todayPriceForm').attr('action',ctxpath + "/price/addprice/");
		                    
						} else {
							id = $(this).val();
							$('#todayPriceForm').attr('action',ctxpath + "/price/removeprice/" + id);
						}
			});
			
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

				<div class="panel panel-default">
					<div class="panel-heading">
						<h1 class="panel-title">
							<span class="fa fa-barcode fa-fw fa-2x"></span>Today Price
						</h1>
					</div>
					
					<c:choose>
					<c:when test="${PAGEMODE == 'PAGEMODE_LIST'}">
					<div class="panel-body">
					
						
								<div class="table-responsive">
									<table class="table table-bordered table-hover">
										<thead>
											<tr>
												<th width="5%">&nbsp;</th>
												<th width="20%">Product</th>
												<th width="20%">Price</th>
												
											</tr>
										</thead>
										<tbody>
											<c:if test="${not empty productList}">
												<c:forEach items="${ productList }" var="p" varStatus="status">
													<tr>
														<td align="center"><input id="cbx_<c:out value="${ p.productId }"/>" type="checkbox" value="<c:out value="${ p.productId }"/>" /></td>
														<td><c:out value="${ p.productName }"></c:out></td>
														<td>
															<c:if test="${not empty p.price }">
																<c:forEach var="price" items="${ p.price }">
																	<c:out value="${ price.levelId }"></c:out> <c:out value="${ price.price }"></c:out>
																</c:forEach>
															</c:if>
														</td>
														
													</tr>
												</c:forEach>
											</c:if>
										</tbody>
									</table>
								</div>
								<a id="editTableSelection" class="btn btn-sm btn-primary" href=""><span class="fa fa-edit fa-fw"></span>&nbsp;Update Price</a>
							
					</div>
					</c:when>
					<c:when test="${PAGEMODE == 'PAGEMODE_EDIT'}">						
						<div class="panel panel-default">
							<div class="panel-heading">
								<h1 class="panel-title">
									<span class="fa fa-code-fork fa-fw fa-2x"></span>&nbsp;Today Price List
								</h1>
							</div>								
							<div class="panel-body">
								<form:form id="todayPriceForm" role="form" class="form-horizontal" modelAttribute="todayPriceForm" action="${pageContext.request.contextPath}/price/save">
									<div class="row">
										<div class="col-md-12">
											<div class="panel panel-default">
												<div class="panel-body">
													<div class="row">
														<div class="col-md-7">
															<div class="form-group">
																<label for="inputSalesCode" class="col-sm-2 control-label">Product Name</label>
																<div class="col-sm-5">
																	<form:hidden path="productId"/>
																	<form:input type="text" class="form-control" id="inputProductName" name="inputProductName" path="productName" placeholder="Enter Sales Code" readonly="true"></form:input>
																
																</div>										
															</div>
															
															
														</div>
														
													</div>
													
																	
												</div>
											</div>
											<div class="row">
												<div class="col-md-12">
													<div class="panel panel-default">
														<div class="panel-heading">
															<h1 class="panel-title">
																Price
															</h1>
														</div>
														<div class="panel-body">
															
															<div class="row">
																<div class="col-md-12">
																
																<button id="addPriceButton" type="submit" class="btn btn-primary pull-left">
																	<span class="fa fa-plus"></span>
																</button>
																
																
																	<table id="priceListTable" class="table table-bordered table-hover display responsive">
																		<thead>
																			<tr>
																				<th width="30%">Price</th>
																				<th width="20%">Price Level</th>
																				<th width="30%">Effective Date</th>
																				<th width="15%">Active</th>
																				<th width="5%">&nbsp;</th>
																				
																			</tr>
																		</thead>
																		<tbody>
																		
																			<c:forEach items="${ todayPriceForm.price }" var="iL" varStatus="iLIdx">
																				<tr>
																					<td style="vertical-align: middle;">
																					    <form:hidden path="price[${ iLIdx.index }].priceId"/>
																						<form:input type="text" size="15" class="form-control text-right" id="inputPrice" name="inputPrice" path="price[${ iLIdx.index }].price" placeholder="Enter Price" data-parsley-type="number" data-parsley-trigger="keyup"></form:input>
																					</td>
																					<td>
																						<form:select path="price[${ iLIdx.index }].levelId" class="form-control" id="inputPriceLevel" name="inputPriceLevel">
																							<option value="">Please Select</option>
																							<form:options items="${ priceLevelDDL }" itemValue="priceLevelId" itemLabel="levelName" />
																						</form:select>
																					</td>
																					<td><form:input type="text" size="5" class="form-control" id="effectiveDate_${ iLIdx.index }" name="effectiveDate_${ iLIdx.index }" path="price[${ iLIdx.index }].effectiveDate" placeholder="Enter Effective Date" data-parsley-type="number" data-parsley-trigger="keyup"></form:input>
																					
																					</td>
																					<td>
																					<form:checkbox class="form-control" id="inputIsActive" name="inputIsActive" path="price[${ iLIdx.index }].isActive"></form:checkbox>
																					
																					</td>
																					<td>
																						<button id="removePriceButton" type="submit" class="btn btn-primary pull-right" value="${ iLIdx.index }"><span class="fa fa-minus"></span></button>
																					</td>
																					
																				</tr>
																				
																			</c:forEach>
																		</tbody>
																	</table>
																</div>
															</div>
															
														</div>
													</div>
												</div>
												
											</div>
											
											<div class="col-md-7 col-offset-md-5">
													<div class="btn-toolbar">
														<button id="cancelButton" type="reset" class="btn btn-primary pull-right">Cancel</button>
														<button id="submitButton" type="submit" class="btn btn-primary pull-right">Submit</button>
													</div>
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
		</div>
		
		<jsp:include page="/WEB-INF/views/include/footer.jsp"></jsp:include>		
	
	</div>	
</body>
</html>
