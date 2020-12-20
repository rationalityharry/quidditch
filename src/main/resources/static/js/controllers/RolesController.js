appControllers.controller('RoleController', ['$scope', '$http', '$location', function ($scope, $http) {
    let that = this;
    $scope["$ctrlRole"] = this;

    that.isAdministrator = false;
    that.isCoach = false;
    that.isDoctor = false;
    that.isPlayer = false;
    that.isOperator = false;
    that.userId = -1;
    that.login = "Not authorised";

    $http.get("/role").then(function (response) {
        switch (response.data.role) {
            case "administrator":
                that.isAdministrator = true;
                break;
            case "player":
                that.isPlayer = true;
                break;
            case "coach":
                that.isCoach = true;
                break;
            case "doctor":
                that.isDoctor = true;
                break;
            case "stat_manager":
                that.isOperator = true;
                break;
            case "operator":
                that.isOperator = true;
                break;
            default :
                that.isAdministrator = false;
                that.isCoach = false;
                that.isDoctor = false;
                that.isPlayer = false;
                that.isOperator = false;
                that.userId = -1;
                that.login = "Not authorised";
        }
        if (response.data.id) {
            that.login = response.data.login;
            that.userId = response.data.id;
        }
    });
}]);