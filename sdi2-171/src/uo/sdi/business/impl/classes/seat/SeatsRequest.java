package uo.sdi.business.impl.classes.seat;

import uo.sdi.business.exception.EntityAlreadyExistsException;
import uo.sdi.infrastructure.Factories;
import uo.sdi.model.Seat;
import uo.sdi.model.SeatStatus;
import uo.sdi.model.Trip;
import uo.sdi.persistence.SeatDao;
import uo.sdi.persistence.exception.AlreadyPersistedException;

public class SeatsRequest {

    public void request(Long idTrip, Long idUser)
	    throws EntityAlreadyExistsException {

	SeatDao sd = Factories.persistence.createSeatDao();

	if (sd.findByUserAndTrip(idUser, idTrip) != null)
	    throw new EntityAlreadyExistsException();
	Trip trip = Factories.persistence.createTripDao().findById(idTrip);
	if (trip.getAvailablePax() <= 0) {
	    Seat seat = new Seat();
	    seat.setTripId(idTrip);
	    seat.setUserId(idUser);
	    seat.setStatus(SeatStatus.SIN_PLAZA);
	    seat.setComment("Sin plaza");
	    try {
		sd.save(seat);
	    } catch (AlreadyPersistedException e) {
		System.out.println(e.getMessage());
	    }
	} else {
	    Seat seat = new Seat();
	    seat.setTripId(idTrip);
	    seat.setUserId(idUser);

	    sd.request(seat);
	}
    }

}
