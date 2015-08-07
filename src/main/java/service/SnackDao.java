package service;


import domain.SnackReservation;
import domain.Snack;
import domain.Transaction;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import java.util.List;
//@Stateless
public class SnackDao {

    @PersistenceContext(unitName = "movieServicePU")
    public EntityManager em;


    //alle zu einer Reservierung
    public List<Snack> findSnackByReservation(SnackReservation snackReservation) {

        List<Snack> snacks= em.createQuery("select sn from Snack sn where sn.id=:rId",
                Snack.class)
                .setParameter("rId", snackReservation.getSnrId())
                .getResultList();
        return snacks;
    }

    public Snack findSnackByTnc(Transaction transaction){
        return em.createQuery("select sn from Snack sn where sn.lastTnc= :lastTnc", Snack.class)
                .setParameter("lastTnc", transaction.getLastTnc())
                .getSingleResult();
    }

    public Snack update(long snId, SnackReservation snackReservation, int lastTnc) {

        Snack snack = em.find(Snack.class, snId);
        snack.setLastTnc(lastTnc);
        snack.setSnackReservation(snackReservation);
        em.merge(snack);
        return snack;
    }

    public Snack findSnackById(long id){
        return em.createQuery("select s from Seat s where s.snId= :snackId", Snack.class)
                .setParameter("snackId", id)
                .getSingleResult();
    }


    public void delete(Snack snack) {
        em.remove(em.getReference(Snack.class, snack.getSnId()));
    }

}
