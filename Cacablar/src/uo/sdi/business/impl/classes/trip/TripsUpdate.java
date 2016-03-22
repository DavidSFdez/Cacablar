package uo.sdi.business.impl.classes.trip;

import uo.sdi.business.exception.EntityNotFoundException;
import uo.sdi.persistence.exception.NotPersistedException;

import uo.sdi.infrastructure.Factories;
import uo.sdi.model.Trip;

public class TripsUpdate {

    public void update(Trip trip, Long idUser) throws EntityNotFoundException {
	// TODO comprobar que el viaje sea promocionado por el usuario y esas milongas
	try{
	Factories.persistence.createTripDao().update(trip);
	}catch (NotPersistedException ex) {
		throw new EntityNotFoundException("Alumno no eliminado " + trip, ex);
	}
    }

}
