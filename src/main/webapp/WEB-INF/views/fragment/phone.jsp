<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<tr>
	<td align="center">
		<input type="checkbox" id="cbx_phoneListId_${rndmId}" value="${addphonecount}"/>																	
	</td>
	<td>
		<form:select class="form-control" path="personEntity.phoneList[${addphonecount}].providerName">
			<form:options items="${ providerDDL }" itemValue="lookupCode" itemLabel="lookupDescription"/>
		</form:select>																	
		<br/>
		<form:input path="personEntity.phoneList[${addphonecount}].phoneNumber" type="text" class="form-control" id="inputPhoneNum" name="inputPhoneNum" placeholder="Phone Number"></form:input>
		<br/>
		<form:input path="personEntity.phoneList[${addphonecount}].phoneNumRemarks" type="text" class="form-control" id="inputPhoneNumRemarks" name="inputPhoneNumRemarks" placeholder="Remarks"></form:input>
	</td>
	<td>
		<form:select class="form-control" path="personEntity.phoneList[${addphonecount}].phoneStatus">
			<form:options items="${ statusDDL }" itemValue="lookupCode" itemLabel="lookupDescription"/>
		</form:select>																																			
	</td>
</tr>													
