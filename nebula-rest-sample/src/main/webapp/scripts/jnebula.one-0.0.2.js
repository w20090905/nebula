if (!jnebula)
	var jnebula = {};

/*
 * 构建网页的默认行为。
 */
jnebula.setup = function() {

	// 创建带有动画效果的下拉式菜单
	$('nav.menu ul').supersubs({
		minWidth : 12,
		maxWidth : 27,
		extraWidth : 1
	}).superfish();

	// 导入网页的各个部分（如：菜单、工作区、侧边栏等）
	$("*[data-href]").each(function() {
		jnebula.include(this, $(this).attr("data-href"));
	});

	// 添加菜单点击效果和动作
	$('nav.menu a[href]').live("click", function(e) {
		e.preventDefault();
		$("nav.menu ul").children(".current").removeClass("current");
		$(this).parent().addClass("current");
		jnebula.navigate(jnebula.rebase($(this).attr("href")));
	});

};

/*
 * 将指定元素的InnerHTML替换为给定URL的返回值。
 */
jnebula.include = function(el, url) {
	$.get(jnebula.rebase(url), function(data) {
		$(el).html(data);
	});
};

/*
 * 重新设置链接
 */
jnebula.rebase = function(href) {
	return "./" + href;
};

/*
 * 通过HTTP的doGet方法，取得请求的页面及数据，并将其按规则呈现于客户端， 同时在该页面中添加默认行为。
 */
jnebula.navigate = function(url) {
	$.get(url, function(data) {

		// 替换“工作区”为当前请求的返回值
		var ws = $("#main-section").html(data);	// “工作区”的ID必须为“main-section”
		
		jnebula.nyroModal(ws);
		jnebula.validate(ws);
		jnebula.dataTables(ws);
		
	});
};

/*
 * 通过HTTP的doGet方法，取得请求的页面及数据，并将其按规则呈现于客户端， 同时在该页面中添加默认行为。
 */
jnebula.popUp = function(url) {
	jnebula.nmManual(url, [ jnebula.validate ]);
};
/*
 * 在给定元素内部，处理复数条数据的请求、表现，并添加默认行为。
 */
jnebula.dataTables = function(el) {

	$("table.toplevel", el).each(function() {
		
		var columns = new Array();

		var url = this.id;									// 取得数据请求的URL
		var pkName = $("tbody td.primary", this).html();	// 取得主键的字段名
		var updateUrl = jnebula.rebase("./html/" + url + "editable.html");

		$("tbody td", this).each(function(index) {
			if ($(this).hasClass("actions")) {
				columns[index] = {
					"mDataProp" : "",
					"sClass" : this.className,				// TODO 有问题，对于class “a b”,只能取得“a”
					"fnRender" : function(obj) {
						return '<a href="' + url + obj.aData[pkName] + '" class="nyroModal" rev="modal" onclick="javascript: jnebula.popUp(\'' + updateUrl + '\'); return false;">' + "Edit" + '</a>';
					}
				};
			} else {
				columns[index] = {
					"mDataProp" : this.innerHTML,
					"sClass" : this.className				// TODO 有问题，对于class “a b”,只能取得“a”
				};
			}
		});

		$(this).dataTable({
			sAjaxSource : jnebula.rebase(url),
			aoColumns : columns,
			sAjaxDataProp : ""
		});
	});
};

/*
 * 处理窗口弹出式的链接。
 */
jnebula.nyroModal = function(panel, callback) {
	$("a.nyroModal", panel).click(function(e) {
		e.preventDefault();
		jnebula.popUp(jnebula.rebase($(this).attr("href")));
	});
};

/*
 * 弹出窗口页面。
 */
jnebula.nmManual = function(url, callbacks) {
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
								$.nmTop().close();
							},
							error : function(XMLHttpRequest, textStatus, errorThrown) {
								alert("错误！");
							}
						});
					}
				});

				$.each(callbacks, function(i, f) {
					f($("form"));
				});

			}
		}
	});
};

/*
 * 为给定表单添加校验行为。
 */
jnebula.validate = function(form) {
	$.each($(form).find("input, select, textarea").not(":submit, :button, :reset, :image, [disabled]"), function(i, f) {

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

};
