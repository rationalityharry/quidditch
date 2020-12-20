appControllers.controller('AuthorisationController', ['$scope', '$http', '$location', function ($scope, $http, $location) {
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
                default :
                    $location.path("/news");
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


appControllers.controller('RegistrationController', ['$scope', '$http', '$location', function ($scope, $http, $location) {
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
