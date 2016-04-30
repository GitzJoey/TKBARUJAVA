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
<nav id="sidebar" class="tuxedo-menu tuxedo-menu-fixed tuxedo-menu-slide-closing tuxedo-menu-pristine">
  <ul>
    <li class="menu-depth-0"><a href="#">Item 1</a></li>
    <li class="menu-depth-0">
      <div class="heading">Heading 2</div>
    </li>
    <li class="menu-depth-0"><a href="#">Item 2</a></li>
    <li class="menu-depth-1"><a href="#">Item 2.1</a></li>
    <li class="menu-depth-1"><a href="#">Item 2.2</a></li>
    <li class="menu-depth-2"><a href="#">Item 2.2.1</a></li>
    <li class="menu-depth-0"><a href="#">Item 3</a></li>
    <li class="menu-depth-0">
      <div class="heading">Heading 4</div>
    </li>
    <li class="menu-depth-0"><a href="#">Item 4</a></li>
    <li class="menu-depth-0">
      <div class="heading">Heading 4.1</div>
    </li>
    <li class="menu-depth-1"><a href="#">Item 4.1</a></li>
  </ul>
</nav>

	<div id="wrapper" class="container-fluid">

		<jsp:include page="/WEB-INF/views/include/topmenu.jsp"></jsp:include>

		<div class="row">
			<div class="col-md-2">
				<jsp:include page="/WEB-INF/views/include/sidemenu.jsp"></jsp:include>
			</div>
			<div id="content" class="col-md-10">
				<c:if test="${ ERRORFLAG == 'ERRORFLAG_SHOW' }">
	    			<div class="alert alert-danger alert-dismissible collapse in" role="alert">
	  					<button type="button" class="close" data-dismiss="alert">
	  						<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
	  					</button>
	  					<h4><strong>Warning!</strong></h4>
	  					<br>
	  					<c:out value="${ errorMessageText }"/>
					</div>
				</c:if>
				
				<div id="jsAlerts"></div>

				<h1>
					<span class="fa fa-plus fa-fw"></span>&nbsp;<spring:message code="" text="Title"/>
				</h1>
				
				<c:choose>
					<c:when test="${true}">
						<div class="panel panel-default">
							<div class="panel-heading">
								<h1 class="panel-title">
									<span class="fa fa-plus fa-fw fa-2x"></span><spring:message code="" text="Sub Title"/>
								</h1>
							</div>
							<div class="panel-body">
								Contents<span class="tuxedo-menu-trigger">&#9776;</span>
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
