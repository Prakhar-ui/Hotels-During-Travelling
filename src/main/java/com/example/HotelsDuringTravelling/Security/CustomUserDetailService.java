package com.example.HotelsDuringTravelling.Security;

import com.example.HotelsDuringTravelling.Exception.UserNotFoundException;
import com.example.HotelsDuringTravelling.Model.User;
import com.example.HotelsDuringTravelling.Repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService {

    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        //Load User from DB by username
        User user = this.userRepository.findByEmail(username).orElseThrow(UserNotFoundException::new);
        return user;
    }
}

