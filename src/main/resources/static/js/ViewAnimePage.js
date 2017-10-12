$(document).ready(function () {
    $.get("/anime/" + animeId + "/info", function (data) {
        var animeBlock = $("#anime-info");
        var titleBlock = $("title");
        var animePage = data;
        var animeName = animePage.name;
        titleBlock.text(animeName);
        var animeGenre = animePage.genre;
        var animeAuthor = animePage.author;
        var animeDescription = animePage.description;
        var animeDiv = $("<div>" + animeName + animeAuthor
            + animeGenre + animeDescription + "</div>" + "<br>" + "<br>");
        animeBlock.append(animeDiv);
    });


    $.get("/anime/" + animeId + "/comments", function (data) {
        var commentsBlock = $("#comments");
        for (var i = 0; i < data.length; i++) {
            var comment = data[i];
            var anime = comment.anime;
            var user = comment.user;
            var commentDiv = $("<div>" + anime.name + " " + user.login + ":" + comment.rate + "</div>" + "<br \>");
            commentsBlock.append(commentDiv);
        }
    });

});