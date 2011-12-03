if (!Admin) var Admin = {}

//scrollToTop() - scroll window to the top
Admin.scrollToTop = function(e) {
	$(e).hide().removeAttr("href");
	if ($(window).scrollTop() != "0") {
		$(e).fadeIn("slow");
	}
	var scrollDiv = $(e);
	$(window).scroll(function() {
		if ($(window).scrollTop() == "0") {
			$(scrollDiv).fadeOut("slow");
		} else {
			$(scrollDiv).fadeIn("slow");
		}
	});
	$(e).click(function() {
		$("html, body").animate({
			scrollTop : 0
		}, "slow");
	})
}

//setup() - Admin init and setup
Admin.setup = function() {
	// Open an external link in a new window
	$('a[href^="http://"]').filter(function() {
		return this.hostname && this.hostname !== location.hostname;
	}).attr('target', '_blank');

	// build an animated footer
	$('#animated').each(function() {
		$(this).hover(function() {
			$(this).stop().animate({
				opacity : 0.8
			}, 400);
		}, function() {
			$(this).stop().animate({
				opacity : 0.0
			}, 200);
		});
	});

	// scroll to top on request
	if ($("a#totop").length)
		Admin.scrollToTop("a#totop");

	// setup content boxes
	if ($(".content-box").length) {
		$(".content-box header").css({
			"cursor" : "s-resize"
		});
		// Give the header in content-box a different cursor	
		$(".content-box header").click(function() {
			$(this).parent().find('section').toggle(); // Toggle the content
			$(this).parent().toggleClass("content-box-closed"); // Toggle the class "content-box-closed" on the content
		});
	}

	// custom tooltips to replace the default browser tooltips for <a title=""> <div title=""> and <span title="">
	$("a[title], div[title], span[title]").tipTip();

	$("*[data-href]").each(function() {
		Admin.include($(this), $(this).attr("data-href"));
	});

	// build animated dropdown navigation
	$('nav.menu ul').supersubs({
		minWidth : 12, // minimum width of sub-menus in em units 
		maxWidth : 27, // maximum width of sub-menus in em units cur
		extraWidth : 1
	// extra width can ensure lines don't sometimes turn over 
	// due to slight rounding differences and font-family 
	}).superfish();

	$('nav.menu a[href]').live("click", function(e) {
		e.preventDefault();
		$("nav.menu ul").children(".current").removeClass("current");
		$(this).parent().addClass("current");

		Admin.navigate(Admin.rebase($(this).attr("href")));
	});

};

//progress() - animate a progress bar "el" to the value "val"
Admin.progress = function(el, val, max) {
	var duration = 400;
	var span = $(el).find("span");
	var b = $(el).find("b");
	var w = Math.round((val / max) * 100);
	$(b).fadeOut('fast');
	$(span).animate({
		width : w + '%'
	}, duration, function() {
		$(el).attr("value", val);
		$(b).text(w + '%').fadeIn('fast');
	});
}

//expandableRows() - expandable table rows
Admin.expandableRows = function() {
	var titles_total = $('td.title').length;
	if (titles_total) { /* setting z-index for IE7 */
		$('td.title').each(function(i, e) {
			$(e).children('div').css('z-index', String(titles_total - i));
		});

		$('td.title').find('a').click(function() {
			// hide previously opened containers
			$('.opened').hide();
			// remove highlighted class from rows
			$('td.highlighted').removeClass('highlighted');

			// locate the row we clicked onto
			var tr = $(this).parents("tr");
			var div = $(this).parent().find('.listingDetails');

			if (!$(div).hasClass('opened')) {
				$(div).addClass('opened').width($(tr).width() - 2).show();
				$(tr).find('td').addClass('highlighted');
			} else {
				$(div).removeClass('opened');
				$(tr).find('td').removeClass('highlighted');
			}
			return false;
		});
	}
}

Admin.include = function(el, url) {
	$.get(Admin.rebase(url), function(data) {
		$(el).html(data);
	});
};

Admin.navigate = function(url) {
	$.get(url, function(data) {

		var panel = $("#main-section").html(data);

		$("table.toplevel", panel).each(function() {
			var columns = new Array();
			$("td", $(this)).each(function(index, e) {
				if ($(this).hasClass("actions")) {
					columns[index] = {
						"mDataProp" : "",
						"sClass" : $(this).css,
						"fnRender" : function(obj) {
							return e.innerHTML;
						}
					};
				} else {
					columns[index] = {
						"mDataProp" : e.innerHTML,
						"sClass" : $(this).css
					};
				}
			});

			$(this).dataTable({
				sAjaxSource : Admin.rebase($(this).attr("id")),
				aoColumns : columns,
				sAjaxDataProp : "" // Could be delete, default is "aaData"
			});
		});

		$("a.nyroModal", panel).click(function(e) {
			e.preventDefault();
			$.nmManual(Admin.rebase($(this).attr("href")), {
				sizes : {
					initW : 1200,
					initH : 1200,
					w : 1200,
					h : 1200,
					minW : 500,
					minH : 700
				},
				callbacks: {
					beforeShowCont: function() {
						var validator = $("form").validate({
							onkeyup : false,
							rules : { },
							messages : { },
							errorPlacement : function(error, element) {
								error.insertAfter(element.parent().find('label:first'));
							},
							submitHandler: function(form) {
								e.preventDefault();
								$.ajax({
									type : form.method,
									url : form.action,
						        	data : $.each($(form).serializeArray(), function(i, f) {
										if (f.name == "password" || f.name == "password_confirm")
							        		f.value = $.md5(f.value);
									}),
									dataType : "json",
									async : false,
									success : function(data) {
										//alert("成功");
										$.nmTop().close();
										//oTable.fnDraw();
									},
									error : function(XMLHttpRequest, textStatus, errorThrown) {
										alert("错误！");
									}
								});
							}
						});
						$.each($("form").find("input, select, textarea").not(":submit, :button, :reset, :image, [disabled]"), function(i, f) {

							var jf = $(f);
							
							if (f.required) {
								jf.rules("add", {
									required : true,
									messages : {
										required : f.title + "不能为空！"
									}
								});
							}

							if (jf.is("[minlength]")) {
								jf.rules("add", {
									minlength : jf.attr("minlength"),
									messages : {
										minlength : f.title + "不能少于 {0} 位"
									}
								});
							}

							if (jf.is("[exists]")) {
								jf.rules("add", {
									remote : {
										url: jf.attr("exists"),
										type: "GET",
										data: {
											username: function() {
												return jf.val();
											}
										},
										dataFilter: function(data, type) {
											if ("true" == data) {
												return "false";
											} else {
												return"true";
											}
										}
									},
									messages : {
										remote : f.title + "已存在！"
									}
								});
							}

							if (jf.is("input[type='email']")) {
								jf.rules("add", {
									email : true,
									messages : {
										email : "输入一个有效的邮箱地址！"
									}
								});
							}

							if (jf.is("[equalto]")) {
								jf.rules("add", {
									equalTo : jf.attr("equalto"),
									messages : {
										equalTo : f.title + "与" + $(jf.attr("equalto")).attr("title") + "不一致！"
									}
								});
							}
							
						});
					}
				}
			});
			
		});

	});
};

Admin.rebase = function(href) {
	/*
	if (href.substring(0, "/".length) == "/") {

	} else if (href.substring(0, "http".length) == "http") {

	} else if (href.substring(0, "./".length) == "./") {
		if (href.substring(0, "./templates/administry/".length) != "./templates/administry/") {
			href = "./templates/administry/" + href.substring("./".length);
		}
	} else {
		href = "./templates/administry/" + href;
	}
	return href;
	*/
	return "./" + href;
};
