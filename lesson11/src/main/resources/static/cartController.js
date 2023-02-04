app.controller("cartController", function($scope, $http, $location, $templateCache) {
    const contextPath = 'http://localhost:8189/lesson11/api/v1';

    $scope.$on('routeChangeStart', function(event, next, current) {
        if (typeof(current) !== 'undefined') {
            $templateCache.remove(next.templateUrl);
        }
    });

    $scope.showNavigationPage = function () {
    console.log('перейти на страницу навигации');///////////////////////
        $location.path('navigation');
    };



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



    $scope.loadProductsInCart = function () {
            $http.get(contextPath + '/cart')
                .then(function (response) {
                $scope.ProductsInCart = response.data;
        });
    };



    $scope.updateProduct = function (product, delta) {
        $http.put(contextPath + '/cart', product)
            .then(function (response) {
                product = null;
                $scope.loadProductsInCart();
            });
    };

    $scope.addProductToCart = function (product) {
        $http.post(contextPath + '/cart', product)
            .then(function (response) {
                product = null;
                $scope.loadProductsInCart();
            });
    };

    $scope.deleteProductFromCart = function (productId) {
        $http.delete(contextPath + '/cart/' + productId)
            .then(function (response) {
                $scope.loadProductsInCart();
            });
    };

    $scope.clearCart = function () {
        $http.delete(contextPath + '/cart')
            .then(function (response) {
                $scope.loadProductsInCart();
            });
    };

    $scope.logout = function() {
      $http.post('logout', {}).then(function() {
        $rootScope.authenticated = false;
        $location.path('/welcome');
      }).error(function(data) {
        $rootScope.authenticated = false;
      });
    }

    $scope.loadProducts();
    $scope.loadProductsInCart();

});