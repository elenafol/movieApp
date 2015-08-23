package rest;


import domain.*;
import dao.*;

import javax.ejb.EJB;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.persistence.PrePersist;
import javax.transaction.Transaction;
import javax.ws.rs.*;
import java.util.List;

@Path("/initial")
public class InitialStartService {

    @EJB
    ClientDao clientDao;

    @EJB
    MovieDao movieDao;

    @EJB
    SeatDao seatDao;

    @EJB
    SnackDao snackDao;

    @EJB
    SeatReservationDao seatReservationDao;

    @EJB
    SnackReservationDAO snackReservationDAO;

    @EJB
    TransactionDao transactionDao;


    @POST
    @Consumes ("application/json")
    public String addClient(JsonObject object) {
        Client c = clientDao.createClient(object);
        return clientToJson(c).toString();
    }

    public static JsonObject clientToJson(Client c) {
        return Json.createObjectBuilder()
                .add("id", c.getId())
                .build();
    }


    @GET
    @Path("seats")
    @Produces ("application/json")
    public String getSeats(){
        List<Seat> allSeats = seatDao.findAll();
        JsonArrayBuilder jsonArray = Json.createArrayBuilder();
        for (Seat seat:allSeats){
            jsonArray.add(seatToJson(seat));
        }
        JsonArray json = jsonArray.build();
        return json.toString();
    }


    public static  JsonObject seatToJson (Seat s){
        return Json.createObjectBuilder()
                .add("movie_id", s.getMovie_fk())
                .add("type", "seats")
                .add("tn", s.getLastTnc())
                .add("id", s.getId())
                .build();
    }

    @GET
    @Path("snacks")
    @Produces("application/json")
    public String getSnacks(){
        List<Snack> allSnacks = snackDao.findAll();
        JsonArrayBuilder jsonArray = Json.createArrayBuilder();
        for (Snack snack:allSnacks){
            jsonArray.add(snackToJson(snack));
        }
        JsonArray json = jsonArray.build();
        return json.toString();
    }

    public  static  JsonObject snackToJson (Snack s){
        return Json.createObjectBuilder()
                .add("snack_id", s.getId())
                .add("type", "snacks")
                .add("snackType", s.getSnackType())
                .add("tn",s.getLastTnc())
                .build();
    }


    @GET
    @Path("{id}/mySnackRes")
    @Produces("application/json")
    public String getSnackReservations (@PathParam("id") Long id){
        List<SnackReservation> allReservations = snackReservationDAO.findByClient(id);
        JsonArrayBuilder jsonArray = Json.createArrayBuilder();
        for (SnackReservation snackReservation : allReservations){
            jsonArray.add(snackResToJson(snackReservation));
        }
        JsonArray json = jsonArray.build();
        return json.toString();
    }


    public  static  JsonObject snackResToJson (SnackReservation s){
        JsonArrayBuilder snackBuilder = Json.createArrayBuilder();
        for (Snack snack : s.getSnack()) {
            snackBuilder.add(snackToJson(snack));
        }

        return Json.createObjectBuilder()
                .add("booking_id", s.getId())
                .add("snacks", snackBuilder.build())
                .add("type", "snack_bookings")
                .add("tn",s.getLastTnc())
                .build();
    }

    @GET
    @Path("{id}/mySeatRes")
    @Produces("application/json")
    public String getSeatReservations (@PathParam("id") Long id){
      List<SeatReservation> allReservations = seatReservationDao.findByClient(id);
        JsonArrayBuilder jsonArray = Json.createArrayBuilder();
        for (SeatReservation seatReservation : allReservations) {
            jsonArray.add(seatResToJson(seatReservation));
        }
        JsonArray json =jsonArray.build();
        return json.toString();
    }

    public  static  JsonObject seatResToJson (SeatReservation s) {
        JsonArrayBuilder seatBuilder = Json.createArrayBuilder();
        for (Seat seat : s.getSeats()) {
            seatBuilder.add(seatToJson(seat));
        }

        return Json.createObjectBuilder()
                .add("booking_id", s.getId())
                .add("seats",seatBuilder.build())
                .add("type", "seat_bookings")
                .add("tn",s.getLastTnc())
                .build();
    }

    @GET
    public JsonObject getGlobalTn(){

        return Json.createObjectBuilder()
                .add("global_tn",transactionDao.getGlobalLastTncNumber())
                .build();
    }

}
