<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<!DOCTYPE html>
<html>
<head>
	<jsp:include page="/WEB-INF/views/include/headtag.jsp"></jsp:include>
	<script type="text/javascript">
		$(document).ready(function() {
			var ctxpath = "${ pageContext.request.contextPath }";
			var lastTab = "${activeTab}" == '' ? "${ loginContext.soList.size()-1 }" : "${activeTab}";
			
			$('[id^="inputSalesDate"]').datetimepicker({ format:'d-m-Y', timepicker:false });
			
			$('[id^="inputSalesDate"]').on('dp.change dp.show',function(e) {
				$(this).parsley().validate();
			});
						
			$('[id^="inputShippingDate"]').datetimepicker({ format:'d-m-Y', timepicker:false });
			
			$('[id^="inputShippingDate"]').on('dp.change dp.show', function(e) {
				$(this).parsley().validate();
			});
			
			var searchTable = $('#searchCustomerResultTable').DataTable({
								"paging":   	false,
						        "ordering": 	false,
						        "info":     	false,
						        "searching": 	false,
						        "fnRowCallback": function(nRow, aData, iDisplayIndex, iDisplayIndexFull ) {
									$('td:eq(5)', nRow).addClass( "actionCell" );
								}
							});

			$('[id^="searchButton_"], #newTab').click(function() {
				var id = "";
				var button = $(this).attr('id');
				var activetab = $(".nav-tabs li.active").attr("id");

				if (button == 'searchButton_' + activetab) {
					$('#inputCustomerSearchQuery_' + activetab).parsley().validate();
                    
					if(false == $('#inputCustomerSearchQuery_' + activetab).parsley().isValid()) {
						return false;
                    } else {
                    	$('#soForm').attr('action', ctxpath + "/sales/search/cust/" + $('#inputCustomerSearchQuery_' + activetab).val());
                    }
				} else if (button == 'newTab') {
					$('#soForm').attr('action', ctxpath + "/sales/addnewtab/");
				} else {
					return false;
				}
			});
			
			$('[id^="submitButton"]').click(function() {
				activetab = $(".nav-tabs li.active").attr("id");
				var salesType = $('[id^="selectSoType_"]').val();
				
				if(salesType=='L015_S'){

				if ($('#inputCustomerId_'+activetab).val() == "") {
					jsAlert("Please select customer");
					return false;
				}
				
				}
				$('#soForm').parsley({
				    excluded: '[id^="inputCustomerSearchQuery_"], [id^="productSelect_"]'
				}).validate();
				
				
				if (false == $('#soForm').parsley().isValid()) {	
					return false;
                } else {
					$('#soForm').attr('action', ctxpath + "/sales/save/" + salesType + "/${ customerId }/" + $(this).val());
                }
			});

			$('[id^="selectCust"]').click(function() {
				var activetab = $(".nav-tabs li.active").attr("id");
				$('#soForm').attr('action', ctxpath + "/sales/select/cust/" + $(this).val() + "/" + activetab);
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

		    $('#addProdButton, #removeProdButton').click(function() {
				var id = "";
				var button = $(this).attr('id');
				var activetab = $(".nav-tabs li.active").attr("id");
				var salesType = $('[id="selectSoType_' + activetab + '"]').val(); 
				var custId = $('input[id="soList_' + activetab + '_customerId"]').val();
				
				if (button == 'addProdButton') {
					productSelect = $("#productSelect_"+ activetab).val();
					
					$('[id^="productSelect_"]').parsley().validate();
                    if(false == $('[id^="productSelect_"]').parsley().isValid()) {
						return false;
                    } else {
                    	$('#soForm').attr('action', ctxpath + "/sales/additems/" + salesType + "/" + custId + "/" + activetab + "/" + productSelect);
                    }		
				} else {
					id = $(this).val();
					$('#soForm').attr('action', ctxpath + "/sales/removeitems/" + salesType + "/" + custId + "/" + activetab + "/" + id);
				}
			});
		    
		    $('[id^="cancelButton_"]').click(function() {
				activetab = $(".nav-tabs li.active").attr("id");
				$('#soForm').attr("action", ctxpath + "/sales/cancel/" + activetab);
			});

			$('#list a[href="#soTab_' + lastTab + '"]').tab('show');

    		$('[id^="selectSoType_"]').change(function() {
    			var activetab = $(".nav-tabs li.active").attr("id");
    			var salesType = $('[id="selectSoType_' + activetab + '"]').val();
    			
    			if (salesType != '') {
	    			$('#soForm').attr("action", ctxpath + "/sales/select/type/" + salesType + "/" + activetab);
	    			$('#soForm').submit();
    			}
    		});
    		
    		$('[id^="submitSalesType_"]').click(function() {
    			var activetab = $(".nav-tabs li.active").attr("id");
    			var salesType = $('[id="selectSoType_' + activetab + '"]').val();    			

    			if (salesType != '') {
	    			$('#soForm').attr("action", ctxpath + "/sales/select/type/" + salesType + "/" + activetab);
	    			$('#soForm').submit();
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
							<div role="tabpanel">
								<ul id="list" class="nav nav-tabs" role="tablist">
									<c:forEach items="${ loginContext.soList }" var="soForm" varStatus="soIdx">
										<li role="presentation" class="" id="${ soIdx.index }"><a href="#soTab_${ soIdx.index }" aria-controls="soTab_${ soIdx.index }" role="tab" data-toggle="tab"><span class="fa fa-dollar fa-fw">
											</span>&nbsp;New Sales</a>
										</li>
									</c:forEach>
									<li>
										<button id="newTab" type="submit" class="btn btn-xs btn-default pull-right"><span class="glyphicon glyphicon-plus"></span></button>
									</li>
								</ul>
								<div class="tab-content">
									<c:forEach items="${ loginContext.soList }" var="soForm" varStatus="soIdx">
										<div role="tabpanel" class="tab-pane" id="soTab_${ soIdx.index }">
											<br/>
											<form:hidden path="soList[${ soIdx.index }].salesId" />
											<div class="row">
												<div class="col-md-12">
													<div class="panel panel-default">
														<div class="panel-body">
															<div class="row">
																<div class="col-md-7">
																	<div class="form-group">
																		<label for="inputSalesCode" class="col-sm-2 control-label">Sales Code</label>
																		<div class="col-sm-5">
																			<form:input type="text" class="form-control data-so" id="inputSalesCode_${ soIdx.index }" name="inputSalesCode_${ soIdx.index }" path="soList[${ soIdx.index }].salesCode" placeholder="Enter Sales Code" readonly="true"></form:input>
																		</div>										
																	</div>
																	<div class="form-group">
																		<label for="inputSalesType" class="col-sm-2 control-label">Sales Type</label>
																		<div class="col-sm-7">
																			<c:if test="${ loginContext.soList[ soIdx.index ].salesStatus == 'L016_D' }">
																		   		<form:select id="selectSoType_${ soIdx.index }" class="form-control" path="soList[${ soIdx.index }].salesType" disabled="${ loginContext.soList[ soIdx.index ].salesStatus != 'L016_D' }" data-parsley-required="true" data-parsley-trigger="change">
																					<option value="">Please Select</option>
																					<form:options items="${ soTypeDDL }" itemValue="lookupKey" itemLabel="lookupValue"/>
																				</form:select>
																			</c:if>
																			<c:if test="${ loginContext.soList[ soIdx.index ].salesStatus != 'L016_D' }">
																				<form:hidden path="soList[${ soIdx.index }].salesType"/>
																				<form:input type="text" class="form-control" id="inputSalesType_${ soIdx.index }" name="inputSalesType_${ soIdx.index }" path="soList[${ soIdx.index }].soTypeLookup.lookupValue" readonly="true" data-parsley-required="true" data-parsley-trigger="keyup"></form:input>
																		   </c:if>
																		</div>
																		<div class="col-sm-1">
																			<button id="submitSalesType_${ soIdx.index }" type="button" class="btn btn-default pull-right"><span class="fa fa-repeat fa-fw"></span></button>
																		</div>																		
																	</div>
																	<div class="form-group">
																		<label for="inputCustomerId_${soIdx.index}" class="col-sm-2 control-label">Customer</label>
																		<div class="col-sm-10">
																			<form:hidden id="soList_${ soIdx.index }_customerId" path="soList[${ soIdx.index }].customerId"/>
																			<form:input type="text" class="form-control" id="inputCustomerId_${ soIdx.index }" name="inputCustomerId_${ soIdx.index }" path="soList[${ soIdx.index }].customerLookup.customerName" placeholder="Search Customer" disabled="true" data-parsley-required="true" data-parsley-trigger="keyup"></form:input>
																		</div>
																	</div>
																	<c:if test="${ loginContext.soList[soIdx.index].salesType == 'L015_WIN' }">
																		<div class="form-group">
																			<label for="inputWalkInCustomerDetail" class="col-sm-2 control-label">&nbsp;</label>
																			<div class="col-sm-10">
																				<form:textarea class="form-control" path="soList[${ soIdx.index }].walkInCustDetail" rows="3" readonly="${ loginContext.soList[ soIdx.index ].salesStatus != 'L016_D' }" placeholder="Enter Walk In Customer Detail"/>
																			</div>
																		</div>
																	</c:if>							
																	<c:if test="${ loginContext.soList[soIdx.index].salesType == 'L015_S' && not empty loginContext.soList[soIdx.index].customerId && loginContext.soList[soIdx.index].customerId != 0 }">
																		<div class="form-group">
																			<label for="inputCustomerDetail_${ soIdx.index }" class="col-sm-2 control-label">&nbsp;</label>
																			<div class="col-sm-10">
																				<textarea class="form-control" rows="3" id="inputCustomerDetail_${ soIdx.index }" readonly="readonly">Customer Details&#13;&#10;Here</textarea>
																			</div>
																		</div>
																	</c:if>
																</div>
																<div class="col-md-5">
																	<div class="form-group">
																		<label for="inputSalesDate" class="col-sm-3 control-label">Sales Date</label>
																		<div class="col-sm-9">
																			<form:input type="text" class="form-control data-so" id="inputSalesDate_${ soIdx.index }" path="soList[${ soIdx.index }].salesCreatedDate" placeholder="Enter Sales Date" readonly="${ loginContext.soList[ soIdx.index ].salesStatus != 'L016_D' }" data-parsley-required="true" data-parsley-trigger="change"></form:input>
																		</div>										
																	</div>
																	<div class="form-group">
																		<label for="inputSalesStatus_${ soIdx.index }" class="col-sm-3 control-label">Status</label>
																		<div class="col-sm-9">
																			<form:hidden path="soList[${ soIdx.index }].salesStatus" />
																			<label id="inputSalesStatus_${ soIdx.index }" class="control-label">
																				<c:out value="${ soForm.statusLookup.lookupValue }"></c:out>
																			</label>
																		</div>										
																	</div>
																</div>
															</div>									
															<c:if test="${ loginContext.soList[soIdx.index].salesType == 'L015_S' && loginContext.soList[soIdx.index].customerId == 0 }">
																<div class="row">
																	<div class="col-md-12">
																		<div id="panelSearchCustomer_${ soIdx.index }" class="panel panel-default">
																			<div class="panel-heading">
																	             <h4 class="panel-title">
																	             	Search Customer
																	      		</h4>
																	      	</div>
																			<div class="panel-body">
																				<div class="row">
																					<div class="col-md-12">
																						<div class="table-responsive">
																							<table class="table nopaddingrow borderless">
																								<tr>
																									<td width="93%">
																										<div class="form-group" style="padding-left: 1.7%">
																											<input type="text" class="form-control search" id="inputCustomerSearchQuery_${ soIdx.index }" name="inputCustomerSearchQuery" value="${ searchQuery }" placeholder="Search Customer Query" data-parsley-required="true" data-parsley-trigger="keyup"></input>
																										</div>
																									</td>
																									<td width="7%" align="right">
																										<button id="searchButton_${ soIdx.index }" type="submit" class="btn btn-primary" >Search</button>
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
																	</div>
																</div>
															</c:if>
															<hr>
															<div class="row">
																<div class="col-md-7">
																	<div class="form-group">
																		<label for="inputShippingDate" class="col-sm-2 control-label">Shipping Date</label>
																		<div class="col-sm-5">
																			<form:input type="text" class="form-control" id="inputShippingDate_${ soIdx.index }" name="inputShippingDate_${ soIdx.index }" path="soList[${ soIdx.index }].shippingDate" placeholder="Enter Shipping Date" readonly="${ loginContext.soList[ soIdx.index ].salesStatus != 'L016_D' }" data-parsley-required="true" data-parsley-trigger="change"></form:input>
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
																		Transactions
																	</h1>
																</div>
																<div class="panel-body">
																<c:if test="${ loginContext.soList[ soIdx.index ].salesStatus == 'L016_D' }">
																	<div class="row">
																		<div class="col-md-11">
																		<div class="form-group" style="padding-left: 2%">
																			<select id="productSelect_${ soIdx.index }" class="form-control" data-parsley-required="true" data-parsley-trigger="change">
																				<option value="">Please Select</option>
																				<c:forEach items="${ productSelectionDDL }" var="psddl">
																					<option value="${ psddl.productId }">${ psddl.productName }</option>
																				</c:forEach>
																			</select>
																			</div>
																		</div>
																		<div class="col-md-1">
																			<button id="addProdButton" type="submit" class="btn btn-primary pull-right"><span class="fa fa-plus"></span></button>
																		</div>
																	</div>
																	</c:if>
																	
																	<br/>
																	<div class="row">
																		<div class="col-md-12">
																			<table id="itemsListTable_${ soIdx.index }" class="table table-bordered table-hover display responsive">
																				<thead>
																					<tr>
																						<th width="30%">Product Name</th>
																						<th width="15%">Quantity</th>
																						<th width="15%" class="text-right">Unit</th>
																						<th width="15%" class="text-right">Price/Base Unit</th>
																						<th width="5%">&nbsp;</th>
																						<th width="20%" class="text-right">Total Price</th>
																					</tr>
																				</thead>
																				<tbody>
																					<c:set var="total" value="${0}" />
																					<c:forEach items="${ soForm.itemsList }" var="iL" varStatus="iLIdx">
																						<tr>
																							<td style="vertical-align: middle;">
																								<form:hidden path="soList[${ soIdx.index }].itemsList[${ iLIdx.index }].itemsId"/>
																								<form:hidden path="soList[${ soIdx.index }].itemsList[${ iLIdx.index }].productId"/>
																								<form:hidden path="soList[${ soIdx.index }].itemsList[${ iLIdx.index }].baseUnitCode" />
																								<form:hidden path="soList[${ soIdx.index }].itemsList[${ iLIdx.index }].toBaseValue" />
																								<form:hidden path="soList[${ soIdx.index }].itemsList[${ iLIdx.index }].toBaseQty" />
																								<c:out value="${ soForm.itemsList[iLIdx.index].productLookup.productName }"></c:out>
																							</td>
																							<td style="vertical-align: middle;">
																								<div class="form-group no-margin">
																									<div class="col-sm-12">
																										<form:input type="text" class="form-control text-right" id="inputItemsQuantity" name="inputItemsQuantity" path="soList[${ soIdx.index }].itemsList[${ iLIdx.index }].prodQuantity" placeholder="Enter Quantity" readonly="${ loginContext.soList[ soIdx.index ].salesStatus != 'L016_D' }" data-parsley-type="number" data-parsley-trigger="keyup"></form:input>
																									</div>
																								</div>
																							</td>
																							<td style="vertical-align: middle;">
																								<div class="form-group no-margin">
																									<div class="col-md-12">
																										<form:select class="form-control no-margin" path="soList[${ soIdx.index }].itemsList[${ iLIdx.index }].unitCode" data-parsley-required="true" data-parsley-trigger="change" disabled="${ loginContext.soList[ soIdx.index ].salesStatus != 'L016_D' }">
																											<option value=""><spring:message code="common.please_select"></spring:message></option>
																											<c:forEach items="${ loginContext.soList[ soIdx.index ].itemsList[iLIdx.index].productLookup.productUnit }" var="prdUnit">
																												<form:option value="${ prdUnit.unitCode }"><c:out value="${ prdUnit.unitCodeLookup.lookupValue }"/></form:option>
																											</c:forEach>
																										</form:select>
																									</div>
																								</div>
																							</td>
																							<td style="vertical-align: middle;">
																								<div class="form-group no-margin">
																									<div class="col-sm-12">
																										<form:input type="text" class="form-control text-right" id="inputItemsProdPrice" name="inputItemsProdPrice" path="soList[${ soIdx.index }].itemsList[${ iLIdx.index }].prodPrice" placeholder="Enter Price" readonly="${ loginContext.soList[ soIdx.index ].salesStatus != 'L016_D' }" data-parsley-type="number" data-parsley-trigger="keyup"></form:input>
																									</div>
																								</div>
																							</td>
																							<td>
																								<c:if test="${ loginContext.soList[ soIdx.index ].salesStatus == 'L016_D' }">
																									<button id="removeProdButton" type="submit" class="btn btn-primary pull-right" value="${ iLIdx.index }"><span class="fa fa-minus"></span></button>
																								</c:if>
																							</td>
																							<td class="text-right">
																								<c:out value="${ (iL.prodQuantity * iL.prodPrice) }"></c:out>
																							</td>
																						</tr>
																						<c:set var="total" value="${ total+ (iL.prodQuantity * iL.prodPrice) }" />
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
																							<c:out value="${ total }"></c:out>
																						</td>
																					</tr>
																				</tbody>
																			</table>
																		</div>														
																	</div>
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
															<h1 class="panel-title">Remarks</h1>
														</div>
														<div class="panel-body">
															<div class="row">
																<div class="col-md-12">
																	<div class="form-group">
																		<div class="col-sm-12">
																			<form:textarea class="form-control" path="soList[${ soIdx.index }].salesRemarks" rows="5" readonly="${ loginContext.soList[ soIdx.index ].salesStatus != 'L016_D' }"/>
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
														<c:if test="${ loginContext.soList[ soIdx.index ].salesStatus == 'L016_D' }">
															<button id="cancelButton_${ soIdx.index }" type="submit" class="btn btn-primary pull-right">Cancel</button>
															<button id="submitButton" type="submit" class="btn btn-primary pull-right" value="${ soIdx.index }">Submit</button>
														</c:if>
														<c:if test="${ loginContext.soList[ soIdx.index ].salesStatus =='L016_WD' }">
															<button id="cancelButton_${ soIdx.index }" type="submit" class="btn btn-primary pull-right">Close</button>
														</c:if>
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
		
		<jsp:include page="/WEB-INF/views/include/footer.jsp"></jsp:include>		
	
	</div>	
</body>
</html>
