app.controller('productsController', function ($scope, $http, $location, $templateCache) {
    const contextPath = 'http://localhost:8189/lesson8';


    $scope.$on('routeChangeStart', function(event, next, current) {
        if (typeof(current) !== 'undefined') {
            $templateCache.remove(next.templateUrl);
        }
    });

    $scope.loadProducts = function () {
        $http.get(contextPath + '/products')
            .then(function (response) {
                $scope.ProductsList = response.data;
            });
    };

    $scope.loadPagesProducts = function (offset, limit) {
            $http({
                url: contextPath + '/products/pages',
                method: 'GET',
                params: {
                    offset: offset,
                    limit: limit
                }
            }).then(function (response) {
                $scope.offset = null;
                $scope.limit = null;
                $scope.ProductsList = response.data.content;
        });
    };

    $scope.showEditPage = function() {
        $location.path("edit");
    };

    $scope.showSearchPage = function() {
        $location.path("search");
    };

    $scope.loadProducts();

});