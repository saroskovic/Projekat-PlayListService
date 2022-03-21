package projekat.playList.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
//@Table(name = "role")
@JsonIgnoreProperties({"handler", "hibernateLazyInitializer"})
@Data
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    //@Column(name = "role_id")
    private Long Id;

    //@Column(name = "role_name")
    private String name;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    private List<User> users = new ArrayList<>();

}
