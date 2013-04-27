'use strict';
/*utility */
function isUndefined(value) {
	return typeof value == 'undefined';
}
function isEmpty(value) {
	return isUndefined(value) || value === '' || value === null
			|| value !== value;
}

/* Directives */
function PopupListCtrl($scope,$resource,urlParams){
	var DataResource = $resource('/d/:typename/', urlParams, {
		query: {method:'GET', params:{}, isArray:true}
	});
	$scope.datalist = DataResource.query({});
	$scope.$returnData = function(index){
		return $scope.datalist[index];
	};
}

function simpleMap(data){
	var params =  {};
	
	var setS = data.split(/[\{\}\,\ ]/);
	for(var i=0;i<setS.length;i++){
		var kvSet = setS[i];
		if(kvSet.length>0){
			var kv = kvSet.split(":");
			var k = kv[0];
			var v = kv[1];
			params[k]=v;
		}		
	}
	return params;
}

function getVal(map,key){
	var keys = key.split(/\./);

	var vl=map,lastVl=vl;
	for(var i=0;i<keys.length-1;i++){
		vl = lastVl[keys[i]];
		if(typeof vl == 'undefined'){
			lastVl[keys[i]] = {};
			vl = lastVl[keys[i]];
		}
		lastVl = vl;
	}
	
	return vl[keys[keys.length-1]];
}

function putVal(map,key,v){
	var keys = key.split(/\./);
	
	var vl=map,lastVl=vl;
	for(var i=0;i<keys.length-1;i++){
		vl = lastVl[keys[i]];
		if(isUndefined(vl)){
			lastVl[keys[i]] = {};
			vl = lastVl[keys[i]];
		}
		lastVl = vl;
	}
	
	vl[keys[keys.length-1]] = v;
}
var PathMatchProvider = function(when) {
    // TODO(i): this code is convoluted and inefficient, we should construct the route matching
    //   regex only once and then reuse it
    var regex = '^' + when.replace(/([\.\\\(\)\^\$])/g, "\\$1") + '$',
        params = [],
        paramMatchs = [],
        dst = {};
    	jQuery.each(when.split(/\W/), function(index,param) {
      if (param) {
        var paramRegExp = new RegExp(":" + param + "([\\W])");
        if (regex.match(paramRegExp)) {
          regex = regex.replace(paramRegExp, "([^\\/]*)$1");
          params.push(param);
          paramMatchs.push(new RegExp(":" + param));
        }
      }
    });
    
    var regexp = new RegExp(regex);
    
    return {
    	check:function(on){
    		
            var match = on.match(regexp);
	        if (match) {
	        	jQuery.each(params, function(index,name) {
	            dst[name] = match[index + 1];
	          });
	        }
	        return match ? dst : null;
    	},
    	replace:function(on,url){
            var match = on.match(regexp);
	        if (match) {
	        	jQuery.each(params, function(index,name) {
	            url = url.replace(paramMatchs[index],match[index + 1]);
	          });
	        }
	        return url;        		
    	}
  }
} 

var neFromListDirective = [ 
   		'$http',
		'$templateCache',
		'$route',
		'$anchorScroll',
		'$compile',
		'$controller',
		'$interpolate',
		function($http, $templateCache, $route, $anchorScroll, $compile,
				$controller, $interpolate) {
	return {
		require : 'ngModel',
		restrict : 'A',
		terminal : true,
		link : function(scope, element, attrs, ngModelCtrl) {
			var reqUrl = attrs.popup;
			var beforePopupExp = attrs.beforepopup || '';
			var afterPopupExp = attrs.afterpopup || '';
			
			if(element.parents("form table").length<1){
				element.wrap('<div class="input-append"></div>').after('<a class="addon btn"><i class="icon-search"></i></a>').next().click(function(){
					var inparams = {};
					
					popup(reqUrl,inparams,function(retData){
						scope.ret = retData;
						scope.$eval(afterPopupExp);
					});
				});
			}else{
				element.wrap('<div class="input-append"></div>').after('<a class="addon btn"><i class="icon-search"></i></a>').next().click(function(){
				//element.addClass("inlinePopup").after('<a class="inlinePopup"><i class="icon-search"></i></a>').click(function(){
					var inparams = {};
					
					popup(reqUrl,inparams,function(retData){
						scope.ret = retData;
						scope.$eval(afterPopupExp);
					});
				});
			}
			
			function preparePopupWin(){
				if(jQuery("#popup-window").length<1){
					jQuery('body').append('<div id="popup-window" class="modal" style="display:none;">')
								  .append('</div>');
				}
			}
			
			preparePopupWin();

			
			var changeCounter = 0, lastScope, lastTemplate;
			function destroyLastScope() {
				if (lastScope) {
					lastScope.$destroy();
					lastScope = null;
				}
			}
			
			function popup(reqUrl,params,onReturn){
				var popupwin = jQuery("#popup-window");
				var popupwinBody = popupwin;
				var thisChangeId = ++changeCounter;
				popupwinBody.html('');

				function clearContent() {
					// ignore callback if another route change occured
					// since
					if (thisChangeId === changeCounter) {
						popupwinBody.html('');
						destroyLastScope();
					}
				}				
				
				var matcher = PathMatchProvider("/d/:typename/");
				var urlParams = matcher.check(reqUrl);
				var template = matcher.replace(reqUrl,"/theme/angularjs/unicorn/:typename-popup.html");
				if(template){
					popupwin.fadeIn(300);
					

					
					$http.get(template).success(function(response) {
						if (thisChangeId === changeCounter) {							
							popupwinBody.html(response);
							
							destroyLastScope();
							var link = $compile(popupwinBody.contents()),controller;

							lastScope = scope.$new();
							lastScope.template = template;
							lastScope.link = link;
							
							if (PopupListCtrl) {
								controller = $controller(PopupListCtrl,
										{
											urlParams : urlParams,
											$scope : lastScope
										});
								
								lastScope.$ret = function(index){
									$('#mask , #popup-window').fadeOut(300 , function() {
										$('#mask').remove();  
									});
									
									var data =lastScope.$returnData(index);
									onReturn(data);
								}
								
								popupwinBody.contents().data(
												'$ngControllerController',
												controller);
							}

							link(lastScope);
							
							//lastScope.$emit('$popupContentLoaded');
							//lastScope.$eval(onloadExp);
						}	
					}).error(clearContent);
				} else {
					clearContent();
				}
				
				// Add the mask to body
				$('body').append('<div id="mask"></div>');
				$('#mask').fadeIn(300);
				
				// When clicking on the button close or the mask layer the popup closed
				$('.close, #mask,.popup-close').live('click', function() { 
					$('#mask , #popup-window').fadeOut(300 , function() {
						$('#mask').remove();  
					});
				});	
			}
		}
	};
} ];

/**
 * @ngdoc event
 * @name ng.directive:ngView#$viewContentLoaded
 * @eventOf ng.directive:ngView
 * @eventType emit on the current ngView scope
 * @description
 * Emitted every time the ngView content is reloaded.
 */
var nbViewDirective = ['$http', '$templateCache', '$route', '$anchorScroll', '$compile',
                       '$controller',
               function($http,   $templateCache,   $route,   $anchorScroll,   $compile,
                        $controller) {
  return {
    restrict: 'ECA',
    terminal: true,
    link: function(scope, element, attr) {
      var lastScope,
          onloadExp = attr.onload || '';

      scope.$on('$routeChangeSuccess', update);
      update();


      function destroyLastScope() {
        if (lastScope) {
          lastScope.$destroy();
          lastScope = null;
        }
      }

      function clearContent() {
        element.html('');
        destroyLastScope();
      }

      function update() {
        var locals = $route.current && $route.current.locals,
            template = locals && locals.$template;

        if (template) {
          element.html(template);
          destroyLastScope();

          var link = $compile(element.contents()),
              current = $route.current,
              controller;

          lastScope = current.scope = scope.$new();
          if (current.controller) {
            locals.$scope = lastScope;
            controller = $controller(current.controller, locals);
            element.contents().data('$ngControllerController', controller);
          }

          link(lastScope);
          lastScope.$emit('$viewContentLoaded');
          lastScope.$eval(onloadExp);

          // $anchorScroll might listen on event...
          $anchorScroll();
        } else {
          // clearContent();
        }
      }
    }
  };
}];


/**
 * @ngdoc event
 * @name ng.directive:ngView#$viewContentLoaded
 * @eventOf ng.directive:ngView
 * @eventType emit on the current ngView scope
 * @description
 * Emitted every time the ngView content is reloaded.
 */
var nbJsonDataDirective = ['$http', '$controller','$resource',
               function($http, $controller,$resource) {
  return {
	scope:true, 
    restrict: 'A',
    link: function(scope, element, attr,controller) {
     var at = attr; 
     scope.jsonData = $resource(attr.jsondataurl).get({},function(){
      	var d = 	 scope.jsonData;
     });
    }
  };
}];

angular.module('nebulaDirectives', [])
.directive('nbView', nbViewDirective)
.directive('popup', neFromListDirective)
.directive('jsondataurl', nbJsonDataDirective);
