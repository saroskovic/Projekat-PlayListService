package projekat.playList.services;

import java.util.List;

import projekat.playList.entities.Video;

public interface VideoService {

	Video saveVideo(Video video);
	
	List<Video> fetchVideoList();
	
	Video getVideoById(Long videoId);
	
	Video updateVideo(Video video);
	
	void deleteVideoById(Long videoId);
}
