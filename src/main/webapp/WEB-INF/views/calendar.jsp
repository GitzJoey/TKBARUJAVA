<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
	<jsp:include page="/WEB-INF/views/include/headtag.jsp"></jsp:include>
	<script>
		$(document).ready(function() {
			var ctxpath = "${ pageContext.request.contextPath }";
			var uid = "${ loginContext.userLogin.userId }";
			
			$('#cancelButton').click(function() {
				window.location.href = ctxpath + "/user/calendar";
			});

			$('#editTableSelection, #deleteTableSelection').click(function() {
				var id = "";
				var button = $(this).attr('id');
								
				$('input[type="checkbox"][id^="cbx_"]').each(function(index, item) {
					if ($(item).prop('checked')) {
						id = $(item).attr("value");	
					}
				});
				
				if (id == "") {
					jsAlert("Please select at least 1 events");
					return false;	
				} else {
					if (button == 'editTableSelection') {
						$('#editTableSelection').attr("href", ctxpath + "/user/calendar/edit/" + id);
					} else {
						$('#deleteTableSelection').attr("href", ctxpath + "/user/calendar/delete/" + id);
					}						
				}
			});
			
			$('#inputStartDate, #inputEndDate').datetimepicker({ format:'d-m-Y', timepicker:false });
			
			$.ajax({
				url : ctxpath + "/user/calendar/get/cal/",
				data : 'userId=' + encodeURIComponent(uid),
				type : "GET",
				async: false,
				success : function(response) {
					var obj = JSON.parse(JSON.stringify(response, null, 4));
					var evvL = [];

					for (i=0; i<obj.length; i++) {
						var evv = new Object();

						evv.start = obj[i].startDateToString;
						evv.title = obj[i].eventTitle;
						evvL.push(evv);						
					}
					
					$('#calendar').fullCalendar({
						header: {
							left: 'prev,next today',
							center: 'title',
							right: 'month,agendaWeek,agendaDay'
						},
						editable: true,
						eventLimit: true,
						events: evvL,
					    dayClick: function() {
					        alert('a day has been clicked!');
					    }
		    		});
				},
				error : function(xhr, status, error) {
					alert(xhr.responseText);
				}
			});
		});
	</script>
</head>
<body>
	<div id="wrapper" class="container-fluid">

		<jsp:include page="/WEB-INF/views/include/topmenu.jsp"></jsp:include>

		<div class="row">
			<div class="col-md-2">
				<jsp:include page="/WEB-INF/views/include/sidemenu.jsp"></jsp:include>
			</div>
			<div id="content" class="col-md-10">
				<c:if test="${ ERRORFLAG == 'ERRORFLAG_SHOW' }">
	    			<div class="alert alert-danger alert-dismissible collapse in" role="alert">
	  					<button type="button" class="close" data-dismiss="alert">
	  						<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
	  					</button>
	  					<h4><strong>Warning!</strong></h4>
	  					<br>
	  					<c:out value="${ errorMessageText }"/>
					</div>
				</c:if>
				
				<div id="jsAlerts"></div>

				<h1>
					<span class="fa fa-calendar fa-fw"></span>&nbsp;<spring:message code="calendar_jsp.title" text="Calendar"/>
				</h1>
				
				<c:choose>
					<c:when test="${ PAGEMODE == 'PAGEMODE_PAGELOAD' || PAGEMODE == 'PAGEMODE_LIST' }">
						<div class="panel panel-default">
							<div class="panel-heading">
								<h1 class="panel-title">
									<span class="fa fa-calendar fa-fw fa-2x"></span>&nbsp;<spring:message code="calendar_jsp.subtitle" text="Events List"/>
								</h1>
							</div>
							<div class="panel-body">
								<table id="calendarListTable" class="table table-bordered table-hover display dt-responsive nowrap" style="width: 100%; border-collapse: separate;">
									<thead>
										<tr>
											<th width="5%">&nbsp;</th>
											<th width="10%"><spring:message code="calendar_jsp.table.header.start_date" text="Start Date"/></th>
											<th width="10%"><spring:message code="calendar_jsp.table.header.end_date" text="End Date"/></th>
											<th width="25%"><spring:message code="calendar_jsp.table.header.events" text="Events"/></th>
											<th width="40%"><spring:message code="calendar_jsp.table.header.ext_url" text="URL"/></th>
											<th width="10%"><spring:message code="calendar_jsp.table.header.status" text="Status"/></th>
										</tr>
									</thead>
									<tbody>
										<c:if test="${ not empty calendarList }">
											<c:forEach items="${ calendarList }" var="c" varStatus="cIdx">
												<tr>
													<td align="center"><input id="cbx_<c:out value="${ c.calendarId }"/>" type="checkbox" value="<c:out value="${ c.calendarId }"/>"/></td>
													<td><fmt:formatDate pattern="dd MMM yyyy" value="${ c.startDate }"/></td>
													<td><fmt:formatDate pattern="dd MMM yyyy" value="${ c.endDate }"/></td>
													<td><c:out value="${ c.eventTitle }"></c:out></td>
													<td><c:out value="${ c.extURL }"></c:out></td>
													<td></td>
												</tr>
											</c:forEach>
										</c:if>
									</tbody>
								</table>
								<a id="BackButton" class="btn btn-sm btn-primary" href="${pageContext.request.contextPath}/user/profile/view/${id}"><span class="fa fa-arrow-left fa-fw"></span>&nbsp;<spring:message code="common.back_button" text="Back"/></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<a id="addNew" class="btn btn-sm btn-primary" href="${pageContext.request.contextPath}/user/calendar/add"><span class="fa fa-plus fa-fw"></span>&nbsp;<spring:message code="common.add_button" text="Add"/></a>&nbsp;&nbsp;&nbsp;
								<a id="editTableSelection" class="btn btn-sm btn-primary" href=""><span class="fa fa-edit fa-fw"></span>&nbsp;<spring:message code="common.edit_button" text="Edit"/></a>&nbsp;&nbsp;&nbsp;
								<a id="deleteTableSelection" class="btn btn-sm btn-primary" href=""><span class="fa fa-close fa-fw"></span>&nbsp;<spring:message code="common.delete_button" text="Delete"/></a>
							</div>
						</div>
						<br/>
						<div class="panel panel-default">
							<div class="panel-body">
								<div id="calendar"></div>
							</div>
						</div>						
					</c:when>
					<c:when test="${ PAGEMODE == 'PAGEMODE_ADD' || PAGEMODE == 'PAGEMODE_EDIT' }">
						<div class="panel panel-default">
							<div class="panel-heading">
								<h1 class="panel-title">
									<c:choose>
										<c:when test="${ PAGEMODE == 'PAGEMODE_ADD' }">
											<span class="fa fa-plus fa-fw fa-2x"></span>&nbsp;<spring:message code="calendar_jsp.add_events" text="Add Events"/>
										</c:when>
										<c:otherwise>
											<span class="fa fa-edit fa-fw fa-2x"></span>&nbsp;<spring:message code="calendar_jsp.edit_events" text="Edit Events"/>
										</c:otherwise>
									</c:choose>
								</h1>
							</div>
							<div class="panel-body">
								<form:form id="calendarForm" role="form" class="form-horizontal" modelAttribute="calendarForm" action="${pageContext.request.contextPath}/user/calendar/save" data-parsley-validate="parsley">
									<form:hidden path="calendarId"/>
									<form:hidden path="userId"/>
									<form:hidden path="createdBy"/>
									<form:hidden path="createdDate"/>
									<div class="form-group">
										<label for="inputStartDate" class="col-sm-2 control-label"><spring:message code="calendar_jsp.start_date" text="Start Date"/></label>
										<div class="col-sm-3">
											<form:input type="text" class="form-control" id="inputStartDate" name="inputStartDate" path="startDate" placeholder="Start Date" data-parsley-required="true"></form:input>
										</div>
									</div>
									<div class="form-group">
										<label for="inputEndDate" class="col-sm-2 control-label"><spring:message code="calendar_jsp.end_date" text="End Date"/></label>
										<div class="col-sm-3">
											<form:input type="text" class="form-control" id="inputEndDate" name="inputEndDate" path="endDate" placeholder="End Date"></form:input>
										</div>
									</div>
									<div class="form-group">
										<label for="inputEventTitle" class="col-sm-2 control-label"><spring:message code="calendar_jsp.event_title" text="Events Title"/></label>
										<div class="col-sm-4">
											<form:input type="text" class="form-control" id="inputEventTitle" name="inputEventTitle" path="eventTitle" placeholder="Enter Events Title" data-parsley-required="true" data-parsley-length="[3, 30]" data-parsley-trigger="keyup"></form:input>
										</div>
									</div>
									<div class="form-group">
										<label for="inputExtURL" class="col-sm-2 control-label"><spring:message code="calendar_jsp.ext_url" text="URL"/></label>
										<div class="col-sm-6">
											<form:input type="text" class="form-control" id="inputExtURL" name="inputExtURL" path="extURL" placeholder="Enter URL" data-parsley-type='url'></form:input>
										</div>
									</div>
									<div class="col-md-7 col-offset-md-5">
										<div class="btn-toolbar">
											<button id="cancelButton" type="reset" class="btn btn-primary pull-right"><spring:message code="common.cancel_button" text="Cancel"/></button>
											<button id="submitButton" type="submit" class="btn btn-primary pull-right"><spring:message code="common.submit_button" text="Submit"/></button>
										</div>
									</div>
								</form:form>
							</div>
						</div>		
					</c:when>
					<c:otherwise>
					</c:otherwise>
				</c:choose>
			</div>
		</div>
		
		<jsp:include page="/WEB-INF/views/include/footer.jsp"></jsp:include>
	
	</div>
</body>
</html>
