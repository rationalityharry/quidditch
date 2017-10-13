$(document).ready(function () {
    $.get("/user/comments", function (data) {
        var commentsBlock = $("#comments");
        for (var i = 0; i < data.length; i++) {
            var comment = data[i];
            var anime = comment.animeId;
            var rate = comment.rate;
            var commentDiv = $("<div>" + anime + " : " + rate + "</div>" + "<br \>");
            commentsBlock.append(commentDiv);
        }
    });
});