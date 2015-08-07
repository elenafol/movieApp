package rest;


import domain.Car;
import domain.Person;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.ws.rs.*;
import java.util.List;

@Stateless
@Path("person")
@Consumes("application/json")
@Produces("application/json")
public class PersonService {

    @EJB
    PersonDAO dao;

    @GET
    @Path("{id}")
    public String getPerson(@PathParam("id") Long id) {
        Person person = dao.findById(id);
        if (person == null)
            throw new NotFoundException("Could not find person with id=" + id);
        return personToJson(person).toString();
    }


    @GET
    public String getAll(@PathParam("id") Long id) {
        List<Person> all = dao.findAll();
        JsonArrayBuilder jsonArray = Json.createArrayBuilder();
        for (Person person : all) {
            jsonArray.add(personToJson(person));
        }
        JsonArray json = jsonArray.build();
        return json.toString();
    }

    @POST
    public String addPerson(JsonObject object) {
        Person p = dao.create(object);
        return personToJson(p).toString();
    }

    @POST
    @Path("{id}/car")
    public String addCar(@PathParam("id") Long id, JsonObject object) {
        Person person = dao.findById(id);
        if (person == null)
            throw new NotFoundException("Could not find person with id=" + id);
        dao.addCar(person, object);
        return personToJson(person).toString();
    }


    public static JsonObject personToJson(Person p) {

        // Create json array for all cars
        JsonArrayBuilder carBuilder = Json.createArrayBuilder();
        for (Car car : p.getCars()) {
            carBuilder.add(carToJson(car));
        }

        // Create person json
        return Json.createObjectBuilder()
                .add("id", p.getId())
                .add("name", p.getName())
                .add("cars", carBuilder.build())
                .build();
    }

    public static JsonObject carToJson(Car p) {
        return Json.createObjectBuilder()
                .add("id", p.getId())
                .add("color", p.getColor())
                .build();
    }

}
