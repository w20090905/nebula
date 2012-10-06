'use strict';

/* App Module */
angular.module('nebula', ['nebulaFilters', 'nebulaServices','nebulaDirectives','ngResource']).
	config(['$routeProvider', function($routeProvider,$rootScope) {
		$routeProvider.
			when('/t/template/:typename-:cat.html.ftl', {template: 'angularjs/angularjs-edit.html', controller: FreeMarkerCtrl}).
			when('/t/:TemplateType/:typename-:cat.html', {template: 'angularjs/angularjs-edit.html', controller: AngularJSCtrl}).
			when('/d/:typename', {templateURL: 'angularjs/{{typename}}-list.html',   controller: EntityListCtrl}).
			when('/d/:typename/!new', {templateURL: 'angularjs/{{typename}}-detail.html', controller: NewEntityCtrl}).
			when('/d/:typename/:id', {templateURL: 'angularjs/{{typename}}-detail.html', controller: EntityCtrl}).
			when('/welcome', {templateURL: 'angularjs/welcome.html', controller: DoNothingCtrl}).
			otherwise({redirectTo: '/d/Type'});
	}]).
	run(function($rootScope, $location,  $interpolate) {
	 	$rootScope.$on('$beforeRouteChange', function(event,next,last) {
	 		if(next.$route.templateURL){
	 			next.$route.template = $interpolate(next.$route.templateURL)(next.pathParams);
	 		}
	});
});
