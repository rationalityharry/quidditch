appControllers.controller('NewsController', ['$scope', '$http', '$location', function ($scope, $http) {
    let that = this;
    $scope["$ctrlNews"] = this;
    that.existingNews = {};
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
