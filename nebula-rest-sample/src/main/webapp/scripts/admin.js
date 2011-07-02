if (!Admin) var Admin = {}

Admin.inclue = function(url) {
	$.ajax({
		type : "GET",
		url : url,
		async : false,
		success : function(data) {
			$("body").append(data);
		}
	});
}

Admin.replace = function(el, url) {
	$.get(url, function(data) {
		$(el).html(data);
	});
}

Admin.setup = function () {
}
