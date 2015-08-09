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
				window.location.href(ctxpath + "/admin/store");
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
						$('#editTableSelection').attr("href", ctxpath + "/admin/store/edit/" + id);	
					} else {
						$('#deleteTableSelection').attr("href", ctxpath + "/admin/store/delete/" + id);	
					}						
				}				
			});

			$('#storeList').dataTable();					
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
					<span class="fa fa-umbrella fa-fw"></span>&nbsp;Store 
				</h1>

				<c:choose>
					<c:when test="${PAGEMODE == 'PAGEMODE_PAGELOAD' || PAGEMODE == 'PAGEMODE_LIST' || PAGEMODE == 'PAGEMODE_DELETE'}">
						<div class="panel panel-default">
							<div class="panel-heading">
								<h1 class="panel-title">
									<span class="fa fa-umbrella fa-fw fa-2x"></span>Store List
								</h1>
							</div>
							<div class="panel-body">
								<table id="storeList" class="table table-bordered table-hover display responsive">
									<thead>
										<tr>
											<th width="5%">&nbsp;</th>
											<th width="15%"><spring:message code="store_jsp.table.header.store_name" text="Store Name"/></th>
											<th width="70%"><spring:message code="store_jsp.table.header.details" text="Details"/></th>
											<th width="5%"><spring:message code="store_jsp.table.header.default" text="Default"/></th>
											<th width="5%"><spring:message code="store_jsp.table.header.status" text="Status"/></th>
										</tr>
									</thead>
									<tbody>
										<c:if test="${not empty storeList}">
											<c:forEach items="${ storeList }" var="i" varStatus="storeIdx">
												<tr>
													<td align="center"><input id="cbx_<c:out value="${ i.storeId }"/>" type="checkbox" value="<c:out value="${ i.storeId }"/>"/></td>
													<td><c:out value="${ i.storeName }"></c:out></td>
													<td>
														<strong><c:out value="${ i.storeName }"></c:out></strong><br/><br/>
														<c:out value="${ i.storeAddress1 }"></c:out><br/>
														<c:out value="${ i.storeAddress2 }"></c:out><br/>
														<c:out value="${ i.storeAddress3 }"></c:out><br/>
														<br/>
														NPWP : <c:out value="${ i.npwpNumber }"/><br/>
														<br/>
													</td>
													<td>
														<c:out value="${ i.isDefaultLookup.lookupValue }"/><br/>
													</td>
													<td>
														<c:out value="${ i.storeStatusLookup.lookupValue }"/><br/>
													</td>
												</tr>
											</c:forEach>
										</c:if>
									</tbody>
								</table>
								<a id="addNew" class="btn btn-sm btn-primary" href="${pageContext.request.contextPath}/admin/store/add"><span class="fa fa-plus fa-fw"></span>&nbsp;<spring:message code="common.add_button" text="Add"/></a>&nbsp;&nbsp;&nbsp;
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
											<span class="fa fa-plus fa-fw fa-2x"></span>&nbsp;Add Store
										</c:when>
										<c:otherwise>
											<span class="fa fa-edit fa-fw fa-2x"></span>&nbsp;Edit Store
										</c:otherwise>
									</c:choose>
								</h1>
							</div>
							<div class="panel-body">
								<form:form id="storeForm" role="form" class="form-horizontal" modelAttribute="storeForm" action="${pageContext.request.contextPath}/admin/store/save" data-parsley-validate="parsley"> 
									<form:hidden path="storeId"/>									
									<div class="form-group">
										<label for="inputStoreName" class="col-sm-2 control-label">Store Name</label>
										<div class="col-sm-3">
											<form:input type="text" class="form-control" id="inputStoreName" name="inputStoreName" path="storeName" placeholder="Store Name" data-parsley-required="true" data-parsley-length="[6, 30]" data-parsley-trigger="keyup"></form:input>
										</div>										
									</div>
									<div class="form-group">
										<label for="inputAddress1" class="col-sm-2 control-label">Address</label>
										<div class="col-sm-5">
											<form:input type="text" class="form-control" id="inputAddress1" name="inputAddress1" path="storeAddress1"></form:input>
											<form:input type="text" class="form-control" id="inputAddress2" name="inputAddress2" path="storeAddress2"></form:input>
											<form:input type="text" class="form-control" id="inputAddress3" name="inputAddress3" path="storeAddress3"></form:input>
										</div>										
									</div>
									<div class="form-group">
										<label for="inputIsDefault" class="col-sm-2 control-label">Is Default Store</label>
										<div class="col-sm-3">
											<form:select class="form-control" path="isDefault" data-parsley-required="true" data-parsley-trigger="change">
												<option value="">Please Select</option>
												<form:options items="${ ynDDL }" itemValue="lookupKey" itemLabel="lookupValue"/>
											</form:select>											
										</div>										
									</div>
									<div class="form-group">
										<label for="inputNpwpNumber" class="col-sm-2 control-label">NPWP Number</label>
										<div class="col-sm-3">
											<form:input type="text" class="form-control" id="inputNpwpNumber" name="inputNpwpNumber" path="npwpNumber" placeholder="NPWP Number"></form:input>
										</div>										
									</div>
									<div class="form-group">
										<label for="inputStoreStatus" class="col-sm-2 control-label">Status</label>
										<div class="col-sm-3">
											<form:select class="form-control" path="storeStatus" data-parsley-required="true" data-parsley-trigger="change">
												<option value="">Please Select</option>
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
