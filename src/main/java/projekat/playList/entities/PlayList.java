package projekat.playList.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class PlayList {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long id;
	
	public String name;
	
	@JsonIgnore
	@OneToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "user")
	public User user;
	@JsonIgnore
	@OneToMany(mappedBy = "playList", fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH})
	private List<PlayListVideo> list = new ArrayList<>();
	
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "channel")
	private Channel channel;
	
	private Integer playListOrderNo;

	public PlayList() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PlayList(Long id, String name, User user, List<PlayListVideo> list, Channel channel,
			Integer playListOrderNo) {
		super();
		this.id = id;
		this.name = name;
		this.user = user;
		this.list = list;
		this.channel = channel;
		this.playListOrderNo = playListOrderNo;
	}

	@Override
	public boolean equals(Object obj) {
		if(this == obj)
			return true;
		if(obj == null || this.getClass() != obj.getClass())
			return false;
		PlayList playList = (PlayList) obj;
		return (playList.id == this.id && playList.name.equals(this.name));
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(id, name);
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<PlayListVideo> getList() {
		return list;
	}

	public void setList(List<PlayListVideo> list) {
		this.list = list;
	}

	public Channel getChannel() {
		return channel;
	}

	public void setChannel(Channel channel) {
		this.channel = channel;
	}

	public Integer getPlayListOrderNo() {
		return playListOrderNo;
	}

	public void setPlayListOrderNo(Integer playListOrderNo) {
		this.playListOrderNo = playListOrderNo;
	}
	
	
}
