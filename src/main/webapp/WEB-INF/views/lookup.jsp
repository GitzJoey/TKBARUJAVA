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
				window.location.href("${ pageContext.request.contextPath }/admin/lookup");
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
			})
			
			$('#editTableSelection').click(function() {
				var id = "";
				$('input[type="checkbox"][id^="cbx_"]').each(function(index, item) {
					if ($(item).prop('checked')) {
						id = $(item).attr("value");	
					}
				});
				if (id == "") {
					jsAlert("Please select at least 1 function");
					return false;	
				} else {
					$('#editTableSelection').attr("href", ctxpath + "/admin/lookup/edit/" + id);	
				}				
			});
			
			$('#deleteTableSelection').click(function() {
				var id = "";
				$('input[type="checkbox"][id^="cbx_"]').each(function(index, item) {
					if ($(item).prop('checked')) {
						id = $(item).attr("value");	
					}
				});
				if (id == "") {
					jsAlert("Please select at least 1 function");
					return false;	
				} else {
					$('#deleteTableSelection').attr("href", ctxpath + "/admin/lookup/delete/" + id);	
				}								
			});
			
			$('#lookupListTable').DataTable();
			
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
			<div id="content" class="col-md-10 offset-md-1">
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
					<span class="fa fa-hand-o-up fa-fw"></span>&nbsp;Lookup
				</h1>

				<c:choose>
					<c:when test="${ PAGEMODE == 'PAGEMODE_PAGELOAD' || PAGEMODE == 'PAGEMODE_LIST' || PAGEMODE == 'PAGEMODE_DELETE' }">
						<div class="panel panel-default">
							<div class="panel-heading">
								<h1 class="panel-title">
									<span class="fa fa-hand-o-up fa-fw fa-2x"></span>Lookup List
								</h1>
							</div>
							<div class="panel-body">
								<div id="categoryDDL" class="btn-group">
									<button class="btn btn-default dropdown-toggle" type="button" data-toggle="dropdown" aria-expanded="false">
										<c:choose>
											<c:when test="${ not empty selectedCat}">
												<c:out value="${ selectedCat }" />&nbsp;&nbsp;<span class="caret"></span>
											</c:when>
											<c:otherwise>
												Select Category&nbsp;&nbsp;<span class="caret"></span>
											</c:otherwise>
										</c:choose>
									</button>
									<ul class="dropdown-menu" role="menu">
										<c:forEach items="${ categoryDDL }" var="catDDL">
											<li role="presentation"><a role="menuitem" tabindex="-1" href="${ pageContext.request.contextPath }/admin/lookup/bycategory/${ catDDL.lookupCategory }">${ catDDL.lookupCategory }</a></li>
										</c:forEach>										
									</ul>
								</div>
								<div>&nbsp;</div>
								<table id="lookupListTable" class="table table-bordered table-hover display responsive">
									<thead>
										<tr>
											<th width="5%">&nbsp;</th>
											<th width="20%"><spring:message code="lookup_jsp.table.header.category" text="Category"/></th>
											<th width="15%"><spring:message code="lookup_jsp.table.header.key" text="Key"/></th>
											<th width="25%"><spring:message code="lookup_jsp.table.header.locale_message_codes" text="Locale Message Codes"/></th>
											<th width="20%"><spring:message code="lookup_jsp.table.header.value" text="Value"/></th>
											<th width="5%"><spring:message code="lookup_jsp.table.header.order" text="Order"/></th>
											<th width="5%"><spring:message code="lookup_jsp.table.header.status" text="Status"/></th>
											<th width="5%"><spring:message code="lookup_jsp.table.header.maintainable" text="Maintainable"/></th>
										</tr>
									</thead>
									<tbody>
										<c:if test="${ not empty lookupList }">
											<c:forEach var="i" varStatus="status" items="${ lookupList }">
												<tr>
													<td align="center"><input id="cbx_<c:out value="${ i.lookupId }"/>" type="checkbox" value="<c:out value="${ i.lookupId }"/>"/></td>
													<td><c:out value="${ i.lookupCategory }"/></td>
													<td><c:out value="${ i.lookupKey }"/></td>
													<td><c:out value="${ i.localeMessageCodes }"/></td>
													<td><c:out value="${ i.lookupValue }"/></td>													
													<td><c:out value="${ i.orderNum }"/></td>
													<td><c:out value="${ i.statusLookup.lookupValue }"/></td>
													<td><c:out value="${ i.maintainabilityLookup.lookupValue }"/></td>
												</tr>
											</c:forEach>
										</c:if>
									</tbody>
								</table>
								<a id="addNew" class="btn btn-sm btn-primary" href="${pageContext.request.contextPath}/admin/lookup/add"><span class="fa fa-plus fa-fw"></span>&nbsp;<spring:message code="common.add_button" text="Add"/></a>&nbsp;&nbsp;&nbsp;
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
											<span class="fa fa-plus fa-fw fa-2x"></span>&nbsp;Add Lookup
										</c:when>
										<c:otherwise>
											<span class="fa fa-edit fa-fw fa-2x"></span>&nbsp;Edit Lookup
										</c:otherwise>
									</c:choose>
								</h1>
							</div>
							<div class="panel-body">
								<form:form id="lookupForm" role="form" class="form-horizontal" commandName="lookupForm" modelAttribute="lookupForm" action="${pageContext.request.contextPath}/admin/lookup/save" data-parsley-validate="parsley">
									<form:hidden path="lookupId"/>
									<div class="form-group">
										<label for="inputCategory" class="col-sm-2 control-label">Category</label>
										<div class="col-sm-3">
											<form:input type="text" class="form-control" id="inputCategory" name="inputCategory" path="lookupCategory" placeholder="Enter Category" data-parsley-required="true" data-parsley-trigger="keyup"></form:input>
										</div>
									</div>
									<div class="form-group">
										<label for="inputLookupKey" class="col-sm-2 control-label">Lookup Key</label>
										<div class="col-sm-3">
											<form:input type="text" class="form-control" id="inputLookupKey" name="inputLookupKey" path="lookupKey" placeholder="Enter Lookup Key" data-parsley-required="true" data-parsley-trigger="keyup"></form:input>
										</div>
									</div>
									<div class="form-group">
										<label for="inputLocaleMessageCodes" class="col-sm-2 control-label">Locale Message Codes</label>
										<div class="col-sm-5">
											<form:input type="text" class="form-control" path="localeMessageCodes" placeholder="Enter Locale Message Codes" data-parsley-required="true" data-parsley-trigger="keyup"></form:input>
										</div>
									</div>									
									<div class="form-group">
										<label for="inputValue" class="col-sm-2 control-label">Value</label>
										<div class="col-sm-5">
											<form:input type="text" class="form-control" path="lookupValue" placeholder="Enter Lookup Value" data-parsley-required="true" data-parsley-trigger="keyup"></form:input>
										</div>
									</div>
									<div class="form-group">
										<label for="inputOrderNum" class="col-sm-2 control-label">Order</label>
										<div class="col-sm-1">
											<form:input type="text" class="form-control" id="inputOrderNum" name="inputOrderNum" path="orderNum" placeholder="Order" data-parsley-required="true" data-parsley-trigger="keyup"></form:input>
										</div>
									</div>
									<div class="form-group">
										<label for="inputMaintainability" class="col-sm-2 control-label">Maintainable</label>
										<div class="col-sm-2">
											<form:select class="form-control" path="lookupMaintainability">
												<option value=""><spring:message code="common.please_select"></spring:message></option>
												<c:forEach items="${ MaintainabilityDDL }" var="i">
													<form:option value="${ i.lookupKey }"><spring:message code="${ i.i18nLookupValue }"></spring:message></form:option>
												</c:forEach>
											</form:select>
										</div>
									</div>									
									<div class="form-group">
										<label for="inputStatus" class="col-sm-2 control-label">Status</label>
										<div class="col-sm-2">
											<form:select class="form-control" path="lookupStatus">
												<option value=""><spring:message code="common.please_select"></spring:message></option>
												<c:forEach items="${ statusDDL }" var="i">
													<form:option value="${ i.lookupKey }"><spring:message code="${ i.i18nLookupValue }"></spring:message></form:option>
												</c:forEach>
											</form:select>
										</div>
									</div>									
									<div class="col-md-3 offset-md-9">
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
