package projekat.playList.services;

import java.util.List;

import projekat.playList.entities.PlayListVideo;


public interface PlayListVideoService {

	PlayListVideo savePlayListVideo(Long playListId, Long videoId);
	
	List<PlayListVideo> fetchPlayListVideoList();
	
	List<PlayListVideo> fetchOrderedPlayListVideoByUserId(Long userId);
	
	PlayListVideo getPlayListVideoById(Long playListVideoId);
	
	PlayListVideo updatePlayListVideo(Long listItemsId, Integer newOrderNo);
	
	void deletePlayListVideoById(Long playListVideoId);
}
