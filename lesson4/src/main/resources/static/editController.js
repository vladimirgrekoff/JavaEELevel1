app.controller('editController', function ($scope, $http, $location, $templateCache) {
    const contextPath = 'http://localhost:8189/app';



    $scope.$on('routeChangeStart', function(event, next, current) {
        if (typeof(current) !== 'undefined') {
            $templateCache.remove(next.templateUrl);
        }
    });

    $scope.loadEditProducts = function () {
        $http.get(contextPath + '/edit_products')
            .then(function (response) {
                $scope.ProductsList = response.data;
            });
    };

    $scope.deleteProduct = function (productId){
        $http.get(contextPath + '/edit_products/delete/' + productId)
            .then(function (response) {
            $scope.productId = null;
            $scope.loadEditProducts();
        });
    };

    $scope.addProduct = function (index, id, title, cost){
            $http({
                url: contextPath + '/edit_products/add_product',
                method: 'GET',
                params: {
                    index: index,
                    id: id,
                    title: title,
                    cost: cost
                }
            }).then(function (response) {
                $scope.index = null;
                $scope.id = null;
                $scope.title = null;
                $scope.cost = null;
                $scope.loadEditProducts();
        });
    };

    $scope.changeScore = function (productId, delta) {
        $http({
            url: contextPath + '/edit_products/change_score',
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

    $scope.showCartPage = function() {
        $location.path("restore");
    };

    $scope.loadEditProducts();

});