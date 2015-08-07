package domain;




import javax.json.JsonObject;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;


@Entity
@Table(name="seatReservation")
public class SeatReservation implements Serializable {


    @OneToMany(fetch = FetchType.EAGER, mappedBy = "seatReservation")
    private Set<Seat> seat;

    public Set <Seat> getSeats() {
        return seat;
    }

    public void setSeat(Set<Seat> seat) {
        this.seat = seat;
    }

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "seatReservation")
    private Set<SnackReservation> snackReservation;

    public Set <SnackReservation> getSnackReservation() {
        return snackReservation;
    }

    @ManyToOne
    @JoinColumn(name="lastTnc", referencedColumnName="tId")
    private Transaction transaction;

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }



    @Id
    @GeneratedValue
    @Column(name="seatResId")
    private long srId;


    @Column(name="lastTnc")
    private long lastTnc;


    public SeatReservation ( ) {

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

    @Override
    public String toString() {
        return "SeatReservation{" +
                "seat=" + seat +
                ", snackReservation=" + snackReservation +
                ", transaction=" + transaction +
                ", srId=" + srId +
                ", lastTnc=" + lastTnc +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SeatReservation that = (SeatReservation) o;

        if (srId != that.srId) return false;
        if (lastTnc != that.lastTnc) return false;
        if (seat != null ? !seat.equals(that.seat) : that.seat != null) return false;
        if (snackReservation != null ? !snackReservation.equals(that.snackReservation) : that.snackReservation != null)
            return false;
        return !(transaction != null ? !transaction.equals(that.transaction) : that.transaction != null);

    }

    @Override
    public int hashCode() {
        int result = seat != null ? seat.hashCode() : 0;
        result = 31 * result + (snackReservation != null ? snackReservation.hashCode() : 0);
        result = 31 * result + (transaction != null ? transaction.hashCode() : 0);
        result = 31 * result + (int) (srId ^ (srId >>> 32));
        result = 31 * result + (int) (lastTnc ^ (lastTnc >>> 32));
        return result;
    }

    public void fromJsonObject(JsonObject jsonObject) {
        srId = Long.valueOf(jsonObject.getJsonNumber("id").longValue());
        lastTnc = Long.valueOf(jsonObject.getInt("tnc"));
    }

    public static SeatReservation fromJsonObject2(JsonObject jsonObject) {
        SeatReservation entity = new SeatReservation();
        entity.srId = Long.valueOf(jsonObject.getJsonNumber("id").longValue());
        entity.lastTnc = Long.valueOf(jsonObject.getInt("tnc"));
        return entity;
    }



}

