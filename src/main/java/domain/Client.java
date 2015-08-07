package domain;
import javax.persistence.*;
import java.util.Set;


@Entity
@Table(name="client")
public class Client {

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "client")
    private Set<Transaction> transactions;

    public Set <Transaction> getTransactions() {
        return transactions;
    }

    public Client() {

    }

    @Id
    @GeneratedValue
    @Column(name="cId")
    private  long cId;

    @Column(name="udI")
    private  long udI;

    public long getcId() {
        return cId;
    }

    public void setcId(long cId) {
        this.cId = cId;
    }

    public long getUdI() {
        return udI;
    }

    public void setUdI(long udI) {
        this.udI = udI;
    }

    @Override
    public String toString() {
        return "Client{" +
                "transactions=" + transactions +
                ", cId=" + cId +
                ", udI=" + udI +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Client client = (Client) o;

        if (cId != client.cId) return false;
        if (udI != client.udI) return false;
        return !(transactions != null ? !transactions.equals(client.transactions) : client.transactions != null);

    }

    @Override
    public int hashCode() {
        int result = transactions != null ? transactions.hashCode() : 0;
        result = 31 * result + (int) (cId ^ (cId >>> 32));
        result = 31 * result + (int) (udI ^ (udI >>> 32));
        return result;
    }

    //Für die Validerung
   /* private int trn = 0;

    int getTrn() {
        return trn;
    }

    public void setTrn(int trn) {
        this.trn = trn;
    }

    private Transaction t = new Transaction();
    public int incTrn() {return ++this.trn;}



    public Client(Transaction t/*//**/


}