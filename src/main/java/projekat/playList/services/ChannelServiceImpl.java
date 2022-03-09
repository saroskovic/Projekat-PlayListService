package projekat.playList.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

import projekat.playList.entities.Category;
import projekat.playList.entities.Channel;
import projekat.playList.repositories.CategoryRepository;
import projekat.playList.repositories.ChannelRepository;
import projekat.playList.repositories.PlayListRepository;

@Service
public class ChannelServiceImpl implements ChannelService {

	private static final Logger log = LoggerFactory.getLogger(ChannelServiceImpl.class);

	@Autowired
	ChannelRepository channelRepository;
	@Autowired
	PlayListRepository playListRepository;
	@Autowired
	CategoryRepository categoryRepository;
	
	@Override
	public Channel saveChannel(Channel channel) {
		if(channel.getName().equals(null)) throw new NullPointerException("Channel name can't be null!");
		return channelRepository.save(channel);
	}

	@Override
	public List<Channel> fetchChannelList() {
		return channelRepository.findAll();
	}

	@Override
	public Channel getChannelById(Long channelId) {
		return channelRepository.findById(channelId)
				.orElseThrow(() -> new NoSuchElementException(String.format("Could not find channel: %d", channelId)));
	}

	@Override
	public Channel updateChannel(Channel channel, Long channelId) {
		Channel newChannel = channelRepository.findById(channelId)
				.orElseThrow(() -> new NoSuchElementException(String.format("Could not find channel: %d", channelId)));
		if(channel.getName() != null)
			newChannel.setName(channel.getName());
		log.info("Channel with id {} updated succesfully!", channelId);
		return channelRepository.save(newChannel);

	}
	
	@Override
	public void deleteChannelById(Long channelId) {
		Channel channel = channelRepository.findById(channelId)
				.orElseThrow(() -> new NoSuchElementException(String.format("Could not find channel: %d", channelId)));
		channelRepository.delete(channel);	
	}

}
