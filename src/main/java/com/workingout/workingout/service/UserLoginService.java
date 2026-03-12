package com.workingout.workingout.service;

import com.workingout.workingout.dto.DTOMapper;
import com.workingout.workingout.dto.UserDTO;
import com.workingout.workingout.models.User;
import com.workingout.workingout.repository.UsersRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserLoginService implements UserDetailsService {
    private final UsersRepository usersRepository;
    private final DTOMapper userDTOMapper;
    private final PasswordEncoder passwordEncoder;

    public UserLoginService(UsersRepository usersRepository, DTOMapper userDTOMapper, PasswordEncoder passwordEncoder){
        this.usersRepository = usersRepository;
        this.userDTOMapper = userDTOMapper;
        this.passwordEncoder = passwordEncoder;
    }

    private boolean isThereAUserByUsername(String username){
        return usersRepository.findByUsername(username) != null;
    }
    public void addUserToDb(UserDTO userDTO) throws IllegalArgumentException{
        User user = userDTOMapper.toEntity(userDTO);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        if(user.getPassword().isBlank() || isThereAUserByUsername(user.getUsername())){
            throw new IllegalArgumentException();
        }
        usersRepository.save(user);
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        User user = usersRepository.findByUsername(username);

        if(user==null){
            throw new UsernameNotFoundException("Username not found");
        }

        return user;
    }
}
