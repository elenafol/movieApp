package dao;

import domain.Movie;
import domain.Seat;
import domain.SeatReservation;
import domain.Transactions;

import javax.ejb.Stateless;
import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Stateless
public class SeatDao implements Serializable{

    @PersistenceContext(unitName = "movieServicePU")
    public EntityManager em;

    public List<Seat> findAll() {
        TypedQuery<Seat> query = em.createQuery("SELECT s FROM Seat s", Seat.class);
        return query.getResultList();
    }


    public List<Seat> findSeatsbyMovie(Movie movie) {

        return em.createQuery("select s from Seat s where s.movie_fk = movie.id",Seat.class).getResultList();
}



    public List <Seat> findAllAvailableByMovie(Movie movie) {
        return em.createQuery(
                "select s from Seat s where s.movie_fk=movie.id and s.seatReservation IS NULL", Seat.class).getResultList();
    }


    public Seat findSeatByTnc(Transactions Transactions){
        return em.createQuery("select s from Seat s where s.lastTnc = Transactions.id", Seat.class).getSingleResult();
    }

    public Seat findSeatById(long id){
        return em.createQuery("select s from seat s where s.sId= :seatId", Seat.class)
                .setParameter("seatId", id)
                .getSingleResult();
    }


    public void updateSeat(Long sId, SeatReservation seatReservation, Transactions transaction) {

        em.getTransaction().begin();
        Seat seat = em.find(Seat.class, sId);
        seat.setTransactions(transaction);
        seat.setSeatReservation(seatReservation);
        em.merge(seat);
        em.getTransaction().commit();

    }

}
