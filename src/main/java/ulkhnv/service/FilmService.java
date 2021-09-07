package ulkhnv.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import ulkhnv.dao.FilmDAO;
import ulkhnv.dao.FilmRatingDAO;
import ulkhnv.dao.UserDAO;
import ulkhnv.model.Film;
import ulkhnv.model.User;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class FilmService {

    private final FilmDAO filmDAO;
    private final FilmRatingDAO filmRatingDAO;

    @Autowired
    public FilmService(FilmDAO filmDAO, FilmRatingDAO filmRatingDAO) {
        this.filmDAO = filmDAO;
        this.filmRatingDAO = filmRatingDAO;
    }

    @Transactional
    public List<Film> getAllFilms(User user) {
        List<Film> result = filmDAO.getAllFilms();
        for (Film film : result) {
            if (filmRatingDAO.isRated(user.getId(), film.getId())) {
                film.setRated(true);
            }
        }
        return result;
    }

    public List<Film> getNotModeratedFilms() {
        return filmDAO.getNotModeratedFilms();
    }

    @Transactional
    public Film getFilmById(Long id) {
        return filmDAO.getFilmById(id);
    }

    @Transactional
    public void addFilm(String name) {
        Film film = new Film();
        film.setName(name);
        filmDAO.addFilm(film);
    }

    @Transactional
    public void updateFilm(Film film) {
        filmDAO.updateFilm(film);
    }

    @Transactional
    public void deleteFilm(Long id) {
        filmDAO.deleteFilm(id);
    }




}
