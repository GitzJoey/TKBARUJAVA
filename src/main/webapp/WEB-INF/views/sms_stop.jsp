<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/WEB-INF/views/include/headtag.jsp"></jsp:include>

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
					<div class="alert alert-danger alert-dismissible collapse in"
						role="alert">
						<button type="button" class="close" data-dismiss="alert">
							<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
						</button>
						<h4>
							<strong>Warning!</strong>
						</h4>
						<br> <c:out value="${ errorMessageText }"/>
					</div>
				</c:if>

				<div id="jsAlerts"></div>

				<h1>
					<span class="fa fa-cog fa-flip-horizontal fa-fw"></span>&nbsp;SMS
					Service Status
				</h1>


				<div class="panel panel-default">
					<div class="panel-heading">
						<h1 class="panel-title">
							<span class="fa fa-cog fa-flip-horizontal fa-fw fa-2x"></span>&nbsp;SMS service has been stopped
						</h1>
					</div>
					<div class="panel-body">
						SMS service has been stopped
					</div>
				</div>
			</div>
		</div>

		<jsp:include page="/WEB-INF/views/include/footer.jsp"></jsp:include>

	</div>
</body>
</html>
