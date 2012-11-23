'use strict';
/*utility */
function isUndefined(value) {
	return typeof value == 'undefined';
}
function isEmpty(value) {
	return isUndefined(value) || value === '' || value === null
			|| value !== value;
}

angular.module('nebulaDirectives', []);/*
	.directive('popup', neFromListDirective);*/
