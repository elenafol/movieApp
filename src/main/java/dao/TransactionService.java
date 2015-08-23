package dao;


import domain.Client;
import domain.SeatReservation;
import domain.SnackReservation;
import domain.Transactions;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

//@Stateless
public class TransactionService {
    @PersistenceContext(unitName = "movieServicePU")
    public EntityManager em;
    private long tnc=0;

    public long getTnc() {
        return tnc;
    }

    public void setTnc(int tnc) {
        this.tnc = tnc;
    }


//
//    public Transactions addTransactions(Client client) {
//        Transactions Transactions = new Transactions();
//        long cId= client.getcId();
//        em.persist(Transactions);
//        return Transactions;
//        }

    public Transactions findTransactionsBySnackReservation(SnackReservation snackReservation) {

        return em.createQuery(
                "select t from Transactions t where t.lastTnc = :lastTnc",Transactions.class)
                .setParameter("lastTnc",snackReservation.getLastTnc())
                .getSingleResult();
    }

    public Transactions findTransactionsBySeatReservation(SeatReservation seatReservation) {

        return em.createQuery(
                "select t from Transactions t where t.lastTnc = :lastTnc",Transactions.class)
                .setParameter("lastTnc",seatReservation.getLastTnc())
                .getSingleResult();
    }

    public Transactions findTransactionsByTnc(long tnc) {
        return em.find(Transactions.class, tnc);

    }

    public Long getGlobalLastTncNumber(){
        return (Long)em.createQuery("select max(t.lastTnc) from Transactions t").getSingleResult();


    }

    }
