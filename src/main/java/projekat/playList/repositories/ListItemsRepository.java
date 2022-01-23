package projekat.playList.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import projekat.playList.entities.ListItems;

public interface ListItemsRepository extends CrudRepository<ListItems, Integer> {

	List<ListItems> findAllByPlayListId(String userId);
	
	List<ListItems> findAllByPlayListIdOrderByOrderNoAsc(String userId);
	

}
