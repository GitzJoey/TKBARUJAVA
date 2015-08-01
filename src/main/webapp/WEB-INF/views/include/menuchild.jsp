<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<c:set var="fList" value="${ requestScope.functionList }"/>
<ul>
	<c:forEach var="f" items="${ fList }">
		<c:choose>
			<c:when test="${ fn:length(f.subFunctions) gt 0 }">
				<li>
					<c:choose>
						<c:when test="${ f.urlLink != '#' }">
							<a href="${ pageContext.request.contextPath }${ f.urlLink }"><i class="${ f.menuIcon }"></i><c:out value="${ f.menuName }"/></a>
						</c:when>
						<c:otherwise>
							<a href="${ f.urlLink }"><i class="${ f.menuIcon }"></i><c:out value="${ f.menuName }"/></a>
						</c:otherwise>
					</c:choose>
					<c:set var="functionList" value="${ f.subFunctions }" scope="request"/>
					<c:set var="parentScope" value="${ f.functionId }" scope="request"/>
					<c:import url="/WEB-INF/views/include/menuchild.jsp"/>
				</li>
			</c:when>
			<c:otherwise>
				<li>
					<c:choose>
						<c:when test="${ f.urlLink != '#' }">
							<a href="${ pageContext.request.contextPath }${ f.urlLink }"><i class="${ f.menuIcon }"></i><c:out value="${ f.menuName }"/></a>
						</c:when>
						<c:otherwise>
							<a href="${ f.urlLink }"><i class="${ f.menuIcon }"></i><c:out value="${ f.menuName }"/></a>
						</c:otherwise>
					</c:choose>
				</li>
			</c:otherwise>
		</c:choose>
	</c:forEach>
</ul>	