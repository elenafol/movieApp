package dao;

import domain.Seat;
import domain.SeatReservation;
import domain.SnackReservation;
import dao.SeatDao;

import javax.ejb.Stateless;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.io.Serializable;
import java.util.List;

@Stateless
public class SeatReservationDao implements Serializable {

    @PersistenceContext
    EntityManager em;

    SeatDao seatDao;
    SnackReservationDAO snackReservationDAO;

    public SeatReservation findById(Long id) {
        return em.find(SeatReservation.class, id);
    }

    public List<SeatReservation> findByClient (Long id){

        return em.createQuery("SELECT s FROM SeatReservation s WHERE s.lastTnc = Transactions.id and Transactions.clientFk = :clientId", SeatReservation.class).
                setParameter("clientId", id).getResultList();
    }

    public List<SeatReservation> findAll() {
        TypedQuery<SeatReservation> query = em.createQuery("SELECT s FROM SnackReservation s", SeatReservation.class);
        return query.getResultList();
    }

    public SeatReservation create(JsonObject object) {
        em.getTransaction().begin();
        SeatReservation sr = new SeatReservation();
        sr.setId(object.getJsonNumber("id").longValue());
        JsonArray seats = object.getJsonArray("seats");
       for(int i = 0; i < seats.size(); i++){
           JsonObject seat = seats.getJsonObject(i);
          Long id=  seat.getJsonNumber("id").longValue();
           Seat s= seatDao.findSeatById(id);
           s.setSeatReservation(sr);
           em.merge(s);
       }
        JsonArray snackReservations = object.getJsonArray("snack_bookings");
        for(int i = 0; i < snackReservations.size(); i++){
            JsonObject snackReservation = snackReservations.getJsonObject(i);
            Long id=  snackReservation.getJsonNumber("id").longValue();
            SnackReservation snr= snackReservationDAO.findById(id);
            snr.setSeatReservation(sr);
            em.merge(snr);
        }

        em.persist(sr);
        em.getTransaction().commit();
        return sr;
    }


    public void deleteSeatReservation (JsonObject object){
       SeatReservation sr = findById( object.getJsonNumber("id").longValue());
        em.getTransaction().begin();
        em.remove(sr);
        em.getTransaction().commit();
    }

}
