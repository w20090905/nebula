'use strict';


// Declare app level module which depends on filters, and services
var nebulaModule = angular.module('nebula', ['nebulaFilters', 'nebulaServices', 'nebulaDirectives','ngResource']);

nebulaModule.config(['$routeProvider', function($routeProvider) {
	$routeProvider.when('/c/:userid', {template: 'angularjs/crmmain.html', controller: ContactRecordsCtrl});
    $routeProvider.otherwise({redirectTo: '/main'});
}]).run(function($rootScope, $location,  $interpolate) {
 	$rootScope.$on('$beforeRouteChange', function(event,next,last) {
 		if(next.$route.templateURL){
 			next.$route.template = $interpolate(next.$route.templateURL)(next.pathParams);
 		}
	});
});
