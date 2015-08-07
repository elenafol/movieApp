package domain;
import javax.json.JsonObject;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Set;



@Entity
@Table(name="movie")
public class Movie implements Serializable{

    public Movie() {
    }

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "movie")
    private Set<Seat> seats;

    public Set <Seat> getSeats() {
        return seats;
    }
    public void setSeats(Set <Seat> seats){this.seats=seats;}



    @Id
    @GeneratedValue
     @Column(name="mId", nullable = false, updatable = false)
    private long mId;


    @Column(name="title")
    private String title;

    @Column(name="timeAndDay")
    private Timestamp timestamp;

    @Column(name="description")
    private String description;

    @Column(name="urlBild")
    private String urlBild;

    @Column(name="lastTnc")
    private long lastTnc;


    public long getLastTnc() {
        return lastTnc;
    }

    public void setLastTnc(long lastTnc) {
        this.lastTnc = lastTnc;
    }


    public long getMId() {
        return mId;
    }

    public void setMId(long mId) {
        this.mId = mId;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
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


    @Override
    public String toString() {
        return "Movie{" +
                "seats=" + seats +
                ", mId=" + mId +
                ", title='" + title + '\'' +
                ", timestamp=" + timestamp +
                ", description='" + description + '\'' +
                ", urlBild='" + urlBild + '\'' +
                ", lastTnc=" + lastTnc +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Movie movie = (Movie) o;

        if (mId != movie.mId) return false;
        if (lastTnc != movie.lastTnc) return false;
        if (seats != null ? !seats.equals(movie.seats) : movie.seats != null) return false;
        if (title != null ? !title.equals(movie.title) : movie.title != null) return false;
        if (timestamp != null ? !timestamp.equals(movie.timestamp) : movie.timestamp != null) return false;
        if (description != null ? !description.equals(movie.description) : movie.description != null) return false;
        return !(urlBild != null ? !urlBild.equals(movie.urlBild) : movie.urlBild != null);

    }

    @Override
    public int hashCode() {
        int result = seats != null ? seats.hashCode() : 0;
        result = 31 * result + (int) (mId ^ (mId >>> 32));
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (timestamp != null ? timestamp.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (urlBild != null ? urlBild.hashCode() : 0);
        result = 31 * result + (int) (lastTnc ^ (lastTnc >>> 32));
        return result;
    }

  /* public void fromJsonObject(JsonObject jsonObject) {
        long movieId = long.valueOf(jsonObject.getJsonNumber("id").longValue());

        setMId(movieId);
    }*/
}
