package grdian.backendgrdian.repos;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import grdian.backendgrdian.entities.AppMessage;

@Repository
public interface AppMessageRepository extends CrudRepository<AppMessage, Long> {

}
