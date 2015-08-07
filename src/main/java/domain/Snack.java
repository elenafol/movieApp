package domain;

import java.io.Serializable;
import javax.persistence.*;


@Entity
@Table (name="snack")
public class Snack implements Serializable {



    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "snackReservation_fk", nullable = false, referencedColumnName = "snackResId")
    private SnackReservation snackReservation;


    public SnackReservation getSnackReservation() {
        return snackReservation;
    }

    public void setSnackReservation(SnackReservation snackReservation) {
        this.snackReservation = snackReservation;
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


    @Id
    @Column(name="snId",  nullable = false, updatable = false)
    @GeneratedValue
    private long snId;

    @Column(name="snackResId")
    private long snrId;

    @Column(name="lastTnc")
    private long lastTnc;

    @Column(name="name")
    private String name;

    @Column(name="snackType")
    private String snackType;


    public Snack() {

    }

    public long getSnId() {
        return snId;
    }

    public void setSnId(long snId) {
        this.snId = snId;
    }

    public long getLastTnc() {
        return lastTnc;
    }

    public void setLastTnc(long lastTnc) {
        this.lastTnc = lastTnc;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getSnackType() {
        return snackType;
    }

    public void setSnackType(String snackType) {
        this.snackType = snackType;
    }


    public long getSnrId() {
        return snrId;
    }

    public void setSnrId(long snrId) {
        this.snrId = snrId;
    }

    @Override
    public String toString() {
        return "Snack{" +
                "snackReservation=" + snackReservation +
                ", transaction=" + transaction +
                ", snId=" + snId +
                ", snrId=" + snrId +
                ", lastTnc=" + lastTnc +
                ", name='" + name + '\'' +
                ", snackType='" + snackType + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Snack snack = (Snack) o;

        if (snId != snack.snId) return false;
        if (snrId != snack.snrId) return false;
        if (lastTnc != snack.lastTnc) return false;
        if (snackReservation != null ? !snackReservation.equals(snack.snackReservation) : snack.snackReservation != null)
            return false;
        if (transaction != null ? !transaction.equals(snack.transaction) : snack.transaction != null) return false;
        if (name != null ? !name.equals(snack.name) : snack.name != null) return false;
        return !(snackType != null ? !snackType.equals(snack.snackType) : snack.snackType != null);

    }

    @Override
    public int hashCode() {
        int result = snackReservation != null ? snackReservation.hashCode() : 0;
        result = 31 * result + (transaction != null ? transaction.hashCode() : 0);
        result = 31 * result + (int) (snId ^ (snId >>> 32));
        result = 31 * result + (int) (snrId ^ (snrId >>> 32));
        result = 31 * result + (int) (lastTnc ^ (lastTnc >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (snackType != null ? snackType.hashCode() : 0);
        return result;
    }
}
