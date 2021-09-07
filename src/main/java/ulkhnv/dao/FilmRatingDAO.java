package ulkhnv.dao;

import org.springframework.stereotype.Repository;
import ulkhnv.model.FilmRating;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class FilmRatingDAO {

    @PersistenceContext
    private EntityManager entityManager;


    public void addRating(FilmRating filmRating) {
        entityManager.persist(filmRating);
    }

    public boolean isRated(Long user_id, Long film_id) {
        return  entityManager.createQuery("SELECT COUNT(f.user.id) FROM FilmRating f WHERE f.user.id = :user_id" +
                " AND f.film.id= :film_id", Long.class).setParameter("user_id" , user_id)
                .setParameter("film_id",film_id).getSingleResult() > 0;
    }

    public Double getAverage(Long id) {
        return entityManager.createQuery("SELECT AVG(f.userRating) FROM FilmRating f WHERE f.film.id =: id", Double.class)
                .setParameter("id", id).getSingleResult();
    }

}
