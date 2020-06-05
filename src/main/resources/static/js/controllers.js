var app = angular.module('controllers', []);

app.controller('AdminController', ['$scope', '$http', function ($scope, $http) {
    let that = this;
    $scope["$ctrlAdmin"] = this;
    that.allUsers = [];
    that.usersToEnable = [];
    /*$http.get("/admin/users").then(function (response) {
        that.allUsers = response.data.data;
    });*/
    $http.get("/admin/disabledUsers").then(function (response2) {
        that.usersToEnable = response2.data;
    });
    that.enableUser = function (id) {
        $http.get("/admin/enableUser/" + id).then(function (response) {
            if (response.data === "ok") {
                alert("Пользователь активирован");
            }
        });
    };
}]);

app.controller('EditUserController', ['$scope', '$http', '$location', '$routeParams', function ($scope, $http, $location, $routeParams) {
    let that = this;
    let id = $routeParams.id;
    that.user = {};
    that.id = id;
    that.image = {};
    that.isAdmin = false;
    $scope["$ctrl"] = this;
    $http.get("/user/data/" + id).then(function (privilegResponse) {
        that.isAdmin = privilegResponse.data.isAdmin;
        that.user.login = privilegResponse.data.login;
        that.user.id = id;
        that.user.password = privilegResponse.data.password;
        that.user.surname = privilegResponse.data.surname;
        that.user.name = privilegResponse.data.name;
        that.user.patronymic = privilegResponse.data.patronymic;
        that.user.email = privilegResponse.data.email;
        that.user.role = privilegResponse.data.role;
        that.user.faculty = privilegResponse.data.faculty;
        that.user.info = privilegResponse.data.info;
        that.user.phone = privilegResponse.data.phone;
        that.user.imageId = privilegResponse.data.id;
    });
    this.editUser = function () {
        let formData = new FormData();
        formData.append('file', that.image);
        $http.post("/loadImage", formData, {
            transformRequest: angular.identity,
            headers: {'Content-Type': undefined}
        }).then(function (response) {
            if (response != null) {
                $http.post("/user/edit/" + id, {
                    login: that.user.login,
                    password: that.user.password,
                    surname: that.user.surname,
                    name: that.user.name,
                    patronymic: that.user.patronymic,
                    email: that.user.email,
                    role: that.user.role,
                    faculty: that.user.faculty,
                    info: that.user.info,
                    phone: that.user.phone,
                    imageId: response.data
                }).then(function (response) {
                    if (response.data !== 0) {
                        alert("Success!");
                        $location.path("/editUser/" + id)
                    }
                });
            } else {
                alert("Some troubles with image loading")
            }
        });
    }
}]);

app.controller('AuthorisationController', ['$scope', '$http', '$location', function ($scope, $http, $location) {
    let that = this;
    $scope["$ctrl2"] = this;
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
            switch (response.data.role) {
                case null:
                case undefined:
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
                    $location.path("/editUser/" + response.data.id)
                    break;
                case "coach":
                    $location.path("/404")
                    break;
                case "doctor":
                    $location.path("/404")
                    break;
                case "stat_manager":
                    $location.path("/404")
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
                phone: that.user.phone,
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

