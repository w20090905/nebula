(function() {
	var jnebula = {
			
	};
	window.jnebula = window.$$ = jnebula;
})();

(function($, $$) {
	
	$.extend($$, {
		
		setup : function() {

			$('nav.menu ul').supersubs({
				minWidth : 12,
				maxWidth : 27,
				extraWidth : 1
			}).superfish();
	
			$("*[data-href]").each(function() {
				$$.include(this, $(this).attr("data-href"));
			});
	
			$('nav.menu a[href]').live("click", function(e) {
				e.preventDefault();
				$("nav.menu ul").children(".current").removeClass("current");
				$(this).parent().addClass("current");
				$$.navigate($$.rebase($(this).attr("href")));
			});
		},
		
		rebase : function(href) {
			return "./" + href;
		},
		
		include : function(el, url) {
			$.get($$.rebase(url), function(data) {
				$(el).html(data);
			});
		},
		
		navigate : function(url) {
			$.get(url, function(data) {

				var ws = $("#main-section").html(data);
				
				$$.renderTable(ws);
				$$.renderPopup(ws);
				
			});
		},

		popUp : function(url, args) {
			$.nmManual(url, {
				sizes : {
					initW : 1200,
					initH : 1200,
					w : 1200,
					h : 1200,
					minW : 500,
					minH : 700
				},
				callbacks: {
					beforeShowCont: function(e) {
						
						var el = e.elts.cont[0];

						$$.renderTemplate(el, args);
						$$.renderValidator(el);
						$$.renderSubmit(el);
						
					}
				}
			});

		},
		
		renderTemplate : function(el, args) {

			if (args == undefined || args["url"] == undefined) {
				return;
			}

			$("form", el).attr("action", args["url"]);
			
			$.ajax({
				type : "GET",
				url : args["url"],
				dataType : "json",
				async : false,
				success : function(data) {
					$.each($(el).find("input, select, textarea").not("input[name='password'], input[name='password_confirm'], :submit, :button, :reset, :image"), function(i, e) {
						
						var v = data[e.name];
						
						if (v == undefined) {
							return true;
						}
						
						if ($(e).is(":radio,:checkbox")) {
							e.checked = e.value == v ? true : false;
						} else {
							e.value = v;
						}
						
					});
				}
			});
			
		},
		
		renderPopup : function(el) {
			$("a.nyroModal", el).click(function(e) {
				e.preventDefault();
				$$.popUp($$.rebase($(this).attr("href")));
			});
		},
 		
		renderSubmit : function(el) {
			$.each($("form", el), function(i, form) {
				$(":submit", form).click(function(e) {
					e.preventDefault();
					if (!$(form).valid()) {
						return false;
					}
					$.ajax({
						type : form.method,
						url : form.action,
			        	data : $.each($(form).serializeArray(), function(i, f) {
							if (f.name == "password" || f.name == "password_confirm") {
				        		f.value = $.md5(f.value);
							}
						}),
						dataType : "json",
						async : false,
						success : function(data) {
							$.nmTop().close();
							// ++++++++++++++++++++++++++++++++++++++++++++
							$("table.toplevel").each(function(i, e) {
								$(e).data("oTable").fnDraw();
							});
							//---------------------------------------------
							return false;
						},
						error : function(XMLHttpRequest, textStatus, errorThrown) {
							alert("错误！");
						}
					});
				});
			});
		},
		
		renderTable : function(el) {

			$("table.toplevel", el).each(function() {
				
				var columns = new Array();

				var url = this.id;									// 取得数据请求的URL
				var pkName = $("tbody td.primary", this).html();	// 取得主键的字段名
				var updateUrl = $$.rebase("html/" + url + "editable.html");

				$("tbody td", this).each(function(index) {
					if ($(this).hasClass("actions")) {
						columns[index] = {
							"mDataProp" : "",
							"sClass" : this.className,				// TODO 有问题，对于class “a b”,只能取得“a”
							"bSortable" : false,
							"fnRender" : function(obj) {
								var ownUrl = url + obj.aData[pkName] + "/";
								return '<a href="'
										+ ownUrl
										+ '" class="nyroModal" rev="modal" onclick="javascript:$$.popUp(\''
										+ updateUrl
										+ '\',{url:\'' + ownUrl + '\'}); return false;">'
										+ "Edit"
										+ '</a>';
							}
						};
					} else {
						columns[index] = {
							"mDataProp" : this.innerHTML,
							"sClass" : this.className				// TODO 有问题，对于class “a b”,只能取得“a”
						};
					}
				});

				var oTable = $(this).dataTable({
					sAjaxSource : $$.rebase(url),
					aoColumns : columns,
					sAjaxDataProp : function(data) {
						return data;
					},
					bServerSide : true
				});
				
				$(this).data("oTable", oTable);
				
			});
		},
		
		renderValidator : function(el) {
			
			var validator = $("form", el).validate({
				onkeyup : false,
				rules : { },
				messages : { },
				errorPlacement : function(error, element) {
					error.insertAfter(element.parent().find('label:first'));
				}
			});
			
			validator.onsubmit = false;
			
			$.each($(el).find("input, select, textarea").not(":submit, :button, :reset, :image, [disabled]"), function(i, f) {

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
							url : jf.attr("exists"),
							type : "GET",
							data : {
								username : function() {
									return jf.val();
								}
							},
							dataFilter : function(data, type) {
								if ("true" == data) {
									return "false";
								} else {
									return "true";
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
		
	});

})(jQuery, jnebula);
