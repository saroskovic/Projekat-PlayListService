package projekat.playList.services;

import java.util.List;

import projekat.playList.entities.Channel;


public interface ChannelService {

	Channel saveChannel(Channel channel);
	
	List<Channel> fetchChannelList();
	
	Channel getChannelById(Long channelId);
	
	Channel updateChannel(Channel channel);
	
	void deleteChannelById(Long channelId);
}
