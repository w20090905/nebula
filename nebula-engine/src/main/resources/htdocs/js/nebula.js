'use strict';


// Declare app level module which depends on filters, and services
var nebulaModule = angular.module('nebula', ['nebula.filters', 'nebula.services', 'nebula.directives','ngResource']);

nebulaModule.config(['$routeProvider', function($routeProvider) {
	$routeProvider.when('/t/angularjs/:typename-:cat.html', {template: 'angularjs/angularjs-edit.html', controller: AngularJSCtrl});
	$routeProvider.when('/t/template/:typename-:cat.html.ftl', {template: 'angularjs/angularjs-edit.html', controller: FreeMarkerCtrl});
	$routeProvider.when('/d/:typename', {template: 'angularjs/{{typename}}-list.html',   controller: EntityListCtrl});
	$routeProvider.when('/d/:typename/!new', {template: 'angularjs/{{typename}}-detail.html', controller: NewEntityCtrl});
    $routeProvider.when('/d/:typename/:id', {template: 'angularjs/{{typename}}-detail.html', controller: EntityCtrl});
    $routeProvider.when('/view1', {template: 'angularjs/partial1.html', controller: MyCtrl1});
    $routeProvider.when('/view2', {template: 'angularjs/partial2.html', controller: MyCtrl2});
    $routeProvider.otherwise({redirectTo: '/view1'});
}]);

nebulaModule.directive('name', function() {
	return {
		require : 'ngModel',
		link : function(scope, elm, attrs, ctrl) {
			ctrl.$parsers.unshift(function(viewValue) {
				if (viewValue) {
					ctrl.$setValidity('name', true);
					return viewValue;
				} else {
					ctrl.$setValidity('name', false);
						return undefined;
				}
			});
		  }
	  };
});

nebulaModule.run(function($rootScope, $location,  $interpolate) {
	$rootScope.$on('$routeChangeStart', function(event, route) {
		var tu = route.$route.templateUrl;
		if (tu) {
			var otu = route.$route.originTemplateUrl;
			if (!otu) {
				otu = route.$route.originTemplateUrl = tu;
			}
			route.$route.templateUrl = $interpolate(otu)(route.params);
		}
	});
});
