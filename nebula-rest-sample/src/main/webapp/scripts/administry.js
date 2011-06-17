/*---------------------------------------------------------------------

Template Name: 	Administry
Version:	 	1.0
Release Date: 	July 12, 2010

File:       	administry.js
Updated:    	2010-07-12

Copyright (c) 2010 Zoran Juric - http://themeforest.net/user/zoranjuric

-----------------------------------------------------------------------

WARNING! DO NOT EDIT THIS FILE UNLESS YOU KNOW WHAT YOU ARE DOING!

---------------------------------------------------------------------*/

// Preload images
imageObj = new Image();
imgs = ["/nebula-rest-sample/img/toggle.gif", "/nebula-rest-sample/img/nyro/ajaxLoader.gif", "/nebula-rest-sample/img/nyro/prev.gif", "/nebula-rest-sample/img/nyro/next.gif"];
for (i = 0; i < imgs.length; i++) imageObj.src = imgs[i];

// Administry object setup
if (!Administry) var Administry = {}

// scrollToTop() - scroll window to the top
Administry.scrollToTop = function (e) {
    $(e).hide().removeAttr("href");
    if ($(window).scrollTop() != "0") {
        $(e).fadeIn("slow");
    }
    var scrollDiv = $(e);
    $(window).scroll(function () {
        if ($(window).scrollTop() == "0") {
            $(scrollDiv).fadeOut("slow");
        } else {
            $(scrollDiv).fadeIn("slow");
        }
    });
    $(e).click(function () {
        $("html, body").animate({
            scrollTop: 0
        }, "slow");
    })
}

// setup() - Administry init and setup
Administry.setup = function () {
    // Open an external link in a new window
    $('a[href^="http://"]').filter(function () {
        return this.hostname && this.hostname !== location.hostname;
    }).attr('target', '_blank');
	
    // build an animated footer
    $('#animated').each(function () {
        $(this).hover(function () {
            $(this).stop().animate({
                opacity: 0.8
            }, 400);
        }, function () {
            $(this).stop().animate({
                opacity: 0.0
            }, 200);
        });
    });

    // scroll to top on request
    if ($("a#totop").length) Administry.scrollToTop("a#totop");

    // setup content boxes
    if ($(".content-box").length) {
        $(".content-box header").css({
            "cursor": "s-resize"
        });
        // Give the header in content-box a different cursor	
        $(".content-box header").click(
        function () {
            $(this).parent().find('section').toggle(); // Toggle the content
            $(this).parent().toggleClass("content-box-closed"); // Toggle the class "content-box-closed" on the content
        });
    }
    
	// custom tooltips to replace the default browser tooltips for <a title=""> <div title=""> and <span title="">
    $("a[title], div[title], span[title]").tipTip();
}

// progress() - animate a progress bar "el" to the value "val"
Administry.progress = function (el, val, max) {
    var duration = 400;
    var span = $(el).find("span");
    var b = $(el).find("b");
    var w = Math.round((val / max) * 100);
    $(b).fadeOut('fast');
    $(span).animate({
        width: w + '%'
    }, duration, function () {
        $(el).attr("value", val);
        $(b).text(w + '%').fadeIn('fast');
    });
}

//expandableRows() - expandable table rows
Administry.expandableRows = function () {
 var titles_total = $('td.title').length;
 if (titles_total) { /* setting z-index for IE7 */
     $('td.title').each(function (i, e) {
         $(e).children('div').css('z-index', String(titles_total - i));
     });

     $('td.title').find('a').click(function () {
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

Administry.inclue = function(el, url) {
	$.get(url, function(data) {
		var e = $(el).empty();
		e.append(data);
	});
}
