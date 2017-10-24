package ru.sofitlabs.firstwebapp.controller;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.sofitlabs.firstwebapp.data.animebase.AnimeEntity;
import ru.sofitlabs.firstwebapp.data.animebase.AnimeEntityService;
import ru.sofitlabs.firstwebapp.data.animebase.CommentsEntity;
import ru.sofitlabs.firstwebapp.data.animebase.CommentsEntityService;
import ru.sofitlabs.firstwebapp.data.user.UserEntityService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
@SessionAttributes(value = "user")
@RequestMapping("/anime")
public class AnimeController {

    @Autowired
    AnimeEntityService animeEntityService;

    @Autowired
    CommentsEntityService commentsEntityService;

    @Autowired
    UserEntityService userEntityService;

    private static class AnimeDTO {
        String name;
        String genre;
        String author;
        String description;
        long imageId;

        public AnimeDTO() {
        }

        public void setName(final String name) {
            this.name = name;
        }

        public void setGenre(final String genre) {
            this.genre = genre;
        }

        public void setAuthor(final String author) {
            this.author = author;
        }

        public void setDescription(final String description) {
            this.description = description;
        }

        public void setImageId(final long imageId) {
            this.imageId = imageId;
        }
    }

    @RequestMapping(value = "/addAnime", method = RequestMethod.POST)
    @ResponseBody
    public long addManyame(@RequestBody final AnimeDTO animeDTO) {
        AnimeEntity title = new AnimeEntity();
        title.setName(animeDTO.name);
        title.setAuthor(animeDTO.author);
        title.setGenre(animeDTO.genre);
        title.setDescription(animeDTO.description);
        title.setAnimeImageId(animeDTO.imageId);
        final AnimeEntity added = animeEntityService.add(title);
        return added.getId();
    }

    @RequestMapping(value = "/{animeId}/info", method = GET)
    @ResponseBody
    public AnimeEntity getAnimeInfo(@PathVariable final Long animeId) {
        return animeEntityService.findOneById(animeId);

    }

    @RequestMapping(value = "/{animeId}/comments", method = GET)
    @ResponseBody
    public List<CommentsEntity> getAnimeComments(@PathVariable final Long animeId) {
        return commentsEntityService.getAllByAnime(animeEntityService.findOneById(animeId));
    }

    @RequestMapping(value = "/all", method = GET)
    @ResponseBody
    public List<AnimeEntity> getAllAnime() {
        return animeEntityService.getAll();
    }
}
