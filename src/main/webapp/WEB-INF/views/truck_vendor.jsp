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
				window.location.href = ctxpath + "/master/vendor/trucking";
			});
				
			$('input[type="checkbox"][id^="cbx_"]').click(function() {
				var selected = $(this);
				
				$('input[type="checkbox"][id^="cbx_"]').each(function(index, item) {
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
						$('#editTableSelection').attr("href", ctxpath + "/master/vendor/trucking/edit/" + id);	
					} else {
						$('#deleteTableSelection').attr("href", ctxpath + "/master/vendor/trucking/delete/" + id);	
					}						
				}				
			});

			$('#TruckVendorList').DataTable();					
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
					<span class="fa fa-ge fa-fw"></span>&nbsp;<spring:message code="vendor_truck_jsp.title" text="Truck Vendor"/>
				</h1>

				<c:choose>
					<c:when test="${ PAGEMODE == 'PAGEMODE_PAGELOAD' || PAGEMODE == 'PAGEMODE_LIST' || PAGEMODE == 'PAGEMODE_DELETE' }">
						<div class="panel panel-default">
							<div class="panel-heading">
								<h1 class="panel-title">
									<span class="fa fa-ge fa-fw"></span>&nbsp;<spring:message code="vendor_truck_jsp.vendor_truck_list" text="Truck Vendor List"/>
								</h1>
							</div>
							<div class="panel-body">
								<table id="TruckVendorList" class="table table-bordered table-hover display responsive">
									<thead>
										<tr>
											<th width="5%">&nbsp;</th>
											<th width="20%"><spring:message code="vendor_truck_jsp.table.header.vendor_truck_name" text="Vendor Truck Name"/></th>
											<th width="25%"><spring:message code="vendor_truck_jsp.table.header.Vendor_truck_address" text="Vendor Truck Address"/></th>
											<th width="15%"><spring:message code="vendor_truck_jsp.table.header.vendor_truck_bank" text="bank"/></th>
											<th width="10%"><spring:message code="vendor_truck_jsp.table.header.vendor_truck_status" text="status"/></th>
											<th width="25%"><spring:message code="vendor_truck_jsp.table.header.vendor_truck_remarks" text="remarks"/></th>
										</tr>
									</thead>
									<tbody>
										<c:if test="${ not empty TruckVendorList }">
											<c:forEach items="${ TruckVendorList }" var="i" varStatus="storeIdx">
												<tr>
													<td align="center"><input id="cbx_<c:out value="${ i.vendorTruckId }"/>" type="checkbox" value="<c:out value="${ i.vendorTruckId }"/>"/></td>
													<td>
														<strong><c:out value="${ i.vendorTruckName }"></c:out></strong><br/><br/>
													</td>
													<td>
														<c:out value="${ i.vendorTruckAddress }"></c:out>
													</td>
													<td>
														<spring:message code="${ i.truckVendorBankLookup.localeMessageCodes }" text="${ i.truckVendorBankLookup.lookupValue }"/><br/>
														<c:out value="${ i.accNum }"></c:out>
													</td>
													<td>
														<spring:message code="${ i.truckVendorStatusLookup.localeMessageCodes }" text="${ i.truckVendorStatusLookup.lookupValue }"/><br/>
													</td>
													<td>
														<c:out value="${ i.remarks }"/><br/>
													</td>
												</tr>
											</c:forEach>
										</c:if>
									</tbody>
								</table>
								<a id="addNew" class="btn btn-sm btn-primary" href="${pageContext.request.contextPath}/master/vendor/trucking/add"><span class="fa fa-plus fa-fw"></span>&nbsp;<spring:message code="common.add_button" text="Add"/></a>&nbsp;&nbsp;&nbsp;
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
											<span class="fa fa-plus fa-fw fa-2x"></span>&nbsp;<spring:message code="vendor_truck_jsp.add_truck_vendor" text="Add Truck Vendor"/>
										</c:when>
										<c:otherwise>
											<span class="fa fa-edit fa-fw fa-2x"></span>&nbsp;<spring:message code="vendor_truck_jsp.edit_truck_vendor" text="Edit Truck Vendor"/>
										</c:otherwise>
									</c:choose>
								</h1>
							</div>
							<div class="panel-body">
								<form:form id="truckVendorForm" role="form" class="form-horizontal" modelAttribute="truckVendorForm" action="${pageContext.request.contextPath}/master/vendor/trucking/save" data-parsley-validate="parsley"> 
									<form:hidden path="vendorTruckId"/>
									<div class="form-group">
										<label for="inputTruckVendorName" class="col-sm-2 control-label"><spring:message code="vendor_truck_jsp.table.vendor_truck_name" text="Truck Vendor Name"/></label>
										<div class="col-sm-3">
											<form:input type="text" class="form-control" id="inputTruckVendorName" name="inputTruckVendorName" path="vendorTruckName" placeholder="Truck Vendor Name" data-parsley-required="true" data-parsley-length="[6, 30]" data-parsley-trigger="keyup"></form:input>
										</div>
									</div>
									<div class="form-group">
										<label for="inputAddress" class="col-sm-2 control-label"><spring:message code="vendor_truck_jsp.table.Vendor_truck_address" text="Address"/></label>
										<div class="col-sm-5">
											<form:input type="text" class="form-control" id="inputAddress" name="inputAddress" path="vendorTruckAddress"  placeholder="Truck Vendor Address"></form:input>
										</div>
									</div>
									<div class="form-group">
										<label for="inputNpwpNumber" class="col-sm-2 control-label"><spring:message code="vendor_truck_jsp.table.vendor_truck_npwp_number" text="NPWP Number"/></label>
										<div class="col-sm-3">
											<form:input type="text" class="form-control" id="inputNpwpNumber" name="inputNpwpNumber" path="npwpNumber" placeholder="NPWP Number"></form:input>
										</div>
									</div>
									<div class="form-group">
										<label for="inputStoreStatus" class="col-sm-2 control-label"><spring:message code="vendor_truck_jsp.table.vendor_truck_bank" text="Truck Vendor Bank"/></label>
										<div class="col-sm-3">
											<form:select class="form-control" path="truckVendorBankLookup.lookupKey" data-parsley-required="true" data-parsley-trigger="change" placeholder="bank">
												<option value=""><spring:message code="common.please_select" text="Please Select"/></option>
												<c:forEach items="${ bankDDL }" var="i">
													<form:option value="${ i.lookupKey }"><spring:message code="${ i.i18nLookupValue }"></spring:message></form:option>
												</c:forEach>
											</form:select>
										</div>
									</div>
									<div class="form-group">
										<label for="inputAccNumr" class="col-sm-2 control-label"><spring:message code="vendor_truck_jsp.table.vendor_truck_account" text="acc Number"/></label>
										<div class="col-sm-3">
											<form:input type="text" class="form-control" id="inputAccNum" name="inputAccNum" path="accNum" placeholder="accNum" data-parsley-type="number"></form:input>
										</div>
									</div>
									<div class="form-group">
										<label for="inputStoreStatus" class="col-sm-2 control-label"><spring:message code="vendor_truck_jsp.table.vendor_truck_status" text="Truck Vendor Status"/></label>
										<div class="col-sm-3">
											<form:select class="form-control" path="truckVendorStatusLookup.lookupKey" data-parsley-required="true" data-parsley-trigger="change" placeholder="status">
												<option value=""><spring:message code="common.please_select" text="Please Select"/></option>
												<c:forEach items="${ statusDDL }" var="i">
													<form:option value="${ i.lookupKey }"><spring:message code="${ i.i18nLookupValue }"></spring:message></form:option>
												</c:forEach>
											</form:select>
										</div>
									</div>
									<div class="form-group">
										<label for="inputRemarks" class="col-sm-2 control-label"><spring:message code="vendor_truck_jsp.table.vendor_truck_remarks" text="remarks"/></label>
										<div class="col-sm-3">
											<form:input type="text" class="form-control" id="inputRemarks" name="inputRemarks" path="remarks" placeholder="remarks"></form:input>
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