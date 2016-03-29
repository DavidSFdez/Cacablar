package uo.sdi.business.impl.classes.application;

import uo.sdi.business.exception.BusinessException;
import uo.sdi.business.exception.EntityAlreadyExistsException;
import uo.sdi.business.exception.EntityNotFoundException;
import uo.sdi.infrastructure.Factories;
import uo.sdi.model.Application;
import uo.sdi.model.Seat;
import uo.sdi.model.SeatStatus;
import uo.sdi.model.Trip;
import uo.sdi.persistence.exception.AlreadyPersistedException;
import uo.sdi.persistence.exception.NotPersistedException;

public class ApplicationsUpdate {

    public void updateToSeat(Application application, SeatStatus status)
	    throws EntityAlreadyExistsException, EntityNotFoundException {

	try {
	    Long[] ids = { application.getUserId(), application.getTripId() };

	    Factories.persistence.createApplicationDao().delete(ids);

	    Trip trip = Factories.persistence.createTripDao().findById(
		    application.getTripId());
	    if (status.equals(SeatStatus.ACCEPTED)) 
		if (trip.getAvailablePax() >= trip.getMaxPax())
		    throw new BusinessException("No quedan plazas disponibles");
		else{ 
		    trip.setAvailablePax(trip.getAvailablePax() + 1);
		    Factories.persistence.createTripDao().update(trip);
		    // No pongo a sin plaza los asientos porque ya lo hago al
		    // comprobar los viajes en el postconstructor de trip
		    // pero si quieres hacerlo tú eres libre XD
		}
	    

	    Seat seat = new Seat();
	    seat.setComment("");
	    seat.setStatus(status);
	    seat.setTripId(application.getTripId());
	    seat.setUserId(application.getUserId());

	    Factories.persistence.createSeatDao().save(seat);

	} catch (NotPersistedException e) {
	    throw new EntityNotFoundException(
		    "No existe la application que se intenta tratar");
	} catch (AlreadyPersistedException e) {

	    throw new EntityAlreadyExistsException(
		    "Ya existe el seat que se intenta tratar");
	}

    }

}
