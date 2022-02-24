package projekat.playList.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import projekat.playList.entities.PlayList;

public interface PlayListRepository extends JpaRepository<PlayList, Long> {

}
