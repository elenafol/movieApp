package service;

import domain.Movie;
import domain.Item;
import domain.SeatReservation;
import domain.Seat;
import domain.Transaction;

import javax.ejb.Stateless;
import javax.persistence.*;
import java.util.List;

//@Stateless
public class SeatDao {

    @PersistenceContext(unitName = "movieServicePU")
    public EntityManager em;


    public List<Seat> findSeatsbyMovie(Movie movie) {

        return em.createQuery("select s from Seat s where s.mId= : mId",
                Seat.class)
                .setParameter("mId", movie.getMId())
                .getResultList();
}



    public List <Seat> findAllAvailableByMovie(Movie movie) {
        return em.createQuery(
                "select s from Seat s where s.movie.id = :mId and s.seatReservation IS NULL", Seat.class)
                .setParameter("mId", movie.getMId())
                .getResultList();
    }


    public Seat findSeatByTnc(Transaction transaction){
        return em.createQuery("select s from seat s where s.lastTnc= :lastTnc", Seat.class)
                .setParameter("lastTnc", transaction.getLastTnc())
                .getSingleResult();
    }

    public Seat findSeatById(long id){
        return em.createQuery("select s from seat s where s.sId= :seatId", Seat.class)
                .setParameter("seatId", id)
                .getSingleResult();
    }


    public void delete(Seat seat) {
        em.remove(em.getReference(Seat.class, seat.getsId()));
    }



}
