package domain;

import javax.persistence.*;
import java.io.Serializable;


@Entity
public class Seat{

    //Ein Movie hat mehrere Sitze
    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    //@JoinColumn(name = "movie_fk", nullable = false, referencedColumnName="mId")
    private Movie movie;

    //Eine Sitzplatzbuchung beinhaltet mehrere Sitzplätze
    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    //@JoinColumn(name = "seatReservation_fk", nullable = false, referencedColumnName="seatResId")
    private SeatReservation seatReservation;

    //Eine Transaktion kann mehrere Sitzplätze buchen/verändern
    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    //@JoinColumn(name = "lastTnc", nullable = false, referencedColumnName="lastTnc")
    private Transactions transactions;


    public Seat() {
    }

    @Id
    @GeneratedValue
    private long id;

    private long lastTnc;

    private long seatReservation_fk;

    private long movie_fk;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Seat seat = (Seat) o;

        return id == seat.id;

    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public long getMovie_fk() {
        return movie_fk;
    }

    public void setMovie_fk(long movie_fk) {
        this.movie_fk = movie_fk;
    }

    public long getSeatReservation_fk() {

        return seatReservation_fk;
    }

    public void setSeatReservation_fk(long seatReservation_fk) {
        this.seatReservation_fk = seatReservation_fk;
    }

    public long getLastTnc() {
        return lastTnc;

    }

    public void setLastTnc(long lastTnc) {
        this.lastTnc = lastTnc;
    }

    public long getId() {
        return id;

    }

    public void setId(long id) {
        this.id = id;
    }

    public Transactions getTransactions() {

        return transactions;
    }

    public void setTransactions(Transactions transactions) {
        this.transactions = transactions;
    }

    public SeatReservation getSeatReservation() {
        return seatReservation;

    }

    public void setSeatReservation(SeatReservation seatReservation) {
        this.seatReservation = seatReservation;
    }
    /* public void fromJsonObject(JsonObject jsonObject) {
        sId = long.valueOf(jsonObject.getJsonNumber("sId").longValue());
        lastTnc = jsonObject.getInt("tnc");
        mId = long.valueOf(jsonObject.getJsonNumber("mId").longValue());
    }*/

}
