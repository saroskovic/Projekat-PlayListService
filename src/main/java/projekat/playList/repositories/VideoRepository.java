package projekat.playList.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import projekat.playList.entities.Video;

public interface VideoRepository extends JpaRepository<Video, Long> {

}
