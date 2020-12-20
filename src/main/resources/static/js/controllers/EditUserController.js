appControllers.controller('EditUserController', ['$scope', '$http', '$location', '$routeParams', function ($scope, $http, $location, $routeParams) {
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
