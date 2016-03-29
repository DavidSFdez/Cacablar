package uo.sdi.business.impl.classes.seat;

import java.util.Date;

import uo.sdi.business.exception.BusinessException;
import uo.sdi.business.exception.EntityNotFoundException;
import uo.sdi.infrastructure.Factories;
import uo.sdi.model.Seat;
import uo.sdi.model.SeatStatus;
import uo.sdi.model.Trip;
import uo.sdi.model.TripStatus;
import uo.sdi.persistence.SeatDao;
import uo.sdi.persistence.TripDao;
import uo.sdi.persistence.exception.NotPersistedException;

public class SeatsCancelSeat {

    public void cancel(Seat seat) throws EntityNotFoundException {
	SeatDao sd = Factories.persistence.createSeatDao();
	TripDao td = Factories.persistence.createTripDao();
	Long[] ids = { seat.getUserId(), seat.getTripId() };
	Seat s = sd.findById(ids);

	if (s == null)
	    throw new EntityNotFoundException(
		    "No existe el sitio que se intenta cancelar");

	Trip trip = td.findById(seat.getTripId());

	if (trip == null)
	    throw new EntityNotFoundException(
		    "Se ha producido un error: No existe el viaje asociado a la plaza");

	if (trip.getAvailablePax() == trip.getMaxPax()
		&& trip.getClosingDate().after(new Date())) {
	    trip.setAvailablePax(trip.getAvailablePax() - 1);
	    trip.setStatus(TripStatus.OPEN);
	    seat.setStatus(SeatStatus.EXCLUDED);
	} else if (trip.getClosingDate().after(new Date())) {
	    trip.setAvailablePax(trip.getAvailablePax() - 1);
	    seat.setStatus(SeatStatus.EXCLUDED);
	} else
	    throw new BusinessException("El viaje ya pasó");

	try {
	    sd.update(seat);
	    td.update(trip);
	} catch (NotPersistedException e) {
	    throw new EntityNotFoundException(
		    "Se ha producido un error cancelando la plaza");
	}

    }

}
