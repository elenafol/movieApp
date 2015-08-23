package dao;


import domain.Client;

import javax.ejb.Stateless;
import javax.json.JsonObject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;

@Stateless
public class ClientDao implements Serializable{

    //@PersistenceContext(unitName = "movieServicePU")
    public EntityManager em;

    public Client findClientByUdid(long udid) {
        return em.find(Client.class, udid);
    }

    //public Client findClientById(long id) {
    //    return em.find(Client.class, id);
    //}



        public Client createClient(JsonObject object) {

            em.getTransaction().begin();
            Client c = new Client();
            c.setUdId(object.getJsonNumber("udId").longValue());
            em.merge(c);
            em.getTransaction().commit();
            return c;


    }

}
