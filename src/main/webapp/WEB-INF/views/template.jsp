<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
	<jsp:include page="/WEB-INF/views/include/headtag.jsp"></jsp:include>
	<script>
		$(document).ready(function() {
			var ctxpath = "${ pageContext.request.contextPath }";
		});
	</script>
	
	
	
</head>
<body  data-debug="true">

<div class="flypanels-container preload">

<div class="offcanvas flypanels-left">

  <div class="panelcontent" data-panel="treemenu">

    <nav class="flypanels-treemenu" role="navigation">

      <ul>

        <li class="haschildren">

          <div> <a href="#" class="link">Node 1</a> <a href="#" class="expand">2</a></div>

          <ul>

            <li>

              <div><a href="#" class="link">Node 1-1</a></div>

            </li>

            <li>

              <div><a href="#" class="link">Node 1-2</a></div>

            </li>

          </ul>

        </li>

        <li class="haschildren">

          <div class="link"> <a href="#" class="link">Node 2</a> <a href="#" class="expand">2</a></div>

          <ul>

            <li>

              <div><a href="#" class="link">Node 2-1</a></div>

            </li>

            <li>

              <div><a href="#" class="link">Node 2-2</a></div>

            </li>

          </ul>

        </li>

      </ul>

    </nav>

  </div>

</div>

</div>


<div class="flypanels-main">

  <div class="flypanels-topbar">

    <a class="flypanels-button-left icon-menu" data-panel="treemenu" href="#">aaaaaaa</a>

  </div>

  <div class="flypanels-content">

    Main content goes here

  </div>

</div>




</body>
</html>
