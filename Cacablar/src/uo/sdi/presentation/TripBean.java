package uo.sdi.presentation;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import uo.sdi.business.exception.EntityAlreadyExistsException;
import uo.sdi.business.exception.EntityNotFoundException;
import uo.sdi.infrastructure.Factories;
import uo.sdi.model.Application;
import uo.sdi.model.Seat;
import uo.sdi.model.SeatStatus;
import uo.sdi.model.Trip;

@ManagedBean(name = "trip")
@RequestScoped
public class TripBean implements Serializable {

    private static final long serialVersionUID = 1L;

    Trip trip;

    public TripBean() {
	trip = new Trip();
    }
    
    @PostConstruct
    private void actualizar()
    {
	//para que compruebes si está bien
	//actualizar estado de viajes ->
	//TRIP_UPDATE_STATUS=UPDATE ttrips SET status = 1 where availablepax>=maxpax AND closingdate < CURRENT_TIMESTAMP
	//recoger las peticiones pendientes de application para ponerlas a sin plaza->
	//APPLICATION_FIND_TO_UPDATE=SELECT * FROM TAPPLICATIONS where appliedtrips_id = (select id from ttrips where status = 1)
	//borrarlas de applications
	//APPLICATION_DELETE_TO_UPDATE=DELETE FROM TAPPLICATIONS where appliedtrips_id = (select id from ttrips where status=1)
	
	//P.D.-> Tal vez convenga juntar los dos últimos métodos en la capa de negocio, tú qué opinas?
	//Lo he hecho en métodos separados por la movida de que pertenecen a diferentes DAO
	
	
	Factories.services.createTripsService().updateTripsStatus();
	List<Application> applications = Factories.services.createApplicationService().getToUpdate();
	try {
	    Factories.services.createSeatsService().seatsToUpdate(applications);
	} catch (EntityAlreadyExistsException e) {
	  
	}
	
    }

    public boolean isPromoter(Long idUser) {
	return Factories.services.createTripsService().findByIdandPromoter(
		trip.getId(), idUser) == null ? false : true;
    }

    public boolean isInApplications(Long idUser) {
	try {
	    Factories.services.createApplicationService().find(trip.getId(),
		    idUser);
	} catch (EntityNotFoundException e1) {
	    return false;
	}
	return true;
    }

    public boolean isInSeats(Long idUser) {
	try {
	    Factories.services.createSeatsService().findByUserAndTrip(idUser,
		    trip.getId());

	} catch (EntityNotFoundException e) {
	    return false;
	}
	return true;

    }

    public boolean isSitting(Long idUser) {

	try {
	   Seat seat = Factories.services.createSeatsService().findByUserAndTrip(idUser,
		    trip.getId());
	   if(!seat.getStatus().equals(SeatStatus.ACCEPTED))
	       return false;
	} catch (EntityNotFoundException e) {
	   return false;
	}

	return true;

    }

    public List<Seat> getSeats() {
	List<Seat> seats = new LinkedList<Seat>();
	if (trip.getId() != null)
	    seats = Factories.services.createSeatsService().findByTrip(
		    trip.getId());
	return seats;
    }

    public List<Application> getApplications() {
	List<Application> applications = new LinkedList<>();
	if (trip.getId() != null)
	    applications = Factories.services.createSeatsService()
		    .findApplicationByTrip(trip.getId());
	return applications;
    }

    public Application getApplicationUser(Long idUser) {

	try {
	    if (trip.getId() != null)
		return Factories.services.createApplicationService().find(
			trip.getId(), idUser);
	} catch (EntityNotFoundException e) {
	    return null;
	}
	return null;
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
    public boolean isUserRelated(Long idUser) {
	if (isPromoter(idUser))
	    return true;

	if (isUserInSeats(idUser))
	    return true;

	if (isUserInApplications(idUser))
	    return true;

	return false;
    }

    public boolean isUserInSeats(Long idUser) {
	try {
	    Factories.services.createSeatsService().findByUserAndTrip(idUser,
		    trip.getId());

	} catch (EntityNotFoundException e) {
	    return false;
	}
	return true;

    }

    public boolean isUserInApplications(Long idUser) {
	try {
	    Factories.services.createApplicationService().find(trip.getId(),
		    idUser);
	} catch (EntityNotFoundException e1) {
	    return false;
	}
	return true;
    }

    public String cancelApplication(Long idUser, Long idTrip) {

	try {
	    Factories.services.createApplicationService()
		    .remove(idUser, idTrip);
	} catch (EntityNotFoundException e) {
	    return "fracaso";
	}

	return "exito";
    }

}
