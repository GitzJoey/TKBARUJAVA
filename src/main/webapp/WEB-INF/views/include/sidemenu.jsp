<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

				<div id="userprofile">
					<table class="table borderless">
						<tr>
							<td>
								<c:choose>
									<c:when test="${ not empty userContext.personEntity.photoPath }">
										<img class="img-responsive" alt="user" src="${pageContext.request.contextPath}/resources/images/def-user.png">
									</c:when>
									<c:otherwise>
										<img class="img-responsive" alt="user" src="${pageContext.request.contextPath}/resources/images/def-user.png">
									</c:otherwise>
								</c:choose>
							</td>
							<td>
								<c:out value="${ userContext.userName }"></c:out>
								<br/>
								Role: <c:out value="${ userContext.roleFunctionEntity.roleName }"></c:out>
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<div class="btn-toolbar">
									<a href="${pageContext.request.contextPath}/logout.html" class="btn btn-primary btn-xs pull-right"><span class="fa fa-child">&nbsp;Logout</span></a>
									<a href="${pageContext.request.contextPath}/user/list.html" class="btn btn-primary btn-xs pull-right"><span class="fa fa-user">&nbsp;Profile</span></a>
								</div>
							</td>
						</tr>
					</table>
				</div>

				<div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">

					<c:set var="currentModule" value=""/>
					<c:if test="${ not empty userContext.roleFunctionEntity.functionList }">
						<c:forEach items="${ userContext.roleFunctionEntity.functionList }" var="fList">
							<c:if test="${ currentModule != fList.module }">
								<c:set var="currentModule" value="${ fList.module }"/>	
								<div class="panel panel-default">
							    	<div class="panel-heading" role="tab" id="heading<c:out value="${ fList.functionCode }"/>">
							      		<h4 class="panel-title no-underline">
							        		<a class="collapsed" data-toggle="collapse" data-parent="#accordion" href="#collapse<c:out value="${ fList.functionCode }"/>" aria-expanded="false" aria-controls="collapse<c:out value="${ fList.functionCode }"/>">
							          			<span class="<c:out value="${ fList.moduleIcon }"/>"></span>&nbsp;<c:out value="${ fList.module }"/>
							        		</a>
							      		</h4>
							    	</div>
							    	<div id="collapse<c:out value="${ fList.functionCode }"/>" class="panel-collapse collapse" role="tabpanel" aria-labelledby="heading<c:out value="${ fList.functionCode }"/>">
							      		<div class="panel-body no-padding">
											<ul class="nav nav-pills nav-stacked" role="tablist">	
												<fmt:parseNumber var="intCurrentDeepLevel" integerOnly="true" type="number" value="1" />				
												<c:forEach items="${ userContext.roleFunctionEntity.functionList }" var="ffList">
													<c:if test="${ ffList.module == currentModule }">
														<fmt:parseNumber var="intDeepLevel" integerOnly="true" type="number" value="${ ffList.deepLevel }" />
														<c:choose>
															<c:when test="${ intDeepLevel != intCurrentDeepLevel }">
																<li role="presentation" class="nav-divider"></li>
																<li role="presentation" class=""><a href="${pageContext.request.contextPath}<c:out value="${ ffList.urlLink }"/>"><span class="<c:out value="${ ffList.menuIcon }"/>"></span>&nbsp;<c:out value="${ ffList.menuName }"/></a></li>																
																<fmt:parseNumber var="intCurrentDeepLevel" integerOnly="true" type="number" value="${ currentDeepLevel + 1 }" />								
															</c:when>
															<c:otherwise>
																<li role="presentation" class=""><a href="${pageContext.request.contextPath}<c:out value="${ ffList.urlLink }"/>"><span class="<c:out value="${ ffList.menuIcon }"/>"></span>&nbsp;<c:out value="${ ffList.menuName }"/></a></li>
															</c:otherwise>
														</c:choose>														
													</c:if>
												</c:forEach>
											</ul>
							      		</div>
							    	</div>
							  	</div>
							</c:if>																		
						</c:forEach>
					</c:if>
				
				</div>

