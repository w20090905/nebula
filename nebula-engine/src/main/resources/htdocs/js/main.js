(function($){
	/** angular start **/
	$.require("lib/angular/angular.js");
	$.require("lib/angular/angular-resource.js");
	$.require("js/services.js");
	$.require("js/controllers.js");
	$.require("js/filters.js");
	$.require("js/directives.js");
	$.require("js/nebula.js");
	/** angular end **/

	/** codemirror start **/
	$.require("lib/codemirror/lib/codemirror.js");
	$.require("lib/codemirror/mode/javascript/javascript.js");
	$.require("lib/codemirror/mode/htmlmixed/htmlmixed.js");
	$.require("lib/codemirror/mode/nebula/nebula.js");
	/** codemirror end **/	
})(jQuery);