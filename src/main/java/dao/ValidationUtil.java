package dao;

import domain.*;

import javax.json.JsonArray;
import javax.json.JsonObject;


public class ValidationUtil {
    static final Object lock = new Object();
    //static int tnc;


    MovieDao movieDao;
    SeatDao seatDao;
    SnackDao snackDao;
    SeatReservationDao seatReservationDao;
    SnackReservationDAO snackReservationDAO;
    TransactionDao transactionDao;

    // Called by mobile client to get tnStart
    //public int getTransactionsId() {
    //    return tnc;
    //}

    //Sets werden als Json von dem Client gesendet (sets= create_set, read_set, update_set, delete_set)
    public boolean isTransactionsValid(JsonArray set, long tnc) {

        for (int i = 0; i < set.size(); i++) {

            JsonObject item = set.getJsonObject(i);
            tnc = item.getJsonNumber("tn").longValue();
            String type = item.getString("type");
            long id = item.getJsonNumber("id").longValue();

            switch (type) {
                case "movies":
                    Movie movie = movieDao.findMovieById(id);
                    Transactions lastTncMovie = movie.getTransactions();
                    if (lastTncMovie.getId() > tnc) {
                        //Transaktion ist nicht valide, weil die read-Version der Ressource nicht aktuell ist
                        return false;
                    }
                    break;
                case "seats":
                    Seat seat = seatDao.findSeatById(id);
                    Transactions lastTncOfSeat = seat.getTransactions();
                    if (lastTncOfSeat.getId() > tnc && seat.getSeatReservation() != null) {
                        return false;
                    }
                    break;
                case "snacks":
                    Snack snack = snackDao.findSnackById(id);
                    Transactions lastTncOfSnack = snack.getTransactions();
                    if (lastTncOfSnack.getId() > tnc) {
                        return false;
                    }
                    break;
                case "snack_bookings":
                    SnackReservation snackReservation = snackReservationDAO.findById(id);
                    Transactions lastTncOfSnRes = snackReservation.getTransactions();
                    if (lastTncOfSnRes.getId() > tnc && snackReservation.getSeatReservation() != null) {

                        return false;
                    }
                    break;
                case "seat_bookings":
                    SeatReservation seatReservation = seatReservationDao.findById(id);
                    Transactions lastTncOfSeatRes = seatReservation.getTransactions();
                    if (lastTncOfSeatRes.getId() > tnc) {
                        //return http response not ok and lastTncOfItem
                        return false;
                    }
                    break;
            }
        }
        return true;
    }



    //Write set besteht aus einer Snack-/SeatReservation mit dazugehörigen Snacks/Seats
    public boolean writeChanges(JsonObject sets) {
        long tnc = sets.getJsonNumber("tnc").longValue();
        boolean valid;

        JsonArray set = sets.getJsonArray("create_set");

        // mit json object von client als parameter
        valid = isTransactionsValid(set, tnc);

        if (valid) {
            synchronized (lock) {
                long tnFinish = transactionDao.getGlobalLastTncNumber()+1;
                valid = isTransactionsValid(set,tnFinish);
                //writePhase
                if (valid) {
                    writeChangesToDatabase(sets);
                }

            }

        }
        return valid;
    }

    private void writeChangesToDatabase(JsonObject sets) {
        //create/update/delete set
        JsonArray createSet = sets.getJsonArray("create_set");
        JsonArray updateSet = sets.getJsonArray("update_set");
        JsonArray deleteSet = sets.getJsonArray("delete_set");

        // Create items
        for (int i = 0; i < createSet.size(); i++) {
            JsonObject item = createSet.getJsonObject(i);
            switch (item.getString("type")) {
                case "seatReservation":
                    seatReservationDao.create(item);
                    break;
                case "snackReservation":
                    snackReservationDAO.create(item);
                    break;
            }
        }


        // Delete items
        for (int i = 0; i < deleteSet.size(); i++) {
            JsonObject item = deleteSet.getJsonObject(i);
            switch (item.getString("type")) {
                case "seatReservation":
                                seatReservationDao.deleteSeatReservation(item);
                    break;
                case "snackReservation":
                                snackReservationDAO.deleteSnackReservation(item);
                    break;
            }
        }
   }
}