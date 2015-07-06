<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<jsp:include page="/WEB-INF/views/include/headtag.jsp"></jsp:include>
</head>
<body>
    <div class="container-fluid">
        <div class="row">
        	<div class="center-block"><br/><br/><br/><br/><br/><br/></div>
    	</div>
    	<div class="row">
    		<div class="col-md-4 col-md-offset-4">
    			<div class="alert alert-warning alert-dismissible ${collapseFlag}" role="alert">
  					<button type="button" class="close" data-dismiss="alert">
  						<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
  					</button>
  					<strong>Warning!</strong>
  					<br>
  					${messageText}
				</div>
    		</div>
    	</div>
		<c:choose>
			<c:when test="${ hideLogin == true }">
			</c:when>
			<c:otherwise>
		        <div class="row">
		            <div class="col-md-4 col-md-offset-4">
		                <div class="login-panel panel panel-default">
		                    <div class="panel-heading">
		                        <h3 class="panel-title">Sign In</h3>
		                    </div>
		                    <div class="panel-body">
		                        <form id="loginForm" role="form" action="<c:url value="/login"/>" method="post" data-parsley-validate>
		                            <fieldset>
										<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
		                                <div class="form-group">
		                                    <input class="form-control" placeholder="UserName" name="username" type="text" autofocus data-parsley-length="[4, 10]" data-parsley-pattern="^[a-zA-Z0-9]+$" data-parsley-trigger="keyup">                                
		                                </div>
		                                <div class="form-group">
		                                    <input class="form-control" placeholder="Password" name="password" type="password" value="" data-parsley-minlength="6" data-parsley-trigger="keyup">
		                                </div>
		                                <button type="submit" class="btn btn-default">Submit</button>
		                            </fieldset>
		                        </form>
		                    </div>
		                </div>
		            </div>
		        </div>
			</c:otherwise>
		</c:choose>
    </div>
</body>
</html>