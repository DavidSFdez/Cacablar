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

   
    public String request(Long idUser, Long idTrip) {

	try {
	    Factories.services.createSeatsService().request(idTrip, idUser);
	} catch (EntityAlreadyExistsException e) {
	    return "fracaso";
	}

	return "exito";
    }

    public String cancelSeat(Long idUser, Long idTrip) {

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
