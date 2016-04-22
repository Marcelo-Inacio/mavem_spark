﻿window.publication = angular.module('psicologa', ['ngRoute', 'LocalStorageModule', 'angular-loading-bar', 'FatecControllers']);

publication.config(['$routeProvider', '$httpProvider', function ($routeProvider, $httpProvider) {
    $routeProvider
        

        //exemplo
        .when('/list',    { templateUrl: 'AngularJS/App/Psicologa/List/List.view.html',       controller: 'ListController' })
        .when('/comment', { templateUrl: 'AngularJS/App/Psicologa/Comment/Comment.view.html', controller: 'CommentController' })


    $httpProvider.interceptors.push('authorizationInterceptor');
}]);


window.FatecControllers = angular.module('FatecControllers', ['ui.bootstrap', 'ngReallyClickModule', 'ui.select', 'ngSanitize', 'checklist-model']);
