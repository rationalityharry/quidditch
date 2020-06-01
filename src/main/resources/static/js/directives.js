var app = angular.module('directives', []);

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