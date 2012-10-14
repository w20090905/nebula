'use strict';


// Declare app level module which depends on filters, and services
var nebulaModule = angular.module('simple', ['nebulaFilters', 'nebulaServices', 'nebulaDirectives','ngResource','ngCookies']);

nebulaModule.config(['$routeProvider',function($routeProvider) {
	$routeProvider.when('/c/:userid', {templateUrl: 'angularjs/crmmain.html', controller: ContactRecordsCtrl});
    $routeProvider.otherwise({redirectTo: '/main'});
}]).run(function($rootScope, $location,  $interpolate) {
 	$rootScope.$on('$routeChangeStart', function(event,next,last) {
 		if(next.$route.templateUrlWP){
 			next.$route.templateUrl = $interpolate(next.$route.templateUrlWP)(next.pathParams);
 		}
	});
});
