<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
				<c:if test="${ not empty loginContext }">
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
	  								<button type="submit" class="btn btn-primary btn-xs pull-right" value="Logout"><span class="fa fa-child">&nbsp;<spring:message code="common.logout_button" text="Logout"/></span></button>
								</form>
								<a href="${ pageContext.request.contextPath }/admin/user/view/${ loginContext.userLogin.userId }" class="btn btn-primary btn-xs pull-right"><span class="fa fa-user">&nbsp;<spring:message code="common.profile_button" text="Profile"/></span></a>
							</div>					
						</div>
					</div>

					<c:if test="${ empty param.hideMenu }">				
						<div class="navbar-default sidebar" role="navigation">
							<div class="sidebar-nav navbar-collapse in override-navbar-collapse">
								<ul id="menu" class="nav metismenu">
									<li class="active">
										<c:set var="functionList" value="${ loginContext.userLogin.roleEntity.allRootFunctions }" scope="request"/>
										<c:import url="/WEB-INF/views/include/menuchild.jsp"/>
										<c:remove var="functionList" scope="request"/>
									</li>
								</ul>						
							</div>
						</div>
					</c:if>
				</c:if>
				<br/>