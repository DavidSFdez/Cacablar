package uo.sdi.business;

import uo.sdi.model.Seat;

public interface SeatsService {

   Seat findByUserAndTrip(Long idUser,Long idTrip);

}
