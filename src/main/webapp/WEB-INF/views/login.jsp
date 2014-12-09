<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<jsp:include page="/WEB-INF/views/include/headtag.jsp"></jsp:include>
	<script>
		$(document).ready(function() {
			$('#loginForm').bootstrapValidator({
       			// To use feedback icons, ensure that you use Bootstrap v3.1.0 or later
       			feedbackIcons: {
           			valid: 'glyphicon glyphicon-ok',
           			invalid: 'glyphicon glyphicon-remove',
					validating: 'glyphicon glyphicon-refresh'
       			},
       			fields: {
           			username: {
               			validators: {
                   			notEmpty: { },
							stringLength: { min: 4, max: 10 },
							regexp: { regexp: /^[a-zA-Z0-9]+$/ },
	                   		different: { field: 'password' }
               			}
           			},
					password: {
               			validators: {
                   			notEmpty: {	},
                   			different: { field: 'username' },
                   			stringLength: { min: 6 }
               			}
           			}
       			}
   			});
		});
	</script>
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
		                        <form id="loginForm" role="form" action="${pageContext.request.contextPath}/dologin.html" method="post">
		                            <fieldset>
		                                <div class="form-group">
		                                    <input class="form-control" placeholder="UserName" name="username" type="text" autofocus>                                
		                                </div>
		                                <div class="form-group">
		                                    <input class="form-control" placeholder="Password" name="password" type="password" value="">
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