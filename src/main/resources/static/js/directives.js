var app = angular.module('directives', []);

app.directive ("reviews", function () {
    return {
        restrict: "E",
        templateUrl: "/html/comments.html"
    };
});
app.directive ("animeAll", function () {
    return {
        restrict: "E",
        templateUrl: "/html/animeAll.html"
    };
});
app.directive ("animePage", function () {
    return{
        restrict: "E",
        templateUrl: "/html/animePage.html"
    }
});