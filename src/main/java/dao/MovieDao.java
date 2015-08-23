package dao;


import domain.Movie;
import domain.Transactions;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.io.Serializable;
import java.util.List;

@Stateless
public class MovieDao implements Serializable{

    @PersistenceContext(unitName = "movieServicePU")
    public EntityManager em;


    public List<Movie> findAll() {

        TypedQuery<Movie> query = em.createQuery("SELECT m FROM Movie m", Movie.class);
        return query.getResultList();
        }


    public Movie findMovieById(long mId) {
        return em.find(Movie.class, mId);

    }

    public Movie findMovieByTnc(Transactions transactions) {
        return em.createQuery(
                "select m from Movie m where m.lastTnc = :tnc", Movie.class).
                setParameter("tnc",transactions.getId() ).getSingleResult();
}

    public Long getGlobalLastTncNumber(){
        return (Long)em.createQuery("select max(m.lastTnc) from Movie m").getSingleResult();

    }


}
