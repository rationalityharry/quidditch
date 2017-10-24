var app = angular.module('controllers', []);

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
    });
}]);

app.controller('AuthorisationController', ['$scope', '$http', '$location', function ($scope, $http, $location) {
    var that = this;
    $scope["$ctrl"] = this;
    that.user = {};
    this.logIn = function () {
        $http.post("/authorisation", {
            login: that.user.login, password: that.user.password
        }).then(function (response) {
            if (response.data == 0) {
                if (confirm("No such user. Want to register now?") == true) {
                    $location.path("/registration");
                }
            } else {
                that.user.id = response;
                $location.path("/user")
            }
        });
    };

}]);

app.controller('AnimeAddController', ['$scope', '$http', '$location', function ($scope, $http, $location) {
    var that = this;
    that.anime = {};
    $scope["$ctrl"] = this;
    that.addAnime = function () {
        $http.post("/addAnime", {
            name: that.anime.name,
            genre: that.anime.genre,
            author: that.anime.author,
            description: that.anime.description,
            imageId: that.anime.imageId
        }).then(function (id) {
            alert("Success, take a look at this awesome anime!");
            $location.path("/anime/" + id)
        });
    }
}]);

app.controller('RegistrationController', ['$scope', '$http', '$location', function ($scope, $http, $location) {
    var that = this;
    $scope["$ctrl"] = this;
    that.user = {};
    that.registerUser = function () {
        $http.post("/registration", {login: that.user.login, password: that.user.password}).then(function (response) {
            if (response.data == 0) {
                alert("Such user exists or login is empty, try again!");
            } else {
                that.user = response.data;
                alert("Success, now try it!");
                $location.path("/authorisation");
            }
        });
    }
}]);