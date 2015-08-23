package domain;




import javax.json.JsonObject;
import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;


@Entity
public class SeatReservation {


    @Id
    private long id;

    @OneToMany(mappedBy = "seatReservation")
    private Collection<Seat> seats = new ArrayList<>();

    @OneToMany(mappedBy = "seatReservation")
    private Collection<SnackReservation> snackReservations = new ArrayList<>();

    @ManyToOne
    private Transactions transactions;

    public Transactions getTransactions() {
        return transactions;
    }

    public void setTransactions(Transactions transactions) {
        this.transactions = transactions;
    }

    private long lastTnc;


    public SeatReservation ( ) {

    }


    public long getLastTnc() {
        return lastTnc;
    }

    public void setLastTnc(long lastTnc) {
        this.lastTnc = lastTnc;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SeatReservation that = (SeatReservation) o;

        return id == that.id;

    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }

    public Collection<SnackReservation> getSnackReservations() {
        return snackReservations;
    }

    public void setSnackReservations(Collection<SnackReservation> snackReservations) {
        this.snackReservations = snackReservations;
    }

    public Collection<Seat> getSeats() {

        return seats;
    }

    public void setSeats(Collection<Seat> seats) {
        this.seats = seats;
    }

    public long getId() {
        return id;

    }

    public void setId(long id) {
        this.id = id;
    }

    //    public void fromJsonObject(JsonObject jsonObject) {
//        srId = Long.valueOf(jsonObject.getJsonNumber("id").longValue());
//        lastTnc = Long.valueOf(jsonObject.getInt("tnc"));
//    }
//
//    public static SeatReservation fromJsonObject2(JsonObject jsonObject) {
//        SeatReservation entity = new SeatReservation();
//        entity.srId = Long.valueOf(jsonObject.getJsonNumber("id").longValue());
//        entity.lastTnc = Long.valueOf(jsonObject.getInt("tnc"));
//        return entity;
//    }



}

