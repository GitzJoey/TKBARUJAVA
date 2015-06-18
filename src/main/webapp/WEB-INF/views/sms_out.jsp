<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/WEB-INF/views/include/headtag.jsp"></jsp:include>
<script>
	$(document).ready(
			function() {
				var ctxpath = "${ pageContext.request.contextPath }";

				$('#cancelButton').click(function() {
					window.location.href(ctxpath + "/truck");
				});

				$('input[type="checkbox"][id^="cbx_"]').click(
						function() {
							var selected = $(this);

							$('input[type="checkbox"][id^="cbx_"]').each(
									function(index, item) {
										if ($(item).attr("id") != $(selected)
												.attr("id")) {
											if ($(item).prop("checked")) {
												$(item).prop("checked", false);
											}
										}
									});
						});

				$('#editTableSelection, #deleteTableSelection').click(
						function() {
							var id = "";
							var button = $(this).attr('id');

							$('input[type="checkbox"][id^="cbx_"]').each(
									function(index, item) {
										if ($(item).prop('checked')) {
											id = $(item).attr("value");
										}
									});

							if (id == "") {
								jsAlert("Please select at least 1 truck");
								return false;
							} else {
								if (button == 'editTableSelection') {
									$('#editTableSelection').attr("href",
											ctxpath + "/truck/edit/" + id);
								} else {
									$('#deleteTableSelection').attr("href",
											ctxpath + "/truck/delete/" + id);
								}
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
					<span class="fa fa-truck fa-flip-horizontal fa-fw"></span>&nbsp;SMS
					SEND
				</h1>


				<div class="panel panel-default">
					<div class="panel-heading">
						<h1 class="panel-title">
							<span class="fa fa-truck fa-flip-horizontal fa-fw fa-2x"></span>&nbsp;Send
							SMS
						</h1>
					</div>
					<div class="panel-body">
						<form:form id="sendForm" role="form" class="form-horizontal"
							modelAttribute="smsOut"
							action="${pageContext.request.contextPath}/smsout/send">
							<div class="row">
								<div class="col-md-7">
									<div class="form-group">
										<label for="inputTo" class="col-sm-2 control-label">To</label>
										<div class="col-sm-5">
											<form:input type="text" class="form-control" id="inputTo"
												path="recipient" placeholder="Enter Recepient"></form:input>
										</div>
									</div>
									<div class="form-group">
										<label for="inputMessage" class="col-sm-2 control-label">Message</label>
										<div class="col-sm-8">
											<form:textarea type="text" class="form-control" rows="3"
												cols="20" id="inputMessage" path="message"></form:textarea>
										</div>
									</div>
								</div>
							</div>
							<div class="col-md-7 col-offset-md-5">
								<div class="btn-toolbar">
									<button id="cancelButton" type="button"
										class="btn btn-primary pull-right">Cancel</button>
									<button id="submitButton" type="submit"
										class="btn btn-primary pull-right">Submit</button>
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
