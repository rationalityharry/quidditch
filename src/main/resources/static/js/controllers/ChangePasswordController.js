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
}]);