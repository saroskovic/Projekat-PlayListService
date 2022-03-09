package projekat.playList.services;


import java.util.List;
import java.util.NoSuchElementException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import projekat.playList.entities.PlayList;
import projekat.playList.repositories.PlayListRepository;

@Service
public class PlayListServiceImpl implements PlayListService {

	private static final Logger log = LoggerFactory.getLogger(PlayListServiceImpl.class);

	@Autowired
	PlayListRepository playListRepository;

	@Override
	public PlayList savePlayList(PlayList playList) {

		return playListRepository.save(playList);
	}

	@Override
	public List<PlayList> fetchPlayLists() {
		return playListRepository.findAll();
	}

	@Override
	public PlayList getPlayListById(Long playListId) {
		return playListRepository.findById(playListId)
				.orElseThrow(() -> new NoSuchElementException(String.format("Could not find playlist: &d", playListId)));
				
	}

	@Override
	public PlayList updatePlayList(PlayList playList) {
		PlayList newPlayList = playListRepository.findById(playList.getId())
								.orElseThrow(() -> new NoSuchElementException(String.format("Could not find playlist: &d", playList.getId())));
		if(playList.getName() != null)
			newPlayList.setName(playList.getName());
		if(playList.getUser() != null)
			newPlayList.setUser(playList.getUser());
		if(playList.getPlayListOrderNo() != null)
			newPlayList.setPlayListOrderNo(playList.getPlayListOrderNo());
		if(playList.getChannel() != null)
			newPlayList.setChannel(playList.getChannel());
		return playListRepository.save(newPlayList);
	}

	@Override
	public void deletePlayListById(Long playListId) {
		PlayList playList = playListRepository.findById(playListId)
				.orElseThrow(() -> new NoSuchElementException(String.format("Could not find playlist: &d", playListId)));
		playListRepository.delete(playList);
	}
	
	

	
	
}
