package projekat.playList.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import projekat.playList.entities.PlayList;
import projekat.playList.entities.User;
import projekat.playList.repositories.PlayListRepository;
import projekat.playList.repositories.UserRepository;

@RestController
@RequestMapping(path = "/api/v1/playLists")
public class PlayListController {

	@Autowired
	PlayListRepository playListRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@RequestMapping(method = RequestMethod.POST)
	public PlayList addNewPlayList(@RequestParam String name, @RequestParam Long userId) {
		if(userRepository.existsById(userId)) {
			User user = userRepository.findById(userId).get();
			PlayList newPlayList = new PlayList();
			newPlayList.setName(name);
			newPlayList.setUser(user);
			return playListRepository.save(newPlayList);
		}
		return null;
	}
	
	
	@RequestMapping(method = RequestMethod.GET)
	public Iterable<PlayList> getAllPlayLists(){
		return playListRepository.findAll();
	}
}
