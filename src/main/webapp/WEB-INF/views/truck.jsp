<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<!DOCTYPE html>
<html>
<head>
	<jsp:include page="/WEB-INF/views/include/headtag.jsp"></jsp:include>
	<script>
		$(document).ready(function() {
			var ctxpath = "${ pageContext.request.contextPath }";
			
			$('#cancelButton').click(function() {				
				window.location.href = ctxpath + "/master/truck";
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
			
			$('#editTableSelection, #deleteTableSelection, #maintenanceTableSelection').click(function() {
				var id = "";
				var button = $(this).attr('id');
								
				$('input[type="checkbox"][id^="cbx_"]').each(function(index, item) {
					if ($(item).prop('checked')) {
						id = $(item).attr("value");	
					}
				});
				
				if (id == "") {
					jsAlert("Please select at least 1 truck");
					return false;	
				} else {
					if (button == 'editTableSelection') {
						$('#editTableSelection').attr("href", ctxpath + "/master/truck/edit/" + id);	
					} else if (button == 'maintenanceTableSelection') {
						$('#maintenanceTableSelection').attr("href", ctxpath + "/master/truck/maintenance/tr/" + id);
					} else {
						$('#deleteTableSelection').attr("href", ctxpath + "/master/truck/delete/" + id);	
					}						
				}				
			});
      
			$('#inputKirDate').datetimepicker({ format:'d-m-Y', timepicker:false });
			$('#inputKirDate').on('dp.change dp.show',function(e) {
				$(this).parsley().validate();
			});
			
			$('#truckListTable').DataTable();			
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
					<span class="fa fa-truck fa-flip-horizontal fa-fw"></span>&nbsp;<spring:message code="truck_jsp.title" text="Truck"/>
				</h1>

				<c:choose>
					<c:when test="${ PAGEMODE == 'PAGEMODE_PAGELOAD' || PAGEMODE == 'PAGEMODE_LIST' || PAGEMODE == 'PAGEMODE_DELETE' }">
						<div class="panel panel-default">
							<div class="panel-heading">
								<h1 class="panel-title">
									<span class="fa fa-truck fa-flip-horizontal fa-fw fa-2x"></span>&nbsp;<spring:message code="truck_jsp.truck_list" text="Truck List"/>
								</h1>
							</div>
							<div class="panel-body">
								<table id="truckListTable" class="table table-bordered table-hover display responsive">
									<thead>
										<tr>
											<th width="5%">&nbsp;</th>
											<th width="10%"><spring:message code="truck_jsp.table.header.truck_type" text="Truck Type"/></th>
											<th width="10%"><spring:message code="truck_jsp.table.header.weight_type" text="Weight Type"/></th>
											<th width="10%"><spring:message code="truck_jsp.table.header.plate_number" text="Plate Number"/></th>
											<th width="10%"><spring:message code="truck_jsp.table.header.kir_date" text="KIR Date"/></th>
											<th width="15%"><spring:message code="truck_jsp.table.header.driver" text="Driver"/></th>
											<th width="15%"><spring:message code="truck_jsp.table.header.status" text="Status"/></th>
											<th width="30%"><spring:message code="truck_jsp.table.header.remarks" text="Remarks"/></th>
										</tr>
									</thead>
									<tbody>
										<c:if test="${not empty truckList}">
											<c:forEach items="${ truckList }" var="i" varStatus="truckIdx">
												<tr>
													<td align="center"><input id="cbx_<c:out value="${ i.truckId }"/>" type="checkbox" value="<c:out value="${ i.truckId }"/>"/></td>
													<td><c:out value="${ i.truckTypeLookup.lookupValue }"></c:out></td>
													<td><c:out value="${ i.weightTypeLookup.lookupValue }"></c:out></td>
													<td><c:out value="${ i.plateNumber }"></c:out></td>
													<td><c:out value="${ i.kirDate }"></c:out></td>
													<td><c:out value="${ i.driver }"></c:out></td>
													<td><spring:message code="${ i.truckStatusLookup.localeMessageCodes }" text="${ i.truckStatusLookup.lookupValue }"></spring:message></td>
													<td><c:out value="${ i.remarks }"></c:out></td>
												</tr>
											</c:forEach>
										</c:if>
									</tbody>
								</table>
								<a id="addNew" class="btn btn-sm btn-primary" href="${pageContext.request.contextPath}/master/truck/add"><span class="fa fa-plus fa-fw"></span>&nbsp;<spring:message code="common.add_button" text="Add"/></a>&nbsp;&nbsp;&nbsp;
								<a id="editTableSelection" class="btn btn-sm btn-primary" href=""><span class="fa fa-edit fa-fw"></span>&nbsp;<spring:message code="common.edit_button" text="Edit"/></a>&nbsp;&nbsp;&nbsp;
								<a id="deleteTableSelection" class="btn btn-sm btn-primary" href=""><span class="fa fa-close fa-fw"></span>&nbsp;<spring:message code="common.delete_button" text="Delete"/></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<a id="maintenanceTableSelection" class="btn btn-sm btn-primary" href=""><span class="fa fa-gears fa-fw"></span>&nbsp;<spring:message code="common.maintenance_button" text="Maintenance"/></a>
							</div>
						</div>
					</c:when>
					<c:when test="${ PAGEMODE == 'PAGEMODE_ADD' || PAGEMODE == 'PAGEMODE_EDIT' }">
						<div class="panel panel-default">
							<div class="panel-heading">
								<h1 class="panel-title">
									<c:choose>
										<c:when test="${ PAGEMODE == 'PAGEMODE_ADD' }">
											<span class="fa fa-plus fa-fw fa-2x"></span>&nbsp;<spring:message code="truck_jsp.add_truck" text="Add Truck"/>
										</c:when>
										<c:otherwise>
											<span class="fa fa-edit fa-fw fa-2x"></span>&nbsp;<spring:message code="truck_jsp.edit_truck" text="Edit Truck"/>
										</c:otherwise>
									</c:choose>
								</h1>
							</div>
							<div class="panel-body">
								<form:form id="truckForm" role="form" class="form-horizontal" modelAttribute="truckForm" action="${pageContext.request.contextPath}/master/truck/save" data-parsley-validate="parsley">
									<form:hidden path="truckId" />
									<div class="form-group">
										<label for="inputTruckType" class="col-sm-2 control-label"><spring:message code="truck_jsp.truck_type" text="Truck Type"/></label>
										<div class="col-sm-3">
											<form:select class="form-control" path="truckTypeLookup.lookupKey" data-parsley-required="true" data-parsley-trigger="change">
												<option value=""><spring:message code="common.please_select" text="Please Select"/></option>
												<c:forEach items="${ truckTypeDDL }" var="j">
													<form:option value="${ j.lookupKey }"><spring:message code="${ j.i18nLookupValue }"></spring:message></form:option>
												</c:forEach>
											</form:select>
										</div>
									</div>
									<div class="form-group">
										<label for="inputWeightType" class="col-sm-2 control-label"><spring:message code="truck_jsp.weight_type" text="Weight Type"/></label>
										<div class="col-sm-3">
											<form:select class="form-control" path="weightTypeLookup.lookupKey" data-parsley-required="true" data-parsley-trigger="change">
												<option value=""><spring:message code="common.please_select" text="Please Select"/></option>
												<c:forEach items="${ weightTypeDDL }" var="k">
													<form:option value="${ k.lookupKey }"><spring:message code="${ k.i18nLookupValue }"></spring:message></form:option>
												</c:forEach>
											</form:select>
										</div>
									</div>
									<div class="form-group">
										<label for="inputPlateNumber" class="col-sm-2 control-label"><spring:message code="truck_jsp.plate_number" text="Plate Number"/></label>
										<div class="col-sm-4">
											<form:input type="text" class="form-control" id="inputPlateNumber" path="plateNumber" placeholder="Enter Plate Number" data-parsley-length="[4, 10]" data-parsley-pattern="^[a-zA-Z0-9]+$" data-parsley-required="true" data-parsley-trigger="keyup"></form:input>
										</div>
									</div>
									<div class="form-group">
										<label for="inputKirDate" class="col-sm-2 control-label"><spring:message code="truck_jsp.kir_date" text="KIR Date"/></label>
										<div class="col-sm-4">
											<form:input type="text" class="form-control" id="inputKirDate" path="kirDate" placeholder="Enter KIR Date" data-parsley-required="true" data-parsley-trigger="keyup"></form:input>
										</div>
									</div>
									<div class="form-group">
										<label for="inputDriver" class="col-sm-2 control-label"><spring:message code="truck_jsp.driver" text="Driver"/></label>
										<div class="col-sm-2">
											<form:select class="form-control" path="driver" data-parsley-required="true" data-parsley-trigger="change">
												<option value=""><spring:message code="common.please_select" text="Please Select"/></option>
												<form:options items="${ driverDDL }" itemValue="userId" itemLabel="personEntity.fullName"/>
											</form:select>
										</div>
									</div>
									<div class="form-group">
										<label for="inputStatus" class="col-sm-2 control-label"><spring:message code="truck_jsp.status" text="Status"/></label>
										<div class="col-sm-2">
											<form:select class="form-control" path="truckStatusLookup.lookupKey" data-parsley-required="true" data-parsley-trigger="change">
												<option value=""><spring:message code="common.please_select" text="Please Select"/></option>
												<c:forEach items="${ statusDDL }" var="l">
													<form:option value="${ l.lookupKey }"><spring:message code="${ l.i18nLookupValue }"></spring:message></form:option>
												</c:forEach>
											</form:select>
										</div>
									</div>
									<div class="form-group">
										<label for="inputRemarks" class="col-sm-2 control-label"><spring:message code="truck_jsp.remarks" text="Remarks"/></label>
										<div class="col-sm-6">
											<form:input type="text" class="form-control" id="inputRemarks" path="remarks" placeholder="Enter Remarks"></form:input>
										</div>
									</div>
									<form:hidden path="createdBy"/>
									<form:hidden path="createdDate"/>
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
