package uo.sdi.business.impl.classes.trip;

import uo.sdi.business.exception.EntityNotFoundException;
import uo.sdi.persistence.exception.NotPersistedException;

import uo.sdi.infrastructure.Factories;
import uo.sdi.model.Trip;
import uo.sdi.model.TripStatus;

public class TripsCancel {

    public void cancel(Trip trip, Long idUser) throws EntityNotFoundException {
	// TODO Comprobar todas las condiciones para que se pueda cancelar, fechas, que sea promotor blabalbla
	// TODO poner a excluidos a toda la peña que tenga asientos solicitados

	try{
	trip.setStatus(TripStatus.CANCELLED);
	Factories.persistence.createTripDao().update(trip);
	}catch (NotPersistedException ex) {
		throw new EntityNotFoundException("Viaje no eliminado " + trip.getId(), ex);
	}
    }

}
