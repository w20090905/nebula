'use strict';

/* App Module */
angular.module('appAdmin', ['nebulaFilters', 'nebulaServices','nebulaDirectives','angularTree','ngResource','ngCookies']).
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
			when('/d/:attachedtypename/:attachedid/:typename/', {templateUrlWP: '/theme/angularjs/unicorn/{{attachedtypename}}-{{typename}}-basic-list.html',   controller: AttachedEntityListCtrl}).
			when('/d/:typename/!new', {templateUrlWP: '/theme/angularjs/unicorn/{{typename}}-home.html', controller: NewEntityCtrl}).
			when('/d/:typename/:id', {templateUrlWP: '/theme/angularjs/unicorn/{{typename}}-home.html', controller: EntityCtrl}).
			when('/welcome', {templateUrl: '/theme/angularjs/unicorn/welcome.html', controller: DoNothingCtrl}).
			otherwise({redirectTo: '/welcome'});
	}])	.run(function($rootScope, $location,  $interpolate) {
	 	$rootScope.$on('$routeChangeStart', function(event,next,last) {
			if(next.$route && next.$route.templateUrlWP){
	 			next.$route.templateUrl = $interpolate(next.$route.templateUrlWP)(next.pathParams);
	 		}
	});
});


(function($) { 
	$("input[required]").parents(".control-group").find(".control-label").addClass("required");
})(jQuery);

