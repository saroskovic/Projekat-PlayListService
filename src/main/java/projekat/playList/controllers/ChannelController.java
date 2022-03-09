package projekat.playList.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import projekat.playList.converters.ChannelConverter;
import projekat.playList.dto.ChannelDto;
import projekat.playList.entities.Channel;
import projekat.playList.services.ChannelService;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/channels")
public class ChannelController {

	@Autowired
	ChannelService channelService;

	@Autowired
	ChannelConverter converter;
	
	@RequestMapping(method = RequestMethod.POST)
	public ChannelDto addChannel(@RequestBody ChannelDto dto) {
		Channel channel = converter.dtoToEntity(dto);
		channelService.saveChannel(channel);
		return converter.entityToDto(channel);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/{channelId}")
	public ChannelDto findChannelById(@PathVariable Long channelId) {
		Channel channel = channelService.getChannelById(channelId);
		return converter.entityToDto(channel);
	}
	@RequestMapping(method = RequestMethod.GET)
	public List<ChannelDto> fetchAllChannels(){
		List<Channel> allChannels = channelService.fetchChannelList();
		return converter.entityToDto(allChannels);
	}
	@RequestMapping(method = RequestMethod.PUT, value = "/{channelId}")
	public ChannelDto updateChannel(@RequestBody ChannelDto dto, @PathVariable Long channelId){
		Channel channel = converter.dtoToEntity(dto);
		Channel newChannel = channelService.updateChannel(channel, channelId);
		return converter.entityToDto(newChannel);
	}
	@RequestMapping(method = RequestMethod.DELETE, value = "/{channelId}")
	public void deleteChannel(@PathVariable Long channelId){
		channelService.deleteChannelById(channelId);
	}
}
