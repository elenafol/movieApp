package service;


import domain.Client;


import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class ClientDao {

    @PersistenceContext(unitName = "movieServicePU")
    public EntityManager em;

    public Client findClientByUdi(long udi) {
        return em.find(Client.class, udi);
    }

    public Client findClientById(long id) {
        return em.find(Client.class, id);
    }
//
        public Client createClient( long udI) {
        Client client = new Client();

        // existiert der Client bereits?
        Client tmp = findClientByUdi(udI);

        if (tmp == null) {

            client = new Client();
            client.setUdI(udI);
            em.persist(client);

        } else {
            client = null;
        }

        return client;
    }

}
