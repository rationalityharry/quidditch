var app = angular.module('directives', []);

app.directive("reviews", function () {
    return {
        restrict: "E",
        templateUrl: "/html/comments.html"
    };
});
app.directive("animeAll", function () {
    return {
        restrict: "E",
        templateUrl: "/html/animeAll.html"
    };
});
app.directive("animePage", function () {
    return {
        restrict: "E",
        templateUrl: "/html/animePage.html"
    }
});
app.directive("reviewForm", function () {
    return {
        restrict: "E",
        templateUrl: "/html/animeComments.html"
    }
});
app.directive("navigation", function () {
   return {
       restrict: "E",
       templateUrl: "/html/user.html"
   }
});
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