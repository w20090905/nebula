'use strict';


// Declare app level module which depends on filters, and services
var nebulaModule = angular.module('app', ['nebulaFilters', 'nebulaServices', 'nebulaDirectives','ngResource','ngCookies']);

nebulaModule.config(['$routeProvider', function($routeProvider,$cookies) {
	$routeProvider.
	when('/c/:userid', {templateUrl: '/theme/angularjs/unicorn/crmmain.html', controller: ContactRecordsCtrl}).
	/* Template edit */
	when('/d/:typename', {templateUrlWP: '/theme/angularjs/unicorn/{{typename}}-list.html',   controller: EntityListCtrl}).
	when('/d/:typename/!new', {templateUrlWP: '/theme/angularjs/unicorn/{{typename}}-detail.html', controller: NewEntityCtrl}).
	when('/d/:typename/:id', {templateUrlWP: '/theme/angularjs/unicorn/{{typename}}-detail.html', controller: EntityCtrl}).
	when('/welcome', {templateUrl: '/theme/angularjs/welcome.html', controller: DoNothingCtrl}).
	otherwise({redirectTo: '/d/Type'});
}])	.run(function($rootScope, $location,  $interpolate) {
	$rootScope.$on('$routeChangeStart', function(event,next,last) {
		if(next.$route.templateUrlWP){
			next.$route.templateUrl = $interpolate(next.$route.templateUrlWP)(next.pathParams);
		}
});
});

