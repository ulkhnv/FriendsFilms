package ulkhnv.model;

import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "filmRatings")
public class FilmRating implements Serializable {

    public FilmRating() {
    }

    public FilmRating(Integer userRating, User user, Film film) {
        this.film = film;
        this.userRating = userRating;
        this.user = user;
    }

    @Id
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @Id
    @ManyToOne
    @JoinColumn(name = "film_id", referencedColumnName = "id")
    private Film film;

    @Column
    private Integer userRating;
}
