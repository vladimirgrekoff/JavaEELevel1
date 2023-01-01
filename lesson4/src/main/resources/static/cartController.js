app.controller('cartController', function ($scope, $http, $location, $templateCache) {
    const contextPath = 'http://localhost:8189/app';


    $scope.loadCart = function () {
        $http.get(contextPath + '/restore')
            .then(function (response) {
                $scope.ProductsInCart = response.data;
            });
    };

    $scope.restoreProduct = function (findString){
        $http.get(contextPath + '/restore/' + findString)
            .then(function (response) {
            $scope.findString = null;
            $scope.loadCart();
        });
    };


    $scope.$on('routeChangeStart', function(event, next, current) {
        if (typeof(current) !== 'undefined') {
            $templateCache.remove(next.templateUrl);
        }
    });

    $scope.showEditPage = function() {
        $location.path("edit_products");
    };

    $scope.loadCart();

});