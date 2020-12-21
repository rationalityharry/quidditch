appControllers.controller('DoctorController', ['$scope', '$http', '$location', '$routeParams', function ($scope, $http, $routeParams) {
    let that = this;
    let id = $routeParams.id;
    $scope["$ctrlDoctor"] = this;

    that.id = id;

    that.patients = [];
    that.patient = {};

    $http.get("/doctor/patients").then(function (response) {
        that.patients = response.data;
    });

    that.examination = {};

    that.createExamination = function () {
        $http.post(`/doctor/create/${id}`, {
            id: id,
            sick: that.examination.sick,
            text: that.examination.text
        })
            .then(function (response) {
            });
    }
}]);