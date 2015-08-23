package rest;

import javax.ejb.EJB;
import javax.json.Json;import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import dao.*;

@Path("/reservation")

public class ReservationService {


    @EJB
    private MovieDao movieDao;

    @EJB
    private SeatDao seatDao;

    @EJB
    private SnackDao snackDao;

    @EJB
    private SnackReservationDAO snackReservationDAO;

    @EJB
    private  SeatReservationDao seatReservationDao;

    @EJB
    private ClientDao clientDao;

    @EJB
    private TransactionDao transactionDao;

    private ValidationUtil validationUtil;


    // Validierngsphase (anhand von read-Set)
    @GET
    //@Produces(MediaType.APPLICATION_JSON)
    //@Consumes(MediaType.APPLICATION_JSON)
    public Response validateReservation(JsonObject sets, long tnc) {


        //read_set aus json-input
        JsonArray readSet = sets.getJsonArray("read_set");
        // ReadTnc von erster Ressource in readSet (readTnc sind bei allen Ressourcen gleich)
        tnc = readSet.getJsonObject(0).getJsonNumber("tn").longValue();
        //Validierung
        boolean isValid = validationUtil.isTransactionsValid(readSet, tnc);
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


        boolean isValid = validationUtil.writeChanges(sets);
        finishTn = transactionDao.getGlobalLastTncNumber();
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
