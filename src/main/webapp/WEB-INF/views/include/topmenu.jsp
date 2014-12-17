<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

		<nav class="navbar navbar-default navbar-fixed-top" role="navigation">
  			<div class="container-fluid">
  				<div class="navbar-header">
    				<a class="navbar-brand" href="${pageContext.request.contextPath}/dashboard.html">
						<i class="fa fa-btc"></i>
      				</a>
      			</div>
      			<p class="navbar-text"><strong>&nbsp;</strong></p>
				<form class="navbar-form navbar-right" role="search">
	  				<div class="form-group">
	    				<input type="text" class="form-control" placeholder="Search">
	  				</div>
	  				<button type="submit" class="btn btn-default"><span class="fa fa-search fa-fw"></span>&nbsp;Search</button>
				</form>
				<div class="btn-toolbar">
					<button type="button" class="btn btn-danger navbar-btn navbar-right">&nbsp;<span class="fa fa-life-buoy fa-spin fa-fw"></span>&nbsp;</button>
					<button type="button" class="btn btn-default navbar-btn navbar-right"><span class="fa fa-envelope fa-fw"></span>&nbsp;Messages</button>
				</div>
  			</div>
		</nav>