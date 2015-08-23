package dao;


import domain.Client;
import domain.Transactions;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;


@Stateless
public class TransactionDao implements Serializable{

    @PersistenceContext(unitName = "movieServicePU")
    public EntityManager em;


    public Transactions addTransactions(Client client) {
        Transactions transactions = new Transactions();
        transactions.setClient(client);
        em.persist(transactions);
        return transactions;

    }

    public Transactions findTransactionsById(Long id) {
        return em.find(Transactions.class, id);

    }

    public Long getGlobalLastTncNumber(){
        return (Long)em.createQuery("select max(t.id) from Transactions t").getSingleResult();

    }
}
