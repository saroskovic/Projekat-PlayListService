package projekat.playList.controllers;

import java.util.Collections;
import java.util.List;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import projekat.playList.entities.PlayListVideo;
import projekat.playList.entities.PlayList;
import projekat.playList.entities.User;
import projekat.playList.entities.Video;
import projekat.playList.repositories.PlayListRepository;
import projekat.playList.repositories.PlayListVideoRepository;
import projekat.playList.repositories.UserRepository;
import projekat.playList.repositories.VideoRepository;

@RestController
@RequestMapping(path = "/api/v1/playListItems")
public class PlayListVideoController {

	@Autowired
	PlayListVideoRepository playListVideoRepository;
	@Autowired
	PlayListRepository playListRepository;
	@Autowired
	VideoRepository videoRepository;

	@RequestMapping(method = RequestMethod.POST) // Dodavanje elemenata u playlistu
	public PlayListVideo addListItems(@RequestParam Long playListId, @RequestParam Long videoId) {
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

	@RequestMapping(method = RequestMethod.GET) // Svi elementi playListe sortirani po rednom broju
	public List<PlayListVideo> userPlayList(@RequestParam Long userId) {
		return playListVideoRepository.findAllByPlayListIdOrderByOrderNoAsc(userId);
	}

	@RequestMapping(method = RequestMethod.DELETE) // Brisanje elemenata playliste
	public PlayListVideo dellListItems(@RequestParam Long listItemsId) {
		if (playListVideoRepository.existsById(listItemsId)) {
			PlayListVideo listItem = playListVideoRepository.findById(listItemsId).get();
			playListVideoRepository.delete(listItem);
			List<PlayListVideo> listItems = playListVideoRepository.findAllByPlayListId(listItem.getPlayList().getId());
			for (PlayListVideo item : listItems) {
				if (item.getOrderNo() > listItem.getOrderNo()) {
					item.setOrderNo(item.getOrderNo() - 1);
					playListVideoRepository.save(item);
				}
			}
			return listItem;
		}
		return null;
	}

	@RequestMapping(method = RequestMethod.PUT) // Izmena redosleda elemenata u playlisti
	public PlayListVideo updateListItems(@RequestParam Long listItemsId, @RequestParam Integer newOrderNo) {
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
}
