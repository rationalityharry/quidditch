package ru.sofitlabs.firstwebapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.sofitlabs.firstwebapp.data.animebase.AnimeEntity;
import ru.sofitlabs.firstwebapp.data.animebase.AnimeEntityService;
import ru.sofitlabs.firstwebapp.data.animebase.CommentsEntity;
import ru.sofitlabs.firstwebapp.data.animebase.CommentsEntityService;
import ru.sofitlabs.firstwebapp.data.user.UserEntityService;

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

    @RequestMapping(value = "/addAnime", method = RequestMethod.POST)
    public ModelAndView addManyame(@RequestParam(name = "name") final String name,
                                   @RequestParam(name = "genre") final String genre,
                                   @RequestParam(name = "author") final String author,
                                   @RequestParam(name = "description") final String description,
                                   @RequestParam(name = "animeLink") final String animeLink) {
        AnimeEntity title = new AnimeEntity();
        title.setName(name);
        title.setAuthor(author);
        title.setGenre(genre);
        title.setDescription(description);
        title.setAnimeLink(animeLink);
        animeEntityService.add(title);
        return new ModelAndView("animePage");
    }

    @RequestMapping(value = "/addAnime", method = GET)
    public ModelAndView viewAddingManyame() {
        return new ModelAndView("addAnime");
    }

    @RequestMapping(value = "/{animeId}", method = GET)
    public ModelAndView viewAnimePage(@PathVariable final Long animeId) {
        ModelAndView animePage = new ModelAndView("animePage");
        animePage.addObject("animeId", animeId);
        return animePage;
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

    @RequestMapping(value = "", method = GET)
    public ModelAndView viewAnimeListing() {
        return new ModelAndView("anime");
    }

    @RequestMapping(value = "/all", method = GET)
    @ResponseBody
    public List<AnimeEntity> getAllAnime() {
        return animeEntityService.getAll();
    }
}
