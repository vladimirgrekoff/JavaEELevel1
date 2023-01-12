app.controller('searchController', function ($scope, $http, $location, $templateCache) {
    const contextPath = 'http://localhost:8189/lesson8';


    $scope.$on('routeChangeStart', function(event, next, current) {
        if (typeof(current) !== 'undefined') {
            $templateCache.remove(next.templateUrl);
        }
    });

    $scope.loadSearchProducts = function () {
        $http.get(contextPath + '/search')
            .then(function (response) {
                $scope.ProductsList = response.data;
            });
    };

    $scope.searchByMinPrice = function(min) {
        $http({
            url: contextPath + '/search/find_by_min',
            method: 'GET',
            params:{
            min: min
            }
        }).then(function(response){
            $scope.min = null;
            $scope.ProductsList = response.data;
        });
    };

    $scope.searchByMaxPrice = function(max) {
        $http({
            url: contextPath + '/search/find_by_max',
            method: 'GET',
            params:{
            max: max
            }
        }).then(function(response){
            $scope.max = null;
            $scope.ProductsList = response.data;
        });
    };

    $scope.searchByBetweenPrice = function (minPrice, maxPrice){
            $http({
                url: contextPath + '/search/find_between_price',
                method: 'GET',
                params: {
                    minPrice: minPrice,
                    maxPrice: maxPrice
                }
            }).then(function (response) {
                $scope.minPrice = null;
                $scope.maxPrice = null;
                $scope.ProductsList = response.data;
        });
    };

    $scope.showProductsPage = function() {
        $location.path("products");
    };

    $scope.loadSearchProducts();

});