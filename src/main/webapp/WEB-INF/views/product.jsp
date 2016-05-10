<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
	<jsp:include page="/WEB-INF/views/include/headtag.jsp"></jsp:include>
	<script>
		$(document).ready(function() {
			var ctxpath = "${ pageContext.request.contextPath }";
			
			$('#cancelButton').click(function() {
				window.location.href = ctxpath + "/master/product";
			});
			
			$('#submitButton').click(function() {
				var baseUnitExist = false;
				$('input[type="checkbox"][id^="cbxisbase_"]').each(function(index, item) {
					if ($(this).is(":checked")) {
						baseUnitExist = true;
					}
				});
				
				if (!baseUnitExist) {
					jsAlert("Must have at least 1 base unit");
					return false;
				}
			});
			
			$('input[type="checkbox"][id^="cbx_"]').click(function() {
				var selected = $(this);

				if ($(selected).attr("id").split('_')[1] == 'isBase') {
					
				}
				$('input[type="checkbox"][id^="cbx_"]').each(function(index, item) {
					if ($(item).attr("id") != $(selected).attr("id")) { 
						if ($(item).prop("checked")) {
							$(item).prop("checked", false);
						}
					}
				});
			});

			$('#inputImage').fileinput({
				showUpload:false,
				allowedFileExtensions:['jpg', 'gif', 'png']
			});
			
			$('input[type="checkbox"][id^="cbxunit_"]').click(function() {
				var selected = $(this);

				if ($(selected).attr("id").split('_')[1] == 'isBase') {
					
				}
				$('input[type="checkbox"][id^="cbxunit_"]').each(function(index, item) {
					if ($(item).attr("id") != $(selected).attr("id")) { 
						if ($(item).prop("checked")) {
							$(item).prop("checked", false);
						}
					}
				});
			});

			$('input[type="checkbox"][id^="cbxisbase_"]').click(function() {
				var selected = $(this);
				var idx = $(this).attr('id').split('_')[1];
				
				$('#conversionValue_' + idx).val('1');
				
				$('input[type="checkbox"][id^="cbxisbase_"]').each(function(index, item) {
					if ($(item).attr("id") != $(selected).attr("id")) { 
						if ($(item).prop("checked")) {
							$(item).prop("checked", false);
						}
					}
				});
			});

			$('#editTableSelection, #deleteTableSelection').click(function() {
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
						$('#editTableSelection').attr("href", ctxpath + "/master/product/edit/" + id);
					} else {
						$('#deleteTableSelection').attr("href", ctxpath + "/master/product/delete/" + id);
					}						
				}				
			});
			
			$('#plusUnit, #minusUnit').click(function() {
				var button = $(this).attr('id');
				var id = "";
				
				if (button == "plusUnit") {
					$('#productForm').attr('action', ctxpath + "/master/product/addunit/0");
				} else {
					$('input[type="checkbox"][id^="cbxunit_"]').each(function(index, item) {
						if ($(item).prop('checked')) {
							id = $(item).attr("value");
						}
					});	
					
					if (id == "") { jsAlert('Please select at least 1 unit'); return false; }
					
					$('#productForm').attr('action', ctxpath + "/master/product/removeunit/" + id);
				}
			});
			
			$('#productListTable').DataTable();
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
					<span class="fa fa-cubes fa-fw"></span>&nbsp;<spring:message code="product_jsp.title" text="Product"/>
				</h1>

				<c:choose>
					<c:when test="${ PAGEMODE == 'PAGEMODE_PAGELOAD' || PAGEMODE == 'PAGEMODE_LIST' || PAGEMODE == 'PAGEMODE_DELETE' }">
						<div class="panel panel-default">
							<div class="panel-heading">
								<h1 class="panel-title">
									<span class="fa fa-cubes fa-fw fa-2x"></span>&nbsp;<spring:message code="product_jsp.product_list" text="Product List"/>
								</h1>
							</div>
							<div class="panel-body">
								<table id="productListTable" class="table table-bordered table-hover display dt-responsive nowrap" style="width: 100%; border-collapse: separate;">
									<thead>
										<tr>
											<th width="5%">&nbsp;</th>
											<th width="10%"><spring:message code="product_jsp.table.header.type" text="Type"/></th>
											<th width="10%"><spring:message code="product_jsp.table.header.code" text="Code"/></th>
											<th width="25%"><spring:message code="product_jsp.table.header.name" text="Name"/></th>
											<th width="40%"><spring:message code="product_jsp.table.header.description" text="Description"/></th>
											<th width="10%"><spring:message code="product_jsp.table.header.status" text="Status"/></th>
										</tr>
									</thead>
									<tbody>
										<c:if test="${ not empty productList }">
											<c:forEach items="${ productList }" var="i" varStatus="productIdx">
												<tr>
													<td align="center"><input id="cbx_<c:out value="${ i.productId }"/>" type="checkbox" value="<c:out value="${ i.productId }"/>"/></td>
													<td><spring:message code="${ i.productTypeLookup.i18nLookupValue }" text="${ i.productTypeLookup.lookupValue }"/></td>
													<td><c:out value="${ i.shortCode }"></c:out></td>
													<td><c:out value="${ i.productName }"></c:out></td>
													<td><c:out value="${ i.productDesc }"></c:out></td>
													<td><spring:message code="${ i.productStatusLookup.i18nLookupValue }" text="${ i.productStatusLookup.lookupValue }"/></td>
												</tr>
											</c:forEach>
										</c:if>
									</tbody>
								</table>
								<a id="addNew" class="btn btn-sm btn-primary" href="${pageContext.request.contextPath}/master/product/add"><span class="fa fa-plus fa-fw"></span>&nbsp;<spring:message code="common.add_button" text="Add"/></a>&nbsp;&nbsp;&nbsp;
								<a id="editTableSelection" class="btn btn-sm btn-primary" href=""><span class="fa fa-edit fa-fw"></span>&nbsp;<spring:message code="common.edit_button" text="Edit"/></a>&nbsp;&nbsp;&nbsp;
								<a id="deleteTableSelection" class="btn btn-sm btn-primary" href=""><span class="fa fa-close fa-fw"></span>&nbsp;<spring:message code="common.delete_button" text="Delete"/></a>
							</div>
						</div>
					</c:when>
					<c:when test="${ PAGEMODE == 'PAGEMODE_ADD' || PAGEMODE == 'PAGEMODE_EDIT' }">
						<div class="panel panel-default">
							<div class="panel-heading">
								<h1 class="panel-title">
									<c:choose>
										<c:when test="${ PAGEMODE == 'PAGEMODE_ADD' }">
											<span class="fa fa-plus fa-fw fa-2x"></span>&nbsp;<spring:message code="product_jsp.add_product" text="Add Product"/>
										</c:when>
										<c:otherwise>
											<span class="fa fa-edit fa-fw fa-2x"></span>&nbsp;<spring:message code="product_jsp.edit_product" text="Edit Product"/>
										</c:otherwise>
									</c:choose>
								</h1>
							</div>
							<div class="panel-body">
								<form:form id="productForm" role="form" class="form-horizontal" modelAttribute="productForm" action="${pageContext.request.contextPath}/master/product/save" enctype="multipart/form-data" data-parsley-validate="parsley" data-parsley-excluded="input[type=file]">
									<form:hidden path="productId"/>
									<div class="form-group">
										<label for="inputProductType" class="col-sm-2 control-label"><spring:message code="product_jsp.producttype" text="Product Type"/></label>
										<div class="col-sm-3">
											<form:select class="form-control" path="productTypeLookup.lookupKey" data-parsley-required="true" data-parsley-trigger="change">
												<option value=""><spring:message code="common.please_select" text="Please Select"/></option>
												<c:forEach items="${ productTypeDDL }" var="i">
													<form:option value="${ i.lookupKey }"><spring:message code="${ i.i18nLookupValue }" text="${ i.lookupValue }"></spring:message></form:option>
												</c:forEach>
											</form:select>
										</div>
									</div>
									<div class="form-group">
										<label for="inputShortCode" class="col-sm-2 control-label"><spring:message code="product_jsp.shortcode" text="Short Code"/></label>
										<div class="col-sm-2">
											<form:input type="text" class="form-control" id="inputShortCode" name="inputShortCode" path="shortCode" placeholder="Short Code" data-parsley-required="true" data-parsley-length="[1, 10]" data-parsley-trigger="keyup"></form:input>
										</div>
									</div>
									<div class="form-group">
										<label for="inputProductName" class="col-sm-2 control-label"><spring:message code="product_jsp.productname" text="Product Name"/></label>
										<div class="col-sm-4">
											<form:input type="text" class="form-control" id="inputProductName" name="inputProductName" path="productName" placeholder="Enter Product Name" data-parsley-required="true" data-parsley-length="[3, 30]" data-parsley-trigger="keyup"></form:input>
										</div>
									</div>
									<div class="form-group">
										<label for="inputImage" class="col-sm-2 control-label"><spring:message code="product_jsp.image" text="Image"/></label>
										<div class="col-sm-6">
											<c:if test="${ PAGEMODE == 'PAGEMODE_EDIT' }">
												<c:if test="${ not empty productForm.imagePath }">
													<c:set var="baseURL" value="${ pageContext.request.scheme }://${ pageContext.request.serverName }:${ pageContext.request.serverPort }" />
													<img class="img-responsive" width="150" height="150" src="${ baseURL }/cdn/${ productForm.imagePath }"/>
												</c:if>
												<form:hidden path="imagePath"></form:hidden>
											</c:if>
											<form:input type="file" class="form-control file" id="inputImage" name="inputImage" path="imageBinary" data-show-upload="false"></form:input>
										</div>
									</div>
									<div class="form-group">
										<label for="inputProductDesc" class="col-sm-2 control-label"><spring:message code="product_jsp.description" text="Description"/></label>
										<div class="col-sm-6">
											<form:input type="text" class="form-control" id="inputProductDesc" name="inputProductDesc" path="productDesc" placeholder="Enter Description"></form:input>
										</div>
									</div>
									<div class="form-group">
										<label for="inputStatus" class="col-sm-2 control-label"><spring:message code="product_jsp.status" text="Status"/></label>
										<div class="col-sm-3">
											<form:select class="form-control" path="productStatusLookup.lookupKey" data-parsley-required="true" data-parsley-trigger="change">
												<option value=""><spring:message code="common.please_select" text="Please Select"/></option>
												<c:forEach items="${ statusDDL }" var="i">
													<form:option value="${ i.lookupKey }"><spring:message code="${ i.i18nLookupValue }" text="${ i.lookupValue }"></spring:message></form:option>
												</c:forEach>
											</form:select>
										</div>
									</div>
									<div class="form-group">
										<label for="inputUnit" class="col-sm-2 control-label"><spring:message code="product_jsp.unit" text="Unit"/></label>
										<div class="col-sm-10">
											<div class="panel panel-default">
												<table class="table table-bordered table-hover">
													<thead>
														<tr>
															<th width="5%">&nbsp;</th>
															<th width="20%"><spring:message code="product_jsp.unit.conversion_unit" text="Conversion Unit"/></th>
															<th width="10%" style="text-align: center;"><spring:message code="product_jsp.unit.base_unit" text="Base Unit"/></th>
															<th width="15%"><spring:message code="product_jsp.unit.conversion_value" text="Conversion Value"/></th>
															<th width="35%"><spring:message code="product_jsp.unit.remarks" text="Remarks"/></th>
														</tr>
													</thead>
													<tbody>
														<c:forEach items="${ productForm.productUnit }" varStatus="prodUnitIdx">
															<tr>
																<td align="center">
																	<form:hidden path="productUnit[${ prodUnitIdx.index }].productUnitId"/>
																	<input id="cbxunit_<c:out value="${ productForm.productUnit[prodUnitIdx.index].productUnitId }"/>" type="checkbox" value="<c:out value="${ prodUnitIdx.index }"/>"/>
																</td>
																<td>
																	<form:select class="form-control" path="productUnit[${ prodUnitIdx.index }].unitCodeLookup.lookupKey" data-parsley-required="true" data-parsley-trigger="change">
																		<option value=""><spring:message code="common.please_select" text="Please Select"/></option>
																		<c:forEach items="${ unitDDL }" var="i">
																			<form:option value="${ i.lookupKey }"><spring:message code="${ i.i18nLookupValue }"></spring:message></form:option>
																		</c:forEach>
																	</form:select>
																</td>
																<td class="center-align">
																	<form:checkbox id="cbxisbase_${ prodUnitIdx.index }" path="productUnit[${ prodUnitIdx.index }].isBaseUnit"></form:checkbox>
																</td>
																<td>
																	<form:input id="conversionValue_${ prodUnitIdx.index }" type="text" class="form-control" path="productUnit[${ prodUnitIdx.index }].conversionValue" placeholder="Enter Value" data-parsley-required="true"></form:input>
																</td>
																<td>
																	<form:input type="text" class="form-control" path="productUnit[${ prodUnitIdx.index }].unitRemarks" placeholder="Enter Remarks"></form:input>
																</td>
															</tr>
														</c:forEach>
													</tbody>
												</table>
												<div class="panel-footer no-padding">
													<div class="btn-toolbar">
														<button id="plusUnit" type="submit" class="btn btn-primary btn-xs"><span class="fa fa-plus fa-fw"></span></button>
														<button id="minusUnit" type="submit" class="btn btn-primary btn-xs"><span class="fa fa-minus fa-fw"></span></button>
													</div>
												</div>
											</div>
										</div>
									</div>
									<div class="col-md-7 col-offset-md-5">
										<div class="btn-toolbar">
											<button id="cancelButton" type="reset" class="btn btn-primary pull-right"><spring:message code="common.cancel_button" text="Cancel"/></button>
											<button id="submitButton" type="submit" class="btn btn-primary pull-right"><spring:message code="common.submit_button" text="Submit"/></button>
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
