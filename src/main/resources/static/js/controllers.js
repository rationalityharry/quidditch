var app = angular.module('controllers', []);

app.controller('AdminController', ['$scope', '$http', '$routeParams', function ($scope, $http, $routeParams) {
    let that = this;
    $scope["$ctrl"] = this;
    that.allUsers = [];
    that.usersToEnable = [];
    /*$http.get("/admin/users").then(function (response) {
        that.allUsers = response.data.data;
    });*/
    $http.get("/admin/disabledUsers").then(function (response2) {
        that.usersToEnable = response2.data.data;
    });
    that.enableUser = function (id) {
        $http.get("/admin/enableUser/" + id).then(function (response) {
            if (response.data.response === "ok") {
                alert("Пользователь активирован");
            }
        });
    };
}]);


app.controller('AuthorisationController', ['$scope', '$http', '$location', function ($scope, $http, $location) {
    let that = this;
    $scope["$ctrl"] = this;
    that.user = {};
    this.logOut = function () {
        $http.post("/exit", {}).then(function (response) {
            if (response.data === 0) {
                $location.path("/authorisation");
            }
        });
    };

    this.logIn = function () {
        $http.post("/authorisation", {
            login: that.user.login,
            password: that.user.password
        }).then(function (response) {
            switch (response.data.data) {
                case "0":
                    if (confirm("Пользователь не найден. Хотите зарегистрировать?") === true) {
                        $location.path("/registration");
                    }
                    break;
                case "1":
                    alert("User is not confirmed by admin yet, try again later.")
                    break;
                case "administrator":
                    $location.path("/admin")
                    break;
                case "player":
                    $location.path("/admin")
                    break;
                case "coach":
                    $location.path("/admin")
                    break;
                case "doctor":
                    $location.path("/admin")
                    break;
                case "stat_manager":
                    $location.path("/admin")
                    break;
            }
        });
    };

}]);


app.controller('RegistrationController', ['$scope', '$http', '$location', function ($scope, $http, $location) {
    let that = this;
    $scope["$ctrl"] = this;
    that.user = {};
    that.registerUser = function () {
        $http.post("/registration", {
                login: that.user.login,
                password: that.user.password,
                surname: that.user.surname,
                name: that.user.username,
                patronymic: that.user.patronymic,
                email: that.user.email,
                role: that.user.role,
                faculty: that.user.faculty
            }
        ).then(function (response) {
            if (response.data === "0") {
                alert("Такой логин занят");
            } else {
                that.user = response.data;
                $location.path("/authorisation");
            }
        });
    }
}]);

