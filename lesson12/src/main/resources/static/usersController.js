app.controller("usersController", function($rootScope, $scope, $http, $location, $templateCache, $localStorage) {
    const contextPath = 'http://localhost:8189/lesson12/api/v1';

    $scope.$on('routeChangeStart', function(event, next, current) {
        if (typeof(current) != 'undefined') {
            $templateCache.remove(next.templateUrl);
        }
    });

    if ($localStorage.springWebUser) {
        $http.defaults.headers.common.Authorization;
    };


    $scope.loadUsers = function () {
        $http.get(contextPath + '/users')
            .then(function (response) {
                $scope.UsersList = response.data;
            });
    };

    $rootScope.hasRole = function(check) {
        var roles = $localStorage.currentRoles;
        if (roles != null) {
            for (i=0; i<roles.length; i++){
                if (check == roles[i].name) {
                    return true;
                }
            }
        }
        return false;
    };

    $scope.editUserProfile = function(id){
        $rootScope.idEditUserProfile = id;
        $location.path('authority');
    };

    $scope.allRolesToString = function(roles) {
        var delimiter = '';
        var string = '';

        for(i=0; i < roles.length; i++) {
            if(i == roles.length-1) {
                delimiter = '';
            } else {
                delimiter = '; ';
            }
            string = string + roles[i].name + delimiter;
        }

        return string;
    };

    $scope.rolesFromStringToRolesArray = function(){
        var rolesArray;
        var userRoles = new Array();
        if ($scope.rolesString.indexOf(';') > 0){
            rolesArray = $scope.rolesString.split(';');
        } else {
            rolesArray[0] = $scope.rolesString.trim();
        }
        for (i = 0; i < rolesArray.length; i++){
             userRoles[i] = {name : rolesArray[i].trim()};
             console.log(userRoles[i]);
        }
        $scope.newUser.roles = userRoles;
    };

    $scope.getDefaultRole = function(){
        var role = $localStorage.defaultRole;
        return role;
    };

    $scope.addNewUser = function () {
        var userRoles = new Array();
        userRoles[0] = $scope.getDefaultRole();
        $scope.newUser.roles = userRoles;
        $http.post(contextPath + '/users', $scope.newUser)
            .then(function (response) {
                $scope.newUser.username = null;
                $scope.newUser.password = null;
                $scope.newUser.email = null;
                $scope.newUser.roles = null;
                $scope.loadUsers();
            });
    };

    $scope.deleteUser = function (userId) {
        $http.delete(contextPath + '/users/' + userId)
            .then(function (response) {
                $scope.loadUsers();
            });
    };

    $scope.showNavigationPage = function () {
        $location.path('navigation');
    };

//    $scope.logout = function() {
//      $http.post('logout', {})
//      .then(function() {
////        $rootScope.authenticated = false;
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

    $scope.loadUsers();

});