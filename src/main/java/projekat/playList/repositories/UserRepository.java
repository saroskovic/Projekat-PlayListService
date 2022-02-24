package projekat.playList.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import projekat.playList.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
