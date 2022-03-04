package projekat.playList.services;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import projekat.playList.entities.Video;
import projekat.playList.repositories.VideoRepository;

@Service
public class VideoServiceImpl implements VideoService {

	@Autowired
	VideoRepository videoRepository;
	
	@Override
	public Video saveVideo(Video video) {
		return videoRepository.save(video);
	}

	@Override
	public List<Video> fetchVideoList() {
		return videoRepository.findAll();
	}

	@Override
	public Video getVideoById(Long videoId) {
		return videoRepository.findById(videoId)
				.orElseThrow(() -> new NoSuchElementException(String.format("could not find video: %d", videoId)));
	}

	@Override
	public Video updateVideo(Video video) {
		Video newVideo = videoRepository.findById(video.getId())
							.orElseThrow(() -> new NoSuchElementException(String.format("could not find video: %d", video.getId())));
		if(video.getName() != null)
			newVideo.setName(video.getName());
		return videoRepository.save(newVideo);
	}

	@Override
	public void deleteVideoById(Long videoId) {
		Video video = videoRepository.findById(videoId)
						.orElseThrow(() -> new NoSuchElementException(String.format("could not find video: %d", videoId)));
		videoRepository.delete(video);
		
	}

}
