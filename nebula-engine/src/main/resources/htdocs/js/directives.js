'use strict';

/* Directives */
var neTabsDirective = [ function() {
	return {
		restrict : 'EC',
		transclude : true,
		scope : {},
		controller : function($scope, $element) {
			var panes = $scope.panes = [];

			$scope.select = function(pane) {
				angular.forEach(panes, function(pane) {
					// TODO pane.selected = false;
					pane.selected = true;
				});
				pane.selected = true;
			}

			this.addPane = function(pane) {
				if (panes.length == 0)
					$scope.select(pane);
				panes.push(pane);
			}
		},
		template : '<div class="tabbable">'
				+ '<ul class="nav nav-tabs">'
				+ '<li ng-repeat="pane in panes" ng-class="{active:pane.selected}">'
				+ '<a href="" ng-click="select(pane)">{{pane.title}}</a>'
				+ '</li>' + '</ul>'
				+ '<div class="tab-content" ng-transclude></div>' + '</div>',
		replace : true
	};
} ];

var nePaneDirective = [ function() {
	return {
		require : '^tabs',
		restrict : 'EC',
		transclude : true,
		scope : {
			title : 'bind'
		},
		link : function(scope, element, attrs, tabsCtrl) {
			if (tabsCtrl) {
				tabsCtrl.addPane(scope);
			} else {
				scope.selected = true;
			}
		},
		template : '<div class="tab-pane" ng-class="{active: selected}" ng-transclude>'
				+ '</div>',
		replace : true
	};
} ];

function isUndefined(value) {
	return typeof value == 'undefined';
}
function isEmpty(value) {
	return isUndefined(value) || value === '' || value === null
			|| value !== value;
}
function compare(left, right) {
	var cleft = left.replace(/\r/g, "");
	var cright = right;
	return cleft == cright;
}

var neCodeEditorDirective = [ function() {
	return {
		require : 'ngModel',
		restrict : 'C',
		terminal : true,
		link : function(scope, element, attrs, ngModelCtrl) {
			var $mode = isEmpty(attrs.mode) ? "text/nebula" : attrs.mode;

			var $editor = CodeMirror.fromTextArea(element[0], {
				lineNumbers : true,
				matchBrackets : true,
				mode : $mode,
				onChange : function(el, param) {
					if (isEmpty(ngModelCtrl.$viewValue)) {
						if (isEmpty($editor.getValue())) {

						} else {
							scope.$apply(function() {
								ngModelCtrl.$setViewValue($editor.getValue());
							});
						}
					} else if (!compare(ngModelCtrl.$viewValue, $editor
							.getValue())) {
						scope.$apply(function() {
							ngModelCtrl.$setViewValue($editor.getValue());
						});
					}
				}
			});
			ngModelCtrl.$oldrender = ngModelCtrl.$render;
			ngModelCtrl.$render = function() {
				if (isEmpty(ngModelCtrl.$viewValue)) {
					$editor.setValue("");
				} else if (ngModelCtrl.$viewValue != $editor.getValue()) {
					$editor.setValue(ngModelCtrl.$viewValue);
				}
			};
		}
	};
} ];


var neShowContentDirective = [ function() {
	return {
		restrict : 'C',
		terminal : true,
		link : function(scope, element, attrs) {
			var dList = attrs.data;
			scope.$watch(function(scope){
				return scope[dList] + 1;
			}, function(value) {
				element.text(value);
			});
		}
	};
} ];




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

var neTreeDataConverterDirective = [ function() {
	return {
		require : 'tree',
		link: function(scope, element, attr) {
			scope.$eval(attr.converter);
		}
	};
} ];

var neTreeViewDirective = [ function() {
	var labelType, useGradients, nativeTextSupport, animate;

	(function() {
		var ua = navigator.userAgent, iStuff = ua.match(/iPhone/i)
				|| ua.match(/iPad/i), typeOfCanvas = typeof HTMLCanvasElement, nativeCanvasSupport = (typeOfCanvas == 'object' || typeOfCanvas == 'function'), textSupport = nativeCanvasSupport
				&& (typeof document.createElement('canvas').getContext('2d').fillText == 'function');
		// I'm setting this based on the fact that ExCanvas provides text
		// support for IE
		// and that as of today iPhone/iPad current text support is lame
		labelType = (!nativeCanvasSupport || (textSupport && !iStuff)) ? 'Native'
				: 'HTML';
		nativeTextSupport = labelType == 'Native';
		useGradients = nativeCanvasSupport;
		animate = !(iStuff || !nativeCanvasSupport);
	})();
	
	var Log = {
		elem : false,
		write : function(text) {
			if (!this.elem)
				this.elem = document.getElementById('log');
			this.elem.innerHTML = text;
			this.elem.style.left = (500 - this.elem.offsetWidth / 2) + 'px';
		}
	};

	return {
	    priority: 0,
		restrict : 'C',
		terminal : true,
		controller : function($scope, $element) {
			$scope.$config = function(config){
				var defaultConfig = {id:"id",name:"name",parent:"parent"};
				$scope.$converter = angular.extend(defaultConfig,config);				
			};
		},
		link : function(scope, element, attrs) {
			// init Spacetree
			// Create a new ST instance
			var st = new $jit.ST({
				// id of viz container element
				injectInto : attrs.id,
				// set duration for the animation
				duration : 300,
				// set animation transition type
				transition : $jit.Trans.Quart.easeInOut,
				// set distance between node and its children
				levelDistance : 80,
				
				levelsToShow : 3,
				constrained : true,
				offsetX : 120,
				offsetY : 80,
				// enable panning
				Navigation : {
					enable : true,
					panning : true
				},
				// set node and edge styles
				// set overridable=true for styling individual
				// nodes or edges
				Node : {
					height : 30,
					width : 10,
					type : 'circle',
					lineWidth: '5',
					dim : '10',
					color : '#aaa',
					align : 'left',
					overridable : true
				},

				Edge : {
					type : 'bezier',
					overridable : true
				},

				onBeforeCompute : function(node) {
					Log.write("loading " + node.name);
				},

				onAfterCompute : function() {
					Log.write("done");
				},

				// This method is called on DOM label creation.
				// Use this method to add event handlers and styles to
				// your node.
				onCreateLabel : function(label, node) {
					label.id = node.id;
					label.innerHTML = node.name;
					label.onclick = function() {
						st.onClick(node.id);
					};
					
					var style = label.style;	
					// if the node belongs to the last plotted level
					if (!node.anySubnode("exist")) {
						// count children number
						var count = 0;
						node.eachSubnode(function(n) {
							count++;
						});
						if(count==0){		
							style.marginLeft = '0px';
							style.textAlign = 'left';
						}
					}					
				},

				// This method is called right before plotting
				// a node. It's useful for changing an individual node
				// style properties before plotting it.
				// The data properties prefixed with a dollar
				// sign will override the global node style properties.
				onBeforePlotNode : function(node) {
					// add some color to the nodes in the path between the
					// root node and the selected node.
					if (node.selected) {
						node.data.$color = "blue";
					} else {
						delete node.data.$color;
						// if the node belongs to the last plotted level
						if (!node.anySubnode("exist")) {
							// count children number
							var count = 0;
							node.eachSubnode(function(n) {
								count++;
							});
							if(count==0){
								node.data.$color ='#ccc';
							}else{
								node.data.$color ='#888';								
							}
							// assign a node color based on
							// how many children it has
							// [ '#aaa', '#baa', '#caa',
									//'#daa', '#eaa', '#faa' ][count];
						}else{
							node.data.$color ='#888';
						}
					}
					
				},

				// This method is called right before plotting
				// an edge. It's useful for changing an individual edge
				// style properties before plotting it.
				// Edge data proprties prefixed with a dollar sign will
				// override the Edge global style properties.
				onBeforePlotLine : function(adj) {
					if (adj.nodeFrom.selected && adj.nodeTo.selected) {
						adj.data.$color = "#BBBBEE";
						adj.data.$lineWidth = 3;
					} else {
						delete adj.data.$color;
						delete adj.data.$lineWidth;
					}
				}
			});

			var lastValue;
			var watchExpr = attrs.data;
			
			scope.$watch(function() {
				var newValue = scope.$eval(watchExpr)
		          if (!angular.equals(lastValue, newValue)) {
		        	lastValue = angular.copy(newValue);
		        	
					var jsonRawData = newValue;
					var config = scope.$converter;

					var json={};
					var w = {};
					angular.forEach(jsonRawData, function(item){
						var n={};
						n.id = item[config.id];
						n.name = item[config.name];
						n.data = {};
						n.parent = item[config.parent];
						w[n.id] = n;
						}, w);

					angular.forEach(w, function(item){
						if(!isEmpty(item.parent)){
							if(isUndefined(w[item.parent].children)){
								w[item.parent].children=[];
							}	
							w[item.parent].children.push(item);								
						}else{
							json = item;
						}		
					},w);
					w = null;
					
					// load json data
					st.loadJSON(json);
					// compute node positions and layout
					st.compute();
					// optional: make a translation of the tree
					st.geom.translate(new $jit.Complex(-200, 0), "current");
					// emulate a click on the root node.
					st.onClick(st.root);
					st.onClick(st.root);
					// end
				}
			});
		}
	};
} ];

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
		if(isUndefined(vl)){
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

var neDragableDirective = [ '$document', function($document) {
	var startX = 0, startY = 0, x = 0, y = 0;
	return {
		restrict : 'EC',
		link : function(scope, element, attr) {
			element.css({
				position : 'relative',
				border : '1px solid red',
				backgroundColor : 'lightgrey',
				cursor : 'pointer'
			});
			element.bind('mousedown', function(event) {
				startX = event.screenX - x;
				startY = event.screenY - y;
				$document.bind('mousemove', mousemove);
				$document.bind('mouseup', mouseup);
			});

			function mousemove(event) {
				y = event.screenY - startY;
				x = event.screenX - startX;
				element.css({
					top : y + 'px',
					left : x + 'px'
				});
			}

			function mouseup() {
				$document.unbind('mousemove', mousemove);
				$document.unbind('mouseup', mouseup);
			}
		}
	}
} ];

/**
 * @ngdoc event
 * @name angular.module.ng.$compileProvider.directive.ngView#$viewContentLoaded
 * @eventOf angular.module.ng.$compileProvider.directive.ngView
 * @eventType emit on the current ngView scope
 * @description Emitted every time the ngView content is reloaded.
 */
var neViewDirective = [
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
				restrict : 'A',
				//template : '<div>' + '<div class="body"></div>'
				//		+ '<div class="body"></div>' + '</div>',
				replace : true,
				transclude : true,
				link : function(scope, element, attr) {
					var changeCounter = 0, lastScope, lastTemplate, onloadExp = attr.onload
							|| '';

					//var elementCur = angular.element(element.children()[0]);
					var elementLast = element;//angular.element(element.children()[1]);

					scope.$on('$afterRouteChange', update);
					scope.showme = function() {
//
//						elementLast.toggle("normal");
//						elementCur.toggle(false);
						
						//var opened = true;
						//elementLast.removeClass(opened ? 'closed' : 'opened');
						//elementLast.addClass(opened ? 'opened' : 'closed');

						//opened = false;
						//elementCur.removeClass(opened ? 'closed' : 'opened');
						//elementCur.addClass(opened ? 'opened' : 'closed');
						
						
						//var t = elementCur;
						//elementCur = elementLast;
						//elementLast = t;
						//elementLast.html("");
						//t = null;
					}
					// update();

					function destroyLastScope() {
						if (lastScope) {
							lastScope.$destroy();
							lastScope = null;
						}
					}

					function update() {
						// old var template = $route.current &&
						// $route.current.template,
						var template = $route.current
								&& $interpolate($route.current.template)(
										$route.current.params), thisChangeId = ++changeCounter;

						function clearContent() {
							// ignore callback if another route change occured
							// since
							if (thisChangeId === changeCounter) {
								element.html('');
								destroyLastScope();
							}
						}

						if (template) {
							$http
									.get(template, {
										cache : $templateCache
									})
									.success(
											function(response) {
												// ignore callback if another
												// route change occured since
												if (thisChangeId === changeCounter) {
													elementLast.html(response);
													destroyLastScope();

													var link = $compile(elementLast
															.contents()), current = $route.current, controller;

													lastScope = current.scope = scope
															.$new();
													lastScope.template = template;
													lastScope.link = link;
													if (current.controller) {
														controller = $controller(
																current.controller,
																{
																	$scope : lastScope
																});
														elementLast
																.contents()
																.data(
																		'$ngControllerController',
																		controller);
													}

													link(lastScope);
													lastScope
															.$emit('$viewContentLoaded');
													lastScope.$eval(onloadExp);

													// $anchorScroll might
													// listen on event...
													$anchorScroll();
												}
											}).error(clearContent);
						} else {
							clearContent();
						}
					}
				}
			};
		} ];

angular.module('nebula.directives', []).directive('appVersion',
		[ 'version', function(version) {
			return function(scope, elm, attrs) {
				elm.text(version);
			};
		} ]).directive('codeeditor', neCodeEditorDirective)
		.directive('neView', neViewDirective)
		.directive('tabs', neTabsDirective)
		.directive('tree', neTreeViewDirective)
		.directive('converter', neTreeDataConverterDirective)
		.directive('showContent', neShowContentDirective)
		.directive('popup', neFromListDirective)
		.directive('pane', nePaneDirective);