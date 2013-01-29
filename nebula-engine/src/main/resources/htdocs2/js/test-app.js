'use strict';


// Declare app level module which depends on filters, and services
var nebulaModule = angular.module('test-app', ['nebulaFilters', 'nebulaServices', 'nebulaDirectives','ngResource','ngCookies']);

nebulaModule.config(['$routeProvider', function($routeProvider,$cookies) {
	$routeProvider.when('/c/!signup', {templateUrl: 'angularjs/User-detail.html', controller: NewUserCtrl})
			.when('/c/:userid', {templateUrl: 'angularjs/crmmain.html', controller: ContactRecordsCtrl})
			.otherwise({redirectTo: '/main'});
}]);

