package grdian.backendgrdian.repos;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import grdian.backendgrdian.entities.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

	User findByEmailAddress(String emailAddress);

}
