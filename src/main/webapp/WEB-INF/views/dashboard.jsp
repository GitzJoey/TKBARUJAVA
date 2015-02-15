<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<jsp:include page="/WEB-INF/views/include/headtag.jsp"></jsp:include>
	<script>
		$(document).ready(function() {
			
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
				<div class="row">
					<div class="col-md-3">
						<div class="panel panel-default">
  							<div class="panel-body">
								<br/>
								<br/>
								<br/>
								<br/>
								<br/>
							</div>
						</div>
					</div>
					<div class="col-md-3">
						<div class="panel panel-default">
  							<div class="panel-body">
								<br/>
								<br/>
								<br/>
								<br/>
								<br/>
							</div>
						</div>
					</div>
					<div class="col-md-3">
						<div class="panel panel-default">
  							<div class="panel-body">
								<br/>
								<br/>
								<br/>
								<br/>
								<br/>
							</div>
						</div>
					</div>
					<div class="col-md-3">
						<div class="panel panel-default">
  							<div class="panel-body">
								<br/>
								<br/>
								<br/>
								<br/>
								<br/>
							</div>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-md-8">
						<div class="panel panel-default">
  							<div class="panel-body">
								<br/>
								<br/>
								<br/>
								<br/>
								<br/>
								<br/>
								<br/>
								<br/>
								<br/>
								<br/>
								<br/>
								<br/>
								<br/>
								<br/>
							</div>
						</div>
					</div>
					<div class="col-md-4">
						<div class="panel panel-default">
							<ul class="list-group">
								<li class="list-group-item">&nbsp;</li>
								<li class="list-group-item">&nbsp;</li>
								<li class="list-group-item">&nbsp;</li>
								<li class="list-group-item">&nbsp;</li>
								<li class="list-group-item">&nbsp;</li>
								<li class="list-group-item">&nbsp;</li>
								<li class="list-group-item">&nbsp;</li>
							</ul>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-md-12">
						<input type='text' class="form-control" id='datetimepicker4'/>
				        <script type="text/javascript">
				            $(function () {
				                $('#datetimepicker4').datetimepicker();
				            });
				        </script>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
