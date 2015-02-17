<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<jsp:include page="/WEB-INF/views/include/headtag.jsp"></jsp:include>
	<style type="text/css">
		.myclass1 {
			stroke: #000;
		}
		.myclass2 {
			stroke: #555;
		}
		.myclass3 {
			stroke: #bbb;
		}
		.myclass4 {
			stroke: #000;
		}
	</style>
	<script>
		$(document).ready(function() {
			new Chartist.Pie('#chart-1', {
				series: [
					{ data: 20, className: "myclass1" },
				    { data: 10, className: "myclass2" },
				    { data: 30, className: "myclass3" },
				    { data: 40, className: "myclass4" }] }
				, {	donut: true,
				  	donutWidth: 60,
				  	startAngle: 270,
				 	total: 200,
				  	showLabel: true });
			new Chartist.Pie('#chart-2', {
				series: [60, 40] }
				, {	donut: true,
				  	donutWidth: 60,
				  	startAngle: 270,
				 	total: 200,
				  	showLabel: true });
			new Chartist.Pie('#chart-3', {
				series: [30, 70] }
				, {	donut: true,
				  	donutWidth: 60,
				  	startAngle: 270,
				 	total: 200,
				  	showLabel: true });
			new Chartist.Pie('#chart-4', {
				series: [70, 30] }
				, {	donut: true,
				  	donutWidth: 60,
				  	startAngle: 270,
				 	total: 200,
				  	showLabel: true });
			new Chartist.Line('#chart-5', {
				  labels: [1, 2, 3, 4, 5, 6, 7, 8],
				  series: [
				    [5, 9, 7, 8, 5, 3, 5, 4]
				  ]
				}, {
				  low: 0,
				  showArea: true });
			
			$('#datetimepicker4').datetimepicker({defaultDate: "11/1/2013"});
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
						<div class="panel panel-primary">
							<div class="panel-heading">
								<div class="row">
									<div class="col-xs-3">
										<i class="fa fa-comments fa-5x"></i>
									</div>
									<div class="col-xs-9 text-right">
										<div class="huge">26</div>
										<div>New Comments!</div>
									</div>
								</div>
							</div>							
							<div class="panel-footer">
								<a href="#">
								<span class="pull-left">View Details</span>
								<span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
								</a>
								<div class="clearfix"></div>
							</div>
						</div>
					</div>                    
				</div>
				<div class="row">
					<div class="col-md-3">
						<div id="chart-1" class="ct-chart"></div>
					</div>
					<div class="col-md-3">
						<div id="chart-2" class="ct-chart"></div>
					</div>
					<div class="col-md-3">
						<div id="chart-3" class="ct-chart"></div>
					</div>
					<div class="col-md-3">
						<div id="chart-4" class="ct-chart"></div>
					</div>
				</div>
				<div class="row">
					<div class="col-md-8">
						<div id="chart-5" class="ct-chart ct-square"></div>
					</div>
					<div class="col-md-4">
						<div class="panel panel-default">
							<ul class="list-group">
								<li class="list-group-item">
									<input type='text' class="form-control" id='datetimepicker4'/>
								</li>
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
			</div>
		</div>
	</div>
</body>
</html>
