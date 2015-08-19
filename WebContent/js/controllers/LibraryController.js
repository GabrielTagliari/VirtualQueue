
var M4M = angular.module('m4mApp', []);


M4M.controller('LibraryController', ['$scope', '$http', function($scope,$http) {
	
	
	$http.get('http://localhost:9000/Book').then(function(result) {
	$scope.books = result.data;
	}); 
	
	
}]);

