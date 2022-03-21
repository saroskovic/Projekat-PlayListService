package projekat.playList.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.*;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Entity
@Data
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	//@Column(name = "user_id")
	private Long id;

	//@Column(name = "name")
	private String name;

	//@Column(name = "email")
	private String email;

	@JsonIgnore
	private String password;
	
	@OneToOne(mappedBy ="user",  cascade = CascadeType.REFRESH, fetch =	FetchType.LAZY)
	private PlayList playList;

	@ManyToMany(fetch = FetchType.EAGER)
	private List<Role> roles;

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User(Long id) {
		this.id = id;
	}

	public User(Long id, String name, PlayList playList) {
		super();
		this.id = id;
		this.name = name;
		this.playList = playList;
	}

	@Override
	public boolean equals(Object obj) {
		if(this == obj)
			return true;
		if(obj == null || this.getClass() != obj.getClass())
			return false;
		User user = (User) obj;
		return (this.id == user.id && this.name.equals(user.name));
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

	public PlayList getPlayList() {
		return playList;
	}

	public void setPlayList(PlayList playList) {
		this.playList = playList;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
