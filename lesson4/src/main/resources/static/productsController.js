app.controller('productsController', function ($scope, $http, $location, $templateCache) {
    const contextPath = 'http://localhost:8189/app';

    $scope.loadProducts = function () {
        $http.get(contextPath + '/products')
            .then(function (response) {
                $scope.ProductsList = response.data;
            });
    };

    $scope.showEditPage = function() {
        $location.path("edit_products");
    };

    $scope.$on('routeChangeStart', function(event, next, current) {
        if (typeof(current) !== 'undefined') {
            $templateCache.remove(next.templateUrl);
        console.log(next);
        console.log(current);
        }
    });

    $scope.loadProducts();

});