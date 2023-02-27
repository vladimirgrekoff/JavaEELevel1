app.controller("welcomeController", function($rootScope, $scope, $http, $location, $templateCache) {
    const contextPath = 'http://localhost:8189/lesson12/api/v1';

    $scope.$on('routeChangeStart', function(event, next, current) {
        if (typeof(current) != 'undefined') {
            $templateCache.remove(next.templateUrl);
        }
    });

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

    $scope.showLoginPage = function () {
    console.log('перейти к форме');///////////////////////
        $location.path('login');
    };

    $scope.showRegistrationPage = function () {
    console.log('перейти к форме');///////////////////////
        $location.path('registration');
    };

//    $scope.logout = function() {
//      $http.post('logout', {})
//      .then(function() {
//        $rootScope.authenticated = false;
//        $location.path('welcome');
//      });
//    };

    $scope.tryLogout = function() {
        if($rootScope.logout){
            $rootScope.logout();
        } else {
            $location.path('welcome')
        }
    };



    $scope.loadProducts();

});