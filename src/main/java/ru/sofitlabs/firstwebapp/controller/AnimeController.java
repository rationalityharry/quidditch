package ru.sofitlabs.firstwebapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.sofitlabs.firstwebapp.data.animebase.AnimeEntity;
import ru.sofitlabs.firstwebapp.data.animebase.AnimeEntityService;
import ru.sofitlabs.firstwebapp.data.animebase.CommentsEntityService;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
@RequestMapping("/anime")
public class AnimeController {

    @Autowired
    AnimeEntityService animeEntityService;

    @Autowired
    CommentsEntityService commentsEntityService;

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

    @RequestMapping(value = "/animeList/{animeId}", method = GET)
    public ModelAndView viewManyame(@PathVariable(value = "animeId") final Long animeId) {
        final ModelAndView animePage = new ModelAndView("animePage");
        animePage.addObject("animeId", animeId);
        return animePage;
    }

    @RequestMapping(value = "/addAnime", method = GET)
    public ModelAndView viewAddingManyame() {
        return new ModelAndView("addAnime");
    }

    @RequestMapping(value = "animeList", method = GET)
    public ModelAndView viewAnimeList() {
        return new ModelAndView("animeList");
    }

}
