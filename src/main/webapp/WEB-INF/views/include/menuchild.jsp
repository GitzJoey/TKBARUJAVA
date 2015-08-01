<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<c:set var="fList" value="${ requestScope.functionList }"/>
<ul>
	<c:forEach var="f" items="${ fList }">
		<c:choose>
			<c:when test="${ fn:length(f.subFunctions) gt 0 }">
				<li>
					<a href="#"><i class="fa fa-plus fa-fw"></i><c:out value="${ f.menuName }"/></a>					
					<c:set var="functionList" value="${ f.subFunctions }" scope="request"/>
					<c:set var="parentScope" value="${ f.functionId }" scope="request"/>
					<c:import url="/WEB-INF/views/include/menuchild.jsp"/>
				</li>
			</c:when>
			<c:otherwise>
				<li>
					<a href="#">
						<i class="fa fa-plus fa-fw"></i>
						<c:out value="${ f.menuName }"/>
					</a>
				</li>
			</c:otherwise>
		</c:choose>
	</c:forEach>
</ul>	