appControllers.controller('AdminController', ['$scope', '$http', function ($scope, $http) {
    let that = this;
    $scope["$ctrlAdmin"] = this;
    that.allUsers = [];
    that.usersToEnable = [];
    $http.get("/admin/users").then(function (response) {
        that.allUsers = response.data;
    });
    $http.get("/admin/disabledUsers").then(function (response2) {
        that.usersToEnable = response2.data;
    });
    that.enableUser = function (id) {
        $http.get(`/admin/enableUser/${id}`).then(function (response) {
            if (response.data === true) {
                alert("Пользователь активирован");
            }
        });
    };
    that.disableUser = function (id) {
        $http.get(`/admin/disableUser/${id}`).then(function (response) {
            if (response.data === true) {
                alert("Пользователь деактивирован");
            }
        });
    };
}]);






