<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
	<jsp:include page="/WEB-INF/views/include/headtag.jsp"></jsp:include>
	<script>
		$(document).ready(function() {
			$('#inputMenuIcon, #inputModuleIcon').change(function() {
				$("#" + $(this).attr('id') + "Sample").attr('class', $(this).val());
			});
			
			$('#cancelButton').click(function() {				
				window.location.href("${ pageContext.request.contextPath }/admin/function.html");
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
				var ctxpath = "${ pageContext.request.contextPath }";
				$('input[type="checkbox"][id^="cbx_"]').each(function(index, item) {
					if ($(item).prop('checked')) {
						id = $(item).attr("value");	
					}
				});
				if (id == "") {
					jsAlert("Please select at least 1 function");
					return false;	
				} else {
					$('#editTableSelection').attr("href", ctxpath + "/admin/function/edit/" + id + ".html");	
				}				
			});
			
			$('#deleteTableSelection').click(function() {
				var id = "";
				var ctxpath = "${ pageContext.request.contextPath }";
				$('input[type="checkbox"][id^="cbx_"]').each(function(index, item) {
					if ($(item).prop('checked')) {
						id = $(item).attr("value");	
					}
				});
				if (id == "") {
					jsAlert("Please select at least 1 function");
					return false;	
				} else {
					$('#deleteTableSelection').attr("href", ctxpath + "/admin/function/delete/" + id + ".html");	
				}								
			});
			
			$('#functionForm').bootstrapValidator({
       			feedbackIcons: {
           			valid: 'glyphicon glyphicon-ok',
           			invalid: 'glyphicon glyphicon-remove',
					validating: 'glyphicon glyphicon-refresh'
       			},
       			submitButtons: 'button[type="submit"]',
       			fields: {
       				inputUserName: {
               			validators: {
                   			notEmpty: { },
							stringLength: { min: 4, max: 10 },
               			}
           			},
           			inputPassword: {
               			validators: {
                   			notEmpty: {	},
                   			stringLength: { min: 6 }
               			}
           			}
       			}
			});
			
			$('#functionListTable').DataTable();
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
					<span class="fa fa-minus-square fa-fw"></span>&nbsp;Function
				</h1>

				<c:choose>
					<c:when test="${PAGEMODE == 'PAGEMODE_PAGELOAD' || PAGEMODE == 'PAGEMODE_LIST' || PAGEMODE == 'PAGEMODE_DELETE'}">
						<div class="panel panel-default">
							<div class="panel-heading">
								<h1 class="panel-title">
									<span class="fa fa-minus-square fa-fw fa-2x"></span>Function List
								</h1>
							</div>
							<div class="panel-body">
								<table id="functionListTable" class="table table-bordered table-hover display responsive" style="width: 100%;">
									<thead>
										<tr>
											<th width="5%">&nbsp;</th>
											<th width="15%">Function Code</th>
											<th width="20%">Module</th>
											<th width="20%">Menu Name</th>
											<th width="20%">Url</th>
											<th width="5%">Order</th>
											<th width="5%">Deep Level</th>
										</tr>
									</thead>
									<tbody>
										<c:if test="${not empty functionList}">
											<c:forEach var="i" varStatus="status" items="${functionList}">
												<tr>
													<td align="center"><input id="cbx_<c:out value="${ i.functionId }"/>" type="checkbox" value="<c:out value="${ i.functionId }"/>"/></td>
													<td><c:out value="${i.functionCode}"></c:out></td>
													<td><span class="<c:out value="${ i.moduleIcon }"></c:out>">&nbsp;</span><c:out value="${ i.module }"></c:out></td>
													<td><span class="<c:out value="${ i.menuIcon }"></c:out>">&nbsp;</span><c:out value="${ i.menuName }"></c:out></td>
													<td><c:out value="${ i.urlLink }"/></td>
													<td><c:out value="${ i.orderNum }"/></td>
													<td><c:out value="${ i.deepLevel }"/></td>
												</tr>
											</c:forEach>
										</c:if>
									</tbody>
								</table>
								<a id="addNew" class="btn btn-sm btn-primary" href="${pageContext.request.contextPath}/admin/function/add.html"><span class="fa fa-plus fa-fw"></span>&nbsp;Add</a>&nbsp;&nbsp;&nbsp;
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
											<span class="fa fa-plus fa-fw fa-2x"></span>&nbsp;Add Function
										</c:when>
										<c:otherwise>
											<span class="fa fa-edit fa-fw fa-2x"></span>&nbsp;Edit Function
										</c:otherwise>
									</c:choose>
								</h1>
							</div>
							<div class="panel-body">
								<form:form id="functionForm" role="form" class="form-horizontal" commandName="fForm" modelAttribute="fForm" action="${pageContext.request.contextPath}/admin/function/save.html">
									<form:hidden path="functionId"/>
									<div class="form-group">
										<label for="inputFunctionCode" class="col-sm-2 control-label">Function Code</label>
										<div class="col-sm-3">
											<form:input type="text" class="form-control" id="inputFunctionCode" name="inputFunctionCode" path="functionCode" placeholder="Enter Function Code"></form:input>
										</div>
									</div>
									<div class="form-group">
										<label for="inputModule" class="col-sm-2 control-label">Module</label>
										<div class="col-sm-3">
											<table class="table borderless nopaddingrow no-margin">
												<tr>
													<td>
														<form:input type="text" class="form-control" id="inputModuleIcon" name="inputModuleIcon" path="moduleIcon" placeholder="Module Name Icon"></form:input>
													</td>
													<td>
														<c:choose>
															<c:when test="${ not empty fForm.moduleIcon }">
																<span id="inputModuleIconSample" class="${ fForm.moduleIcon }"></span>
															</c:when>
															<c:otherwise>
																<span id="inputModuleIconSample" class=""></span>
															</c:otherwise>
														</c:choose>
													</td>
												</tr>
												<tr>
													<td colspan="2">
														<form:input type="text" class="form-control" id="inputModule" name="inputModule" path="module" placeholder="Module Name"></form:input>
													</td>
												</tr>
											</table>
										</div>
									</div>
									<div class="form-group">
										<label for="inputMenuName" class="col-sm-2 control-label">Menu Name</label>
										<div class="col-sm-5">
											<table class="table borderless nopaddingrow no-margin">
												<tr>
													<td>
														<form:input type="text" class="form-control" id="inputMenuIcon" name="inputMenuIcon" path="menuIcon" placeholder="Menu Name Icon"></form:input>
													</td>
													<td>
														<c:choose>
															<c:when test="${ not empty fForm.menuIcon }">
																<span id="inputMenuIconSample" class="${ fForm.menuIcon }"></span>
															</c:when>
															<c:otherwise>
																<span id="inputMenuIconSample" class=""></span>
															</c:otherwise>
														</c:choose>
													</td>
												</tr>
												<tr>
													<td colspan="2">
														<form:input type="text" class="form-control" id="inputMenuName" name="inputMenuName" path="menuName" placeholder="Menu Name"></form:input>
													</td>
												</tr>
											</table>
										</div>
									</div>
									<div class="form-group">
										<label for="inputUrlLink" class="col-sm-2 control-label">URL</label>
										<div class="col-sm-5">
											<form:input type="text" class="form-control" id="inputUrlLink" name="inputUrlLink" path="urlLink" placeholder="URL"></form:input>
										</div>
									</div>
									<div class="form-group">
										<label for="inputOrderNum" class="col-sm-2 control-label">Order</label>
										<div class="col-sm-2">
											<form:input type="text" class="form-control" id="inputOrderNum" name="inputOrderNum" path="orderNum" placeholder="Order"></form:input>
										</div>
									</div>
									<div class="form-group">
										<label for="inputDeepLevel" class="col-sm-2 control-label">Deep Level</label>
										<div class="col-sm-2">
											<form:input type="text" class="form-control" id="inputDeepLevel" name="inputDeepLevel" path="deepLevel" placeholder="Deep Level"></form:input>
										</div>
									</div>
									<div class="col-md-3 offset-md-9">
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
