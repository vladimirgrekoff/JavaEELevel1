angular.module('lesson9', []).controller("indexController", function($scope, $http) {
    const contextPath = 'http://localhost:8189/lesson9/api/v1';



    $scope.loadProducts = function (offset, limit) {
            $http({
                url: contextPath + '/products',
                method: 'GET',
                params: {
                    min_price: $scope.filter ? $scope.filter.min_price : null,
                    max_price: $scope.filter ? $scope.filter.max_price : null,
                    part_title: $scope.filter ? $scope.filter.part_title : null,
                    offset: offset,
                    limit: limit
                }
            }).then(function (response) {
                $scope.ProductsList = response.data.content;
        });
    };


    $scope.updateProduct = function (product, delta) {
        product.price = product.price + delta;
        $http.put(contextPath + '/products', product)
            .then(function (response) {
                product = null;
                $scope.loadProducts();
            });
    };

    $scope.addNewProduct = function () {
        $http.post(contextPath + '/products', $scope.newProduct)
            .then(function (response) {
                $scope.newProduct.title = null;
                $scope.newProduct.price = null;
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