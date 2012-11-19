'use strict';

/* App Module */
angular.module('appAdmin', ['nebulaFilters', 'nebulaServices','nebulaDirectives','ngResource','ngCookies']).
	config(['$routeProvider', function($routeProvider) {
		$routeProvider.
			when('/t/template/:typename-:cat.html.ftl', {templateUrl: 'angularjs/angularjs-edit.html', controller: FreeMarkerCtrl}).
			when('/t/:TemplateType/:typename-:cat.html', {templateUrl: 'angularjs/angularjs-edit.html', controller: AngularJSCtrl}).
			when('/d/:typename', {templateUrlWP: 'angularjs/{{typename}}-list.html',   controller: EntityListCtrl}).
			when('/d/:typename/!new', {templateUrlWP: 'angularjs/{{typename}}-detail.html', controller: NewEntityCtrl}).
			when('/d/:typename/:id', {templateUrlWP: 'angularjs/{{typename}}-detail.html', controller: EntityCtrl}).
			when('/welcome', {templateUrl: 'angularjs/welcome.html', controller: DoNothingCtrl}).
			otherwise({redirectTo: '/d/Type'});
	}])	.run(function($rootScope, $location,  $interpolate) {
	 	$rootScope.$on('$routeChangeStart', function(event,next,last) {
	 		if(next.$route.templateUrlWP){
	 			next.$route.templateUrl = $interpolate(next.$route.templateUrlWP)(next.pathParams);
	 		}
	});
});


