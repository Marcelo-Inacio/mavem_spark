FatecControllers.controller('LogoutController',
    ['$scope', '$routeParams', 'AuthenticationService', 'localStorageService', '$cookies',
        function ($scope, $routeParams, authenticationService, localStorageService, $cookies) {

            init();

            function init() {
                _logout();
            }

            function _logout() {
                localStorageService.clearAll();
                $cookies.remove('token'); //remove token da sessão
                document.location = "Login.html";
            }

        }]);