'use strict';


// Declare app level module which depends on filters, and services
angular.module('nebula', ['nebula.filters', 'nebula.services', 'nebula.directives','ngResource']).
  config(['$routeProvider', function($routeProvider) {
	$routeProvider.when('/t/:TemplateType/:typename-:cat.html', {template: 'angularjszice/angularjszice-edit.html', controller: AngularJSCtrl});
	$routeProvider.when('/t/template/:typename-:cat.html.ftl', {template: 'angularjszice/angularjszice-edit.html', controller: FreeMarkerCtrl});
	$routeProvider.when('/d/:typename', {template: 'angularjszice/{{typename}}-list.html',   controller: EntityListCtrl});
	$routeProvider.when('/d/:typename/!new', {template: 'angularjszice/{{typename}}-detail.html', controller: NewEntityCtrl});
    $routeProvider.when('/d/:typename/:id', {template: 'angularjszice/{{typename}}-detail.html', controller: EntityCtrl});
    $routeProvider.when('/view1', {template: 'angularjszice/partial1.html', controller: MyCtrl1});
    $routeProvider.when('/view2', {template: 'angularjszice/partial2.html', controller: MyCtrl2});
    $routeProvider.otherwise({redirectTo: '/view1'});
  }]);
