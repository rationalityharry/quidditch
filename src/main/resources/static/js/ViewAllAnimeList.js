$(document).ready(function () {
    $.get("/anime/all", function (data) {
        var animeListBlock = $("#all-anime-list");
        for (var i=0; i<data.length; i++){
            var title = data[i];
            var name = title.name;
            var genre = title.genre;
            var author = title.author;
            var description = title.description;

            var titleDiv = $("<div>" + name +
                             "<br \>" + genre +
                             "<br \>" + author +
                             "<br \>" + description +
                             "</div>");
            animeListBlock.append(titleDiv);

        }

    });
});