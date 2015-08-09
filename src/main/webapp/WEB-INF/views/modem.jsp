<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
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
				<c:if test="${ERRORPAGE == 'ERRORPAGE_SHOW'}">
					<div class="alert alert-danger alert-dismissible collapse"
						role="alert">
						<button type="button" class="close" data-dismiss="alert">
							<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
						</button>
						<h4>
							<strong>Warning!</strong>
						</h4>
						<br> ${errorMessageText}
					</div>
				</c:if>

				<div id="jsAlerts"></div>

				<h1>
					<span class="fa fa-cog fa-flip-horizontal fa-fw"></span>&nbsp;Modem
				</h1>


				<div class="panel panel-default">
					<div class="panel-heading">
						<h1 class="panel-title">
							<span class="fa fa-cog fa-flip-horizontal fa-fw fa-2x"></span>&nbsp;
							Setting
						</h1>
					</div>
					<div class="panel-body">
						<form:form id="sendForm" role="form" class="form-horizontal"
							modelAttribute="modem"
							action="${pageContext.request.contextPath}/admin/modem/save">
							<div class="row">
								<div class="col-md-7">
									<div class="form-group">
										<label for="inputModemId" class="col-sm-2 control-label">ID</label>
										<div class="col-sm-5">
											<form:input type="text" class="form-control" id="inputModemId"
												path="modemId" readonly="true"></form:input>
										</div>
									</div>
									<div class="form-group">
										<label for="inputPort" class="col-sm-2 control-label">Port</label>
										<div class="col-sm-8">
											<form:input type="text" class="form-control" 
												id="inputPort" path="port"></form:input>
										</div>
									</div>
									<div class="form-group">
										<label for="inputManufacturer" class="col-sm-2 control-label">Manufacturer</label>
										<div class="col-sm-8">
											<form:input type="text" class="form-control" 
												id="inputManufacturer" path="manufacturer"></form:input>
										</div>
									</div>
									<div class="form-group">
										<label for="inputModel" class="col-sm-2 control-label">Model</label>
										<div class="col-sm-8">
											<form:input type="text" class="form-control" 
												id="inputModel" path="model"></form:input>
										</div>
									</div>
									<div class="form-group">
										<label for="inputBaudRate" class="col-sm-2 control-label">Baud Rate</label>
										<div class="col-sm-8">
											<form:input type="text" class="form-control" 
												id="inputBaudRate" path="baudRate"></form:input>
										</div>
									</div>
									<div class="form-group">
										<label for="inputSmsc" class="col-sm-2 control-label">SMS Center</label>
										<div class="col-sm-8">
											<form:input type="text" class="form-control" 
												id="inputSmsc" path="smsCenter"></form:input>
										</div>
									</div>
								</div>
							</div>
							
							<div class="col-md-7 col-offset-md-5">
								<div class="btn-toolbar">
									<button id="cancelButton" type="button" class="btn btn-primary pull-right"><spring:message code="common.cancel_button" text="Cancel"/></button>
									<button id="submitButton" type="submit" class="btn btn-primary pull-right"><spring:message code="common.submit_button" text="Submit"/></button>
								</div>
							</div>
						</form:form>
					</div>
				</div>
			</div>
		</div>

		<jsp:include page="/WEB-INF/views/include/footer.jsp"></jsp:include>

	</div>
</body>
</html>
