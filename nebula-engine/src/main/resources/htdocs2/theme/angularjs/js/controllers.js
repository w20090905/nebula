/* Controllers */
function DoNothingCtrl($scope, $routeParams) {
	'use strict';
	$scope.$routeParams = $routeParams;
}
function AppControl($scope, $cookies, $location) {
	'use strict';
	$scope.base = $location;
	$scope.LoginUserID = $cookies.LoginUserID;
}

function TypeQuickEditCtrl($scope, $resource, $routeParams, $location) {

	'use strict';
	/*
	 * $scope.typename = $routeParams.typename; var DataResource =
	 * $scope.resource = $resource('/d/:typename/:id', $routeParams, {'save':
	 * {method: 'PUT'}}); $scope.data = DataResource.get($routeParams);
	 * $scope.update = true; $scope.$save = function () {
	 * $scope.data.$save(function (u, getResponseHeaders) {
	 * $location.url($interpolate('d/{{typename}}/')($routeParams)); }); };
	 * $scope.$back = function () {
	 * $location.url($interpolate('d/{{typename}}/')($routeParams)); };
	 */

	$scope.$addMenu = function(menuBar, menuName, menuIcon, menuUrl) {
		var MenuResource = $resource('/d/MenuBar/' + menuBar + '',
				$routeParams, {
					'save' : {
						method : 'PUT'
					}
				});
		var MenuData = MenuResource.get($routeParams);
		$.extend(MenuData, {
			Menu : [ {
				"Name" : menuName,
				"IconName" : "icon-adjust",
				"URL" : menuUrl
			} ]
		});
		MenuData.$save();
	}
}

function NewUserCtrl($scope, $resource, $routeParams, $location) {
	'use strict';
	$scope.typename = $routeParams.typename;
	var DataResource = $resource('/d/User/', $routeParams);
	$scope.data = {};
	$scope.data.$save = DataResource.prototype.$save;
	$scope.$save = function() {
		$scope.data.$save(function(u, getResponseHeaders) {
			$location.url("/login.html");
		});
	};
}

function EntityListCtrl($scope, $route, $resource, $routeParams) {
	'use strict';
	$scope.typename = $routeParams.typename;
	var DataResource = $resource('/d/:typename/', $routeParams, {
		query : {
			method : 'GET',
			params : {},
			isArray : true
		}
	});
	$scope.datalist = DataResource.query();
	$scope.$reload = function() {
		$scope.datalist = DataResource.query();
	};
}

function NewEntityCtrl($scope, $resource, $routeParams, $location, $interpolate) {
	'use strict';
	$scope.typename = $routeParams.typename;
	var DataResource = $resource('/d/:typename/', $routeParams);
	$scope.data = {};
	$scope.data.$save = DataResource.prototype.$save;
	$scope.$save = function() {
		$scope.data.$save(function(u, getResponseHeaders) {
			$location.url($interpolate('/d/{{typename}}/')($routeParams));
		});
	};
	$scope.$back = function() {
		$location.url($interpolate('/d/{{typename}}/')($routeParams));
	};
}

function EntityCtrl($scope, $resource, $routeParams, $location, $interpolate) {
	'use strict';
	$scope.typename = $routeParams.typename;
	var DataResource = $scope.resource = $resource('/d/:typename/:id',
			$routeParams, {
				'save' : {
					method : 'PUT'
				}
			});
	$scope.data = DataResource.get($routeParams);
	$scope.update = true;
	$scope.$save = function() {
		$scope.data.$save(function(u, getResponseHeaders) {
			$location.url($interpolate('/d/{{typename}}/')($routeParams));
		});
	};
	$scope.$back = function() {
		$location.url($interpolate('/d/{{typename}}/')($routeParams));
	};
}

function TypeListCtrl($scope, $route, $resource, $routeParams) {
	'use strict';
	$scope.typename = "Type";
	var DataResource = $resource('/d/Type/', $routeParams, {
		query : {
			method : 'GET',
			params : $routeParams,
			isArray : true
		}
	});
	$scope.datalist = DataResource.query();
}

function NewTypeCtrl($scope, $resource, $routeParams, $http, $location,
		$interpolate, $templateCache) {
	'use strict';
	$scope.typename = "Type";
	var DataResource = $resource('/d/Type/', $routeParams);
	$scope.data = {};
	$scope.data.$save = DataResource.prototype.$save;
	$scope.$save = function() {
		$http.post('/d/Type/', $scope.data.Code).success(
				function(data, status, headers, config) {
					$templateCache.removeAll();
					$location.url(data);
				});
	};
	$scope.$back = function() {
		$location.url($interpolate('d/Type/')($routeParams));
	};
}

function TypeCtrl($scope, $resource, $routeParams, $http, $location,
		$interpolate, $templateCache) {
	'use strict';
	$scope.typename = "Type";
	$scope.resourcename = $interpolate('/d/Type/{{id}}')($routeParams);
	var DataResource = $scope.resource = $resource('/d/Type/:id', $routeParams,
			{
				'save' : {
					method : 'PUT'
				}
			});
	$scope.data = DataResource.get($routeParams);
	$scope.update = true;
	$scope.$save = function() {
		$http.put($scope.resourcename, $scope.data.Code).success(
				function(data, status, headers, config) {
					$templateCache.removeAll();
					$scope.data = DataResource.get($routeParams);
				});
	};
	$scope.$back = function() {
		$location.url($interpolate('d/Type/')($routeParams));
	};
}

function AttributeEditCtrl($scope, $resource) {
	'use strict';
	$scope.$parent.$loadChild = function(name) {
		var AttributeDataResource = $scope.resource = $resource('/d/Attribute/'
				+ name, {}, {
			'save' : {
				method : 'PUT'
			}
		});
		$scope.entityData = {};
		$scope.entityData = AttributeDataResource.get({});
		$scope.update = true;
		$scope.$save = function() {
			$scope.entityData.$save(function(u, getResponseHeaders) {
				$scope.$parent.$reload();
				$scope.$parent.$loadChild(name);
			});
			return false;
		};
	};

	$scope.$parent.$newChild = function() {
		var AttributeDataResource = $resource('/d/Attribute/', {});
		$scope.entityData = {};
		$scope.update = false;
		$scope.entityData.Values = [];
		$scope.entityData.$save = AttributeDataResource.prototype.$save;
		$scope.$save = function() {
			$scope.entityData.$save(function(u, getResponseHeaders) {
				$scope.$parent.$reload();
				$scope.$parent.$newChild();
			});
		};
	};
	$scope.$parent.$newChild();
}

function extractParams(url, params) {
	'use strict';
	var newurl = url;
	angular.forEach(params || {}, function(value, key) {
		newurl = newurl.replace(":" + key, value);
	});
	return newurl;
}
function AngularJSCtrl($scope, $route, $location, $http, $routeParams,
		$templateCache) {
	'use strict';
	$scope.resourcename = extractParams(
			"/theme/:theme/:skin/:typename-:cat.html", $routeParams);
	$http.get($scope.resourcename).success(
			function(data, status, headers, config) {
				$scope.data = {
					code : data
				};
			});
	$scope.newcode = "";
	$scope.$save = function() {
		$http.put($scope.resourcename, $scope.data.code).success(
				function(data, status, headers, config) {
					$scope.data = {
						code : data
					};
					$templateCache.removeAll();
				});
	};
}

function FreeMarkerCtrl($scope, $route, $location, $http, $routeParams,
		$templateCache) {
	'use strict';
	$scope.resourcename = extractParams("/template/:typename-:cat.html.ftl",
			$routeParams);
	$http.get($scope.resourcename).success(
			function(data, status, headers, config) {
				$scope.data = {
					code : data
				};
			});
	$scope.newcode = "";
	$scope.$save = function() {
		$http.put($scope.resourcename, $scope.data.code).success(
				function(data, status, headers, config) {
					$scope.data = {
						code : data
					};
					$templateCache.removeAll();
				});
	};
}

Date.prototype.format = function(format) {
	'use strict';
	var o = {
		"M+" : this.getMonth() + 1, // month
		"d+" : this.getDate(), // day
		"h+" : this.getHours(), // hour
		"m+" : this.getMinutes(), // minute
		"s+" : this.getSeconds(), // second
		"q+" : Math.floor((this.getMonth() + 3) / 3), // quarter
		"S" : this.getMilliseconds()
	// millisecond
	};

	if (/(y+)/.test(format)) {
		format = format.replace(RegExp.$1, (this.getFullYear() + "")
				.substr(4 - RegExp.$1.length));
	}
	for ( var k in o) {
		if (new RegExp("(" + k + ")").test(format)) {
			format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k]
					: ("00" + o[k]).substr(("" + o[k]).length));
		}
	}
	return format;
}

function ContactRecordsCtrl($scope, $resource) {
	'use strict';
	$scope.data = {};

	$scope.companyList = $resource('/d/Company/?SCMRole=客户', {}, {
		query : {
			method : 'GET',
			params : {},
			isArray : true
		}
	}).query(function() {
		$scope.data.CompanyName = $scope.companyList[0].Name;
		$scope.refresh();
	});

	var DataResource = $resource('/d/ContactRecord/', {}, {
		query : {
			method : 'GET',
			params : {
				"CompanyName" : $scope.data.CompanyName
			},
			isArray : true
		}
	});

	$scope.getActive = function(v) {
		if (v == $scope.data.CompanyName)
			return "active";
		else
			return "";
	}

	$scope.refresh = function() {
		$scope.contactRecords = $resource('/d/ContactRecord/', {}, {
			query : {
				method : 'GET',
				params : {
					"CompanyName" : $scope.data.CompanyName
				},
				isArray : true
			}
		}).query();
	};

	$scope.$watch('data.CompanyName', function(newValue, oldValue) {
		if ($scope.data.CompanyName) {
			$scope.personList = $resource('/d/Person/', {}, {
				query : {
					method : 'GET',
					params : {
						"CompanyName" : $scope.data.CompanyName
					},
					isArray : true
				}
			}).query();
		}
	});

	$scope.remotedata = {};

	$scope.refresh();

	$scope.call = function(o) {
		alert(o);
	}

	$scope.remotedata.$save = DataResource.prototype.$save;

	$scope.addContactRecord = function() {
		$.extend($scope.data, {
			AutoID : new Date().getTime(),
			Done : false,
			LastUpdated : new Date().format("yyyy-MM-dd hh:mm:ss.S")
		});

		$.extend($scope.remotedata, $scope.data);
		$scope.remotedata.$save();

		$scope.contactRecords.push($scope.data);

		$scope.data = $.extend({}, $scope.data, {
			Content : ""
		});
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

	$scope.remove = function(AutoID) {
		var Record = $resource('/d/ContactRecord/:AutoID', {
			AutoID : '@AutoID'
		});
		var record = Record.get({
			AutoID : AutoID
		}, function() {
			record.$remove();
		});
	}
}

// PhoneDetailCtrl.$inject = ['$scope', '$routeParams', 'Phone'];
