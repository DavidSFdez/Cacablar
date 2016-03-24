package uo.sdi.business.impl.classes.seat;

import uo.sdi.business.exception.EntityNotFoundException;
import uo.sdi.infrastructure.Factories;
import uo.sdi.model.Seat;
import uo.sdi.model.SeatStatus;
import uo.sdi.persistence.SeatDao;

public class SeatsCancelSeat {

    public void cancel(Long idUser, Long idTrip) throws EntityNotFoundException {
	//TODO comprobar si existe en la tabla esta de mierda que se inventaron los profesores para tocar los cojones
	//TODO poner a cancelado, recoger la excepción si no existe para devolver fracaso
	SeatDao sd = Factories.persistence.createSeatDao();
	
	Seat seat = sd.findByUserAndTrip(idUser, idTrip);
	
	if(seat==null)
	    throw new EntityNotFoundException();
	
	//sd.remove(seat); ¿Cómo cojones borrabas?
    }

}
