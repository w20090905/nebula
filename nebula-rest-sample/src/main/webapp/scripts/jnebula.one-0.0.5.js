(function() {
	var jnebula = {};
	window.jnebula = window.$$ = jnebula;
})();

(function($, $$) {

	$.extend($$, {
        
        setup : function() {
        	
        },

		rebase : function(href) {
			return href;
		},

		include : function(el, url) {
			$.get($$.rebase(url), function(data) {
				var e = $(el).html(data);
			});
		},
        
        navigate : function(templateUrl, dataUrl) {

            $.get(templateUrl, function(data) {
                var el = $("#main-section").html(data);
                $$.renderTemplate(el, dataUrl);
                $$.renderLink(el);
				$$.renderValidator(el);
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
        
        renderLink : function(el) {
            $("a[template]", el).each(function() {
                var ja = $(this);
                ja.click(function() {
                    $$.navigate(ja.attr("template"), $$.rebase(ja.attr("href")));
                    return false;
                });
            });
        },

        renderTemplate : function(el, url) {

            //$("form", el).attr("action", url);
            
            $.ajax({
                type : "GET",
                url : url,
                dataType : "json",
                async : false,
                success : function(data) {
                    $$.renderValue(el, data);
                    $$.renderTable(el, data);
                }
            });
            
        },
        
        renderValue : function(el, data) {
            $.each($(el).find("input, select, textarea, span[name]").not("input[type='password'], :submit, :button, :reset, :image"), function(i, e) {
                
                var v = $$.getData(data, $(e).attr("name"));
                if (v == undefined) {
                    return true;
                }
                
                if ($(e).is(":radio, :checkbox")) {
                    e.checked = e.value == v ? true : false;
                } else if ($(e).is("span")) {
                    e.innerHTML = v;
                } else {
                    e.value = v;
                }
                
            });
        },
        
        renderTable : function(el, data) {
            $("table[name]", el).each(function() {

                var v = $$.getData(data, $(this).attr("name"));
                if (v == undefined) {
                    return true;
                }

                var url = $(this).attr("id");
                var pkName = $("tbody td.primary", this).html();

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
                                    $$.navigate(updateUrl, ownUrl);
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
                                    $$.navigate(removeUrl, ownUrl);
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
                            var ownUrl = refer + $$.getData(aData, referby) + "" + "/";
                            var referUrl = $$.rebase("html/" + refer + "view.html");
                            var a = $(document.createElement("a"));
                            a.attr("href", ownUrl);
                            a.html(td.html());
                            a.click(function() {
                                $$.navigate(referUrl, ownUrl);
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
                            var ownUrl = actionUrl + $$.getData(aData, referby)  + method;
                            var referUrl = $$.rebase("html/" + actionUrl + "list.html");
                            var a = $(document.createElement("a"));
                            a.attr("href", ownUrl);
                            a.html("+");
                            a.click(function() {
                                $$.navigate(referUrl, ownUrl);
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
                    aaData : data,
                    aoColumns : columns,
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
		},
        
        getData : function(data, name) {
            if (name.length == 0) {
                return data;
            }
            var result = data;
            var ns = name.split(".");
            for (var i = 0; i < ns.length; i++) {
                result = result[ns[i]];
                if (result == undefined) {
                    return undefined;
                }
            }
            return result;
        }

	});

})(jQuery, jnebula);
