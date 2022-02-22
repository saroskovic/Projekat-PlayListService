package projekat.playList.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import projekat.playList.entities.Video;
import projekat.playList.repositories.VideoRepository;

@RestController
@RequestMapping(path = "/api/v1/videos")
public class VideoController {

	
	@Autowired
	VideoRepository videoRepository;
	
	@RequestMapping(method = RequestMethod.GET)
	public Iterable<Video> allUsers(){
		return videoRepository.findAll();
	}
}
