/*
package ru.quidditch.webapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.quidditch.webapp.data.animebase.*;
import ru.quidditch.webapp.data.entity.UserEntity;
import ru.quidditch.webapp.data.service.UserEntityService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
@PreAuthorize("hasAuthority('ROLE_PLAYER')")
@SessionAttributes(value = "user")
@RequestMapping("/anime")
public class PlayersController {

    @Autowired
    AnimeEntityService animeEntityService;

    @Autowired
    CommentsEntityService commentsEntityService;

    @Autowired
    UserEntityService userEntityService;

    private static class CommentDTO {
        int rate;
        String review;
        int userId;

        public CommentDTO() {
        }

        public int getRate() {
            return rate;
        }

        public String getReview() {
            return review;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(final int userId) {
            this.userId = userId;
        }

        public void setRate(final int rate) {
            this.rate = rate;
        }

        public void setReview(final String review) {
            this.review = review;
        }
    }

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
        return animeEntityService.add(title).getId();
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

    @RequestMapping(value = "/{animeId}/addComment", method = RequestMethod.POST)
    @ResponseBody
    public CommentsEntity addComment(@PathVariable final Long animeId,
                                     @RequestBody final CommentDTO comment,
                                     final HttpServletRequest request) {
        CommentsEntity review = new CommentsEntity();
        review.setAnime(animeEntityService.findOneById(animeId));
        review.setRate(comment.rate);
        review.setBody(comment.review);
        review.setUser((UserEntity) request.getSession().getAttribute("user"));

        return commentsEntityService.add(review);
    }


    @RequestMapping(value = "/all", method = GET)
    @ResponseBody
    public List<AnimeEntity> getAllAnime() {
        return animeEntityService.getAll();
    }
}
*/
