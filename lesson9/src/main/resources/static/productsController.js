app.controller('productsController', function ($scope, $http, $location, $templateCache) {
    const contextPath = 'http://localhost:8189/lesson8';



    $scope.loadProducts = function (offset, limit) {
            $http({
                url: contextPath + '/products/pages',
                method: 'GET',
                params: {

                part_title: $scope.filter ? $scope.filter.part_title : null,
                min_price: $scope.filter ? $scope.filter.min_price : null,
                max_price: $scope.filter ? $scope.filter.max_price : null
                    offset: offset,
                    limit: limit
                }
            }).then(function (response) {
                $scope.offset = null;
                $scope.limit = null;
                $scope.ProductsList = response.data.content;
        });
    };

    $scope.createProductJson = function () {
        console.log($scope.newProductJson);/////////////////////////////////
        $http.post(contextPath + '/students', $scope.newProductJson)
            .then(function (response) {
                $scope.loadProducts();
            });
    };

    $scope.deleteProduct = function (productId) {
        $http.delete(contextPath + '/products/' + productId)
            .then(function (response) {
                $scope.loadProducts();
            });
    };

    $scope.loadProducts();

});