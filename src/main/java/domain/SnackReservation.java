package domain;
import javax.json.JsonObject;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
public class SnackReservation extends Item{


    @OneToMany(fetch = FetchType.EAGER, mappedBy = "snackReservation")
    private Set<Snack> snack;

    public Set <Snack> getSnack() {
        return snack;
    }
    public void setSnack(Set<Snack> snack) {
        this.snack = snack;
    }


    @ManyToOne
    //@JoinColumn(name="lastTnc", referencedColumnName="lastTnc")
    private Transactions transactions;

    public Transactions getTransactions() {
        return transactions;
    }

    public void setTransactions(Transactions transactions) {
        this.transactions = transactions;
    }

    @ManyToOne
    //@JoinColumn(name="seatReservation_fk", referencedColumnName="seatResId")
    private SeatReservation seatReservation;
    public SeatReservation getSeatReservation() {
        return seatReservation;
    }
    public void setSeatReservation(SeatReservation seatReservation) {
        this.seatReservation = seatReservation;
    }


    @Id
    @GeneratedValue
    private long id;


    private long lastTnc;

    private long seatReservation_fk;

    public SnackReservation ( ) {

    }




    public long getLastTnc() {
        return lastTnc;
    }

    public void setLastTnc(long lastTnc) {
        this.lastTnc = lastTnc;
    }

    @Override
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getSeatReservation_fk() {
        return seatReservation_fk;
    }

    public void setSeatReservation_fk(long seatReservation_fk) {
        this.seatReservation_fk = seatReservation_fk;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SnackReservation that = (SnackReservation) o;

        return id == that.id;

    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }

    //    public void fromJsonObject(JsonObject jsonObject) {
//        snrId = Long.valueOf(jsonObject.getJsonNumber("id").longValue());
//        lastTnc = Long.valueOf(jsonObject.getJsonNumber("tnc").longValue());
//        srId = Long.valueOf(jsonObject.getJsonNumber("SeatResId").longValue());
//    }


}
