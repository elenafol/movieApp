package domain;
import javax.json.JsonObject;
import javax.persistence.*;
import java.util.HashSet;
import java.util.HashSet.*;
import java.util.Set;

//@Entity
public class Transaction {


    @Id
    @GeneratedValue
    private long tId;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "transaction")
    private Set<SnackReservation> snackReservations;

    public Set <SnackReservation> getReservations() {
        return snackReservations;
    }

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "transaction")
    private Set<SeatReservation> seatReservations;
    public Set <SeatReservation> getSeatReservations() {
        return seatReservations;
    }

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "client_fk", nullable = false, referencedColumnName="cId")
    private Client client;

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Transaction() {
    }

    @Column(name = "client_fk")
    private long cId;

    @Column(name = "lastTnc")
    private long lastTnc;

    public void setLastTnc(long lastTnc) {
        this.lastTnc = lastTnc;
    }

    public long getLastTnc() {
        return lastTnc;
    }

    public long getcId() {
        return cId;
    }

    public void setcId(long cId) {
        this.cId = cId;
    }

    public long gettId() {
        return tId;
    }

    public void settId(long tId) {
        this.tId = tId;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                ", snackReservations=" + snackReservations +
                ", seatReservations=" + seatReservations +
                ", cId=" + cId +
                ", lastTnc=" + lastTnc +
                '}';
    }


//public void fromJsonObject(JsonObject jsonObject) {
    // cId= long.valueOf(jsonObject.getJsonNumber("cId").longValue());
    //lastTnc = jsonObject.getInt("tnc");

    // }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Transaction that = (Transaction) o;

        if (tId != that.tId) return false;
        if (cId != that.cId) return false;
        if (lastTnc != that.lastTnc) return false;
        if (snackReservations != null ? !snackReservations.equals(that.snackReservations) : that.snackReservations != null)
            return false;
        if (seatReservations != null ? !seatReservations.equals(that.seatReservations) : that.seatReservations != null)
            return false;
        return !(client != null ? !client.equals(that.client) : that.client != null);

    }

    @Override
    public int hashCode() {
        int result = (int) (tId ^ (tId >>> 32));
        result = 31 * result + (snackReservations != null ? snackReservations.hashCode() : 0);
        result = 31 * result + (seatReservations != null ? seatReservations.hashCode() : 0);
        result = 31 * result + (client != null ? client.hashCode() : 0);
        result = 31 * result + (int) (cId ^ (cId >>> 32));
        result = 31 * result + (int) (lastTnc ^ (lastTnc >>> 32));
        return result;
    }
}


