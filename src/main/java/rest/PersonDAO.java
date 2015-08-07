package rest;


import domain.Car;
import domain.Person;

import javax.ejb.Stateless;
import javax.json.JsonObject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Stateless
public class PersonDAO {

    @PersistenceContext
    EntityManager em;


    public Person findById(Long id) {
        return em.find(Person.class, id);
    }

    public List<Person> findAll() {
        TypedQuery<Person> query = em.createQuery("SELECT p FROM Person p", Person.class);
        return query.getResultList();
    }

    public Person create(JsonObject object) {
        Person p = new Person();
        p.setName(object.getString("name"));
        em.persist(p);
        return p;
    }

    public void addCar(Person person, JsonObject object) {
        // Create new car
        Car car = new Car();
        car.setColor(object.getString("color"));
        car.setOwner(person);
        person.getCars().add(car);

        // Persists car + update person
        em.persist(car);
        em.merge(person);
    }
}
