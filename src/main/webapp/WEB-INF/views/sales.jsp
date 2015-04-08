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
			var tabCount = "${ loginContext.soList.size() }";
			var lastTab = "${ loginContext.soList.size()-1 }";
			
			$('[id^="inputSalesDate"]').datetimepicker({format: "DD-MM-YYYY"});
			$('[id^="inputSalesDate"]').on('dp.change dp.show',function(e) {
				$('#soForm').formValidation('revalidateField', 'inputSalesDate');
			});
			
			$('[id^="inputShippingDate"]').datetimepicker({format: "DD-MM-YYYY"});
			$('[id^="inputShippingDate"]').on('dp.change dp.show',function(e) {
				$('#soForm').formValidation('revalidateField', 'inputShippingDate');
			});

			var searchTable = 
				$('#searchCustomerResultTable').DataTable({
					"paging":   	false,
			        "ordering": 	false,
			        "info":     	false,
			        "searching": 	false,
			        "fnRowCallback": function( nRow, aData, iDisplayIndex, iDisplayIndexFull ) {
						$('td:eq(5)', nRow).addClass( "actionCell" );
					}
				});

			$('#searchButton, #newTab').click(function() {
				
				var id = "";
				var button = $(this).attr('id');

				if (button == 'searchButton') {
				//	$('#soForm').data('formValidation').enableFieldValidators('customerSearchQuery', true).revalidateField('customerSearchQuery');
					$('#soForm').attr('action', ctxpath + "/sales/search/cust/" + $('#inputCustomerSearchQuery').val());	
				} else if (button == 'newTab'){
					$('#soForm').attr('action', ctxpath + "/sales/addnewtab/${customerId}");
					//$('#soForm').submit();
				} else {
					return false;
				}
			});
			
			$('[id^="submitButton"]').click(function() {
				
				
					$('#soForm').attr('action', ctxpath + "/sales/save/${ customerId }/"+$(this).val());
					//$('#soForm').submit();
				
			});

			$('[id^="selectCust"]').click(function() {
				
				
				$('#soForm').attr('action', ctxpath + "/sales/select/cust/"+$(this).val());
				
			
		});

		    $('#searchCustomerResultTable tbody').on('click', 'td:not(".actionCell")', function() {
				var tr = $(this).closest('tr');
		        var row = searchTable.row(tr);
		 
		        if (row.child.isShown()) {
		        	row.child.hide();
		            tr.removeClass('shown');
		        } else {
		            row.child(format(row.data())).show();
		            tr.addClass('shown');
		        }
		    });

		    function format ( d ) {		        
		        return '<strong>Customer Details</strong><br/><br/>';
		    }
		    
		    $("#soForm").formValidation({
				locale : 'id_ID',
				framework : 'bootstrap',
				button : {
					selector : '[id^="submitButton"]',
					disabled : 'disabled'
				},
				excluded : 'disabled',
				icon : {
					valid : 'glyphicon glyphicon-ok',
					invalid : 'glyphicon glyphicon-remove',
					validating : 'glyphicon glyphicon-refresh'
				},
				fields : {

					'inputSalesCode' : {
						selector : '[id^="inputSalesCode"]',
						row : '.col-sm-5',
						validators : {
							notEmpty : {}
						}
					},
					'inputSalesDate' : {
						selector : '[id^="inputSalesDate"]',
						row : '.col-sm-9',
						validators : {
							notEmpty : {},
							date : {
								format : 'DD-MM-YYYY'
							}
						}
					},
					'inputShippingDate' : {
						selector : '[id^="inputShippingDate"]',
						row : '.col-sm-5',
						validators : {
							notEmpty : {},
							date : {
								format : 'DD-MM-YYYY'
							}
						}
					}
				}
			});

		    $('#addProdButton, #removeProdButton').click(
					function() {
						var id = "";
						var button = $(this).attr('id');

						if (button == 'addProdButton') {
							activetab = $(".nav-tabs li.active").attr("id");
							productSelect = $("#productSelect_"+ activetab).val();
							$('#soForm').attr('action',ctxpath + "/sales/additems/${customerId}/"+ activetab + "/"+ productSelect);
						} else {
							id = $(this).val();
							activetab = $(".nav-tabs li.active").attr("id");
							$('#soForm').attr('action',ctxpath + "/sales/removeitems/${customerId}/"+ activetab + "/" + id);
						}
			});

		    $('#list a[href="#soTab_' + lastTab + '"]').tab('show');

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
						<form:form id="soForm" role="form" class="form-horizontal" modelAttribute="loginContext" action="${pageContext.request.contextPath}/sales/save">
							
							<div class="panel panel-default">
								<div class="panel-body">
									<div class="row">
										<div class="col-md-12">
											<div class="table-responsive">
												<table class="table nopaddingrow borderless">
													<tr>
														<td width="93%">
															<input type="text" class="form-control" id="inputCustomerSearchQuery" name="inputCustomerSearchQuery" placeholder="Search Customer Query"></input>
														</td>
														<td width="7%" align="right">
															<button id="searchButton" type="submit" class="btn btn-primary" >Search</button>
														</td>
													</tr>
												</table>
											</div>
											<table id="searchCustomerResultTable" class="table table-bordered table-hover display responsive">
												<thead>
													<tr>
														<th width="25%">Customer Name</th>
														<th width="25%">Address</th>
														<th width="20%">PIC</th>
														<th width="20%">Bank Account</th>
														<th width="5%">Status</th>
														<th width="5%">&nbsp;</th>
													</tr>
												</thead>
												<tbody>
													<c:if test="${ not empty customerList }">
														<c:forEach items="${ customerList }" varStatus="cIdx">
															<tr>
																<td><c:out value="${ customerList[cIdx.index].customerName }"></c:out></td>
																<td>
																	<c:out value="${ customerList[cIdx.index].customerAddress }"></c:out><br/>
																	<c:out value="${ customerList[cIdx.index].customerCity }"></c:out><br/>
																	<c:out value="${ customerList[cIdx.index].customerPhone }"></c:out><br/><br/>
																	<c:out value="${ customerList[cIdx.index].customerRemarks }"></c:out>
																</td>
																<td>
																	<c:forEach items="${ customerList[cIdx.index].picList }" varStatus="picIdx">
																		<c:out value="${ customerList[cIdx.index].picList[picIdx.index].firstName }"/>&nbsp;<c:out value="${ soForm.customerSearchResults[cIdx.index].picList[picIdx.index].firstName }"/><br/>
																	</c:forEach>
																</td>
																<td>
																	<c:forEach items="${ customerList[cIdx.index].bankAccList }" varStatus="baIdx">
																		<c:out value="${ customerList[cIdx.index].bankAccList[baIdx.index].bankName }"/><br/>
																	</c:forEach>																	
																</td>
																<td align="center">
																	<c:out value="${ customerList[cIdx.index].statusLookup.lookupValue }"/>
																</td>
																<td align="center" style="vertical-align: middle;">
																	<button id="selectCust" type="submit" class="btn btn-primary btn-xs" value="${ customerList[cIdx.index].customerId }"><span class="fa fa-check"></span></button>
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
							<div role="tabpanel">
								<ul id="list" class="nav nav-tabs" role="tablist">
								<c:forEach items="${ loginContext.soList }" var="soForm" varStatus="soIdx">
									
									<li role="presentation" class="" id="${soIdx.index}"><a href="#soTab_${soIdx.index}" aria-controls="soTab_${soIdx.index}" role="tab" data-toggle="tab"><span class="fa fa-dollar fa-fw">
										</span>&nbsp;New Sales</a>
									</li>
								</c:forEach>
									
									<li>
										<button id="newTab" type="submit" class="btn btn-xs btn-default pull-right"><span class="glyphicon glyphicon-plus"></span></button>
									</li>
								</ul>

								
								<div class="tab-content">
								<c:forEach items="${ loginContext.soList }" var="soForm" varStatus="soIdx">
									<div role="tabpanel" class="tab-pane" id="soTab_${soIdx.index}">
										<br/>
										
										<form:hidden path="soList[${soIdx.index}].salesId" />
										<div class="row">
											<div class="col-md-12">
												<div class="panel panel-default">
													<div class="panel-body">
														<div class="row">
															<div class="col-md-7">
																<div class="form-group">
																	<label for="inputSalesCode" class="col-sm-2 control-label">Sales Code</label>
																	<div class="col-sm-5">
																		<form:input type="text" class="form-control" id="inputSalesCode" name="inputSalesCode" path="soList[${soIdx.index}].salesCode" placeholder="Enter Sales Code"></form:input>
																	</div>										
																</div>
																<div class="form-group">
																	<label for="inputSalesType" class="col-sm-2 control-label">Sales Type</label>
																	<div class="col-sm-8">
																		<form:select class="form-control" path="soList[${soIdx.index}].salesType">
																			<option value="">Please Select</option>
																			<form:options items="${ soTypeDDL }" itemValue="lookupKey" itemLabel="lookupValue"/>
																		</form:select>	
																	</div>										
																</div>
																<div class="form-group">
																	<label for="inputCustomerId" class="col-sm-2 control-label">Customer</label>
																	<div class="col-sm-9">
																		<form:hidden path="soList[${soIdx.index}].customerId"/>
																		<form:input type="text" class="form-control" id="inputCustomerId" name="inputCustomerId" path="soList[${soIdx.index}].customerLookup.customerName" placeholder="Search Customer" disabled="true"></form:input>
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
																		<form:input type="text" class="form-control" id="inputSalesDate" name="soList[${soIdx.index}].inputSalesDate" path="soList[${soIdx.index}].salesCreatedDate" placeholder="Enter Sales Date"></form:input>
																	</div>										
																</div>
																<div class="form-group">
																	<label for="inputSalesStatus" class="col-sm-3 control-label">Status</label>
																	<div class="col-sm-9">
																		<form:hidden path="soList[${soIdx.index}].salesStatus" />
																		
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
																		<form:input type="text" class="form-control" id="inputShippingDate" name="inputShippingDate" path="soList[${soIdx.index}].shippingDate" placeholder="Enter Shipping Date"></form:input>
																	</div>										
																</div>
															</div>
														</div>					
													</div>
												</div>
												<div class="row">
													<div class="col-md-8">
														<div class="panel panel-default">
															<div class="panel-heading">
																<h1 class="panel-title">
																	New Transaction
																</h1>
															</div>
															<div class="panel-body">
																<div class="row">
																	<div class="col-md-11">
																		<select id="productSelect_${soIdx.index}" class="form-control">
																			<option value="">Please Select</option>
																			<c:forEach items="${ productSelectionDDL }" var="psddl">
																				<option value="${ psddl.productId }">${ psddl.productName }</option>
																			</c:forEach>
																		</select>
																	</div>
																	<div class="col-md-1">
																		<button id="addProdButton" type="submit" class="btn btn-primary pull-right"><span class="fa fa-plus"></span></button>
																	</div>
																</div>
																<br/>
																<div class="row">
																	<div class="col-md-12">
																		<table id="itemsListTable" class="table table-bordered table-hover display responsive">
																			<thead>
																				<tr>
																					<th width="40%">Product Name</th>
																					<th width="10%">Quantity</th>
																					<th width="10%">Unit</th>
																					<th width="15%">Price/Unit</th>
																					<th width="5%">&nbsp;</th>
																					<th width="20%" class="text-right">Total Price</th>
																				</tr>
																			</thead>
																			<tbody>
																				<c:forEach items="${ soForm.itemsList }" var="iL" varStatus="iLIdx">
																					<tr>
																						<td style="vertical-align: middle;">
																							<form:hidden path="soList[${soIdx.index}].itemsList[${ iLIdx.index }].itemsId"/>
																							<form:hidden path="soList[${soIdx.index}].itemsList[${ iLIdx.index }].productId"/>
																							<c:out value="${ soForm.itemsList[iLIdx.index].productLookup.productName }"></c:out>
																						</td>
																						<td>
																							<form:input type="text" class="form-control text-right" id="inputItemsQuantity" name="inputItemsQuantity" path="soList[${soIdx.index}].itemsList[${ iLIdx.index }].prodQuantity" placeholder="Enter Quantity"></form:input>
																						</td>
																						<td>
																							&nbsp;
																						</td>
																						<td>
																							<form:input type="text" class="form-control text-right" id="inputItemsProdPrice" name="inputItemsProdPrice" path="soList[${soIdx.index}].itemsList[${ iLIdx.index }].prodPrice" placeholder="Enter Price"></form:input>
																						</td>
																						<td>
																							<button id="removeProdButton" type="submit" class="btn btn-primary pull-right" value="${ iLIdx.index }"><span class="fa fa-minus"></span></button>
																						</td>
																						<td class="text-right">
																							&nbsp;
																						</td>
																					</tr>
																				</c:forEach>
																			</tbody>
																		</table>
																	</div>
																</div>
																<div class="row">
																	<div class="col-md-12">
																		<table id="itemsTotalListTable" class="table table-bordered table-hover display responsive">
																			<tbody>
																				<tr>
																					<td width="80%" class="text-right">
																						Total
																					</td>
																					<td width="20%" class="text-right">
																						12344556677
																					</td>
																				</tr>
																			</tbody>
																		</table>
																	</div>														
																</div>
															</div>
														</div>
													</div>
													<div class="col-md-4">
														<div class="panel panel-default">
															<ul class="list-group">
																<li class="list-group-item"></li>
																<li class="list-group-item"></li>
																<li class="list-group-item"></li>
																<li class="list-group-item"></li>
																<li class="list-group-item"></li>
																<li class="list-group-item"></li>
																<li class="list-group-item"></li>
															</ul>
														</div>
													</div>
												</div>
											</div>
										</div>
										<div class="row">
										<div class="col-md-12">
											<div class="panel panel-default">
												<div class="panel-heading">
													<h1 class="panel-title">Remarks</h1>
												</div>
												<div class="panel-body">
													<div class="row">
														<div class="col-md-12">
															<div class="form-group">
																<div class="col-sm-12">
																	<form:textarea class="form-control" path="soList[${soIdx.index}].salesRemarks" rows="5"/>
																</div>
															</div>
														</div>
													</div>
												</div>
											</div>
										</div>
									</div>
										<div class="row">
										<div class="col-md-7 col-offset-md-5">
											<div class="btn-toolbar">
												<button id="cancelButton" type="reset" class="btn btn-primary pull-right">Cancel</button>
												<button id="submitButton" type="submit" class="btn btn-primary pull-right" value="${ soIdx.index }">Submit</button>
											</div>
										</div>
									</div>	
										
									</div>
									</c:forEach>
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
