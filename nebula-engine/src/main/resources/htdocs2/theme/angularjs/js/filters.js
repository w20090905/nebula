'use strict';

/* Filters */

angular.module('nebulaFilters', []).filter('checkmark', function() {
  return function(input) {
    return input ? '\u2713' : '\u2718';
  };
}).filter('replace', function() {
	  return function(input,regexp,replacement) {
		    return input.replace(regexp,replacement);
		  };
});
