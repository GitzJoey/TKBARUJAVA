<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<tr>
	<td width="25%">
		<form:input type="text" class="form-control" id="inputProvider" name="inputProvider" path="userForm.personEntity.phoneList[${addphonecount}].providerName" placeholder="Provider"></form:input>
	</td>
	<td>
		<form:input type="text" class="form-control" id="inputPhoneNum" name="inputPhoneNum" path="userForm.personEntity.phoneList[${addphonecount}].phoneNumber" placeholder="Phone Number"></form:input>
	</td>
</tr>