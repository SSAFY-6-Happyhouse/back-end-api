package com.ssafy.happyhouse.security;

import com.ssafy.happyhouse.user.entity.User;
import com.ssafy.happyhouse.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = Optional.ofNullable(userRepository.findByUsername(username))
                .orElseThrow(()-> new UsernameNotFoundException(username + "이란 아이디 존재하지 않습니다."));
        return UserDetailsImpl.build(user.get());
    }
}
