var app = angular.module('controllers', []);

app.controller('AdminController', ['$scope', '$http', function ($scope, $http) {
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
        $http.get("/admin/enableUser/" + id).then(function (response) {
            if (response.data === "ok") {
                alert("Пользователь активирован");
            }
        });
    };
    that.disableUser = function (id) {
        $http.get("/admin/disableUser/" + id).then(function (response) {
            if (response.data === "ok") {
                alert("Пользователь деактивирован");
            }
        });
    };
}]);

app.controller('TrainingsController', ['$scope', '$http', function ($scope, $http) {
    let that = this;
    $scope["$ctrlCoach"] = this;
    that.training = {};

    $http.get("/training/get").then(function (response) {
        that.training.monday = response.data.monday;
        that.training.tuesday = response.data.tuesday;
        that.training.wednesday = response.data.wednesday;
        that.training.thursday = response.data.thursday;
        that.training.friday = response.data.friday;
        that.training.saturday = response.data.saturday;
        that.training.sunday = response.data.sunday;

    });
    that.saveTraining = function () {
        $http.post("/training/saveTraining", {
            monday: that.training.monday,
            tuesday: that.training.tuesday,
            wednesday: that.training.wednesday,
            thursday: that.training.thursday,
            friday: that.training.friday,
            saturday: that.training.saturday,
            sunday: that.training.sunday
        }).then(function (response) {
            if (response.data === true) {
                alert("Расписание тренировок сохранено");
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
        that.isAdmin = privilegResponse.data.admin;
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
        that.user.birthdate = privilegResponse.data.birthdate
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
                    imageId: response.data,
                    birthdate: that.user.birthdate
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

app.controller('ChangePasswordController', ['$scope', '$http', '$location', function ($scope, $http, $location) {
    let that = this;
    that.data = {}

    this.changePassword = function () {
        if (that.data.newPassword !== that.data.newPasswordConfirmation) {
            alert("Неправильное подтверждение пароля");
        } else {
            $http.post("/changePassword", {
                oldPassword: that.data.oldPassword,
                newPassword: that.data.newPassword,
                newPasswordConfirmation: that.data.newPasswordConfirmation,
                userId: $("#idToChangepwd").val()
            }).then(function (changeResponse) {
                switch (changeResponse.data.reason) {
                    case 0:
                        alert("Пользователь не найден");
                        break;
                    case 1:
                        alert("Неправильное подтверждение пароля");
                        break;
                    case 2:
                        alert("Неверный старый пароль");
                        break;
                    case 3:
                        alert("Пароль успешно изменен");
                        break;
                }
            });
        }
    }

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
                    imageId: response.data,
                    birthdate: that.user.birthdate
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

app.controller('RoleController', ['$scope', '$http', '$location', function ($scope, $http, $location) {
    let that = this;
    $scope["$ctrlRole"] = this;

    that.isAdministrator = false;
    that.isCoach = false;
    that.isDoctor = false;
    that.isPlayer = false;
    that.isOperator = false;
    that.userId = -1;
    that.login = "Not authorised";

    $http.get("/role").then(function (response) {
        switch (response.data.role) {
            case "administrator":
                that.isAdministrator = true;
                break;
            case "player":
                that.isPlayer = true;
                break;
            case "coach":
                that.isCoach = true;
                break;
            case "doctor":
                that.isDoctor = true;
                break;
            case "stat_manager":
                that.isOperator = true;
                break;
            case "operator":
                that.isOperator = true;
                break;
            default :
                that.isAdministrator = false;
                that.isCoach = false;
                that.isDoctor = false;
                that.isPlayer = false;
                that.isOperator = false;
                that.userId = -1;
                that.login = "Not authorised";
        }
        if (response.data.id) {
            that.login = response.data.login;
            that.userId = response.data.id;
        }
    });
}]);
app.controller('AuthorisationController', ['$scope', '$http', '$location', function ($scope, $http, $location) {
    let that = this;
    $scope["$ctrlAuth"] = this;
    that.user = {};
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
                    alert("User is not confirmed by admin yet, try again later.");
                    break;
                case "operator":
                    $location.path("/stat_manager");
                    break;
                default :
                    $location.path("/" + response.data.role);
                    break;
            }
        });
    };
    this.logOut = function () {
        $http.post("/exit", {}).then(function (response) {
            if (response.data === 0) {
                $location.path("/authorisation");
            }
        });
    };

}]);


app.controller('RegistrationController', ['$scope', '$http', '$location', function ($scope, $http, $location) {
    let that = this;
    $scope["$ctrlRegistration"] = this;
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
                faculty: that.user.faculty,
                birthdate: that.user.birthdate
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


app.controller('NewsController', ['$scope', '$http', '$location', function ($scope, $http, $location) {
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
            headline: that.news.headline,
            content: that.news.content
        })
            .then(function (response) {
                if (response.data) {
                    alert("hui");
                }
            });
    }
}]);

