<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:if test="${ module == 'supplier' }">
	<tr>
		<td><form:hidden path="supplierForm.bankAccList[${counter}].bankAccDetail.bankAccId"/></td>
		<td><form:input type="text" class="form-control" id="inputBankName" name="inputBankName" path="supplierForm.bankAccList[${counter}].bankAccDetail.bankName"></form:input></td>
		<td><form:input type="text" class="form-control" id="inputShortName" name="inputShortName" path="supplierForm.bankAccList[${counter}].bankAccDetail.shortName"></form:input></td>
		<td><form:input type="text" class="form-control" id="inputAccNum" name="inputAccNum" path="supplierForm.bankAccList[${counter}].bankAccDetail.accNum"></form:input></td>
		<td><form:input type="text" class="form-control" id="inputBankRemarks" name="inputBankRemarks" path="supplierForm.bankAccList[${counter}].bankAccDetail.bankRemarks"></form:input></td>
		<td>
			<form:select class="form-control" path="supplierForm.bankAccList[${counter}].bankAccDetail.bankStatus">
				<form:options items="${ statusDDL }" itemValue="lookupCode" itemLabel="lookupDescription"/>
			</form:select>
		</td>
	</tr>
</c:if>

<c:if test="${ module == 'customer' }">
	<tr>
		<td align="center">
			<input id="cbx_bankAccId_${ customerForm.bankAccList[0].bankAccId }" type="checkbox" value="${counter}"/>
			<form:hidden path="customerForm.bankAccList[${counter}].bankAccId" value="${ customerForm.bankAccList[0].bankAccId }"></form:hidden>
			<form:hidden path="customerForm.bankAccList[${counter}].shortName" value="${ customerForm.bankAccList[0].shortName }"></form:hidden>
			<form:hidden path="customerForm.bankAccList[${counter}].bankName" value="${ customerForm.bankAccList[0].bankName }"></form:hidden>
			<form:hidden path="customerForm.bankAccList[${counter}].accNum" value="${ customerForm.bankAccList[0].accNum }"></form:hidden>
			<form:hidden path="customerForm.bankAccList[${counter}].bankRemarks" value="${ customerForm.bankAccList[0].bankRemarks }"></form:hidden>
			<form:hidden path="customerForm.bankAccList[${counter}].bankStatus" value="${ customerForm.bankAccList[0].bankStatus }"></form:hidden>
		</td>
		<td>&nbsp;<form:label path="customerForm.bankAccList[${counter}].shortName"><c:out value="${ customerForm.bankAccList[0].shortName }"></c:out></form:label>&nbsp;-&nbsp;<form:label path="customerForm.bankAccList[${counter}].bankName"><c:out value="${ customerForm.bankAccList[0].bankName }"></c:out></form:label></td>
		<td>&nbsp;<form:label path="customerForm.bankAccList[${counter}].accNum"><c:out value="${ customerForm.bankAccList[0].accNum }"></c:out></form:label></td>
		<td>&nbsp;<form:label path="customerForm.bankAccList[${counter}].bankRemarks"><c:out value="${ customerForm.bankAccList[0].bankRemarks }"></c:out></form:label></td>
		<td>&nbsp;<form:label path="customerForm.bankAccList[${counter}].bankStatus"><c:out value="${ customerForm.bankAccList[0].bankStatus }"></c:out></form:label></td>
	</tr>
</c:if>