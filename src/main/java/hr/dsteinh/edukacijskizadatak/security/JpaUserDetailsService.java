package hr.dsteinh.edukacijskizadatak.security;

import hr.dsteinh.edukacijskizadatak.service.security.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class JpaUserDetailsService implements UserDetailsService {

    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        return userService.findByUsername(username).orElseThrow(()
                -> new UsernameNotFoundException("Username: " + username + " not found"));
    }
}
