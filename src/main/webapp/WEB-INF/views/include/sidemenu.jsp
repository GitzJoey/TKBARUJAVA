<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

				<div id="userprofile">
					<table class="table borderless">
						<tr>
							<td>
								<img class="img-responsive" alt="user" src="${pageContext.request.contextPath}/resources/images/def-user.png">
							</td>
							<td>
								Admin
								<br/>
								Role: Admin
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<div class="btn-toolbar">
									<a href="${pageContext.request.contextPath}/logout.html" class="btn btn-primary btn-xs pull-right"><span class="fa fa-child">&nbsp;Logout</span></a>
									<a href="${pageContext.request.contextPath}/user/list.html" class="btn btn-primary btn-xs pull-right"><span class="fa fa-user">&nbsp;Profile</span></a>
								</div>
							</td>
						</tr>
					</table>
				</div>
				<div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
					<div class="panel panel-default">
				    	<div class="panel-heading" role="tab" id="headingCustomer">
				      		<h4 class="panel-title no-underline">
				        		<a class="collapsed" data-toggle="collapse" data-parent="#accordion" href="#collapseCustomer" aria-expanded="false" aria-controls="collapseCustomer">
				          			<span class="fa fa-smile-o fa-fw"></span>&nbsp;Customer
				        		</a>
				      		</h4>
				    	</div>
				    	<div id="collapseCustomer" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingCustomer">
				      		<div class="panel-body no-padding">
								<ul class="nav nav-pills nav-stacked" role="tablist">
									<li role="presentation" class="active"><a href="#"><span class="fa fa-plus fa-fw"></span>&nbsp;Add Customer</a></li>
								  	<li role="presentation"><a href="#"><span class="fa fa-pencil fa-fw"></span>&nbsp;Edit Customer</a></li>
								  	<li role="presentation"><a href="#">Search Customer</a></li>
								</ul>
				      		</div>
				    	</div>
				  	</div>
					<div class="panel panel-default">
				    	<div class="panel-heading" role="tab" id="headingSupplier">
				      		<h4 class="panel-title no-underline">
				        		<a class="collapsed" data-toggle="collapse" data-parent="#accordion" href="#collapseSupplier" aria-expanded="false" aria-controls="collapseSupplier">
				          			<span class="fa fa-building-o fa-fw"></span>&nbsp;Supplier
				        		</a>
				      		</h4>
				    	</div>
				    	<div id="collapseSupplier" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingSupplier">
				      		<div class="panel-body no-padding">
								<ul class="nav nav-pills nav-stacked" role="tablist">
									<li role="presentation" class="active"><a href="#"><span class="fa fa-plus fa-fw"></span>&nbsp;Add Supplier</a></li>
								  	<li role="presentation"><a href="#"><span class="fa fa-pencil fa-fw"></span>&nbsp;Edit Supplier</a></li>
								  	<li role="presentation"><a href="#">Search Supplier</a></li>
								</ul>
				      		</div>
				    	</div>
				  	</div>
					<div class="panel panel-default">
				    	<div class="panel-heading" role="tab" id="headingProduct">
				      		<h4 class="panel-title no-underline">
				        		<a class="collapsed" data-toggle="collapse" data-parent="#accordion" href="#collapseProduct" aria-expanded="false" aria-controls="collapseProduct">
				          			<span class="fa fa-cubes fa-fw"></span>&nbsp;Product
				        		</a>
				      		</h4>
				    	</div>
				    	<div id="collapseProduct" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingProduct">
				      		<div class="panel-body no-padding">
								<ul class="nav nav-pills nav-stacked" role="tablist">
									<li role="presentation" class="active"><a href="#"><span class="fa fa-plus fa-fw"></span>&nbsp;Add Product</a></li>
								  	<li role="presentation"><a href="#"><span class="fa fa-pencil fa-fw"></span>&nbsp;Edit Product</a></li>
								  	<li role="presentation"><a href="#">Search Product</a></li>
								</ul>
				      		</div>
				    	</div>
				  	</div>
					<div class="panel panel-default">
				    	<div class="panel-heading" role="tab" id="headingPurchaseOrder">
				      		<h4 class="panel-title no-underline">
				        		<a class="collapsed" data-toggle="collapse" data-parent="#accordion" href="#collapsePurchaseOrder" aria-expanded="false" aria-controls="collapsePurchaseOrder">
				          			<span class="fa fa-truck fa-fw"></span>&nbsp;Purchase Order
				        		</a>
				      		</h4>
				    	</div>
				    	<div id="collapsePurchaseOrder" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingPurchaseOrder">
				      		<div class="panel-body no-padding">
				        		.
				      		</div>
				    	</div>
				  	</div>
					<div class="panel panel-default">
				    	<div class="panel-heading" role="tab" id="headingSalesOrder">
				      		<h4 class="panel-title no-underline">
				        		<a class="collapsed" data-toggle="collapse" data-parent="#accordion" href="#collapseSalesOrder" aria-expanded="false" aria-controls="collapseSalesOrder">
				          			<span class="fa fa-dollar fa-fw"></span>&nbsp;Sales Order
				        		</a>
				      		</h4>
				    	</div>
				    	<div id="collapseSalesOrder" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingSalesOrder">
				      		<div class="panel-body no-padding">
								<ul class="nav nav-pills nav-stacked" role="tablist">
									<li role="presentation" class="active"><a href="#"><span class="fa fa-dollar fa-fw"></span>&nbsp;Set Today Price</a></li>
								  	<li role="presentation"><a href="#"><span class="fa fa-pencil fa-fw"></span>&nbsp;Sales</a></li>
								</ul>
				      		</div>
				    	</div>
				  	</div>
				  	<div class="panel panel-default">
				    	<div class="panel-heading" role="tab" id="headingMonitoring">
				      		<h4 class="panel-title no-underline">
				        		<a class="collapsed" data-toggle="collapse" data-parent="#accordion" href="#collapseMonitoring" aria-expanded="false" aria-controls="collapseMonitoring">
				          			<span class="fa fa-eye fa-fw"></span>&nbsp;Monitoring
				        		</a>
				      		</h4>
				    	</div>
				   		<div id="collapseMonitoring" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingMonitoring">
				      		<div class="panel-body no-padding">
				        		.
				      		</div>
				    	</div>
				  	</div>
				  	<div class="panel panel-default">
				    	<div class="panel-heading" role="tab" id="headingReport">
				      		<h4 class="panel-title no-underline">
				        		<a class="collapsed" data-toggle="collapse" data-parent="#accordion" href="#collapseReport" aria-expanded="false" aria-controls="collapseReport">
				          			<span class="fa fa-bar-chart-o fa-fw"></span>&nbsp;Report
				        		</a>
				      		</h4>
				    	</div>
				   		<div id="collapseReport" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingReport">
				      		<div class="panel-body no-padding">
				        		<ul class="nav nav-pills nav-stacked" role="tablist">
									<li role="presentation" class="active"><a href="#">Report 1</a></li>
								  	<li role="presentation"><a href="#">Report 2</a></li>
								  	<li role="presentation"><a href="#">Report 3</a></li>
								  	<li role="presentation" class="nav-divider"></li>
								  	<li role="presentation"><a href="#">Report 2</a></li>
								  	<li role="presentation"><a href="#">Report 3</a></li>
								</ul>
				      		</div>
				    	</div>
				  	</div>
				  	<div class="panel panel-default">
				    	<div class="panel-heading" role="tab" id="headingAdmin">
					      	<h4 class="panel-title no-underline">
					        	<a class="collapsed" data-toggle="collapse" data-parent="#accordion" href="#collapseAdmin" aria-expanded="false" aria-controls="collapseAdmin">
					          		<span class="glyphicon glyphicon-cog"></span>&nbsp;Admin Menu
					        	</a>
					      	</h4>
				 		</div>
				    	<div id="collapseAdmin" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingAdmin">
				      		<div class="panel-body no-padding">
				        		<ul class="nav nav-pills nav-stacked" role="tablist">
				        			<li role="presentation"><a href="${pageContext.request.contextPath}/admin/user.html">User</a></li>
				        			<li role="presentation"><a href="${pageContext.request.contextPath}/admin/role.html">Role</a></li>
				        			<li role="presentation"><a href="${pageContext.request.contextPath}/admin/function.html">Function</a></li>
				        			<li role="presentation" class="nav-divider"></li>
				        			<li role="presentation"><a href="#">Lookup</a></li>
				        		</ul>
				      		</div>
				    	</div>
				  	</div>
				</div>
