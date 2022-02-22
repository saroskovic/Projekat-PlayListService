package projekat.playList.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
	public String id;
	public String name;
	@JsonIgnore
	@OneToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "user")
	public User user;
	@JsonIgnore
	@OneToMany(mappedBy = "playList", fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH})
	private List<ListItems> list = new ArrayList<>();
	
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "channel")
	private Channel channel;
	
	private Integer playListOrderNo;

	public PlayList() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
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

	public List<ListItems> getList() {
		return list;
	}

	public void setList(List<ListItems> list) {
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
