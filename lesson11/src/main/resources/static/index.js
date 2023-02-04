var app = angular.module('lesson11', ['ngRoute'])
    .config(function ($routeProvider, $httpProvider) {

    $routeProvider.when('/welcome',
    {
    templateUrl:'welcomeTemplate.html',
    controller:'welcomeController',
    controllerAs:'welcome'
    })
    .when('/login',
    {
    templateUrl:'loginTemplate.html',
    controller:'loginController',
    controllerAs:'login'
    })
    .when('/navigation',
    {
    templateUrl:'navigationTemplate.html',
    controller:'navigationController',
    controllerAs:'navigation'
    })
    .when('/cart',
    {
    templateUrl:'cartTemplate.html',
    controller:'cartController',
    controllerAs:'cart'
    })
    .when('/products',
    {
    templateUrl:'productTemplate.html',
    controller:'productController',
    controllerAs:'products'
    })
    .when('/users',
    {
    templateUrl:'usersTemplate.html',
    controller:'usersController',
    controllerAs:'users'
    })
    .otherwise({
    redirectTo: 'welcome'
    });

    $httpProvider.defaults.headers.common["X-Requested-With"] = 'XMLHttpRequest';

});