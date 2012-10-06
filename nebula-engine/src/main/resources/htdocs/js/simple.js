'use strict';


// Declare app level module which depends on filters, and services
var nebulaModule = angular.module('nebula', ['nebula.filters', 'nebula.services', 'nebula.directives','ngResource']);

nebulaModule.config(['$routeProvider', function($routeProvider) {
	$routeProvider.when('/c/:userid', {template: 'angularjs/crmmain.html', controller: ContactRecordsCtrl});
    $routeProvider.otherwise({redirectTo: '/main'});
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
