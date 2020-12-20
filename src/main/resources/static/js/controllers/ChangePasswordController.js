appControllers.controller('ChangePasswordController', ['$scope', '$http', '$location', function ($scope, $http, $location) {
    let that = this;
    that.data = {};

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
    };

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