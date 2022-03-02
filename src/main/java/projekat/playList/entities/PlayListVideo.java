package projekat.playList.entities;

import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class PlayListVideo {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "playList")
	private PlayList playList;
	
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "video")
	private Video video;
	
	private Integer orderNo;

	public PlayListVideo() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public PlayListVideo(Long id, PlayList playList, Video video, Integer orderNo) {
		super();
		this.id = id;
		this.playList = playList;
		this.video = video;
		this.orderNo = orderNo;
	}

	@Override
	public boolean equals(Object obj) {
		if(this == obj)
			return true;
		if(obj == null || this.getClass() != obj.getClass())
			return false;
		PlayListVideo pLVideo = (PlayListVideo) obj;
		return (pLVideo.id == this.id && pLVideo.orderNo.equals(this.orderNo));
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(id, orderNo);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public PlayList getPlayList() {
		return playList;
	}

	public void setPlayList(PlayList playList) {
		this.playList = playList;
	}

	public Integer getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}

	public Video getVideo() {
		return video;
	}

	public void setVideo(Video video) {
		this.video = video;
	}

	
	
	
	
}
