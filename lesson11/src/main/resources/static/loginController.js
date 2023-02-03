app.controller("loginController", function($rootScope, $scope, $http, $location, $window) {
    const contextPath = 'http://localhost:8189/lesson11';

    $scope.$on('routeChangeStart', function(event, next, current) {
        if (typeof(current) !== 'undefined') {
            $templateCache.remove(next.templateUrl);
        }
    });


    var authenticate = function(username, password, callback) {

        $http({
            url: contextPath + '/api/v1/verification',
            method: 'POST',
            params: {
                username : $scope.username,
                password : $scope.password
                }
        }).then(function(response) {
            if (response.data) {
                var token = $window.btoa($scope.username + ':' + $scope.password);
                var userData = {
                    username: $scope.username,
                    authData: token
                }
                $window.sessionStorage.setItem('userData', JSON.stringify(userData));
                $http.defaults.headers.common['Authorization'] = 'Basic ' + token;
                $rootScope.authenticated = true;
                $location.path('/navigation');
            } else {
                $rootScope.authenticated = false;
            }
            callback && callback($rootScope.authenticated);
        }, function() {
            $rootScope.authenticated = false;
            callback && callback(false);
        });
    };

   authenticate();
   $scope.credentials = {};
   $scope.loginUser = function() {
   console.log("вызов авторизации");////////////////////////////////
//        authenticate($scope.credentials, function() {
        authenticate($scope.username, $scope.password, function() {
             if ($rootScope.authenticated) {
                 $location.path('/navigation');
                 $scope.error = false;
             } else {
                 $location.path('/login');
                 $scope.error = true;
             }
        })
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