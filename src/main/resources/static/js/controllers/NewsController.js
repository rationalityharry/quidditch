appControllers.controller('NewsController', ['$scope', '$http', '$location', function ($scope, $http) {
    let that = this;
    $scope["$ctrlNews"] = this;
    that.players = [];
    that.existingNews = {};
    $http.get("/news/playersRate").then(function (response) {
        that.players = response.data.sort(function (a, b) {
            if (a.rating > b.rating) {
                return -1;
            }
            if (a.rating < b.rating) {
                return 1;
            }
            return 0;
        });
    });

    $http.get("/news/all")
        .then((response) => {
            that.existingNews = response.data;
        });
    that.news = {};
    that.createNews = function () {
        $http.post("/news/create", {
            id: null,
            headline: that.news.headline,
            content: that.news.content,
            faculty: that.news.faculty
        })
            .then(function (response) {
                if (response.data) {
                    alert("Новость добавлена");
                }
            });
    }
}]);
