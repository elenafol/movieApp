package domain;
import javax.json.JsonObject;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.HashSet.*;
import java.util.Set;

@Entity
public class Transactions {


    @Id
    @GeneratedValue
    private long id;

    @OneToMany(mappedBy = "transactions")
    private Collection<SnackReservation> snackReservations = new ArrayList<>();

    @OneToMany(mappedBy = "transactions")
    private Collection<SeatReservation> seatReservations = new ArrayList<>();

    @OneToMany(mappedBy = "transactions")
    private Collection<Seat> seats = new ArrayList<>();

    @OneToMany(mappedBy = "transactions")
    private Collection<Snack> snacks = new ArrayList<>();

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    //@JoinColumn(name = "client_fk", nullable = false, referencedColumnName="cId")
    private Client client;

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

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Collection<SeatReservation> getSeatReservations() {
        return seatReservations;
    }

    public void setSeatReservations(Collection<SeatReservation> seatReservations) {
        this.seatReservations = seatReservations;
    }

    public Collection<Snack> getSnacks() {
        return snacks;
    }

    public void setSnacks(Collection<Snack> snacks) {
        this.snacks = snacks;
    }

    public long getClientFk() {
        return clientFk;
    }

    public void setClientFk(long clientFk) {
        this.clientFk = clientFk;
    }


    public Transactions() {
    }


    private long clientFk;



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Transactions that = (Transactions) o;

        return id == that.id;

    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }

    //public void fromJsonObject(JsonObject jsonObject) {
    // cId= long.valueOf(jsonObject.getJsonNumber("cId").longValue());
    //lastTnc = jsonObject.getInt("tnc");
    // }



}


