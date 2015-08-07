package rest;
import domain.Client;
import domain.Movie;
import domain.Seat;
import domain.Snack;
import service.*;

import javax.ejb.EJB;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;




public class MovieAppService {


    @EJB
    private MovieDao movieDao;

    @EJB
    private SeatDao seatDao;

    @EJB
    private SnackDao snackDao;

    @EJB
    private ReservationService reservationService;

    @EJB
    private ClientDao clientDao;

    @EJB
    private TransactionService transactionService;

    private ValidationUtil validationUtil;



    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("movie/{id}")
    public Movie findMovie(@PathParam("mId") long mid)  {
        final Movie movie = movieDao.findMovieById(mid);
        return movie;
    }


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("movie/{id}/seats")
    public List<Seat> findSeatsByMovie(@PathParam("id") long mId)  {

        final Movie movie = movieDao.findMovieById(mId);
        final List<Seat> seats = null;
        seatDao.findSeatsbyMovie(movie);
        return seats;
        }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("seats/{id}")
    public Seat findSeatById(@PathParam("id") long sId)  {

        final Seat seat = seatDao.findSeatById(sId);
        return seat;
    }

    @GET
    @Path("snacks/{id}")
    public Snack findSnackById(@PathParam("id") long sId)  {

        final Snack snack = snackDao.findSnackById(sId);
        return snack;
    }



    // Validierngsphase (anhand von read-Set)
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response validateReservation(JsonObject sets, long tnc) {

        //Client mit Unique Device Identifier
        long udi = sets.getJsonNumber("client").longValue();
        Client client = clientDao.createClient(udi);

        //read_set aus json-input
        JsonArray readSet = sets.getJsonArray("read_set");
        // ReadTnc von erster Ressource in readSet (readTnc sind bei allen Ressourcen gleich)
        tnc = readSet.getJsonObject(0).getJsonNumber("tn").longValue();
        //Validierung
        boolean isValid = validationUtil.isTransactionValid(sets, tnc);
        //json object mit Transaktionsnummer, gegen welche die Validierung erfolgte und Status der Validierung
        JsonObject responseObject = Json.createObjectBuilder()
                .add("midTn", tnc)
                .add("isValid", isValid)
                .build();
        //war die Validierung erfolgreich, so wird response status = 200 (ok) mit jsonObject an Client gesendet
        if(isValid) {
            return Response.ok().entity(responseObject).build();
        }
        //war die Validierung nicht erfolgreich, so wird response status = 409 (Conflict) mit jsonObject an Client gesendet
        else {
            return Response.status(409).entity(responseObject).build();
        }
    }

    //Write-Phase (write_set als input)
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createReservation(JsonObject sets, long finishTn) {

        long udi = sets.getJsonNumber("client").longValue();
        Client client = clientDao.createClient(udi);
        boolean isValid = validationUtil.writeChanges(sets);
        finishTn = transactionService.getGlobalLastTncNumber();
        //json object mit der aktuell höchster Transaktionsnummer und Status der Write-Phase
        JsonObject responseObject = Json.createObjectBuilder()
                .add("finishTn", finishTn)
                .add("isValid", isValid)
                .build();
        if(isValid) {
            return Response.status(200).entity(responseObject).build();
        }
        else {
            return Response.status(409).entity(responseObject).build();
        }
    }


}
