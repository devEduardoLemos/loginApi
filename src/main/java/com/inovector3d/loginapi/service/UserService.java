package com.inovector3d.loginapi.service;

import com.inovector3d.loginapi.dto.RoleDTO;
import com.inovector3d.loginapi.dto.UserDTO;
import com.inovector3d.loginapi.dto.UserInsertDTO;
import com.inovector3d.loginapi.entities.Role;
import com.inovector3d.loginapi.entities.User;
import com.inovector3d.loginapi.repositories.RoleRepository;
import com.inovector3d.loginapi.repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserService {
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
        User entity = result.orElseThrow(() -> new RuntimeException("Failed to find user"));

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
    public UserDTO update(Long id, UserDTO dto) {
        try {
            User entity = userRepository.getOne(id);
            copyDtoToEntity(dto,entity);
            entity = userRepository.save(entity);
            return new UserDTO(entity);
        } catch (EntityNotFoundException exception) {
            throw new RuntimeException(exception);
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

    public void delete(Long id) {
        try {
            userRepository.deleteById(id);
        }catch (EmptyResultDataAccessException exception){
            throw new RuntimeException(exception);
        }
    }
}
