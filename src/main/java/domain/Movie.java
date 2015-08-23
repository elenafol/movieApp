package domain;
import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;



@Entity
public class Movie {

    @Id
    @GeneratedValue
    private long id;

    public Movie() {
    }

    @OneToMany(mappedBy = "movie")
    private Collection<Seat> seats = new ArrayList<>();

    @ManyToOne
    private Transactions transactions;

    private String title;

    private Timestamp timeandday;

    private String description;

    private String urlBild;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Collection<Seat> getSeats() {
        return seats;
    }

    public void setSeats(Collection<Seat> seats) {
        this.seats = seats;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Timestamp getTimestamp() {
        return timeandday;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timeandday = timestamp;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrlBild() {
        return urlBild;
    }

    public void setUrlBild(String urlBild) {
        this.urlBild = urlBild;
    }

    public Transactions getTransactions() {
        return transactions;
    }

    public Timestamp getTimeandday() {
        return timeandday;
    }

    public void setTimeandday(Timestamp timeandday) {
        this.timeandday = timeandday;
    }

    public void setTransactions(Transactions transactions) {
        this.transactions = transactions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Movie movie = (Movie) o;

        return id == movie.id;


    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }

    /* public void fromJsonObject(JsonObject jsonObject) {
        long movieId = long.valueOf(jsonObject.getJsonNumber("id").longValue());

        setMId(movieId);
    }*/
}
