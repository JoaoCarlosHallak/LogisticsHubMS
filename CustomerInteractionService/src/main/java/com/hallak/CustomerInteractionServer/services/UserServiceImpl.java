package com.hallak.CustomerInteractionServer.services;

import com.hallak.CustomerInteractionServer.dtos.UserDTO;
import com.hallak.CustomerInteractionServer.entities.RoleType;
import com.hallak.CustomerInteractionServer.entities.User;
import com.hallak.CustomerInteractionServer.repositories.UserRepository;
import jakarta.persistence.EntityExistsException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserDetailsService, UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;


    @Autowired
    public UserServiceImpl(ModelMapper modelMapper, UserRepository userRepository, PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.modelMapper = modelMapper;
    }



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElseThrow(() -> new EntityExistsException("User Not Found"));

    }

    @Override
    public UserDTO newUser(UserDTO userDTO) {
        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        userDTO.getRoles().add(RoleType.ROLE_CUSTOMER);
        return modelMapper.map(
                userRepository.save(modelMapper.map(userDTO, User.class)), UserDTO.class);


    }
}
