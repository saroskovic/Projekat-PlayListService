package projekat.playList.repositories;

import org.springframework.data.repository.CrudRepository;

import projekat.playList.entities.PlayList;

public interface PlayListRepository extends CrudRepository<PlayList, String> {

}
