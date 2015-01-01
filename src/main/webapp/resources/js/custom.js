$.fn.sortSelect = function() {	    
	var options = $("#" + this.attr('id') + ' option');

	options.sort(function(a, b) { 
		a = a.value;
		b = b.value;
		return a - b;
	});
    	
    $(this).html(options);
};

function jsAlert(message) {
	$('#jsAlerts').empty().append('<div class="alert alert-danger" role="alert">' + message + '</div>');
};

function searchTopMenu() {
	var ctxpath = window.location.pathname.substring(0, window.location.pathname.indexOf("/",2));
	window.location.href(ctxpath + "/search/query/" + document.getElementById("searchTopMenuQuery").value);
}