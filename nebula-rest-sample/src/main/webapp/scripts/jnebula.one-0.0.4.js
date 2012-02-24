(function() {
	var jnebula = {
			
	};
	window.jnebula = window.$$ = jnebula;

})();


(function($, $$, document) {
	
	$.extend($$, {

		rebase : function(href) {
			return "./" + href;
		},
		
		include : function(el, url) {
			$.get($$.rebase(url), function(data) {
				$(el).html(data);
			});
		},
		
		navigate : function(url, settings) {

			$.get(url, function(data) {

				var ws = $("#main-section").html(data);

			});
		},

		popUp : function(url, settings) {
			$.nmManual(url, {
				sizes : {
					initH : 1200
				},
				callbacks: {
					beforeShowCont: function(e) {
						
						var el = e.elts.cont[0];

						
					}
				}
			});
		},

		renderTemplate : function(el, settings) {

			if (settings == undefined || settings["dateUrl"] == undefined) {
				return;
			}

			//$("form", el).attr("action", settings["dateUrl"]);

			$.ajax({
				type : "GET",
				url : settings["dateUrl"],
				dataType : "json",
				async : false,
				success : function(data) {
					$.each($(el).find("input, select, textarea, span[name]").not("input[type='password'], :submit, :button, :reset, :image"), function(i, e) {
						
						var v = data[$(e).attr("name")];
						
						if (v == undefined) {
							return true;
						}
						
						if ($(e).is(":radio,:checkbox")) {
							e.checked = e.value == v ? true : false;
						} else {
							e.value = v;
						}

						if ($(e).is("span")) {
							e.innerHTML = v;
						}
						
					});
				}
			});

		}

	});

})(jQuery, jnebula, document);
