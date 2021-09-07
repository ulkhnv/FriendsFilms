package ulkhnv.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ulkhnv.dao.FilmDAO;
import ulkhnv.dao.FilmRatingDAO;
import ulkhnv.model.Film;
import ulkhnv.model.FilmRating;

import javax.transaction.Transactional;

@Service
public class FilmRatingService {

    private final FilmRatingDAO filmRatingDAO;
    private final FilmDAO filmDAO;

    @Autowired
    public FilmRatingService (FilmRatingDAO filmRatingDAO, FilmDAO filmDAO) {
        this.filmRatingDAO = filmRatingDAO;
        this.filmDAO = filmDAO;
    }

    @Transactional
    public void addRating(FilmRating filmRating) {
        filmRatingDAO.addRating(filmRating);
        Film film = filmDAO.getFilmById(filmRating.getFilm().getId());
        film.setRating(filmRatingDAO.getAverage(film.getId()));
        filmDAO.updateFilm(film);
    }
}
