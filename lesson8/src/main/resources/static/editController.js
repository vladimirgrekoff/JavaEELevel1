app.controller('editController', function ($scope, $http, $location, $templateCache) {
    const contextPath = 'http://localhost:8189/lesson8';


    $scope.$on('routeChangeStart', function(event, next, current) {
        if (typeof(current) !== 'undefined') {
            $templateCache.remove(next.templateUrl);
        }
    });

    $scope.loadEditProducts = function () {
        $http.get(contextPath + '/edit')
            .then(function (response) {
                $scope.ProductsList = response.data;
            });
    };

    $scope.deleteProduct = function (productId){
        $http.get(contextPath + '/edit/delete/' + productId)
            .then(function (response) {
            $scope.productId = null;
            $scope.loadEditProducts();
        });
    };

    $scope.addProduct = function (title, price){
            $http({
                url: contextPath + '/edit/add_product',
                method: 'GET',
                params: {
                    title: title,
                    price: price
                }
            }).then(function (response) {
                $scope.title = null;
                $scope.price = null;
                $scope.loadEditProducts();
        });
    };

    $scope.changePrice = function (productId, delta) {
        $http({
            url: contextPath + '/edit/change_price',
            method: 'GET',
            params: {
                productId: productId,
                delta: delta
            }
        }).then(function (response){
            $scope.productId = null;
            $scope.delta = null;
            $scope.loadEditProducts();
        });
    };

    $scope.showProductsPage = function() {
        $location.path("products");
    };

    $scope.loadEditProducts();

});