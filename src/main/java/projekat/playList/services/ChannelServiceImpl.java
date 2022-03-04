package projekat.playList.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

import projekat.playList.entities.Channel;
import projekat.playList.repositories.ChannelRepository;

@Service
public class ChannelServiceImpl implements ChannelService {

	@Autowired
	ChannelRepository channelRepository;
	
	@Override
	public Channel saveChannel(Channel channel) {
		return channelRepository.save(channel);
	}

	@Override
	public List<Channel> fetchChannelList() {
		return channelRepository.findAll();
	}

	@Override
	public Channel getChannelById(Long channelId) {
		return channelRepository.findById(channelId)
				.orElseThrow(() -> new NoSuchElementException(String.format("Could not find channel: &d", channelId)));
	}

	@Override
	public Channel updateChannel(Channel channel) {
		Channel newChannel = channelRepository.findById(channel.getId())
				.orElseThrow(() -> new NoSuchElementException(String.format("Could not find channel: &d", channel.getId())));
		if(channel.getName() != null)
			newChannel.setName(channel.getName());
		return channelRepository.save(newChannel);

	}
	
	@Override
	public void deleteChannelById(Long channelId) {
		Channel channel = channelRepository.findById(channelId)
				.orElseThrow(() -> new NoSuchElementException(String.format("Could not find channel: &d", channelId)));
		channelRepository.delete(channel);	
	}

}
