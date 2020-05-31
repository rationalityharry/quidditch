var app = angular.module("mainApp", ["ngRoute", "controllers", "directives"]);

app.config(["$routeProvider", function ($routeProvider) {
    $routeProvider
        .when("/", {
            templateUrl: "/html/admin/animeAll.html"
        })
        .when("/exit",{
            templateUrl: "/html/auth/authorisation.html"
        })
        .when("/anime/:id", {
            templateUrl: "/html/animePage.html"
        })
        .when("/authorisation", {
            templateUrl: "/html/auth/authorisation.html"
        })
        .when("/registration", {
            templateUrl: "/html/auth/registration.html"
        })
        .when("/addAnime", {
            templateUrl: "/html/addAnime.html"
        })
        .when("/animeAll", {
            templateUrl: "/html/admin/animeAll.html"
        })
        .when("/user/comments", {
            templateUrl: "/html/comments.html"
        });
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
                if (rejection.status == 401) {
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

