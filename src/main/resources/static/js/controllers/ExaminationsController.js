appControllers.controller('ExaminationsController', ['$scope', '$http', '$location', '$routeParams', function ($scope, $http, $routeParams) {
    let that = this;
    $scope["$ctrlExams"] = this;
    let id = $routeParams.$$url;
    id = id.split("/").pop();
    that.existingExams = {};
    that.name = {};
    that.surname = {};

    $http.get(`/examinations/all/${id}`)
        .then((response) => {
            that.existingExams = response.data.list;
            that.name =  response.data.name;
            that.surname =  response.data.surname;
        });
}]);
