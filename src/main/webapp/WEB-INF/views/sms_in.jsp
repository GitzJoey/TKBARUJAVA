<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
					Received
				</h1>




				<div class="panel panel-default">
					<div class="panel-heading">
						<h1 class="panel-title">
							<span class="fa fa-smile-o fa-fw fa-2x"></span>SMS List
						</h1>
					</div>
					<div class="panel-body">
						<div class="table-responsive">
							<table class="table table-bordered table-hover">
								<thead>
									<tr>
										<th width="5%">&nbsp;</th>
										<th width="20%">From</th>
										<th width="20%">Message</th>
										<th width="20%">Date</th>
									</tr>
								</thead>
								<tbody>
									<c:if test="${not empty smsList}">
										<c:forEach items="${ smsList }" var="i" varStatus="status">
											<tr>
												<td align="center"><input
													id="cbx_<c:out value="${ i.smsInId }"/>" type="checkbox"
													value="<c:out value="${ i.smsInId }"/>" /></td>
												<td><c:out value="${ i.sender }"></c:out></td>
												<td><c:out value="${ i.message }"></c:out><td><fmt:formatDate pattern="dd-MM-yyyy"
														value="${ i.createdDate }" /></td>


											</tr>
												</c:forEach>
											</c:if>
										</tbody>
									</table>
								</div>
								<a id="editTableSelection" class="btn btn-sm btn-primary"
							href=""><span class="fa fa-edit fa-fw"></span>&nbsp;Revise</a>
							</div>
						</div>
				
			</div>
		</div>

		<jsp:include page="/WEB-INF/views/include/footer.jsp"></jsp:include>

	</div>
</body>
</html>
