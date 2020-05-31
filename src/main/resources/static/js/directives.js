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
        templateUrl: "/html/admin/animeAll.html"
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
       templateUrl: "/html/navigation.html"
   }
});