var app = angular.module('lesson8', ['ngRoute'])
    .config(function ($routeProvider) {
    $routeProvider.when('/products',
    {
    templateUrl:'productsTemplate.html',
    controller:'productsController',
    controllerAs:'products'
    })
    .when('/edit',
    {
    templateUrl:'editProductsTemplate.html',
    controller:'editController',
    controllerAs:'edit'
    })
    .when('/search',
    {
    templateUrl:'searchTemplate.html',
    controller:'searchController',
    controllerAs:'search'
    })
    .otherwise({
    redirectTo: '/products'
    });

});