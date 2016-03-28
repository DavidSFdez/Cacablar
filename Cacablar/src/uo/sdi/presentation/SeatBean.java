package uo.sdi.presentation;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import uo.sdi.business.exception.EntityAlreadyExistsException;
import uo.sdi.business.exception.EntityNotFoundException;
import uo.sdi.infrastructure.Factories;
import uo.sdi.model.Seat;

@ManagedBean(name = "seat")
@RequestScoped
public class SeatBean implements Serializable {

    private Seat seat;

    public SeatBean() {
	this.seat = new Seat();
    }

    public String view(Long idTrip) {

	return "exito";
    }

    public boolean isSitting(Long idUser, Long idTrip) {
	// TODO comprobar el asiento en la otra tabla, que no me acordé de la
	// puta tabla de mierda nueva
	// * Este comprueba que tiene asiento, habrá que hacer un
	// BeanApplication
	// en el que se compruebe si tiene una appalication y desde algun sitio
	// (viaje seguramente)
	// se comprobarán las cosas

	// Try-cacths en vez de ifs. Welcome to the slow way!
	try {
	    Factories.services.createSeatsService().findByUserAndTrip(idUser,
		    idTrip);

	} catch (EntityNotFoundException e) {
	    try {
		Factories.services.createApplicationService().find(idTrip,
			idUser);
	    } catch (EntityNotFoundException e1) {
		return false;
	    }
	}

	return true;

    }

    public String request(Long idUser, Long idTrip) {

	try {
	    Factories.services.createSeatsService().request(idTrip, idUser);
	} catch (EntityAlreadyExistsException e) {
	    return "fracaso";
	}

	return "exito";
    }

    public String CancelSeat(Long idUser, Long idTrip) {

	try {
	    Factories.services.createSeatsService().cancelSeat(idUser, idTrip);
	} catch (EntityNotFoundException e) {
	    return "fracaso";
	}

	return "exito";
    }

    public Seat getSeat() {
	return seat;
    }

    public void setSeat(Seat seat) {
	this.seat = seat;
    }

}
