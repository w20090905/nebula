'use strict';

/* Controllers */


function MyCtrl1() {}
MyCtrl1.$inject = [];


function MyCtrl2() {
}
MyCtrl2.$inject = [];



function MenuCtrl($scope,$resource){
	var DataResource = $resource('e/Menu/list.json', {}, {
    query: {method:'GET', params:{}, isArray:true}
  });	
	$scope.datalist = DataResource.query();
}

function EntityListCtrl($scope,$route,$resource,$routeParams){
	$scope.typename = $routeParams.typename;	
	var DataResource = $resource('e/:typename/list.json', $routeParams, {
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