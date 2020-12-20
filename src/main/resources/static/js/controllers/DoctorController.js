appControllers.controller('DoctorController', ['$scope', '$http', '$location', function ($scope, $http) {
    let that = this;
    $scope["$ctrlDoctor"] = this;

    that.patients = [];

    $http.get("/doctor/patients").then(function (response) {
        that.patients = response.data;
    });

}]);