package app.security.service;

import app.repository.UserRepository;
import app.security.entity.CustomUserDetails;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
            return userRepo.findByEmail(username)
                    .map(CustomUserDetails::new)
                    .orElseThrow(() -> new UsernameNotFoundException("Username not found!"));
    }
}