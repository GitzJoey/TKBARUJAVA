<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<!DOCTYPE html>
<html>
<head>
	<jsp:include page="/WEB-INF/views/include/headtag.jsp"></jsp:include>
	<script>
		$(document).ready(function() {
			var ctxpath = "${ pageContext.request.contextPath }";
			
			$('#cancelButton').click(function() {				
				window.location.href(ctxpath + "/price/pricelevel");
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
					jsAlert("Please select at least 1 price level");
					return false;
				} else {
					if (button == 'editTableSelection') {
						$('#editTableSelection').attr("href", ctxpath + "/price/updatepricelevel/" + id);
					} else {
						$('#deleteTableSelection').attr("href", ctxpath + "/price/deletepricelevel/" + id);
					}
				}
			});
			
			$('select[id="priceLevelSelect"]').change(function() {
				if ($(this).val() == 'L022_INC') {
					$('#inputIncrementValue').prop('readonly', false);
					$('#inputPercentageValue').val('0').prop('readonly', true);
				} else {
					$('#inputIncrementValue').val('0').prop('readonly', true);
					$('#inputPercentageValue').prop('readonly', false);
				}
			});
			
			$('#priceLevelTableList').dataTable();
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
					<span class="fa fa-dollar fa-fw"></span>&nbsp;Price Level
				</h1>

				<c:choose>
					<c:when test="${ PAGEMODE == 'PAGEMODE_PAGELOAD' }">
						<div class="panel panel-default">
							<div class="panel-heading">
								<h1 class="panel-title">
									<span class="fa fa-table fa-fw fa-2x"></span>Price Level List
								</h1>
							</div>
							<div class="panel-body">
								<table id="priceLevelTableList" class="table table-bordered table-hover display responsive">
									<thead>
										<tr>
											<th width="5%">&nbsp;</th>
											<th width="15%">Price Level</th>
											<th width="25%">Description</th>
											<th width="10%">Status</th>
										</tr>
									</thead>
									<tbody>
										<c:if test="${not empty priceLevelList}">
											<c:forEach items="${ priceLevelList }" var="p" varStatus="pIdx">
												<tr>
													<td align="center"><input id="cbx_<c:out value="${ p.priceLevelId }"/>" type="checkbox" value="<c:out value="${ p.priceLevelId }"/>" /></td>
													<td><c:out value="${ p.priceLevelName }"></c:out></td>
													<td><c:out value="${ p.priceLevelDescription }"></c:out></td>
													<td><spring:message code="${ p.statusLookup.i18nLookupValue }" text="${ p.priceLevelStatus }"></spring:message></td>	
												</tr>
											</c:forEach>
										</c:if>
									</tbody>
								</table>
								<a id="addTableSelection" class="btn btn-sm btn-primary" href="${pageContext.request.contextPath}/price/addpricelevel"><span class="fa fa-plus fa-fw"></span>&nbsp;Add</a>
								<a id="editTableSelection" class="btn btn-sm btn-primary" href=""><span class="fa fa-edit fa-fw"></span>&nbsp;Edit</a>					
								<a id="deleteTableSelection" class="btn btn-sm btn-primary" href=""><span class="fa fa-close fa-fw"></span>&nbsp;Delete</a>
							</div>
						</div>
					</c:when>	
					<c:when test="${ PAGEMODE == 'PAGEMODE_EDIT' || PAGEMODE == 'PAGEMODE_ADD' }">						
						<div class="panel panel-default">
							<div class="panel-heading">
								<h1 class="panel-title">
									<c:choose>
										<c:when test="${ PAGEMODE == 'PAGEMODE_ADD' }">
											<span class="fa fa-plus fa-fw fa-2x"></span>&nbsp;Add Price Level
										</c:when>
										<c:otherwise>
											<span class="fa fa-edit fa-fw fa-2x"></span>&nbsp;Edit Price Level
										</c:otherwise>
									</c:choose>
								</h1>
							</div>								
							<div class="panel-body">
								<form:form id="todayPriceForm" role="form" class="form-horizontal" modelAttribute="priceLevelForm" action="${pageContext.request.contextPath}/price/savepricelevel" data-parsley-validate="parsley">
									<div class="form-group">
										<label for="inputPriceLevelType" class="col-sm-2 control-label">Price Level Type</label>
										<div class="col-sm-5">
											<form:select id="priceLevelSelect" class="form-control" path="priceLevelType" data-parsley-required="true" data-parsley-trigger="change">
												<option value=""><spring:message code="common.please_select"></spring:message></option>
												<c:forEach items="${ priceLevelDDL }" var="i">
													<form:option value="${ i.lookupKey }"><spring:message code="${ i.i18nLookupValue }"></spring:message></form:option>
												</c:forEach>
											</form:select>
										</div>
									</div>
									<div class="form-group">
										<label for="inputPriceLevelName" class="col-sm-2 control-label">Price Level Name</label>
										<div class="col-sm-5">
											<form:hidden path="priceLevelId"/>
											<form:input type="text" class="form-control" id="inputPriceLevelName" name="inputPriceLevelName" path="priceLevelName" placeholder="Enter Price Level Name" data-parsley-required="true" data-parsley-trigger="keyup"></form:input>
										</div>
									</div>
									<div class="form-group">
										<label for="inputPriceLevelDescription" class="col-sm-2 control-label">Description</label>
										<div class="col-sm-5">
											<form:input type="text" class="form-control" id="inputPriceLevelDescription" name="inputPriceLevelDescription" path="priceLevelDescription" placeholder="Enter Price Level Name"></form:input>
										</div>
									</div>
									<div class="form-group">
										<label for="inputPriceLevelStatus" class="col-sm-2 control-label">Status</label>
										<div class="col-sm-3">
											<form:select class="form-control" path="priceLevelStatus" data-parsley-required="true" data-parsley-trigger="change">
												<option value=""><spring:message code="common.please_select" text="Please Select"></spring:message></option>
												<c:forEach items="${ statusDDL }" var="i">
													<form:option value="${ i.lookupKey }"><spring:message code="${ i.i18nLookupValue }"></spring:message></form:option>
												</c:forEach>
											</form:select>
										</div>
									</div>
									<div class="form-group">
										<label for="inputIncrementValue" class="col-sm-2 control-label">Increment Value</label>
										<div class="col-sm-5">
											<form:input type="text" class="form-control" id="inputIncrementValue" name="inputIncrementValue" path="incrementValue" placeholder="Enter Increment Value"></form:input>
										</div>	
									</div>
									<div class="form-group">
										<label for="inputPercentageValue" class="col-sm-2 control-label">Percentage Value</label>
										<div class="col-sm-5">																	
											<form:input type="text" class="form-control" id="inputPercentageValue" name="inputPercentageValue" path="percentageValue" placeholder="Enter Percentage Value"></form:input>
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
