package ulkhnv.controller.rest;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import ulkhnv.model.Film;
import ulkhnv.model.FilmRating;
import ulkhnv.model.User;
import ulkhnv.service.FilmRatingService;
import ulkhnv.service.FilmService;

import java.security.Principal;

@RestController
public class FilmController {

    private FilmRatingService filmRatingService;
    private FilmService filmService;

    public FilmController(FilmRatingService filmRatingService, FilmService filmService) {
        this.filmRatingService = filmRatingService;
        this.filmService = filmService;
    }

    @PostMapping("/film/update")
    public void addRating(@ModelAttribute("rating") Integer rating, @ModelAttribute("film_id") Long film_id, Principal principal) {
        User user = (User) ((Authentication) principal).getPrincipal();
        filmRatingService.addRating(new FilmRating(rating,user,filmService.getFilmById(film_id)));
    }

    @PostMapping("/film/add")
    public void addFilm(@RequestBody String name) {
        filmService.addFilm(name);
    }

    @DeleteMapping("film/delete/{id}")
    public void deleteFilm(@PathVariable Long id) {
        filmService.deleteFilm(id);
    }

    @PostMapping("/film/save/{id}")
    public void moderateFilm(@PathVariable Long id) {
        Film film = filmService.getFilmById(id);
        film.setModerated(true);
        filmService.updateFilm(film);
    }

}
