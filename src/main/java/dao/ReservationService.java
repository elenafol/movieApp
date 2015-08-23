package dao;
import domain.*;

import javax.json.JsonObject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.util.List;

//@Stateless
public class ReservationService  implements Serializable{


//    @PersistenceContext(unitName = "movieServicePU")
//    public EntityManager em;
//
//      public List<SeatReservation> findSeatReservationByClient(Client client, Transactions Transactions) {
//
//        return em.createQuery("select sr from SeatReservation sr, sr.lastTnc, " +
//                        "Transactions.tId  join Transactions " +
//                        "where sr.lastTnc=:tId and Transactions.client_fk=:cId",
//                SeatReservation.class)
//                .setParameter("cId", client.getcId()).setParameter("tId",Transactions.gettId())
//                .getResultList();
//    }
//
//    public List<SnackReservation> findSnackReservationByClient(Client client, Transactions Transactions) {
//
//        return em.createQuery("select snr from snackReservation snr, snackReservation.lastTnc, " +
//                        "Transactions.tId  join Transactions " +
//                        "where Reservation .lastTnc=:tId and Transactions.client_fk=:cId",
//                SnackReservation.class)
//                .setParameter("cId", client.getcId()).setParameter("tId", Transactions.gettId())
//                .getResultList();
//    }
//
//
// /*   public SnackReservation addSnacks (long rId, long lastTnc, Set<Snack> snack) {
//
//        SnackReservation reservation = em.find(SnackReservation.class, rId);
//        reservation.setLastTnc(lastTnc);
//        reservation.setSnack(snack);
//        em.merge(reservation);
//        return reservation;
//    }*/
//
//    public SeatReservation updateSeatReservation (JsonObject jsonObject){
//        SeatReservation seatReservation = new SeatReservation();
//        seatReservation.fromJsonObject(jsonObject);
//        em.merge(seatReservation);
//        return seatReservation;
//    }
//
//    public SnackReservation updateSnackReservation (JsonObject jsonObject){
//        SnackReservation snackReservation = new SnackReservation();
//        snackReservation.fromJsonObject(jsonObject);
//        em.merge(snackReservation);
//        return snackReservation;
//    }
//
//    //create seatReservation(writeSet)
//    public SeatReservation createSeatReservation (JsonObject jsonObject){
//        SeatReservation seatReservation = new SeatReservation();
//        seatReservation.fromJsonObject(jsonObject);
//
//        seatReservation = SeatReservation.fromJsonObject2(jsonObject);
//
//        em.persist(seatReservation);
//        return seatReservation;
//    }
//
//    //create snackReservation(writeSet)
//    public SnackReservation createSnackReservation (JsonObject jsonObject){
//        SnackReservation snackReservation = new SnackReservation();
//        snackReservation.fromJsonObject(jsonObject);
//        em.persist(snackReservation);
//        return snackReservation;
//    }
//
//    public void deleteSeatReservation(SeatReservation reservation) {
//        em.remove(em.getReference(SeatReservation.class, reservation.getSrId()));
//    }
//
//    public void deleteSnackReservation(SnackReservation reservation) {
//        em.remove(em.getReference(SnackReservation.class, reservation.getSnrId()));
//    }
//
//    public SeatReservation findSeatReservationById(long id){
//        return em.createQuery("select sr from seatReservation sr where sr.Id= :seatResId", SeatReservation.class)
//                .setParameter("seatResId", id)
//                .getSingleResult();
//    }
//
//    public SnackReservation findSnackReservationById(long id){
//        return em.createQuery("select snr from snackReservation snr where snr.Id= :snackResId", SnackReservation.class)
//                .setParameter("snackResId", id)
//                .getSingleResult();
//    }

}
