package ulkhnv.model;
import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.List;

@Entity
@Table(name ="films")
public class Film {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column(columnDefinition = "double default 0.0")
    private Double rating = 0.0;

    @OneToMany(mappedBy = "film")
    private List<FilmRating> ratesOfUsers;

    @Column(columnDefinition = "boolean default false")
    private boolean isModerated = false;

    @Transient
    private boolean rated = false;

    public Film() {}

    public Film(Long id, String name, Double rating) {
        this.id = id;
        this.name = name;
        this.rating = rating;
    }

    public boolean isModerated() {
        return isModerated;
    }

    public void setModerated(boolean moderated) {
        isModerated = moderated;
    }

    public boolean isRated() {
        return rated;
    }

    public void setRated(boolean rated) {
        this.rated = rated;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }
}
