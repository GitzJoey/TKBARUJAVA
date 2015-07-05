<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

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
								<div class="btn-toolbar">
									<a href="${ pageContext.request.contextPath }/logout.html" class="btn btn-primary btn-xs pull-right"><span class="fa fa-child">&nbsp;Logout</span></a>
									<a href="${ pageContext.request.contextPath }/admin/user/view/${ loginContext.userLogin.userId }" class="btn btn-primary btn-xs pull-right"><span class="fa fa-user">&nbsp;Profile</span></a>
								</div>
							</td>
						</tr>
					</table>
				</div>

				<div class="panel-group" id="accordion">
					<c:set var="currentModule" value=""/>
					<c:if test="${ not empty loginContext.userLogin.roleEntity.functionList }">
						<c:forEach items="${ loginContext.userLogin.roleEntity.functionList }" var="fList">
							<c:if test="${ currentModule != fList.module }">
								<c:set var="currentModule" value="${ fList.module }"/>
					            <div class="panel panel-default">
									<div class="panel-heading">
										<h4 class="panel-title no-underline">
											<a data-toggle="collapse" data-parent="#accordion" href="#collapse_<c:out value="${ fList.functionCode }"/>">
												<span class="<c:out value="${ fList.moduleIcon }"/>"></span>&nbsp;<c:out value="${ fList.module }"/>
											</a>
					                	</h4>
					              	</div>
									<div id="collapse_<c:out value="${ fList.functionCode }"/>" class="panel-collapse collapse">
										<ul class="list-group">
											<fmt:parseNumber var="intCurrentDeepLevel" integerOnly="true" type="number" value="1" />				
											<c:forEach items="${ loginContext.userLogin.roleEntity.functionList }" var="ffList">
												<c:if test="${ ffList.module == currentModule }">
												<fmt:parseNumber var="intDeepLevel" integerOnly="true" type="number" value="1" />
													<c:choose>
														<c:when test="${ intDeepLevel != intCurrentDeepLevel }">
															<fmt:parseNumber var="intCurrentDeepLevel" integerOnly="true" type="number" value="${ intCurrentDeepLevel + 1 }" />
															<ul class="list-group">
																<li class="list-group-item"><span class="<c:out value="${ ffList.menuIcon }"/>"></span><a href="${pageContext.request.contextPath}<c:out value="${ ffList.urlLink }"/>">&nbsp;<c:out value="${ ffList.menuName }"/></a></li>
															</ul>															
														</c:when>
														<c:otherwise>
															<li class="list-group-item"><span class="<c:out value="${ ffList.menuIcon }"/>"></span><a href="${pageContext.request.contextPath}<c:out value="${ ffList.urlLink }"/>">&nbsp;<c:out value="${ ffList.menuName }"/></a></li>
														</c:otherwise>
													</c:choose>														
												</c:if>
											</c:forEach>											
										</ul>
									</div>		
								</div>
							</c:if>
						</c:forEach>
					</c:if>				
				</div>