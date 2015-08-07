package domain;
import javax.json.JsonObject;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Embeddable
@Entity
@Table(name="snackReservation")
public class SnackReservation extends Item implements Serializable {


    @OneToMany(fetch = FetchType.EAGER, mappedBy = "snackReservation")
    private Set<Snack> snack;

    public Set <Snack> getSnack() {
        return snack;
    }
    public void setSnack(Set<Snack> snack) {
        this.snack = snack;
    }


    @ManyToOne
    @JoinColumn(name="lastTnc", referencedColumnName="lastTnc")
    private Transaction transaction;

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }


    @ManyToOne
    @JoinColumn(name="seatReservation_fk", referencedColumnName="seatResId")
    private SeatReservation seatReservation;
    public SeatReservation getSeatReservation() {
        return seatReservation;
    }
    public void setSeatReservation(SeatReservation seatReservation) {
        this.seatReservation = seatReservation;
    }


    @Id
    @GeneratedValue
    @Column(name="snackResId")
    private long snrId;


    @Column(name="lastTnc")
    private long lastTnc;

    @Column(name="seatReservation_fk")
    private long srId;

    public SnackReservation ( ) {

    }


    public long getSrId() {
        return srId;
    }

    public void setSrId(long srId) {
        this.srId = srId;
    }

    public long getLastTnc() {
        return lastTnc;
    }

    public void setLastTnc(long lastTnc) {
        this.lastTnc = lastTnc;
    }

    public long getSnrId() {
        return snrId;
    }


    public void setSnrId(long snrId) {
        this.snrId = snrId;
    }


    @Override
    public String toString() {
        return "SnackReservation{" +
                "snack=" + snack +
                ", transaction=" + transaction +
                ", seatReservation=" + seatReservation +
                ", snrId=" + snrId +
                ", lastTnc=" + lastTnc +
                ", srId=" + srId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SnackReservation that = (SnackReservation) o;

        if (snrId != that.snrId) return false;
        if (lastTnc != that.lastTnc) return false;
        if (srId != that.srId) return false;
        if (snack != null ? !snack.equals(that.snack) : that.snack != null) return false;
        if (transaction != null ? !transaction.equals(that.transaction) : that.transaction != null) return false;
        return !(seatReservation != null ? !seatReservation.equals(that.seatReservation) : that.seatReservation != null);

    }

    @Override
    public int hashCode() {
        int result = snack != null ? snack.hashCode() : 0;
        result = 31 * result + (transaction != null ? transaction.hashCode() : 0);
        result = 31 * result + (seatReservation != null ? seatReservation.hashCode() : 0);
        result = 31 * result + (int) (snrId ^ (snrId >>> 32));
        result = 31 * result + (int) (lastTnc ^ (lastTnc >>> 32));
        result = 31 * result + (int) (srId ^ (srId >>> 32));
        return result;
    }

    public void fromJsonObject(JsonObject jsonObject) {
        snrId = Long.valueOf(jsonObject.getJsonNumber("id").longValue());
        lastTnc = Long.valueOf(jsonObject.getJsonNumber("tnc").longValue());
        srId = Long.valueOf(jsonObject.getJsonNumber("SeatResId").longValue());
    }


}
