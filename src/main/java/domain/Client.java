package domain;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;


@Entity
public class Client {

    @Id
    @GeneratedValue
    private  long id;
    private  long udId;

    @OneToMany(mappedBy = "client")
    private Collection<Transactions> transactions = new ArrayList<>();



    public Client() {

    }

    public Collection<Transactions> getTransactions() {
        return transactions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Client client = (Client) o;

        return id == client.id;

    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }

    public void setTransactions(Collection<Transactions> transactions) {
        this.transactions = transactions;
    }

    public long getUdId() {
        return udId;

    }

    public void setUdId(long udId) {
        this.udId = udId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    //Für die Validerung
   /* private int trn = 0;

    int getTrn() {
        return trn;
    }

    public void setTrn(int trn) {
        this.trn = trn;
    }

    private Transactions t = new Transactions();
    public int incTrn() {return ++this.trn;}
*/


}