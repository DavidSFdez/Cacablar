package uo.sdi.business.impl;

import uo.sdi.business.SeatsService;
import uo.sdi.business.impl.classes.seat.SeatsFindByUserAndTrip;
import uo.sdi.model.Seat;

public class SimpleSeatsService implements SeatsService{

    @Override
    public Seat findByUserAndTrip(Long idUser, Long idTrip) {

	return new SeatsFindByUserAndTrip().find(idUser, idTrip);
    }

}
