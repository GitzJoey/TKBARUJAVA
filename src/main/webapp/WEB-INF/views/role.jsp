<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<jsp:include page="/WEB-INF/views/include/headtag.jsp"></jsp:include>
	<script>
		$(document).ready(function() {
			$('#cancelButton').click(function() {				
				window.location.href("${ pageContext.request.contextPath }/admin/role.html");
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
					jsAlert("Please select at least 1 role");
					return false;	
				} else {
					$('#editTableSelection').attr("href", ctxpath + "/admin/role/edit/" + id + ".html");	
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
					jsAlert("Please select at least 1 role");
					return false;	
				} else {
					$('#deleteTableSelection').attr("href", ctxpath + "/admin/role/delete/" + id + ".html");	
				}								
			});		
			
			$('#selectLeft, #selectRight').change(function() {
				if ($(this).attr("id").indexOf("Left") > 0) {
					$('#selectRight').val([]);
				} else {
					$('#selectLeft').val([]);
				}
			});
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
					<span class="fa fa-tree fa-fw"></span>&nbsp;Role 
				</h1>

				<c:choose>
					<c:when test="${PAGEMODE == 'PAGEMODE_PAGELOAD' || PAGEMODE == 'PAGEMODE_LIST' || PAGEMODE == 'PAGEMODE_DELETE'}">
						<div class="panel panel-default">
							<div class="panel-heading">
								<h1 class="panel-title">
									<span class="fa fa-tree fa-fw fa-2x"></span>Role List
								</h1>
							</div>
							<div class="panel-body">
								<div class="table-responsive">
									<table class="table table-bordered table-hover">
										<thead>
											<tr>
												<th width="5%">&nbsp;</th>
												<th width="15%">Role Name</th>
												<th width="80%">Function List</th>
											</tr>
										</thead>
										<tbody>
											<c:if test="${not empty rfList}">
												<c:forEach var="i" varStatus="status" items="${rfList}">
													<tr>
														<td align="center"><input id="cbx_<c:out value="${ i.roleId }"/>" type="checkbox" value="<c:out value="${ i.roleId }"/>"/></td>
														<td><c:out value="${i.roleName}"></c:out></td>
														<td></td>
													</tr>
												</c:forEach>
											</c:if>
										</tbody>
									</table>
								</div>
								<a id="addNew" class="btn btn-sm btn-primary" href="${pageContext.request.contextPath}/admin/role/add.html"><span class="fa fa-plus fa-fw"></span>&nbsp;Add</a>&nbsp;&nbsp;&nbsp;
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
											<span class="fa fa-plus fa-fw fa-2x"></span>&nbsp;Add Role
										</c:when>
										<c:otherwise>
											<span class="fa fa-edit fa-fw fa-2x"></span>&nbsp;Edit Role
										</c:otherwise>
									</c:choose>
								</h1>
							</div>
							<div class="panel-body">
								<form:form id="roleForm" role="form" class="form-horizontal" modelAttribute="roleForm" action="${pageContext.request.contextPath}/admin/role/save.html">
									<form:hidden path="roleId"/>
									<div class="form-group">
										<label for="inputRoleName" class="col-sm-2 control-label">Role Name</label>
										<div class="col-sm-3">
											<form:input path="roleName" type="text" class="form-control" id="inputRoleName" name="inputRoleName" placeholder="Enter Role Name"></form:input>
										</div>
									</div>
									<div class="form-group">
										<label for="inputFunctionList" class="col-sm-2 control-label">Function List</label>
										<div class="col-sm-10">
											<table class="table borderless nopaddingrow">
												<tr>
													<td width="45%">														
														<select id="selectLeft" multiple class="form-control" size="15">
															<c:forEach items="${ functionList }" var="f" varStatus="fi">
																<option value="${ f.functionId }"><c:out value="${ f.module }" /> - <c:out value="${ f.menuName }" /></option> 	
															</c:forEach>
														</select>
													</td>
													<td width="5%" align="center" style="vertical-align: middle;">
														<button id="moveLeftButton" type="button" class="btn btn-xs btn-default"><span class="fa fa-arrow-circle-o-left fa-fw fa-2x"></span></button>						
														<br/>
														<br/>
														<br/>
														<button id="moveRightButton" type="button" class="btn btn-xs btn-default"><span class="fa fa-arrow-circle-o-right fa-fw fa-2x"></span></button>
														<br/>
														<br/>
														<br/>
													</td>
													<td width="45%">
														<select id="selectRight" multiple class="form-control" size="15">
															<option>1</option>
															<option>2</option>
															<option>3</option>
															<option>4</option>
															<option>5</option>
														</select>
													</td>
												</tr>
											</table>
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
