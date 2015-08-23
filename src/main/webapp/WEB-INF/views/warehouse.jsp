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
			
			$('#editTableSelection, #deleteTableSelection').click(function() {
				var id = "";
				var button = $(this).attr('id');
				
				$('input[type="checkbox"][id^="cbx_"]').each(function(index, item) {
					if ($(item).prop('checked')) {
						id = $(item).attr("value");	
					}
				});
				
				if (id == "") {
					jsAlert("Please select at least 1 warehouse");
					return false;	
				} else {
					if (button == 'editTableSelection') {
						$('#editTableSelection').attr("href", ctxpath + "/warehouse/edit/" + id);
					} else {
						$('#deleteTableSelection').attr("href", ctxpath + "/warehouse/delete/" + id);	
					}
				}				
			});

			$('#cancelButton').click(function() {
				window.location.href(ctxpath + "/warehouse");
			});
			
			$('#whListTable').dataTable();
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
					<span class="fa fa-wrench fa-fw"></span>&nbsp;<spring:message code="warehouse_jsp.title" text="Warehouse"/>
				</h1>

				<c:choose>
					<c:when test="${ PAGEMODE == 'PAGEMODE_PAGELOAD' || PAGEMODE == 'PAGEMODE_LIST' || PAGEMODE == 'PAGEMODE_DELETE' }">
						<div class="panel panel-default">
							<div class="panel-heading">
								<h1 class="panel-title">
									<span class="fa fa-wrench fa-fw fa-2x"></span><spring:message code="warehouse_jsp.warehouse_list" text="Warehouse List"/>
								</h1>
							</div>
							<div class="panel-body">
								<table id="whListTable" class="table table-bordered table-hover">
									<thead>
										<tr>
											<th width="5%">&nbsp;</th>
											<th width="30%"><spring:message code="warehouse_jsp.table.header.warehouse_name" text="Warehouse Name"/></th>
											<th width="60%"><spring:message code="warehouse_jsp.table.header.location" text="Location"/></th>
											<th width="5%"><spring:message code="warehouse_jsp.table.header.status" text="Status"/></th>												
										</tr>
									</thead>
									<tbody>
										<c:if test="${not empty warehouseList}">
											<c:forEach items="${ warehouseList }" var="i" varStatus="warehouseIdx">
												<tr>
													<td align="center"><input id="cbx_<c:out value="${ i.warehouseId }"/>" type="checkbox" value="<c:out value="${ i.warehouseId }"/>"/></td>
													<td><c:out value="${ i.warehouseName }"></c:out></td>
													<td>
														<strong><c:out value="${ i.warehouseName }"></c:out></strong><br/><br/>
														<c:out value="${ i.warehouseLocation }"></c:out><br/><br/>
														<c:out value="${ i.warehouseRemarks }"></c:out>
													</td>
													<td>
														<c:out value="${ i.statusLookup.lookupValue }"/><br/>
													</td>
												</tr>
											</c:forEach>
										</c:if>
									</tbody>
								</table>
								<a id="addNew" class="btn btn-sm btn-primary" href="${pageContext.request.contextPath}/warehouse/add"><span class="fa fa-plus fa-fw"></span>&nbsp;<spring:message code="common.add_button" text="Add"/></a>&nbsp;&nbsp;&nbsp;
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
											<span class="fa fa-plus fa-fw fa-2x"></span>&nbsp;<spring:message code="warehouse_jsp.add_warehouse" text="Add Warehouse"/>
										</c:when>
										<c:otherwise>
											<span class="fa fa-edit fa-fw fa-2x"></span>&nbsp;<spring:message code="warehouse_jsp.edit_warehouse" text="Edit Warehouse"/>
										</c:otherwise>
									</c:choose>
								</h1>
							</div>
							<div class="panel-body">
								<form:form id="warehouseForm" role="form" class="form-horizontal" modelAttribute="warehouseForm" action="${pageContext.request.contextPath}/warehouse/save" data-parsley-validate="parsley"> 
									<form:hidden path="warehouseId"/>
									<div class="form-group">
										<label for="inputWarehouseName" class="col-sm-2 control-label"><spring:message code="warehouse_jsp.warehouse_name" text="Warehouse Name"/></label>
										<div class="col-sm-3">
											<form:input type="text" class="form-control" id="inputWarehouseName" name="inputWarehouseName" path="warehouseName" placeholder="Warehouse Name" data-parsley-required="true" data-parsley-trigger="keyup"></form:input>
										</div>
									</div>
									<div class="form-group">
										<label for="inputWarehouseLocation" class="col-sm-2 control-label"><spring:message code="warehouse_jsp.location" text="Location"/></label>
										<div class="col-sm-5">
											<form:input type="text" class="form-control" id="inputWarehouseLocation" name="inputWarehouseLocation" path="warehouseLocation" data-parsley-required="true" data-parsley-trigger="keyup"></form:input>
										</div>										
									</div>
									<div class="form-group">
										<label for="inputWarehouseRemarks" class="col-sm-2 control-label"><spring:message code="warehouse_jsp.remarks" text="Remarks"/></label>
										<div class="col-sm-6">
											<form:input type="text" class="form-control" id="inputWarehouseRemarks" name="inputWarehouseRemarks" path="warehouseRemarks"></form:input>
										</div>
									</div>
									<div class="form-group">
										<label for="inputWarehouseStatus" class="col-sm-2 control-label"><spring:message code="warehouse_jsp.status" text="Status"/></label>
										<div class="col-sm-3">
											<form:select class="form-control" path="warehouseStatus" data-parsley-required="true" data-parsley-trigger="change">
												<option value=""><spring:message code="common.please_select" text="Please Select"/></option>
												<form:options items="${ statusDDL }" itemValue="lookupKey" itemLabel="lookupValue"/>
											</form:select>											
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
