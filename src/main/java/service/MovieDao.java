package service;


import domain.Movie;
import domain.Seat;
import domain.Transaction;


import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Stateless
public class MovieDao implements Serializable{

    @PersistenceContext(unitName = "movieServicePU")
    public EntityManager em;


    public List getAll() {

            return em.createQuery(
                    "select m from movie m")
                    .getResultList();
        }


    public Movie findMovieById(long mId) {
        return em.find(Movie.class, mId);

    }

    /*public Movie findMovieByTnc(Transaction transaction) {
        return em.createQuery(
                "select m from Movie m where m.lastTnc = :tnc", Movie.class).
                setParameter("tnc",transaction.getLastTnc() ).getSingleResult();
}*/

}
