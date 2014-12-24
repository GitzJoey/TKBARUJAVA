$(document).ready(function() {
	var ctxpath = window.location.pathname.substring(0, window.location.pathname.indexOf("/",2));
	
	function jsAlert(message) { 
		$('#jsAlerts').empty().append('<div class="alert alert-danger" role="alert">' + message + '</div>'); 
	};
	
	$('#searchTopMenu').click(function() {
		window.location.href(ctxpath + "/search/query/" + $('#searchTopMenuQuery').val());
	});
});