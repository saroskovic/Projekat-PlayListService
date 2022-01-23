package projekat.playList.repositories;

import org.springframework.data.repository.CrudRepository;

import projekat.playList.entities.User;

public interface UserRepository extends CrudRepository<User, String> {

}
