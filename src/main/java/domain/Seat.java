package domain;

import javax.persistence.*;
import java.io.Serializable;


//@Entity
@Table(name="Seat")
public class Seat implements  Serializable{

    //Ein Movie hat mehrere Sitze
    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "movie_fk", nullable = false, referencedColumnName="mId")
    private Movie movie;

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }



    //Eine Sitzplatzbuchung beinhaltet mehrere Sitzplätze
    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "seatReservation_fk", nullable = false, referencedColumnName="seatResId")
    private SeatReservation seatReservation;

    public SeatReservation getSeatReservation() {
        return seatReservation;
    }

    public void setSeatReservation(SeatReservation seatReservation) {
        this.seatReservation = seatReservation;
    }

    //Eine Transaktion kann mehrere Sitzplätze buchen/verändern
    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "lastTnc", nullable = false, referencedColumnName="lastTnc")
    private Transaction transaction;

    public Transaction getTransaction() {
        return transaction;
    }
    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    public Seat() {
    }

    @Id
    @GeneratedValue
    @Column(name="sId", nullable = false, updatable = false)
    private long sId;

    @Column(name="lastTnc")
    private long lastTnc;

    @Column (name="seatReservation_fk")
    private long srId;

    @Column (name="movie_fk")
    private long mId;



    public long getsId() {
        return sId;
    }

    public void setsId(long sId) {
        this.sId = sId;
    }


    public long getLastTnc() {
        return lastTnc;
    }

    public void setLastTnc(long lastTnc) {
        this.lastTnc = lastTnc;
    }

    public long getSrId() {
        return srId;
    }

    public void setSrId(long srId) {
        this.srId = srId;
    }

    public long getmId() {
        return mId;
    }

    public void setmId(long mId) {
        this.mId = mId;
    }

    @Override
    public String toString() {
        return "Seat{" +
                "movie=" + movie +
                ", seatReservation=" + seatReservation +
                ", transaction=" + transaction +
                ", sId=" + sId +
                ", lastTnc=" + lastTnc +
                ", srId=" + srId +
                ", mId=" + mId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Seat seat = (Seat) o;

        if (sId != seat.sId) return false;
        if (lastTnc != seat.lastTnc) return false;
        if (srId != seat.srId) return false;
        if (mId != seat.mId) return false;
        if (movie != null ? !movie.equals(seat.movie) : seat.movie != null) return false;
        if (seatReservation != null ? !seatReservation.equals(seat.seatReservation) : seat.seatReservation != null)
            return false;
        return !(transaction != null ? !transaction.equals(seat.transaction) : seat.transaction != null);

    }

    @Override
    public int hashCode() {
        int result = movie != null ? movie.hashCode() : 0;
        result = 31 * result + (seatReservation != null ? seatReservation.hashCode() : 0);
        result = 31 * result + (transaction != null ? transaction.hashCode() : 0);
        result = 31 * result + (int) (sId ^ (sId >>> 32));
        result = 31 * result + (int) (lastTnc ^ (lastTnc >>> 32));
        result = 31 * result + (int) (srId ^ (srId >>> 32));
        result = 31 * result + (int) (mId ^ (mId >>> 32));
        return result;
    }

 /* public void fromJsonObject(JsonObject jsonObject) {
        sId = long.valueOf(jsonObject.getJsonNumber("sId").longValue());
        lastTnc = jsonObject.getInt("tnc");
        mId = long.valueOf(jsonObject.getJsonNumber("mId").longValue());
    }*/

}
