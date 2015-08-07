package service;


import com.google.gson.JsonObject;
import domain.Client;
import domain.SeatReservation;
import domain.SnackReservation;
import domain.Transaction;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
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



    public Transaction addTransaction(Client client) {
        Transaction transaction = new Transaction();
        long cId= client.getcId();
        em.persist(transaction);
        return transaction;
        }

    public Transaction findTransactionBySnackReservation(SnackReservation snackReservation) {

        return em.createQuery(
                "select t from Transaction t where t.lastTnc = :lastTnc",Transaction.class)
                .setParameter("lastTnc",snackReservation.getLastTnc())
                .getSingleResult();
    }

    public Transaction findTransactionBySeatReservation(SeatReservation seatReservation) {

        return em.createQuery(
                "select t from Transaction t where t.lastTnc = :lastTnc",Transaction.class)
                .setParameter("lastTnc",seatReservation.getLastTnc())
                .getSingleResult();
    }

    public Transaction findTransactionByTnc(long tnc) {
        return em.find(Transaction.class, tnc);

    }

    public Long getGlobalLastTncNumber(){
        return (Long)em.createQuery("select max(t.lastTnc) from Transaction t").getSingleResult();


    }

    }
