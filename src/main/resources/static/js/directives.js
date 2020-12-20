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
        templateUrl: "/html/coach/trainingsPlan.html"
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

app.directive("playerRating", function () {
    return {
        restrict: "E",
        templateUrl: "/html/coach/playerRating.html"
    }
});