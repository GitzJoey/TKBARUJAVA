<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:if test="${ module == 'supplier' }">
	<tr>
		<td>
			<c:out value="picList.firstName"/>&nbsp;<c:out value="picList.lastName"/>
		</td>
		<td>
			<c:forEach items="${ picList.phoneList }" var="picPhoneList" varStatus="picPhoneIdx">
				<c:out value="picPhoneList.providerName"/>&nbsp;<c:out value="picPhoneList.phoneNumber"/>
			</c:forEach>
		</td>
	</tr>
</c:if>