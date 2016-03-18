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
		});
	</script>
</head>
<body>
	<div id="wrapper" class="container-fluid">
		<form:form id="userForm" role="form" class="form-horizontal" action="${pageContext.request.contextPath}/static/forgot/reset" data-parsley-validate="parsley">
			<div class="row">
				<div class="col-md-3">
					<label for="inputUserName" class="control-label">Please input your user name or email to reset</label>
					<input type="text" class="form-control" id="inputUserName" name="inputUserName" placeholder="Input User Name or Email" data-parsley-required="true"/>
					<br/>
					<button type="submit" class="btn btn-default">Submit</button>
					<a href="${ pageContext.request.contextPath }/login.html" class="btn btn-default">Cancel</a>
				</div>
			</div>
		</form:form>
	</div>
</body>
</html>