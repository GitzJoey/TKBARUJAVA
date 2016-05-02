<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
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
  					<c:choose>
	  					<c:when test="${ not empty SPRING_SECURITY_LAST_EXCEPTION }">
	  						<spring:message code="spring_security_${ SPRING_SECURITY_LAST_EXCEPTION.message }" text="${ SPRING_SECURITY_LAST_EXCEPTION.message }"></spring:message>
  						</c:when>
  						<c:otherwise>
  							${ errorMessageText }
  						</c:otherwise>
  					</c:choose>
				</div>
    		</div>
    	</div>
		<c:choose>
			<c:when test="${ hideLogin == true }">
				<c:choose>
					<c:when test="${ true }"></c:when>
					<c:otherwise></c:otherwise>
				</c:choose>
			</c:when>
			<c:otherwise>
		        <div class="row">
		            <div class="col-md-4 col-md-offset-4">
		                <div class="login-panel panel panel-default">
		                    <div class="panel-heading">
		                        <h3 class="panel-title"><spring:message code="login_jsp.login_title" text="Sign In"/></h3>
		                    </div>
		                    <div class="panel-body">
		                        <form id="loginForm" role="form" action="<c:url value="/login"/>" method="post" data-parsley-validate="parsley">
		                            <fieldset>
										<sec:csrfInput/>
		                                <div class="form-group">
		                                    <input class="form-control" placeholder="UserName" name="username" type="text" autofocus data-parsley-length="[4, 10]" data-parsley-pattern="^[a-zA-Z0-9]+$" data-parsley-trigger="keyup">                              
		                                </div>
		                                <div class="form-group">
		                                    <input class="form-control" placeholder="Password" name="password" type="password" value="" data-parsley-minlength="6" data-parsley-trigger="keyup">
		                                </div>
										<div class="form-group">
											<div class="checkbox">
							        			<input id="cbx_remember_me" name="_spring_security_remember_me" type="checkbox" value=""/>
							        			<label for="cbx_remember_me"><spring:message code="login_jsp.remember_me" text="Remember Me"/></label> 
								        		<a class="pull-right" href="${ pageContext.request.contextPath }/static/forgot.html"><spring:message code="login_jsp.forgot_password" text="Forgot Password"></spring:message></a>
											</div>
										</div>
										<div class="button-toolbar">
											<button type="submit" class="btn btn-default"><spring:message code="common.login_button" text="Login"/></button>
										</div>
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