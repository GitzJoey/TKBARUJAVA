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
					<span class="fa fa-truck fa-fw"></span>&nbsp;Purchase Order
				</h1>

				<div class="panel panel-default">
					<div class="panel-heading">
						<h1 class="panel-title">
							<c:choose>
								<c:when test="${PAGEMODE == 'PAGEMODE_ADD'}">
									<span class="fa fa-truck fa-fw fa-2x"></span>&nbsp;New Purchase Order
								</c:when>
							</c:choose>
						</h1>
					</div>
					<div class="panel-body">
						<form:form id="poForm" role="form" class="form-horizontal" modelAttribute="poForm" action="${pageContext.request.contextPath}/po/save">
							<div class="row">
								
							</div>
						</form:form>
					</div>					
				</div>						
			</div>
		</div>
	</div>	
</body>
</html>
