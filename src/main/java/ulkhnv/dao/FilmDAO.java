package ulkhnv.dao;

import org.springframework.stereotype.Repository;
import ulkhnv.model.Film;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class FilmDAO {

    @PersistenceContext
    EntityManager entityManager;

    public List<Film> getAllFilms() {
        return entityManager.createQuery("SELECT f FROM Film f WHERE f.isModerated=true ORDER BY f.rating DESC" , Film.class).getResultList();
    }

    public List<Film> getNotModeratedFilms() {
        return entityManager.createQuery("SELECT f FROM Film f WHERE f.isModerated=false" , Film.class).getResultList();
    }

    public void updateFilm(Film film) {
        entityManager.merge(film);
    }

    public Film getFilmById(Long id) {
        return entityManager.createQuery("SELECT f FROM Film f WHERE f.id = :id", Film.class)
                .setParameter("id", id).getSingleResult();
    }

    public void addFilm(Film film) {
        entityManager.persist(film);
    }

    public void deleteFilm(Long id) {
        entityManager.remove(getFilmById(id));
    }


}
