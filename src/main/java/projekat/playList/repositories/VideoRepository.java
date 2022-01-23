package projekat.playList.repositories;

import org.springframework.data.repository.CrudRepository;

import projekat.playList.entities.Video;

public interface VideoRepository extends CrudRepository<Video, String> {

}
