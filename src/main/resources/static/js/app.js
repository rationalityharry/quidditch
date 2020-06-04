var app = angular.module("mainApp", ["ngRoute", "controllers", "directives"]);

app.config(["$routeProvider", function ($routeProvider) {
    $routeProvider
        .when("/", {
            templateUrl: "/html/admin/index.html"
        })
        .when("/exit",{
            templateUrl: "/html/auth/authorisation.html"
        })
        .when("/authorisation", {
            templateUrl: "/html/auth/authorisation.html"
        })
        .when("/registration", {
            templateUrl: "/html/auth/registration.html"
        })
        .when("/admin", {
            templateUrl: "/html/admin/index.html"
        })
        .when("/editUser/:id", {
            templateUrl: "/html/user/profile.html"
        })
        .when("/404", {
            templateUrl: "/html/default/404.html"
        })
}]);

app.config(["$provide", function ($provide) {
    $provide.factory('myHttpInterceptor', ["$q", "$location", function ($q, $location) {
        return {
            // optional method
            'request': function (config) {
                // do something on success
                return config;
            },
            // optional method
            'requestError': function (rejection) {
                return $q.reject(rejection);
            },
            // optional method
            'response': function (response) {
                // do something on success
                return response;
            },
            'responseError': function (rejection) {
                if (rejection.status === 401) {
                    $location.path("/authorisation");
                    return $q.reject(rejection);
                }
                // same as above
                return $q.reject(rejection);
            }
        };
    }]);
}]);

app.config(["$httpProvider", function ($httpProvider) {
    $httpProvider.interceptors.push('myHttpInterceptor');
}]);

