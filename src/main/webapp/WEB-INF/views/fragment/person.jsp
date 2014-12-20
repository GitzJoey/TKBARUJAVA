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

<c:if test="${ module == 'customer' }">
	<tr>
		<td align="center">
			<input id="cbx_personId_<c:out value="${ customerForm.picList[0].personId }"/>" type="checkbox" value="<c:out value="${counter}"/>"/>																		
			<form:hidden path="customerForm.picList[${counter}].personId"/>																																				
			<form:hidden path="customerForm.picList[${counter}].firstName"/>
			<form:hidden path="customerForm.picList[${counter}].lastName"/>
			<form:hidden path="customerForm.picList[${counter}].addressLine1"/>
			<form:hidden path="customerForm.picList[${counter}].addressLine2"/>
			<form:hidden path="customerForm.picList[${counter}].addressLine3"/>
			<form:hidden path="customerForm.picList[${counter}].emailAddr"/>
			<form:hidden path="customerForm.picList[${counter}].photoPath"/>
			<c:forEach items="${ customerForm.picList[0].phoneList }" varStatus="phoneListIdx">
				<form:hidden path="customerForm.picList[${counter}].phoneList[${phoneListIdx.index}].providerName"/>																			
				<form:hidden path="customerForm.picList[${counter}].phoneList[${phoneListIdx.index}].phoneNumber"/>
				<form:hidden path="customerForm.picList[${counter}].phoneList[${phoneListIdx.index}].phoneStatus"/>
				<form:hidden path="customerForm.picList[${counter}].phoneList[${phoneListIdx.index}].phoneNumRemarks"/>
			</c:forEach>
		</td>
		<td>&nbsp;<span id="customerForm.picList[${counter}].firstName"><c:out value="${ customerForm.picList[0].firstName }"></c:out></span>&nbsp;<span id="picList[${counter}].lastName"><c:out value="${ customerForm.picList[0].lastName }">/</c:out></span></td>
		<td>
			&nbsp;<span id="customerForm.picList[${counter}].addressLine1"><c:out value="${ customerForm.picList[0].addressLine1 }"></c:out></span><br/>
			&nbsp;<span id="customerForm.picList[${counter}].addressLine2"><c:out value="${ customerForm.picList[0].addressLine1 }"></c:out></span><br/>
			&nbsp;<span id="customerForm.picList[${counter}].addressLine3"><c:out value="${ customerForm.picList[0].addressLine1 }"></c:out></span><br/>
			<br/>
			<strong>Phone List</strong><br/>
			<c:forEach items="${ customerForm.picList[0].phoneList }" varStatus="phoneListIdx">
				<span id="customerForm.picList[${counter}].phoneList[${phoneListIdx.index}].providerName"><c:out value="${ customerForm.picList[0].phoneList[phoneListIdx.index].providerName }"></c:out></span>
				&nbsp;-&nbsp;
				<span id="customerForm.picList[${counter}].phoneList[${phoneListIdx.index}].phoneNumber"><c:out value="${ customerForm.picList[0].phoneList[phoneListIdx.index].phoneNumber }"></c:out></span>
				&nbsp;(&nbsp;
				<span id="customerForm.picList[${counter}].phoneList[${phoneListIdx.index}].phoneStatus"><c:out value="${ customerForm.picList[0].phoneList[phoneListIdx.index].phoneStatus }"></c:out></span>
				&nbsp;-&nbsp;
				<span id="customerForm.picList[${counter}].phoneList[${phoneListIdx.index}].phoneNumRemarks"><c:out value="${ customerForm.picList[0].phoneList[phoneListIdx.index].phoneNumRemarks }"></c:out></span>
				&nbsp;)&nbsp;
				<br/>
			</c:forEach>
		</td>
		<td>&nbsp;<span id="customerForm.picList[${counter}].emailAddr"><c:out value="${ customerForm.picList[0].emailAddr }"></c:out></span></td>
		<td></td>
	</tr>
</c:if>