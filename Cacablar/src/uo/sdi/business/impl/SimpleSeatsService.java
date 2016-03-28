package uo.sdi.business.impl;

import java.util.List;

import uo.sdi.business.SeatsService;
import uo.sdi.business.exception.EntityAlreadyExistsException;
import uo.sdi.business.exception.EntityNotFoundException;
import uo.sdi.business.impl.classes.application.ApplicationByTrip;
import uo.sdi.business.impl.classes.application.ApplicationFind;
import uo.sdi.business.impl.classes.seat.SeatsCancelSeat;
import uo.sdi.business.impl.classes.seat.SeatsFindByTrip;
import uo.sdi.business.impl.classes.seat.SeatsFindByUserAndTrip;
import uo.sdi.business.impl.classes.seat.SeatsRequest;
import uo.sdi.model.Application;
import uo.sdi.model.Seat;

public class SimpleSeatsService implements SeatsService{

    @Override
    public Seat findByUserAndTrip(Long idUser, Long idTrip) throws EntityNotFoundException {
	return new SeatsFindByUserAndTrip().find(idUser, idTrip);
    }

    @Override
    public void request(Long idTrip, Long idUser) throws EntityAlreadyExistsException {

	new SeatsRequest().request(idTrip,idUser);
	
    }

    @Override
    public void cancelSeat(Long idUser, Long idTrip) throws EntityNotFoundException {
	new SeatsCancelSeat().cancel(idUser,idTrip);
    }

    @Override
    public List<Seat> findByTrip(Long idTrip) {

	return new SeatsFindByTrip().find(idTrip);
    }

    @Override
    public Application findApplication(Long idUser, Long id) throws EntityNotFoundException {
	ApplicationFind action = new ApplicationFind();
	return action.find(idUser, id);
    }

    @Override
    public List<Application> findApplicationByTrip(Long id) {
	ApplicationByTrip action = new ApplicationByTrip();
	return action.find(id);
    }

}
