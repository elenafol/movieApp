package dao;


import domain.Seat;
import domain.SnackReservation;
import domain.Snack;
import domain.Transactions;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.io.Serializable;
import java.util.List;
@Stateless
public class SnackDao implements Serializable{

    @PersistenceContext(unitName = "movieServicePU")
    public EntityManager em;
    public List<Snack> findAll() {

        TypedQuery<Snack> query = em.createQuery("SELECT s FROM Snack s", Snack.class);
        return query.getResultList();
    }


    //alle zu einer Reservierung
    public List<Snack> findSnackByReservation(SnackReservation snackReservation) {

        List<Snack> snacks= em.createQuery("select s from Snack s where s.snackReservation_fk = snackReservation.id", Snack.class).getResultList();
        return snacks;
    }

    public List <Snack> findSnackByTnc(Transactions Transactions){

        List<Snack> snacks= em.createQuery("select s from Snack s where s.lastTnc = Transactions.id", Snack.class).getResultList();
        return snacks;
    }

    public void updateSnack(long snId, SnackReservation snackReservation, int lastTnc) {

        Snack snack = em.find(Snack.class, snId);
        snack.setLastTnc(lastTnc);
        snack.setSnackReservation(snackReservation);
        em.merge(snack);

    }

    public Snack findSnackById(long id){
        return em.createQuery("select s from Seat s where s.snId= :snackId", Snack.class)
                .setParameter("snackId", id)
                .getSingleResult();
    }


}
