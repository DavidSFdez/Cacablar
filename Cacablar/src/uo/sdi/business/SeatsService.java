package uo.sdi.business;


import java.util.List;

import uo.sdi.business.exception.EntityAlreadyExistsException;
import uo.sdi.business.exception.EntityNotFoundException;
import uo.sdi.model.Seat;

public interface SeatsService {

   Seat findByUserAndTrip(Long idUser,Long idTrip);

   void request(Long idTrip, Long idUser) throws EntityAlreadyExistsException;

   void cancelSeat(Long idUser, Long idTrip) throws EntityNotFoundException;

   List<Seat> findByTrip(Long id);

}
