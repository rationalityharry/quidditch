appControllers.controller('DoctorController', ['$scope', '$http', '$location', function ($scope, $http) {
    let that = this;
    $scope["$ctrlDoctor"] = this;

    that.patients = [];

    $http.get("/doctor/patients").then(function (response) {
        that.patients = response.data;
    });

    that.startExamination = function (id) {
        $http.get(`/doctor/patients/${id}`).then(function (response) {
            $location.path("/doctor/createExamination");

        });
    };

    that.examination = {};

    that.createExamination = function (id) {
        $http.post("/doctor/create", {
            id: id,
            sick: that.examination.sick,
            text: that.examination.text
        })
            .then(function (response) {
                if (response.data) {
                    alert("Карточка добавлена");
                }
            });
    }
}]);