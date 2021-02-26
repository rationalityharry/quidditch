appControllers.controller('GamesController', ['$scope', '$http', '$location', function ($scope, $http) {
    let that = this;
    $scope["$ctrlGame"] = this;

    that.existingGames = {};

    $http.get("/games/all")
        .then((response) => {
            that.existingGames = response.data;
        });


    that.endedGames = {};

    $http.get("/games/ended")
        .then((response) => {
            that.endedGames = response.data;
        });


    that.game = {};

    that.createGame = function () {
        $http.post("/games/create", {
            date: that.game.date,
            time: that.game.time,
            location: that.game.location,
            team1: that.game.team1,
            team2: that.game.team2,
        })
            .then(function (response) {
                if (response.data) {
                    alert("Игра добавлена");
                }
            });
    }

    that.saveEnded = function (id, team1Score, team2Score) {
        $http.post(`/games/save`, {
            id, team1Score, team2Score
        }).then(function (response) {
            if (response.data === true) {
            }
        });
    };
}]);
