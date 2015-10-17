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
		var tabCount = "${ loginContext.poList.size() }";
		var activetab;
		var productSelect;

		$('.poCreatedDate, .shippingDate').datetimepicker({ format:'d-m-Y', timepicker:false });
		
		$('.poCreatedDate, .shippingDate').on('dp.change dp.show',function(e) {
			$(this).parsley().validate();
		});

		$('[id^="removeProdButton_"]').click(function() {
			activetab = $(".nav-tabs li.active").attr("id");
			id = $(this).val();
			$('#poForm').attr('action', ctxpath + "/po/removeitems/" + activetab + "/" + id);
		});

		$('button[id^="addProdButton"]').click(function() {
			activetab = $(".nav-tabs li.active").attr("id");
			productSelect = $("#productSelect" + activetab).val();
						
			$('#poForm').parsley().validate('poTab' + activetab);
			
			if (false == $('#poForm').parsley().isValid('poTab' + activetab)) {		
				return false;
            } else {            	
            	$('#poForm').parsley().destroy();
            	$('#poForm').attr('action', ctxpath + "/po/additems/" + activetab + "/" + productSelect);
            }
		});

		$("button[id^='submitButton']").click(function() {
			$('#poForm').parsley({
			    excluded: '[id^="productSelect"]'
			}).validate();
			
			if(false == $('#poForm').parsley().isValid()) {
				return false;
            } else {
				activetab = $(".nav-tabs li.active").attr("id");
				$('#poForm').attr('action', ctxpath + "/po/save/" + activetab);
            }
		});

		$('[id^="cancelButton"]').click(function() {
			activetab = $(".nav-tabs li.active").attr("id");
			$('#poForm').attr("action", ctxpath + "/po/cancel/" + activetab);
		});

		$('#list a[href="#tab' + tabCount + '"]').tab('show');

		$('select[id^="inputSupplierId"]').change(function() {
			var tabIdx = $(this).attr('id').replace("inputSupplierId", "");
			
			if ($('select[id="inputSupplierId' + tabIdx + '"]').val() != "") {
				$.ajax({
					url : ctxpath+ "/po/retrieve/supplier",
					data : 'supplierId=' + encodeURIComponent($('select[id="inputSupplierId' + tabIdx + '"]').val()),
					type : "GET",
					success : function(response) {
						var obj = JSON.parse(JSON.stringify(response, null, 4));
						
						var htmlTag = '<strong>' + obj.supplierName + '</strong>';
						$('button[id="supplierTooltip' + tabIdx +'"]').tooltip('hide').attr('data-original-title', htmlTag).tooltip('fixTitle');
						
						var prodList = '';
						for (var i=0; i<obj.prodList.length; i++) {
							prodList += obj.prodList[i].productId + ',';
						}
						
						$('#supplierProduct' + tabIdx + '').val(prodList.substring(0, prodList.length - 1));
					},
					error : function(xhr, status, error) {
						alert(xhr.responseText);
					}
				});
			}
		});
		
		$('#addTab').click(function() {
			activetab = $(".nav-tabs li.active").attr("id");
			
			$('#poForm').parsley({
				excluded: '[id^="productSelect"]'
			}).validate();
			
			if (false == $('#poForm').parsley().isValid('poTab' + activetab, true)) { return false; }
			
			activetab = $(".nav-tabs li.active").attr("id");
			$('#addTab').attr("href", ctxpath + "/po/addpoform");
		});		

		window.Parsley.addValidator('validprod', function (value, idx) {			
			var pL = $('#supplierProduct' + idx + '').val();
			
			if (pL.length == 0) return true;
			
			var exist = false;			
			var pLL = pL.split(',');
			
			for(var i=0; i<pLL.length; i++) {
				if (pLL[i] == value) {
					exist = true;
				}
			}
			
			return exist;
		}, 32)
		.addMessage('en', 'validprod', 'Selected Product is not valid with selected Supplier')
		.addMessage('id', 'validprod', 'Produk tidak sesuai dengan Supplier terpilih');
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
					<div class="alert alert-danger alert-dismissible collapse"
						role="alert">
						<button type="button" class="close" data-dismiss="alert">
							<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
						</button>
						<h4>
							<strong>Warning!</strong>
						</h4>
						<br> ${errorMessageText}
					</div>
				</c:if>
				
				<div id="jsAlerts"></div>
				
				<h1>
					<span class="fa fa-truck fa-fw"></span>&nbsp;<spring:message code="po_jsp.title" text="Purchase Orders"/>
				</h1>
				
				<div class="panel panel-default">
					<div class="panel-heading">
						<h1 class="panel-title">
							<c:choose>
								<c:when test="${ PAGEMODE == 'PAGEMODE_ADD' }">
									<span class="fa fa-truck fa-fw fa-2x"></span>&nbsp;<spring:message code="po_jsp.subtitle" text="New Purchase Order"/>
								</c:when>
							</c:choose>
						</h1>
					</div>
					<div class="panel-body">
						<form:form id="poForm" role="form" class="form-horizontal" modelAttribute="loginContext">
							<ul id="list" class="nav nav-tabs" role="tablist">
								<c:forEach items="${ loginContext.poList }" var="poForm" varStatus="poIdx">
									<li role="presentation" class="" id="${ poIdx.index }">
										<a href="#tab${poIdx.index+1}" aria-controls="tab${poIdx.index+1}" role="tab" data-toggle="tab"><span class="fa fa-asterisk fa-fw"></span>
											&nbsp;<spring:message code="po_jsp.new_po" text="New PO"/>&nbsp;${ poIdx.index + 1 }
										</a>
									</li>
								</c:forEach>
								<li id="last">
									<a id="addTab" class="btn btn-xs btn-default pull-right" href="#"><span class="glyphicon glyphicon-plus"></span></a>
								</li>
							</ul>
							<div class="tab-content">
								<br />
								<c:forEach items="${ loginContext.poList }" var="poForm" varStatus="poIdx">
									<div role="tabpanel" class="tab-pane" id="tab${ poIdx.index + 1 }">
										<form:hidden path="poList[${ poIdx.index }].poId" />
										<div class="row">
											<div class="col-md-12">
												<div class="panel panel-default">
													<div class="panel-body">
														<div class="row">
															<div class="col-md-7">
																<div class="form-group">
																	<label for="poCode" class="col-sm-2 control-label"><spring:message code="po_jsp.po_code" text="PO Code"/></label>
																	<div class="col-sm-5">
																		<form:input type="text" class="form-control poCode" id="poCode${ poIdx.index }" path="poList[${ poIdx.index }].poCode" placeholder="Enter PO Code" readonly="true"></form:input>
																	</div>
																</div>
																<div class="form-group">
																	<label for="inputPOType${ poIdx.index }" class="col-sm-2 control-label"><spring:message code="po_jsp.po_type" text="PO Type"/></label>
																	<div class="col-sm-8">
																	 	<c:choose>
																	    	<c:when test="${ loginContext.poList[poIdx.index].poStatus == 'L013_WA' }">
																	        	<form:hidden path="poList[${ poIdx.index }].poType"/>
																	    		<form:input type="text" class="form-control" id="poType_${ poIdx.index }" path="poList[${ poIdx.index }].poTypeLookup.lookupValue" placeholder="Enter PO Code" readonly="true"></form:input>
																	    	</c:when>
																	    	<c:otherwise>
																				<form:select class="form-control" id="inputPOType${ poIdx.index }" path="poList[${ poIdx.index }].poType" disabled="${ loginContext.poList[poIdx.index].poStatus == 'L013_WA' }" data-parsley-required="true" data-parsley-trigger="change" data-parsley-group="poTab${ poIdx.index }">
																					<option value=""><spring:message code="common.please_select" text="Please Select"/></option>
																					<form:options items="${ poTypeDDL }" itemValue="lookupKey" itemLabel="lookupValue" />
																				</form:select>
																				</c:otherwise>
																		</c:choose>
																	</div>
																</div>
																<div class="form-group">
																	<label for="inputSupplierId${ poIdx.index }" class="col-sm-2 control-label"><spring:message code="po_jsp.supplier" text="Supplier"/></label>
																	<div class="col-sm-9">
																	<c:choose>
																	    <c:when test="${ loginContext.poList[poIdx.index].poStatus == 'L013_WA' }">
																	        <form:hidden path="poList[${ poIdx.index }].supplierId"/>
																	    	<form:input type="text" class="form-control" id="supplier_${ poIdx.index }" path="poList[${ poIdx.index }].supplierLookup.supplierName" readonly="true"></form:input>
																	    </c:when>
																	    <c:otherwise>
																			<form:select class="form-control supplierId" id="inputSupplierId${ poIdx.index }" path="poList[${ poIdx.index }].supplierId" disabled="${ loginContext.poList[poIdx.index].poStatus == 'L013_WA' }" data-parsley-required="true" data-parsley-trigger="change" data-parsley-group="poTab${ poIdx.index }">
																				<option value=""><spring:message code="common.please_select" text="Please Select"/></option>
																				<form:options items="${ supplierSelectionDDL }" itemValue="supplierId" itemLabel="supplierName" />
																			</form:select>
																		</c:otherwise>
																	</c:choose>
																	</div>
																	<div class="col-sm-1">
																		<button id="supplierTooltip${ poIdx.index }" type="button" class="btn btn-default" data-toggle="tooltip" data-trigger="hover" data-html="true" data-placement="right" data-title="">
																			<span class="fa fa-external-link fa-fw"></span>
																		</button>
																		<input type="hidden" id="supplierProduct${ poIdx.index }" value=""/>
																	</div>
																</div>
															</div>
															<div class="col-md-5">
																<div class="form-group">
																	<label for="poCreatedDate" class="col-sm-3 control-label"><spring:message code="po_jsp.po_date" text="PO Date"/></label>
																	<div class="col-sm-9">
																		<form:input type="text" class="form-control poCreatedDate" id="poCreatedDate${ poIdx.index }" path="poList[${ poIdx.index }].poCreatedDate" placeholder="Enter PO Date" readonly="${ loginContext.poList[poIdx.index].poStatus == 'L013_WA' }" data-parsley-required="true" data-parsley-trigger="change" data-parsley-group="poTab${ poIdx.index }"></form:input>
																	</div>
																</div>
																<div class="form-group">
																	<label for="inputPOStatus${ poIdx.index }" class="col-sm-3 control-label"><spring:message code="po_jsp.po_status" text="Status"/></label>
																	<div class="col-sm-9">
																		<form:hidden path="poList[${ poIdx.index }].poStatus" />
																		<label id="inputPOStatus${ poIdx.index }" class="control-label">
																			<spring:message code="${ poForm.statusLookup.i18nLookupValue }" text="${ poForm.statusLookup.lookupValue }"></spring:message>
																		</label>
																	</div>
																</div>
															</div>
														</div>
														<hr>
														<div class="row">
															<div class="col-md-7">
																<div class="form-group">
																	<label for="shippingDate${ poIdx.index }" class="col-sm-2 control-label"><spring:message code="po_jsp.shipping_date" text="Shipping Date"/></label>
																	<div class="col-sm-5">
																		<form:input type="text" class="form-control shippingDate" id="shippingDate${ poIdx.index }" path="poList[${ poIdx.index }].shippingDate" placeholder="Enter Shipping Date" readonly="${ loginContext.poList[poIdx.index].poStatus == 'L013_WA' }" data-parsley-required="true" data-parsley-trigger="change" data-parsley-group="poTab${ poIdx.index }"></form:input>
																	</div>
																</div>
																<div class="form-group">
																	<label for="inputWarehouseId${ poIdx.index }" class="col-sm-2 control-label"><spring:message code="po_jsp.warehouse" text="Warehouse"/></label>
																	<div class="col-sm-8">
																		<c:choose>
																		    <c:when test="${ loginContext.poList[poIdx.index].poStatus == 'L013_WA' }">
																		        <form:hidden path="poList[${ poIdx.index }].warehouseId"/>
																		    	<form:input type="text" class="form-control" id="warehouse_${ poIdx.index }" path="poList[${ poIdx.index }].warehouseLookup.warehouseName" readonly="true"></form:input>
																		    </c:when>
																		    <c:otherwise>
																				<form:select class="form-control warehouseId" id="inputWarehouseId${ poIdx.index }" path="poList[${ poIdx.index }].warehouseId" disabled="${ loginContext.poList[poIdx.index].poStatus == 'L013_WA' }" data-parsley-required="true" data-parsley-trigger="change" data-parsley-group="poTab${ poIdx.index }">
																					<option value=""><spring:message code="common.please_select" text="Please Select"/></option>
																					<form:options items="${ warehouseSelectionDDL }" itemValue="warehouseId" itemLabel="warehouseName" />
																				</form:select>
																			</c:otherwise>
																		</c:choose>
																	</div>
																</div>
															</div>
															<div class="col-md-5">
																<div class="form-group">
																	<label for="inputEmpty" class="col-sm-3 control-label"></label>
																	<div class="col-sm-9"></div>
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
														<h1 class="panel-title"><spring:message code="po_jsp.transactions" text="Transactions"/></h1>
													</div>
													<div class="panel-body">
														<div class="row">
															<div class="col-md-11" id="product-select${ poIdx.index }">
																<div class="form-group" style="padding-left: 2%">
																	<select id="productSelect${ poIdx.index }" class="form-control productSelect" data-parsley-required="true" data-parsley-trigger="change" data-parsley-group="poTab${ poIdx.index }" data-parsley-validprod="${ poIdx.index }">
																		<option value=""><spring:message code="common.please_select" text="Please Select"/></option>
																		<c:forEach items="${ productSelectionDDL }" var="psddl">
																			<option value="${ psddl.productId }">${ psddl.productName }</option>
																		</c:forEach>
																	</select>
																</div>
															</div>
															<div class="col-md-1">
															<c:if test="${ loginContext.poList[poIdx.index].poStatus == 'L013_D' }">
																<button id="addProdButton${ poIdx.index }" type="submit" class="btn btn-primary pull-right" >
																	<span class="fa fa-plus"></span>
																</button>
															</c:if>
															</div>
														</div>
														<br />
														<div class="row">
															<div class="col-md-12">
																<table id="itemsListTable" class="table table-bordered table-hover">
																	<thead>
																		<tr>
																			<th width="30%"><spring:message code="po_jsp.table.item.header.product_name" text="Product Name"/></th>
																			<th width="15%"><spring:message code="po_jsp.table.item.header.quantity" text="Quantity"/></th>
																			<th width="15%" class="text-right"><spring:message code="po_jsp.table.item.header.unit" text="Unit"/></th>
																			<th width="15%" class="text-right"><spring:message code="po_jsp.table.item.header.price_unit" text="Price/Base Unit"/></th>
																			<th width="5%">&nbsp;</th>
																			<th width="20%" class="text-right"><spring:message code="po_jsp.table.item.header.total_price" text="Total Price"/></th>
																		</tr>
																	</thead>
																	<tbody>
																		<c:set var="total" value="${0}" />
																		<c:forEach items="${ loginContext.poList[poIdx.index].itemsList }" var="iL" varStatus="iLIdx">
																			<tr>
																				<td style="vertical-align: middle;">
																					<form:hidden path="poList[${ poIdx.index }].itemsList[${ iLIdx.index }].itemsId" />
																					<form:hidden path="poList[${ poIdx.index }].itemsList[${ iLIdx.index }].productId" />
																					<form:hidden path="poList[${ poIdx.index }].itemsList[${ iLIdx.index }].baseUnitCode" />
																					<form:hidden path="poList[${ poIdx.index }].itemsList[${ iLIdx.index }].toBaseValue" />
																					<form:hidden path="poList[${ poIdx.index }].itemsList[${ iLIdx.index }].toBaseQty" />
																					<label>
																						<c:out value="${ iL.productLookup.productName }"></c:out>
																					</label>
																				</td>
																				<td class="center-align">
																					<div class="form-group no-margin">
																						<div class="col-sm-12">
																							<form:input type="text" class="form-control text-right no-margin" id="inputItemsQuantity${ poIdx.index }" path="poList[${ poIdx.index }].itemsList[${ iLIdx.index }].prodQuantity" placeholder="Enter Quantity" readonly="${ loginContext.poList[poIdx.index].poStatus == 'L013_WA' }" data-parsley-type="number" min="1" data-parsley-trigger="keyup" data-parsley-group="poTab${ poIdx.index }" onfocus="this.select()"></form:input>
																						</div>
																					</div>
																				</td>
																				<td style="vertical-align: middle;">
																					<div class="form-group no-margin">
																						<div class="col-md-12">
																							<c:if test="${ loginContext.poList[poIdx.index].poStatus == 'L013_WA' }">
																								<form:hidden path="poList[${ poIdx.index }].itemsList[${ iLIdx.index }].unitCode" />
																							</c:if>																							
																							<form:select class="form-control no-margin" path="poList[${ poIdx.index }].itemsList[${ iLIdx.index }].unitCode" data-parsley-required="true" data-parsley-trigger="change" disabled="${ loginContext.poList[poIdx.index].poStatus == 'L013_WA' }" data-parsley-group="poTab${ poIdx.index }">
																								<option value=""><spring:message code="common.please_select"></spring:message></option>
																								<c:forEach items="${ loginContext.poList[poIdx.index].itemsList[iLIdx.index].productLookup.productUnit }" var="prdUnit">
																									<form:option value="${ prdUnit.unitCode }">
																										<c:choose>
																											<c:when test="${ prdUnit.baseUnit }">
																												<c:out value="${ prdUnit.unitCodeLookup.lookupValue }"/>*
																											</c:when>
																											<c:otherwise>
																												<c:out value="${ prdUnit.unitCodeLookup.lookupValue }"/>
																											</c:otherwise>
																										</c:choose>
																									</form:option>
																								</c:forEach>
																							</form:select>
																						</div>
																					</div>
																				</td>
																				<td style="vertical-align: middle;">
																					<div class="form-group no-margin">
																						<div class="col-sm-12">
																							<form:input type="text" class="form-control text-right" id="inputItemsProdPrice${ poIdx.index }" path="poList[${ poIdx.index }].itemsList[${ iLIdx.index }].prodPrice" placeholder="Enter Price" readonly="${ loginContext.poList[poIdx.index].poStatus == 'L013_WA' }" data-parsley-type="number" data-parsley-trigger="keyup" data-parsley-group="poTab${ poIdx.index }" onfocus="this.select()"></form:input>
																						</div>
																					</div>
																				</td>
																				<td style="vertical-align: middle;">
																				<c:if test="${ loginContext.poList[poIdx.index].poStatus == 'L013_D' }">
																					<button id="removeProdButton_${ iLIdx.index }" type="submit" value="${ iLIdx.index }" class="btn btn-primary pull-right">
																						<span class="fa fa-minus"></span>
																					</button>
																				</c:if>
																				</td>
																				<td style="vertical-align: middle;" class="text-right">
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
																<table id="itemsTotalListTable" class="table table-bordered">
																	<tbody>
																		<tr>
																			<td width="80%" class="text-right"><spring:message code="po_jsp.total" text="Total"/></td>
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
										<div class="row">
											<div class="col-md-12">
												<div class="panel panel-default">
													<div class="panel-heading">
														<h1 class="panel-title"><spring:message code="po_jsp.remarks" text="Remarks"/></h1>
													</div>
													<div class="panel-body">
														<div class="row">
															<div class="col-md-12">
																<div class="form-group">
																	<div class="col-sm-12">
																		<form:textarea id="poRemarks${ poIdx.index }" class="form-control" path="poList[${ poIdx.index }].poRemarks" rows="5" readonly="${ loginContext.poList[poIdx.index].poStatus == 'L013_WA' }"/>
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
												    <c:if test="${ loginContext.poList[poIdx.index].poStatus == 'L013_D' }">
														<button id="cancelButton${ poIdx.index }" type="submit" class="btn btn-primary pull-right"><spring:message code="common.cancel_button" text="Cancel"/></button>
														<button id="submitButton${ poIdx.index }" type="submit" class="btn btn-primary pull-right"><spring:message code="common.submit_button" text="Submit"/></button>
													</c:if>
													<c:if test="${ loginContext.poList[poIdx.index].poStatus == 'L013_WA' }">
														<button id="cancelButton${ poIdx.index }" type="submit" class="btn btn-primary pull-right"><spring:message code="po_jsp.close_button" text="Close"/></button>
													</c:if>
												</div>
											</div>
										</div>
									</div>
								</c:forEach>
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
