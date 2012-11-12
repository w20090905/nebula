'use strict';

/* Controllers */
function DoNothingCtrl($scope, $routeParams) {
	$scope.$routeParams = $routeParams;
}
function AppControl($scope,$cookies){
	$scope.LoginUserID = $cookies.LoginUserID;
}

function MenuCtrl($scope,$resource){
	var DataResource = $resource('e/menu/list.json', {}, {
    query: {method:'GET', params:{}, isArray:true}
  });	
	$scope.datalist = DataResource.query();
}

function EntityListCtrl($scope,$route,$resource,$routeParams){
	$scope.typename = $routeParams.typename;
	var DataResource = $resource('d/:typename/', $routeParams, {
		query: {method:'GET', params:{}, isArray:true}
	});
	$scope.datalist = DataResource.query();
}

function NewEntityCtrl($scope,$resource,$routeParams,$location,$interpolate){
	$scope.typename = $routeParams.typename;
	var DataResource = $resource('d/:typename/', $routeParams);
	$scope.data = {};
	$scope.data.$save = DataResource.prototype.$save;
	$scope.$save = function(){
		$scope.data.$save(function(u, getResponseHeaders){
			$location.url($interpolate('d/{{typename}}/')($routeParams));
		});
	};	
}


function NewUserCtrl($scope,$resource,$routeParams,$location,$interpolate){
	$scope.typename = $routeParams.typename;
	var DataResource = $resource('d/User/', $routeParams);
	$scope.data = {};
	$scope.data.$save = DataResource.prototype.$save;
	$scope.$save = function(){
		$scope.data.$save(function(u, getResponseHeaders){
			$location.url("/login.html");
		});
	};	
}

function EntityCtrl($scope,$resource,$routeParams,$location,$interpolate){
	$scope.typename = $routeParams.typename;
	var DataResource = $scope.resource = $resource('d/:typename/:id',$routeParams,{'save':   {method:'PUT'}});	
	$scope.data = DataResource.get($routeParams);
	$scope.update = true;
	$scope.$save = function(){
		$scope.data.$save(function(u, getResponseHeaders){
			$location.url($interpolate('d/{{typename}}/')($routeParams));
		});
	};
	
}

function extractParams(url,params){
    var newurl = url;
    angular.forEach(params || {}, function(value, key){
    	newurl = newurl.replace(":" + key , value);
    });
    return newurl;
}
function AngularJSCtrl($scope,$route,$location,$http,$routeParams,$templateCache){
	$scope.resourcename = extractParams(":TemplateType/:typename-:cat.html",$routeParams);
	$http.get($scope.resourcename ).success(function(data, status, headers, config){
		$scope.data = {code:data};
	});
	$scope.newcode = "";
	$scope.$save = function(){
		$http.put($scope.resourcename,$scope.data.code).success(function(data, status, headers, config){
			$scope.data = {code:data};
			$templateCache.removeAll();
		});
	};
}


function FreeMarkerCtrl($scope,$route,$location,$http,$routeParams,$templateCache){
	$scope.resourcename = extractParams("template/:typename-:cat.html.ftl",$routeParams);
	$http.get($scope.resourcename ).success(function(data, status, headers, config){
		$scope.data = {code:data};
	});
	$scope.newcode = "";
	$scope.$save = function(){
		$http.put($scope.resourcename,$scope.data.code).success(function(data, status, headers, config){
			$scope.data = {code:data};
			$templateCache.removeAll();
		});
	};
}


Date.prototype.format = function(format)
{
	var o = {
		"M+" : this.getMonth() + 1, // month
		"d+" : this.getDate(), // day
		"h+" : this.getHours(), // hour
		"m+" : this.getMinutes(), // minute
		"s+" : this.getSeconds(), // second
		"q+" : Math.floor((this.getMonth() + 3) / 3), // quarter
		"S" : this.getMilliseconds()
	// millisecond
	}

	if (/(y+)/.test(format))
	{
		format = format.replace(RegExp.$1, (this.getFullYear() + "")
				.substr(4 - RegExp.$1.length));
	}
	for ( var k in o)
	{
		if (new RegExp("(" + k + ")").test(format))
		{
			format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k]
					: ("00" + o[k]).substr(("" + o[k]).length));

		}
	}
	return format;
}

function ContactRecordsCtrl($scope,$resource) {
	$scope.data={};

	$scope.companyList = $resource('d/Company/', {}, {
		query : {
			method : 'GET',
			params : {},
			isArray : true
		}
	}).query();

	$scope.personList = $resource('d/Person/', {}, {
		query : {
			method : 'GET',
			params : {},
			isArray : true
		}
	}).query();
	
	
	var DataResource = $resource('d/ContactRecord/', {}, {
		query : {
			method : 'GET',
			params : {},
			isArray : true
		}
	});

	$scope.refresh = function(){
		$scope.contactRecords=$resource('d/ContactRecord/', {}, {
			query : {
				method : 'GET',
				params : {"CompanyName" : $scope.data.CompanyName},
				isArray : true
			}
		}).query();	
	};

	
	
	$scope.remotedata = {};
	
	$scope.refresh();

	$scope.call = function(o){
		alert(o);
	}
	

	$scope.remotedata.$save = DataResource.prototype.$save;
	
	$scope.addContactRecord = function() {
		$.extend($scope.data,{
			AutoID:new Date().format("yyyyMMddhhmmssS"),
			Done : false,
			LastUpdated : new Date().format("yyyy-MM-dd hh:mm:ss.S")
		});

		$.extend($scope.remotedata,$scope.data);		
		$scope.remotedata.$save();
		
		$scope.contactRecords.push($scope.data);
		
		$scope.data=$.extend({},$scope.data,{Content:""});
	};

	$scope.remaining = function() {
		var count = 0;
		angular.forEach($scope.contactRecords, function(contactRecord) {
			count += contactRecord.Done ? 0 : 1;
		});
		return count;
	};

	$scope.archive = function() {
		var oldContactRecords = $scope.contactRecords;
		$scope.contactRecords = [];
		angular.forEach(oldContactRecords, function(contactRecord) {
			if (!contactRecord.Done)
				$scope.contactRecords.push(contactRecord);
		});
	};
	
	$scope.remove = function(AutoID){
		var Record = $resource('d/ContactRecord/:AutoID', {AutoID:'@AutoID'});
	    var record = Record.get({AutoID:AutoID}, function() {
	    	 record.$remove();
	    });
	}
}

//PhoneDetailCtrl.$inject = ['$scope', '$routeParams', 'Phone'];