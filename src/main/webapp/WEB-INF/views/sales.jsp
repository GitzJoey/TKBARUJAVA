<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
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
                    	$('#soForm').attr('action', ctxpath + "/sales/t/" + activetab + "/search/cust/" + $('#inputCustomerSearchQuery_' + activetab).val());
                    }
				} else if (button == 'newTab') {
					$('#soForm').attr('action', ctxpath + "/sales/addnewtab/");
				} else {
					return false;
				}
			});
			
			$('[id^="inputCustomerSearchQuery_"]').keypress(function(e) {
				if(e.which == 13) {
					e.preventDefault();
			        var id = $(this).attr('id').split('_')[1];
			        $('#searchButton_' + id).click();
			    }
			});
			
			$('[id^="submitButton"]').click(function() {
				activetab = $(".nav-tabs li.active").attr("id");

				$('#soForm').parsley({
				    excluded: '[id^="inputCustomerSearchQuery_"], [id^="productSelect_"]'
				}).validate('tab_' + activetab, false);
				
				if (false == $('#soForm').parsley().isValid('tab_' + activetab, false)) {
					return false;
                } else {
					$('#soForm').attr('action', ctxpath + "/sales/t/" + activetab + "/save");
                }
			});

			$('[id^="selectCust"]').click(function() {
				var activetab = $(".nav-tabs li.active").attr("id");
				$('#soForm').attr('action', ctxpath + "/sales/t/" + activetab + "/select/cust/" + $(this).val());
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
				var custId = $('input[id="soList_' + activetab + '_customerId"]').val();
				
				if (button == 'addProdButton') {
					productSelect = $("#productSelect_"+ activetab).val();
					
					$('#productSelect_' +  activetab).parsley().validate();
					
					if(false == $('#productSelect_' + activetab).parsley().isValid()) {
						return false;
                    } else {
                    	$('#soForm').attr('action', ctxpath + "/sales/t/" + activetab  + "/additems/" + productSelect + "#trx_" + activetab);
                    }		
				} else {
					id = $(this).val();
					$('#soForm').parsley().destroy();
					$('#soForm').attr('action', ctxpath + "/sales/t/" + activetab + "/removeitems/" + id + "#trx_" + activetab);
				}
			});
		    
		    $('[id^="cancelButton_"]').click(function() {
		    	var activetab = $(".nav-tabs li.active").attr("id");
		    	$('#soForm').parsley().destroy();
				$('#soForm').attr("action", ctxpath + "/sales/t/" + activetab + "/cancel");	
			});

			$('#list a[href="#soTab_' + lastTab + '"]').tab('show');

    		$('[id^="selectCustType_"]').change(function() {
    			var activetab = $(".nav-tabs li.active").attr("id");
    			var custType = $('[id="selectCustType_' + activetab + '"]').val();
    			
    			if (custType != '') {
	    			$('#soForm').attr("action", ctxpath + "/sales/t/" + activetab + "/select/cust/type/" + custType);
	    			$('#soForm').submit();
    			}
    		});

    		$('[id^="selectSoType_"]').change(function() {
    			var activetab = $(".nav-tabs li.active").attr("id");
    			var salesType = $('[id="selectSoType_' + activetab + '"]').val();
    			
    			if (salesType != '') {
	    			$('#soForm').attr("action", ctxpath + "/sales/t/" + activetab + "/select/sales/type/" + salesType);
	    			$('#soForm').submit();
    			}
    		});

    		$('[id^="submitCustType_"]').click(function() {
    			var activetab = $(".nav-tabs li.active").attr("id");
    			var custType = $('[id="selectCustType_' + activetab + '"]').val();

    			if (custType != '') {
	    			$('#soForm').attr("action", ctxpath + "/sales/t/" + activetab + "/select/type/" + custType);
	    			$('#soForm').submit();
    			}
    		});    		

			window.Parsley.addValidator('validquantity', function (value, index) {
				var salesIdx = index.split('_')[0];
				var itemsIdx = index.split('_')[1];
				var stocksId = $('#items_' + salesIdx + '_' + itemsIdx + '_stocksId').val();
				var qty = value;
				var unit = $('#items_' + salesIdx + '_' + itemsIdx + '_unitSelect').val();
				var ret = false;
				var salesType = $('#selectSoType_' + salesIdx).val();

				if (salesType == 'L015_SVC') { ret = true; return ret; }
				
				var response = $.ajax({
					url : ctxpath+ "/sales/check/stocks",
					data : {stocksId: stocksId, qty: qty, unit: unit},
					type : "GET",
					async: false
				}).responseText;
				
				if (response == "true") { ret = true; } else { ret = false; }
				return ret;
			}, 32)
			.addMessage('en', 'validquantity', 'Out of stocks')
			.addMessage('id', 'validquantity', 'Melebihi stok');
		});
		
		function stringToDate(_date,_format,_delimiter) {
			var formatLowerCase=_format.toLowerCase();
			var formatItems=formatLowerCase.split(_delimiter);
			var dateItems=_date.split(_delimiter);
			var monthIndex=formatItems.indexOf("mm");
			var dayIndex=formatItems.indexOf("dd");
			var yearIndex=formatItems.indexOf("yyyy");
			var month=parseInt(dateItems[monthIndex]);
			month-=1;
			var formatedDate = new Date(dateItems[yearIndex],month,dateItems[dayIndex]);
			return formatedDate;
		}

		window.Parsley.addValidator('nobackdate', function (value, todayDate) {
			var tDate = stringToDate(todayDate, 'dd-mm-yyyy', '-');
			var iDate = stringToDate(value, 'dd-mm-yyyy', '-');

			if (iDate - tDate >= 0) {
				return true;
			} else {
				return false
			}
		}, 32)
		.addMessage('en', 'nobackdate', 'Backdated is not allowed.')
		.addMessage('id', 'nobackdate', 'Tidak diperkenankan mengisi tanggal kemarin.');		
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
					<span class="fa fa-cart-arrow-down fa-fw"></span>&nbsp;<spring:message code="sales_jsp.title" text="Sales Order"/>
				</h1>
				
				<div class="panel panel-default">
					<div class="panel-heading">
						<h1 class="panel-title">
							<c:choose>
								<c:when test="${ PAGEMODE == 'PAGEMODE_ADD' }">
									<span class="fa fa-cart-arrow-down fa-fw fa-2x"></span>&nbsp;<spring:message code="sales_jsp.subtitle" text="New Sales Order"/>
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
											</span>&nbsp;<spring:message code="sales_jsp.new_sales" text="New Sales"/>&nbsp;${ soIdx.index + 1 }</a>
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
																		<label for="inputSalesCode" class="col-sm-2 control-label"><spring:message code="sales_jsp.sales_code" text="Sales Code"/></label>
																		<div class="col-sm-5">
																			<form:input type="text" class="form-control data-so" id="inputSalesCode_${ soIdx.index }" name="inputSalesCode_${ soIdx.index }" path="soList[${ soIdx.index }].salesCode" placeholder="Enter Sales Code" readonly="true"></form:input>
																		</div>
																	</div>
																	<div class="form-group">
																		<label for="inputSalesType" class="col-sm-2 control-label"><spring:message code="sales_jsp.sales_type" text="Sales Type"/></label>
																		<div class="col-sm-7">
																	   		<form:select id="selectSoType_${ soIdx.index }" class="form-control" path="soList[${ soIdx.index }].salesTypeLookup.lookupKey" disabled="${ loginContext.soList[ soIdx.index ].salesStatusLookup.lookupKey != 'L016_D' }" data-parsley-required="true" data-parsley-trigger="change" data-parsley-group="tab_${ soIdx.index }">
																				<option value=""><spring:message code="common.please_select" text="Please Select"/></option>
																				<c:forEach items="${ soTypeDDL }" var="i">
																					<form:option value="${ i.lookupKey }"><spring:message code="${ i.i18nLookupValue }" text="${ i.lookupValue }"></spring:message></form:option>
																				</c:forEach>
																			</form:select>																		
																		</div>
																	</div>
																</div>
																<div class="col-md-5">
																	<div class="form-group">
																		<label for="inputSalesDate" class="col-sm-3 control-label"><spring:message code="sales_jsp.sales_date" text="Sales Date"/></label>
																		<div class="col-sm-9">
																			<form:input type="text" class="form-control data-so" id="inputSalesDate_${ soIdx.index }" path="soList[${ soIdx.index }].salesCreatedDate" placeholder="Enter Sales Date" readonly="${ loginContext.soList[ soIdx.index ].salesStatusLookup.lookupKey != 'L016_D' }" data-parsley-required="true" data-parsley-trigger="change" data-parsley-group="tab_${ soIdx.index }"></form:input>
																		</div>
																	</div>
																	<div class="form-group">
																		<label for="inputSalesStatus_${ soIdx.index }" class="col-sm-3 control-label"><spring:message code="sales_jsp.status" text="Status"/></label>
																		<div class="col-sm-9">
																			<form:hidden path="soList[${ soIdx.index }].salesStatusLookup.lookupKey" />
																			<label id="inputSalesStatus_${ soIdx.index }" class="control-label">
																				<spring:message code="${ soForm.salesStatusLookup.i18nLookupValue }" text="${ soForm.salesStatusLookup.lookupValue }"></spring:message>
																			</label>
																		</div>
																	</div>
																</div>
															</div>
															<hr>
															<div class="row">
																<div class="col-md-7">
																	<div class="form-group">
																		<label for="inputShippingDate" class="col-sm-2 control-label"><spring:message code="sales_jsp.shipping_date" text="Shipping Date"/></label>
																		<div class="col-sm-5">
																			<jsp:useBean id="todayDate" class="java.util.Date" scope="page" />																			
																			<fmt:formatDate value="${ todayDate }" var="formattedTodayDate" type="date" pattern="dd-MM-yyyy" />
																			<form:input type="text" class="form-control" id="inputShippingDate_${ soIdx.index }" name="inputShippingDate_${ soIdx.index }" path="soList[${ soIdx.index }].shippingDate" placeholder="Enter Shipping Date" readonly="${ loginContext.soList[ soIdx.index ].salesStatusLookup.lookupKey != 'L016_D' }" data-parsley-required="true" data-parsley-nobackdate="${ formattedTodayDate }" data-parsley-trigger="change" data-parsley-group="tab_${ soIdx.index }"></form:input>
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
												             <h4 class="panel-title">
												             	<spring:message code="sales_jsp.customer" text="Customer"/>
												      		</h4>
												      	</div>
														<div class="panel-body">
															<div class="row">
																<div class="col-md-7">
																	<div class="form-group">
																		<label for="inputCustomerType" class="col-sm-2 control-label"><spring:message code="sales_jsp.customer_type" text="Customer Type"/></label>										
																		<div class="col-sm-7">
																			<c:if test="${ loginContext.soList[ soIdx.index ].salesStatusLookup.lookupKey == 'L016_D' }">
																		   		<form:select id="selectCustType_${ soIdx.index }" class="form-control" path="soList[${ soIdx.index }].customerTypeLookup.lookupKey" disabled="${ loginContext.soList[ soIdx.index ].salesStatusLookup.lookupKey != 'L016_D' }" data-parsley-required="true" data-parsley-trigger="change" data-parsley-group="tab_${ soIdx.index }">
																					<option value=""><spring:message code="common.please_select" text="Please Select"/></option>
																					<c:forEach items="${ custTypeDDL }" var="i">
																						<form:option value="${ i.lookupKey }"><spring:message code="${ i.i18nLookupValue }"></spring:message></form:option>
																					</c:forEach>
																				</form:select>
																			</c:if>
																			<c:if test="${ loginContext.soList[ soIdx.index ].salesStatusLookup.lookupKey != 'L016_D' }">
																				<form:hidden path="soList[${ soIdx.index }].customerTypeLookup.lookupKey"/>
																				<form:input type="text" class="form-control" id="inputCustType_${ soIdx.index }" name="inputCustType_${ soIdx.index }" path="soList[${ soIdx.index }].customerTypeLookup.lookupValue" readonly="true" data-parsley-required="true" data-parsley-trigger="keyup" data-parsley-group="tab_${ soIdx.index }"></form:input>
																		   </c:if>
																		</div>
																		<div class="col-sm-1">
																			<button id="submitCustType_${ soIdx.index }" type="button" class="btn btn-default pull-right"><span class="fa fa-repeat fa-fw"></span></button>
																		</div>
																	</div>
																	<div class="form-group">
																		<label for="inputCustomerId_${soIdx.index}" class="col-sm-2 control-label"><spring:message code="sales_jsp.customer" text="Customer"/></label>
																		<div class="col-sm-10">
																			<form:hidden id="soList_${ soIdx.index }_customerId" path="soList[${ soIdx.index }].customerEntity.customerId"/>
																			<c:choose>
																				<c:when test="${ loginContext.soList[soIdx.index].customerTypeLookup.lookupKey == 'L022_WIN' }">
																					<input type="text" class="form-control" id="inputCustomerId_${ soIdx.index }" name="inputCustomerId_${ soIdx.index }" placeholder="Walk In Customer" disabled="disabled"/>
																				</c:when>
																				<c:otherwise>
																					<form:input type="text" class="form-control" id="inputCustomerId_${ soIdx.index }" name="inputCustomerId_${ soIdx.index }" path="soList[${ soIdx.index }].customerEntity.customerName" placeholder="Search Customer" readonly="true" data-parsley-required="true" data-parsley-trigger="keyup"></form:input>
																				</c:otherwise>
																			</c:choose>
																		</div>
																	</div>
																	<c:if test="${ loginContext.soList[soIdx.index].customerTypeLookup.lookupKey == 'L022_WIN' }">
																		<div class="form-group">
																			<label for="inputWalkInCustomerDetail" class="col-sm-2 control-label">&nbsp;</label>
																			<div class="col-sm-10">
																				<form:textarea class="form-control" path="soList[${ soIdx.index }].walkInCustDetail" rows="3" readonly="${ loginContext.soList[ soIdx.index ].salesStatusLookup.lookupKey != 'L016_D' }" placeholder="Enter Walk In Customer Detail"/>
																			</div>
																		</div>
																	</c:if>
																	<c:if test="${ loginContext.soList[soIdx.index].customerTypeLookup.lookupKey == 'L022_R' && not empty loginContext.soList[soIdx.index].customerEntity.customerId }">
																		<div class="form-group">
																			<label for="inputCustomerDetail_${ soIdx.index }" class="col-sm-2 control-label">&nbsp;</label>
																			<div class="col-sm-10">
																				<textarea class="form-control" rows="3" id="inputCustomerDetail_${ soIdx.index }" readonly="readonly"><c:out value="${ loginContext.soList[soIdx.index].customerEntity }"/></textarea>
																				<form:hidden path="soList[${ soIdx.index }].customerEntity.priceLevelEntity.priceLevelId"/>
																			</div>
																		</div>
																	</c:if>
																</div>
																<div class="col-md-5">
																</div>
															</div>
															<c:if test="${ loginContext.soList[soIdx.index].customerTypeLookup.lookupKey == 'L022_R' && empty loginContext.soList[soIdx.index].customerEntity.customerId }">
																<div class="row">
																	<div class="col-md-12">
																		<div id="panelSearchCustomer_${ soIdx.index }" class="panel panel-default">
																			<div class="panel-heading">
																	             <h4 class="panel-title">
																	             	<spring:message code="sales_jsp.search_customer" text="Search Customer"/>
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
																										<button id="searchButton_${ soIdx.index }" type="submit" class="btn btn-primary"><spring:message code="sales_jsp.search_button" text="Search"/></button>
																									</td>
																								</tr>
																							</table>
																						</div>
																						<table id="searchCustomerResultTable" class="table table-bordered table-hover display responsive">
																							<thead>
																								<tr>
																									<th width="25%"><spring:message code="sales_jsp.table.search.header.customer_name" text="Customer Name"/></th>
																									<th width="25%"><spring:message code="sales_jsp.table.search.header.address" text="Address"/></th>
																									<th width="20%"><spring:message code="sales_jsp.table.search.header.pic" text="PIC"/></th>
																									<th width="20%"><spring:message code="sales_jsp.table.search.header.bank_account" text="Bank Account"/></th>
																									<th width="5%"><spring:message code="sales_jsp.table.search.header.status" text="Status"/></th>
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
																												<spring:message code="${ customerList[cIdx.index].customerStatusLookup.i18nLookupValue }" text="${ customerList[cIdx.index].customerStatusLookup.lookupValue }"/>
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
														</div>
													</div>
													<div class="row">
														<div class="col-md-12">
															<div class="panel panel-default">
																<div class="panel-heading">
																	<h1 class="panel-title" id="trx_${ soIdx.index }">
																		<spring:message code="sales_jsp.transactions" text="Transactions"/>
																	</h1>
																</div>
																<div class="panel-body">
																	<c:if test="${ loginContext.soList[ soIdx.index ].salesStatusLookup.lookupKey == 'L016_D' }">
																		<div class="row">
																			<div class="col-md-11">
																				<select id="productSelect_${ soIdx.index }" class="form-control" data-parsley-required="true" data-parsley-trigger="change">
																					<option value=""><spring:message code="common.please_select" text="Please Select"/></option>
																					<c:if test="${ loginContext.soList[ soIdx.index ].salesTypeLookup.lookupKey == 'L015_S' }">
																						<c:forEach items="${ stocksListDDL }" var="sddl">
																							<option value="${ sddl.stocksId }">${ sddl.productEntity.productName }</option>
																						</c:forEach>
																					</c:if>
																					<c:if test="${ loginContext.soList[ soIdx.index ].salesTypeLookup.lookupKey == 'L015_SVC' }">
																						<c:forEach items="${ productListDDL }" var="pddl">
																							<option value="${ pddl.productId }">${ pddl.productName }</option>
																						</c:forEach>
																					</c:if>
																				</select>
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
																						<th width="30%"><spring:message code="sales_jsp.table.item.header.product_name" text="Product Name"/></th>
																						<th width="15%"><spring:message code="sales_jsp.table.item.header.quantity" text="Quantity"/></th>
																						<th width="15%" class="text-right"><spring:message code="sales_jsp.table.item.header.unit" text="Unit"/></th>
																						<th width="15%" class="text-right"><spring:message code="sales_jsp.table.item.header.price_unit" text="Price/Base Unit"/></th>
																						<th width="5%">&nbsp;</th>
																						<th width="20%" class="text-right"><spring:message code="sales_jsp.table.item.header.total_price" text="Total Price"/></th>
																					</tr>
																				</thead>
																				<tbody>
																					<c:set var="total" value="${0}" />
																					<c:forEach items="${ soForm.itemsList }" var="iL" varStatus="iLIdx">
																						<tr>
																							<td style="vertical-align: middle;">
																								<form:hidden id="items_${ soIdx.index }_${ iLIdx.index }_itemsId" path="soList[${ soIdx.index }].itemsList[${ iLIdx.index }].itemsId"/>
																								<form:hidden id="items_${ soIdx.index }_${ iLIdx.index }_productId" path="soList[${ soIdx.index }].itemsList[${ iLIdx.index }].productEntity.productId"/>
																								<form:hidden id="items_${ soIdx.index }_${ iLIdx.index }_baseUnitCode" path="soList[${ soIdx.index }].itemsList[${ iLIdx.index }].baseUnitCodeLookup.lookupKey" />
																								<form:hidden id="items_${ soIdx.index }_${ iLIdx.index }_toBaseValue" path="soList[${ soIdx.index }].itemsList[${ iLIdx.index }].toBaseValue" />
																								<form:hidden id="items_${ soIdx.index }_${ iLIdx.index }_toBaseQty" path="soList[${ soIdx.index }].itemsList[${ iLIdx.index }].toBaseQty" />
																								<c:if test="${ soForm.salesTypeLookup.lookupKey == 'L015_S' }">
																									<form:hidden id="items_${ soIdx.index }_${ iLIdx.index }_stocksId" path="soList[${ soIdx.index }].itemsList[${ iLIdx.index }].stocksEntity.stocksId"/>
																									<form:hidden id="items_${ soIdx.index }_${ iLIdx.index }_Product_Stocks_prodQuantity" path="soList[${ soIdx.index }].itemsList[${ iLIdx.index }].productEntity.stocksList[0].prodQuantity" />
																								</c:if>
																								<c:out value="${ soForm.itemsList[iLIdx.index].productEntity.productName }"></c:out>
																							</td>
																							<td style="vertical-align: middle;">
																								<div class="form-group no-margin">
																									<div class="col-sm-12">
																										<form:input type="text" class="form-control text-right" id="inputItemsQuantity" name="inputItemsQuantity" path="soList[${ soIdx.index }].itemsList[${ iLIdx.index }].prodQuantity" placeholder="Enter Quantity" readonly="${ loginContext.soList[ soIdx.index ].salesStatusLookup.lookupKey != 'L016_D' }" data-parsley-type="number" data-parsley-min="1" data-parsley-trigger="change" data-parsley-validquantity="${ soIdx.index }_${ iLIdx.index }" onfocus="this.select()" data-parsley-group="['tab_${ soIdx.index }', 'item_${ soIdx.index }']"></form:input>
																									</div>
																								</div>
																							</td>
																							<td style="vertical-align: middle;">
																								<div class="form-group no-margin">
																									<div class="col-md-12">
																										<c:if test="${ loginContext.soList[ soIdx.index ].salesStatusLookup.lookupKey != 'L016_D' }">
																											<form:hidden id="items_${ soIdx.index }_${ iLIdx.index }_unitCode" path="soList[${ soIdx.index }].itemsList[${ iLIdx.index }].unitCodeLookup.lookupKey" />
																										</c:if>
																										<form:select id="items_${ soIdx.index }_${ iLIdx.index }_unitSelect" class="form-control no-margin" path="soList[${ soIdx.index }].itemsList[${ iLIdx.index }].unitCodeLookup.lookupKey" data-parsley-required="true" data-parsley-trigger="change" disabled="${ loginContext.soList[ soIdx.index ].salesStatusLookup.lookupKey != 'L016_D' }" data-parsley-group="['tab_${ soIdx.index }', 'item_${ soIdx.index }']">
																											<option value=""><spring:message code="common.please_select"></spring:message></option>
																											<c:forEach items="${ loginContext.soList[ soIdx.index ].itemsList[iLIdx.index].productEntity.productUnit }" var="prdUnit">
																												<form:option value="${ prdUnit.unitCodeLookup.lookupKey }"><c:out value="${ prdUnit.unitCodeLookup.lookupValue }"/></form:option>
																											</c:forEach>
																										</form:select>
																									</div>
																								</div>
																							</td>
																							<td style="vertical-align: middle;">
																								<div class="form-group no-margin">
																									<div class="col-sm-12">
																										<form:input type="text" class="form-control text-right" id="inputItemsProdPrice" name="inputItemsProdPrice" path="soList[${ soIdx.index }].itemsList[${ iLIdx.index }].prodPrice" placeholder="Enter Price" readonly="${ loginContext.soList[ soIdx.index ].salesStatusLookup.lookupKey != 'L016_D' }" data-parsley-type="number" data-parsley-min="1" data-parsley-trigger="keyup" onfocus="this.select()" data-parsley-group="['tab_${ soIdx.index }', 'item_${ soIdx.index }']"></form:input>
																									</div>
																								</div>
																							</td>
																							<td>
																								<c:if test="${ loginContext.soList[ soIdx.index ].salesStatusLookup.lookupKey == 'L016_D' }">
																									<button id="removeProdButton" type="submit" class="btn btn-primary pull-right" value="${ iLIdx.index }"><span class="fa fa-minus"></span></button>
																								</c:if>
																							</td>
																							<td style="vertical-align: middle; text-align: right;">
																								<fmt:formatNumber type="number" pattern="##,###.00" value="${ (iL.toBaseQty * iL.prodPrice) }"></fmt:formatNumber>
																							</td>
																						</tr>
																						<c:set var="total" value="${ total + (iL.toBaseQty * iL.prodPrice) }" />
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
																							<spring:message code="sales_jsp.total" text="Total"/>
																						</td>
																						<td width="20%" class="text-right">
																							<fmt:formatNumber type="number" pattern="##,###.00" value="${ total }"></fmt:formatNumber>
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
															<h1 class="panel-title"><spring:message code="sales_jsp.remarks" text="Remarks"/></h1>
														</div>
														<div class="panel-body">
															<div class="row">
																<div class="col-md-12">
																	<div class="form-group">
																		<div class="col-sm-12">
																			<form:textarea class="form-control" path="soList[${ soIdx.index }].salesRemarks" rows="5" readonly="${ loginContext.soList[ soIdx.index ].salesStatusLookup.lookupKey != 'L016_D' }"/>
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
														<c:if test="${ loginContext.soList[ soIdx.index ].salesStatusLookup.lookupKey == 'L016_D' }">
															<button id="cancelButton_${ soIdx.index }" type="submit" class="btn btn-primary pull-right"><spring:message code="common.cancel_button" text="Cancel"/></button>
															<button id="submitButton" type="submit" class="btn btn-primary pull-right" value="${ soIdx.index }"><spring:message code="common.submit_button" text="Submit"/></button>
														</c:if>
														<c:if test="${ loginContext.soList[ soIdx.index ].salesStatusLookup.lookupKey == 'L016_WD' }">
															<button id="cancelButton_${ soIdx.index }" type="submit" class="btn btn-primary pull-right"><spring:message code="sales_jsp.close_button" text="Close"/></button>
															<a id="printButton_${ soIdx.index }" href="${ pageContext.request.contextPath }/sales/t/${ soIdx.index }/generate/${ loginContext.soList[ soIdx.index ].salesId }" target="_blank" class="btn btn-primary pull-right">
																<spring:message code="common.print_button" text="Print"/>
															</a>
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