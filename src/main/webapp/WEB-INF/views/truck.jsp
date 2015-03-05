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
			
			$('#cancelButton').click(function() {				
				window.location.href(ctxpath + "/truck");
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
					jsAlert("Please select at least 1 truck");
					return false;	
				} else {
					if (button == 'editTableSelection') {
						$('#editTableSelection').attr("href", ctxpath + "/truck/edit/" + id);	
					} else {
						$('#deleteTableSelection').attr("href", ctxpath + "/truck/delete/" + id);	
					}						
				}				
			});
      
      $('#inputKirDate').datetimepicker({format: "DD-MM-YYYY"});
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
					<span class="fa fa-truck fa-flip-horizontal fa-fw"></span>&nbsp;Truck 
				</h1>

				<c:choose>
					<c:when test="${PAGEMODE == 'PAGEMODE_PAGELOAD' || PAGEMODE == 'PAGEMODE_LIST' || PAGEMODE == 'PAGEMODE_DELETE'}">
						<div class="panel panel-default">
							<div class="panel-heading">
								<h1 class="panel-title">
									<span class="fa fa-truck fa-flip-horizontal fa-fw fa-2x"></span>&nbsp;Truck List
								</h1>
							</div>
							<div class="panel-body">
								<div class="table-responsive">
									<table class="table table-bordered table-hover">
										<thead>
											<tr>
												<th width="5%">&nbsp;</th>
												<th width="10%">Truck Type</th>
												<th width="10%">Weight Type</th>
												<th width="20%">Plate Number</th>
												<th width="10%">KIR Date</th>
												<th width="15%">Driver</th>
												<th width="30%">Remarks</th>
											</tr>
										</thead>
										<tbody>
											<c:if test="${not empty truckList}">
												<c:forEach items="${ truckList }" var="i" varStatus="truckIdx">
													<tr>
														<td align="center"><input id="cbx_<c:out value="${ i.truckId }"/>" type="checkbox" value="<c:out value="${ i.truckId }"/>"/></td>
														<td><c:out value="${ i.truckType }"></c:out></td>
														<td><c:out value="${ i.weightType }"></c:out></td>
														<td><c:out value="${ i.plateNumber }"></c:out></td>
														<td><c:out value="${ i.kirDate }"></c:out></td>
														<td><c:out value="${ i.driver }"></c:out></td>
														<td><c:out value="${ i.remarks }"></c:out></td>														
													</tr>
												</c:forEach>
											</c:if>
										</tbody>
									</table>
								</div>
								<a id="addNew" class="btn btn-sm btn-primary" href="${pageContext.request.contextPath}/truck/add"><span class="fa fa-plus fa-fw"></span>&nbsp;Add</a>&nbsp;&nbsp;&nbsp;
								<a id="editTableSelection" class="btn btn-sm btn-primary" href=""><span class="fa fa-edit fa-fw"></span>&nbsp;Edit</a>&nbsp;&nbsp;&nbsp;
								<a id="deleteTableSelection" class="btn btn-sm btn-primary" href=""><span class="fa fa-close fa-fw"></span>&nbsp;Delete</a>
							</div>
						</div>						
					</c:when>
					<c:when test="${PAGEMODE == 'PAGEMODE_ADD' || PAGEMODE == 'PAGEMODE_EDIT'}">
						<div class="panel panel-default">
							<div class="panel-heading">
								<h1 class="panel-title">
									<c:choose>
										<c:when test="${PAGEMODE == 'PAGEMODE_ADD'}">
											<span class="fa fa-plus fa-fw fa-2x"></span>&nbsp;Add Truck
										</c:when>
										<c:otherwise>
											<span class="fa fa-edit fa-fw fa-2x"></span>&nbsp;Edit Truck
										</c:otherwise>
									</c:choose>
								</h1>
							</div>
							<div class="panel-body">
								<form:form id="truckForm" role="form" class="form-horizontal" modelAttribute="truckForm" action="${pageContext.request.contextPath}/truck/save">
									<form:hidden path="truckId" />									
									<div class="form-group">
										<label for="inputTruckType" class="col-sm-2 control-label">Truck Type</label>
										<div class="col-sm-3">
											<form:select class="form-control" path="truckType">
												<option>Select Truck Type</option>
												<form:options items="${ truckTypeDDL }" itemValue="lookupKey" itemLabel="lookupValue"/>
											</form:select>															
										</div>										
									</div>
									<div class="form-group">
										<label for="inputWeightType" class="col-sm-2 control-label">Weight Type</label>
										<div class="col-sm-3">
											<form:select class="form-control" path="weightType">
												<option>Select Weight Type</option>
												<form:options items="${ weightTypeDDL }" itemValue="lookupKey" itemLabel="lookupValue"/>
											</form:select>															
										</div>										
									</div>
									<div class="form-group">
										<label for="inputPlateNumber" class="col-sm-2 control-label">Plate Number</label>
										<div class="col-sm-4">
											<form:input type="text" class="form-control" id="inputPlateNumber" path="plateNumber" placeholder="Enter Plate Number"></form:input>
										</div>
									</div>
									<div class="form-group">
										<label for="inputKirDate" class="col-sm-2 control-label">KIR Date</label>
										<div class="col-sm-4">
											<form:input type="text" class="form-control" id="inputKirDate" path="kirDate" placeholder="Enter KIR Date"></form:input>
										</div>
									</div>									
									<div class="form-group">
										<label for="inputDriver" class="col-sm-2 control-label">Driver</label>
										<div class="col-sm-2">											
											<form:select class="form-control" path="driver">
												<option>Select Driver</option>
												<form:options items="${ driverDDL }" itemValue="userId" itemLabel="personEntity.fullName"/>
											</form:select>	
										</div>
									</div>                  									
									<div class="form-group">
										<label for="inputRemarks" class="col-sm-2 control-label">Remarks</label>
										<div class="col-sm-6">
											<form:input type="text" class="form-control" id="inputRemarks" path="remarks" placeholder="Enter Remarks"></form:input>
										</div>
									</div>
									<div class="col-md-7 col-offset-md-5">
										<div class="btn-toolbar">
											<button id="cancelButton" type="reset" class="btn btn-primary pull-right">Cancel</button>
											<button id="submitButton" type="submit" class="btn btn-primary pull-right">Submit</button>
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
</body>
</html>
