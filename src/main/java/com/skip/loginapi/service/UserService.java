package com.skip.loginapi.service;

import com.skip.loginapi.dto.RoleDTO;
import com.skip.loginapi.dto.UserDTO;
import com.skip.loginapi.dto.UserInsertDTO;
import com.skip.loginapi.dto.UserUpdateDTO;
import com.skip.loginapi.entities.Role;
import com.skip.loginapi.entities.User;
import com.skip.loginapi.repositories.RoleRepository;
import com.skip.loginapi.repositories.UserRepository;
import com.skip.loginapi.service.exceptions.NotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    private static Logger logger = LoggerFactory.getLogger(UserService.class);
    @Autowired
    public UserRepository userRepository;
    @Autowired
    public RoleRepository roleRepository;

    @Autowired
    public BCryptPasswordEncoder passwordEncoder;

    public Page<UserDTO> findAllPaged(Pageable pageable) {
        Page<User> list = userRepository.findAll(pageable);

        return list.map(x -> new UserDTO(x));
    }

    public UserDTO findById(Long id) {
        Optional<User> result = userRepository.findById(id);
        User entity = result.orElseThrow(() -> new NotFoundException("Failed to find user"));

        return new UserDTO(entity);
    }

    @Transactional
    public UserDTO insert(UserInsertDTO dto) {
        User entity = new User();
        copyDtoToEntity(dto, entity);
        entity.setPassword(passwordEncoder.encode(dto.getPassword()));
        entity = userRepository.save(entity);
        return new UserDTO(entity);
    }

    @Transactional
    public UserDTO update(Long id, UserUpdateDTO dto) {
        try {
            User entity = userRepository.getOne(id);
            copyDtoToEntity(dto,entity);

            //if provided update the password too
            if(dto.getPassword() !=null && !dto.getPassword().isEmpty()){
                entity.setPassword(passwordEncoder.encode(dto.getPassword()));
            }

            entity = userRepository.save(entity);
            return new UserDTO(entity);
        } catch (EntityNotFoundException exception) {
            throw new NotFoundException("Unexpected. The server was not able to update the user.");
        }
    }

    public void delete(Long id) {
        try {
            userRepository.deleteById(id);
        }catch (EmptyResultDataAccessException exception){
            throw new NotFoundException("Unexpected. The server was not able to delete the user.");
        }
    }

    private void copyDtoToEntity(UserDTO dto, User entity) {
        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setEmail(dto.getEmail());

        entity.getRoles().clear();
        for (RoleDTO roleDto : dto.getRoles()) {
            Role role = roleRepository.getOne(roleDto.getId());
            entity.getRoles().add(role);
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username);
        if(user == null){
            logger.error("User not Found"+username);
            throw new UsernameNotFoundException("Email not found.");
        }
        logger.info("User found:"+username);
        return user;
    }
}
