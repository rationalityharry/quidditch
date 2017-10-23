var app = angular.module("mainApp", ["ngRoute"]);

app.config(["$routeProvider", function ($routeProvider) {
    $routeProvider
        .when("/", {
            templateUrl: "/html/authorisation.html"
        })
        .when("/anime/:id", {
            templateUrl: "/html/animePage.html"
        })
        .when("/authorisation", {
            templateUrl: "/html/authorisation.html"
        })
        .when("/registration", {
            templateUrl: "/html/registration.html"
        })
        .when("/user", {
            templateUrl: "/html/user.html"
        })
        .when("/anime/addAnime", {
            templateUrl: "/html/addAnime.html"
        })
        .when("/animeAll", {
            templateUrl: "/html/animeAll.html"
        })
        .when("/user/comments", {
            templateUrl: "/html/comments.html"
        });
}]);

app.config(["$provide", function ($provide) {

    $provide.factory('myHttpInterceptor', ["$q", "$location", function($q, $location) {
        return {
            // optional method
            'request': function(config) {
                // do something on success
                return config;
            },

            // optional method
            'requestError': function(rejection) {
                return $q.reject(rejection);
            },



            // optional method
            'response': function(response) {
                // do something on success
                return response;
            },

            'responseError': function(rejection) {
                if (rejection.status == 401) {
                    $location.path("/authorisation")
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

app.controller('AllAnimeViewController', ['$scope', '$http', function ($scope, $http) {
    var that = this;
    $scope["$ctrl"] = this;
    this.animeList = [];
    $http.get("/anime/all").then(function (response) {
        var animeList = response.data;
        that.animeList = animeList;
    });

}]);

app.controller('AnimePageController', ['$scope', '$http', '$routeParams', function ($scope, $http, $routeParams) {
    var that = this;
    var id = $routeParams.id;
    $scope["$ctrl"] = this;
    that.anime = {};
    that.commentaries = [];


    $http.get("/anime/" + id + "/info").then(function (response) {
        var anime = response.data;
        that.anime = anime;
    });

    $http.get("/anime/" + id + "/comments").then(function (response2) {
        var comments = response2.data;
        that.commentaries = comments;
    });
}]);
app.controller('ReviewController', ['$scope', '$http', function ($scope, $http) {
    var that = this;
    $scope["$ctrl"] = this;
    that.revews = [];
    $http.get("/user/comments").then(function (response) {
        var comments = response.data;
        that.reviews = comments;
    })

}]);

app.controller('authorisationController', ['$scope', '$http', function ($scope, $http) {
    var that = this;
    $scope["$ctrl"] = this;
    that.user = {};
    $http.get("")
}]);

app.controller('animeAddController', ['$scope', '$http', function ($scope, $http) {
    var that = this;
    $scope["$ctrl"]=this;

}])