'use strict';

/* Controllers */


function MyCtrl1() {}
MyCtrl1.$inject = [];


function MyCtrl2() {
}
MyCtrl2.$inject = [];



function MainCtrl($scope,$route){
	$scope.$route = $route.current;
}


function MenuCtrl($scope,$resource){
	var DataResource = $resource('e/Menu/list.json', {}, {
    query: {method:'GET', params:{}, isArray:true}
  });	
	$scope.datalist = DataResource.query();
}


function EntityListCtrl($scope,$route,$resource,$routeParams){
	$scope.typename = $routeParams.typename;	
	var DataResource = $resource('d/:typename/', $routeParams, {
    query: {method:'GET', params:{}, isArray:true}
  });
	$scope.datalist = DataResource.query({},$scope.showme,$scope.showme);
}

function EntityCtrl($scope,$route,$resource,$routeParams){
	$scope.$route = $route;
	$scope.typename = $routeParams.typename;	
	$scope.id = $routeParams.id;
	var DataResource = $scope.resource = $resource('d/:typename/:id',$routeParams);	
	$scope.data = DataResource.get($routeParams,$scope.showme,$scope.showme);
}

function AngularJSCtrl($scope,$route,$location,$http,$routeParams){
	$scope.resourcename = extractParams("angularjs/:typename-:cat.html",$routeParams);
	$http.get($scope.resourcename ).success(function(data, status, headers, config){
		$scope.code=data;
		$scope.showme();
	});
	$scope.newcode = "";
	$scope.cur=$scope;
	$scope.save = function(){
		$scope.$eval(function(scope){
			scope.newcode = scope.code;
//			$http.put($scope.resourcename,$scope.code).success(function(data, status, headers, config){
//				$scope.code=data;
//			});
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

