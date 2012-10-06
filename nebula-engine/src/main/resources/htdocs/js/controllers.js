'use strict';

/* Controllers */


function MyCtrl1() {}
MyCtrl1.$inject = [];


function MyCtrl2() {
}
MyCtrl2.$inject = [];

/**
 * @returns interpolation of the redirect path with the parametrs
 */
function interpolate(string, params) {
  var result = [];
  angular.forEach((string||'').split(':'), function(segment, i) {
    if (i == 0) {
      result.push(segment);
    } else {
      var segmentMatch = segment.match(/(\w+)(.*)/);
      var key = segmentMatch[1];
      result.push(params[key]);
      result.push(segmentMatch[2] || '');
      delete params[key];
    }
  });
  return result.join('');
}


function MainCtrl($scope,$route){
	$scope.$route = $route.current;
}


function MenuCtrl($scope,$resource){
	var DataResource = $resource('e/Menu/list.json', {}, {
    query: {method:'GET', params:{}, isArray:true}
  });	
	$scope.datalist = DataResource.query();
}


function CrmMainListCtrl($scope, $route, $resource, $routeParams) {
	$scope.typename = $routeParams.typename;
	var DataResource = $resource('d/Contact/', $routeParams, {
		query : {
			method : 'GET',
			params : {},
			isArray : true
		}
	});
	$scope.datalist = DataResource.query({}, $scope.showme, $scope.showme);

}


function ContactRecordsCtrl($scope,$resource) {

	$scope.companyList = $resource('d/Company/', {}, {
		query : {
			method : 'GET',
			params : {},
			isArray : true
		}
	}).query();

	$scope.userList = $resource('d/User/', {}, {
		query : {
			method : 'GET',
			params : {},
			isArray : true
		}
	}).query();
	
	$scope.contactRecords = [ {
		CompanyName: "test",
		UserName: "test",
		Content : 'learn angular',
		Done : true,
		LastUpdated : new Date()
	}, {
		CompanyName: "test",
		UserName: "test",
		Content : 'build an angular app',
		Done : false,
		LastUpdated : new Date()
	} ];

	$scope.addContactRecord = function() {
		if ($scope.Content != '') {
			$scope.contactRecords.push({
				CompanyName: $scope.Company.Name,
				UserName: $scope.User.Name,
				Content : $scope.Content,
				Done : false,
				LastUpdated : new Date()
			});
			$scope.Content = '';
		}
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
}

function EntityListCtrl($scope,$route,$resource,$routeParams){
	$scope.typename = $routeParams.typename;	
	var DataResource = $resource('d/:typename/', $routeParams, {
    query: {method:'GET', params:{}, isArray:true}
  });
	$scope.datalist = DataResource.query({},$scope.showme,$scope.showme);
}

function NewEntityCtrl($scope,$route,$resource,$routeParams,$location){
	$scope.typename = $routeParams.typename;	
	var DataResource = $resource('d/:typename/', $routeParams);
	$scope.data = {};
	$scope.data.$save = DataResource.prototype.$save;

	$scope.$save = function(){
		$scope.data.$save(function(u, getResponseHeaders){
			var url = interpolate('d/:typename/', $routeParams);
			$location.url(url);
		});
	};	
	$scope.showme();
	
	
}

function EntityCtrl($scope,$route,$resource,$routeParams,$location){
	$scope.$route = $route;
	$scope.typename = $routeParams.typename;	
	$scope.id = $routeParams.id;
	var DataResource = $scope.resource = $resource('d/:typename/:id',$routeParams,{'save':   {method:'PUT'}});	
	$scope.data = DataResource.get($routeParams,$scope.showme,$scope.showme);
	$scope.update = true;
	$scope.$save = function(){
		$scope.data.$save(function(u, getResponseHeaders){
			var url = interpolate('d/:typename/', $routeParams);
			$location.url(url);
		});
	};
	
}

function AngularJSCtrl($scope,$route,$location,$http,$routeParams,$templateCache){
	$scope.resourcename = extractParams(":TemplateType/:typename-:cat.html",$routeParams);
	$http.get($scope.resourcename ).success(function(data, status, headers, config){
		$scope.data = {code:data};
		$scope.showme();
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
		$scope.showme();
	});
	$scope.newcode = "";
	$scope.$save = function(){
//		$scope.$apply(function() {
//			$scope.newcode = $scope.dcode;
			$http.put($scope.resourcename,$scope.data.code).success(function(data, status, headers, config){
				$scope.data = {code:data};
				$templateCache.removeAll();
			});
//		});
	};
}

function extractParams(url,params){
    var newurl = url;
    angular.forEach(params || {}, function(value, key){
    	newurl = newurl.replace(":" + key , value);
    });
    return newurl;
}

