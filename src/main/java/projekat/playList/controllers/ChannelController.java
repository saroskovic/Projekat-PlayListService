package projekat.playList.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import projekat.playList.entities.Channel;
import projekat.playList.repositories.ChannelRepository;

@RestController
@RequestMapping(path = "/api/v1/channels")
public class ChannelController {

	@Autowired
	ChannelRepository channelRepository;
	
	@RequestMapping(method = RequestMethod.POST)
	public Channel addChannel() {
		return null;
	}
}
