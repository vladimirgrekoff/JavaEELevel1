app.controller("usersController", function($scope, $http, $location, $templateCache) {
    const contextPath = 'http://localhost:8189/lesson11/api/v1';

    $scope.$on('routeChangeStart', function(event, next, current) {
        if (typeof(current) !== 'undefined') {
            $templateCache.remove(next.templateUrl);
        }
    });



    $scope.loadUsers = function () {
        $http.get(contextPath + '/users')
            .then(function (response) {
            console.log('ответ: ' + response.data);//////////////////////////////////////
                $scope.UsersList = response.data;
            });
    };




    $scope.addNewUser = function () {
        $http.post(contextPath + '/users', $scope.newUser)
            .then(function (response) {
                $scope.newUser.username = null;
                $scope.newUser.password = null;
                $scope.newUser.email = null;
                $scope.loadUsers();
            });
    };

    $scope.deleteUser = function (userId) {
        $http.delete(contextPath + '/users/' + userId)
            .then(function (response) {
            console.log('удаление пользователя');///////////////////////
                $scope.loadUsers();
            });
    };

    $scope.showNavigationPage = function () {
    console.log('перейти на страницу навигации');///////////////////////
        $location.path('navigation');
    };

    $scope.logout = function() {
      $http.post('logout', {})
      .then(function() {
//        $rootScope.authenticated = false;
        $location.path('welcome');
      });
    };

    $scope.loadUsers();

});