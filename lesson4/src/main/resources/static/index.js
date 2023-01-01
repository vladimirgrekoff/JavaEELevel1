var app = angular.module('app', ['ngRoute'])
    .config(function ($routeProvider) {
    $routeProvider.when('/products',
    {
    templateUrl:'productsTemplate.html',
    controller:'productsController',
    controllerAs:'products'
    })
    .when('/edit_products',
    {
    templateUrl:'editProductsTemplate.html',
    controller:'editController',
    controllerAs:'edit_products'
    })
    .when('/restore',
    {
    templateUrl:'cartTemplate.html',
    controller:'cartController',
    controllerAs:'restore'
    })
    .otherwise({
    redirectTo: '/products'
    });

});