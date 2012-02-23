(function() {
	var jnebula = {
			
	};
	window.jnebula = window.$$ = jnebula;

	$.address.init();
	$.address.autoUpdate(false);
	$.address.strict(false);
	$.address.change(function(event) {
		var url = $.address.value();
		if (url == "") {
			return;
		}
		$.get(url, function(data) {
			var ws = $("#main-section").html(data);
			$$.renderTable(ws);
			$$.renderPopup(ws);
		});
	});

})();


(function($, $$, document) {
	
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
		
		navigate : function(url, args) {
			
			$.address.value(url);
			$.address.update();

			$.get(url, function(data) {

				var ws = $("#main-section").html(data);
				
				$$.renderTable(ws);
				$$.renderPopup(ws);
				$$.renderTemplate(ws, args);
				$$.renderValidator(ws);
				$$.renderSubmit(ws);
				
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

						$$.renderTable(el);
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
					$.each($(el).find("input, select, textarea, span[name]").not("input[name='password'], input[name='password_confirm'], :submit, :button, :reset, :image"), function(i, e) {
						
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

				var url = this.id;									// 取得数据请求的URL
				var pkName = $("tbody td.primary", this).html();	// 取得主键的字段名
				
				var columns = new Array();
				var callbacks = new Array();

				$("tbody td", this).each(function(index) {
					if ($(this).hasClass("actions")) {
						columns[index] = {
							sClass : $(this).attr("class"),
							mDataProp : null,
							bSortable : false
						};
						if (/.*edit.*/.test($(this).html())) {
							callbacks[callbacks.length] = function(nRow, aData, iDisplayIndex, iDisplayIndexFull) {
								var td = $("td:eq(" + index + ")", nRow);
								var ownUrl = url + aData[pkName] + "/";
								var updateUrl = $$.rebase("html/" + url + "editable.html");
								var a = $(document.createElement("a"));
								a.attr("href", ownUrl);
								a.addClass("nyroModal");
								a.attr("rev", "modal");
								a.html("<img src='img/page_save.png'/>");
								a.click(function() {
									$$.navigate(updateUrl, { url : ownUrl });
									return false;
								});
								td.append(a);
							};
						}
						if (/.*delete.*/.test($(this).html())) {
							callbacks[callbacks.length] = function(nRow, aData, iDisplayIndex, iDisplayIndexFull) {
								var td = $("td:eq(" + index + ")", nRow);
								var ownUrl = url + aData[pkName] + "/";
								var removeUrl = $$.rebase("html/" + url + "removable.html");
								var a = $(document.createElement("a"));
								a.attr("href", ownUrl);
								a.addClass("nyroModal");
								a.attr("rev", "modal");
								a.html("<img src='img/delete.png'/>");
								a.click(function() {
									$$.navigate(removeUrl, { url : ownUrl });
									return false;
								});
								td.append(a);
							};
						}
					} else if ($(this).is("td[refer]")) {
						columns[index] = {
							sClass : $(this).attr("class"),
							mDataProp : this.innerHTML
						};
						var refer = $(this).attr("refer");
						var referby = $(this).attr("referby");
						callbacks[callbacks.length] = function(nRow, aData, iDisplayIndex, iDisplayIndexFull) {
							var td = $("td:eq(" + index + ")", nRow);
							var ownUrl = refer + $$._getData(aData, referby) + "" + "/";
							var referUrl = $$.rebase("html/" + refer + "view.html");
							var a = $(document.createElement("a"));
							a.attr("href", ownUrl);
							a.html(td.html());
							a.click(function() {
								$$.navigate(referUrl, { url : ownUrl });
								return false;
							});
							td.empty();
							td.append(a);
						};
					} else if ($(this).is("td[refers]")) {
						columns[index] = {
							sClass : $(this).attr("class"),
							mDataProp : null,
							bSortable : false
						};
						var refers = $(this).attr("refers");
						var referby = $(this).attr("referby");
						var actionUrl = refers;
						if (refers.indexOf("!") > 0) {
							actionUrl = refers.substring(0, refers.indexOf("!")) + "/";
						}
						var method = refers.substring(refers.indexOf("!"));
						callbacks[callbacks.length] = function(nRow, aData, iDisplayIndex, iDisplayIndexFull) {
							var td = $("td:eq(" + index + ")", nRow);
							var ownUrl = actionUrl + $$._getData(aData, referby)  + method;
							var referUrl = $$.rebase("html/" + actionUrl + "list.html");
							var a = $(document.createElement("a"));
							a.attr("href", ownUrl);
							a.html("+");
							a.click(function() {
								$$.navigate(referUrl, { url : ownUrl });
								return false;
							});
							td.empty();
							td.append(a);
						};
					} else {
						columns[index] = {
							sClass : $(this).attr("class"),
							mDataProp : this.innerHTML
						};
					}
				});
				
				var oTable = $(this).dataTable({
					sAjaxSource : $$.rebase(url),
					aoColumns : columns,
					bServerSide : true,
					sAjaxDataProp : function(data) {
						return data;
					},
					fnRowCallback : function(nRow, aData, iDisplayIndex, iDisplayIndexFull) {
						$.each(callbacks, function(i, f) {
							f(nRow, aData, iDisplayIndex, iDisplayIndexFull);
						});
						return nRow;
					}
				});
				
				$(this).data("oTable", oTable);

			});
		},
		
		_getData : function(data, name) {
			var result = data;
			var ns = name.split(".");
			for (var i = 0; i < ns.length; i++) {
				result = result[ns[i]];
			}
			return result;
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
			
			if (validator == undefined)
				return;
			
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

})(jQuery, jnebula, document);
