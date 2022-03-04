package projekat.playList.services;

import java.util.List;

import projekat.playList.entities.PlayList;


public interface PlayListService {

	PlayList savePlayList(PlayList playList);
	
	List<PlayList> fetchPlayLists();
	
	PlayList getPlayListById(Long playListId);
	
	PlayList updatePlayList(PlayList playList);
	
	void deletePlayListById(Long playListId);
}
