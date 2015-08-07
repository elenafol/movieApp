package service;

import domain.*;

import javax.json.JsonArray;
import javax.json.JsonObject;


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

    //Sets werden als Json von dem Client gesendet (sets= writeset, readset, updateset, deleteset)
    public boolean isTransactionValid(JsonObject sets, long tnc) {

        //Read set ans write sets von Client
        JsonArray readSet = sets.getJsonArray("read_set");

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
        }
        return true;
    }


    //Schreibphase: für write/update/delete set (->write_set)
    //Write set besteht aus einer Snack-/SeatReservation mit dazugehörigen Snacks/Seats
    public boolean writeChanges(JsonObject sets) {
        //int midTn = tnc;
        boolean valid;


        long udi = Long.valueOf(sets.getJsonNumber("client").longValue());
        Client client = clientDao.createClient(udi);

        // mit json object von client als parameter
        valid = isTransactionValid(sets, tnc);

        if (valid) {
            synchronized (lock) {
                int tnFinish = tnc;
                valid = isTransactionValid(sets, tnFinish);
                //writePhase
                if (valid) {
                    writeChangesToDatbase(sets);
                }

            }

        }
        return valid;
    }

    private void writeChangesToDatbase(JsonObject sets) {
        //create/update/delete set (1 set mit Änderungen -> write_set für Schreibphase, alle anderen leer)
        JsonArray createSet = sets.getJsonArray("create_set");
        JsonArray updateSet = sets.getJsonArray("update_set");
        JsonArray deleteSet = sets.getJsonArray("delete_set");

        // Create items
        for (int i = 0; i < createSet.size(); i++) {
            JsonObject item = createSet.getJsonObject(i);
            switch (item.getString("type")) {
                case "seatReservation":
                    reservationService.createSeatReservation(item);
                    break;
                case "snackReservation":
                    reservationService.createSeatReservation(item);
                    break;
            }
        }

        // Update items
        for (int i = 0; i < updateSet.size(); i++) {
            JsonObject item = updateSet.getJsonObject(i);
            switch (item.getString("type")) {
                case "seatReservation":
                    reservationService.updateSeatReservation(item);
                    break;
                case "snackReservation":
                    reservationService.updateSnackReservation(item);
                    break;
            }
        }

        // Delete items
        for (int i = 0; i < deleteSet.size(); i++) {
            JsonObject item = deleteSet.getJsonObject(i);
            switch (item.getString("type")) {
                case "seatReservation":
//                                reservationService.deleteSeatReservation(item); //TODO
                    break;
                case "snackReservation":
//                                reservationService.deleteSnackReservation(item); //TODO
                    break;
            }
        }
    }
}