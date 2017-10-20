var app = angular.module("mainApp", ["ngRoute"]);

app.config(function ($routeProvider) {
    $routeProvider
        .when("/", {
            templateUrl: "/html/animeAll.html"
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
});

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
    this.reviewList = [];

    this.addReview = function (anime, author) {
        this.reviewList.push()
    };

}]);