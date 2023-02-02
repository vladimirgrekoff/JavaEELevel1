app.controller("usersController", function($scope, $http, $location, $templateCache) {
    const contextPath = 'http://localhost:8189/lesson11/api/v1';

    $scope.$on('routeChangeStart', function(event, next, current) {
        if (typeof(current) !== 'undefined') {
            $templateCache.remove(next.templateUrl);
        }
    });



    $scope.loadUsers = function () {
        $http.post(contextPath + '/users')
            .then(function (response) {
                $scope.UsersList = response.data.content;
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
            $location.path(response);///////////////////////
                $scope.loadUsers();
            });
    };

    $scope.showLoginPage = function () {
    console.log('перейти к форме');
        $location.path('login');///////////////////////
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