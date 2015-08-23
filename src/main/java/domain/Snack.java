package domain;

import java.io.Serializable;
import javax.persistence.*;


@Entity
public class Snack  {



    @Id
    @GeneratedValue
    private long id;

    private long snackReservation_fk;

    private long lastTnc;

    private String name;

    private String snackType;

    @ManyToOne
    //@JoinColumn(name = "snackReservation_fk", nullable = false, referencedColumnName = "snackResId")
    private SnackReservation snackReservation;


    @ManyToOne
    //@JoinColumn(name="lastTnc", referencedColumnName="lastTnc")
    private Transactions transactions;


    public Snack() {

    }

    public long getLastTnc() {
        return lastTnc;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Snack snack = (Snack) o;

        return id == snack.id;

    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }

    public Transactions getTransactions() {
        return transactions;
    }

    public void setTransactions(Transactions Transactions) {
        this.transactions = Transactions;
    }

    public SnackReservation getSnackReservation() {
        return snackReservation;
    }

    public void setSnackReservation(SnackReservation snackReservation) {
        this.snackReservation = snackReservation;
    }

    public String getSnackType() {
        return snackType;
    }

    public void setSnackType(String snackType) {
        this.snackType = snackType;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLastTnc(long lastTnc) {
        this.lastTnc = lastTnc;
    }

    public long getSnackReservation_fk() {
        return snackReservation_fk;

    }

    public void setSnackReservation_fk(long snackReservation_fk) {
        this.snackReservation_fk = snackReservation_fk;
    }

    public long getId() {

        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
