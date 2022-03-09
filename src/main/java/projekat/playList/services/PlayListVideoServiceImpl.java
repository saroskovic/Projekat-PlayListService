package projekat.playList.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import projekat.playList.entities.PlayList;
import projekat.playList.entities.PlayListVideo;
import projekat.playList.entities.Video;
import projekat.playList.repositories.PlayListRepository;
import projekat.playList.repositories.PlayListVideoRepository;
import projekat.playList.repositories.VideoRepository;

@Service
public class PlayListVideoServiceImpl implements PlayListVideoService {

	private static final Logger log = LoggerFactory.getLogger(PlayListVideoServiceImpl.class);

	@Autowired
	PlayListVideoRepository playListVideoRepository;
	@Autowired
	PlayListRepository playListRepository;
	@Autowired
	VideoRepository videoRepository;
	
	@Override
	public PlayListVideo savePlayListVideo(Long playListId, Long videoId) {
		if (playListRepository.existsById(playListId)) {
			if (videoRepository.existsById(videoId)) {
				Video video = videoRepository.findById(videoId).get();
				PlayList playList = playListRepository.findById(playListId).get();
				PlayListVideo newListItems = new PlayListVideo();
				newListItems.setPlayList(playList);			
				newListItems.setVideo(video);
				Integer orderNo = playListVideoRepository.findAllByPlayListId(playListId).size() + 1;
				newListItems.setOrderNo(orderNo);
				return playListVideoRepository.save(newListItems);
			}
		}
		return null;
	}

	@Override
	public List<PlayListVideo> fetchPlayListVideoList() {
		return playListVideoRepository.findAll();
	}
	
	@Override
	public List<PlayListVideo> fetchOrderedPlayListVideoByUserId(Long userId) {
		return playListVideoRepository.findAllByPlayListIdOrderByOrderNoAsc(userId);
	}


	@Override
	public PlayListVideo getPlayListVideoById(Long playListVideoId) {
		if(playListVideoRepository.existsById(playListVideoId))
			return playListVideoRepository.getById(playListVideoId);
			return null;
	}

	@Override
	public PlayListVideo updatePlayListVideo(Long listItemsId, Integer newOrderNo) {
		if (playListVideoRepository.existsById(listItemsId)) {
			PlayListVideo listItem = playListVideoRepository.findById(listItemsId).get();
			List<PlayListVideo> listItems = playListVideoRepository.findAllByPlayListId(listItem.getPlayList().getId());
			playListVideoRepository.delete(listItem);
			for (PlayListVideo item : listItems) {
				if (item.getOrderNo() > listItem.getOrderNo()) {
					item.setOrderNo(item.getOrderNo() - 1);
					playListVideoRepository.save(item);
				}
			}
			for (PlayListVideo item : listItems) {
				if (item.getOrderNo() > newOrderNo - 1) {
					item.setOrderNo(item.getOrderNo() + 1);
				}
			}
			listItem.setOrderNo(newOrderNo);
			return playListVideoRepository.save(listItem);
		}
		return null;
	}

	@Override
	public void deletePlayListVideoById(Long playListVideoId) {
		if (playListVideoRepository.existsById(playListVideoId)) {
			PlayListVideo listItem = playListVideoRepository.findById(playListVideoId).get();
			playListVideoRepository.delete(listItem);
			List<PlayListVideo> listItems = playListVideoRepository.findAllByPlayListId(listItem.getPlayList().getId());
			for (PlayListVideo item : listItems) {
				if (item.getOrderNo() > listItem.getOrderNo()) {
					item.setOrderNo(item.getOrderNo() - 1);
					playListVideoRepository.save(item);
				}
			}
		}
		
	}

	
}
