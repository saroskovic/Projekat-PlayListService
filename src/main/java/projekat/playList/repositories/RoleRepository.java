package projekat.playList.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import projekat.playList.entities.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

    public Role findByName(String name);
}
