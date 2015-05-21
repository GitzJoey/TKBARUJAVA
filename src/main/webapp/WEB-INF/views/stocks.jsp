<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<jsp:include page="/WEB-INF/views/include/headtag.jsp"></jsp:include>
	<script>
		$(document).ready(function() {
			var ctxpath = "${ pageContext.request.contextPath }";
			
			var dt = $('#stockListTable').DataTable();
			
			var detailRows = [];
			 
		    $('#stockListTable tbody').on('click', 'tr td.detail-control', function() {
		        var tr = $(this).closest('tr');
		        var row = dt.row(tr);
		        var idx = $.inArray(tr.attr('id'), detailRows);
		 
		        if (row.child.isShown()) {
		            tr.removeClass('details');
		            row.child.hide();
		 
		            detailRows.splice(idx, 1);
		        }
		        else {
		            tr.addClass('details');
		            row.child(format(row.data())).show();
		 
		            if (idx === -1) {
		                detailRows.push(tr.attr('id'));
		            }
		        }
		    });
		    
		    dt.on('draw', function() {
		        $.each(detailRows, function(i, id) {
		            $('#' + id + ' td.detail-control').trigger('click');
		        });
		    });
		    
		    function format(d) {
		        return '<p>' + 'Details' + '</p>';
		    }
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
				<c:if test="${ERRORPAGE == 'ERRORPAGE_SHOW'}">
	    			<div class="alert alert-danger alert-dismissible collapse" role="alert">
	  					<button type="button" class="close" data-dismiss="alert">
	  						<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
	  					</button>
	  					<h4><strong>Warning!</strong></h4>
	  					<br>
	  					${errorMessageText}
					</div>
				</c:if>
				
				<div id="jsAlerts"></div>

				<h1>
					<span class="fa fa-eye fa-fw"></span>&nbsp;Monitoring
				</h1>

				<c:choose>
					<c:when test="${PAGEMODE == 'PAGEMODE_PAGELOAD' || PAGEMODE == 'PAGEMODE_LIST'}">
						<div class="panel panel-default">
							<div class="panel-heading">
								<h1 class="panel-title">
									<span class="fa fa-database fa-fw fa-2x"></span>Stocks
								</h1>
							</div>
							<div class="panel-body">
								<table id="stockListTable" class="table table-bordered table-hover display">
									<thead>
										<tr>
											<th width="50%">Product Name</th>
											<th width="20%">Inflow</th>
											<th width="20%">Outflow</th>
											<th width="10%">Detail</th>
										</tr>
									</thead>
									<tbody>
										<c:if test="${ not empty stocksList }">
											<c:forEach items="${ stocksList }" var="s">
												<tr>
													<td><c:out value="${ s.productLookup.productName }"/></td>
													<td></td>
													<td></td>
													<td class="center-align detail-control">
														<span class="fa fa-plus fa-fw cursor-pointer"></span>
													</td>
												</tr>
											</c:forEach>
										</c:if>
									</tbody>
								</table>											
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
