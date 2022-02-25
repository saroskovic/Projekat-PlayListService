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
import projekat.playList.services.PlayListVideoService;

@RestController
@RequestMapping(path = "/api/v1/playListVideos")
public class PlayListVideoController {

	@Autowired
	PlayListVideoService playListVideoService;
	

	@RequestMapping(method = RequestMethod.POST) // Dodavanje elemenata u playlistu
	public PlayListVideo addListItems(@RequestParam Long playListId, @RequestParam Long videoId) {	
		return playListVideoService.savePlayListVideo(playListId, videoId);
	}

	@RequestMapping(method = RequestMethod.GET) // Svi elementi playListe sortirani po rednom broju
	public List<PlayListVideo> userPlayList(@RequestParam Long userId) {
		return playListVideoService.fetchOrderedPlayListVideoByUserId(userId);
	}

	@RequestMapping(method = RequestMethod.DELETE) // Brisanje elemenata playliste
	public void dellPlayListVideo(@RequestParam Long playListVideoId) {
		playListVideoService.deletePlayListVideoById(playListVideoId);
	}

	@RequestMapping(method = RequestMethod.PUT) // Izmena redosleda elemenata u playlisti
	public PlayListVideo updateListItems(@RequestParam Long listItemsId, @RequestParam Integer newOrderNo) {
		return playListVideoService.updatePlayListVideo(listItemsId, newOrderNo);
	}
}
