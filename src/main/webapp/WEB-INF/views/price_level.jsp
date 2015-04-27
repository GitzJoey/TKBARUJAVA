<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
	<jsp:include page="/WEB-INF/views/include/headtag.jsp"></jsp:include>
	<script>
		$(document).ready(function() {
			var ctxpath = "${ pageContext.request.contextPath }";
			
			$('#editTableSelection').click(function() {
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
						return false;
					}
				}
			});	
			
			$('#addTableSelection').click(function() {
				$('#addTableSelection').attr("href", ctxpath + "/price/addpricelevel/");
					
				
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
					<span class="fa fa-dollar fa-fw"></span>&nbsp;Price
				</h1>

				<div class="panel panel-default">
					<div class="panel-heading">
						<h1 class="panel-title">
							<span class="fa fa-table fa-fw fa-2x"></span>Price Level
						</h1>
					</div>
					<c:choose>
					<c:when test="${PAGEMODE == 'PAGEMODE_PAGELOAD'}">
					<div class="panel-body">
					
						
								<div class="table-responsive">
									<table class="table table-bordered table-hover">
										<thead>
											<tr>
												<th width="5%">&nbsp;</th>
												<th width="20%">Price Level</th>
												
											</tr>
										</thead>
										<tbody>
											<c:if test="${not empty priceLevelList}">
												<c:forEach items="${ priceLevelList }" var="p" varStatus="status">
													<tr>
														<td align="center"><input id="cbx_<c:out value="${ p.priceLevelId }"/>" type="checkbox" value="<c:out value="${ p.priceLevelId }"/>" /></td>
														<td><c:out value="${ p.levelName }"></c:out></td>
														
													</tr>
												</c:forEach>
											</c:if>
										</tbody>
									</table>
								</div>
								<a id="addTableSelection" class="btn btn-sm btn-primary" href=""><span class="fa fa-edit fa-fw"></span>&nbsp;Add</a>
								<a id="editTableSelection" class="btn btn-sm btn-primary" href=""><span class="fa fa-edit fa-fw"></span>&nbsp;Edit</a>
							
					</div>
					</c:when>
					
					<c:when test="${PAGEMODE == 'PAGEMODE_EDIT' || PAGEMODE == 'PAGEMODE_ADD'}">						
						<div class="panel panel-default">
							<div class="panel-heading">
								<h1 class="panel-title">
									<span class="fa fa-code-fork fa-fw fa-2x"></span>&nbsp;Price Level List
								</h1>
							</div>								
							<div class="panel-body">
								<form:form id="todayPriceForm" role="form" class="form-horizontal" modelAttribute="priceLevelForm" action="${pageContext.request.contextPath}/price/savepricelevel">
									<div class="row">
										<div class="col-md-12">
											<div class="panel panel-default">
												<div class="panel-body">
													<div class="row">
														<div class="col-md-7">
															<div class="form-group">
																<label for="inputPriceLevelName" class="col-sm-2 control-label">Price Level Name</label>
																<div class="col-sm-5">
																	<form:hidden path="priceLevelId"/>
																	<form:input type="text" class="form-control" id="inputPriceLevelName" name="inputPriceLevelName" path="levelName" placeholder="Enter Sales Code"></form:input>
																</div>										
															</div>
															<div class="form-group">
																<label for="inputAddition" class="col-sm-2 control-label">Addition</label>
																<div class="col-sm-5">
																	
																	<form:input type="text" class="form-control" id="inputAddition" name="inputAddition" path="addition" placeholder="Enter Sales Code"></form:input>
																</div>										
															</div>
															<div class="form-group">
																<label for="inputSubtraction" class="col-sm-2 control-label">Subtraction</label>
																<div class="col-sm-5">
																	
																	<form:input type="text" class="form-control" id="inputSubtraction" name="inputSubtraction" path="subtraction" placeholder="Enter Sales Code"></form:input>
																</div>										
															</div>
															
															
														</div>
														
													</div>
													
																	
												</div>
											</div>
											
											
											<div class="col-md-7 col-offset-md-5">
													<div class="btn-toolbar">
														<button id="cancelButton" type="reset" class="btn btn-primary pull-right">Cancel</button>
														<button id="submitButton" type="submit" class="btn btn-primary pull-right">Submit</button>
													</div>
												</div>
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
	</div>	
</body>
</html>
