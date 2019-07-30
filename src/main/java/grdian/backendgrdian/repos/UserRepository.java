package grdian.backendgrdian.repos;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import grdian.backendgrdian.entities.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

}
