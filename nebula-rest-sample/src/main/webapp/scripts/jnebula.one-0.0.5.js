(function() {
    var jnebula = {};
    window.jnebula = window.$$ = jnebula;
})();

(function($, $$) {

    $.extend($$, {

        setup : function() {

            $("[template]").each(function() {
                var je = $(this);
                $$.include(this, je.attr("template"));
                if (je.is("[data]")) {
                    processTemplate(this, je.attr("data"));
                }
            });

            $('nav.menu ul').supersubs({
                minWidth : 12,
                maxWidth : 27,
                extraWidth : 1
            }).superfish();
            
            $('nav.menu li a[href]').live("click", function(e) {
                e.preventDefault();
                $("nav.menu ul").children(".current").removeClass("current");
                $(this).parent().addClass("current");
                $$.navigate($(this).attr("template"), $$.rebase($(this).attr("href")));
            });

            $('nav.menu li.current > a[href]').click();

        },

        rebase : function(url) {
            return "./" + url;
        },

        include : function(el, url) {
            $.ajax({
                type : "GET",
                url : $$.rebase(url),
                async : false,
                success : function(data) {
                    $(el).html(data);
                }
            });
        },

        navigate : function(templateUrl, dataUrl) {
            $.get(templateUrl, function(data) {
                var el = $("#main-section").html(data);
                $$.processTemplate(el, dataUrl);
                $$.renderLink(el);
                $$.renderValidator(el);
                $$.renderSubmit(el);
            });

        },

        popUp : function(templateUrl, dataUrl) {
            $.nmManual(templateUrl, {
//                sizes : {
//                    minW : 500
//                },
                callbacks : {
                    beforeShowCont : function(e) {
                        var el = e.elts.cont[0];

                    }
                }
            });

        },

        renderLink : function(el) {
            $$.renderNavigate(el);
            $$.renderPopup(el);
        },

        renderNavigate : function(el) {
            $("a[template]", el).each(function(e) {
                var ja = $(this);
                ja.click(function() {
                    e.preventDefault();
                    $$.navigate(ja.attr("template"), $$.rebase(ja.attr("href")));
                });
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
                        type : $(form).attr("method"),
                        url : form.action,
                        data : $.each($(form).serializeArray(), function(i, f) {
                            if (f.name == "password" || f.name == "password_confirm") {
                                f.value = $.md5(f.value);
                            }
                        }),
                        dataType : "json",
                        async : false,
                        success : function(data) {
                            return false;
                        },
                        error : function(XMLHttpRequest, textStatus, errorThrown) {
                            alert("错误！");
                        }
                    });
                });
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
            
            if (validator == undefined) {
                return;
            }
            
            validator.onsubmit = false;
            
            
            
            
        },

        processTemplate : function(el, dataUrl) {

            if (dataUrl == undefined) {
                return;
            }

            $("form", el).attr("action", dataUrl);

            $.ajax({
                type : "GET",
                url : dataUrl,
                dataType : "json",
                async : false,
                success : function(data) {
                    $$.processValueTemplate(el, data);
                    $$.processTableTemplate(el, data);
                }
            });

        },

        processValueTemplate : function(el, data) {
            $.each($(el).find("input, select, textarea, span[name], a[href]").not("input[type='password'], :submit, :button, :reset, :image"), function(i, e) {

                if ($(e).is("a[href]")) {
                	var vn = /\{(.*)\}/.exec($(e).attr("href"));
                	if (vn != null) {
                		//$(e).attr("href", $(e).attr("href").replace(/\{(.*)\}/, "$1"));
                		$(e).attr("href", $(e).attr("href").replace(/\{(.*)\}/, $$.getData(data, vn[1])));
                	}
                }

                var v = data[$(e).attr("name")];

                if (v == undefined) {
                    return true;
                }

                if ($(e).is(":radio,:checkbox")) {
                    e.checked = e.value == v ? true : false;
                } else {
                    e.value = v;
                }

                if ($(e).is("span[name]")) {
                    e.innerHTML = v;
                }

            });
        },

        processTableTemplate : function(el, data) {

            $("table[name]", el).each(function() {

                var url = $(this).attr("id");
                var pkName = $("tbody td.primary", this).html();
                var dataName = $(this).attr("name");
                
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
                    aaData : $$.getData(data, dataName),
                    aoColumns : columns,
                    fnRowCallback : function(nRow, aData, iDisplayIndex, iDisplayIndexFull) {
                        $.each(callbacks, function(i, f) {
                            f(nRow, aData, iDisplayIndex, iDisplayIndexFull);
                        });
                        return nRow;
                    }
                });

            });
        },

        getData : function(data, name) {
            if (name.length == 0) {
                return data;
            }
            var result = data;
            var ns = name.split(".");
            for ( var i = 0; i < ns.length; i++) {
                result = result[ns[i]];
                if (result == undefined) {
                    return undefined;
                }
            }
            return result;
        }
    });

})(jQuery, jnebula);
