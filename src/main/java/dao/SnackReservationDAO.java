package dao;


import domain.*;

import javax.ejb.Stateless;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.io.Serializable;
import java.util.List;

@Stateless
public class SnackReservationDAO implements Serializable{

    @PersistenceContext
    EntityManager em;

    SnackDao snackDao;


    public SnackReservation findById(Long id) {
        return em.find(SnackReservation.class, id);
    }

    public List<SnackReservation> findAll() {
        TypedQuery<SnackReservation> query = em.createQuery("SELECT s FROM SnackReservation s", SnackReservation.class);
        return query.getResultList();
    }

    public SnackReservation findSnackReservationByTnc (Transactions Transactions) {
        return em.createQuery(
                "select s from SnackReservation s where s.lastTnc = :tnc", SnackReservation.class).
                setParameter("tnc",Transactions.getId() ).getSingleResult();
    }



    public List<SnackReservation> findByClient (Long id){

        return em.createQuery("SELECT s FROM SnackReservation s WHERE s.lastTnc = Transactions.id and Transactions.clientFk = :clientId", SnackReservation.class).
                setParameter("clientId", id).getResultList();
    }

    public List<SnackReservation> findBySeatReservation (SeatReservation seatReservation){

        return em.createQuery("SELECT s FROM SnackReservation s WHERE s.seatReservation_fk = seatReservation.id", SnackReservation.class).getResultList();
    }

    public SnackReservation create(JsonObject object) {
        em.getTransaction().begin();
        SnackReservation sr = new SnackReservation();
        sr.setId(object.getJsonNumber("id").longValue());
        JsonArray snacks = object.getJsonArray("snacks");
        for(int i = 0; i < snacks.size(); i++){
            JsonObject snack = snacks.getJsonObject(i);
            Long id=  snack.getJsonNumber("id").longValue();
            Snack s= snackDao.findSnackById(id);
            s.setSnackReservation(sr);
            em.merge(s);
        }
        em.persist(sr);
        em.getTransaction().commit();
        return sr;
    }


    public void deleteSnackReservation(JsonObject object){
        SnackReservation sr = findById( object.getJsonNumber("id").longValue());
        em.getTransaction().begin();
        em.remove(sr);
        em.getTransaction().commit();
    }

}
