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
			new Chartist.Line('#line-chart', 
					{
						labels: ['12/3', '13/3', '14/3', '15/3', '16/3', '17/3', '18/3', '19/3', '20/3', '21/3'],
				  		series: [
				  		         [12, 9, 7, 8, 5, 2, 1, 3.5, 7, 3],
				  		         [2, 1, 3.5, 7, 3, 1, 3, 4, 5, 6],
				  		         [1, 3, 4, 5, 6, 2, 1, 3.5, 7, 3]
				  		         ]}, 
					{
						fullWidth: true,
						height: '300px',
						chartPadding: {
							right: 40
						}
			});			

			var data = { series: [5, 3, 4] }; 
			var sum = function(a, b) { return a + b };

			new Chartist.Pie('#pie-chart', 
					data ,
					{
						labelInterpolationFnc: function(value) {
					    return Math.round(value / data.series.reduce(sum) * 100) + '%';
					}
			});
					
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
									<div class="col-md-6">
										<img src="${ pageContext.request.contextPath }/resources/images/palm-oil.jpg" class="img-responsive img-circle"/>
									</div>
									<div class="col-md-6 text-right">
										<div class="huge">9800<span class="small">/Kg</span></div>
										<div class="tiny">17 March 2015</div>
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
					<div class="col-md-3">
						<div class="panel panel-primary">
							<div class="panel-heading">
								<div class="row">
									<div class="col-md-6">
										<img src="${ pageContext.request.contextPath }/resources/images/soybeans.jpg" class="img-responsive img-circle"/>
									</div>
									<div class="col-md-6 text-right">
										<div class="huge">7100<span class="small">/Kg</span></div>
										<div class="tiny">17 March 2015</div>
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
					<div class="col-md-3">
						<div class="panel panel-primary">
							<div class="panel-heading">
								<div class="row">
									<div class="col-md-6">
										<img src="${ pageContext.request.contextPath }/resources/images/sugar.jpg" class="img-responsive img-circle"/>
									</div>
									<div class="col-md-6 text-right">
										<div class="huge">8900<span class="small">/Kg</span></div>
										<div class="tiny">17 March 2015</div>
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
					<div class="col-md-3">
						<div class="panel panel-primary">
							<div class="panel-heading">
								<div class="row">
									<div class="col-md-6">
										<img src="${ pageContext.request.contextPath }/resources/images/wheat.jpg" class="img-responsive img-circle"/>
									</div>
									<div class="col-md-6 text-right">
										<div class="huge">2600<span class="small">/Kg</span></div>
										<div class="tiny">17 March 2015</div>
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
					<div class="col-md-9">
						<div id="line-chart" class="ct-chart"></div>
					</div>
					<div class="col-md-3">
						<div id="pie-chart" class="ct-chart"></div>
					</div>
				</div>
				<div class="row">
					<div class="col-md-8">
						<div class="panel panel-default">
							<div class="panel-heading">
								<div class="panel-title">
									&nbsp;
								</div>
							</div>
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
								&nbsp;
							</div>
						</div>
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
			</div>
		</div>
	</div>
</body>
</html>
