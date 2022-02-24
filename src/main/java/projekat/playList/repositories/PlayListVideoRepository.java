package projekat.playList.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import projekat.playList.entities.PlayListVideo;

public interface PlayListVideoRepository extends JpaRepository<PlayListVideo, Long> {

	List<PlayListVideo> findAllByPlayListId(Long userId);
	
	List<PlayListVideo> findAllByPlayListIdOrderByOrderNoAsc(Long userId);
	

}
