package anywr.test_spring.model;

import java.util.HashSet;
import java.util.Set;

public class User {
	private String username;
	private String password;
	private Set<Role> roles = new HashSet<>();
	public User() {
		super();
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Set<Role> getRoles() {
		return roles;
	}
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}	
	
	
}
