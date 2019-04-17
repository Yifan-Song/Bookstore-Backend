package bookstorebackend.Config;


import bookstorebackend.Repository.UserRepository;
import bookstorebackend.Entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class RealUserDetailsService implements UserDetailsService { // custom UserService interface

    @Autowired
    UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) {
        UserEntity user = userRepository.findByUsername(username);
        if(user == null){
            throw new UsernameNotFoundException("User Not Found");
        }

        Collection<GrantedAuthority> grantedAuthority = new ArrayList<>();

        if (user.getRole().equals("ADMIN")) {
            grantedAuthority.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        }
        else if (user.getRole().equals("USER")) {
            grantedAuthority.add(new SimpleGrantedAuthority("ROLE_USER"));
        }

        List<SimpleGrantedAuthority> authorities = new ArrayList<>();

        authorities.add(new SimpleGrantedAuthority(user.getRole()));

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        return new User(user.getUsername(),encoder.encode(user.getPassword()) ,grantedAuthority);
    }

}