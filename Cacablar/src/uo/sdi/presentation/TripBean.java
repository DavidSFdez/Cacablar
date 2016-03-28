package uo.sdi.presentation;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import uo.sdi.business.exception.EntityAlreadyExistsException;
import uo.sdi.business.exception.EntityNotFoundException;
import uo.sdi.infrastructure.Factories;
import uo.sdi.model.Application;
import uo.sdi.model.Seat;
import uo.sdi.model.Trip;

@ManagedBean(name = "trip")
@RequestScoped
public class TripBean implements Serializable {

    private static final long serialVersionUID = 1L;

    Trip trip;

    public TripBean() {
	trip = new Trip();
    }

   public boolean isPromoter(Long idTrip, Long idUser) {
	return Factories.services.createTripsService().findByIdandPromoter(
		idTrip, idUser) == null ? false : true;
    }

    public List<Seat> getSeats() {
	List<Seat> seats = new LinkedList<Seat>();
	if (trip.getId() != null)
	    seats = Factories.services.createSeatsService().findByTrip(trip.getId());
	return seats;
    }
    
    public List<Application> getApplications() {
	List<Application> applications = new LinkedList<>();
	if (trip.getId() != null)
	    applications = Factories.services.createSeatsService().findApplicationByTrip(trip.getId());
	return applications;
    }

    public String updateTrip(Long idUser) {
	try {
	    Factories.services.createTripsService().update(trip, idUser);
	} catch (EntityNotFoundException e) {
	    return "fracaso";
	}

	return "exito";
    }

    public String cancelTrip(Long idUser) {

	try {
	    Factories.services.createTripsService().cancel(trip, idUser);
	} catch (EntityNotFoundException e) {
	    return "fracaso";
	}
	return "exito";
    }

    public String saveTrip(Long idUser) {
	try {
	    Factories.services.createTripsService().save(trip, idUser);
	} catch (EntityAlreadyExistsException e) {
	    return "fracaso";
	}
	return "exito";
    }

    public String view(Long idTrip) {
	// revisar el facesconfig, no estoy seguro de que sea así como se cambie
	// de página aunque funcione
	try {
	    trip = Factories.services.createTripsService().findById(idTrip);
	} catch (EntityNotFoundException e) {
	    System.out.println("Fracaso view trip");
	    return "fracaso";
	}

	return "exito";
    }

    public Trip getTrip() {
	return trip;
    }

    public void setTrip(Trip trip) {
	this.trip = trip;
    }
    
    /**
     * Si el usuario es primitor, participante o ha pedido plaza
     */
    public boolean isUserRetated(Long idUser){
	if(trip.getPromoterId().equals(idUser))
	    // Es promotor
	    return true;
	
	try {
	    // Si tiene asiento
	    Factories.services.createSeatsService().findByUserAndTrip(idUser, trip.getId());
	    // Está sentado
	    return true;
	} catch (EntityNotFoundException ignored) {
	    // No tiene asiento
	}
	
	try {
	    // Si tiene petición
	    Factories.services.createSeatsService().findApplication(idUser, trip.getId());
	    // Tiene peticion
	    return true;
	} catch (EntityNotFoundException ignored) {
	    // No tiene petición
	}
	
	return false;
    }

}
