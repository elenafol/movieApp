package service;

import domain.*;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.json.JsonArray;
import javax.json.JsonObject;

/**
 * Created by Elena on 30.07.2015.
 */
public class ValidationUtil {
    static final Object lock = new Object();
    static int tnc;

    TransactionService transactionService;
    private Transaction t = new Transaction();

    private MovieDao movieDao;

    private SeatDao seatDao;

    private SnackDao snackDao;

    private ReservationService reservationService;

    private ClientDao clientDao;

    // Called by mobile client to get tnStart
    public int getTransactionId() {
        return tnc;
    }

    //Read set ans write sets von Client
    JsonArray readSet = sets.getJsonArray("read_set");
    //create/update/delete set (1 set mit Änderungen -> write_set für Schreibphase, alle anderen leer)
    JsonArray createSet = sets.getJsonArray("create_set");
    JsonArray updateSet = sets.getJsonArray("update_set");
    JsonArray deleteSet = sets.getJsonArray("delete_set");

    //Sets werden als Json von dem Client gesendet (sets= writeset, readset, updateset, deleteset)
    public boolean isTransactionValid(JsonObject sets, long tnc) {


        for (int i = 0; i < readSet.size(); i++) {

            JsonObject item = readSet.getJsonObject(i);
            //long readTnc = item.getJsonNumber("tn").longValue();
            String type = item.getString("type");
            long id = item.getJsonNumber("id").longValue();

            switch (type) {
                case "movies":
                    Movie movie = movieDao.findMovieById(id);
                    long lastTncMovie = movie.getLastTnc();
                    if (lastTncMovie > tnc) {
                        //Transaktion ist nicht valide, weil die read-Version der Ressource nicht aktuell ist
                        return false;
                    }
                    break;
                case "seat":
                    Seat seat = seatDao.findSeatById(id);
                    long lastTncOfSeat = seat.getLastTnc();
                    if (lastTncOfSeat > tnc && seat.getSeatReservation() != null) {
                        return false;
                    }
                    break;
                case "snack":
                    Snack snack = snackDao.findSnackById(id);
                    long lastTncOfSnack = snack.getLastTnc();
                    if (lastTncOfSnack > tnc) {
                        return false;
                    }
                    break;
                case "snackReservation":
                    SnackReservation snackReservation = reservationService.findSnackReservationById(id);
                    long lastTncOfSnRes = snackReservation.getLastTnc();
                    if (lastTncOfSnRes > tnc && snackReservation.getSeatReservation() != null) {

                        return false;
                    }
                    break;
                case "seatReservation":
                    SeatReservation seatReservation = reservationService.findSeatReservationById(id);
                    long lastTncOfSeatRes = seatReservation.getLastTnc();
                    if (lastTncOfSeatRes > tnc) {
                        //return http responst not ok and lastTncOfItem
                        return false;
                    }
                    break;
            }
            return true;

        }
    }


    //Schreibphase: für write/update/delete set (->write_set)
    //Write set besteht aus einer Snack-/SeatReservation mit dazugehörigen Snacks/Seats
    public boolean writeChanges(JsonObject sets) {
        //int midTn = tnc;
        boolean valid;

    public String writeSetType() {
        if (updateSet.isEmpty() && deleteSet.isEmpty()) {
            JsonArray writeSet = createSet;
            String setType = "create";
        } else if (updateSet.isEmpty() && createSet.isEmpty()) {
            JsonArray writeSet = deleteSet;
            String setType = "delete";
        } else
            JsonArray writeSet = updateSet;
        String setType = "update";
        return setType;
    }

    long udi = Long.valueOf(sets.getJsonNumber("client").longValue());
    Client client = clientDao.createClient(udi);

    // mit json object von client als parameter
    valid= isTransactionValid(sets, tnc);

    if(valid){
        synchronized (lock) {
            int tnFinish = tnc;
            valid = isTransactionValid(sets, tnFinish);


            //writePhase
            if (valid) {
                //Änderung von Client werden geschrieben
                //Get Paramter from Json Object

                for (int i = 0; i < writeSet.size(); i++) {

                    JsonObject item = writeSet.getJsonObject(i);
                    String type = item.getString("type");


                    if ("seatReservation".equalsIgnoreCase(type)) {
                        switch (writeSetType()) {
                            case "create":
                                reservationService.createSeatReservation(item);
                                break;
                            case "update":
                                reservationService.updateSeatReservation(item);
                                break;
                            case "delete":
                                reservationService.deleteSeatReservation(item);
                                break;
                        }


                        else if ("snackReservation".equalsIgnoreCase(type)) {
                            switch (writeSetType()) {
                                case "create":
                                    reservationService.createSnackReservation(item);
                                    break;
                                case "update":
                                    reservationService.updateSnackReservation(item);
                                    break;
                                case "delete":
                                    reservationService.deleteSnackReservation(item);
                                    break;
                            }
                            transactionService.addTransaction(client);
                        }

                    }

                    return valid;
                }
            }
        }
    }
}
}