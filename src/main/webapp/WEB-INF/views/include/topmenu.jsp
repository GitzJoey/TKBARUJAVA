<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

		<nav class="navbar navbar-default navbar-fixed-top navbar-inverse" role="navigation">
  			<div class="container-fluid">
  				<div class="navbar-header">
    				<a class="navbar-brand" href="${ pageContext.request.contextPath }/dashboard">
						<span class="fa fa-home fa-border"></span><span>&nbsp;&nbsp;&nbsp;</span><strong><c:out value="${ loginContext.userLogin.storeEntity.storeName }"/></strong>
      				</a>
	                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".sidebar-nav">
	                    <span class="sr-only">Toggle navigation</span>
	                    <span class="icon-bar"></span>
	                    <span class="icon-bar"></span>
	                    <span class="icon-bar"></span>
	                </button>
      			</div>
				<form class="navbar-form navbar-right hidden-xs hidden-sm" role="search">
	  				<div class="form-group">
	    				<input id="searchTopMenuQuery" type="text" class="form-control" placeholder="Search"/>
	  				</div>
	  				<a id="searchTopMenu" type="button" class="btn btn-default" href="javascript: searchTopMenu();"><span class="fa fa-search fa-fw"></span>&nbsp;<spring:message code="common.search_button" text="Search"/></a>
				</form>
				<ul class="nav navbar-nav pull-right hidden-xs hidden-sm">
					<li class="dropdown">
					 <a class="dropdown-toggle" data-toggle="dropdown" href="#"><span class="glyphicon glyphicon-globe"></span></a>
						<ul class="dropdown-menu">
							<li>
								<a href="${ requestScope['javax.servlet.forward.request_uri'] }?locale=en">
									English
								</a>
							</li>
							<li>
								<a href="${ requestScope['javax.servlet.forward.request_uri'] }?locale=in">
									Indonesia
								</a>
							</li>
						</ul>
					</li>
					<li>
						<a href="${ pageContext.request.contextPath }/static/index.html" target="_blank" title="Open website in new browser"><span class="fa fa-external-link fa-fw"></span></a>
					</li>
				</ul>
      			<p class="navbar-text pull-right hidden-xs hidden-sm">
					<span id="timeoutCount"></span>
      			</p>
  			</div>
		</nav>