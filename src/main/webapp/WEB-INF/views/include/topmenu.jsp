<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

		<nav class="navbar navbar-default navbar-fixed-top" role="navigation">
  			<div class="container-fluid">
  				<div class="navbar-header">
    				<a class="navbar-brand" href="${pageContext.request.contextPath}/dashboard">
						<i class="fa fa-home fa-border"></i>
      				</a>
      			</div>
      			<p class="navbar-text"><strong>&nbsp;</strong></p>
				<form class="navbar-form navbar-right" role="search">
	  				<div class="form-group input-group-sm">
	    				<input type="text" class="form-control" placeholder="Search">
	  				</div>
	  				<button type="submit" class="btn btn-sm btn-default"><span class="fa fa-search fa-fw"></span>&nbsp;Search</button>
				</form>
  			</div>
		</nav>