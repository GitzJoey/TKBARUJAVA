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
				var truckId = $('#inputHidden_truckId').val();
				
				window.location.href = ctxpath + "/truck/maintenance/tr/" + truckId;
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

			$('#editTableSelection, #deleteTableSelection').click(function() {
				var id = "";
				var button = $(this).attr('id');
				var truckId = $('#inputHidden_truckId').val();
				
				$('input[type="checkbox"][id^="cbx_"]').each(function(index, item) {
					if ($(item).prop('checked')) {
						id = $(item).attr("value");	
					}
				});
				
				if (id == "") {
					jsAlert("Please select at least 1 data truck maintenance");
					return false;	
				} else {
					if (button == 'editTableSelection') {
						$('#editTableSelection').attr("href", ctxpath + "/truck/maintenance/tr/" + truckId + "/edit/" + id);
					} else {
						$('#deleteTableSelection').attr("href", ctxpath + "/truck/maintenance/tr/" + truckId + "/delete/" + id);
					}						
				}				
			});
			
			
			$('#mtcListTable').DataTable();
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
					<span class="fa fa-gears fa-fw"></span>&nbsp;<spring:message code="truckmtc_jsp.title" text="Truck Maintenance"/>
				</h1>

				<c:choose>
					<c:when test="${ PAGEMODE == 'PAGEMODE_PAGELOAD' || PAGEMODE == 'PAGEMODE_LIST' || PAGEMODE == 'PAGEMODE_DELETE' }">
						<div class="panel panel-default">
							<div class="panel-heading">
								<h1 class="panel-title">
									<span class="fa fa-gears fa-fw fa-2x"></span>&nbsp;<spring:message code="truckmtc_jsp.truckmtc_list" text="Truck Maintenance List"/>
								</h1>
							</div>
							<div class="panel-body">
								<table id="mtcListTable" class="table table-bordered table-hover display dt-responsive nowrap" style="width: 100%; border-collapse: separate;">
									<thead>
										<tr>
											<th width="5%">&nbsp;</th>
											<th width="15%"><spring:message code="truckmtc_jsp.table.header.type" text="Type"/></th>
											<th width="10%"><spring:message code="truckmtc_jsp.table.header.cost" text="Cost"/></th>
											<th width="10%"><spring:message code="truckmtc_jsp.table.header.odometer" text="Odometer"/></th>
											<th width="50%"><spring:message code="truckmtc_jsp.table.header.remarks" text="Remarks"/></th>
										</tr>
									</thead>
									<tbody>
										<c:if test="${ not empty mtcList }">
											<c:forEach items="${ mtcList }" var="i" varStatus="mtcIdx">
												<tr>
													<td align="center">
														<input type="hidden" id="inputHidden_truckId" value="${ truckId }"/>
														<input id="cbx_<c:out value="${ i.truckMaintenanceId }"/>" type="checkbox" value="<c:out value="${ i.truckMaintenanceId }"/>"/>
													</td>
													<td>
														<spring:message code="${ i.maintenanceTypeLookup.i18nLookupValue }" text="${ i.maintenanceTypeLookup.lookupValue }"/>
													</td>
													<td><c:out value="${ i.cost }"></c:out></td>
													<td><c:out value="${ i.odometer }"></c:out></td>
													<td><c:out value="${ i.remarks }"></c:out></td>
												</tr>
											</c:forEach>
										</c:if>
									</tbody>
								</table>
								<a id="addNew" class="btn btn-sm btn-primary" href="${pageContext.request.contextPath}/truck/maintenance/tr/${ truckId }/add"><span class="fa fa-plus fa-fw"></span>&nbsp;<spring:message code="common.add_button" text="Add"/></a>&nbsp;&nbsp;&nbsp;
								<a id="editTableSelection" class="btn btn-sm btn-primary" href=""><span class="fa fa-edit fa-fw"></span>&nbsp;<spring:message code="common.edit_button" text="Edit"/></a>&nbsp;&nbsp;&nbsp;
							</div>
						</div>						
					</c:when>
					<c:when test="${ PAGEMODE == 'PAGEMODE_ADD' || PAGEMODE == 'PAGEMODE_EDIT' }">
						<div class="panel panel-default">
							<div class="panel-heading">
								<h1 class="panel-title">
									<c:choose>
										<c:when test="${ PAGEMODE == 'PAGEMODE_ADD' }">
											<span class="fa fa-plus fa-fw fa-2x"></span>&nbsp;<spring:message code="truckmtc_jsp.add_truckmtc" text="Add Maintenance"/>
										</c:when>
										<c:otherwise>
											<span class="fa fa-edit fa-fw fa-2x"></span>&nbsp;<spring:message code="truckmtc_jsp.edit_truckmtc" text="Edit Maintenance"/>
										</c:otherwise>
									</c:choose>
								</h1>
							</div>
							<div class="panel-body">
								<form:form id="mtcForm" role="form" class="form-horizontal" modelAttribute="mtcForm" action="${pageContext.request.contextPath}/truck/maintenance/tr/${ mtcForm.truckId }/save" data-parsley-validate="parsley">
									<form:hidden path="truckMaintenanceId"/>
									<form:hidden id="inputHidden_truckId" path="truckId"/>
									<div class="form-group">
										<label for="inputMaintenanceType" class="col-sm-2 control-label"><spring:message code="truckmtc_jsp.truckmtctype" text="Maintenance Type"/></label>
										<div class="col-sm-3">
											<form:select class="form-control" path="maintenanceTypeLookup.lookupKey" data-parsley-required="true" data-parsley-trigger="change">
												<option value=""><spring:message code="common.please_select" text="Please Select"/></option>
												<c:forEach items="${ maintenanceTypeDDL }" var="i">
													<form:option value="${ i.lookupKey }"><spring:message code="${ i.i18nLookupValue }" text="${ i.lookupValue }"></spring:message></form:option>
												</c:forEach>
											</form:select>
										</div>
									</div>
									<div class="form-group">
										<label for="inputCost" class="col-sm-2 control-label"><spring:message code="truckmtc_jsp.cost" text="Cost"/></label>
										<div class="col-sm-2">
											<form:input type="text" class="form-control" id="inputCost" name="inputCost" path="cost" placeholder="Cost" data-parsley-required="true" data-parsley-trigger="keyup"></form:input>
										</div>
									</div>
									<div class="form-group">
										<label for="inputOdometer" class="col-sm-2 control-label"><spring:message code="truckmtc_jsp.odometer" text="Odometer"/></label>
										<div class="col-sm-2">
											<form:input type="text" class="form-control" id="inputOdometer" name="inputOdometer" path="odometer" placeholder="Enter Odometer" data-parsley-required="true" data-parsley-type="number" data-parsley-trigger="keyup"></form:input>
										</div>
									</div>
									<div class="form-group">
										<label for="inputRemarks" class="col-sm-2 control-label"><spring:message code="truckmtc_jsp.remarks" text="Remarks"/></label>
										<div class="col-sm-6">
											<form:textarea class="form-control" id="inputRemarks" name="inputRemarks" path="remarks" rows="5" placeholder="Enter Remarks"/>
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
