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
	window.location.href = ctxpath + "/search/query/" + document.getElementById("searchTopMenuQuery").value;
}

function getCookie(cname) {
    var name = cname + "=";
    var ca = document.cookie.split(';');
    for (var i = 0; i < ca.length; i++) {
        var c = ca[i];
        while (c.charAt(0) == ' ') c = c.substring(1);
        if (c.indexOf(name) == 0) return c.substring(name.length, c.length);
    }
    return "";
}