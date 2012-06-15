'use strict';

/* Directives */


var neTabsDirective = [function() {
    return {
      restrict: 'EC',
      transclude: true,
      scope: {},
      controller: function($scope, $element) {
        var panes = $scope.panes = [];
 
        $scope.select = function(pane) {
          angular.forEach(panes, function(pane) {
            //TODO pane.selected = false;
        	  pane.selected = true;
          });
          pane.selected = true;
        }
 
        this.addPane = function(pane) {
          if (panes.length == 0) $scope.select(pane);
          panes.push(pane);
        }
      },
      template:
        '<div class="tabbable">' +
          '<ul class="nav nav-tabs">' +
            '<li ng-repeat="pane in panes" ng-class="{active:pane.selected}">'+
              '<a href="" ng-click="select(pane)">{{pane.title}}</a>' +
            '</li>' +
          '</ul>' +
          '<div class="tab-content" ng-transclude></div>' +
        '</div>',
      replace: true
    };
  }];

var nePaneDirective = [ function() {
    return {
      require: '^tabs',
      restrict: 'EC',
      transclude: true,
      scope: { title: 'bind' },
      link: function(scope, element, attrs, tabsCtrl) {
    	  if(tabsCtrl){
    	        tabsCtrl.addPane(scope);    		  
    	  }else{
    		  scope.selected = true;
    	  }
      },
      template:
        '<div class="tab-pane" ng-class="{active: selected}" ng-transclude>' +
        '</div>',
      replace: true
    };
  }];

function isUndefined(value){return typeof value == 'undefined';}
function isEmpty(value) {
	  return isUndefined(value) || value === '' || value === null || value !== value;
}

var neCodeEditorDirective = [ function() {
    return {
      require: 'ngModel',
      restrict: 'C',
      terminal: true,
      link: function(scope, element, attrs, ngModelCtrl) {
    	  var $mode = isEmpty(attrs.mode)?"text/nebula":attrs.mode;
  
          var $editor = CodeMirror.fromTextArea(element[0], {
              lineNumbers: true,
              matchBrackets: true,
              mode: $mode,
  		      onChange: function(el,param){
  				if(ngModelCtrl.$viewValue != $editor.getValue()){
  				      scope.$apply(function() {
  							ngModelCtrl.$setViewValue($editor.getValue());
  				      });  				      
  				}        	
  			}
            });
    	ngModelCtrl.$oldrender = ngModelCtrl.$render;
    	ngModelCtrl.$render = function(){    		
			if(isEmpty(ngModelCtrl.$viewValue)){
				$editor.setValue('');				
			}else{
				$editor.setValue("");
				$editor.setValue(ngModelCtrl.$viewValue);
			}
    	};
      }
    };
  }];

/*
 * 
var neCodeEditorDirective = [ function() {
    return {
      require: 'ngModel',
      restrict: 'C',
      terminal: true,
      template:
          '<div>' +
          '</div>',
        replace: true,
      link: function(scope, element, attrs, ngModelCtrl) {

          var $editor = CodeMirror(element[0], {
              lineNumbers: true,
              matchBrackets: true,
              mode:attrs['x-mode'],
  		    onChange: function(el,param){
  				if(isEmpty($editor.getValue())){
  					
  				}else if(ngModelCtrl.$viewValue != $editor.getValue()){
  				      scope.$apply(function() {
  							ngModelCtrl.$setViewValue($editor.getValue());
  				      });  				      
  				}        	
  			}
            });
    	ngModelCtrl.$oldrender = ngModelCtrl.$render;
    	ngModelCtrl.$render = function(){    		
			if(isEmpty(ngModelCtrl.$viewValue)){
				$editor.setValue('');				
			}else{
				$editor.setValue("");
				$editor.setValue(ngModelCtrl.$viewValue);
			}
    	};
      }
    };
  }];
*/

var neDragableDirective = ['$document',function($document) { 
	var startX=0, startY=0, x = 0, y = 0; 
	return {
	      restrict: 'EC',
	      link: function(scope, element, attr) { 
//	    	  element.html("dddddd");
				element.css({ position: 'relative', border: '1px solid red', backgroundColor: 'lightgrey', cursor: 'pointer' }); 
				element.bind('mousedown', function(event) { 
					startX = event.screenX - x; startY = event.screenY - y; 
					$document.bind('mousemove', mousemove); 
					$document.bind('mouseup', mouseup);
				});

				function mousemove(event) {
				  y = event.screenY - startY;
				  x = event.screenX - startX;
				  element.css({
				    top: y + 'px',
				    left:  x + 'px'
				  });
				}
				
				function mouseup() {
				  $document.unbind('mousemove', mousemove);
				  $document.unbind('mouseup', mouseup);
				}
	      }
	}
}];

/**
 * @ngdoc event
 * @name angular.module.ng.$compileProvider.directive.ngView#$viewContentLoaded
 * @eventOf angular.module.ng.$compileProvider.directive.ngView
 * @eventType emit on the current ngView scope
 * @description
 * Emitted every time the ngView content is reloaded.
 */
var neViewDirective = ['$http', '$templateCache', '$route', '$anchorScroll', '$compile',
                       '$controller','$interpolate',
               function($http,   $templateCache,   $route,   $anchorScroll,   $compile,
                        $controller, $interpolate) {
  return {
    restrict: 'A',
    template: '<div>' +
    			'<div class="body"></div>' +
    			'<div class="body"></div>' +
    		 '</div>',
    replace: true,
    transclude: true,
    link: function(scope, element, attr) {
      var changeCounter = 0,
          lastScope,
          lastTemplate,
          onloadExp = attr.onload || '';

      var elementCur = angular.element(element.children()[0]);
      var elementLast = angular.element(element.children()[1]);
            
      scope.$on('$afterRouteChange', update);
      scope.showme = function(){
          var opened = true;
          elementLast.removeClass(opened ? 'closed' : 'opened');
          elementLast.addClass(opened ? 'opened' : 'closed');

          opened = false;
          elementCur.removeClass(opened ? 'closed' : 'opened');
          elementCur.addClass(opened ? 'opened' : 'closed');

          var t = elementCur;
          elementCur = elementLast;
          elementLast = t;
          elementLast.html("");
          t = null;
      }
      //update();


      function destroyLastScope() {
        if (lastScope) {
          lastScope.$destroy();
          lastScope = null;
        }
      }
      
      

      function update() {
          //old var template = $route.current && $route.current.template,
        var template = $route.current && $interpolate($route.current.template)($route.current.params),
            thisChangeId = ++changeCounter;

        function clearContent() {
          // ignore callback if another route change occured since
          if (thisChangeId === changeCounter) {
            element.html('');
            destroyLastScope();
          }
        }

        if (template) {
          $http.get(template, {cache: $templateCache}).success(function(response) {
            // ignore callback if another route change occured since
            if (thisChangeId === changeCounter) {
              elementLast.html(response);
              destroyLastScope();

              var link = $compile(elementLast.contents()),
                  current = $route.current,
                  controller;

              lastScope = current.scope = scope.$new();
              lastScope.template = template;
              lastScope.link = link;
              if (current.controller) {
                controller = $controller(current.controller, {$scope: lastScope});
                elementLast.contents().data('$ngControllerController', controller);
              }

              link(lastScope);
              lastScope.$emit('$viewContentLoaded');
              lastScope.$eval(onloadExp);
                            
              
              // $anchorScroll might listen on event...
              $anchorScroll();
            }
          }).error(clearContent);
        } else {
          clearContent();
        }
      }
    }
  };
}];

angular.module('nebula.directives', []).
  directive('appVersion', ['version', function(version) {
    return function(scope, elm, attrs) {
      elm.text(version);
    };
  }]).
  directive('codeeditor', neCodeEditorDirective).
//  directive('dragable', neDragableDirective).
  directive('neView', neViewDirective).
  directive('tabs', neTabsDirective).
  directive('pane', nePaneDirective);


