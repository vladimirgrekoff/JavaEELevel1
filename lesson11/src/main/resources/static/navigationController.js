app.controller("navigationController", function($rootScope, $scope, $http, $location, $window) {
    const contextPath = 'http://localhost:8189/lesson11';

    $scope.$on('routeChangeStart', function(event, next, current) {
        if (typeof(current) !== 'undefined') {
            $templateCache.remove(next.templateUrl);
        }
    });


    $scope.showProfilePage = function () {
    console.log('перейти к профилю');
        $location.path('profile');///////////////////////
    };

    $scope.showCartPage = function () {
    console.log('перейти к выбору продуктов');
        $location.path('cart');///////////////////////
    };

    $scope.showProductsPage = function () {
    console.log('перейти к правке списка продуктов');
        $location.path('products');///////////////////////
    };

    $scope.showUsersPage = function () {
    console.log('перейти к правке списка пользователей');
        $location.path('users');///////////////////////
    };


    $scope.logout = function() {
      $http.post('logout', {}).then(function() {
        $rootScope.authenticated = false;
        $location.path('welcome');
      }).error(function(data) {
        $rootScope.authenticated = false;
      });
    }
});