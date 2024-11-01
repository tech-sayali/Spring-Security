package com.spring.service;

import com.spring.entity.AppUser;
import com.spring.payload.LoginDto;
import com.spring.repo.UserRepository;
import com.spring.utils.JwtUtil;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private JwtUtil jwtUtil;

    public ResponseEntity<?> createUser(AppUser appUser) {
        Optional<AppUser> username = userRepository.findByUsername(appUser.getUsername());
        if(username.isPresent()){
            return new ResponseEntity<>("Username already taken", HttpStatus.FORBIDDEN);
        }

        Optional<AppUser> email = userRepository.findByEmail(appUser.getEmail());
        if(email.isPresent()) {
            return new ResponseEntity<>("Email already taken", HttpStatus.FORBIDDEN);
        }
        appUser.setPassword(passwordEncoder.encode(appUser.getPassword()));
        appUser.setRole("ROLE_USER");
        userRepository.save(appUser);
        return new ResponseEntity<>(appUser,HttpStatus.CREATED);
    }

    public String loginUser(LoginDto loginDto) {
        Optional<AppUser> opUser = userRepository.findByUsername(loginDto.getUsername());
        if (opUser.isPresent()){
            AppUser user = opUser.get();
            if(passwordEncoder.matches(loginDto.getPassword(), user.getPassword())){
               return jwtUtil.generateToken(user.getUsername());
            }
            return null;
        }else{
            return null;
        }

    }
}
