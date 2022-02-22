package projekat.playList.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
public class Video {

	@Id
	public String id;
	public String name;
	
	@OneToMany(mappedBy = "video", fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH})
	private List<ListItems> list = new ArrayList<>();

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	@JoinTable(name = "videoCategory", joinColumns =
				{@JoinColumn(name = "videoId", nullable = false, updatable = false)},
				inverseJoinColumns = {@JoinColumn(name = "categoryId", nullable = false, updatable = false)})
	private List<Category> categories = new ArrayList<>();
	
	public Video() {
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
	
	
}
