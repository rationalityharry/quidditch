

$(document).ready(function() {
    $.get("/anime")


    $.get("/comments/" + animeId, function (data) {
        var commentsBlock = $("#comments");
        for (var i = 0; i < data.length; i++) {
            var comment = data[i];
            var anime = comment.anime;
            var user = comment.user;
            var commentDiv = $("<div>" + user.login + ":" + comment.rate + "</div>");
            commentsBlock.append(commentDiv);
        }
    })

});