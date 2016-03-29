package uo.sdi.business;

import java.util.List;

import uo.sdi.business.exception.EntityAlreadyExistsException;
import uo.sdi.business.exception.EntityNotFoundException;
import uo.sdi.model.Application;
import uo.sdi.model.Seat;
import uo.sdi.model.SeatStatus;
import uo.sdi.persistence.exception.NotPersistedException;

public interface ApplicationService {

    Application find(Long idTrip, Long idUser) throws EntityNotFoundException;

    void remove(Long idUser, Long idTrip) throws EntityNotFoundException;

    List<Application> getToUpdate();

    void updateApplication(Application application, SeatStatus status) throws EntityAlreadyExistsException, EntityNotFoundException;

}
