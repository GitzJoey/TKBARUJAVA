<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

				<div class="panel panel-default">
					<div id="userprofile">
						<table class="table borderless">
							<tr>
								<td>
									<c:choose>
										<c:when test="${ not empty loginContext.userLogin.personEntity.photoPath }">
											<img class="img-responsive" alt="user" src="${ pageContext.request.contextPath }/resources/images/user/${ loginContext.userLogin.personEntity.photoPath }">
										</c:when>
										<c:otherwise>
											<img class="img-responsive" alt="user" src="${ pageContext.request.contextPath }/resources/images/def-user.png">
										</c:otherwise>
									</c:choose>
								</td>
								<td>
									<c:out value="${ loginContext.userLogin.userName }"></c:out>
									<br/>
									Role: <c:out value="${ loginContext.userLogin.roleEntity.roleName }"></c:out>
								</td>
							</tr>
							<tr>
								<td colspan="2">
								</td>
							</tr>
						</table>
					</div>
					<div class="panel-footer">
						<div class="btn-toolbar">
							<form action="${ pageContext.request.contextPath }/logout" method="post">
								<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
  								<input type="submit" class="btn btn-primary btn-xs pull-right" value="Logout"/>
							</form>
							<a href="${ pageContext.request.contextPath }/admin/user/view/${ loginContext.userLogin.userId }" class="btn btn-primary btn-xs pull-right"><span class="fa fa-user">&nbsp;Profile</span></a>
						</div>					
					</div>
				</div>

				<div class="panel panel-default">
					<ul class="navgoco navgoco-root">
						<li><a class="root-menu" href="#"><i class="fa fa-plus fa-fw"></i>Menu</a>							
							<c:set var="functionList" value="${ loginContext.userLogin.roleEntity.allRootFunctions }" scope="request"/>
							<c:import url="/WEB-INF/views/include/menuchild.jsp"/>
							<c:remove var="functionList" scope="request"/>
						</li>
					</ul>					
				</div>