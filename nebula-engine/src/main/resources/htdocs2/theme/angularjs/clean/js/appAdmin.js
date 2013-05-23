'use strict';

/* App Module */
angular.module('appAdmin', ['nebulaFilters', 'nebulaServices','nebulaDirectives','ngResource','ngCookies']).
	config(['$routeProvider', function($routeProvider) {
		$routeProvider.
			/* Template edit */
			when('/t/template/:typename-:cat.html.ftl', {templateUrl: '/theme/angularjs/unicorn/Theme-edit.html', controller: FreeMarkerCtrl}).
			when('/t/:theme/:skin/:typename-:cat.html', {templateUrl: '/theme/angularjs/unicorn/Theme-edit.html', controller: AngularJSCtrl}).
			/* Type edit */
			when('/d/Type/', {templateUrlWP: '/theme/angularjs/unicorn/Type-list.html',   controller: TypeListCtrl}).
			when('/d/Type/!new', {templateUrlWP: '/theme/angularjs/unicorn/Type-detail.html', controller: NewTypeCtrl}).
			when('/d/Type/:id', {templateUrlWP: '/theme/angularjs/unicorn/Type-detail.html', controller: TypeCtrl}).
			/* Entity edit */
			when('/d/:typename', {templateUrlWP: '/theme/angularjs/unicorn/{{typename}}-list.html',   controller: EntityListCtrl}).
			when('/d/:typename/!new', {templateUrlWP: '/theme/angularjs/unicorn/{{typename}}-detail.html', controller: NewEntityCtrl}).
			when('/d/:typename/:id', {templateUrlWP: '/theme/angularjs/unicorn/{{typename}}-detail.html', controller: EntityCtrl}).
			when('/welcome', {templateUrl: '/theme/angularjs/unicorn/welcome.html', controller: DoNothingCtrl}).
			otherwise({redirectTo: '/welcome'});
	}])	.run(function($rootScope, $location,  $interpolate) {
	 	$rootScope.$on('$routeChangeStart', function(event,next,last) {
			if(next.$route && next.$route.templateUrlWP){
	 			next.$route.templateUrl = $interpolate(next.$route.templateUrlWP)(next.pathParams);
	 		}
	});
});


