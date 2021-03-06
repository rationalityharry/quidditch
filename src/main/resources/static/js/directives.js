var app = angular.module('directives', []);

app.directive('file', function () {
    return {
        scope: {
            file: '='
        },
        link: function (scope, el, attrs) {
            el.bind('change', function (event) {
                var file = event.target.files[0];
                scope.file = file ? file : undefined;
                scope.$apply();
            });
        }
    };
});

app.directive("navigation", function () {
    return {
        restrict: "E",
        templateUrl: "/html/default/topNav.html"
    }
});
app.directive("adminContent", function () {
    return {
        restrict: "E",
        templateUrl: "/html/admin/activation.html"
    }
});
app.directive("trainingPlan", function () {
    return {
        restrict: "E",
        templateUrl: "/html/coach/parts/trainingsPlan.html"
    }
});
app.directive("schedule", function () {
    return {
        restrict: "E",
        templateUrl: "/html/player/schedule.html"
    }
});
app.directive("sideNavigation", function () {
    return {
        restrict: "E",
        templateUrl: "/html/default/sideNav.html"
    }
});
app.directive("profile", function () {
    return {
        restrict: "E",
        templateUrl: "/html/user/profile.html"
    }
});

app.directive("footerHog", function () {
    return {
        restrict: "E",
        templateUrl: "/html/default/footer.html"
    }
});
app.directive("news", function () {
    return {
        restrict: "E",
        templateUrl: "/html/shared/news.html"
    }
});

app.directive("createNews", function () {
    return {
        restrict: "E",
        templateUrl: "/html/statistics/createNews.html"
    }
});

app.directive("patients", function () {
    return {
        restrict: "E",
        templateUrl: "/html/doctor/patients.html"
    }
});

app.directive("examinations", function () {
    return {
        restrict: "E",
        templateUrl: "/html/shared/examinationsList.html"
    }
});

app.directive("playerRating", function () {
    return {
        restrict: "E",
        templateUrl: "/html/coach/parts/playerRating.html"
    }
});

app.directive("games", function () {
    return {
        restrict: "E",
        templateUrl: "/html/shared/games.html"
    }
});

app.directive("createGames", function () {
    return {
        restrict: "E",
        templateUrl: "/html/statistics/createGames.html"
    }
});

app.directive("endedGames", function () {
    return {
        restrict: "E",
        templateUrl: "/html/statistics/endedGames.html"
    }
});
app.directive("teamMembersCoach", function () {
    return {
        restrict: "E",
        templateUrl: "/html/coach/parts/teamMembers.html"
    }
});
app.directive("createExam", function () {
    return {
        restrict: "E",
        templateUrl: "/html/doctor/createExam.html"
    }
});