appControllers.controller('SaveExam', ['$scope', '$http', '$location', '$routeParams', function ($scope, $http, $routeParams) {
    let that = this;
    let id = $routeParams.$$url;
    id = id.split("/").pop();
    $scope["$ctrlDoctorExam"] = this;
    that.id = id;
    that.examination = {};
    that.createExamination = function () {
        $http.post(`/doctor/create`, {
            id: id,
            sick: that.examination.sick = false,
            text: that.examination.text
        })
            .then(function (response) {
                if (response.data) {
                    alert("Карточка добавлена");
                }
            });
    }
}]);