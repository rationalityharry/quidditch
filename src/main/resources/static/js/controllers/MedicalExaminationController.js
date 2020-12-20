appControllers.controller('MedicalExaminationController', ['$scope', '$http', '$location', function ($scope, $http) {
    let that = this;
    $scope["$ctrlExaminations"] = this;

    that.examination = {};

    that.createExamination = function () {
        $http.post("/examinations/create", {
            id: null,
            name: that.examination.name,
            surname: that.examination.surname,
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