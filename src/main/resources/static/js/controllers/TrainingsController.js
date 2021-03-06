appControllers.controller('TrainingsController', ['$scope', '$http', function ($scope, $http) {

    let that = this;
    $scope["$ctrlCoach"] = this;
    that.training = {};
    that.baseUrl = "/training";
    that.players = [];
    that.selectedPlayer = {};
    that.rating = 0;
    that.positions = [];

    that.getSchedule = function () {
        $http.get(`${that.baseUrl}/getSchedule`).then(function (response) {
            that.training.monday = response.data.monday;
            that.training.tuesday = response.data.tuesday;
            that.training.wednesday = response.data.wednesday;
            that.training.thursday = response.data.thursday;
            that.training.friday = response.data.friday;
            that.training.saturday = response.data.saturday;
            that.training.sunday = response.data.sunday;
            that.training.mondayPlan = response.data.mondayPlan;
            that.training.tuesdayPlan = response.data.tuesdayPlan;
            that.training.wednesdayPlan = response.data.wednesdayPlan;
            that.training.thursdayPlan = response.data.thursdayPlan;
            that.training.fridayPlan = response.data.fridayPlan;
            that.training.saturdayPlan = response.data.saturdayPlan;
            that.training.sundayPlan = response.data.sundayPlan;
        });
    };

    that.getTraining = function () {
        $http.get(`${that.baseUrl}/getTraining`).then(function (response) {
            that.training.monday = response.data.monday;
            that.training.tuesday = response.data.tuesday;
            that.training.wednesday = response.data.wednesday;
            that.training.thursday = response.data.thursday;
            that.training.friday = response.data.friday;
            that.training.saturday = response.data.saturday;
            that.training.sunday = response.data.sunday;
            that.training.mondayPlan = response.data.mondayPlan;
            that.training.tuesdayPlan = response.data.tuesdayPlan;
            that.training.wednesdayPlan = response.data.wednesdayPlan;
            that.training.thursdayPlan = response.data.thursdayPlan;
            that.training.fridayPlan = response.data.fridayPlan;
            that.training.saturdayPlan = response.data.saturdayPlan;
            that.training.sundayPlan = response.data.sundayPlan;
        });
    };

    that.getTeamPlayers = function () {
        $http.get(`${that.baseUrl}/players`).then(function (response) {
            that.players = response.data;
        });
        $http.get(`${that.baseUrl}/positions`).then(function (response) {
            that.positions = response.data;
        });
    };

    that.savePlayer = function (id, inStock, captain, position, rating) {
        $http.post(`${that.baseUrl}/changePlayer`, {
            id,
            rating,
            inStock,
            captain,
            position
        }).then(function (response) {
        });
    };

    that.members = [];
    that.getTeamMembers = () => {
        $http.get(`${that.baseUrl}/members`).then(function (response) {
            that.members = response.data;
        });
    };

    that.saveTraining = function () {
        $http.post(`${that.baseUrl}/saveTraining`, {
            monday: that.training.monday,
            tuesday: that.training.tuesday,
            wednesday: that.training.wednesday,
            thursday: that.training.thursday,
            friday: that.training.friday,
            saturday: that.training.saturday,
            sunday: that.training.sunday,
            mondayPlan: that.training.mondayPlan,
            tuesdayPlan: that.training.tuesdayPlan,
            wednesdayPlan: that.training.wednesdayPlan,
            thursdayPlan: that.training.thursdayPlan,
            fridayPlan: that.training.fridayPlan,
            saturdayPlan: that.training.saturdayPlan,
            sundayPlan: that.training.sundayPlan
        }).then(function (response) {
            if (response.data === true) {
                alert("Расписание тренировок сохранено");
            }
        });
    };

}]);