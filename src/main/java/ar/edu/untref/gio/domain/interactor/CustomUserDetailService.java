package ar.edu.untref.gio.domain.interactor;

import ar.edu.untref.gio.domain.User;
import ar.edu.untref.gio.domain.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Optional;

@Transactional
@Service("customUserDetailService")
public class CustomUserDetailService implements UserDetailsService {

    @Resource(name = "defaultUserRepository")
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> optionalUser = this.userRepository.findByEmail(email);
        User user = getUserOrThrowException(email, optionalUser);
        boolean accountNonExpired = true;
        boolean credentialsNonExpired = true;
        boolean accountNonLocked = true;
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
                Boolean.TRUE, accountNonExpired, credentialsNonExpired,
                accountNonLocked, new ArrayList<GrantedAuthority>());
    }

    private User getUserOrThrowException(String email, Optional<User> optionalUser) {
        return optionalUser.orElseThrow(() -> new UsernameNotFoundException("User does not exist with the email: " + email));
    }
}
