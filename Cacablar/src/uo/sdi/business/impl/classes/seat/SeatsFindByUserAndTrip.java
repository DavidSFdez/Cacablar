package uo.sdi.business.impl.classes.seat;

import uo.sdi.business.exception.EntityNotFoundException;
import uo.sdi.infrastructure.Factories;
import uo.sdi.model.Seat;
import uo.sdi.persistence.exception.NotPersistedException;

public class SeatsFindByUserAndTrip {

    public Seat find(Long idUser,Long idTrip) throws EntityNotFoundException {
	Long[] ids = {idUser, idTrip};
	
	try {
	    return Factories.persistence.createSeatDao().findById(ids);
	} catch (NotPersistedException e) {
	    throw new EntityNotFoundException();
	}
    }

}
