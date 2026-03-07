package com.workingout.workingout.service;

import com.workingout.workingout.dto.DTOMapper;
import com.workingout.workingout.dto.UserDTO;
import com.workingout.workingout.models.User;
import com.workingout.workingout.repository.UsersRepository;
import org.springframework.stereotype.Service;

@Service
public class UserLoginService {
    private final UsersRepository usersRepository;
    private final DTOMapper userDTOMapper;

    public UserLoginService(UsersRepository usersRepository, DTOMapper userDTOMapper){
        this.usersRepository = usersRepository;
        this.userDTOMapper = userDTOMapper;
    }

    public boolean isThereAUserByThisUsernameAndPassword(UserDTO userDTO){
        User user = userDTOMapper.toEntity(userDTO);
        return usersRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword())  != null;
    }
}
