'use strict';
/* Directives */

function PopupListCtrl($scope,$resource,urlParams,params){
	$scope.params = params;
	var DataResource = $resource('d/:typename/', urlParams, {
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
			var params = simpleMap(attrs.params);
			var returns = simpleMap(attrs.returns);
			
			element.after('<a>::</a>').next().click(function(){
				var inparams = {};
				angular.forEach(params,function(that,me){
					putVal(inparams,that,getVal(scope,me));
				});				
				
				popup(reqUrl,inparams,function(retData){
					angular.forEach(returns,function(that,me){
						putVal(scope,me,getVal(retData,that));
					});
					//scope.$digest();
				});
			});
			
			function preparePopupWin(){
				if(jQuery("#popup-window").length<1){
					jQuery('body').append('<div id="popup-window"><a class="close"><img src="css\/close_pop.png" class="btn_close" title="Close Window" alt="Close" /></a>' 
									+ '<div id="popup-content"></div>'
									+ '</div>');
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
				var popupwinBody = angular.element(popupwin.children()[1]);
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
				var template = matcher.replace(reqUrl,"angularjs/:typename-popup.html");
				if(template){
					popupwin.fadeIn(300);
					
					//Set the center alignment padding + border
					var marginTop = ($(popupwin).height() + 24) / 2; 
					var marginLeft = ($(popupwin).width() + 24) / 2; 

					popupwin.css({ 
						'margin-top' : -marginTop,
						'margin-left' : -marginLeft
					});
					
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
											params : params,
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
				$('a.close, #mask').live('click', function() { 
					$('#mask , #popup-window').fadeOut(300 , function() {
						$('#mask').remove();  
					});
				});	
			}
		}
	};
} ];

angular.module('nebulaDirectives', [])
	.directive('popup', neFromListDirective);
