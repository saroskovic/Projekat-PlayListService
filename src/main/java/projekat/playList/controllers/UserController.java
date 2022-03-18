package projekat.playList.controllers;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import projekat.playList.dto.UserDto;
import projekat.playList.entities.User;
import projekat.playList.repositories.UserRepository;
import projekat.playList.services.UserService;
import projekat.playList.util.Encryption;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/api/v1/users")
public class UserController {

	@Value("${spring.security.secret-key}")
	private String secretKey;

	@Value("${spring.security.token-duration}")
	private Integer tokenDuration;

	@Autowired
	private UserService userService;
	
	/*@RequestMapping(method = RequestMethod.GET)
	public Iterable<User> allUsers(){
		return userService.fetchUserList();
	}*/
	
	@RequestMapping(method = RequestMethod.GET, value = "/{userId}")
	public User getUserById(@PathVariable Long userId) {
		return userService.getUserById(userId);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public User addUser(@RequestBody User newUser) {
		return userService.saveUser(newUser);
	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "/{userId}")
	public User updateUser(@PathVariable Long userId, @RequestBody User user) {
		return userService.updateUser(user, userId);
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "{userId}")
	public void deleteUser(@PathVariable Long userId) {
		userService.deleteUserById(userId);
	}

	private String getJWTToken(User user) {
		List<GrantedAuthority> grantedAuthorities = AuthorityUtils
				.commaSeparatedStringToAuthorityList(user.getRole().getName());
		String token = Jwts.builder().setId("softtekJWT").setSubject(user.getEmail())
				.claim("authorities", grantedAuthorities.stream()
						.map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + this.tokenDuration))
				.signWith(SignatureAlgorithm.HS512, this.secretKey.getBytes()).compact();
		return "Bearer " + token;
	}

	@RequestMapping(path = "/login", method = RequestMethod.POST)
	public ResponseEntity<?> login(@RequestParam("user") String email, @RequestParam("password")
			String pwd) {
		User user = userService.getUserByEmail(email);
		if (user != null && Encryption.validatePassword(pwd, user.getPassword())) {
			String token = getJWTToken(user);
			UserDto userDto = new UserDto();
			userDto.setEmail(email);
			userDto.setToken(token);
			return new ResponseEntity<>(user, HttpStatus.OK);
		}
		return new ResponseEntity<>("Wrong credentials", HttpStatus.UNAUTHORIZED);
	}
	@Secured("ROLE_USER")
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> listUsers() {
		return new ResponseEntity<>(userService.fetchUserList(), HttpStatus.OK);
	}
}


