package anywr.test_spring.service;

import java.util.HashSet;
import java.util.Set;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import anywr.test_spring.model.ERole;
import anywr.test_spring.model.Role;
import anywr.test_spring.model.User;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = new User();
		user.setUsername("anywr");
		user.setPassword("$2a$10$VcdzH8Q.o4KEo6df.XesdOmXdXQwT5ugNQvu1Pl0390rmfOeA1bhS");
		Set<Role> roles = new HashSet<>();
		roles.add(new Role(ERole.ROLE_USER));
		user.setRoles(roles);

		return UserDetailsImpl.build(user);
	}
}
