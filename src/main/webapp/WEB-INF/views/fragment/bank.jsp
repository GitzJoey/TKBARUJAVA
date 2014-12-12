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