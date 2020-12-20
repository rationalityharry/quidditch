var app = angular.module("mainApp", ["ngRoute", "controllers", "directives"]);

app.config(["$routeProvider", function ($routeProvider) {
    $routeProvider
        .when("/", {
            templateUrl: "/html/mainIndex.html"
        })
        .when("/index", {
            templateUrl: "/html/mainIndex.html"
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
        .when("/administrator", {
            templateUrl: "/html/admin/index.html"
        })
        .when("/coach", {
            templateUrl: "/html/coach/views/viewTrainings.html"
        })
        .when("/ratingChange", {
            templateUrl: "/html/coach/views/viewRating.html"
        })
        .when("/myTeam", {
            templateUrl: "/html/coach/views/viewMembers.html"
        })
        .when("/player", {
            templateUrl: "/html/player/index.html"
        })
        .when("/doctor", {
            templateUrl: "/html/doctor/viewPatients.html"
        })
        .when("/news", {
            templateUrl: "/html/statistics/viewNews.html"
        })
        .when("/games", {
            templateUrl: "/html/statistics/viewGames.html"
        })
        .when("/create_news", {
            templateUrl: "/html/statistics/viewCreateNews.html"
        })
        .when("/create_games", {
            templateUrl: "/html/statistics/viewCreateGames.html"
        })
        .when("/editUser/:id", {
            templateUrl: "/html/user/index.html"
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

